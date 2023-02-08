package com.scb.notification.config;

import com.solacesystems.jms.SolConnectionFactory;
import com.solacesystems.jms.SolJmsUtility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import javax.jms.JMSContext;

@Configuration
@EnableJms
public class JMSConfig {

    @Bean
    public DefaultJmsListenerContainerFactory nonfinJmsContFactory() throws Exception {
        DefaultJmsListenerContainerFactory containerFactory = new DefaultJmsListenerContainerFactory();
        containerFactory.setPubSubDomain(false);
        containerFactory.setConnectionFactory(connectionFactory());
        containerFactory.setMessageConverter(jacksonJmsMsgConverter());
        //containerFactory.setSubscriptionDurable(true);
        return containerFactory;
    }

    @Bean
    public CachingConnectionFactory connectionFactory() throws Exception {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        SolConnectionFactory connectionFactory = SolJmsUtility.createConnectionFactory();
        connectionFactory.setHost("tcps://mr-connection-f46f82zrwr2.messaging.solace.cloud:55443");
        connectionFactory.setVPN("psl-trial");
        connectionFactory.setAuthenticationScheme("AUTHENTICATION_SCHEME_CLIENT_CERTIFICATE");
        connectionFactory.setSSLTrustStore("D:\\Narendra\\JKS\\solacetrust.jks");
        connectionFactory.setSSLTrustStoreFormat("JKS");
        connectionFactory.setSSLTrustStorePassword("password");
        connectionFactory.setSSLKeyStore("D:\\Narendra\\JKS\\newkeystore.jks");
        connectionFactory.setSSLKeyStoreFormat("JKS");
        connectionFactory.setSSLKeyStorePassword("password");
        connectionFactory.setSSLPrivateKeyAlias("deb");
        connectionFactory.setSSLPrivateKeyPassword("password");
        factory.setTargetConnectionFactory(connectionFactory);
        return factory;
    }

    @Bean
    public MessageConverter jacksonJmsMsgConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}
