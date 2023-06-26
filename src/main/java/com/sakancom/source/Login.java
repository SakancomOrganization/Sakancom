package com.sakancom.source;

import java.sql.*;

public class Login {
    private String username;
    private String password;
    private String type;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean canLogin() {
        String url = "jdbc:mysql://localhost:3306/sakancom";
        try {
            try (Connection conn = DriverManager.getConnection(url, "root", "Mohammad12002Sakancom")) {
                String query = "SELECT * FROM users WHERE username = ? and password = ? and type = ?";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setString(1, this.username);
                    ps.setString(2, this.password);
                    ps.setString(3, this.type);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        String usernameDB = rs.getString("username");
                        String passwordDB = rs.getString("password");
                        String typeDB = rs.getString("type");
                        if (this.username.equals(usernameDB)
                                && this.password.equals(passwordDB)
                                && this.type.equals(typeDB))
                            return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
