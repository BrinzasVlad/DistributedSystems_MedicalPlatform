package ro.brinzas.rest_backend.services.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ro.brinzas.rest_backend.dto.ActivityRabbitDTO;

@Configuration
public class MessageConfiguration {
	
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
	
	// This code has been sourced from
	// https://grokonez.com/spring-framework/spring-amqp/rabbitmq-sendreceive-java-object-messages-spring-rabbitmq-springboot
	// I claim no ownership, but its purpose is simple: associate the JSON converter
	// to the @RabbitListener that we use.
	@Bean
    public SimpleRabbitListenerContainerFactory jsaFactory(ConnectionFactory connectionFactory,
            SimpleRabbitListenerContainerFactoryConfigurer configurer) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        return factory;
    }
}
