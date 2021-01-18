package ro.brinzas.rest_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.TimeZone;

//////============= RUN ON EMBEDDED TOMCAT - JAR ============
//@SpringBootApplication
//public class SpringRESTApplication {
//
//	public static void main(String[] args) {
//
//		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
//		SpringApplication.run(SpringRESTApplication.class, args);
//	}
//
//}

////============= RUN AS TOMCAT - WAR ============
@SpringBootApplication
public class SpringRESTApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringRESTApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringRESTApplication.class, args);
	}
}
