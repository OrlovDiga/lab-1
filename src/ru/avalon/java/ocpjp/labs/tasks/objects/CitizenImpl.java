package ru.avalon.java.ocpjp.labs.tasks.objects;

public class CitizenImpl implements Citizen {

    private String lastName;
    private String name;
    private Country country;


    public CitizenImpl(String lastName, String name, Country country) {
        this.lastName = lastName;
        this.name = name;
        this.country = country;
    }


    public CitizenImpl(String lastName, String name) {
        this.lastName = lastName;
        this.name = name;
    }


    @Override
    public Country getCountry() {
        return country;
    }


    @Override
    public String getName() {
        return name;
    }


    @Override
    public String getLastName() {
        return lastName;
    }
}