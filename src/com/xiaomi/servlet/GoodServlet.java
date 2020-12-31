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
		CartService cartService = new CartService();
		//显示小米手机列表
		if("xiaomi".equals(operate)){
			List<Good> selectGoodList = goodservice.selectGoodList();
			request.getSession().setAttribute("goodsList", selectGoodList);
			request.getRequestDispatcher("goods_list.jsp").forward(request, response);
		}
		//添加购物车
		if("detail".equals(operate)){
			int goodnum=1;
			Users user = (Users)request.getSession().getAttribute("user");
			//判断是否登录
			if(user==null){
				System.out.println("未登陆");
				request.getRequestDispatcher("errorempty.jsp").forward(request, response);
			}else{
							
				List<Good> selectGoodByName = goodservice.selectGoodByName(name);
				CartService cartservice = new CartService();
				List<Cart> selectCartList = cartservice.selectCartList(user.getUid());
				int uid = (int)request.getSession().getAttribute("uid");
				for(Good good:selectGoodByName){
	
					if(selectCartList.size()==0){
						
						Cart cart = new Cart();
						cart.setGoodId(good.getGoodId());
						cart.setGoodNum(goodnum);
						cart.setPrice(good.getGoodPrice());
						cart.setStatus(0);
						cart.setUid(uid);
						int insert = cartService.insert(cart);
						if(insert==1){
							request.getRequestDispatcher("success_add_cart.jsp").forward(request, response);
							return ;
						}else{
							request.getRequestDispatcher("index.jsp").forward(request, response);
							return ;
						}
					}
					
					else{
						
						
						
//						List listGood_id = new ArrayList();
//						for(Cart cart1:selectCartList){
//							listGood_id.add(cart1.getGoodId());
//						}
						
//						for(int x=0;x<listGood_id.size();x++){
//							int goodid = (int)listGood_id.get(x);
//							//判断购物车是否已存在相同手机
//							Cart creadata = cartService.isCreadata(goodid,user.getUid());
//							if(creadata==null){
//								continue;
//							}else{
//								response.getWriter().append("购物车已经存在该手机了啦");
//								return;
//							}
//						}
						
								Cart cart = new Cart();
								cart.setGoodId(good.getGoodId());
								cart.setGoodNum(goodnum);
								cart.setPrice(good.getGoodPrice());
								cart.setStatus(0);
								
								cart.setUid(uid);
								int insert = cartService.insert(cart);
								if(insert==1){
									request.getRequestDispatcher("success_add_cart.jsp").forward(request, response);
									return ;
								}else{
									request.getRequestDispatcher("index.jsp").forward(request, response);
									return ;
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
