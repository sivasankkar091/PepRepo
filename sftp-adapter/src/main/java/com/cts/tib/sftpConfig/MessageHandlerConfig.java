package com.cts.tib.sftpConfig;

import java.io.File;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.file.FileNameGenerator;
import org.springframework.integration.sftp.outbound.SftpMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;

import com.cts.tib.util.SftpAdapterConstants;

@Configuration
public class MessageHandlerConfig {
	private static Logger log = Logger.getLogger(MessageHandlerConfig.class);
	@Autowired
	private SessionFactoryConfig sessionFactory;

	@Bean
	@ServiceActivator(inputChannel = SftpAdapterConstants.UPLOAD_TO_SFTP_CHANNEL)
	public MessageHandler messagHandler() {
		SftpMessageHandler msgHandler = new SftpMessageHandler(sessionFactory.sftpSessionFactory());
		msgHandler.setRemoteDirectoryExpression(
				new LiteralExpression(sessionFactory.getSftpConfig().getRemoteDirectoryPath()));
		FileNameGenerator fileNameGenerator = new FileNameGenerator() {

			@Override
			public String generateFileName(Message<?> message) {
				if (message.getPayload() instanceof File) {
					log.info("Inside generate fileName"+((File) message.getPayload()).getName());
					return (((File) message.getPayload()).getName());
				} else {
					throw new IllegalArgumentException("File should be sent as payload");
				}
			}
		};
		msgHandler.setFileNameGenerator(fileNameGenerator);
		return msgHandler;

	}
}
