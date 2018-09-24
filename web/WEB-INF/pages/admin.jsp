<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fnt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="f" uri="http://itheima.cn/common/" %>
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
            <h1 class="page-header">代码管理</h1>
            <div class="row placeholders">
                <div class="col-md-4">
                    <form action="${pageContext.request.contextPath }/code/adminfandKeyCode" method="post">
                        <div class="input-group">
                            <input type="text" class="form-control" name="findkey" placeholder="按关键词查找">
                            <span class="input-group-btn">
				        <button class="btn btn-default" type="submit"><span
                                class="glyphicon glyphicon-search"></span></button>
				      </span>
                        </div>
                    </form>
                </div>
            </div>
            <div class="table-responsive">
                <table class="table table-bordered  table-striped">
                    <tr>
                        <th width="5%">序号</th>
                        <th width="35%">代码文件</th>
                        <th width="35%">代码说明</th>
                        <th width="10%">上传者</th>
                        <th width="15%">上传时间</th>
                        <th width="15%">操作</th>
                    </tr>

                    <c:choose>
                        <c:when test="${not empty codes.rows }">
                            <c:forEach items="${codes.rows}" var="code" varStatus="status">
                                <tr>
                                    <td align="center">${code.id}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath }/${code.filepath}">${code.codename}</a>
                                    </td>
                                    <td style="text-indent: 2em;">${code.intro }</td>
                                    <td align="center">${code.owner.username }</td>
                                    <td align="center">
                                        <fnt:formatDate value="${code.addTime}" pattern="yyyy年MM月dd日HH：mm"/>
                                    </td>
                                    <td align="center">
                                        <a class="btn btn-default btn-sm btn-success"
                                           href="${pageContext.request.contextPath }/code/update/${code.id}"
                                           role="button"><span class="glyphicon glyphicon-edit"></span>&nbsp;修改</a>
                                        <a class="btn btn-default btn-sm btn-danger remover-form"
                                           href="${pageContext.request.contextPath }/code/deletecode/${code.id}"
                                           role="button"><span class="glyphicon glyphicon-trash"></span>&nbsp;删除</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="6" align="center">暂无数据。</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </table>

                <form class="submitDelete" method="post">
                    <input type="hidden" name="_method" value="DELETE">
                    <%--restful风格的删除操作需要实体的表单设置隐藏字段，action通过jquery的方式插入。--%>
                </form>

                <nav aria-label="Page navigation" class="text-right">

                    <f:page url="${pageContext.request.contextPath }/code/adminfandKeyCode" bean="codes"/>

                </nav>
            </div>
        </div>
    </div>
</div>
<jsp:include page="include/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
    $(this).ready(function () {
        $(".remover-form").click(function () {
           var url =  $(this).attr("href");
            $(".submitDelete").attr("action", url);
            $(".submitDelete").submit();
            return false;
        });
    });
</script>
</html>
