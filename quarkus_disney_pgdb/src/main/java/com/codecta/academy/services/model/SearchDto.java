package com.codecta.academy.services.model;

public class SearchDto{
    private String name;
    private String greeting;

    public SearchDto() {
        this.name = null;
        this.greeting = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

}
