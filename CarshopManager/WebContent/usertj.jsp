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
	<div id="main"
		style="width: 90%; height: 400px; margin: auto; margin-top: 2%; border: 1px solid #eeeeee"></div>
	<script type="text/javascript">
		var myChart = echarts.init(document.getElementById('main'));
		var option = {
			title : {
				text : '二手车市场用户年龄统计图'
			},
			tooltip : {},
			legend : {
				data : [ '年龄' ]
			},
			xAxis : {
				data : []
			},
			yAxis : {},
			series : [ {
				name : '人数',
				type : 'bar',
				data : []
			} ]
		};

		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
		$(document).ready(function() {
			var keys = new Array();
			var values = new Array();
			var result;
			$.ajax({
				type : "post",
				url : "user1/UserAction!getTongjiResultByAge.action",
				dataType : "json",
				success : function(data) {
					result = data.data;
					for ( var x in data.data) {
						keys.push(data.data[x].name + " ")
						values.push(data.data[x].value)
					}
					console.log(result);
					console.log(keys);
					console.log(values);
					myChart.setOption({
						title : {
							text : '二手车市场用户年龄统计图',
							subtext: '数据来源：已注册用户',
					        x:'center'
						},
						tooltip : {},
						legend : {
							data : [ '人数' ]
						},
						xAxis : {
							data : keys
						},
						yAxis : {},
						series : [ {
							name : '数量',
							type : 'bar',
							data : values
						} ]
					});
				}
			});
		})
	</script>
</body>
</html>