package com.cts.tib.sftpConfig;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import com.cts.tib.util.SftpAdapterConstants;

@Configuration
public class SftpMessageHandlerConfig {
	private static Logger log = Logger.getLogger(SftpMessageHandlerConfig.class);
	
	@Autowired
	private SessionFactoryConfig sessionConfig;

	@Bean
	public SftpRemoteFileTemplate template() {
		return new SftpRemoteFileTemplate(sessionConfig.sftpSessionFactory());
	}
	
	@Bean
	@ServiceActivator(inputChannel = SftpAdapterConstants.INBOUND_SFTP_CHANNEL)
	public MessageHandler handler() {
		return new MessageHandler() {

			@Override
			public void handleMessage(Message<?> message) throws MessagingException {
				//Integration with another microservice which will save the file to a location
			}
		};

	}
}
