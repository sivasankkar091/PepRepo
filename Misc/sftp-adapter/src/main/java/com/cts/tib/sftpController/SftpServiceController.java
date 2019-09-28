package com.cts.tib.sftpController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cts.tib.sftpConfig.UploadGatewayConfig;

@RestController
public class SftpServiceController {
	private static Logger log = Logger.getLogger(SftpServiceController.class);
	@Autowired
	private UploadGatewayConfig uploadGateway;

	@PostMapping(path = "/uploadToSftp/{fileName}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void uploadCsvToSftp(@PathVariable("fileName") String sourceFileName,
			@RequestParam("file") MultipartFile sourceFile) throws IOException {

		File destinationFile = new File(sourceFileName+".csv");
		destinationFile.createNewFile();
		FileOutputStream outputStream= new FileOutputStream(destinationFile);
		outputStream.write(sourceFile.getBytes());
		sourceFile.transferTo(destinationFile);
		outputStream.close();
		uploadGateway.uploadFile(destinationFile);
	}
	@PostMapping(path = "/csvReceiver")
	public void uploadCsvToSftp(@RequestBody HashMap<String,String> sourceMap) throws IOException {
		log.info("The output of service is"+sourceMap.get("csvOutput"));
	}
}
