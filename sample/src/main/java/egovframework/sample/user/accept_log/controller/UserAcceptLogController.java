package egovframework.sample.user.accept_log.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import egovframework.sample.manager.accept_log.service.UserAcceptLogService;
import egovframework.sample.user.accept_log.model.UserAcceptLogVo;

@Controller
public class UserAcceptLogController {

	@Autowired
	UserAcceptLogService UserAcceptLogService;
	
	protected Log log = LogFactory.getLog(UserAcceptLogController.class);
	
	
	
}
