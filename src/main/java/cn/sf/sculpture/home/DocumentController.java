package cn.sf.sculpture.home;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.sf.sculpture.document.service.DocumentService;


@Controller
@RequestMapping("document")
public class DocumentController {

	@Autowired
	private DocumentService documentService;
	
	@PostMapping
	@ResponseBody
	public void allProjects(MultipartFile file, Long objectId) throws IOException {
		
		documentService.insert(file.getInputStream(), file.getOriginalFilename(), objectId);
	}
	
}
