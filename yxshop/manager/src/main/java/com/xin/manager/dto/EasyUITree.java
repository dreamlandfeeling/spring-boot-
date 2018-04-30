package com.xin.manager.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EasyUITree implements Serializable{
    private Long id;//商品编号
    private String text;//商品名称
    private String state = "open";//默认关闭状态closed,打开opened
    private List<EasyUITree> children;//子节点
    public static final String STATE_OPEN = "opened";
    public static final String STATE_CLOSED = "closed";

    public EasyUITree(){
    }

    public EasyUITree(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String isState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<EasyUITree> getChildren() {
        return children;
    }

    public void setChildren(List<EasyUITree> children) {
        this.children = children;
    }
}
