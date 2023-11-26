package com.bsuir.by.nastassia.yankova.nfilm.commands.implementation;

import jakarta.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.bsuir.by.nastassia.yankova.nfilm.commands.Command;
import com.bsuir.by.nastassia.yankova.nfilm.controller.LanguageResourceBundleProvider;
import com.bsuir.by.nastassia.yankova.nfilm.controller.PageURLMapper;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.CommandException;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.ServiceException;
import com.bsuir.by.nastassia.yankova.nfilm.services.AuthorizationService;
import com.bsuir.by.nastassia.yankova.nfilm.services.FilmService;
import com.bsuir.by.nastassia.yankova.nfilm.units.Film;
import com.bsuir.by.nastassia.yankova.nfilm.units.User;
import java.nio.charset.StandardCharsets;

/**
 * The RegistrationCommand class represents a command that is executed for user registration.
 * It retrieves the user login and password from the request parameters, performs password encryption using SHA-256,
 * calls the AuthorizationService to register the user, and sets the registered user in the session if registration is successful.
 * It also sets attributes related to film data if the user is redirected to the user page.
 */
public class RegistrationCommand implements Command {
    final static Integer NUMBER_OF_FILMS_PER_PAGE = 7;
    private static Logger logger = LogManager.getLogger(RegistrationCommand.class.getName());

    /**
     * Executes the logic for user registration.
     * Retrieves the user login and password from the request parameters,
     * performs password encryption using SHA-256,
     * calls the AuthorizationService to register the user,
     * and sets the registered user in the session if registration is successful.
     * It also sets attributes related to film data if the user is redirected to the user page.
     *
     * @param request the HttpServletRequest object
     * @return the URL of the corresponding page (user page or registration page)
     * @throws CommandException if an error occurs during command execution
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ResourceBundle bundle = LanguageResourceBundleProvider.getBundle(request.getSession());
        try {
            AuthorizationService authService = AuthorizationService.getInstance();
            FilmService filmService = FilmService.getInstance();
            MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
            StringBuilder hexString = new StringBuilder(); 

            // Encrypt the password using SHA-256
            byte[] encodedHash = msgDigest.digest(request.getParameter("password").getBytes(StandardCharsets.UTF_8));
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            String sha256Hash = hexString.toString();
            String page = null;
            try {
                User user = authService.registration(request.getParameter("login"), sha256Hash);
                request.getSession().setAttribute("user", user);
                request.getSession().setMaxInactiveInterval(100000);
                page = PageURLMapper.USER;
            } catch (ServiceException e) {
                logger.error(e.getMessage());
                request.setAttribute("failedRegistration", bundle.getString("registrationError"));
                page = PageURLMapper.REGISTER;
            }
            
            if (page == PageURLMapper.USER) {
                // Set attributes related to film data
                Integer filmAmount = filmService.getFilmAmount();
                request.setAttribute("filmAmount", filmAmount);
                request.setAttribute("numberOfFilmsPerPage", NUMBER_OF_FILMS_PER_PAGE);
                request.setAttribute("page", 1);
                ArrayList<Film> filmList = filmService.getFilmList(1);
                request.setAttribute("filmList", filmList);
            }

            return page;
        } catch (ServiceException | NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
            throw new CommandException(bundle.getString("criticalRegistrationError"));
        }
    }
}