package com.dev.Splitwise.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity(name = "SplitWiseUser")
public class User extends BaseModel {
    private String name;
    private String email;
    private String password;
    @ManyToMany
//    @JsonBackReference
    private List<Group> groups;
    @ManyToMany
    private List<User> friends;
}
