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
import com.bsuir.by.nastassia.yankova.nfilm.services.ReviewService;
import com.bsuir.by.nastassia.yankova.nfilm.services.FilmService;
import com.bsuir.by.nastassia.yankova.nfilm.units.Film;
import com.bsuir.by.nastassia.yankova.nfilm.units.Review;
import com.bsuir.by.nastassia.yankova.nfilm.units.User;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The ToFilmPageCommand class represents a command that is executed to navigate to a specific film page.
 * It retrieves the required data from the FilmService and ReviewService for displaying on the film page.
 * The film page displays information about a specific film, its reviews, and user-specific data.
 */
public class ToFilmPageCommand implements Command {

    private static Logger logger = LogManager.getLogger(ToFilmPageCommand.class.getName());

    /**
     * Executes the logic for navigating to the film page.
     * Retrieves the film and review data from the FilmService and ReviewService, sets the necessary attributes in the request.
     * Calculates the average rating for the film if there are reviews available.
     *
     * @param request the HttpServletRequest object
     * @return the URL of the film page
     * @throws CommandException if any error occurs during command execution
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ResourceBundle bundle = LanguageResourceBundleProvider.getBundle(request.getSession());
        try {
            Integer idUser = ((User)request.getSession().getAttribute("user")).getId();

            FilmService filmService = FilmService.getInstance();
            Integer idFilm = Integer.parseInt(request.getParameter("idFilm"));

            ReviewService reviewService = ReviewService.getInstance();
            Film currentFilm = filmService.getFilmById(idFilm);
            ArrayList<Review> reviewList = reviewService.getReviewsByFilmId(idFilm);

            Double rating = 0.0;
            if (reviewList.size() > 0) {
                for (Review review : reviewList) {
                    rating += review.getRating();
                }
                rating /= reviewList.size();
                currentFilm.setRating(rating);
            }
            request.setAttribute("film", currentFilm);
            request.setAttribute("filmStatus", filmService.getFilmStatus(idFilm, idUser));
            request.setAttribute("isFilmInFavorites", filmService.isFilmInFavorites(idFilm, idUser));
            request.setAttribute("reviewList", reviewList);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            throw new CommandException(bundle.getString("toFilmPageError"));
        }
        return PageURLMapper.FILM;
    }
}