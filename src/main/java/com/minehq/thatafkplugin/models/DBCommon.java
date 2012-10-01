package com.minehq.thatafkplugin.models;

import com.minehq.thatafkplugin.ThatAFKPlugin;
import com.minehq.thatafkplugin.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCommon {
    public DBCommon() {}

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("org.h2.Driver");

            return DriverManager.getConnection("jdbc:h2:" + ThatAFKPlugin.getPlugin().getDataFolder().getAbsolutePath() + "/afk_log", "thatafkplugin", "");
        } catch (ClassNotFoundException ex) {
            ThatAFKPlugin.getPlugin().printStackTrace(ex);
            return null;
        }
    }

    public void setup() {
        Connection con;
        Statement st;

        try {
            con = getConnection();
            st = con.createStatement();

            st.executeUpdate("CREATE TABLE IF NOT EXISTS log (" +
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "type VARCHAR(20) NOT NULL," +
                    "info VARCHAR(100) NOT NULL" +
                    ")");
        } catch (SQLException ex) {
            ThatAFKPlugin.getPlugin().printStackTrace(ex);
        }

        Log.info("Created logging database.");
    }
}
