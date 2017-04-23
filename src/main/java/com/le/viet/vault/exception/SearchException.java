package com.le.viet.vault.exception;

/**
 * Created by onelazyguy on 4/23/17.
 */
public class SearchException extends VaultException{
    public SearchException(String msg, int statusCd) {
        super(msg, statusCd);
    }

    @Override
    public String toString() {
        return "SearchException{"+super.toString()+"}";
    }
}
