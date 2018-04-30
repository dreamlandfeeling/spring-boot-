package com.xin.manager.dto;

import java.util.HashMap;
import java.util.Map;

public class Query extends HashMap<String,Object>{

    public Query(Map<String,Object> params) {
        this.putAll(params);
    }
}
