package com.le.viet.vault.validation;

import com.le.viet.vault.exception.ServiceException;
import com.le.viet.vault.exception.ValidationException;
import com.le.viet.vault.model.auth.User;
import com.le.viet.vault.model.search.SearchPromptRequest;
import org.apache.commons.lang3.StringUtils;

import static com.le.viet.vault.model.common.Common.*;

/**
 * Created by onelazyguy on 1/2/17.
 */
public class GeneralValidation {

    public static boolean isLoginDataValid(User user){
        boolean isloginDataValid = true;
        if(user == null || user.getUsername() == null ||
                user.getUsername().isEmpty() || user.getPassword() == null || user.getPassword().isEmpty()){
            isloginDataValid = false;
        }
        return isloginDataValid;
    }

    public static void isSearchPromptRequestValid(SearchPromptRequest request) throws ValidationException {
        if(request == null){
            throw new ValidationException("request is invalid", VALIDATION_EXCEPTION);
        }else if(StringUtils.isBlank(request.getId())){
            throw new ValidationException("id cannot be blank", VALIDATION_EXCEPTION);
        }else if(StringUtils.isBlank(request.getPassword())){
            throw new ValidationException("password cannot be blank", VALIDATION_EXCEPTION);
        }
    }
}
