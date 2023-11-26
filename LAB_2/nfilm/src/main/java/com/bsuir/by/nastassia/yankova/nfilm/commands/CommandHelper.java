package com.bsuir.by.nastassia.yankova.nfilm.commands;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.bsuir.by.nastassia.yankova.nfilm.commands.implementation.*;

/**
 * The CommandHelper class is a utility class that provides access to the available commands in the application.
 */
public final class CommandHelper {

    private static final CommandHelper instance = new CommandHelper();
    private Map<CommandName, Command> commands = new HashMap<>();

    private static Logger logger = LogManager.getLogger(CommandHelper.class.getName());

    /**
     * Private constructor to enforce singleton behavior.
     * Initializes the commands map with all available commands.
     */
    private CommandHelper() {
        commands.put(CommandName.TO_MAIN_PAGE, new ToMainPageCommand());
        commands.put(CommandName.TO_REGISTRATION_PAGE, new ToRegistrationPageCommand());
        commands.put(CommandName.TO_AUTHORIZATION_PAGE, new ToAuthorizationPageCommand());
        commands.put(CommandName.TO_USER_PAGE, new ToUserPageCommand());
        commands.put(CommandName.TO_FILM_PAGE, new ToFilmPageCommand());
        commands.put(CommandName.TO_ADMIN_PAGE, new ToAdminPageCommand());
        commands.put(CommandName.TO_CART_PAGE, new ToCartPageCommand());
        commands.put(CommandName.TO_FAVORITES_PAGE, new ToFavoritesPageCommand());
        commands.put(CommandName.REGISTRATION, new RegistrationCommand());
        commands.put(CommandName.AUTHORIZATION, new AuthorizationCommand());
        commands.put(CommandName.LOGOUT, new LogoutCommand());
        commands.put(CommandName.ADD_TO_CART, new AddToCartCommand());
        commands.put(CommandName.DELETE_FROM_CART, new DeleteFromCartCommand());
        commands.put(CommandName.PURCHASE_FILMS, new PurchaseFilmsCommand());
        commands.put(CommandName.ADD_REVIEW, new AddReviewCommand());
        commands.put(CommandName.DELETE_REVIEW, new DeleteReviewCommand());
        commands.put(CommandName.EDIT_REVIEW, new EditReviewCommand());
        commands.put(CommandName.EDIT_SALE_RATE, new EditSaleRateCommand());
        commands.put(CommandName.EDIT_FILM, new EditFilmCommand());
        commands.put(CommandName.SHOW_EDIT_REVIEW_FORM, new ShowEditReviewFormCommand());
        commands.put(CommandName.SHOW_EDIT_FILM_FORM, new ShowEditFilmFormCommand());
        commands.put(CommandName.SHOW_EDIT_SALE_RATE_FORM, new ShowEditSaleRateFormCommand());
        commands.put(CommandName.ADD_FILM, new AddFilmCommand());
        commands.put(CommandName.DELETE_FILM, new DeleteFilmCommand());
        commands.put(CommandName.DELETE_FROM_FAVORITES, new DeleteFromFavoritesCommand());
        commands.put(CommandName.ADD_TO_FAVORITES, new AddToFavoritesCommand());
        commands.put(CommandName.UNKNOWN_COMMAND, new UnknownCommand());
    }

    /**
     * Gets the singleton instance of the CommandHelper class.
     *
     * @return the CommandHelper instance
     */
    public static CommandHelper getInstance() {
        return instance;
    }

    /**
     * Retrieves the command object associated with the given command name.
     *
     * @param commandName the name of the command
     * @return the Command object corresponding to the command name, or the UnknownCommand if the command name is invalid
     */
    public Command getCommandByName(String commandName) {
        CommandName name = null;
        Command command = null;
        try {
            name = CommandName.valueOf(commandName.toUpperCase());
            command = commands.get(name);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            command = commands.get(CommandName.UNKNOWN_COMMAND);
        }
        return command;
    }
}
