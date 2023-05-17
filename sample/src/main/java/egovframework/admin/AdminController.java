package egovframework.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.system.util.SUtil;

@Controller
public class AdminController {
	
	@RequestMapping(value="/admin/index.do" , method = RequestMethod.GET)
	public String AdminIndex(HttpServletRequest request , HttpServletResponse response) {
		
		System.out.println("admin index");
		
		
		return "admin/index";
		
	}
	
	@RequestMapping(value="/admin/alertmove/test.do" , method = RequestMethod.GET)
	public void AdminSubPageDelete(HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		SUtil.AlertAndPageMove(response, "페이지가 이동되었습니다.", "/admin/subpage/list.do");
		
	}
	

}
