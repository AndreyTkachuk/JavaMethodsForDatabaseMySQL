package com.company.JavaMethodsForDatabaseMySQL;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcPreparedTesting {

    public static void main(String[] args) {

        String cs = "jdbc:mysql://localhost:3306/testdb?serverTimezone=Europe/Kiev&useSSL=false";
        String user = "root";
        String password = "root12345";

        String sql = "INSERT INTO Testing(Id) VALUES(?)";

        try (Connection con = DriverManager.getConnection(cs, user, password);
                PreparedStatement pst = con.prepareStatement(sql)) {

            for (int i = 1; i <= 5000; i++) {

                pst.setInt(1, i * 2);
                pst.executeUpdate();
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(JdbcPreparedTesting.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
