package com.company.JavaMethodsForDatabaseMySQL;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.cj.jdbc.MysqlDataSource;

public class ComLineDSEx {

    public static MysqlDataSource getMySQLDataSource() {

        Properties props = new Properties();

        String fileName = "src/main/resources/db.properties";

        try (FileInputStream fis = new FileInputStream(fileName)) {
            props.load(fis);
        } catch (IOException ex) {
            Logger lgr = Logger.getLogger(ComLineDSEx.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        MysqlDataSource ds = new MysqlDataSource();
        ds.setURL(props.getProperty("mysql.url"));
        ds.setUser(props.getProperty("mysql.username"));
        ds.setPassword(props.getProperty("mysql.password"));

        return ds;
    }

    public static void main(String[] args) {

        MysqlDataSource ds = getMySQLDataSource();

        String query = "SELECT VERSION()";

        try (Connection con = ds.getConnection();
                PreparedStatement pst = con.prepareStatement(query);
                ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {

                String version = rs.getString(1);
                System.out.println(version);
            }
        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(ComLineDSEx.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}