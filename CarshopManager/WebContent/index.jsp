<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base
	href="${pageContext.request.scheme }://${pageContext.request.serverName}:${pageContext.request.serverPort}/">
<title>二手车市场后台管理系统-主页</title>
<%@include file="resource.txt"%>

</head>
<body class="easyui-layout" style="padding: 10px">
	<div class="easyui-layout" style="width: 100%; height: 100%;">
		<div data-options="region:'north'"
			style="height: 80px; padding-left: 20px;">
			<img src="images/browser_logo.png"
				style="width: 60px; height: 60px; float: left; margin: 10px;" />
			<h1
				style="text-shadow: 0px 0px 5px lightskyblue; display: inline-block;">二手车市场管理后台</h1>
				<a
				style="float: right; display: inline-block; margin-top: 35px; margin-right: 20px"
				href="javascript:logoff('user/UserAction!logoff.action')">安全退出</a>
			<span
				style="float: right; display: inline-block; margin-top: 20px; margin-right: 20px">欢迎您：<s:property
					value="#session.user.username" />&nbsp;&nbsp;<img
				src='<s:property value="#session.user.image" />'
				style="width: 30px; height: 30px; border: 1px solid gray; border-radius: 15px; box-shadow: 0px 0px 1px lightskyblue; position: relative; top: 10px" /></span>
			

		</div>
		<div data-options="region:'south',split:true" style="height: 50px;">
			<A href="#">网站前台</A>
		</div>
		<div data-options="region:'east',split:true" title="通知区域"
			style="width: 100px;"></div>
		<div data-options="region:'west',split:true" title="系统菜单"
			style="width: 160px;">
			<ul class="easyui-tree" id="sysmenu">
				<li data-options="state:'open'"><span>用户管理</span>
					<ul>
						<li><span>用户列表</span></li>
						<li><span>用户添加</span></li>
						<li><span>统计分析</span>
							<ul>
								<li><span>年龄统计</span></li>
								<li><span>性别统计</span></li>
								<li><span>职业统计</span></li>
							</ul></li>
					</ul></li>
			</ul>
			<ul class="easyui-tree" id="sysmenu">
				<li data-options="state:'closed'"><span>汽车管理</span>
					<ul>
						<li><span>汽车列表</span></li>
						<li><span>汽车添加</span></li>
						<li><span>汽车推广</span></li>
					</ul></li>
			</ul>
		</div>
		<div data-options="region:'center',">
			<div id="tt" class="easyui-tabs" data-options="tools:'#tab-tools'"
				style="width: 100%; height: 100%"></div>

		</div>
	</div>

	<script type="text/javascript">
		$('#sysmenu').tree({
			onClick : function(node) {
				if ($('#tt').tabs('exists', node.text)) {
					$('#tt').tabs('select', node.text);
				} else {
					if (node.text == '用户列表') {
						$('#tt').tabs('add', {
							title : node.text,
							href : 'userlist.jsp',
							// 						content: '<div style="padding:10px">Content这是用户列表的tab</div>',
							closable : true
						});
					} else if (node.text == '年龄统计') {
						$('#tt').tabs('add', {
							title : node.text,
							href : 'usertj.jsp',
							// 						content: '<div style="padding:10px">Content这是用户列表的tab</div>',
							closable : true
						});
					} else if (node.text == '性别统计') {
						$('#tt').tabs('add', {
							title : node.text,
							href : 'usertjsex.jsp',
							// 						content: '<div style="padding:10px">Content这是用户列表的tab</div>',
							closable : true
						});
					} else if (node.text == '职业统计') {
						$('#tt').tabs('add', {
							title : node.text,
							href : 'usertjjob.jsp',
							// 						content: '<div style="padding:10px">Content这是用户列表的tab</div>',
							closable : true
						});
					}
				}
			}
		});
		
		function logoff(url){
			$.messager.confirm({
				title:'确认消息',
				msg:'确认要退出登陆吗?',
				ok:"确认",
				cancel:"取消",
				fn:function(r){
					if (r){
						location.href=url;
					}
				}
			});
		}
	</script>
</body>
</html>