package ro.brinzas.patient_sensor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

public class FileToMessagesRunner implements CommandLineRunner {
	
	@Value("${sender.message-delay:0}")
	private long delayBetweenMessages;
	
	@Value("${sender.patient-id:1}")
	private long patientId;

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

	@Override
	public void run(String... args) {
		InputStream is = getClass().getClassLoader().getResourceAsStream("activities.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		reader.lines().forEachOrdered(line -> {
			String[] splitLine = line.trim().split("\t\t");
			sendOneMessage(splitLine[0], splitLine[1], splitLine[2]);
			try {
				Thread.sleep(delayBetweenMessages);
			} catch (InterruptedException e) {
				// Should never happen, since nobody will interrupt
				e.printStackTrace();
			}
		});
		// TODO: once done, terminate?
	}

	public void sendOneMessage(String activityStart, String activityEnd, String activityName) {
		ActivityRabbitDTO activity = ActivityRabbitDTO.builder()
				.patient_id(patientId)
				.start(activityStart)
				.end(activityEnd)
				.name(activityName)
				.build();
		
		this.template.convertAndSend(queue.getName(), activity);
	}

}
