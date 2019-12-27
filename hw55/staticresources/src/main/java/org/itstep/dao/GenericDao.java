package org.itstep.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class GenericDao<T> {

    protected Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public GenericDao(String connectionString, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(connectionString, user, password);
        DatabaseMetaData metaData = connection.getMetaData();
        if (metaData.supportsTransactionIsolationLevel(Connection.TRANSACTION_REPEATABLE_READ)) {
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        }
    }

    protected void startTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    protected void commit() throws SQLException {
        connection.commit();
        connection.setAutoCommit(true);
    }

    protected void rollback() throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
    }

    public void close() throws SQLException {
        connection.close();
    }

    public abstract void save(T data) throws SQLException;

    public abstract List<T> findAll() throws SQLException;

    public abstract void update(T data, int set) throws SQLException;

    public abstract void delete(T data) throws SQLException;
}
