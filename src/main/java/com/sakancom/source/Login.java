package com.sakancom.source;

import java.sql.*;

public class Login {
    private final User user;

    public Login() {
        user = new User();
    }
    public User getUser() {
        return user;
    }

    public boolean canLogin() {
        String url = "jdbc:mysql://localhost:3306/sakancom";
        try {
            try (Connection conn = DriverManager.getConnection(url, "root", "Mohammad12002Sakancom")) {
                //language=sql
                String query = "SELECT * FROM users WHERE username = ? and password = ? and type = ?";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setString(1, user.getUsername());
                    ps.setString(2, user.getPassword());
                    ps.setString(3, user.getType().toString());
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()) {
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
