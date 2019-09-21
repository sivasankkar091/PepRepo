package com.cts.tib.sftpController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cts.tib.sftpConfig.UploadGatewayConfig;

@RestController
public class SftpServiceController {
	private static Logger log = Logger.getLogger(SftpServiceController.class);
	@Autowired
	private UploadGatewayConfig uploadGateway;
	
	@PostMapping(path="/uploadToSftp/{fileName}")
	public void uploadCsvToSftp(@PathVariable("fileName") String sourceFileName, @RequestParam("file") MultipartFile sourceFile) throws IOException {
		
		File destinationFile=new File(sourceFileName);
		destinationFile.createNewFile();
		FileOutputStream fos=new FileOutputStream(destinationFile);
		fos.write(sourceFile.getBytes());
		fos.close();
		uploadGateway.uploadFile(destinationFile);
	}
}
