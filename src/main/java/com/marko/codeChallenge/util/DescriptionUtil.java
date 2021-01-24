package com.marko.codeChallenge.util;


import javax.validation.constraints.NotNull;
import java.util.ResourceBundle;

public class DescriptionUtil {

    public static String errorDescriptionPath = "desc/error_description";

    @NotNull
    public static String getErrorDescription(String key){
        ResourceBundle errorResourceBundle = ResourceBundle.getBundle(errorDescriptionPath);
        String errorMessage = null;
        try{
          errorMessage = errorResourceBundle.getString(key);
        }catch(Exception ex){
            errorMessage = "Ne postoji opis za prosleđenu grešku "+ key;
        }
        return errorMessage;
    }


}




