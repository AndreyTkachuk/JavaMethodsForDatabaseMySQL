package com.company.JavaMethodsForDatabaseMySQL;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcWriteImage {

    public static void main(String[] args) {

        String cs = "jdbc:mysql://localhost:3306/testdb?serverTimezone=Europe/Kiev&useSSL=false";
        String user = "root";
        String password = "root12345";

        String sql = "INSERT INTO Images(Data) VALUES(?)";

        try (Connection con = DriverManager.getConnection(cs, user, password);
                PreparedStatement pst = con.prepareStatement(sql)) {

            File myFile = new File("src/main/resources/tree.png");

            try (FileInputStream fin = new FileInputStream(myFile)) {

                pst.setBinaryStream(1, fin, (int) myFile.length());
                pst.executeUpdate();
                
            } catch (IOException ex) {
                
                Logger lgr = Logger.getLogger(JdbcWriteImage.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        } catch (SQLException ex) {
            
            Logger lgr = Logger.getLogger(JdbcWriteImage.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}