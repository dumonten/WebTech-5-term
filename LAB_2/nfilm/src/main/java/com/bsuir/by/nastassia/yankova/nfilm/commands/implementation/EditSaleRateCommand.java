package com.bsuir.by.nastassia.yankova.nfilm.commands.implementation;

import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.bsuir.by.nastassia.yankova.nfilm.commands.Command;
import com.bsuir.by.nastassia.yankova.nfilm.commands.CommandHelper;
import com.bsuir.by.nastassia.yankova.nfilm.controller.LanguageResourceBundleProvider;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.CommandException;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.ServiceException;
import com.bsuir.by.nastassia.yankova.nfilm.services.UserService;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The EditSaleRateCommand class represents a command that is executed to handle the editing of the sale rate for a user.
 * It retrieves the user's ID and the new sale rate from the request parameters,
 * calls the UserService to update the user's sale rate by ID,
 * and then redirects the user to the admin page.
 */
public class EditSaleRateCommand implements Command {
    private static Logger logger = LogManager.getLogger(EditSaleRateCommand.class.getName());

    /**
     * Executes the logic for editing the sale rate for a user.
     * It retrieves the user's ID and the new sale rate from the request parameters,
     * calls the UserService to update the user's sale rate by ID,
     * and then redirects the user to the admin page.
     *
     * @param request the HttpServletRequest object
     * @return the URL of the admin page
     * @throws CommandException if an error occurs during command execution
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ResourceBundle bundle = LanguageResourceBundleProvider.getBundle(request.getSession());
        try {
            UserService service = UserService.getInstance();
            Integer idUser = Integer.parseInt(request.getParameter("idUser"));
            Double saleRate = Double.parseDouble(request.getParameter("saleRate"));

            service.updateUserSaleRateById(idUser, saleRate);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            throw new CommandException(bundle.getString("editSaleRateError"));
        }
        Command command = CommandHelper.getInstance().getCommandByName("to_admin_page");
        return command.execute(request);
    }
}