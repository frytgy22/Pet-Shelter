package org.itstep;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * The method checks by login AND second value, if the customer is in the DB
 */
public interface SqlQuery {
    static boolean existInBaseAND(Connection connection, String select, String login, String forEquals) throws SQLException {
        final String SELECT = "SELECT customers.login FROM customers WHERE login = ? AND " + select + " = ? ";
        String customerLogin = "";

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, forEquals);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                customerLogin = resultSet.getString("login");
            }

            if (customerLogin.equals(login)) {
                return true;
            }
        }
        return false;
    }

    /**
     * The method checks by login OR second value, if the customer is in the DB
     */
    static boolean existInBaseOR(Connection connection, String select, String login, String forEquals) throws SQLException {
        final String SELECT = "SELECT login, " + select + " FROM customers WHERE login = ? OR " + select + " = ? ";
        String customerLogin = "";
        String customerValue = "";

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, forEquals);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                customerLogin = resultSet.getString("login");
                customerValue = resultSet.getString(select);
            }

            if (customerLogin.equals(login) || customerValue.equals(forEquals)) {
                return true;
            }
        }
        return false;
    }
}
