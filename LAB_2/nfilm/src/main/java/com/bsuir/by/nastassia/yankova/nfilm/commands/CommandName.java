package com.bsuir.by.nastassia.yankova.nfilm.commands;

/**
 * The CommandName enum represents the available command names in the application.
 */
public enum CommandName {
    TO_MAIN_PAGE, // Command to navigate to the main page
    TO_REGISTRATION_PAGE, // Command to navigate to the registration page
    TO_AUTHORIZATION_PAGE, // Command to navigate to the sign in page
    TO_USER_PAGE, // Command to navigate to the user page
    TO_ADMIN_PAGE, // Command to navigate to the admin page
    TO_FILM_PAGE, // Command to navigate to the film page
    TO_CART_PAGE, // Command to navigate to the cart page
    TO_FAVORITES_PAGE, // Command to navigate to the favorites page
    REGISTRATION, // Command for user registration
    AUTHORIZATION, // Command for user sign in
    EDIT_SALE_RATE, // Command for editing a sale rate
    SHOW_EDIT_SALE_RATE_FORM, // Command to show the form for editing a sale rate
    LOGOUT, // Command for user logout
    ADD_FILM, // Command for adding a film
    DELETE_FILM, // Command for deleting a film
    EDIT_FILM, // Command for editing a film
    SHOW_EDIT_FILM_FORM, // Command to show the form for editing a film
    ADD_REVIEW, // Command for adding a review
    DELETE_REVIEW, // Command for deleting a review
    EDIT_REVIEW, // Command for editing a review
    SHOW_EDIT_REVIEW_FORM, // Command to show the form for editing a review
    ADD_TO_CART, // Command for adding a film to the cart
    DELETE_FROM_CART, // Command for deleting a film from the cart
    PURCHASE_FILMS, // Command for purchasing films in the cart
    ADD_TO_FAVORITES, // Command for adding a film to favorites
    DELETE_FROM_FAVORITES, // Command for deleting a film from favorites
    UNKNOWN_COMMAND // Command for handling unknown commands
}