package com.web.application;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class XmlFileDownloadBean {


	private long uploadId;
	private MultipartFile file;

	public long getUploadId() {
		return uploadId;
	}

	public void setUploadId(long uploadId) {
		this.uploadId = uploadId;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
