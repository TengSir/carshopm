<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>二手车市场管理后台-用户添加页面</title>
<%-- <%@include file="resource.txt"%> --%>
<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>
<link rel="stylesheet" type="text/css" href="css/easyui.css">
<link rel="stylesheet" type="text/css" href="css/icon.css">
<link rel="stylesheet" type="text/css" href="css/demo.css">
<link rel="stylesheet" type="text/css" href="css/uploadify.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" src="js/jquery.uploadify.js"></script>
<link rel="shortcut icon" type="image/x-icon"
	href="images/browser_logo.gif" />
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8"
	src="ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
	<div id="win" data-options="closed:true">
		<form id="addForm">
			<div id="aa" class="easyui-accordion"
				style="width: 100%; height: 330px">
				<div title="用户基本信息" data-options="iconCls:'icon-pencil'"
					style="overflow: auto; padding: 0px;">
					<table id="add" style="width: 100%; height: 100%;" cellspacing="0">
						<tr>
							<th align="center" valign="middle" rowspan="5" width="10%">
								用<br />户<br />头<br />像
							</th>
							<td align="center" valign="middle" rowspan="5" width="30%">
								<img src="images/default.jpg" id="imgPre"
								style="width: 180px; height: 220px; border: 1px solid gray" />
								<input name="image" id="file_upload" type="file" style="width: 180px;">
<!-- 								<input class="easyui-filebox" name="image" id="file_upload" type="file" -->
<!-- 								data-options="prompt:'选择文件上传',buttonText:'选择'" -->
<!-- 								style="width: 180px;"> -->
							</td>
							<th align="center" valign="middle" width="15%">用户账号:</th>
							<td align="center" valign="middle" width="20%"><input
								class="easyui-textbox" style="width: 200px;" type="text"
								name="name" data-options=""></td>
							<th align="center" valign="middle" width="15%">用户年龄:</th>
							<td align="center" valign="middle" width="20%"><input
								class="easyui-numberspinner" value="20"
								data-options="increment:1,showTip:true,value:20"
								style="width: 200px;"></td>
						</tr>
						<tr>
							<th align="center" valign="middle">账号密码:</th>
							<td align="center" valign="middle"><input
								class="easyui-passwordbox" style="width: 200px;" type="text"
								name="name" data-options=""></td>
							<th align="center" valign="middle">用户职业:</th>
							<td align="center" valign="middle"><input
								class="easyui-tagbox" value="自由职业者" style="width: 200px"></td>
						</tr>
						<tr>
							<th align="center" valign="middle">用户昵称:</th>
							<td align="center" valign="middle"><input
								class="easyui-textbox" style="width: 200px;" type="text"
								name="name" data-options=""></td>
							<th align="center" valign="middle">用户驾龄:</th>
							<td align="center" valign="middle"><input
								class="easyui-slider" style="width: 180px;"
								data-options="showTip:true,value:1,max:40,min:0"></td>
						</tr>
						<tr>
							<th align="center" valign="middle">用户性别:</th>
							<td align="center" valign="middle"><select
								class="easyui-combobox" name="state" style="width: 200px;"
								data-options="editable:false">
									<option value="1">男</option>
									<option value="0">女</option>
							</select></td>
							<th align="center" valign="middle">用户邮箱:</th>
							<td align="center" valign="middle"><input
								class="easyui-textbox" style="width: 200px;" type="text"
								name="name" data-options="validType:'email'"></td>
						</tr>
						<tr>
							<th align="center" valign="middle">用户电话:</th>
							<td align="center" valign="middle"><input
								class="easyui-textbox" style="width: 200px;" type="text"
								name="name" data-options=""></td>
							<th align="center" valign="middle">用户状态:</th>
							<td align="center" valign="middle"><input
								class="easyui-switchbutton"
								data-options="onText:'启用',offText:'禁用'" checked></td>
						</tr>
					</table>

				</div>
				<div title="用户介绍信息" data-options="iconCls:'icon-pencil'"
					style="overflow: hidden; padding: 0px;">
					<script id="editor" type="text/plain"
						style="width:100%;height:220px;"></script>
				</div>
			</div>
			<a href="javascript:void()" class="easyui-linkbutton"
				data-options="iconCls:'icon-ok'"
				onclick="$('#data').datagrid('deleteRow',1)">添加</a> <a
				href="javascript:void()" class="easyui-linkbutton"
				data-options="iconCls:'icon-clear'" onclick="$('#addForm').reset()">重置</a>
			<a href="javascript:void()" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'"
				onclick="$('#win').window({'closed':true})">取消</a>
		</form>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#file_upload').change( function() {
				var formData = new FormData();
				formData.append("image", $('#file_upload')[0].files[0]);
				$.ajax({
					type : 'post',
					url : 'user1/UserAction!upload.action',
					data : formData,
					dataType : 'json',
					processData : false, // 不处理数据
					contentType : false, // 不设置内容类型
					success : function(data) {
						$("#imgPre").attr("src", data.url);
						$.messager.progress('close');
					},
					xhr:function(){
						var xhr=new XMLHttpRequest();
						xhr.upload.onprogress=function(evt){
							$.messager.progress({
								title:'上传进度',
								text:'['+evt.loaded+']/['+evt.total+']'
							}); 
						}
						return xhr;
					}
				});
			})
// 			$('#file_upload').filebox({
// 				onChange : function() {
// 					alert('change');
// 					var formData = new FormData();
// 					formData.append("image", $('#file_upload')[0].files[0]);
// 					$.ajax({
// 						type : 'post',
// 						url : 'user1/UserAction!upload.action',
// 						data : formData,
// 						dataType : 'json',
// 						processData : false, // 不处理数据
// 						contentType : false, // 不设置内容类型
// 						success : function(data) {
// 							$("#imgPre").attr("src", data.url);
// 						}
// 					});
// 				}
// 			})
		})

		//实例化编辑器
		var ue = UE.getEditor('editor');
	</script>
</body>
</html>