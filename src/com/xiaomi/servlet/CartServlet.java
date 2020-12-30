package com.xiaomi.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xiao.service.CartService;
import com.xiaomi.pojo.vo.Cart;
import com.xiaomi.pojo.vo.CartGood;
import com.xiaomi.pojo.vo.Good;
import com.xiaomi.pojo.vo.Users;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CartService cartService = new CartService();
		HttpSession session = request.getSession();
		Users user = (Users)session.getAttribute("user");
		if(user==null){
			request.getRequestDispatcher("errorempty.jsp").forward(request, response);
		}else{
			boolean attribute =(boolean) request.getSession().getAttribute("cartGood");
			if(attribute==true){
				request.getRequestDispatcher("cartlist.jsp").forward(request, response);
			}else{
			Integer uid = user.getUid();
			List<Cart> cartlist1 = cartService.selectCartList(uid);
			List<CartGood> cartlist = new ArrayList();
			for(Cart cart:cartlist1){
				int id=cart.getGoodId();
				Good good = cartService.selectGoodById(id);
				CartGood cartGood = new CartGood(cart,good);
				cartlist.add(cartGood);
				request.getSession().removeAttribute("cartGood");
				request.getSession().setAttribute("cartGood", true);
			}
			request.getSession().setAttribute("cartlist", cartlist);
			
			request.getRequestDispatcher("cartlist.jsp").forward(request, response);
		}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
