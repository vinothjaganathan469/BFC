package com.chatback.configuration;

//import java.util.Map;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;

//import org.springframework.context.annotation.Bean;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServletServerHttpRequest;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

//import com.chatback.model.Users;


@Configuration
@EnableWebSocketMessageBroker  
@EnableScheduling
@ComponentScan(basePackages="com.chatback")
public class WebSocketconfiguration implements WebSocketMessageBrokerConfigurer {


	

	public void registerStompEndpoints(StompEndpointRegistry registry) {
		System.out.println("REGISTER STOMP ENDPOINTS...");
		registry.addEndpoint("/portfolio").withSockJS();
	}

	
	public void configureMessageBroker(MessageBrokerRegistry  configurer) {
		System.out.println("CONFIGURE MESSAGE BROKER REGISTRY");
		configurer.enableSimpleBroker("/queue/", "/topic/");
		configurer.setApplicationDestinationPrefixes("/app");
	}

	
	public void configureClientInboundChannel(ChannelRegistration registration) {
		// TODO Auto-generated method stub
		
	}


	public void configureClientOutboundChannel(ChannelRegistration registration) {
		// TODO Auto-generated method stub
		
	}


}