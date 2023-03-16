package springbook.user.dao;

public class DaoFactory {
    public ConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }

    public UserDao userDao() {
        UserDao userDao = new UserDao();
        userDao.setConnectionMaker(connectionMaker());
        return userDao;
    }
}
