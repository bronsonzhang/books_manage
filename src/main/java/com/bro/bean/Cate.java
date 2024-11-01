package com.bro.bean;

import com.bro.dao.CateDao;

public class Cate {
    private int id;
    private String name;

    public Cate() {
    }

    public Cate(int id) {
        this.id = id;
        CateDao cateDao = new CateDao();
        this.name = cateDao.queryNameById(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
