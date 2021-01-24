package com.marko.codeChallenge.exceptions;

import com.marko.codeChallenge.util.DescriptionUtil;
import lombok.Data;

@Data
public class AppException extends RuntimeException{

    public AppException(String code){
        super(code);
    }

    private String getMessage(String code){
        String errorMessage = code;
        try {
            errorMessage = DescriptionUtil.getErrorDescription(code);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return errorMessage;
    }
}
