package com.project.chatapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Chat App API",
                version = "1.0.0",
                description = "Backend for System Architecture subject",
                contact = @Contact(
                        name = "Nguyễn Thanh Sang",
                        email = "sangdoannguyen7@gmail.com"
                )
        )
)
public class SwaggerConfig {

}
