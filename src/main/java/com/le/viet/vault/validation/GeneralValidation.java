package com.le.viet.vault.validation;

import com.le.viet.vault.model.auth.User;

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
}
