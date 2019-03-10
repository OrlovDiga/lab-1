package ru.avalon.java.ocpjp.labs.tasks.objects;

import java.awt.*;

public class CountryImpl implements Country {

    private String code;
    private String name;


    public CountryImpl(String code, String name) {
        this.code = code;
        this.name = name;
    }
    public CountryImpl(String codeAndName) {
        String[] temp = codeAndName.split(":");
        this.code = temp[0];
        this.name = temp[1];
    }


    @Override
    public String getCode() {
        return code;

    }


    @Override
    public String getName() {
        return name;
    }
}
