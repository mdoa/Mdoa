package com.htsoft.core.web.servlet;

import static nl.captcha.Captcha.NAME;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import nl.captcha.Captcha;
import nl.captcha.servlet.CaptchaServletUtil;

/**
 * Generates, displays, and stores in session a 200x50 CAPTCHA image with
 * sheared black text, a solid dark grey background, and a straight, slanted red
 * line through the text. 画出一个验证码
 * 
 * @author <a href="mailto:james.childers@gmail.com">James Childers</a>
 */
public class SimpleCaptchaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Log logger = LogFactory.getLog(SimpleCaptchaServlet.class);

	private static final String PARAM_HEIGHT = "height";
	private static final String PARAM_WIDTH = "width";

	protected int _width = 200;
	protected int _height = 50;

	@Override
	public void init() throws ServletException {
		if (getInitParameter(PARAM_HEIGHT) != null) {
			_height = Integer.valueOf(getInitParameter(PARAM_HEIGHT));
		}

		if (getInitParameter(PARAM_WIDTH) != null) {
			_width = Integer.valueOf(getInitParameter(PARAM_WIDTH));
		}
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Captcha captcha = new Captcha.Builder(_width, _height).addText()
					.addBackground()
					// .gimp()//文字扭曲
					.addNoise()// 干扰
					// .addBorder()//边框
					.build();

			CaptchaServletUtil.writeImage(resp, captcha.getImage());
			req.getSession().setAttribute(NAME, captcha);
		} catch (Exception e) {
			// TODO: handle exception
			logger.debug(e.getStackTrace());
		}
	}
}
