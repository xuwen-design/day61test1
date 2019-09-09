package com.pojo;

import java.util.Date;

/**
 * CREATE TABLE `users` (
 *   `uid` int(11) NOT NULL AUTO_INCREMENT,
 *   `uname` varchar(255) DEFAULT NULL,
 *   `pwd` varchar(255) DEFAULT NULL,
 *   `birthday` date DEFAULT NULL,
 *   PRIMARY KEY (`uid`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
 */
public class User {
    private Integer uid;
    private String uname;
    private String pwd;
    private Date birthday;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", pwd='" + pwd + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
