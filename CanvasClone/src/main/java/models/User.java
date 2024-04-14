/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author ruthvikg
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class User {

    private String name;
    private String email;
    private String department;
    private String usertype;
    private String studentId;
    private String password; // Store the hashed password instead of the plain text password
    private boolean isVerified;
    Connection conn = null;
    PreparedStatement stmt = null;

    // Constructor
    public User() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password");
    }

    // Set all fields at once
    public void setAllFields(String name, String email, String department, String usertype, String studentId, String password, boolean isVerified) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.usertype = usertype;
        this.studentId = studentId;
        this.password = password; // Hash the password
        this.isVerified = isVerified;
    }
    
    
    public void createUser() {

        try {
            String sql = "INSERT INTO Users (name, email, department, usertype, student_id, password_hash, is_verified) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, department);
            stmt.setString(4, usertype);
            stmt.setString(5, studentId);
            stmt.setString(6, password);
            stmt.setBoolean(7, isVerified);
            stmt.executeUpdate();
            System.out.println("User Record created successfully.");
        } catch (SQLException e) {
        }
    }
}
