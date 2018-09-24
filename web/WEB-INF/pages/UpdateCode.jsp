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
            <h1 class="page-header">代码管理</h1>
            <div class="row placeholders">
                <div class="col-md-8">
                    <form action="${pageContext.request.contextPath }/code/submitupdate/${code.id}" class="form-horizontal"
                          method="post">
                        <%-- restful 风格修改。需要在表单中添加隐藏字段。 namewe=_method value是提交的格式--%>
                        <input type="hidden" name="_method" value="PUT">
                        <input type="hidden" name="id" value="${code.id}">
                        <div class="form-group">
                            <label for="inputEmail3" class="col-sm-2 control-label">代码名称</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" name="codename" id="codename" placeholder="代码名称"
                                       value="${code.codename }">
                            </div>
                            <div class="message-codename">
                                <p class="msgcodename text-warning text-left"
                                   style="font-size: 16px; padding-top: 4px;"><span
                                        class="glyphicon glyphicon-info-sign"></span>&nbsp; 代码名称</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label">代码说明</label>
                            <div class="col-sm-5">
                                <textarea class="form-control" id="intro" name="intro" placeholder="代码说明" rows="5"
                                          style="resize:none;">${code.intro }</textarea>
                            </div>
                            <div class="message-intro">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-5">
                                <button type="submit" class="submitupdate  btn btn-success btn-block">提交</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>

<jsp:include page="include/footer.jsp"></jsp:include>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/resource/bootstrap-fileinput-master/js/locales/zh.js"></script>
</body>
<script type="text/javascript">
    $(this).ready(function () {
        var iscodename = true; var isintro = true;
        $("#intro").blur(function () {
            var value = $(this).val();
            $(".remove-p").remove();
            if (value == null || value == "") {
                isintro = false;
                var p = $("<p class='remove-p text-danger text-left ' style='font-size: 16px; padding-top: 4px;'><span class='glyphicon glyphicon-remove-sign'></span>&nbsp;代码说明不可为空！</p>");
                $(".message-intro").append(p);
            } else {
                isintro = true;
                $(".remove-p").remove();
            }
        });

        $("#codename").blur(function () {
            var value = $(this).val();
            $(".msgcodename").remove();
            if (null == value || value == "") {
                iscodename = false;
                var p = $("<p class='msgcodename text-danger text-left ' style='font-size: 16px; padding-top: 4px;'><span class='glyphicon glyphicon-remove-sign'></span>&nbsp;代码名称不可为空！</p>");
                $(".message-codename").append(p);
            } else {
                iscodename = true;
                $(".msgcodename").remove();
            }
        });

        $(".submitupdate").click(function () {
            if (iscodename && isintro){
                $(this).submit();
            } else {
                alert("正确填写表单后提交。");
                return false;
            }
        });

    });
</script>
</html>
