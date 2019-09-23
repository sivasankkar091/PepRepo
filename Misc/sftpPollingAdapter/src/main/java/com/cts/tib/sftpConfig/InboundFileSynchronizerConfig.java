package com.cts.tib.sftpConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.file.remote.synchronizer.InboundFileSynchronizer;
import org.springframework.integration.sftp.filters.SftpSimplePatternFileListFilter;
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizer;

import com.cts.tib.util.SftpAdapterConstants;

@Configuration
public class InboundFileSynchronizerConfig {

	@Autowired
	SessionFactoryConfig sessionConfig;

	@Bean
	public InboundFileSynchronizer sftpInboundFileSynchronizer() {
		SftpInboundFileSynchronizer filesync = new SftpInboundFileSynchronizer(sessionConfig.sftpSessionFactory());
		filesync.setDeleteRemoteFiles(false);
		filesync.setRemoteDirectory(sessionConfig.getSftpConfig().getRemoteDirectoryPath());
		filesync.setFilter(new SftpSimplePatternFileListFilter(SftpAdapterConstants.FILE_PATTEN_SUFFIX));
		return filesync;

	}
}
