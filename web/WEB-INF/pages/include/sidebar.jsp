s<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<!-- 左侧边栏 -->
<div class="col-sm-3 col-md-2 sidebar">
   <div style="padding:0px 0px 20px 0px;">
     <span class="glyphicon glyphicon-bell"></span>&nbsp;当前在线人数：${applicationScope.online}人
   </div>
     <ul class="nav nav-sidebar" style="font-size: 18px;">
         <li class="active"><a href="${pageContext.request.contextPath }/code/codes"><span class="glyphicon glyphicon-list-alt"></span>&nbsp;代码一览 <span class="sr-only">(current)</span></a></li>

         <c:choose>
             <c:when test="${loginUser.rank==1}">
                 <li><a href="${pageContext.request.contextPath }/code/admin"><span class="glyphicon glyphicon-cog"></span>&nbsp;代码管理</a></li>
                 <li><a href="${pageContext.request.contextPath }/upload"><span class="glyphicon glyphicon-upload"></span>&nbsp;代码上传</a></li>
             </c:when>
         </c:choose>




     </ul>
</div>