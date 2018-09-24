package com.scms.pojo;

import java.sql.Timestamp;

public class Code {
    private Integer id;
    private String codename;
    private String filepath;
    private String intro;
    private User owner;
    private Timestamp addTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }


    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Code(Integer id, String codename, String filepath, String intro, User owner, Timestamp addTime) {
        this.id = id;
        this.codename = codename;
        this.filepath = filepath;
        this.intro = intro;
        this.owner = owner;
        this.addTime = addTime;
    }

    public Code() {
    }

    @Override
    public String toString() {
        return "Code{" +
                "id=" + id +
                ", codename='" + codename + '\'' +
                ", filepath='" + filepath + '\'' +
                ", intro='" + intro + '\'' +
                ", owner=" + owner +
                ", addTime=" + addTime +
                '}';
    }
}
