package io.github.marcoscouto.exception;

public class PasswordInvalidException extends RuntimeException {
    public PasswordInvalidException() {
        super("Password is incorrect");
    }
}
