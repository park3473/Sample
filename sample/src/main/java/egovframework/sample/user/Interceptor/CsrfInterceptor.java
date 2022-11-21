package egovframework.sample.user.Interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CsrfInterceptor extends HandlerInterceptorAdapter {

	protected Log log = LogFactory.getLog(CsrfInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		String reqUrl = request.getRequestURL().toString();
		if(reqUrl.indexOf("/resources/") != -1){
			return true;
		}
		if(reqUrl.indexOf("/filedata/product/filepost.do") != -1){
			return true;
		}
		System.out.println("--------------------csrfInterceptor--------------");
		HttpSession session = request.getSession();
		//새로운 토큰 값 설정
		String token = UUID.randomUUID().toString();
		session.setAttribute("CSRF_TOKEN", token);
		if (log.isDebugEnabled()) {
			log.debug(" Request URI token make \t:  " + token);
		}
		return true;
	}

	@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (log.isDebugEnabled()) {
        }
    }
}
