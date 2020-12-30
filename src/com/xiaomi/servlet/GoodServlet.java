package com.xiaomi.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiao.service.CartService;
import com.xiao.service.GoodService;
import com.xiaomi.pojo.vo.Cart;
import com.xiaomi.pojo.vo.Good;
import com.xiaomi.pojo.vo.Users;

/**
 * Servlet implementation class GoodServlet
 */
@WebServlet("/GoodServlet")
public class GoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String operate = request.getParameter("operate");
		GoodService goodservice = new GoodService();
		CartService cartService = new CartService();
		if("xiaomi".equals(operate)){
			List<Good> selectGoodList = goodservice.selectGoodList();
			request.getSession().setAttribute("goodsList", selectGoodList);
			request.getRequestDispatcher("goods_list.jsp").forward(request, response);
		}
		if("detail".equals(operate)){
			int goodnum=1;
			Users user = (Users)request.getSession().getAttribute("user");
			System.out.println("景来了");
			if(user==null){
				System.out.println("未登陆");
				request.getRequestDispatcher("errorempty.jsp").forward(request, response);
			}else{
				String name =request.getParameter("good_name");				
				List<Good> selectGoodByName = goodservice.selectGoodByName(name);
				CartService cartservice = new CartService();
				for(Good good:selectGoodByName){
					
					List<Cart> selectCartList = cartservice.selectCartList(user.getUid());
					if(selectCartList.size()==0){
						
						Cart cart = new Cart();
						cart.setGoodId(good.getGoodId());
						cart.setGoodNum(goodnum);
						cart.setPrice(good.getGoodPrice());
						cart.setStatus(0);
						int uid = (int)request.getSession().getAttribute("uid");
						cart.setUid(uid);
						int insert = cartService.insert(cart);
						if(insert==1){
							request.getRequestDispatcher("success_add_cart.jsp").forward(request, response);
						}else{
							request.getRequestDispatcher("index.jsp").forward(request, response);
						}
					}
					
					else{
						
						for(Cart cart1:selectCartList){
							if(cart1.getGoodId()==good.getGoodId()){
								
								Cart cart = new Cart();
								cart.setGoodNum(cart1.getGoodNum()+1);
								cartService.updataGood_Num(cart , cart1.getPreId());
								
								
								request.getRequestDispatcher("success_add_cart.jsp").forward(request, response);
							}else{
								
								Cart cart = new Cart();
								cart.setGoodId(good.getGoodId());
								cart.setGoodNum(goodnum);
								cart.setPrice(good.getGoodPrice());
								cart.setStatus(0);
								int uid = (int)request.getSession().getAttribute("uid");
								cart.setUid(uid);
								int insert = cartService.insert(cart);
								if(insert==1){
									request.getRequestDispatcher("success_add_cart.jsp").forward(request, response);
								}else{
									request.getRequestDispatcher("index.jsp").forward(request, response);
								}
							}
						}
					}
					
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operate = request.getParameter("operate");
		GoodService goodservice = new GoodService();
		if("search".equals(operate)){
			String goodName = request.getParameter("good_name");
			List<Good> selectGoodByName = goodservice.selectGoodByName(goodName);
			request.getSession().setAttribute("searchgoods", selectGoodByName);
			request.getRequestDispatcher("searchlist.jsp").forward(request, response);
		}
		
		
	}

}
