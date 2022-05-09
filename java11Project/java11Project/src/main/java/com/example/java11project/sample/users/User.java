package com.example.java11project.sample.users;

import org.dizitart.no2.objects.Id;


public class User {
    @Id
    private String username;
    private String password;
    private String role;
    private String price;
    private String instrument;
    private String description;
    private String review;
    private String programare;
    private int status;


    public User(String username, String password, String role, String price, String instrument, String description, String review, String programare, int status) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.price = price;
        this.instrument = instrument;
        this.description = description;
        this.review = review;
        this.programare = programare;
        this.status = status;

    }

    public User() {
    }


    public String getUsername() {
        return username;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProgramare() {
        return programare;
    }


    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }


    public void setProgrmare(String progrmare) {
        this.programare = progrmare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return role != null ? role.equals(user.role) : user.role == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }


}

