package org.itstep;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * The method checks by login AND second value, if the customer is in the DB
 */
public interface SqlQuery {
    static Integer existInBaseAND(Connection connection, String select, String login, String forEquals) throws SQLException {
        final String SELECT = "SELECT customers.login, customers.customersID FROM customers WHERE login = ? AND " + select + " = ? ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, forEquals);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("customersID");
            }
        }
        return null;
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

    /**
     * get all goods from DB
     */
    static List<Goods> getGoods(Connection connection) throws SQLException {
        List<Goods> goodsList = new ArrayList<>();
        final String SELECT = "SELECT * FROM goods";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            Goods goods;
            while (resultSet.next()) {
                goods = new Goods(resultSet.getInt("goodsID"), resultSet.getString("name"), resultSet.getInt("counter"));
                goodsList.add(goods);
            }
        }
        return goodsList;
    }

    /**
     * get counter "?" in base
     */
    static Integer getCountId(Connection connection, String SELECT) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("COUNT(*)");
            }
        }
        return null;
    }
}
