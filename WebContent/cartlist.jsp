<%@ page language="java"  import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<title>我的购物车-小米商城</title>
	<link rel="stylesheet" type="text/css" href="./css/style.css">

 		<script src="./js/jquery-1.11.3.min.js" type="text/javascript" charset="utf-8"></script>
 		<script type="text/javascript">
 			function quanxuan(qx){
 				var check = document.getElementsByName("check");
 				var price = document.getElementsByName("price");
 				var total_price = 0.0;
 				var total_count = 0;
 				for(var i = 0;i<check.length;i++){
 					check[i].checked = qx.checked;
 					if(check[i].checked == true){
 						total_price += parseFloat(price[i].innerText);
 						total_count += 1;
 					}
 				}
 				$("#total_price").attr("value",total_price);
 				$("#total_count").attr("value",total_count);
 			}
	 		function summary(){
 				var check = document.getElementsByName("check");
 				var price = document.getElementsByName("price");
 				var qx = document.getElementById("qx");
 				var total_price = 0.0;
 				var total_count = 0;
	 			for(var i = 0;i<check.length;i++){
	 				if(check[i].checked == true){
	 					total_price += parseFloat(price[i].innerText);
	 					total_count += 1;
	 				}
	 			}
 				if(total_count == check.length){
 					qx.checked = true;
 				}else{
 					qx.checked = false;
 				}
	 			$("#total_price").attr("value",total_price);
	 			$("#total_count").attr("value",total_count);
	 		}
	 		function change_number(obj){
	 			var num1 = obj.value;
	 			var max = $(obj).attr("max");
	 			/* var id = document.getElementById("good_id").value; */
	 			
	 			
	 			if (parseInt(num1)>parseInt(max)){
	 				alert("该宝贝库存只有"+max+"不能再多啦");
	 				return
	 			}
	 			if (num1 <= 0){
	 				alert("该宝贝不能减少了哟~");
	 			}else{
	 				/* document.getElementById("operate").value="change_number";
	 				document.forms[0].submit(); */
	 				
	 				
	 				var operate = "change_number1";
	 				var good_num =num1;
	 				var cart_id =  $(obj).parents(".sub_content").parents(".content2").prev().prev().val();
	 				var good_price = $(obj).parents(".sub_content").parents(".content2").find("#good_price").val();
	 				 $.ajax({
	 					url:"CartServlet",
	 					type:"post",
	 					data:{"operate":operate,"good_num":good_num,"cart_id":cart_id,"good_price":good_price},
	 					datatype:"json",
	 					success:function(data){
	 						var data1=JSON.parse(data)
	 						$(obj).parents(".sub_content").parents(".content2").find("#good_num").val(data1.num)
	 						$(obj).parents(".sub_content").parents(".content2").find("#price").val(data1.price)
	 						$(obj).parents(".sub_content").parents(".content2").find("#price").next().text(data1.price.toFixed(1))
	 						
	 					}
	 					
	 				}) 
	 			}
	 		}
	 		function settlement(obj){
	 			var check = document.getElementsByName("check");
	 			var cart_id = document.getElementsByName("cart_id");
	 			var temperate_id = document.getElementsByName("temperate_id");
	 			
	 			for(var i = 0;i<check.length;i++){
		 			if(check[i].checked == true){
		 				temperate_id[i].value = cart_id[i].defaultValue;
		 			}	 			
	 			}
	 			
	 			
	 		}
 		</script>
  </head>
  
  <body>
    <!-- start banner_x -->
		<div class="banner_x center">
			<a href="./index.html" target="_blank"><div class="logo fl"></div></a>
			
			<div class="wdgwc fl ml40">我的购物车</div>
			<div class="wxts fl ml20">温馨提示：产品是否购买成功，以最终下单为准哦，请尽快结算</div>
			<div class="dlzc fr">
				<ul>
					<% 
					String username = (String)session.getAttribute("username");
					int uid = (Integer)session.getAttribute("uid");
					if(username!=null){
					%>
						<li><%=username %></li>
						<li><a href="UserServlet?operate=selfinfo">个人中心</a></li>
						<li><a href="UserServlet?operate=logout">退出登录 </a> </li>
						
				<% }else{%>
				
						<li><a href="login.jsp" >登录</a></li>
						<li>|</li>
						<li><a href="register.jsp" >注册</a></li>
						<li>|</li>
						<li><a href="">消息通知</a></li>
				<%} %>
				</ul>
				
			</div>
			<div class="clear"></div>
		</div>
		<div class="xiantiao"></div>
		<div class="gwcxqbj">
		<form method="post" action="CartServlet">
		<input type="hidden" id="operate" name="operate" value="jiesuan"/>
			<div class="gwcxd center">
				<div class="top2 center">
					<div class="sub_top fl">
						<input type="checkbox" id = "qx" name="qx" class="quanxuan" onclick="quanxuan(this)"/>全选
					</div>
					<div class="sub_top fl">商品名称</div>
					<div class="sub_top fl">单价</div>
					<div class="sub_top fl">数量</div>
					<div class="sub_top fl">小计</div>
					<div class="sub_top fr">操作</div>
					<div class="clear"></div>
				</div>
				<% int count = 0;%>
				<c:forEach items="${cartlist}" var="list">
				<% count += 1; %>
				<input type="hidden" id = "cart_id" name = "cart_id" value ="${list.preId}" />
				<input type = "hidden" id = "temperate_id" name = "temperate_id" value = "flag"/>
					<div class="content2 center">
						<div class="sub_content fl">
							<input type="checkbox" id = "check" name="check" class="quanxuan" onclick="summary()" />
						</div>
						<div class="sub_content fl"><img style="height:80px;width:80px;" src="${list.g.goodImg}"></div>
						<div class="sub_content fl ft20">${list.g.goodName} ${list.g.goodType} ${list.g.goodColor}</div>
						<div class="sub_content fl ft20"><input type = "hidden" id= "good_price" name = "good_price" value="${list.g.goodPrice}"/>${list.g.goodPrice}</div>
						<div class="sub_content fl">
							<input class="shuliang" type="number" autocomplete="off" id = "good_num" name = "good_num" value="${list.goodNum}" step="1" min="1" max ="${list.g.goodCount}" onblur= "change_number(this)" >
						</div>
						<div class="sub_content fl" name ="price"><span><input type = "hidden" id= "price" value="${list.price}"/>
						<span>${list.price}</span></span></div>
						<div class="sub_content fl"><a href="CartServlet?operate=deleteCart&id=${list.preId}"><img src="./image/timg.png"></a></div>
						<div class="clear"></div>
					</div>
				</c:forEach>
			</div>
			<div class="jiesuandan mt20 center">
				<div class="tishi fl ml20">
					<ul>
						<li><a href="./index.jsp">继续购物</a></li>
						<li>|</li>
						<li>共<span><%=count %></span>件商品，已选择<span><input id = "total_count" type = "text"  name ="total_count" value = 0 style="border:0px;width:20px" readonly/></span>件</li>
						<div class="clear"></div>
					</ul>
				</div>
				<div class="jiesuan fr">
					<div class="jiesuanjiage fl">合计（不含运费）：<input id = "total_price" type = "text"  name ="total_price" value = 0.0 style="border:0px;width:40px" readonly/></div>
					<div class="jsanniu fr"><a href="alipay.jsp"><input class="jsan" type="submit"  id  = "jiesuan" name="jiesuan"  value="去结算" onclick="settlement(this)"/></a></div>

					<div class="clear"></div>
				</div>
				<div class="clear"></div>
			</div>
			</form>
		</div>	
  </body>
</html>
