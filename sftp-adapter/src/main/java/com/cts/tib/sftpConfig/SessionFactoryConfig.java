package com.cts.tib.sftpConfig;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;

@Configuration
public class SessionFactoryConfig {
	private static Logger log = Logger.getLogger(SessionFactoryConfig.class);

	@Autowired
	private SftpConfiguration sftpConfig;

	public SftpConfiguration getSftpConfig() {
		return sftpConfig;
	}

	public void setSftpConfig(SftpConfiguration sftpConfig) {
		this.sftpConfig = sftpConfig;
	}

	@Bean
	public DefaultSftpSessionFactory sftpSessionFactory() {
		DefaultSftpSessionFactory sessionFactory = new DefaultSftpSessionFactory();
		sessionFactory.setHost(getSftpConfig().getHostName());
		if (getSftpConfig().getPort() != null) {
			sessionFactory.setPort(Integer.parseInt(getSftpConfig().getPort()));
		}
		sessionFactory.setUser(getSftpConfig().getUserName());
		sessionFactory.setPassword(getSftpConfig().getPassword());
		sessionFactory.setAllowUnknownKeys(true);
		sessionFactory.setPrivateKey(null);
		sessionFactory.setPrivateKeyPassphrase(null);
		return sessionFactory;
	}

}