package com.bsuir.by.nastassia.yankova.nfilm.commands.implementation;

import com.bsuir.by.nastassia.yankova.nfilm.commands.Command;
import com.bsuir.by.nastassia.yankova.nfilm.commands.CommandHelper;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.CommandException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The ShowEditReviewFormCommand class represents a command that is executed to show the form for editing a review.
 * It retrieves the review ID from the request parameters, sets it as an attribute in the request, 
 * and redirects to the "to_film_page" command to navigate to the film page.
 */
public class ShowEditReviewFormCommand implements Command {

    /**
     * Executes the logic for showing the form for editing a review.
     * Retrieves the review ID from the request parameters, sets it as an attribute in the request, 
     * and redirects to the "to_film_page" command to navigate to the film page.
     *
     * @param request the HttpServletRequest object
     * @return the URL of the film page
     * @throws CommandException if an error occurs during command execution
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        request.setAttribute("editIdReview", Integer.parseInt(request.getParameter("idReview")));
        Command command = CommandHelper.getInstance().getCommandByName("to_film_page");
        return command.execute(request);
    }
}