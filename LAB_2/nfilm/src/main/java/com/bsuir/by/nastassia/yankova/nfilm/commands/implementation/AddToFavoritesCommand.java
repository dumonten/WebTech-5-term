package com.bsuir.by.nastassia.yankova.nfilm.commands.implementation;

import java.util.ResourceBundle;
import com.bsuir.by.nastassia.yankova.nfilm.commands.Command;
import com.bsuir.by.nastassia.yankova.nfilm.commands.CommandHelper;
import com.bsuir.by.nastassia.yankova.nfilm.controller.LanguageResourceBundleProvider;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.CommandException;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.ServiceException;
import com.bsuir.by.nastassia.yankova.nfilm.services.FavoritesService;
import com.bsuir.by.nastassia.yankova.nfilm.units.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The AddToFavoritesCommand class represents a command that is executed to add a film to a user's favorites.
 * It retrieves the idFilm and idUser from the request parameters, calls the FavoritesService to add the film to the user's favorites,
 * and then redirects the user to the favorites page.
 */
public class AddToFavoritesCommand implements Command {
    private static Logger logger = LogManager.getLogger(AddToFavoritesCommand.class.getName());

    /**
     * Executes the logic for adding a film to a user's favorites.
     * It retrieves the idFilm and idUser from the request parameters, calls the FavoritesService to add the film to the user's favorites,
     * and then redirects the user to the favorites page.
     *
     * @param request the HttpServletRequest object
     * @return the URL of the favorites page to redirect to
     * @throws CommandException if an error occurs during command execution
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ResourceBundle bundle = LanguageResourceBundleProvider.getBundle(request.getSession());
        try {
            FavoritesService service = FavoritesService.getInstance();
            Integer idUser = ((User)request.getSession().getAttribute("user")).getId();
            Integer idFilm = Integer.parseInt(request.getParameter("idFilm"));

            service.addFilmToFavorites(idFilm, idUser);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            throw new CommandException(bundle.getString("addToFavoritesError"));
        }
        Command command = CommandHelper.getInstance().getCommandByName("to_favorites_page");
        return command.execute(request);
    }
}