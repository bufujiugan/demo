package com.as;

public class User1 {

   private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User1{" +
                "id='" + id + '\'' +
                '}';
    }
}
