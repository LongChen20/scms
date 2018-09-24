<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
  
    <%@ include file="include/include.jsp" %>
    
    <title>代码管理系统</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/resource/bootstrap-3.3.7-dist/css/dashboard.css" >
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/resource/bootstrap-fileinput-master/css/fileinput.css" />
  </head>

  <body>

 		<jsp:include page="include/heder.jsp"></jsp:include>
 
    <!-- Begin page content -->
    <div class="container-fluid">
      <div class="row">
      
        <jsp:include page="include/sidebar.jsp"></jsp:include>
        
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        	<h1 class="page-header">代码上传</h1>
        	<div class="row placeholders">
        		<div class="col-md-8">
		        	<form action="${pageContext.request.contextPath }/code/submitupload" class="form-horizontal" method="post" enctype="multipart/form-data">
					  <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">代码文件</label>
					    <div class="col-sm-5">
					      <input type="file" class="form-control file" name="codefile" id="codefile" placeholder="代码文件" data-msg-placeholder="请选择文件" data-show-preview="false" data-show-upload="false">
					    </div>
					    <div class="col-sm-5">
					      <p class="text-warning text-left" style="font-size: 16px; padding-top: 4px;"><span class="glyphicon glyphicon-info-sign"></span>&nbsp;上传文件限定于.zip，.rar文件，文件大小不超过20M</p>
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="inputPassword3" class="col-sm-2 control-label">代码说明</label>
					    <div class="col-sm-5">
					      <textarea class="form-control" id="intro" name="intro" placeholder="代码说明" rows="5"  style="resize:none;"></textarea>
					    </div>
					    <div class="message-intro">
					    
					      <!-- <p class="text-danger text-left remove-p" style="font-size: 16px; padding-top: 4px;"><span class="glyphicon glyphicon-remove-sign"></span>&nbsp;代码说明不可为空！</p> -->
					   
					    </div>
					  </div>
					  <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-5">
					      <button type="submit" class="btn btn-success btn-block">提交</button>
					    </div>
					  </div>
					</form>
	        	</div>
        	</div>
			
			</div>
        </div>
      </div>
    
	<jsp:include page="include/footer.jsp"></jsp:include>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resource/bootstrap-fileinput-master/js/fileinput.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resource/bootstrap-fileinput-master/js/locales/zh.js"></script>

  </body>
	<script type="text/javascript">
		$(this).ready(function(){
			$("#intro").blur(function (){
				var value = $(this).val();
					$(".remove-p").remove();
				if(value==null||value==""){
					var p = $("<p class='remove-p text-danger text-left ' style='font-size: 16px; padding-top: 4px;'><span class='glyphicon glyphicon-remove-sign'></span>&nbsp;代码说明不可为空！</p>");
					$(".message-intro").append(p);
				}else{
					$(".remove-p").remove();
				};
			});
		});
	</script>
</html>
