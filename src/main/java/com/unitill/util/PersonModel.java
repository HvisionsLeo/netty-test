package com.unitill.util;

/**
 * @Description:
 * @Author: Leo
 * @Date: 2018-05-04 上午 11:59
 */
public class PersonModel {

    private String orgNo;
    private String merNo;
    private String psw;
    private String name;
    private String age;

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public String getMerNo() {
        return merNo;
    }

    public void setMerNo(String merNo) {
        this.merNo = merNo;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PersonModel{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
