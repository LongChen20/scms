<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    
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
    	       <h1 class="text-danger"><span class="glyphicon glyphicon-remove-circle" style="position: relative; top:6px;"></span>&nbsp;权限错误！请确认您拥有当前操作的权限</h1>
             <hr>
             <p style="font-size: 20px;">
             您当前所做的操作要求拥有一定的操作权限方可进行<br>
             如您需要进行当前操作，请联系管理员
    			</div>
        </div>
      </div>
    </div>

    	<jsp:include page="include/footer.jsp"></jsp:include>
    
  </body>
</html>
