package com.le.viet.vault.model.entry;

/**
 * Created by associate on 5/2/17.
 */
public class AdminEntryResponse {
    private boolean isSuccess;
    private String message;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdminEntryResponse that = (AdminEntryResponse) o;

        if (isSuccess != that.isSuccess) return false;
        return message != null ? message.equals(that.message) : that.message == null;
    }

    @Override
    public int hashCode() {
        int result = (isSuccess ? 1 : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AdminEntryResponse{" +
                "isSuccess=" + isSuccess +
                ", message='" + message + '\'' +
                '}';
    }
}
