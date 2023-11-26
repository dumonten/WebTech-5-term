	package com.bsuir.by.nastassia.yankova.nfilm.controller;
		
import java.util.Locale;
import java.util.ResourceBundle;
import jakarta.servlet.http.HttpSession;
	
/**
* The LanguageResourceBundleProvider class provides a method to retrieve the appropriate resource bundle based on the session language.
*/
public class LanguageResourceBundleProvider {
	    
	/**
	* Retrieves the resource bundle based on the session language.
	*
    * @param session the HttpSession object
    * @return the resource bundle for the specified session language
    */
	public static ResourceBundle getBundle(HttpSession session) {
		String lang = (String) session.getAttribute("lang");
		Locale locale = null;
		locale = (lang == "ru_RU") ? new Locale.Builder().setLanguage("ru").setRegion("RU").build() 
	                                        : new Locale.Builder().setLanguage("en").setRegion("US").build();
	    return ResourceBundle.getBundle("messages", locale);
	}
}