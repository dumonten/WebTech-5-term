package com.bsuir.by.nastassia.yankova.nfilm.listener;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bsuir.by.nastassia.yankova.nfilm.connectionpool.ConnectionPool;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

/**
 * The ShutdownListener class is a listener that handles the shutdown of the application.
 * It implements the ServletContextListener interface.
 */
public class ShutdownListener implements ServletContextListener {
    private static Logger logger = LogManager.getLogger(ShutdownListener.class.getName());

    /**
     * Performs cleanup operations when the servlet context is destroyed.
     *
     * @param sce the ServletContextEvent object
     */
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().terminateConnections();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
}