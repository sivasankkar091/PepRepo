package com.cts.tib.sftpConfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("sftp")
@Component
public class SftpConfiguration {

	private String hostName;
	private String port;
	private String uploadPort;
	private String userName;
	private String password;
	private String remoteDirectoryPath;
	private String privatekey;
	private String privateKeyPassphrase;

	public String getUploadPort() {
		return uploadPort;
	}
	public void setUploadPort(String uploadPort) {
		this.uploadPort = uploadPort;
	}
	public String getPrivateKeyPassphrase() {
		return privateKeyPassphrase;
	}
	public void setPrivateKeyPassphrase(String privateKeyPassphrase) {
		this.privateKeyPassphrase = privateKeyPassphrase;
	}
	public String getPrivatekey() {
		return privatekey;
	}
	public void setPrivatekey(String privatekey) {
		this.privatekey = privatekey;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRemoteDirectoryPath() {
		return remoteDirectoryPath;
	}
	public void setRemoteDirectoryPath(String remoteDirectoryPath) {
		this.remoteDirectoryPath = remoteDirectoryPath;
	}

}
