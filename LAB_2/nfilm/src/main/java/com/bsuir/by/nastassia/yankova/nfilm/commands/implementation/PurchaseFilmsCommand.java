package com.bsuir.by.nastassia.yankova.nfilm.commands.implementation;

import java.util.ArrayList;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.bsuir.by.nastassia.yankova.nfilm.commands.Command;
import com.bsuir.by.nastassia.yankova.nfilm.controller.LanguageResourceBundleProvider;
import com.bsuir.by.nastassia.yankova.nfilm.controller.PageURLMapper;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.CommandException;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.ServiceException;
import com.bsuir.by.nastassia.yankova.nfilm.services.CartService;
import com.bsuir.by.nastassia.yankova.nfilm.services.FilmService;
import com.bsuir.by.nastassia.yankova.nfilm.units.Film;
import com.bsuir.by.nastassia.yankova.nfilm.units.User;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The PurchaseFilmsCommand class represents a command that is executed to handle the purchase of films from a user's cart.
 * It retrieves the user's ID from the session attribute, calls the CartService to purchase the films from the cart,
 * and sets the purchased films as a request attribute.
 * If the purchase is successful, it returns the URL of the user's cart page.
 * If the purchase fails, it throws a CommandException with an error message.
 */
public class PurchaseFilmsCommand implements Command {
    private static Logger logger = LogManager.getLogger(PurchaseFilmsCommand.class.getName());

    /**
     * Executes the logic for purchasing films from the user's cart.
     * Retrieves the user's ID from the session attribute, calls the CartService to purchase the films from the cart,
     * and sets the purchased films as a request attribute.
     * If the purchase is successful, it returns the URL of the user's cart page.
     * If the purchase fails, it throws a CommandException with an error message.
     *
     * @param request the HttpServletRequest object
     * @return the URL of the user's cart page
     * @throws CommandException if an error occurs during command execution
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ResourceBundle bundle = LanguageResourceBundleProvider.getBundle(request.getSession());
        try {
            Integer idUser = ((User) request.getSession().getAttribute("user")).getId();

            CartService cartService = CartService.getInstance();
            cartService.purchaseFilmsFromCart(idUser);

            FilmService filmService = FilmService.getInstance();
            ArrayList<Film> filmList = filmService.getFilmsInCartByUserId(idUser);

            request.setAttribute("filmList", filmList);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            throw new CommandException(bundle.getString("purchaseFilmsError"));
        }
        return PageURLMapper.USER_CART;
    }
}