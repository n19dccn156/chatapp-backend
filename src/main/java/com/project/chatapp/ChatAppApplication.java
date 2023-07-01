package com.project.chatapp;

import com.project.chatapp.config.LogsBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.TimeZone;

@SpringBootApplication
public class ChatAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatAppApplication.class, args);
		try {
			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
			botsApi.registerBot(new LogsBot());
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println("New default time zone: " + TimeZone.getDefault().getID());
//		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
//		System.out.println("New default time zone: " + TimeZone.getDefault().getID());
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
						.allowedOrigins("*")
						.allowedHeaders("*")
						.exposedHeaders("Authorization");
			}
		};
	}
}
