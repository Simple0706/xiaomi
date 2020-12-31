package com.xiaomi.servlet;

import java.io.IOException;
import java.util.ArrayList;
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
		String name =request.getParameter("good_name");	
		GoodService goodservice = new GoodService();
		
		//显示小米手机列表
		if("xiaomi".equals(operate)){
			List<Good> selectGoodList = goodservice.selectGoodList();
			request.getSession().setAttribute("goodsList", selectGoodList);
			request.getRequestDispatcher("goods_list.jsp").forward(request, response);
		}
		
		//详情页
		//good_name=xiaomi6
		if("detail".equals(operate)){
			List<Good> selectGoodByName = goodservice.selectGoodByName(name);
			request.setAttribute("goodlist",selectGoodByName );
			
			request.getRequestDispatcher("goods_details.jsp").forward(request, response);;
		}		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operate = request.getParameter("operate");
		String name =request.getParameter("good_name");	
		GoodService goodservice = new GoodService();
		CartService cartService = new CartService();
		if("search".equals(operate)){
			String goodName = request.getParameter("good_name");
			List<Good> selectGoodByName = goodservice.selectGoodLikeByName("%"+goodName+"%");
			request.getSession().setAttribute("searchgoods", selectGoodByName);
			request.getRequestDispatcher("searchlist.jsp").forward(request, response);
		}
		
		//添加购物车
				if("buy".equals(operate)){
					int goodnum=1;
					Users user = (Users)request.getSession().getAttribute("user");
					//判断是否登录
					if(user==null){
						System.out.println("未登陆");
						request.getRequestDispatcher("errorempty.jsp").forward(request, response);
					}else{	
							String type=request.getParameter("type");
							String color=request.getParameter("color");
							if(type!=null&&color!=null){
								Good good = goodservice.selectGoodByNameAndType(name,type);
								int goodId = good.getGoodId();
								CartService cartservice = new CartService();
								Cart cartall = cartservice.selectGoodByUidAndGoodid(user.getUid(),goodId);
								if(cartall==null){
									Cart cart = new Cart();
									cart.setGoodId(good.getGoodId());
									cart.setGoodNum(goodnum);
									cart.setPrice(good.getGoodPrice());
									cart.setStatus(0);
									cart.setUid(user.getUid());
									int insert = cartService.insert(cart);
									if(insert==1){
										request.getRequestDispatcher("success_add_cart.jsp").forward(request, response);
										return ;
									}else{
										request.getRequestDispatcher("index.jsp").forward(request, response);
										return ;
									}
								}else{
									Cart cart = new Cart();
									cart.setGoodNum(cartall.getGoodNum()+1);
									cart.setPrice(cartall.getPrice()*2);
									int updateCartCartByCartId = cartservice.updateCartCartByCartId(cart, user.getUid(),cartall.getPreId());
									request.getRequestDispatcher("success_add_cart.jsp").forward(request, response);
								}
								
							}
							else{
								System.out.println("未选择属性标签");
							}
					}
				}
		
		
	}

}
