package org.example.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "User")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;


    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "title")
    private String title;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }
    public User(String name, String password, String title) {
        this.name = name;
        this.password = password;
        this.title = title;
    }



    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
