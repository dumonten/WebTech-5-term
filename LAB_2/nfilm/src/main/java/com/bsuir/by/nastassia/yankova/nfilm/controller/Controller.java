package com.bsuir.by.nastassia.yankova.nfilm.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;
import org.apache.logging.log4j.*;

import com.bsuir.by.nastassia.yankova.nfilm.commands.Command;
import com.bsuir.by.nastassia.yankova.nfilm.commands.CommandHelper;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.CommandException;

/**
 * The Controller class acts as the servlet controller in the application.
 */
public class Controller extends HttpServlet {
    
    private static final long serialVersionUID = 82011568739848723L;
    private static final String COMMAND = "command";
    private static Logger logger = LogManager.getLogger(Controller.class.getName());

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doCommand(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Executes the command based on the request and forwards to the appropriate page.
     *
     * @param request the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
    private void doCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResourceBundle bundle = LanguageResourceBundleProvider.getBundle(request.getSession());
        String commandName = request.getParameter(COMMAND);
        String page;
        if (commandName != null) {
            try {
                Command command = CommandHelper.getInstance().getCommandByName(commandName);
                page = command.execute(request);
            } catch (CommandException | NullPointerException e) {
                logger.error(e.getMessage());
                request.setAttribute("errorMessage", e.getMessage());
                page = PageURLMapper.ERROR;
            }
        } else if (request.getParameter("changeLocale") != null) {
            try {
                Command command = CommandHelper.getInstance().getCommandByName("to_main_page");
                page = command.execute(request);
            } catch (CommandException | NullPointerException e) {
                logger.error(e.getMessage());
                request.setAttribute("errorMessage", e.getMessage());
                page = PageURLMapper.ERROR;
            }
        } else {
            request.setAttribute("errorMessage", bundle.getString("noCommandError"));
            page = PageURLMapper.ERROR;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        } else {
            extractErrorMessageFromResponse(response);
        }
    }

    /**
     * Extracts an error message from the response and writes it to the output.
     *
     * @param response the HttpServletResponse object
     * @throws IOException if an I/O error occurs
     */
    private void extractErrorMessageFromResponse(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().println("Error");
    }
}