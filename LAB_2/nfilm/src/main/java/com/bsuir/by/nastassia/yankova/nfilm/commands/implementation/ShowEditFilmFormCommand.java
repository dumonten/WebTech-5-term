package com.bsuir.by.nastassia.yankova.nfilm.commands.implementation;

import com.bsuir.by.nastassia.yankova.nfilm.commands.Command;
import com.bsuir.by.nastassia.yankova.nfilm.commands.CommandHelper;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.CommandException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The ShowEditFilmFormCommand class represents a command that is executed to show the form for editing a film.
 * It retrieves the film ID from the request parameters, sets it as an attribute in the request, 
 * and redirects to the "to_admin_page" command to navigate to the administration page.
 */
public class ShowEditFilmFormCommand implements Command {

    /**
     * Executes the logic for showing the form for editing a film.
     * Retrieves the film ID from the request parameters, sets it as an attribute in the request, 
     * and redirects to the "to_admin_page" command to navigate to the administration page.
     *
     * @param request the HttpServletRequest object
     * @return the URL of the administration page
     * @throws CommandException if an error occurs during command execution
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        request.setAttribute("editIdFilm", Integer.parseInt(request.getParameter("idFilm")));
        Command command = CommandHelper.getInstance().getCommandByName("to_admin_page");
        return command.execute(request);
    }
}