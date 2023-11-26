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
 * The AddToCartCommand class represents a command that is executed to add a film to the user's cart.
 * It retrieves the idFilm and idUser from the request parameters, calls the CartService to add the film to the user's cart,
 * and then redirects the user to the cart page.
 */
public class AddToCartCommand implements Command {
    private static Logger logger = LogManager.getLogger(AddToCartCommand.class.getName());

    /**
     * Executes the logic for adding a film to the user's cart.
     * It retrieves the idFilm and idUser from the request parameters, calls the CartService to add the film to the user's cart,
     * and then redirects the user to the cart page.
     *
     * @param request the HttpServletRequest object
     * @return the URL of the cart page to redirect to
     * @throws CommandException if an error occurs during command execution
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ResourceBundle bundle = LanguageResourceBundleProvider.getBundle(request.getSession());
        try {
            CartService service = CartService.getInstance();
            Integer idUser = ((User)request.getSession().getAttribute("user")).getId();
            Integer idFilm = Integer.parseInt(request.getParameter("idFilm"));

            service.addFilmToCart(idFilm, idUser);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            throw new CommandException(bundle.getString("addToCartError"));
        }
        Command command = CommandHelper.getInstance().getCommandByName("to_cart_page");
        return command.execute(request);
    }
}