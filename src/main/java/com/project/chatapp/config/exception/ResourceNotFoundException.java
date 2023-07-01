package com.project.chatapp.config.exception;

public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String message) {
    super(message);
  }

  public ResourceNotFoundException() {
    super("Không tìm thấy dữ liệu");
  }

}
