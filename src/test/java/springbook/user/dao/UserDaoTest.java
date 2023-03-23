package springbook.user.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import springbook.user.domain.User;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/applicationContext.xml")
class UserDaoTest {
    @Autowired
    private UserDao dao;
    private User user;
    private User user2;
    private User user3;


    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        dao.deleteAll();

        user = new User("abcd", "park", "no1");
        user2 = new User("abcd2", "park2", "no2");
        user3 = new User("abcd3", "park3", "no3");
    }

    @Test
    void addAndGetTest() throws SQLException, ClassNotFoundException {
        assertThat(dao.getCount()).isEqualTo(0);
        dao.add(user);
        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);

        User findUser = dao.get("abcd");
        assertThat(findUser.getId()).isEqualTo(user.getId());
        assertThat(findUser.getName()).isEqualTo(user.getName());
        assertThat(findUser.getPassword()).isEqualTo(user.getPassword());
    }

    @Test
    void addAndGetErrorTest() throws SQLException, ClassNotFoundException {
        dao.add(user);

        assertThrows(EmptyResultDataAccessException.class, () -> dao.get("abcd2"));
    }
}