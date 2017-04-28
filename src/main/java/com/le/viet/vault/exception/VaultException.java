package com.le.viet.vault.exception;

/**
 * Created by vle on 9/24/2015.
 */
public class VaultException extends Exception{
    private int statusCd;
    public VaultException(String msg, int statusCd){
        super(msg);
        this.statusCd = statusCd;
    }

    public int getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(int statusCd) {
        this.statusCd = statusCd;
    }

    @Override
    public String toString() {
        return "VaultException{" +
                "statusCd=" + statusCd +
                '}';
    }
}
