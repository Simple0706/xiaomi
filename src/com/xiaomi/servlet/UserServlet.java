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
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	String operate = request.getParameter("operate");
    	//跳转登录界面
    	if("logout".equals(operate)){
			request.getSession().invalidate();
			request.getRequestDispatcher("index.jsp").forward(request, response);
			}
    	//跳转个人中心
    	if("selfinfo".equals(operate)){
    		request.getRequestDispatcher("self_info.jsp").forward(request, response);
    			
    		}
    	//跳转修改个人中心
    	if("edit".equals(operate)){
    		String uid = request.getParameter("uid");
    		request.getSession().setAttribute("uid", uid);
    		request.getRequestDispatcher("edituser.jsp").forward(request, response);
    	}
    	
    	
    	
    }
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		UserService userService = new UserService();
		String operate = request.getParameter("operate");
		//注册验证
		if("register".equals(operate)){
			String image = request.getParameter("image").toLowerCase();
			String code =(String) request.getSession().getAttribute("code");
			String code1 = code.toLowerCase();
			if(!image.equals(code1)){
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
		}
		//登录验证
		if("login".equals(operate)){
			
			String image = request.getParameter("image").toLowerCase();
			String code =(String) request.getSession().getAttribute("code");
			String code1=code.toLowerCase();
			if(!image.equals(code1)){
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
				session.setAttribute("user",login);
				session.setAttribute("username",login.getUsername());
				session.setAttribute("uid", login.getUid());
				request.getSession().setAttribute("cartGood", false);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else{
				request.setAttribute("msg", "用户名不存在或密码错误");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
		//修改个人中心资料
		if("editupdate".equals(operate)){
			String uid = (String)request.getSession().getAttribute("uid");
			String password = request.getParameter("password");
			String phonenumber = request.getParameter("phonenumber");
			String address = request.getParameter("address");
			String hobby = request.getParameter("hobby");
			String sign = request.getParameter("sign");
			Users upuser = new Users();
			upuser.setPassword(password);
			upuser.setHobby(hobby);
			upuser.setPhonenumber(phonenumber);
			upuser.setSign(sign);
			upuser.setAddress(address);
			Users editupdate = userService.editupdate(upuser,Integer.valueOf(uid));
			request.getSession().removeAttribute("user");
			request.getSession().setAttribute("user", editupdate);
			request.getRequestDispatcher("self_info.jsp").forward(request, response);
			

			
		}
		if("ajaxregister".equals(operate)){
			String username = request.getParameter("username");
			Users user = new Users();
			user.setUsername(username);
			int register = userService.ajaxregister(user);
			if(register==0){
				response.getWriter().append("false");
			}else{
				response.getWriter().append("true");
			}
		}
		
		
		
		
	}

}
