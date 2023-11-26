package com.bsuir.by.nastassia.yankova.nfilm.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.bsuir.by.nastassia.yankova.nfilm.units.Review;
import com.bsuir.by.nastassia.yankova.nfilm.units.Film;
import com.bsuir.by.nastassia.yankova.nfilm.units.User;
import com.bsuir.by.nastassia.yankova.nfilm.connectionpool.ConnectionPool;
import com.bsuir.by.nastassia.yankova.nfilm.dao.DatabaseDAO;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.DatabaseDAOException;

/**
 * The MySQLDatabaseDAO class is an implementation of the DatabaseDAO interface that interacts with a MySQL database.
 */
public final class MySQLDatabaseDAO implements DatabaseDAO {
	private final static MySQLDatabaseDAO instance = new MySQLDatabaseDAO();
	private final static ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	/**
     * Returns the singleton instance of the MySQLDatabaseDAO.
     * 
     * @return the singleton instance of the MySQLDatabaseDAO
     */
	public static DatabaseDAO getInstance() {
	    return instance;
	}

    /**
     * Authenticates a user with the provided login and password.
     *
     * @param login the user login
     * @param password the user password
     * @return the authenticated user
     * @throws DatabaseDAOException if an error occurs during the authorization process
     */
	@Override
	public User authorization(String login, String password) throws DatabaseDAOException {
		String sqlQuery = "SELECT u_id, u_login, u_is_admin, u_sale_rate FROM user WHERE u_login = ? AND u_password = ?";

		Connection connection = null;
    	PreparedStatement pStatement = null;
        ResultSet resSet = null;
		
		try {
            connection = connectionPool.getConnection();
            pStatement = connection.prepareStatement(sqlQuery);
            pStatement.setString(1, login);
            pStatement.setString(2, password);
            resSet = pStatement.executeQuery();
            if (!resSet.next()) {
            	throw new DatabaseDAOException();
            } else {
            	return new User(resSet.getInt("u_id"), 
            					resSet.getString("u_login"), 
            					resSet.getBoolean("u_is_admin"), 
            					resSet.getDouble("u_sale_rate"));
            }
        } catch (SQLException e) {
            throw new DatabaseDAOException(e.getMessage());
        } finally {
        	try {
        		connectionPool.freeConnection(connection);
        		if (pStatement != null) {
        			pStatement.close();        			
        		}
        		if (resSet != null) {
        			resSet.close();	
        		}
        	} catch (SQLException e) {
        		throw new DatabaseDAOException(e.getMessage());
        	}
        }
	}

	/**
     * Registers a new user with the provided login and password.
     *
     * @param login the user login
     * @param password the user password
     * @return the registered user
     * @throws DatabaseDAOException if an error occurs during the registration process
     */
	@Override
	public User registration(String login, String password) throws DatabaseDAOException {
      String sqlQueryInsert = "INSERT INTO user (u_login, u_password, u_is_admin, u_sale_rate) VALUES (?, ?, 0, 0.0)";
	    String sqlQuerySelect = "SELECT u_id FROM user WHERE u_login = ?";

	    Connection connection = null;
	    PreparedStatement pStatementInsert = null;
	    PreparedStatement pStatementSelect = null;
	    ResultSet resSet = null;
	    
	    try {
	        connection = connectionPool.getConnection();
	        pStatementInsert = connection.prepareStatement(sqlQueryInsert);
	        pStatementInsert.setString(1, login);
	        pStatementInsert.setString(2, password);
	        try {
	            pStatementInsert.executeUpdate();
	        } catch (SQLException e) {
	            throw new DatabaseDAOException(e.getMessage());
	        }
	        pStatementSelect = connection.prepareStatement(sqlQuerySelect);
	        pStatementSelect.setString(1, login);
	        resSet = pStatementSelect.executeQuery();
	        resSet.next();
	        return new User(resSet.getInt("u_id"), login, false, 0.0);
	    } catch (SQLException e) {
	        throw new DatabaseDAOException(e.getMessage());
	    } finally {
	        try {
	            connectionPool.freeConnection(connection);
	            if (pStatementInsert != null) 
	            {
	            	pStatementInsert.close();	            	
	            }
	            if (pStatementSelect != null) 
	            {
	            	pStatementSelect.close();
	            }	
	            if (resSet != null) 
	            {
	            	resSet.close();
	            }
	        } catch (SQLException e) {
	            throw new DatabaseDAOException(e.getMessage());
	        }
	    }
	}

	 /**
     * Adds a new film to the database with the specified details.
     *
     * @param title the film title
     * @param description the film description
     * @param genre the film genre
     * @param duration the film duration
     * @param director the film director
     * @param releaseYear the film release year
     * @param language the film language
     * @param country the film country
     * @param price the film price
     * @param imageURL the URL of the film image
     * @throws DatabaseDAOException if an error occurs while adding the film
     */
	@Override
	public void addFilm( String name, String description, String genre, String length, String director, Integer year, String language, String country, Double price, String URL)
	        throws DatabaseDAOException {
	    String sqlQuery = "INSERT INTO film (f_title, f_description, f_genre, f_duration, f_director, "
	    				+ "f_release_year, f_country, f_language, f_cover_image, f_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    Connection connection = null;
	    PreparedStatement statement = null;
	    
	    try {
	        connection = connectionPool.getConnection();
	        statement = connection.prepareStatement(sqlQuery);
        	statement.setString(1, name);
        	statement.setString(2, description);
        	statement.setString(3, genre);
        	statement.setString(4, length);
        	statement.setString(5, director);
        	statement.setInt(6, year);
        	statement.setString(7, country);
        	statement.setString(8, language);
        	statement.setString(9, URL);
        	statement.setDouble(10, price);
	        statement.executeUpdate();
	    } catch (SQLException e) {
	        throw new DatabaseDAOException(e.getMessage());
	    } finally {
	        try {
	            connectionPool.freeConnection(connection);
	            if (statement != null) {
	            	statement.close();
	            }
	        } catch (SQLException e) {
	            throw new DatabaseDAOException(e.getMessage());
	        }
	    }
	}	


	/**
     * Updates a film in the database with the specified ID and details.
     *
     * @param idFilm the ID of the film to update
     * @param title the updated film title
     * @param description the updated film description
     * @param genre the updated film genre
     * @param duration the updated film duration
     * @param director the updated film director
     * @param releaseYear the updated film release year
     * @param language the updated film language
     * @param country the updated film country
     * @param price the updated film price
     * @param imageURL the updated URL of the film image
     * @throws DatabaseDAOException if an error occurs while updating the film
     */
	@Override
	public void updateFilmById(Integer idFilm, String title, String description, String genre, String duration, String director, 
		 	    Integer release_year, String language, String country, Double price, String imageURL) throws DatabaseDAOException {
		String sqlQuery = "UPDATE film SET f_title = ?, f_description = ?, f_genre = ?, f_duration = ?, f_director = ?, f_release_year = ?, f_country = ?, f_language = ?, f_cover_image = ?, f_price = ? WHERE f_id = ?";
		
		Connection connection = null;
    	PreparedStatement statement = null;
        
    	try {
        	connection = connectionPool.getConnection();
        	statement = connection.prepareStatement(sqlQuery);
        	statement.setString(1, title);
        	statement.setString(2, description);
        	statement.setString(3, genre);
        	statement.setString(4, duration);
        	statement.setString(5, director);
        	statement.setInt(6, release_year);
        	statement.setString(7, country);
        	statement.setString(8, language);
        	statement.setString(9, imageURL);
        	statement.setDouble(10, price);
        	statement.setInt(11, idFilm);
        	statement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseDAOException(e.getMessage());
        } finally {
	        try {
	            connectionPool.freeConnection(connection);
	            if (statement != null) {
	            	statement.close();
	            }
	        } catch (SQLException e) {
	            throw new DatabaseDAOException(e.getMessage());
	        }
        }
	}
	
	/**
     * Deletes a film from the database with the specified ID.
     *
     * @param idFilm the ID of the film to delete
     * @throws DatabaseDAOException if an error occurs while deleting the film
     */
	@Override
	public void deleteFilmById(Integer idFilm) throws DatabaseDAOException {
		String sqlQuery = "DELETE FROM film WHERE f_id = ?";
		
		Connection connection = null;
    	PreparedStatement statement = null;
    	
    	try {
    		connection = connectionPool.getConnection();
        	statement = connection.prepareStatement(sqlQuery);
        	statement.setInt(1, idFilm);
        	statement.executeUpdate();
    	} catch (SQLException e) {
            throw new DatabaseDAOException(e.getMessage());
        } finally {
        	try {
        		connectionPool.freeConnection(connection);
        		if (statement != null) {
        			statement.close();
        		}
        	} catch (SQLException e) {
        		throw new DatabaseDAOException(e.getMessage());
        	}
        }
	}

	/**
     * Retrieves the total number of users in the database.
     *
     * @return the total number of users
     * @throws DatabaseDAOException if an error occurs while retrieving the user amount
     */
	@Override
	public Integer getUserAmount() throws DatabaseDAOException {
		String sqlQuery = "SELECT COUNT(*) FROM user";
			
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resSet = null;
		try {
			connection = connectionPool.getConnection();
			pStatement = connection.prepareStatement(sqlQuery);
			resSet = pStatement.executeQuery();
			resSet.next();
			return resSet.getInt(1);
		} catch (SQLException e) {
			throw new DatabaseDAOException(e.getMessage());
		} finally {
			try {
				connectionPool.freeConnection(connection);
				if (pStatement != null) { 
					pStatement.close(); 
				}
				if (resSet != null) { 
					resSet.close(); 
				}
			} catch (SQLException e) {
				throw new DatabaseDAOException(e.getMessage());
			}
		}
	}
	
	/**
     * Retrieves a list of users with pagination support.
     *
     * @param pageNumberInUserList the page number in the user list
     * @return a list of users
     * @throws DatabaseDAOException if an error occurs while retrieving the user list
     */
	@Override
	public ArrayList<User> getUserList(Integer pageNumberInUserList) throws DatabaseDAOException {
		String sqlQuery = "SELECT * FROM user ORDER BY u_id LIMIT ? OFFSET ?";
		
		final Integer numberOfUserPerPage = 5;
		Integer offset = (pageNumberInUserList - 1) * numberOfUserPerPage;
		Connection connection = null;
    	PreparedStatement pStatement = null;
    	ResultSet resSet = null;
        
    	try {
        	connection = connectionPool.getConnection();
        	pStatement = connection.prepareStatement(sqlQuery);
        	pStatement.setInt(1, numberOfUserPerPage);
        	pStatement.setInt(2, offset);
        	resSet = pStatement.executeQuery();
        	
        	ArrayList<User> userList = new ArrayList<User>();
        	while (resSet.next()) {
        		userList.add(new User(
        				resSet.getInt("u_id"), 
        				resSet.getString("u_login"), 
        				resSet.getBoolean("u_is_admin"), 
        				resSet.getDouble("u_sale_rate")));
        	}
        	
        	return userList;
        } catch (SQLException e) {
            throw new DatabaseDAOException(e.getMessage());
        } finally {
        	try {
        		connectionPool.freeConnection(connection);
        		if (pStatement != null) { 
        			pStatement.close(); 
        		}
        		if (resSet != null) { 
        			resSet.close(); 
        		}
        	} catch (SQLException e) {
        		throw new DatabaseDAOException(e.getMessage());
        	}
        }
	}
	
	/**
     * Retrieves a user with the specified ID.
     *
     * @param idUser the ID of the user to retrieve
     * @return the user with the specified ID
     * @throws DatabaseDAOException if an error occurs while retrieving the user
     */
	@Override
	public User getUserById(Integer idUser) throws DatabaseDAOException {
		String sqlQuery = "SELECT * FROM user WHERE u_id = ?";
		
		Connection connection = null;
    	PreparedStatement pStatement = null;
        ResultSet resSet = null;
        
        try {
        	connection = connectionPool.getConnection();
        	pStatement = connection.prepareStatement(sqlQuery);
        	pStatement.setInt(1, idUser);
        	resSet = pStatement.executeQuery();
        	resSet.next();
        	User user = new User(
        			resSet.getInt("u_id"), 
    				resSet.getString("u_login"), 
    				resSet.getBoolean("u_is_admin"), 
    				resSet.getDouble("u_sale_rate"));
        	return user;
        } catch (SQLException e) {
            throw new DatabaseDAOException(e.getMessage());
        } finally {
        	try {
        		connectionPool.freeConnection(connection);
        		if (pStatement != null) { 
        			pStatement.close(); 
        		}
        		if (resSet != null) { 
        			resSet.close(); 
        		}
        	} catch (SQLException e) {
        		throw new DatabaseDAOException(e.getMessage());
        	}
        }	
	}
	

	/**
     * Deletes the user with the specified login.
     *
     * @param login the login of the user to delete
     * @throws DatabaseDAOException if an error occurs while deleting the user
     */
	@Override
	public void deleteUserByLogin(String login) throws DatabaseDAOException {
		String sqlQuery = "DELETE FROM user WHERE login = ?";
		
		Connection connection = null;
    	PreparedStatement pStatement = null;
        try {
        	connection = connectionPool.getConnection();
        	pStatement = connection.prepareStatement(sqlQuery);
        	pStatement.setString(1, login);
        	pStatement.executeUpdate();
        	
        } catch (SQLException e) {
            throw new DatabaseDAOException(e.getMessage());
        } finally {
        	try {
        		connectionPool.freeConnection(connection);
        		if (pStatement != null) { 
        			pStatement.close(); 
        		}	
        	} catch (SQLException e) {
        		throw new DatabaseDAOException(e.getMessage());
        	}
        }
	}
	
	/**
     * Updates the sale rate of a user with the specified ID.
     *
     * @param idUser the ID of the user to update the sale rate
     * @param saleRate the new sale rate
     * @throws DatabaseDAOException if an error occurs while updating the sale rate
     */
	@Override
	public void updateUserSaleRateById(Integer idUser, Double saleRate) throws DatabaseDAOException {
		String sqlQuery = "UPDATE user SET u_sale_rate = ? WHERE u_id = ?";
		
		Connection connection = null;
    	PreparedStatement pStatement = null;
        
    	try {
        	connection = connectionPool.getConnection();
        	pStatement = connection.prepareStatement(sqlQuery);
        	pStatement.setDouble(1, saleRate);
        	pStatement.setInt(2, idUser);
        	pStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseDAOException(e.getMessage());
        } finally {
        	try {
        		connectionPool.freeConnection(connection);
        		if (pStatement != null) { 
        			pStatement.close(); 
        		}
        	} catch (SQLException e) {
        		throw new DatabaseDAOException(e.getMessage());
        	}
        }
	}

	
	/**
     * Retrieves the total number of films in the database.
     *
     * @return the total number of films
     * @throws DatabaseDAOException if an error occurs while retrieving the film amount
     */
	@Override
	public Integer getFilmAmount() throws DatabaseDAOException {
		String sqlQuery = "SELECT COUNT(*) FROM film";
		
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resSet = null;
		
		try {
            connection = connectionPool.getConnection();
            pStatement = connection.prepareStatement(sqlQuery);
            resSet = pStatement.executeQuery();
            resSet.next();
            return resSet.getInt(1);
        } catch (SQLException e) {
            throw new DatabaseDAOException(e.getMessage());
        } finally {
        	try {
        		connectionPool.freeConnection(connection);
        		if (pStatement != null) { 
        			pStatement.close(); 
        		}
        		if (resSet != null) { 
        			resSet.close(); 
        		}
        	} catch (SQLException e) {
        		throw new DatabaseDAOException(e.getMessage());
        	}
        }
	}
	
	/**
     * Retrieves a list of films with pagination support.
     *
     * @param pageNumberInFilmList the page number in the film list
     * @return a list of films for the specified page number
     * @throws DatabaseDAOException if an error occurs while retrieving the film list
     */
	@Override
	public ArrayList<Film> getFilmList(Integer pageNumberInFilmList) throws DatabaseDAOException {
	    String sqlQuery = "SELECT * FROM film ORDER BY f_id LIMIT ? OFFSET ?";

	    final Integer numberOfUserPerPage = 7;
	    Integer offset = (pageNumberInFilmList - 1) * numberOfUserPerPage;
	    Connection connection = null;
	    PreparedStatement pStatement = null;
	    ResultSet resSet = null;
	    
	    try {
	        connection = connectionPool.getConnection();
	        pStatement = connection.prepareStatement(sqlQuery);
	        pStatement.setInt(1, numberOfUserPerPage);
	        pStatement.setInt(2, offset);
	        resSet = pStatement.executeQuery();

	        ArrayList<Film> filmsList = new ArrayList<Film>();
	        while (resSet.next()) {
	            filmsList.add(new Film(
	                    resSet.getInt("f_id"),
	                    resSet.getString("f_description"),
	                    resSet.getString("f_title"),
	                    resSet.getString("f_duration"),
	                    resSet.getString("f_genre"),
	                    resSet.getString("f_director"),
	                    resSet.getInt("f_release_year"),
	                    resSet.getString("f_country"),
	                    resSet.getString("f_language"),
	                    resSet.getDouble("f_price"),
	                    resSet.getString("f_cover_image")));
	        }

	        return filmsList;
	    } catch (SQLException e) {
	        throw new DatabaseDAOException(e.getMessage());
	    } finally {
        	try {
        		connectionPool.freeConnection(connection);
        		if (pStatement != null) { 
        			pStatement.close(); 
        		}
        		if (resSet != null) { 
        			resSet.close(); 
        		}
        	} catch (SQLException e) {
        		throw new DatabaseDAOException(e.getMessage());
        	}
	    }
	}
	
	/**
     * Retrieves a film with the specified ID.
     *
     * @param idFilm the ID of the film to retrieve
     * @return the film with the specified ID
     * @throws DatabaseDAOException if an error occurs while retrieving the film
     */
	@Override
	public Film getFilmById(Integer idFilm) throws DatabaseDAOException {
		 String sqlQuery = "SELECT * FROM film WHERE f_id = ?";

		 Connection connection = null;
		 PreparedStatement pStatement = null;
		 ResultSet resSet = null;
		 try {	
			 connection = connectionPool.getConnection();
			 pStatement = connection.prepareStatement(sqlQuery);
			 pStatement.setInt(1, idFilm);
			 resSet = pStatement.executeQuery();
			 resSet.next();
			 Film film = new Film(
					 resSet.getInt("f_id"),
					 resSet.getString("f_description"),
					 resSet.getString("f_title"),
					 resSet.getString("f_duration"),
					 resSet.getString("f_genre"),
					 resSet.getString("f_director"),
					 resSet.getInt("f_release_year"),
					 resSet.getString("f_country"),
					 resSet.getString("f_language"),
					 resSet.getDouble("f_price"),
					 resSet.getString("f_cover_image"));
		        return film;
		 } catch (SQLException e) {
			 throw new DatabaseDAOException(e.getMessage());
		 } finally {
			 try {
				 connectionPool.freeConnection(connection);
				 if (pStatement != null) { 
					 pStatement.close(); 
				 }
				 if (resSet != null) { 
					 resSet.close(); 
				 }
			 } catch (SQLException e) {
				 throw new DatabaseDAOException(e.getMessage());
			 }
		 }
	}
		
	/**
     * Retrieves the status of a film for a user.
     *
     * @param idFilm the ID of the film
     * @param idUser the ID of the user
     * @return the status of the film for the user
     * @throws DatabaseDAOException if an error occurs while retrieving the film status
     */
	@Override
	public Integer getFilmStatus(Integer idFilm, Integer idUser) throws DatabaseDAOException {
		String sqlQuery = "SELECT uc_is_bought FROM user_cart WHERE uc_id_user = ? AND uc_id_film = ?";
		
		Connection connection = null;
    	PreparedStatement pStatement = null;
        ResultSet resSet = null;
        
        try {
        	connection = connectionPool.getConnection();
        	pStatement = connection.prepareStatement(sqlQuery);
        	pStatement.setInt(1, idUser);
        	pStatement.setInt(2, idFilm);
        	resSet = pStatement.executeQuery();
        	if (resSet.next()) {
        		return resSet.getInt(1);
        	} else {
        		return -1;
        	}
        } catch (SQLException e) {
                throw new DatabaseDAOException(e.getMessage());
        } finally {
        	try {
        		connectionPool.freeConnection(connection);
        		if (pStatement != null) { 
        			pStatement.close(); 
        		}
        		if (resSet != null) { 
        			resSet.close(); 
        		}
        	} catch (SQLException e) {
        		throw new DatabaseDAOException(e.getMessage());
        	}
        }
	}
	
	/**
     * Retrieves a list of films in the cart for a user.
     *
     * @param idUser the ID of the user
     * @return a list of films in the cart for the user
     * @throws DatabaseDAOException if an error occurs while retrieving the films in the cart
     */
	@Override
	public ArrayList<Film> getFilmsInCartByUserId(Integer idUser) throws DatabaseDAOException {
	    String sqlQuery= "SELECT * FROM user_cart WHERE uc_id_user = ? AND uc_is_bought = 0 ORDER BY uc_id_film";

	    Connection connection = null;
	    PreparedStatement pStatement = null;
	    ResultSet resSet = null;
	    
	    try {
	        connection = connectionPool.getConnection();
	        pStatement = connection.prepareStatement(sqlQuery);
	        pStatement.setInt(1, idUser);
	        resSet = pStatement.executeQuery();
	        ArrayList<Film> filmsList = new ArrayList<Film>();
	        while (resSet.next()) {
	            filmsList.add(getFilmById(resSet.getInt("uc_id_film")));
	        }
	        return filmsList;
	    } catch (SQLException e) {
	        throw new DatabaseDAOException(e.getMessage());
	    } finally {
	    	try {
        		connectionPool.freeConnection(connection);
        		if (pStatement != null) { 
        			pStatement.close(); 
        		}
        		if (resSet != null) { 
        			resSet.close(); 
        		}
        	} catch (SQLException e) {
        		throw new DatabaseDAOException(e.getMessage());
        	}
	    }
	}

	/**
     * Adds a film to the cart of a user.
     *
     * @param idFilm the ID of the film to add to the cart
     * @param idUser the ID of the user to add the film to their cart
     * @throws DatabaseDAOException if an error occurs while adding the film to the cart
     */
	@Override
	public void addFilmToCart(Integer idFilm, Integer idUser) throws DatabaseDAOException {
		String sqlQuery = "INSERT INTO user_cart (uc_id_film, uc_id_user, uc_is_bought) VALUES (?, ?, 0)";
		
		Connection connection = null;
    	PreparedStatement pStatement = null;
    	
    	try {
    		connection = connectionPool.getConnection();
        	pStatement = connection.prepareStatement(sqlQuery);
        	pStatement.setInt(1, idFilm);
        	pStatement.setInt(2, idUser);
        	pStatement.executeUpdate();
    	} catch (SQLException e) {
            throw new DatabaseDAOException(e.getMessage());
        } finally {
        	try {
        		connectionPool.freeConnection(connection);
        		if (pStatement != null) { 
        			pStatement.close(); 
        		}
        	} catch (SQLException e) {
        		throw new DatabaseDAOException(e.getMessage());
        	}
        }
	}
	
	/**
     * Removes a film from the cart of a user.
     *
     * @param idFilm the ID of the film to remove from the cart
     * @param idUser the ID of the user to remove the film from their cart
     * @throws DatabaseDAOException if an error occurs while removing the film from the cart
     */
	@Override
	public void deleteFilmFromCart(Integer idFilm, Integer idUser) throws DatabaseDAOException {
		String sqlQuery = "DELETE FROM user_cart WHERE uc_id_film = ? AND uc_id_user = ?";
		
		Connection connection = null;
    	PreparedStatement pStatement = null;
    	
    	try {
    		connection = connectionPool.getConnection();
        	pStatement = connection.prepareStatement(sqlQuery);
        	pStatement.setInt(1, idFilm);
        	pStatement.setInt(2, idUser);
        	pStatement.executeUpdate();
    	} catch (SQLException e) {
            throw new DatabaseDAOException(e.getMessage());
        } finally {
        	try {
        		connectionPool.freeConnection(connection);
        		if (pStatement != null) { 
        			pStatement.close(); 
        		}
        	} catch (SQLException e) {
        		throw new DatabaseDAOException(e.getMessage());
        	}
        }
	}
	
	/**
     * Purchases the films from the cart of a user.
     *
     * @param idUser the ID of the user
     * @throws DatabaseDAOException if an error occurs while purchasing the films from the cart
     */
	@Override
	public void purchaseFilmsFromCart(Integer idUser) throws DatabaseDAOException {
		String sqlQuery = "UPDATE user_cart SET uc_is_bought = 1 WHERE uc_id_user = ? AND uc_is_bought = 0";
		
		Connection connection = null;
    	PreparedStatement pStatement = null;
    	
        try {
        	connection = connectionPool.getConnection();
        	pStatement = connection.prepareStatement(sqlQuery);
        	pStatement.setInt(1, idUser);
        	pStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseDAOException(e.getMessage());
        } finally {
        	try {
        		connectionPool.freeConnection(connection);
        		if (pStatement != null) { 
        			pStatement.close(); 
        		}
        	} catch (SQLException e) {
        		throw new DatabaseDAOException(e.getMessage());
        	}
        }
	}

	/**
     * Retrieves the favorite films list for a user.
     *
     * @param idUser the ID of the user
     * @return a list of favorite films for the specified user
     * @throws DatabaseDAOException if an error occurs while retrieving the favorite films list
     */
	@Override
	public ArrayList<Film> getFavoritesList(Integer idUser) throws DatabaseDAOException {
		String sqlQuery = "SELECT * FROM favorites WHERE f_id_user = ? ORDER BY f_id_film";

	    Connection connection = null;
	    PreparedStatement pStatement = null;
	    ResultSet resSet = null;
	    
	    try {
	        connection = connectionPool.getConnection();
	        pStatement = connection.prepareStatement(sqlQuery);
	        pStatement.setInt(1, idUser);
	        resSet = pStatement.executeQuery();
	        ArrayList<Film> filmsList = new ArrayList<Film>();
	        while (resSet.next()) {
	            filmsList.add(getFilmById(resSet.getInt("f_id_film")));
	        }
	        return filmsList;
	    } catch (SQLException e) {
	        throw new DatabaseDAOException(e.getMessage());
	    } finally {
        	try {
        		connectionPool.freeConnection(connection);
        		if (pStatement != null) {
        			pStatement.close();        			
        		}
        		if (resSet != null) {
        			resSet.close();	
        		}
        	} catch (SQLException e) {
        		throw new DatabaseDAOException(e.getMessage());
        	}
	    }
	}
	
	/**
     * Adds a film to the favorites list of a user.
     *
     * @param idFilm the ID of the film to add to the favorites list
     * @param idUser the ID of the user to add the film to their favorites list
     * @throws DatabaseDAOException if an error occurs while adding the film to the favorites list
     */
	@Override
	public void addFilmToFavorites(Integer idFilm, Integer idUser) throws DatabaseDAOException {
		String sqlQuery = "INSERT INTO favorites (f_id_film, f_id_user) VALUES (?, ?)";
		
		Connection connection = null;
    	PreparedStatement pStatement = null;
        
    	try {
        	connection = connectionPool.getConnection();
        	pStatement = connection.prepareStatement(sqlQuery);
        	pStatement.setInt(1, idFilm);
        	pStatement.setInt(2, idUser);
        	pStatement.executeUpdate();

        } catch (SQLException e) {
                throw new DatabaseDAOException(e.getMessage());
        } finally {
            try {
            	connectionPool.freeConnection(connection);
            	if (pStatement != null) { 
            		pStatement.close(); 
            	}
           	} catch (SQLException e) {
           		throw new DatabaseDAOException(e.getMessage());
           	}
        }
	}
	
	/**
     * Removes a film from the favorites list of a user.
     *
     * @param idFilm the ID of the film to remove from the favorites list
     * @param idUser the ID of the user to remove the film from their favorites list
     * @throws DatabaseDAOException if an error occurs while removing the film from the favorites list
     */
	@Override
	public void deleteFilmFromFavorites(Integer idFilm, Integer idUser) throws DatabaseDAOException {
		String sqlQuery = "DELETE FROM favorites WHERE f_id_film = ? AND f_id_user = ?";
		
		Connection connection = null;
    	PreparedStatement pStatement = null;
       
    	try {
        	connection = connectionPool.getConnection();
        	pStatement = connection.prepareStatement(sqlQuery);
        	pStatement.setInt(1, idFilm);
        	pStatement.setInt(2, idUser);
        	pStatement.executeUpdate();
        } catch (SQLException e) {
                throw new DatabaseDAOException(e.getMessage());
        } finally {
            try {
            	connectionPool.freeConnection(connection);
            	if (pStatement != null) { 
            		pStatement.close(); 
            	}
           	} catch (SQLException e) {
           		throw new DatabaseDAOException(e.getMessage());
           	}
        }
	}
	
	/**
     * Checks if a film is in the favorites list of a user.
     *
     * @param idFilm the ID of the film to check
     * @param idUser the ID of the user
     * @return 1 if the film is in the favorites list, 0 otherwise
     * @throws DatabaseDAOException if an error occurs while checking if the film is in the favorites list
     */
	@Override
	public Integer isFilmInFavorites(Integer idFilm, Integer idUser) throws DatabaseDAOException {
		String sqlQuery= "SELECT COUNT(*) FROM favorites WHERE f_id_film = ? AND f_id_user = ?";
		
		Connection connection = null;
    	PreparedStatement pStatement = null;
        ResultSet resSet = null;
        
        try {
        	connection = connectionPool.getConnection();
        	pStatement = connection.prepareStatement(sqlQuery);
        	pStatement.setInt(1, idFilm);
        	pStatement.setInt(2, idUser);
        	resSet = pStatement.executeQuery();
        	if (resSet.next()) {
        		return resSet.getInt(1);
        	} else {
        		return 0;
        	}
        } catch (SQLException e) {
                throw new DatabaseDAOException(e.getMessage());
        } finally {
        	try {
        		connectionPool.freeConnection(connection);
        		if (pStatement != null) {
        			pStatement.close();        			
        		}
        		if (resSet != null) {
        			resSet.close();	
        		}
        	} catch (SQLException e) {
        		throw new DatabaseDAOException(e.getMessage());
        	}
        }
	}

	/**
     * Retrieves the reviews for a film with the specified ID.
     *
     * @param idFilm the ID of the film to retrieve reviews for
     * @return a list of reviews for the specified film
     * @throws DatabaseDAOException if an error occurs while retrieving the reviews
     */
	@Override
	public ArrayList<Review> getReviewsByFilmId(Integer idFilm) throws DatabaseDAOException {
		String sqlQuery= "SELECT * FROM comment WHERE c_id_film = ?";
		
		Connection connection = null;
    	PreparedStatement pStatement = null;
        ResultSet resSet = null;
        
        try {
        	connection = connectionPool.getConnection();
        	pStatement = connection.prepareStatement(sqlQuery);
        	pStatement.setInt(1, idFilm);
        	resSet = pStatement.executeQuery();	
        	ArrayList<Review> reviewsList = new ArrayList<Review>();
        	while (resSet.next()) {
        		reviewsList.add(new Review(
        				resSet.getInt("c_id"), 
        				resSet.getInt("c_id_film"), 
        				resSet.getString("c_user_name"), 
        				resSet.getString("c_description"),
        				resSet.getInt("c_rating")));
        	}
        	
        	return reviewsList;
        } catch (SQLException e) {
            throw new DatabaseDAOException(e.getMessage());
        } finally {
        	try {
        		connectionPool.freeConnection(connection);
        		if (pStatement != null) { 
        			pStatement.close(); 
        		}
        		if (resSet != null) { 
        			resSet.close(); 
        		}
        	} catch (SQLException e) {
        		throw new DatabaseDAOException(e.getMessage());
        	}
        }
	}
	
	/**
     * Adds a review for a film.
     *
     * @param idFilm the ID of the film
     * @param username the username of the user who wrote the review
     * @param rating the rating of the film in the review
     * @param description the description of the review
     * @throws DatabaseDAOException if an error occurs while adding the review
     */
	@Override
	public void addReview(Integer idFilm, String username, Integer rating, String description) throws DatabaseDAOException {
		String sqlQuery= "INSERT INTO comment (c_id_film, c_user_name, c_description, c_rating) VALUES (?, ?, ?, ?)";
		
		Connection connection = null;
    	PreparedStatement pStatement = null;
    	
    	try {
    		connection = connectionPool.getConnection();
        	pStatement = connection.prepareStatement(sqlQuery);
        	pStatement.setInt(1, idFilm);
        	pStatement.setString(2, username);
        	pStatement.setString(3, description);
        	pStatement.setInt(4, rating);
        	pStatement.executeUpdate();
    	} catch (SQLException e) {
            throw new DatabaseDAOException(e.getMessage());
        } finally {
        	try {
        		connectionPool.freeConnection(connection);
        		if (pStatement != null) { 
        			pStatement.close(); 
        		}
        	} catch (SQLException e) {
        		throw new DatabaseDAOException(e.getMessage());
        	}
        }
	}
	
	/**
     * Deletes a review with the specified ID.
     *
     * @param idReview the ID of the review to delete
     * @throws DatabaseDAOException if an error occurs while deleting the review
     */
	@Override
	public void deleteReviewById(Integer idReview) throws DatabaseDAOException {
		String sqlQuery = "DELETE FROM comment WHERE c_id = ?";
		
		Connection connection = null;
    	PreparedStatement pStatement = null;
    	
    	try {
    		connection = connectionPool.getConnection();
        	pStatement = connection.prepareStatement(sqlQuery);
        	pStatement.setInt(1, idReview);
        	pStatement.executeUpdate();
    	} catch (SQLException e) {
            throw new DatabaseDAOException(e.getMessage());
        } finally {
        	try {
        		connectionPool.freeConnection(connection);
        		if (pStatement != null) { pStatement.close(); }
        	} catch (SQLException e) {
        		throw new DatabaseDAOException(e.getMessage());
        	}
        }
	}
	
	/**
     * Updates a review with the specified ID.
     *
     * @param idReview the ID of the review to update
     * @param rating the updated rating for the review
     * @param description the updated description for the review
     * @throws DatabaseDAOException if an error occurs while updating the review
     */
	@Override
	public void updateReviewById(Integer idReview, Integer rating, String description) throws DatabaseDAOException {
		String sqlQuery = "UPDATE comment SET c_rating = ?, c_description = ? WHERE c_id = ?";
		
		Connection connection = null;
    	PreparedStatement pStatement = null;
    	
    	try {
    		connection = connectionPool.getConnection();
        	pStatement = connection.prepareStatement(sqlQuery);
        	pStatement.setInt(1, rating);
        	pStatement.setString(2, description);
        	pStatement.setInt(3, idReview);
        	pStatement.executeUpdate();
    	} catch (SQLException e) {
            throw new DatabaseDAOException(e.getMessage());
        } finally {
        	try {
        		connectionPool.freeConnection(connection);
        		if (pStatement != null) { 
        			pStatement.close(); 
        		}
        	} catch (SQLException e) {
        		throw new DatabaseDAOException(e.getMessage());
        	}
        }
	}      
}
