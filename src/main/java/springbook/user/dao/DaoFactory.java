package springbook.user.dao;

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class DaoFactory {
    public UserDao userDao() {
        UserDao userDao = new UserDao();
        userDao.setDataSource(dataSource());
        return userDao;
    }

    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost/toby");
        dataSource.setUsername("tobyuser");
        dataSource.setPassword("tobypwd");

        return dataSource;
    }
}
