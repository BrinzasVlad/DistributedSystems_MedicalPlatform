package ro.brinzas.soap_backend;

import javax.xml.ws.Endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import ro.brinzas.soap_backend.services.SoapActivityServiceImpl;
import ro.brinzas.soap_backend.services.SoapIntakeTakenServiceImpl;

@SpringBootApplication
public class SoapBackend
{
	private static final int PORT = 8099;
	private final SoapActivityServiceImpl activityService;
	private final SoapIntakeTakenServiceImpl intakeTakenService;
	
	@Autowired
	public SoapBackend(SoapActivityServiceImpl activityService, SoapIntakeTakenServiceImpl intakeTakenService) {
		this.activityService = activityService;
		this.intakeTakenService = intakeTakenService;
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void publishWebService() {
		Endpoint.publish("http://0.0.0.0:" + PORT + "/soap_activity_service", activityService);
		Endpoint.publish("http://0.0.0.0:" + PORT + "/soap_intake_taken_service", intakeTakenService);
        System.out.println("Web service started, yay!");
	}
	
    public static void main( String[] args )
    {
    	SpringApplication.run(SoapBackend.class, args);
    }
}
