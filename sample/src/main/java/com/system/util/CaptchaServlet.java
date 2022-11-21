package com.system.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CaptchaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      /*  Captcha captcha = new Captcha.Builder(148, 48)
                .addText() // default: 5개의 숫자+문자
                .addNoise().addNoise() // 시야 방해 라인 3개
                .addBackground() // 기본 하얀색 배경
                .build();
        
        AudioCaptcha ac = new AudioCaptcha.Builder()
		.addAnswer()
		.addNoise()
		.build();
 
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Max-Age", 0);
        response.setContentType("image/png");
        CaptchaServletUtil.writeImage(response, captcha.getImage()); // 이미지 그리기
        request.getSession().setAttribute("captcha", captcha.getAnswer()); // 값 저장
       */
    }
}
