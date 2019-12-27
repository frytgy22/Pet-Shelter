package org.itstep.dao;

import org.itstep.domain.BuilderUser;
import org.itstep.domain.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends GenericDao<User> {

    private static final String INSERT = "INSERT INTO users(login, password) VALUES(?, ?)";
    private static final String SELECT = "SELECT userID, login, password, coins, `date` FROM users";
    private static final String UPDATE = "UPDATE users SET coins = ? WHERE userID = ?";
    private static final String DELETE = "DELETE FROM users WHERE userID = ? ";

    public UserDao(String connectionString, String user, String password) throws SQLException {
        super(connectionString, user, password);
    }

    @Override
    public void save(User data) throws SQLException {
        try {
            startTransaction();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, data.getLogin());
            preparedStatement.setString(2, data.getPassword());
            preparedStatement.execute();
            commit();
        } catch (SQLException e) {
            rollback();
        }
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        BuilderUser builderUser;

        try {
            startTransaction();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT);

            while (resultSet.next()) {
                builderUser = new BuilderUser();
                builderUser.setID(resultSet.getInt("userID"));
                builderUser.setLogin(resultSet.getString("login"));
                builderUser.setPassword(resultSet.getString("password"));
                builderUser.setCoins(resultSet.getInt("coins"));
                builderUser.setDate(resultSet.getString("date"));
                users.add(builderUser.build());
            }
            commit();
        } catch (SQLException e) {
            rollback();
        }
        return users;
    }


    public void update(User data, int coins) throws SQLException {
        try {
            startTransaction();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setInt(1, coins);
            preparedStatement.setInt(2, data.getId());
            preparedStatement.execute();
            commit();
        } catch (SQLException e) {
            rollback();
        }
    }

    @Override
    public void delete(User data) throws SQLException {
        try {
            startTransaction();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, data.getId());
            preparedStatement.executeUpdate();
            commit();
        } catch (SQLException e) {
            rollback();
        }
    }
}
