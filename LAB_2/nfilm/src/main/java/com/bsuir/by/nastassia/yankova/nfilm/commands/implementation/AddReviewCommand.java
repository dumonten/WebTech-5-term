package com.bsuir.by.nastassia.yankova.nfilm.commands.implementation;

import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.bsuir.by.nastassia.yankova.nfilm.commands.Command;
import com.bsuir.by.nastassia.yankova.nfilm.commands.CommandHelper;
import com.bsuir.by.nastassia.yankova.nfilm.controller.LanguageResourceBundleProvider;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.CommandException;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.ServiceException;
import com.bsuir.by.nastassia.yankova.nfilm.services.ReviewService;
import com.bsuir.by.nastassia.yankova.nfilm.units.User;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The AddReviewCommand class represents a command that is executed to add a review for a film.
 * It retrieves the idFilm, rating, description, and username from the request parameters, calls the ReviewService to add the review,
 * and then redirects the user to the film page.
 */
public class AddReviewCommand implements Command {
    private static Logger logger = LogManager.getLogger(AddReviewCommand.class.getName());

    /**
     * Executes the logic for adding a review for a film.
     * It retrieves the idFilm, rating, description, and username from the request parameters, calls the ReviewService to add the review,
     * and then redirects the user to the film page.
     *
     * @param request the HttpServletRequest object
     * @return the URL of the film page to redirect to
     * @throws CommandException if an error occurs during command execution
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ResourceBundle bundle = LanguageResourceBundleProvider.getBundle(request.getSession());
        try {
            ReviewService service = ReviewService.getInstance();
            String username = ((User)request.getSession().getAttribute("user")).getLogin();
            Integer idFilm = Integer.parseInt(request.getParameter("idFilm"));
            Integer rating = Integer.parseInt(request.getParameter("rating"));
            String description = request.getParameter("description");
            
            service.addReview(idFilm, username, rating, description);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            throw new CommandException(bundle.getString("addReviewError"));
        }
        Command command = CommandHelper.getInstance().getCommandByName("to_film_page");
        return command.execute(request);
    }
}