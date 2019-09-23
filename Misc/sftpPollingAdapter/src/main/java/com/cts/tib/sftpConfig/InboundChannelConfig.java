package com.cts.tib.sftpConfig;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.filters.AcceptOnceFileListFilter;
import org.springframework.integration.file.remote.synchronizer.AbstractInboundFileSynchronizer;
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizingMessageSource;

import com.cts.tib.util.SftpAdapterConstants;
import com.jcraft.jsch.ChannelSftp.LsEntry;

@Configuration
public class InboundChannelConfig {

	@Autowired
	InboundFileSynchronizerConfig fileSyncConfig;

	@Autowired
	@SuppressWarnings("unchecked")
	@Bean
	@InboundChannelAdapter(channel = SftpAdapterConstants.INBOUND_SFTP_CHANNEL, poller = @Poller(cron = SftpAdapterConstants.POLLER_TIME_OUT))
	public MessageSource<File> sftpMessageSource() {
		SftpInboundFileSynchronizingMessageSource msgSource = new SftpInboundFileSynchronizingMessageSource(
				(AbstractInboundFileSynchronizer<LsEntry>) fileSyncConfig.sftpInboundFileSynchronizer());
		msgSource.setAutoCreateLocalDirectory(true);
		msgSource.setLocalDirectory(new File("sftp-inbound"));
		msgSource.setLocalFilter(new AcceptOnceFileListFilter<File>());
		return msgSource;

	}
}
