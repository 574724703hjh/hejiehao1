package com.life.good.db;


public class User {
    private int id;
    private String username;
    private String userpwd;
    private String email;
    private String imageName;
    private String mobile;
    private String age;
    private String gxqm;
    public String getGxqm() {
        return gxqm;
    }

    public void setGxqm(String gxqm) {
        this.gxqm = gxqm;
    }

    public User() {
    }

    public User(int id, String username, String userpwd, String email, String imageName, String mobile, String age,String gxqm) {
        this.id = id;
        this.username = username;
        this.userpwd = userpwd;
        this.email = email;
        this.imageName = imageName;
        this.mobile = mobile;
        this.age = age;
        this.gxqm = gxqm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }



}
