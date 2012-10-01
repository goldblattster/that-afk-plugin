package com.minehq.thatafkplugin.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AFKLog {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    boolean saved;

    String text;
    AFKAction action;

    public AFKLog(String text, AFKAction action) {
        this.text = text;
        this.action = action;
    }

    private AFKLog(int id, String text, AFKAction action) {

    }

    public void save() {
        if (!this.saved) {

        }
    }
}
