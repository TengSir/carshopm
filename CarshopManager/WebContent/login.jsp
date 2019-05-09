<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib  uri="/struts-tags" prefix="s" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base href="${pageContext.request.scheme }://${pageContext.request.serverName}:${pageContext.request.serverPort}/CarshopManager/">
<title>二手车市场管理后台-登陆</title>
<%@include file="linkResource.txt"%>
</head>
<body class="easyui-layout">
	<div class="easyui-layout"
		style="width: 100%; height: 100%; border: none">
		<div data-options="region:'north'"
			style="height: 30%; padding-left: 20px;"></div>
		<div data-options="region:'east',split:true"
			style="width: 30%;; border: none"></div>
		<div data-options="region:'west',split:true"
			style="width: 30%;; border: none"></div>
		<div data-options="region:'center'">
			<h2>登陆窗口</h2>
			<div class="easyui-panel"
				style="width: 400px; padding: 50px 60px; margin: auto">
				<form method="post" action="user/UserAction!login.action">
				<div style="margin-bottom: 20px">
					<input name="user.username" class="easyui-textbox" prompt="Username" iconWidth="28"
						style="width: 100%; height: 34px; padding: 10px;">
						<b style="color: red;"><s:fielderror fieldName="usernameError"></s:fielderror></b>
				</div>
				<div style="margin-bottom: 20px">
					<input name="user.password" class="easyui-passwordbox" prompt="Password" iconWidth="28"
						style="width: 100%; height: 34px; padding: 10px">
						
						<b style="color: red;"><s:fielderror fieldName="passwordError"></s:fielderror></b>
				</div>
				<div style="margin-bottom: 20px">
					<a href="javascript:window.document.forms[0].submit()" class="easyui-linkbutton">登陆</a> <a href="#">找回密码</a>
				</div>
				</form>
			</div>

		</div>
	</div>

</body>

</html>