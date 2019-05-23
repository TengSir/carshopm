<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base
	href="${pageContext.request.scheme }://${pageContext.request.serverName}:${pageContext.request.serverPort}/">
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
						<input name="user.username" class="easyui-textbox" prompt="管理账号" value="tengsir"
							iconWidth="28" style="width: 100%; height: 30px; padding: 10px;">
						<b style="color: red;"><s:fielderror fieldName="usernameError"></s:fielderror></b>
					</div>
					<div style="margin-bottom: 20px">
						<input name="user.password" class="easyui-passwordbox" value="123"
							prompt="账号密码" iconWidth="28"
							style="width: 100%; height: 30px; padding: 10px"> <b
							style="color: red;"><s:fielderror fieldName="passwordError"></s:fielderror></b>
					</div>
					<div style="margin-bottom: 20px">
						<input id="kaptchafield" name="kaptchafield" class="easyui-textbox" prompt="验证码"
							iconWidth="28" style="width: 50%; height: 30px; padding: 10px;">
							<img id="code" src="Kaptcha.jpg" style="width: 50%; height: 30px;float:left;"/>
					</div>
					<div style="text-align: center;">
						<a href="javascript:login()"
							class="easyui-linkbutton">&nbsp;&nbsp;登陆&nbsp;&nbsp;</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script>
		function login(){
			$.ajax({
				type:"post",
				url:"user1/UserAction!checkCode.action",
				data:"verifyCode="+$("#kaptchafield").val(),
				success:function(data){
					if(!data.result){
						$.messager.show({
							width:260,
							height:150,
							title:'提示消息',
							msg:'<b style="color:red">验证码输入错误!</b>',
							timeout:3000,
							showType:'slide'
						});
						$("#kaptchafield").textbox("setText","");
						$("#kaptchafield").focus();
					}else{
						$.ajax({
							type:"post",
							url:"user1/UserAction!login.action",
							data:{
								"user.username":$("[name='user.username']").val(),
								"user.password":$("[name='user.password']").val(),
							},
							success:function(data){
								if(data.result=='fail'){
									$("#code").attr("src","Kaptcha.jpg?time="+Math.random())
									$.messager.show({
										width:260,
										height:150,
										title:'提示消息',
										msg:'<b style="color:red">登陆失败，请检查用户名和密码!</b>',
										timeout:3000,
										showType:'slide'
									});
									$("[name='user.username']").textbox("setText","");
									$("[name='user.password']").textbox("setText","");
									
								}else{
									location.href='index.jsp';
								}
							}
						});
					}
				}
			});
		}
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
			$("#code").click(function(){
				$(this).attr("src","Kaptcha.jpg?time="+Math.random())
			})
		})
	</script>
</body>
</html>
