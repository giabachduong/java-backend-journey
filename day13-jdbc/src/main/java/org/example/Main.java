package org.example;
import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {
        String url =
                "jdbc:mysql://localhost:3306/parcel_db";
        Connection connection = DriverManager.getConnection(
                url,
                "root",
                "Mothaiba123!"
        );
        System.out.println("Connected!");

        PreparedStatement stmt =
                connection.prepareStatement(
                        "SELECT * FROM parcels"
                );

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String sender = rs.getString("sender");
            String receiver = rs.getString("receiver");
            double weight = rs.getDouble("weight");
            String status = rs.getString("status");

            System.out.println("Parcel ID: " + id);
            System.out.println("Sender: " + sender);
            System.out.println("Receiver: " + receiver);
            System.out.println("Weight: " + weight);
            System.out.println("Status: " + status);
        }

    }
}