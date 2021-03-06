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

import net.sf.json.JSONObject;

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
		String operate = request.getParameter("operate");
		if("deleteCart".equals(operate)){
			String idstr =request.getParameter("id");
			int id = Integer.valueOf(idstr);
			int uid = user.getUid();
			int deleteCartById = cartService.deleteCartById(id,uid);
//			this.doGet(request, response);
//			request.getRequestDispatcher("CartServlet").forward(request, response);
			response.sendRedirect("CartServlet");
			return ;
		}
		
		if(user==null){
			request.getRequestDispatcher("errorempty.jsp").forward(request, response);
		}else{
			Integer uid = user.getUid();
			List<Cart> cartlist1 = cartService.selectCartList(uid);
			if(cartlist1.size()==0){
				request.getRequestDispatcher("errorempty.jsp").forward(request, response);
				return ;
			}
			List<CartGood> cartlist = new ArrayList();
			for(Cart cart:cartlist1){
				int id=cart.getGoodId();
				Good good = cartService.selectGoodById(id);
				CartGood cartGood = new CartGood(cart,good);
				cartlist.add(cartGood);
			}
			request.getSession().removeAttribute("cartGood");
			request.getSession().setAttribute("cartlist", cartlist);
			
			request.getRequestDispatcher("cartlist.jsp").forward(request, response);
		
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CartService cartService = new CartService();
		HttpSession session = request.getSession();
		Users user = (Users)session.getAttribute("user");
		String operate = request.getParameter("operate");
		
		//增加数据
		
		if("change_number".equals(operate)){
			String[] cartid = request.getParameterValues("cart_id");
			String[] cartnum = request.getParameterValues("good_num");
			String[] good_price = request.getParameterValues("good_price");
			
			for(int x=0;x<cartid.length;x++){
				int cartid1=Integer.valueOf(cartid[x]);
				int cartnum1 = Integer.valueOf(cartnum[x]);
				float parseFloat = Float.parseFloat(good_price[x]);
				Cart cart = new Cart();
				cart.setPreId(cartid1);
				cart.setGoodNum(cartnum1);
				cart.setPrice(parseFloat*cartnum1);
				boolean is = cartService.updateCartCartByCartId(cart);
			}
//			request.getRequestDispatcher("CartServlet").forward(request, response);
			response.sendRedirect("CartServlet");
			return ;
		}if("change_number1".equals(operate)){
			String cartid = request.getParameter("cart_id");
			String cartnum = request.getParameter("good_num");
			String good_price = request.getParameter("good_price");
			int cartid1=Integer.valueOf(cartid);
			int cartnum1 = Integer.valueOf(cartnum);
			float parseFloat = Float.parseFloat(good_price);
			Cart cart = new Cart();
			cart.setPreId(cartid1);
			cart.setGoodNum(cartnum1);
			cart.setPrice(parseFloat*cartnum1);
			boolean is = cartService.updateCartCartByCartId(cart);
			JSONObject js = new JSONObject();
			js.put("num", cartnum1);
			js.put("price", cart.getPrice());
			
			response.getWriter().append(js.toString());
		}
		if("jiesuan".equals(operate)){
			
			String total_price=request.getParameter("total_price");
			session.setAttribute("pirce", total_price);
			request.getRequestDispatcher("zhifu.jsp").forward(request, response);
		}
		
		
		
	}
	

}
