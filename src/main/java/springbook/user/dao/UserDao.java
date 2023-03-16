package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import springbook.user.domain.User;

public class UserDao {
    private ConnectionMaker connectionMaker;

    public void setConnectionMaker(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection con = connectionMaker.makeNewConnection();

        PreparedStatement pstmt = con.prepareStatement(
                "INSERT INTO USERS(id, name, password) VALUES(?, ?, ?)");
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getPassword());

        pstmt.executeUpdate();

        pstmt.close();
        con.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection con = connectionMaker.makeNewConnection();

        PreparedStatement pstmt = con.prepareStatement("SELECT * FROM USERS WHERE id=?");
        pstmt.setString(1, id);

        ResultSet rs = pstmt.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        pstmt.close();
        con.close();

        return user;
    }

    public void deleteAll() throws SQLException, ClassNotFoundException {
        Connection con = connectionMaker.makeNewConnection();

        PreparedStatement pstmt = con.prepareStatement("DELETE FROM USERS");
        pstmt.executeUpdate();

        pstmt.close();
        con.close();
    }
}
