package com.le.viet.vault.exception;

/**
 * Created by onelazyguy on 5/7/17.
 */
public class ValidationException extends VaultException {
    public ValidationException(String msg, int statusCd) {
        super(msg, statusCd);
    }
}
