package com.test.ssh4.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real datasource like a database.
 */
public class LazyInfoDataModel extends LazyDataModel<Info> {
    
    private List<Info> datasource;
    
    public LazyInfoDataModel(List<Info> datasource) {
        this.datasource = datasource;
    }
    
    @Override
    public Info getRowData(String rowKey) {
        for(Info info : datasource) {
            if(info.getId().equals(rowKey))
                return info;
        }

        return null;
    }

    @Override
    public Object getRowKey(Info info) {
        return info.getId();
    }

    @Override
    public List<Info> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<Info> data = new ArrayList<Info>();

        //filter
        for(Info info : datasource) {
            boolean match = true;

            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        Field f1 = info.getClass().getDeclaredField(filterProperty);
                    	f1.setAccessible(true);
                        String fieldValue = String.valueOf(f1.get(info));

                        if(filterValue == null || fieldValue.contains(filterValue.toString())) {
                            match = true;
                    }
                    else {
                            match = false;
                            break;
                        }
                    } catch(Exception e) {
                        match = false;
                    }
                }
            }

            if(match) {
                data.add(info);
            }
        }

        //sort
        if(sortField != null) {
            Collections.sort(data, new LazySorter(sortField, sortOrder));
        }

        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);

        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }
}