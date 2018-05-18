package com.xin.manager.model;

public class Authority {
    private Long aid;

    private String name;

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        return "Authority{" +
                "aid=" + aid +
                ", name='" + name + '\'' +
                '}';
    }
}