package ro.brinzas.patient_sensor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class SpringMessageGenerator {

    @Bean
    public Queue hello() {
        return new Queue("patient-activities", true, false, true);
    }
    
    @Bean
    public DefaultClassMapper classMapper()
    {
        DefaultClassMapper classMapper = new DefaultClassMapper();
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("ActivityRabbitDTO", ActivityRabbitDTO.class);
        classMapper.setIdClassMapping(idClassMapping);
        return classMapper;
    }
    
    @Bean
    public MessageConverter jsonMessageConverter() {
    	Jackson2JsonMessageConverter jsonConverter = new Jackson2JsonMessageConverter();
    	jsonConverter.setClassMapper(classMapper());
    	return jsonConverter;
    }
    
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
	
	@Bean
	public CommandLineRunner fileToMessages() {
		return new FileToMessagesRunner();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringMessageGenerator.class, args);
	}

}
