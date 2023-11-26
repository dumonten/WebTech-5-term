package com.bsuir.by.nastassia.yankova.nfilm.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * The ConnectionPool class represents a connection pool for managing database connections.
 * It uses lazy initialization to create a single instance of the connection pool.
 * The maximum number of connections is configurable.
 */
public class ConnectionPool {
    private static ConnectionPool instance = null;

    private final String databaseUrl;
    private final String databaseUser;
    private final String databasePassword;

    private final int maxConnections = 25;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final List<Connection> connectionPool;
    private final List<Connection> usedConnections = new ArrayList<>();

    /**
     * Private constructor to prevent direct instantiation of the ConnectionPool class.
     * It reads the database connection details from the "database" resource bundle.
     */
    private ConnectionPool() {
        ResourceBundle bundle = ResourceBundle.getBundle("database");
        databaseUrl = bundle.getString("db.url") + bundle.getString("db.name");
        databaseUser = bundle.getString("db.user");
        databasePassword = bundle.getString("db.password");

        this.connectionPool = new ArrayList<>(maxConnections);
    }

    /**
     * Returns the single instance of the ConnectionPool class.
     *
     * @return the ConnectionPool instance
     */
    public synchronized static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    /**
     * Creates a new database connection.
     *
     * @return the new database connection
     * @throws SQLException if an error occurs while creating the connection
     */
    private Connection createConnection() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", databaseUser);
        properties.setProperty("password", databasePassword);
        return DriverManager.getConnection(databaseUrl, properties);
    }

    /**
     * Releases a database connection back to the connection pool.
     *
     * @param connection the database connection to be released
     */
    public synchronized void freeConnection(Connection connection) {
        lock.writeLock().lock();
        try {
            usedConnections.remove(connection);
            connectionPool.add(connection);
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Retrieves a database connection from the connection pool.
     * If the connection pool is not empty, a connection is taken from the pool.
     * If the connection pool is empty but the maximum number of connections has not been reached,
     * a new connection is created and added to the pool.
     * If the maximum number of connections has been reached, an exception is thrown.
     *
     * @return a database connection
     * @throws SQLException if an error occurs while getting a connection or if the maximum number of connections is exceeded
     */
    public synchronized Connection getConnection() throws SQLException {
        lock.readLock().lock();
        try {
            if (!connectionPool.isEmpty()) {
                Connection connection = connectionPool.remove(connectionPool.size() - 1);
                usedConnections.add(connection);
                return connection;
            } else {
                if (usedConnections.size() < maxConnections) {
                    Connection connection = createConnection();
                    usedConnections.add(connection);
                    return connection;
                } else {
                    throw new SQLException("Exceeded the maximum allowed connections.");
                }
            }
        } finally {
            lock.readLock().unlock();
        }
    }
    
    /**
     * Terminates all connections in the connection pool.
     * It closes all connections in the connection pool and the list of used connections.
     *
     * @throws SQLException if an error occurs while closing the connections
     */
    public void terminateConnections() throws SQLException {
        lock.writeLock().lock();
        try {
            for (Connection connection : connectionPool) {
                connection.close();
            }
            connectionPool.clear();
            for (Connection connection : usedConnections) {
                connection.close();
            }
            usedConnections.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }
}