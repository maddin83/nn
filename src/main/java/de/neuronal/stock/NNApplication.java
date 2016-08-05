package de.neuronal.stock;

import java.sql.SQLException;

import javax.servlet.ServletContext;

import org.h2.server.web.WebServlet;
import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NNApplication {

	public static void main(String[] args) {
		SpringApplication.run(NNApplication.class, args);
	}
	
	@Bean
	public ServletRegistrationBean h2servletRegistration() {
	    ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
	    registration.addUrlMappings("/console/*");
	    registration.addInitParameter("webAllowOthers", "true");
	    
	    return registration;
	}
	
	@Bean(initMethod="start", destroyMethod="stop")
	public Server initH2TCPServer(ServletContext servletContext) {
	    Server server = null;
		try {
	        server = Server.createTcpServer( "-tcp", "-tcpAllowOthers", "-tcpPort", "8082" );
	    } catch( SQLException e ) {
	        e.printStackTrace();
	    } 
	    return server;
	}
}
