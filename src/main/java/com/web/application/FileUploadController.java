package com.web.application;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
	@Autowired
	private Service service;
	@Autowired
	private XmlFileDownloadBean xmlFileDownloadBean;

	@GetMapping(value = "/home")
	public String home(Model model) {
		model.addAttribute("fileDownloadModel", new XmlFileDownloadBean());
		return "view";
	}

	@PostMapping(value = "/downloadFile")
	public String downloadFileHandler(Model model, @ModelAttribute("fileDownloadModel") XmlFileDownloadBean fileDownloadBean,
			BindingResult bindingResult) {
		String DOWNLOAD_PATH = "D:\\Coder\\XmlParser-master\\XmlParser-master\\src\\main\\webApp\\WEB-INF\\views";
		MultipartFile file = fileDownloadBean.getFile();
		if (bindingResult.hasErrors()) {
			return "upload-xml-file";
		}
		String fileName = file.getOriginalFilename();
		try {
			byte[] bytes = file.getBytes();
			File files = new File(DOWNLOAD_PATH + File.separator + fileName);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(files));
			stream.write(bytes);
			parsingXmlFile(files);
			stream.close();
			return "redirect:/";
		} catch (Exception e) {
			return "view";
		}
	}

	public void parsingXmlFile(File file) {
		service.parsingXmlFile(file);
	}
}
