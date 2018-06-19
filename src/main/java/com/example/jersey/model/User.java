package com.example.jersey.model;

import java.util.Random;

public class User {

    private int id;
    private String name;
    private String token;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        token = generateToken();
    }

    public static String generateToken() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }
}
