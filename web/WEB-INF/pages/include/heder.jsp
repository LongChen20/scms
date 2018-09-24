<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<!-- 头部 -->
<nav class="navbar navbar-inverse navbar-fixed-top" style="height: 80px;">
	<div class="container-fluid">
	  <div class="navbar-header" style="height: 80px;">
	   <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
	     <span class="sr-only">Toggle navigation</span>
	     <span class="icon-bar"></span>
	     <span class="icon-bar"></span>
	     <span class="icon-bar"></span>
	   </button>
	   <a class="navbar-brand" href="${pageContext.request.contextPath}/home">
	   	<img src="${pageContext.request.contextPath }/resource/images/logo.png" alt="" width="55px" height="55px">
	   </a>
	   <a class="navbar-brand" href="${pageContext.request.contextPath}/home">
	   	<h2 style="margin:10px;"><span style="color:#02e207;">代码</span>管理系统</h2>
	   </a>
	 </div>
	 
	 <c:choose>
	 	<c:when test="${empty sessionScope.loginUser }">
	 			<!-- 如果session中是空的则显示未登陆状态 -->
			 <div id="navbar" class="navbar-collapse collapse" style="height: 80px;">
			   <ul class="nav navbar-nav navbar-right" style="padding:15px 15px 0px 0px;">
			     <li><button id="login-btn" type="button" class="btn btn-success" style="margin: 10px;"> <span class="glyphicon glyphicon-log-in"></span>&nbsp;登录</button></li>
			     <li><button id="register-btn" type="button" class="btn btn-info" style="margin: 10px;"> <span class="glyphicon glyphicon-user"></span>&nbsp;注册</button></li>
			      </ul>
			  </div>
	 	</c:when>
	 	<c:otherwise>
	 			<!-- 显示登陆状态 -->
		 	 <div id="navbar" class="navbar-collapse collapse" style="height: 80px;">
	            <ul class="nav navbar-nav navbar-right" style="padding:15px 15px 0px 0px;">
		            <li class="dropdown">
			          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span style="font-size: 18px;"><span class="glyphicon glyphicon-user"></span>&nbsp; ${sessionScope.loginUser.username } <span class="caret"></span></span></a>
			          <ul class="dropdown-menu" style="margin-top: 12px;">
			            <li><a href="${pageContext.request.contextPath}/change_password"><span class="glyphicon glyphicon-cog"></span>&nbsp;修改密码</a></li>
			            <li><a href="${pageContext.request.contextPath}/user/loginout"><span class="glyphicon glyphicon-log-out"></span>&nbsp;退出登录</a></li>
			          </ul>
			        </li>
		        </ul>
	        </div>
	 	</c:otherwise>
	 </c:choose>
	 
	</div>
</nav>
<script type="text/javascript">
	$(this).ready(function(){
		$("#login-btn").click(function(){
			location.href="login";
		});
		$("#register-btn").click(function(){
            location.href="register";
		});
	});
</script>