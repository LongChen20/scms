<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="include/include.jsp" %>

    <title>代码管理系统</title>


</head>

<body>


<div class="container-fluid">
    <div class="row">


        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">修改密码</h1>
            <div class="row placeholders">
                <div class="col-md-8">
                    <form class="form-horizontal" action="${pageContext.request.contextPath}/user/changePassword" method="post">
                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label">原密码</label>
                            <div class="col-sm-5">
                                <input type="password" class="form-control" name="password" id="password"
                                       placeholder="原密码">
                            </div>
                            <div class="passwordmsg">
                                <p class="removepass  text-warning text-left"
                                   style="font-size: 16px; padding-top: 4px;"><span
                                        class="glyphicon glyphicon-remove"></span>&nbsp;请输入您原有的密码</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="newpassword" class="col-sm-2 control-label">新密码</label>
                            <div class="col-sm-5">
                                <input type="password" class="form-control" name="newpassword" id="newpassword"
                                       placeholder="新密码">
                            </div>
                            <div class="newpasswordmsg">
                                <p class="removenewpass text-warning text-left"
                                   style="font-size: 16px; padding-top: 4px;"><span
                                        class="glyphicon glyphicon-remove"></span>&nbsp;请输入您要修改的密码</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="repassword" class="col-sm-2 control-label">确认密码</label>
                            <div class="col-sm-5">
                                <input type="password" class="form-control" name="repassword" id="repassword"
                                       placeholder="确认密码">
                            </div>
                            <div class="repasswordmsg">
                                <p class="removerepass  text-warning text-left"
                                   style="font-size: 16px; padding-top: 4px;"><span
                                        class="glyphicon glyphicon-remove"></span>&nbsp;请重复输入您要修改的密码以确认正确</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-5">
                                <button type="submit" class="submitpassword  btn btn-success btn-block">提交修改</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>
</div>

<%@include file="include/footer.jsp" %>


</body>

<SCRIPT type="text/javascript">
    $(this).ready(function () {
        var ispassword = false;
        var isnewpassword = false;
        var isrepassword = false;

        //原始密码
        $("#password").blur(function () {
            var value = $(this).val();
            if (!isnull(value)) {
                $(".removepass").remove();
                $(".passwordmsg").append("<p class='removepass  text-warning text-left' style='font-size: 16px; padding-top: 4px;'><span class='glyphicon glyphicon-remove'></span>&nbsp;请输入您原有的密码！</p>");
                ispassword = false;
            } else {
                $(".removepass").remove();
                $(".passwordmsg").append("<p class='removepass  text-success text-left' style='font-size: 16px; padding-top: 4px;'><span class='glyphicon glyphicon-ok'></span>&nbsp;</p>");
                ispassword = true;
            }
        });


        //失去焦点事件 新密码。
        $("#newpassword").blur(function () {
            var value = $(this).val();
            if (!isnull(value)) {
                $(".removenewpass").remove();
                $(".newpasswordmsg").append("<p class='removenewpass  text-warning text-left' style='font-size: 16px; padding-top: 4px;'><span class='glyphicon glyphicon-remove'></span>&nbsp;请输入您要修改的密码！</p>");
                isnewpassword = false;
            } else {
                $(".removenewpass").remove();
                $(".newpasswordmsg").append("<p class='removenewpass  text-success text-left' style='font-size: 16px; padding-top: 4px;'><span class='glyphicon glyphicon-ok'></span>&nbsp;</p>");
                isnewpassword = true;
            }
        });

        $("#repassword").blur(function () {
            var value = $("#repassword").val();
            var n = $("#newpassword").val();
            if (!isnull(value)) {
                $(".removerepass").remove();
                $(".repasswordmsg").append("<p class='removerepass  text-warning text-left' style='font-size: 16px; padding-top: 4px;'><span class='glyphicon glyphicon-remove'></span>&nbsp;请输入您要修改的密码！</p>");
                isrepassword = false;
            }
            if (value != n) {
                $(".removerepass").remove();
                $(".repasswordmsg").append("<p class='removerepass  text-warning text-left' style='font-size: 16px; padding-top: 4px;'><span class='glyphicon glyphicon-remove'></span>&nbsp;密码不一致！</p>");
                isrepassword = false;
            } else {
                $(".removerepass").remove();
                $(".repasswordmsg").append("<p class='removerepass  text-success text-left' style='font-size: 16px; padding-top: 4px;'><span class='glyphicon glyphicon-ok'></span>&nbsp;</p>");
                isrepassword = true;
            }
        });

        //点击事件判断确认密码和三个关键子。
        $(".submitpassword").click(function () {

            if (ispassword && isnewpassword && isrepassword) {
                $(this).submit();
            } else {
                alert("请正确填写表单！")
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
</SCRIPT>

</html>
