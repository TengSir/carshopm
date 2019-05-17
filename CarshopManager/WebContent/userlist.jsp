<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base
	href="${pageContext.request.scheme }://${pageContext.request.serverName}:${pageContext.request.serverPort}/">
<title>二手车市场管理后台-用户列表页面</title>
<%@include file="resource.txt"%>
</head>
<body>
	<table id="data" class="easyui-datagrid"
		style="width: 100%; height: 100%"
		data-options="fitColumns:true,pageList:[5,10,15,20,30,40],pageSize:10,pagination:true,rownumbers:true,singleSelect:true,url:'user1/UserAction!listUsers.action',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'userid'" style="display:none">用户编号</th>
				<th data-options="field:'image'">用户头像</th>
				<th data-options="field:'nickname'">用户昵称</th>
				<th data-options="field:'username',align:'right'">用户名</th>
				<th data-options="field:'sex',align:'right'">用户性别</th>
				<th data-options="field:'age'">用户年龄</th>
				<th data-options="field:'jialing'">用户驾龄</th>
				<th data-options="field:'tel',align:'center'">用户电话</th>
				<th data-options="field:'job',align:'center'">职业</th>
				<th data-options="field:'email',align:'center'">用户邮箱</th>
				
			</tr>
		</thead>
	</table>
	
	<div id="win"  data-options="closed:true" style="">
		<%@include file="useradd.jsp" %>
	</div>
	
	<div id="mm" class="easyui-menu" style="width:120px;">
	    <div data-options="iconCls:'icon-edit'">编辑</div>
	    <div class="menu-sep"></div>
	    <div data-options="iconCls:'icon-remove'">删除</div>
	    <div class="menu-sep"></div>
	    <div data-options="iconCls:'icon-reload'">刷新</div>
	</div>
	<script>
		
		$(document).ready(function() {
			
			
			$("#add  th").css({"background":"#eeeeee"});
			//数据网格添加右键事件
			$("#data").datagrid({
				onRowContextMenu:function(e, rowIndex, rowData){
					   if(3 == e.which){ 
						   $('#mm').menu('show', {
							    left: window.event.clientX,
							    top:  window.event.clientY
							});
						   $("#data").datagrid("selectRow",rowIndex)
						   e.preventDefault();//组织浏览器本身的右键事件
			          }
				}
			});
			//给右键菜单添加点击事件
			$('#mm').menu({
			    onClick:function(item){
			    	if(item.text=='删除'){
			    		deleteUser();
			    	}else if(item.text=='刷新'){
			    		$("#data").datagrid("reload");
			    	}else if(item.text=='编辑'){
			    		$("#data").datagrid("reload");
			    	}
			    }
			});
			//数据网格添加工具条点击事件,以及一些基本参数设置
			$('#data').datagrid({
				toolbar: [{
					iconCls: 'icon-add',
					handler: function(){
						$('#win').window({
// 							href:'useradd.jsp',
						    width:800,
						    zIndex:2,
						    height:400,
						    modal:true,
						    title:"添加用户",
						    closed:false,
						    iconCls:'icon-man'
						});
					}
				},'-',{
					iconCls: 'icon-edit',
					handler: function(){
						editUser();
					}
				},'-',{
					iconCls: 'icon-save',
					handler: function(){alert('save')}
				},'-',{
					iconCls: 'icon-remove',
					handler: function(){
						deleteUser();
					}
				}],
				striped:true,
				multiSort:true,
				fitColumns:true,
				idField:"userid",
				loadMsg:"正在加载信息，请稍后！",
				checkOnSelect:true,
				sortName:"age",
				autoSizeColumn:true
				
			});
		})
		function editUser(){
			var yourSelect=$("#data").datagrid("getSelected");
			//2.判断选择的行是否为null，
			if(yourSelect==null){
				$.messager.show({
					width:260,
					height:150,
					title:'提示消息',
					msg:'必须要选择一条信息才能编辑!',
					timeout:3000,
					showType:'slide'
				});
			}else{
				$.ajax({
					type:"post",
					url:"user1/UserAction!getUserByUserId.action",
					data:"user.userid="+yourSelect.userid,
					success:function(data){
						$("#username").textbox({value:data.username});
						$("#password").textbox({value:data.password});
						$("#nickname").textbox({value:data.nickname});
						$("#job").tagbox({value:data.job});
						$("#email").textbox({value:data.email});
						$("#tel").textbox({value:data.tel});
						$("#age").spinner({value:data.age});
						$("#jialing").slider({value:data.jialing});
						$("#image").textbox({value:data.image});
						$("#imgPre").attr("src",data.image);
						ue.setContent(data.jianjie);
						$('#win').window({
						    width:800,
						    zIndex:2,
						    height:400,
						    modal:true,
						    title:"编辑用户",
						    closed:false,
						    iconCls:'icon-pencil'
						});
					}
				});
			}
		}
		function deleteUser(){
			//这个匿名函数就是当用户点击datagrid上面当删除按钮时应该执行当业务代码
			//1.通过datagrid的方法获取用户当前选择的行信息
			var yourSelect=$("#data").datagrid("getSelected");
			//2.判断选择的行是否为null，
			if(yourSelect==null){
				$.messager.show({
					width:260,
					height:150,
					title:'提示消息',
					msg:'必须要选择一条信息才能删除!',
					timeout:3000,
					showType:'slide'
				});
			}else{
				$.messager.confirm({
					title:'确认消息',
					msg:'确认要删除这个用户信息吗?',
					ok:"确认",
					cancel:"取消",
					fn:function(r){
						if (r){
							$.ajax({
								type:"post",
								url:"user1/UserAction!deleteUserById.action",
								data:{"user.userid":yourSelect.userid},
								success:function(data){
									$("#data").datagrid("reload");
									if(data.result==true){
										$.messager.show({
											width:260,
											height:150,
											title:'提示消息',
											msg:'删除成功!(3秒自动消失)',
											timeout:3000,
											showType:'slide'
										});
									}else{
										$.messager.show({
											width:260,
											height:150,
											title:'提示消息',
											msg:'删除失败!(3秒自动消失)',
											timeout:3000,
											showType:'slide'
										});
									}
								}
							})
						}
					}
				});
			}
			
		}
// 		$(document).ready(function() {
// 			$("#data").datagrid({
// 				onLoadSuccess : function(data) {
// 					console.log(data);
// 					for(var n in data.rows){
// 						console.log(data.rows[n].sex)
// 						if(data.rows[n].sex==1){
// 							data.rows[n].sex='男';
// 						}else{
// 							data.rows[n].sex='女';
// 						}
// 					}
// 					$('#data').datagrid({
// 						data:data.rows
// 					});
// 					console.log($("#data").datagrid("getData"));
// 					return;
// 				}
// 			})
// 		})
	</script>
</body>
</html>