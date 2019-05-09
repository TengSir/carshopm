<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base
	href="${pageContext.request.scheme }://${pageContext.request.serverName}:${pageContext.request.serverPort}/CarshopManager/">
<title>二手车市场管理后台-登陆</title>
<%@include file="resource.txt"%>
</head>
<body>
	<div id="login"
		style="width: 400px; height: 300px; position: absolute;">
		<div data-options="region:'center'" style="text-align: center">
			<h1
				style="font-size: 1cm; color: white; text-shadow: 0px 0px 2px black">用户登陆</h1>
			<div class="easyui-panel"
				style="width: 400px; padding: 50px 60px; margin: auto; box-shadow: 0px 0px 5px black">
				<form method="post" action="user/UserAction!login.action">
					<div style="margin-bottom: 20px">
						<input name="user.username" class="easyui-textbox" prompt="管理账号"
							iconWidth="28" style="width: 100%; height: 30px; padding: 10px;">
						<b style="color: red;"><s:fielderror fieldName="usernameError"></s:fielderror></b>
					</div>
					<div style="margin-bottom: 20px">
						<input name="user.password" class="easyui-passwordbox"
							prompt="账号密码" iconWidth="28"
							style="width: 100%; height: 30px; padding: 10px"> <b
							style="color: red;"><s:fielderror fieldName="passwordError"></s:fielderror></b>
					</div>
					<div style="margin-bottom: 20px">
						<input name="kaptchafield" class="easyui-textbox" prompt="验证码"
							iconWidth="28" style="width: 50%; height: 30px; padding: 10px;">
							<img src="Kaptcha.jpg" style="width: 50%; height: 30px;float:left;"/>
					</div>
					<div style="text-align: center;">
						<a href="javascript:window.document.forms[0].submit()"
							class="easyui-linkbutton">&nbsp;&nbsp;登陆&nbsp;&nbsp;</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script>
		function changeLocation() {
			var x = ($(window).width() - 400) / 2;
			var y = ($(window).height() - 400) / 2;
			$("#login").css({
				"left" : (x + "px"),
				"top" : (y + "px")
			});
		}
		$(document).ready(function() {
			changeLocation();
			$(window).resize(function() {
				changeLocation();
			});
		})
	</script>
</body>
</html>
