package com.client.utils;

import com.github.javafaker.Faker;

public class FakerUtils {

    public static String generateName(){
        Faker faker = new Faker();
        return "Board_" + faker.regexify("[A-Za-z0-9 ,_-]{10}");
    }
}
