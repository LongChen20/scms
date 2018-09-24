<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>


    <jsp:include page="include/heder.jsp"/>

    <title>代码管理系统</title>

    <%@include file="include/include.jsp" %>


    <link href="${pageContext.request.contextPath}/resource/bootstrap-3.3.7-dist/css/register.css" rel="stylesheet">


</head>

<body>

<div class="container">

    <form class="fromjson  form-signin" action="${pageContext.request.contextPath}/user/subimtregister" method="post">
        <h2 class="form-signin-heading">用户注册</h2>
        <div id="message">

        </div>
        <label for="username" class="sr-only">用户名</label>
        <input type="text" id="username" name="username" class="form-control" placeholder="用户名" required autofocus>
        <label for="password" class="sr-only">密码</label>
        <input type="password" id="password" name="password" class="form-control" placeholder="密码" required>
        <label for="repassword" class="sr-only">重复密码</label>
        <input type="password" id="repassword" name="repassword" class="form-control" placeholder="重复密码" required>
        <div style="margin-bottom: 20px;">
            <a href="${pageContext.request.contextPath}/home"><span class="glyphicon glyphicon-arrow-left"></span>&nbsp;回首页</a>
            <a href="${pageContext.request.contextPath}/login" class="pull-right">已注册，去登录</a>
        </div>
        <button class="submitRegister  btn btn-lg btn-primary btn-block" type="submit">注册</button>
    </form>
</div>
</body>

<script type="text/javascript"href="${pageContext.request.contextPath}/resource/bootstrap-3.3.7-dist/js/jquery.min.js"></script>
<script type="text/javascript">
    $(this).ready(function () {
        // 关键子都为true才满足提交条件
        var u = false;
        var p = false;
        var r = false;

        // Ajax请求判断用户名是否存在
        $("#username").blur(function () {
            $(".removemessage").remove();

            // 表单数据转JSON串
            var ToJson = {};
            var formArray = $("#username").serializeArray();
            $.each(formArray, function (i, item) {
                ToJson[item.name] = item.value;
            });

            $.ajax({
                //发送的请求路径
                url: "${pageContext.request.contextPath}/user/isusername",
                //亲求方式
                type: "POST",
                //发送的格式字符编码格式
                contentType: "application/json;charset=UTF-8",
                //发送的json串。
                data: JSON.stringify(ToJson),
                //服务器返回的格式。
                dataType: "json",
                //回调函数
                success: function (data) {
                    if (!data.username) {
                        $("#message").append("<p class='removemessage  text-danger text-left' style='font-size: 16px; padding-top: 4px;'><span class='glyphicon glyphicon-info-sign'></span>&nbsp;用户名被占用！</p>");
                    }
                }
            });
        });

        // 判断用户名密码确认密码是否为空或非法字符
        $("#username").blur(function () {
            $(".removemessage").remove();
            var value = $(this).val();
            if (!isnull(value)) {
                $("#message").append("<p class='removemessage  text-danger text-left' style='font-size: 16px; padding-top: 4px;'><span class='glyphicon glyphicon-info-sign'></span>&nbsp;用户名不能为空！</p>");
                u = false;
            }else{
                u = true;
            }
        });

        $("#password").blur(function () {
            $(".removemessage").remove();
            var value = $(this).val();
            if (!isnull(value)) {
                $("#message").append("<p class='removemessage  text-danger text-left' style='font-size: 16px; padding-top: 4px;'><span class='glyphicon glyphicon-info-sign'></span>&nbsp;密码不能为空！</p>");
                p = false;
            }else {
                p = true;
            }
        });

        $("#repassword").blur(function () {
            $(".removemessage").remove();
            var p = $(this).val();
            var s = $("#password").val();
            if (!isnull(s)) {
                $("#message").append("<p class='removemessage  text-danger text-left' style='font-size: 16px; padding-top: 4px;'><span class='glyphicon glyphicon-info-sign'></span>&nbsp;确认密码不能为空！</p>");
                r = false;
            }
            if (p != s) {
                $("#message").append("<p class='removemessage  text-danger text-left' style='font-size: 16px; padding-top: 4px;'><span class='glyphicon glyphicon-info-sign'></span>&nbsp;密码不匹配！</p>");
                r = false;
            }else {
                r = true;
            }
        });

        $(".submitRegister").click(function () {
            if (u && p && r) {
                var formJson = {};
                var formArray = $(".fromjson").serializeArray();
                $.each(formArray, function (i, item) {
                    formJson[item.name] = item.value;
                });
                $.ajax({
                    url: "${pageContext.request.contextPath}/user/register",
                    type: "POST",
                    contentType: "application/json;charset=UTF-8",
                    data: JSON.stringify(formJson),
                });
            } else {
                alert("请正确填写表单");

                return false;
            }
        });


        function isnull(key) {
            if (null == key || "" == key) {
                return false;
            } else {
                return true;
            }
        }
    });
</script>

</html>
