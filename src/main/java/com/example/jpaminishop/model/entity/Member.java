package com.example.jpaminishop.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Member {

    /*Field*/
    @Id
//    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private String id;


    private String username;


    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;




    public Member() {

    }
    public Member(String id, String username) {
        setId(id);
        setUsername(username);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
