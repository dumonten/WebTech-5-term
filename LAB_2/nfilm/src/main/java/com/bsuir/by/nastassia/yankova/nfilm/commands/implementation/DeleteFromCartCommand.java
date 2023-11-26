package com.bsuir.by.nastassia.yankova.nfilm.commands.implementation;

import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.bsuir.by.nastassia.yankova.nfilm.commands.Command;
import com.bsuir.by.nastassia.yankova.nfilm.commands.CommandHelper;
import com.bsuir.by.nastassia.yankova.nfilm.controller.LanguageResourceBundleProvider;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.CommandException;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.ServiceException;
import com.bsuir.by.nastassia.yankova.nfilm.services.CartService;
import com.bsuir.by.nastassia.yankova.nfilm.units.User;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The DeleteFromCartCommand class represents a command that is executed to handle the removal of a film from the cart.
 * It retrieves the film ID and user ID from the request parameters, calls the CartService to remove the film from the user's cart,
 * and then redirects the user to the cart page.
 */
public class DeleteFromCartCommand implements Command {
    private static Logger logger = LogManager.getLogger(DeleteFromCartCommand.class.getName());

    /**
     * Executes the logic for removing a film from the cart.
     * It retrieves the film ID and user ID from the request parameters, calls the CartService to remove the film from the user's cart,
     * and then redirects the user to the cart page.
     *
     * @param request the HttpServletRequest object
     * @return the URL of the cart page
     * @throws CommandException if an error occurs during command execution
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ResourceBundle bundle = LanguageResourceBundleProvider.getBundle(request.getSession());
        try {
            CartService service = CartService.getInstance();
            Integer idFilm = Integer.parseInt(request.getParameter("idFilm"));
            Integer idUser = ((User) request.getSession().getAttribute("user")).getId();

            service.deleteFilmFromCart(idFilm, idUser);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            throw new CommandException(bundle.getString("deleteFromCartError"));
        }
        Command command = CommandHelper.getInstance().getCommandByName("to_cart_page");
        return command.execute(request);
    }
}