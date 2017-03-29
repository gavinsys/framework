package com.test.ssh4.model;

import java.lang.reflect.Field;
import java.util.Comparator;

import org.primefaces.model.SortOrder;


public class LazySorter implements Comparator<Info> {

    private String sortField;
    
    private SortOrder sortOrder;
    
    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    public int compare(Info info1, Info info2) {
        try {
        	Field f1 = Info.class.getDeclaredField(this.sortField);
        	f1.setAccessible(true);
            Object value1 = f1.get(info1);
            Field f2 = Info.class.getDeclaredField(this.sortField);
        	f2.setAccessible(true);
            Object value2 = f2.get(info2);
	
            int value = ((Comparable)value1).compareTo(value2);
            
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
        	throw new RuntimeException(e);
        }
    }
}