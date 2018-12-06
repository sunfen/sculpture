package cn.sf.sculpture.home;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.sf.sculpture.home.domian.ProjectDTO;

@RestController
public class HomeController {

	@GetMapping("allProjects")
	@ResponseBody
	public Page<ProjectDTO> allProjects(
			@PageableDefault(page = 0, size = 7) Pageable pageable) {
		
		List<ProjectDTO> results = new ArrayList<>();
//		ProjectDTO dto1 = new ProjectDTO();
//		dto1.setId(1l);
//		dto1.setAddress("北京宋庄");
//		dto1.setEndTime("2018-12-08 12:23:11");
//		dto1.setPricipal("于鑫");
//		dto1.setRemark("12个字我是备注哦12个字我是备注哦12个字我是备注哦");
//		dto1.setStartTime("2018-11-08 12:23:11");
//		dto1.setType("浮雕");
//		dto1.setStartTimeFormat("11-08");
//		results.add(dto1);
//		
//		ProjectDTO dto2 = new ProjectDTO();
//		dto2.setId(2l);
//		dto2.setAddress("顺义西小区");
//		dto2.setPricipal("苏凌志");
//		dto2.setRemark("我是备注哦");
//		dto2.setStartTime("2018-01-08 12:23:11");
//		dto2.setStartTimeFormat("01-08");
//		dto2.setType("圆雕");
//		results.add(dto2);
//		
//		ProjectDTO dto3 = new ProjectDTO();
//		dto3.setId(3l);
//		dto3.setAddress("北京宋庄双埠头");
//		dto3.setEndTime("2018-04-24 12:02:11");
//		dto3.setPricipal("刘东");
//		dto3.setStartTime("2018-06-08 12:23:11");
//		dto3.setStartTimeFormat("06-28");
//		dto3.setType("浮雕&圆雕");
//		results.add(dto3);
//		
//		ProjectDTO dto4 = new ProjectDTO();
//		dto4.setId(4l);
//		dto4.setAddress("廊坊大厂");
//		dto4.setEndTime("2018-12-08 12:23:11");
//		dto4.setPricipal("于天祥");
//		dto4.setRemark("我是备注哦");
//		dto4.setStartTime("2018-07-19 12:23:11");
//		dto4.setStartTimeFormat("07-19");
//		dto4.setType("浮雕");
//		results.add(dto4);
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
