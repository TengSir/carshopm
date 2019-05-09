<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base href="${pageContext.request.scheme }://${pageContext.request.serverName}:${pageContext.request.serverPort}/CarshopManager/">
<title>二手车市场管理后台-主页</title>
<%@include file="linkResource.txt"%>
</head>
<body class="easyui-layout" style="padding: 10px">
	<div class="easyui-layout" style="width: 100%; height: 100%;">
		<div data-options="region:'north'"
			style="height: 80px; padding-left: 20px;">

			<h1 style="text-shadow: 0px 0px 5px lightskyblue">二手车市场管理后台</h1>


		</div>
		<div data-options="region:'south',split:true" style="height: 50px;">
			<A href="#">网站前台</A>
		</div>
		<div data-options="region:'east',split:true" title="通知区域"
			style="width: 100px;"></div>
		<div data-options="region:'west',split:true" title="系统菜单"
			style="width: 160px;">
			<ul class="easyui-tree" id="sysmenu">
				<li  data-options="state:'closed'"><span>用户管理</span>
					<ul>
						<li><span>用户列表</span></li>
						<li><span>用户添加</span></li>
						<li><span>用户统计</span></li>
					</ul></li>
			</ul>
			<ul class="easyui-tree" id="sysmenu">
				<li  data-options="state:'closed'"><span>汽车管理</span>
					<ul>
						<li><span>汽车列表</span></li>
						<li><span>汽车添加</span></li>
						<li><span>汽车推广</span></li>
					</ul></li>
			</ul>
		</div>
		<div data-options="region:'center',">这是网站首页打开中间区域</div>
	</div>

	<script type="text/javascript">
		$('#sysmenu').tree({
			onClick : function(node) {
				if (node.text == '用户添加') {

				} else {

				}
				alert(node.text); // alert node text property when clicked
			}
		});
	</script>
</body>
</html>