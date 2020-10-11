package com.pwc.addressbook.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PropertyCopier {
    public void copyProperties(Object customer, Object customerDTO, Set<String> properties) {
        BeanWrapper srcWrap = PropertyAccessorFactory.forBeanPropertyAccess(customer);
        BeanWrapper trgWrap = PropertyAccessorFactory.forBeanPropertyAccess(customerDTO);

        properties.forEach(p -> trgWrap.setPropertyValue(p, srcWrap.getPropertyValue(p)));
    }
}