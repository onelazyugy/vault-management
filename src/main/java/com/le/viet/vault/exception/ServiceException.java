package com.le.viet.vault.exception;

/**
 * Created by associate on 4/28/17.
 */
public class ServiceException extends VaultException {
    public ServiceException(String msg, int statusCd) {
        super(msg, statusCd);
    }
}
