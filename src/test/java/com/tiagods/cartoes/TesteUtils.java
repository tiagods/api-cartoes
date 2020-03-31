package com.tiagods.cartoes;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.Converter;

import java.lang.reflect.InvocationTargetException;

public class TesteUtils {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        BeanA beanA = new BeanA(1L, new BeanB(3L,"Hello"));
        BeanA beanA1 = new BeanA();

        BeanC beanC = new BeanC();

        BeanUtils.copyProperties(beanA1,beanA);
        BeanUtils.copyProperties(beanC,beanA);
        BeanUtils.setProperty(beanC,"beanId", beanA.getBean().getId());

        System.out.println(beanA1);
        System.out.println(beanC);



    }
}
