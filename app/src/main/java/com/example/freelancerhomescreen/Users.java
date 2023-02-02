package com.example.freelancerhomescreen;

public class Users {
    int user_id;
    String name;
    int role;
    String email;
    String password;
    String description;
    int identityID;

    public Users(String name, int role, String email, String password, String description) {
        this.name = name;
        this.role = role;
        this.email = email;
        this.password = password;
        this.description = description;
    }
    public Users(int user_id,String name, int role, String email, String password, String description) {
        this.user_id = user_id;
        this.name = name;
        this.role = role;
        this.email = email;
        this.password = password;
        this.description = description;
    }

    public Users(int user_id,String name, int role, String email, String password, int identityID) {
        this.user_id = user_id;
        this.name = name;
        this.role = role;
        this.email = email;
        this.password = password;
        this.identityID = identityID;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public int getIdentityID() {
        return identityID;
    }

    public void setIdentityID(int identityID) {
        this.identityID = identityID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Users{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                ", identityID=" + identityID +
                '}';
    }
}
