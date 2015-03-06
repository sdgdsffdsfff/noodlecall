<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../global.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>welcome</title>
       
    <link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/common/css/my.css" />
    
	<script src="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/external/jquery-1.8.2.min.js" type="text/javascript"></script>
  	<script src="<%=request.getContextPath()%>/common/js/common.js" type="text/javascript"></script>

    <script type="text/javascript">
    	
    	function callback(trnId, data, other) {
    	}
    	
    	function init() {
    	}
		
	</script>
  </head>

  <body onload="init();" >
	<div class="page-header">
	    <h2>Welcome</h2>
	</div>
	<div class="page-list">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<colgroup>
				<col width="15%" />
				<col width="15%" />
				<col width="85%" />
			</colgroup>					
		    <tr>
		    	<td valign="top">
			    	<ul>
						<li>1、配置
							<ul>
					        	<li><a href="#" onclick="javascript:top.addTab('mainframe_1_1', '服务', '<%=request.getContextPath()%>/view/console/service/service_main.jsp');">服务</a></li>
					      		<li><a href="#" onclick="javascript:top.addTab('mainframe_1_2', '方法', '<%=request.getContextPath()%>/view/console/method/method_main.jsp');">方法</a></li>
					      		<li><a href="#" onclick="javascript:top.addTab('mainframe_1_3', '分组', '<%=request.getContextPath()%>/view/console/group/group_main.jsp');">分组</a></li>
					      		<li><a href="#" onclick="javascript:top.addTab('mainframe_1_4', '客户端', '<%=request.getContextPath()%>/view/console/client/client_main.jsp');">客户端</a></li>
					      		<li><a href="#" onclick="javascript:top.addTab('mainframe_1_5', '服务端', '<%=request.getContextPath()%>/view/console/server/server_main.jsp');">服务端</a></li>
							</ul>
						</li>
				    </ul>
		    	</td>
		    	<td valign="top">
		    		<ul>
						<li>2、监控
							<ul>
								<li>服务
									<ul>
				                        <li><a href="#" onclick="javascript:top.addTab('mainframe_2_1_1', '服务-超时次数', '<%=request.getContextPath()%>/view/monitor/service/overtime/overtime_chart.jsp');">超时次数</a></li>
				                        <li><a href="#" onclick="javascript:top.addTab('mainframe_2_1_2', '服务-平均响应时间', '<%=request.getContextPath()%>/view/monitor/service/avgtime/avgtime_chart.jsp');">平均响应时间</a></li>
										<li><a href="#" onclick="javascript:top.addTab('mainframe_2_1_3', '服务-失败次数', '<%=request.getContextPath()%>/view/monitor/service/success/success_chart.jsp');">错误次数</a></li>
									</ul>
								</li>
								<li>服务端
									<ul>
				                        <li><a href="#" onclick="javascript:top.addTab('mainframe_2_2_1', '服务端-超时次数', '<%=request.getContextPath()%>/view/monitor/server/overtime/overtime_chart.jsp');">超时次数</a></li>
				                        <li><a href="#" onclick="javascript:top.addTab('mainframe_2_2_2', '服务端-平均响应时间', '<%=request.getContextPath()%>/view/monitor/server/avgtime/avgtime_chart.jsp');">平均响应时间</a></li>
										<li><a href="#" onclick="javascript:top.addTab('mainframe_2_2_3', '服务端-失败次数', '<%=request.getContextPath()%>/view/monitor/server/success/success_chart.jsp');">错误次数</a></li>
									</ul>
								</li>
							</ul>
						</li>						
				    </ul>
		    	</td>
		    	<td valign="top">
		    		<%-- <ul>
						<li>3、报警
							<ul>
					        	<li><a href="#" onclick="javascript:top.addTab('mainframe_3_1', '服务报警', '<%=request.getContextPath()%>/view/alarm/service/service_alarm_main.jsp');">服务报警</a></li>
				 	 			<li><a href="#" onclick="javascript:top.addTab('mainframe_3_2', '报警人配置', '<%=request.getContextPath()%>/view/alarm/alarmpeople/alarmpeople_main.jsp');">报警人配置</a></li>
							</ul>
						</li>						
				    </ul> --%>
		    	</td>
			</tr>
		</table>
  	</div>
  </body>
</html>
