package com.project.chatapp.config.exception;

public class ResourceBadRequestException extends RuntimeException {

    public ResourceBadRequestException(String message) {
        super(message);
    }

    public ResourceBadRequestException() {
        super("Không tìm thấy");
    }
}
