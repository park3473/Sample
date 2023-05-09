package egovframework.sample.user.Interceptor;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import egovframework.sample.manager.accept_log.service.UserAcceptLogService;

public class UserLoginInterceptor extends HandlerInterceptorAdapter {

	
	@Value("#{DB['currentPrjName']}")
	public String currentPrjName;

	protected Log log = LogFactory.getLog(UserLoginInterceptor.class);


	@Autowired
	SessionLocaleResolver localeResolver;
	
	@Autowired
	UserAcceptLogService AcceptLogService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		String level = session.getAttribute("UserLevel") + "";
		int levelInt = Integer.parseInt(level);
		if(level.equals("1") || levelInt >= 1)
		{
			return true;
		}else 
		{
			response.sendRedirect(request.getContextPath()+"/index.do");
			return false;
		}
		
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("======================================           END          ======================================\n");
			
		}
	}
	
	

}
