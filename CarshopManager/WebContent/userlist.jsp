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
				<th data-options="field:'tel',align:'center'">用户电话</th>
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog" title="消息提示" 
		style="width: 260px; height: 160px; padding: 10px"
		data-options="
				iconCls: 'icon-delete',
				toolbar: '#dlg-toolbar',
				buttons: '#dlg-buttons',
				resizable:false,
				modal:true,
				closed:true
			">
		必须选中一条才能删除！</div>
	<div id="dlg-toolbar" style="padding: 2px 0"></div>
	<div id="dlg-buttons">
		 <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:$('#dlg').dialog('close')">关闭</a>
	</div>
	<div id="dlg1" class="easyui-dialog" title="确认操作" 
		style="width: 260px; height: 160px; padding: 10px"
		data-options="
				iconCls: 'icon-delete',
				toolbar: '#dlg-toolbar1',
				buttons: '#dlg-buttons1',
				resizable:false,
				modal:true,
				closed:true
			">
		您确认要删除这条用户信息吗？</div>
	<div id="dlg-toolbar1" style="padding: 2px 0"></div>
	<div id="dlg-buttons1">
		<a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:confirmDelete()">确认</a>
		 <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:$('#dlg1').dialog('close')">关闭</a>
	</div>
	



	<script>
		function confirmDelete(){
			var yourSelect=$("#data").datagrid("getSelected");
			var willdeleteUserid=yourSelect.userid;
			
			$.ajax({
				type:"post",
				url:"user1/UserAction!deleteUserById.action",
				data:{"user.userid":willdeleteUserid},
				success:function(data){
					alert(data.result);
				}
			})
		}
		
		$(document).ready(function() {
			//这个相当于给数据网格组件添加但行选中事件
// 			$('#data').datagrid({
// 				onSelect: function(rowIndex,rowData){
// 					alert(rowIndex)
// 					alert(rowData.nickname)
// 				}
// 			});
			
			$('#data').datagrid({
				toolbar: [{
					iconCls: 'icon-add',
					handler: function(){alert('edit')}
				},'-',{
					iconCls: 'icon-edit',
					handler: function(){alert('edit')}
				},'-',{
					iconCls: 'icon-save',
					handler: function(){alert('edit')}
				},'-',{
					iconCls: 'icon-remove',
					handler: function(){
						//这个匿名函数就是当用户点击datagrid上面当删除按钮时应该执行当业务代码
						//1.通过datagrid的方法获取用户当前选择的行信息
						var yourSelect=$("#data").datagrid("getSelected");
						//2.判断选择的行是否为null，
						if(yourSelect==null){
							$('#dlg').dialog('open');
						}else{
							$('#dlg1').dialog('open');
						}
						
						
					}
				}],
				striped:true,
				fitColumns:true,
				idField:"userid",
				loadMsg:"正在加载信息，请稍后！",
				checkOnSelect:true,
				sortName:"age",
				autoSizeColumn:true
				
			});
		})
		$(document).ready(function() {
			$("#data").datagrid({
				onLoadSuccess : function(data) {
					console.log(data);
					for(var n in data.rows){
						console.log(data.rows[n].sex)
						if(data.rows[n].sex==1){
							
							data.rows[n].sex='男';
						}else{
							data.rows[n].sex='女';
						}
					}
				}
			})
		})
	</script>
</body>
</html>