<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

  <%@ include file="include/include.jsp" %>

    <title>代码管理系统</title>
    
		<link href="${pageContext.request.contextPath }/resource/bootstrap-3.3.7-dist/css/dashboard.css" rel="stylesheet">
  
  </head>

  <body>

    <jsp:include page="include/heder.jsp"></jsp:include>

    <!-- Begin page content -->
    <div class="container-fluid">
      <div class="row">
      
        <jsp:include page="include/sidebar.jsp"></jsp:include>
        
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    			<div class="text-center">
    	       <h1 class="text-danger"><span class="glyphicon glyphicon-remove-circle" style="position: relative; top:6px;"></span>&nbsp;未登录错误！请先登录</h1>
             <hr>
             <p style="font-size: 20px;">
             您当前所做的操作要求在已登录状态进行<br>
             如您已有账户，请先 <a href="${pageContext.request.contextPath}/login">登录</a><br>
             如您尚未注册账户，请先 <a href="${pageContext.request.contextPath }/register">注册</a> 再登录</p>
    			</div>
        </div>
      </div>
    </div>

    <jsp:include page="include/footer.jsp"></jsp:include>
    
  </body>
</html>
