package com.college.feedback.Entity;

import jakarta.persistence.*;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int sd;
    private int fs;
    private int nm;
    private int ss;
    private int aptitude;
    private int cloud;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    public Feedback() {}

    public Feedback(int sd, int fs, int nm, int ss, int aptitude, int cloud, User user) {
        this.sd = sd;
        this.fs = fs;
        this.nm = nm;
        this.ss = ss;
        this.aptitude = aptitude;
        this.cloud = cloud;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public int getSd() {
        return sd;
    }

    public void setSd(int sd) {
        this.sd = sd;
    }

    public int getFs() {
        return fs;
    }

    public void setFs(int fs) {
        this.fs = fs;
    }

    public int getNm() {
        return nm;
    }

    public void setNm(int nm) {
        this.nm = nm;
    }

    public int getSs() {
        return ss;
    }

    public void setSs(int ss) {
        this.ss = ss;
    }

    public int getAptitude() {
        return aptitude;
    }

    public void setAptitude(int aptitude) {
        this.aptitude = aptitude;
    }

    public int getCloud() {
        return cloud;
    }

    public void setCloud(int cloud) {
        this.cloud = cloud;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}