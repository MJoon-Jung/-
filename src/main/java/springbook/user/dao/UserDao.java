package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import springbook.user.domain.User;

public class UserDao {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(User user) throws SQLException {

        try (Connection con = dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement("INSERT INTO USERS(id, name, password) VALUES(?, ?, ?)");) {
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public User get(String id) throws SQLException {
        try (Connection con = dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement("SELECT * FROM USERS WHERE id=?");) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery();) {
                User user = null;
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getString("id"));
                    user.setName(rs.getString("name"));
                    user.setPassword(rs.getString("password"));
                }

                if (user == null) {
                    throw new EmptyResultDataAccessException(1);
                }
                return user;
            } catch (SQLException e) {
                throw e;
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    public void deleteAll() throws SQLException {
        try (Connection con = dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement("DELETE FROM USERS");) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public int getCount() throws SQLException {
        try (Connection con = dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement("SELECT COUNT(*) FROM USERS");) {
            try (ResultSet rs = pstmt.executeQuery();) {
                rs.next();
                return rs.getInt(1);
            } catch (SQLException e) {
                throw e;
            }
        } catch (SQLException e) {
            throw e;
        }
    }
}
