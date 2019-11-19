package com.company.JavaMethodsForDatabaseMySQL;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcMulStat {

    public static void main(String[] args) throws SQLException {

        String cs = "jdbc:mysql://localhost:3306/"
                + "testdb?allowMultiQueries=true&useSSL=false";
        String user = "root";
        String password = "root12345";

        String query = "SELECT Id, Name FROM Authors WHERE Id=1;"
                + "SELECT Id, Name FROM Authors WHERE Id=2;"
                + "SELECT Id, Name FROM Authors WHERE Id=3";

        try (Connection con = DriverManager.getConnection(cs, user, password);
                PreparedStatement pst = con.prepareStatement(query);) {

            boolean isResult = pst.execute();

            do {
                try (ResultSet rs = pst.getResultSet()) {

                    while (rs.next()) {

                        System.out.print(rs.getInt(1));
                        System.out.print(": ");
                        System.out.println(rs.getString(2));
                    }

                    isResult = pst.getMoreResults();
                }

            } while (isResult);
        }
    }
}