package com.luxoft.cjp.april16.bankapp.service;

import com.luxoft.cjp.april16.bankapp.model.NoDB;

import java.lang.reflect.Field;
import java.util.Arrays;

public class TestService {
    /**
     * This method should analyze the fields o1 and o2.
     * It should compare all the fields with the help of equals,
     * except for those fields that are marked with an annotation
     *
     * @NoDB And return true, if all the fields are equal.
     * Also it should be able to compare the collections.
     */
    public static boolean isEquals(Object o1, Object o2) {


        Field[] fieldsObject1 = o1.getClass().getDeclaredFields();
        Field[] fieldsObject2 = o2.getClass().getDeclaredFields();

        // Filtering fields with annotation @NoDB
        fieldsObject1 = Arrays.stream(fieldsObject1).filter(fie -> fie.getAnnotation(NoDB.class) == null).toArray(Field[]::new);
        fieldsObject2 = Arrays.stream(fieldsObject2).filter(fie -> fie.getAnnotation(NoDB.class) == null).toArray(Field[]::new);

        // Return false if list of annotated fields for objects are different
        if (!Arrays.equals(fieldsObject1, fieldsObject2)) return false;

        for (int i = 0; i < fieldsObject1.length; i++) {
            fieldsObject1[i].setAccessible(true);
            fieldsObject2[i].setAccessible(true);

            try {
                if (!fieldsObject1[i].get(o1).equals(fieldsObject2[i].get(o2))) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return true;
        //throw new UnsupportedOperationException();
    }
}