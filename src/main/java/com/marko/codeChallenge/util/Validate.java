package com.marko.codeChallenge.util;

import com.marko.codeChallenge.exceptions.AppException;
import com.marko.codeChallenge.model.AbstractDataModel;

import java.util.List;

public class Validate {

    public static void isNotNull(Object object, AppException exception) throws AppException {
        if (object == null) {
            throw exception;
        } else if((object instanceof String) && (object.equals(""))) {
            throw exception;
        }
    }

    public static void isNotNull(Object object, String exceptionMessage) throws AppException {
        if (object == null) {
            throw new AppException(DescriptionUtil.getErrorDescription(exceptionMessage));
        } else if((object instanceof String) && (object.equals(""))) {
            throw new AppException(DescriptionUtil.getErrorDescription(exceptionMessage));
        } else  if((object instanceof List) && (((List) object).size() == 0)) {
            throw new AppException(DescriptionUtil.getErrorDescription(exceptionMessage));
        }
    }

    public static void isNull(Object object, AppException exception) throws AppException {
        if (object != null) {
            throw exception;
        }
    }

    public static void isEntityOrIdNull(AbstractDataModel object, String exceptionMessage) throws AppException {
        isNotNull(object, DescriptionUtil.getErrorDescription(exceptionMessage));
        isNotNull(object.getId(), DescriptionUtil.getErrorDescription(exceptionMessage));
    }

}
