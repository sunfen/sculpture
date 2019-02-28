package cn.sf.sculpture.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sf.sculpture.project.domain.PrincipalListDTO;
import cn.sf.sculpture.project.service.PrincipalService;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;


@Controller
@RequestMapping("principal")
public class PrincipalController {

	@Autowired
	private PrincipalService principalService;
	
	@GetMapping("search")
	@ResponseBody
	public List<PrincipalListDTO> allProjects() throws BadHanyuPinyinOutputFormatCombination {
		
		return principalService.getList();
	}
	
	
	
}
