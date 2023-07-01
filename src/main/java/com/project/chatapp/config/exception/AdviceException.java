package com.project.chatapp.config.exception;

import com.project.chatapp.config.LogsBot;
import com.project.chatapp.model.Message;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@RestControllerAdvice(basePackages = "com.project.chatapp")
@AllArgsConstructor
public class AdviceException {

    @NonNull final LogsBot logsBot;

    // status 400
    @ExceptionHandler(value = {BindException.class})
    public ResponseEntity<Message> bindExceptionHandler(BindException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage()));
    }

    // status 400
    @ExceptionHandler(value = {ResourceBadRequestException.class})
    public ResponseEntity<Message> handleResourceExistException(ResourceBadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(ex.getMessage()));
    }

    // status 401
    @ExceptionHandler(value = {UnauthorizedException.class})
    public ResponseEntity<Message> handleUnauthorizedException(UnauthorizedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Message(ex.getMessage()));
    }

    // status 403
    @ExceptionHandler(value = {ForbiddenException.class})
    public ResponseEntity<Message> handleForbiddenException(ForbiddenException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Message(ex.getMessage()));
    }

    // status 404
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Message> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(ex.getMessage()));
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Message> handleResourceException(Exception ex) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(6057112135L);
        sendMessage.setText(ex.getMessage());
        try {
            logsBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message(ex.getMessage()));
    }
}
