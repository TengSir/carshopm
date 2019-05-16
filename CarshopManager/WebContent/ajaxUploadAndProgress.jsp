<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.min.js"></script>
<!-- <script type="text/javascript" src="js/jquery.uploadify.js"></script> -->
<!-- <link rel="stylesheet" type="text/css" href="css/uploadify.css"> -->
</head>
<body>
	<!-- <form method="post" action="user/UserAction!upload.action" enctype="multipart/form-data"> -->
				<img  id="imgPrew"  style="width: 100px;height: 80px;border:1px solid red"/>
				<div style="width: 100px;height:4px;border:1px solid lightgray;padding: 0px;margin: 2px">
					<div id="progressBar" style="margin: 0px;background-color: blue;height: 100%;width: 0%;"></div>
				</div><span id="percent"></span>
					<input type="file" name="image" id="image" onchange="uploadThisFile(this.value)" />
	<br />
	<input type="submit" />
	<!-- </form> -->
	<script type="text/javascript">
	function uploadThisFile(x){
		
		var timer;
		//ajax上传文件肯定不能直接在data属性里面声明文件的内容，必须使用一个FormData类来将表单数据封装成一个对象，传入到data属性里
		var formData=new FormData();//创建了一个表单数据对象（可以将文件和文本表单数据一起封装成一个对象）
		formData.append("image",$('#image')[0].files[0]);
		$.ajax({
			type:'post',
			url:'user1/UserAction!upload.action',
			data:formData,
			dataType:'json',
			processData: false,  // 不处理数据
		    contentType: false,   // 不设置内容类型
		    success:function(data){
				$("#imgPrew").attr("src",data.url);
				clearInterval(timer);
				$("#progressBar").css("width","100%")
				$("#percent").text("上传进度：100%");
			},
			beforeSend:function(){
				timer=setInterval(function(){
					$.get("user1/UserAction!loadProgress.action","size="+document.getElementById("image").files[0].size,function(data){
						console.log("上传的百分比："+data.progress);
						$("#progressBar").css("width",data.progress+"%");
						$("#percent").text("上传进度："+data.progress+"%");
					})
				},50);
			}
		});
	}
// 	$(document).ready(function(){
// 		 $('#image').uploadify({
// 			 	'auto'    :  true,
// 			 	'fileObjName':'image',
// 			 	'buttonText' : '选中图片',
// 		        'swf'      : 'images/uploadify.swf',
// 		        'uploader' : 'user/UserAction!upload.action',
// 		        'onUploadSuccess' : function(file, data, response) {
// 			        	$("#imgPrew").attr("src",data);
// 			            //alert('The file ' + file.name + ' was successfully uploaded with a response of ' + response + ':' + data);
// 			        }
// 			    });
// 	})
// 		$(function() {
// 			$('#file_upload').uploadify({
// 				'swf' : 'images/uploadify.swf',
// 				'uploader' : 'user/UserAction!upload.action',
// 				'auto':true,
// 				'progressData':'spped',
// 				'onDialogClose':function(queueData){
// 					alert(queueData.filesSelected);
// 					$('#file_upload').uploadify('upload','*')
// 				}
// 			// Put your options here
// 			});
// 		});
	</script>
</body>
</html>