package com.marko.codeChallenge.util;


import javax.validation.constraints.NotNull;
import java.util.Locale;
import java.util.ResourceBundle;

public class DescriptionUtil {

    public static String errorDescriptionPath = "desc/error_description";

    @NotNull
    public static String getErrorDescription(String key){
        Locale locale = new Locale(getLocale());
        ResourceBundle errorResourceBundle = ResourceBundle.getBundle(errorDescriptionPath, locale);
        String errorMessage = null;
        try{
          errorMessage = errorResourceBundle.getString(key);
        }catch(Exception ex){
            errorMessage = "There is no description for threw error "+ key;
        }
        return errorMessage;
    }

    public static String getLocale(){
        ResourceBundle applicationRB = ResourceBundle.getBundle("application");
        String defaultLocale = null;
        try{
            defaultLocale = applicationRB.getString("app.locale");
        }catch (Exception e){
            defaultLocale = "en";
        }
        return defaultLocale;
    }
}




