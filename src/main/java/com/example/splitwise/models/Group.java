package com.example.splitwise.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "my_group")
public class Group extends BaseModel{
    private String name;
    @ManyToMany(mappedBy = "groups")
    private List<User> members;
    @ManyToOne
    private User admin;
    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Expense> expenses;
}
