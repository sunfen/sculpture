package cn.sf.sculpture.home;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sf.sculpture.home.domian.ProjectDTO;

@Controller
@RequestMapping("project")
public class HomeController {

	@GetMapping("search")
	@ResponseBody
	public Page<ProjectDTO> allProjects(
			@PageableDefault(page = 0, size = 7) Pageable pageable) {
		
		List<ProjectDTO> results = new ArrayList<>();
		int size = pageable.getPageNumber() == 0 ? pageable.getPageNumber() + 1 : pageable.getPageNumber();
		for(int i = pageable.getPageNumber(); i< pageable.getPageSize()* size; i ++){
			ProjectDTO dto5 = new ProjectDTO();
			dto5.setId(Long.valueOf(i));
			dto5.setAddress("廊坊大厂" + i);
			dto5.setEndTime("2018-12-08 12:23:11");
			dto5.setPricipal("于天祥" + i);
			dto5.setRemark("我是备注哦");
			dto5.setStartTime("2018-07-19 12:23:11");
			dto5.setStartTimeFormat("07-19");
			dto5.setType("浮雕");
			results.add(dto5);
		}
		return new PageImpl<>(results, pageable, pageable.getPageSize() * 5);
	}




	@RequestMapping("")
	public String getText() {
		return "hello world";
	}
}
