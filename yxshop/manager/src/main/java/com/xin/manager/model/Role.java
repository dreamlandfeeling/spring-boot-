package com.xin.manager.model;

import java.util.List;

public class Role {
    private Long rid;

    private String name;

    private List<Authority> authorities;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "Role{" +
                "rid=" + rid +
                ", name='" + name + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}