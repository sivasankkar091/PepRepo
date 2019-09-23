package com.cts.tib.sftpConfig;

import java.io.File;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import com.cts.tib.util.SftpAdapterConstants;

@MessagingGateway
public interface UploadGatewayConfig {
	@Gateway(requestChannel=SftpAdapterConstants.UPLOAD_TO_SFTP_CHANNEL)
	public void uploadFile(File file);
}
