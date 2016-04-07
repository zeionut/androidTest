package com.example.svilupposw.listtodo;

/**
 * Created by svilupposw on 22/03/16.
 */
public class WhatToDo {

    private String id;
    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WhatToDo() {}

    public WhatToDo(String text) {
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}
