package com.xiaomi.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xiao.service.UserService;
import com.xiaomi.pojo.vo.Users;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserServlet() {
        super();
        
    }

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService userService = new UserService();
		String operate = request.getParameter("operate");
		if("register".equals(operate)){
			String image = request.getParameter("image");
			String code =(String) request.getSession().getAttribute("code");
			if(!image.equals(code)){
				request.setAttribute("rmsg", "验证码错误");
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return ;
			}
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String phonenumber = request.getParameter("phonenumber");
			String address = request.getParameter("address");
			String hobby = request.getParameter("hobby");
			String sign = request.getParameter("sign");
			Users user=new Users();
			user.setUsername(username);
			user.setPassword(password);
			user.setPhonenumber(phonenumber);
			user.setAddress(address);
			user.setHobby(hobby);
			user.setSign(sign);
			 int register = userService.register(user);
			if(register==0){
				request.setAttribute("rmsg", "用户名已注册");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}if("login".equals(operate)){
			
			String image = request.getParameter("image");
			String code =(String) request.getSession().getAttribute("code");
			if(!image.equals(code)){
				request.setAttribute("imageError", "验证码错误");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return ;
			}
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Users user = new Users();
			user.setUsername(username);
			user.setPassword(password);
			 Users login = userService.login(user);
			if(login!=null){
				HttpSession session = request.getSession();
				session.setAttribute("username",login);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else{
				request.setAttribute("msg", "用户名不存在或密码错误");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			
		}
		
		
		
		
	}

}
