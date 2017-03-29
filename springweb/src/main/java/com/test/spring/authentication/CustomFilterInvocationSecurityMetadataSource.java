package com.test.spring.authentication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;

import com.test.spring.dao.ResourceDao;
import com.test.spring.entity.Resource;

public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	Logger logger = Logger.getLogger(CustomFilterInvocationSecurityMetadataSource.class.getName());
	private static LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> resourceMap = null; 
    private ResourceDao resourceDao;

	public CustomFilterInvocationSecurityMetadataSource(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
        resourceMap = loadResourceMatchAuthority();
    }

    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String url = ((FilterInvocation) object).getRequestUrl();
        HttpServletRequest request = ((FilterInvocation) object).getRequest();
        if(logger.isDebugEnabled()){
        	logger.info("requestUrl is " + url);
        }
        if (resourceMap == null) {
            loadResourceMatchAuthority();
        }
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {  
            if (entry.getKey().matches(request)) {  
                return entry.getValue();  
            }  
        }  
        return null;
    }

    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }

    private LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> loadResourceMatchAuthority() {
    	LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> map = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
        List<Resource> resources =  resourceDao.findEntityListByEntityName(Resource.class.getName());
        for (Resource resource: resources) {
            Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
            ConfigAttribute config = new SecurityConfig(resource.getName());
            configAttributes.add(config);
            if(StringUtils.isNotEmpty(resource.getUrl())){
            	map.put(new AntPathRequestMatcher(resource.getUrl()), configAttributes);
            }
        }
        return map;
    }

}