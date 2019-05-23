<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>二手车市场管理后台-用户统计页面</title>
<%@include file="resource.txt"%>

</head>
<body>
	<div id="mainsex"
		style="width: 90%; height: 400px; margin: auto; margin-top: 2%; border: 1px solid #eeeeee"></div>
	<script type="text/javascript">
		var myChartsex = echarts.init(document.getElementById('mainsex'));
		// 使用刚指定的配置项和数据显示图表。
		$(document).ready(function() {
			var keys = new Array();
			var result;
			$.ajax({
				type : "post",
				url : "user1/UserAction!getTongjiResultBySex.action",
				dataType : "json",
				success : function(data) {
					result = data.data;
					for ( var x in data.data) {
						keys.push(data.data[x].name)
					}
					console.log(result);
					console.log(keys);
					myChartsex.setOption({
						title : {
							text : '二手车市场用户性别统计图',
							subtext : '数据来源：已注册用户',
							x : 'center'
						},
						tooltip : {
							trigger : 'item',
							formatter : "{a} <br/>{b} : {c} ({d}%)"
						},
						legend : {
							show:true,
							orient : 'vertical',
							left : 'left',
							data : keys
						},
						series : [ {
							name : '性别',
							type : 'pie',
							radius : '55%',
							center : [ '50%', '60%' ],
							data :result,
							itemStyle : {
								emphasis : {
									shadowBlur : 10,
									shadowOffsetX : 0,
									shadowColor : 'rgba(0, 0, 0, 0.5)'
								}
							}
						} ]
					});
				}
			});
		})
	</script>
</body>
</html>