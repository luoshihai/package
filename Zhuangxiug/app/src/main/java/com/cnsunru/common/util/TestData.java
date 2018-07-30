package com.cnsunru.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cnsunrun on 2017-08-02.
 */

public class TestData {

    public static String TEST_IMAGE = "http://s9.knowsky.com/bizhi/l/35001-45000/200952904241438473283.jpg";


    public static <T> List<T> createTestData(int num, Class<T> claz) {
        try {
            T e = claz.newInstance();
            return createTestData(num, e);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static <T> List<T> createTestData(int num, T claz) {
        ArrayList<T> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(claz);
        }
        return list;
    }



}
