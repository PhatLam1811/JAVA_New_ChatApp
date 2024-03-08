package com.example.java_new_chatapp.Models;

public class User {
    private String id;
    private String name;

    // getters && setters
    public String getId() { return this.id; }
    public String getName() { return this.name; }

    // constructors
    public User() {
        this.id = "";
        this.name = "";
    }
    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // fake logics
    private static User _fakeAppUser;
    private static User _fakePartner;

    public static User getFakeAppUser() {
        if (_fakeAppUser == null) {
            _fakeAppUser = new User("user_id_0", "fake_app_user");
        }
        return _fakeAppUser;
    }
    public static User getFakePartner() {
        if (_fakePartner == null) {
            _fakePartner = new User("user_id_1", "fake_partner");
        }
        return _fakePartner;
    }
}
