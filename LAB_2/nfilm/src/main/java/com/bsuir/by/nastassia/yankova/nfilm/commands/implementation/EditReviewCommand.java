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

import jakarta.servlet.http.HttpServletRequest;

/**
 * The EditReviewCommand class represents a command that is executed to handle the editing of a review.
 * It retrieves the review ID, rating, and description from the request parameters,
 * calls the ReviewService to update the review in the database,
 * and then redirects the user to the film page.
 */
public class EditReviewCommand implements Command {
    private static Logger logger = LogManager.getLogger(EditReviewCommand.class.getName());

    /**
     * Executes the logic for editing a review.
     * It retrieves the review ID, rating, and description from the request parameters,
     * calls the ReviewService to update the review in the database,
     * and then redirects the user to the film page.
     *
     * @param request the HttpServletRequest object
     * @return the URL of the film page
     * @throws CommandException if an error occurs during command execution
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ResourceBundle bundle = LanguageResourceBundleProvider.getBundle(request.getSession());
        try {
            ReviewService service = ReviewService.getInstance();
            Integer idReview = Integer.parseInt(request.getParameter("idReview"));
            Integer rating = Integer.parseInt(request.getParameter("rating"));
            String description = request.getParameter("description");

            service.updateReviewById(idReview, rating, description);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            throw new CommandException(bundle.getString("editReviewError"));
        }
        Command command = CommandHelper.getInstance().getCommandByName("to_film_page");
        return command.execute(request);
    }
}