package com.xiaomi.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiao.service.XmOrderService;
import com.xiaomi.pojo.vo.Xmorder;

/**
 * Servlet implementation class Xmorder
 */
@WebServlet("/XmOrderServlet")
public class XmOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XmOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		XmOrderService xm = new XmOrderService();
		List<Xmorder> selectAll = xm.selectAll();
		for(Xmorder xmorder : selectAll){
			String cartId = xmorder.getCartId();
			String[] split = cartId.split("#");
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
