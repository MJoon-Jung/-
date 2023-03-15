package springbook.user.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springbook.user.domain.User;

class UserDaoTest {
    private UserDao dao = new DaoFactory().userDao();

    @BeforeEach
    void beforeEach() throws SQLException, ClassNotFoundException {
        dao.deleteAll();
    }

    @Test
    void addTest() throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setId("examId");
        user.setName("nickname");
        user.setPassword("1234567");

        dao.add(user);

        User newUser = dao.get("examId");
        assertThat(newUser.getId()).isEqualTo(user.getId());
        assertThat(newUser.getName()).isEqualTo(user.getName());
        assertThat(newUser.getPassword()).isEqualTo(user.getPassword());
    }
}