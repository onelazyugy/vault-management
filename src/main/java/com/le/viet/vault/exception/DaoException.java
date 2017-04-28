package com.le.viet.vault.exception;

/**
 * Created by associate on 4/28/17.
 */
public class DaoException extends VaultException {
    public DaoException(String msg, int statusCd) {
        super(msg, statusCd);
    }
}
