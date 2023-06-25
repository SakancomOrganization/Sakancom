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
        try {
            String query = "SELECT * FROM users WHERE username = ? and password = ? and type = ?";
            String connectionUrl = "jdbc:mysql://localhost:3306/sakancom";
            Connection conn = DriverManager.getConnection(connectionUrl, "root", "");
            try {
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, this.username);
                ps.setString(2, this.password);
                ps.setString(3, this.type);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String type = rs.getString("type");
                    if (this.username.equals(username)
                            && this.password.equals(password)
                            && this.type.equals(type))
                        return true;
                }
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } //inner-catch
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
