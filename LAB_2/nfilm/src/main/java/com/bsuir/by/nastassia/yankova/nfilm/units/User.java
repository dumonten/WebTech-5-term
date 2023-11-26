package com.bsuir.by.nastassia.yankova.nfilm.units;

/**
 * Class representing a user in the system.
 */
public class User {
    private Integer id;
    private String login;
    private Boolean isAdmin;
    private Double saleRate;

    /**
     * Constructs a User object with the specified properties.
     *
     * @param id        the ID of the user
     * @param login     the login of the user
     * @param isAdmin   true if the user is an admin, false otherwise
     * @param saleRate  the sale rate for the user
     */
    public User(Integer id, String login, Boolean isAdmin, Double saleRate) {
        this.id = id;
        this.login = login;
        this.isAdmin = isAdmin;
        this.saleRate = saleRate;
    }

    /**
     * Returns the ID of the user.
     *
     * @return the ID of the user
     */
    public Integer getId() {
        return id;
    }

    /**
     * Returns the login of the user.
     *
     * @return the login of the user
     */
    public String getLogin() {
        return login;
    }

    /**
     * Checks if the user is an admin.
     *
     * @return true if the user is an admin, false otherwise
     */
    public Boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Returns the sale rate for the user.
     *
     * @return the sale rate for the user
     */
    public Double getSaleRate() {
        return saleRate;
    }
}