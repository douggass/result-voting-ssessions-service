package com.service.resultvotingssessionsservice.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.service.resultvotingssessionsservice.messaging.Consumer;
import com.service.resultvotingssessionsservice.messaging.SessionMessageDto;

@Configuration
@EnableKafka
public class KafkaConfiguration {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Value("${spring.kafka.properties.security.protocol}")
	private String securityProtocol;

	@Value("${spring.kafka.properties.sasl.mechanism}")
	private String saslMechanism;

	@Value("${spring.kafka.properties.sasl.jaas.config}")
	private String saslJaasConfig;

	@Value("${spring.kafka.consumer.group-id}")
	private String groupId;

	@Value("${spring.kafka.consumer.auto-offset-reset}")
	private String autoOffsetReset;

	@Value("${spring.kafka.consumer.properties.spring.json.trusted.packages}")
	private String jsonTrustedPackages;

	@Bean
	public Map<String, Object> consumerConfigs() {

		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer());
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);

		props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, securityProtocol);
		props.put("sasl.mechanism", saslMechanism);
		props.put("sasl.jaas.config", saslJaasConfig);

		props.put(JsonDeserializer.TRUSTED_PACKAGES, jsonTrustedPackages);

		return props;
	}

	private JsonDeserializer<SessionMessageDto> deserializer() {
		JsonDeserializer<SessionMessageDto> deserializer = new JsonDeserializer<>(SessionMessageDto.class);
		deserializer.setRemoveTypeHeaders(false);
		deserializer.addTrustedPackages("*");
		deserializer.setUseTypeMapperForKey(true);
		return deserializer;
	}

	@Bean
	public ConsumerFactory<String, SessionMessageDto> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), deserializer());
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, SessionMessageDto> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, SessionMessageDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

	@Bean
	public Consumer receiver() {
		return new Consumer();
	}

}
