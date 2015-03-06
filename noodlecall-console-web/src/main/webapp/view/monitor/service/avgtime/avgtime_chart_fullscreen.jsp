<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../../../global.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>NotifyServer-Child</title>
       
    <link href="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/common/css/my.css" rel="stylesheet" type="text/css" />
     
    <script src="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/external/jquery-1.8.2.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/external/jquery-ui-1.9.1.custom.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/common/tool/highcharts/js/highcharts.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/common/tool/highcharts/js/modules/exporting.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/wijmo/jquery.wijmo.wijutil.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/wijmo/jquery.wijmo.wijsuperpanel.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/wijmo/jquery.wijmo.wijexpander.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/common/tool/highcharts/js/themes/mytheme.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/common/js/common.js" type="text/javascript"></script>

    <script type="text/javascript">
    
	    var regionCurrent = 60;
	    var intervalStart = false;
	    var chartInfoMap;
	    var optionsMap;
	    var intervalProcessMap;
	    var intervalLastTimeMap;
	
		function callback(trnId, data, other) {
			
			if (trnId == "CHART_QUERY") {
				
				var options = optionsMap.get(other);
				
				if (options == null) {
					return;
				}
				
				var averageTimeData = [];
				
				var i;
				
				for (i=0; i<data.length; i++) {
					averageTimeData.push([data[i].timestamp, data[i].averageTime]);
				}
				
				if (data.length > 0) {
					intervalLastTimeMap.put(other, data[i-1].timestamp);
				}
	
			 	options.series[0].data = averageTimeData;
		    
		        new Highcharts.Chart(options);
		         	
			} else if (trnId == "GET_DATETIME") {
				intervalLastTimeMap.put(other, data.timestamp);
			}
		}

		function init() {
			
			$('#query').button().click(function() {
				query();
			});
			
			chartInfoMap = new Map();
			chartInfoMap.setEntry(<%=request.getParameter("urlParam")%>);
			optionsMap = new Map();
		    intervalProcessMap = new Map();
		    intervalLastTimeMap = new Map();
			
			Highcharts.setOptions({
   	            global: {
   	                useUTC: false
   	            }
   	        });
			
			$('#catch_Tm_Start').datepicker({
	            dateFormat: 'yy-mm-dd'
	        });
			$('#catch_Tm_End').datepicker({
	            dateFormat: 'yy-mm-dd'
	        });
			for (var i=0; i<24; i++) {
				var time = i;
				if (time < 10) {
					time = '0' + i;
				}
				$('#catch_Tm_Hour_Start').append('<option value="' + time + '">' + time + '</option>');
			}
			for (var i=0; i<60; i++) {	
				var time = i;
				if (time < 10) {
					time = '0' + i;
				}
				$('#catch_Tm_Minute_Start').append('<option value="' + time + '">' + time + '</option>');
			}
			for (var i=0; i<24; i++) {
				var time = i;
				if (time < 10) {			
					time = '0' + i;
				}
				$('#catch_Tm_Hour_End').append('<option value="' + time + '">' + time + '</option>');
			}
			for (var i=0; i<60; i++) {
				var time = i;
				if (time < 10) {			
					time = '0' + i;
				}
				$('#catch_Tm_Minute_End').append('<option value="' + time + '">' + time + '</option>');
			}
			
			$('#region').append('<option value="">--select--</option>')
			$('#region').append('<option value="10">10分钟</option>');
			$('#region').append('<option value="30">30分钟</option>');
			$('#region').append('<option value="60">1小时</option>');
			$('#region').append('<option value="360">6小时</option>');
			$('#region').append('<option value="720">12小时</option>');
			$('#region').append('<option value="1440">1天</option>');
			$('#region').append('<option value="10080">1周</option>');
			$('#region').append('<option value="43200">1个月(30天)</option>');
						
			if (Sys.ie) {
				
				$('form input[id^="catch_Tm_"]').attr('style', 'width:24%;');
				$('form select[id^="catch_Tm_"]').attr('style', 'width:10%;');
				
			} else if (Sys.chrome) {

				$('form input[id^="catch_Tm_"]').attr('style', 'width:24.3%;');
				$('form select[id^="catch_Tm_"]').attr('style', 'width:9%;');
			}
			
			$('#chart').height($(window).height() - 110);
			
			
			var keys = chartInfoMap.getKeys();
			for(var i=0; i<keys.length; i++) {
				var chartInfo = chartInfoMap.get(keys[i]);
				buildChart(chartInfo);
			}
		}
		
		function query() {
			
			var keys = chartInfoMap.getKeys();
			for(var i=0; i<keys.length; i++) {
				var chartInfo = chartInfoMap.get(keys[i]);
				cleanChart(chartInfo);
				queryChart(chartInfo);
			}
		}
		
		function timeQuery () {
			
			if ($('#region').val() == '') {
				if ($('#catch_Tm_Start').val() != ''
					&& $('#catch_Tm_End').val() != '') {
					query();
				}
			}
		}
		
		function buildChart(obj) {
			
			$('#' + obj.containerId).append('<div id="' 
					+ obj.renderTo 
					+ '" style="min-width:400px; width:94%; height:180px; margin:0 auto; margin-top:5px; margin-bottom:8px;"></div>');
			
			transaction({
				id: 'GET_DATETIME',
				url: '<%=request.getContextPath()%>/monitor/chart/getdatetime',
				async: false,
				other: obj.renderTo
			});
			
			options = {
	            chart: {
	                renderTo: obj.renderTo,
	                type: 'spline',
	                events: {
	                    load: function() {
                        	var options = this;
                        	if (intervalStart == true) {
                        		var intervalProcess = setInterval(function() {

                        			var intervalProcessNow = intervalProcessMap.get(obj.renderTo);
                        			if (intervalProcessNow != intervalProcess) {
                        				clearInterval(intervalProcess);
                        				return;
                        			}
    	    						
    	                			var jsonSet = new JsonSet();
    	                			jsonSet.put('input', obj.input);
    	                			jsonSet.put('intervalLastTime', intervalLastTimeMap.get(obj.renderTo));
    	                						
    	                        	$.ajax({

    	                        		url: '<%=request.getContextPath()%>/monitor/chart/querychartsinglenowlast',
    	                        		data: jsonSet.toString(),
    	                        		cache: false,
    	                        		dataType: 'json',
    	                        				
    	                        		success: function(data, textStatus, jqXHR) {
    	                        			var i;
    	                        			for (i=0; i<data.length; i++) {

    	                        				var x = data[i].timestamp;
    	                                        var y = data[i].averageTime;
    	                                        options.series[0].addPoint([x, y], true, true);
    	                        			}

    	                    				if (data.length > 0) {
    	                    					intervalLastTimeMap.put(obj.renderTo, data[i-1].timestamp);
    	                        			}
    	                        		}
    	                        	});
    	                			
    	                		}, 60000);
                            	
                            	intervalProcessMap.put(obj.renderTo, intervalProcess);
                        	}
	                    }
	                }
	            },
	            title: {
	                text: obj.title
	            },
	            subtitle: {
	                text: 'AvgTime'
	            },
	            xAxis: {
	            	type: 'datetime',
	            	tickPixelInterval: 100
	            },
	            yAxis: {
	                title: {
	                    text: 'Avg Time'
	                },
	                min: 0
	            },
	            legend: {
	                layout: 'vertical',
	                align: 'right',
	                verticalAlign: 'top',
	                x: 0,
	                y: 58,
	                borderWidth: 1
	            },
	            tooltip: {
	                crosshairs: true,
	                shared: true
	            },
	            plotOptions: {
	            	spline: {
	            		lineWidth: 2,
	            		states: {
	                        hover: {
	                            lineWidth: 2
	                        }
	                    },
	                    shadow: false,
	                	dataLabels: {
	                        enabled: false
	                    },
	                    marker: {
	                    	enabled: false,
	                    	states: {
	                            hover: {
	                                enabled: true,
	                                radius: 4
	                            }
	                        }
	                    }
	                }
	            },
	            series: [{
	                name: 'AgTm',
	                marker: {
	                    symbol: 'square'
	                }
	            }]
	        };
			
			optionsMap.put(obj.renderTo, options);
			
			queryChart(obj);
		}
		
		function queryChart(obj) {
			
			var url = '';
			var jsonSet = new JsonSet();
			
			if ($('#region').val() != '') {
				regionCurrent = $('#region').val();
				jsonSet.put('region', regionCurrent);
				intervalStart = true;
				url = '<%=request.getContextPath()%>/monitor/chart/querychartsinglenow';
			} else {
				if ($('#catch_Tm_Start').val() != ''
						&& $('#catch_Tm_End').val() != '') {
					var catch_Tm_Start = 
							$('#catch_Tm_Start').val() + ' ' +
							$('#catch_Tm_Hour_Start').val() + ':' +
							$('#catch_Tm_Minute_Start').val() + ':' +
							'00';
					jsonSet.put('beginTime', catch_Tm_Start);
					var catch_Tm_End = 
							$('#catch_Tm_End').val() + ' ' +
							$('#catch_Tm_Hour_End').val() + ':' + 
							$('#catch_Tm_Minute_End').val() + ':' +
							'00';
					jsonSet.put('endTime', catch_Tm_End);
					intervalStart = false;
					url = '<%=request.getContextPath()%>/monitor/chart/querychartbganded';
				} else {
					regionCurrent = 60;
					jsonSet.put('region', regionCurrent);
					intervalStart = true;
					url = '<%=request.getContextPath()%>/monitor/chart/querychartsinglenow';
				}
			}
			
			jsonSet.put('input', obj.input);
			
			transaction({
				id: 'CHART_QUERY',
				url: url,
				jsonSet: jsonSet,
				other: obj.renderTo
			});
		}
		
		function destroyChart(obj) {
			
			cleanChart(obj);
			
			intervalLastTimeMap.remove(obj.renderTo);
			optionsMap.remove(obj.renderTo);
			$('#' + obj.renderTo).remove();
		}
		
		function cleanChart(obj) {
			
			var intervalProcess = intervalProcessMap.get(obj.renderTo);
			if (intervalProcess != null) {
				clearInterval(intervalProcess);
				intervalProcessMap.remove(obj.renderTo);
			}
		}
		
	</script>

  </head>

  <body onload="init();" onkeydown="onEnterDown(query);" >
	
	<div id="box">
	    <div id="right" style="width:100%;">
			<div id="button">
				<button id="query">查询</button>
			</div>
		
			<div id="forms">
			  <table width="100%" border="1" cellspacing="0" cellpadding="0" class="s_layout">
					<colgroup>
						<col width="10%" />
						<col width="15%" />
						<col width="10%" />
						<col width="65%" />
					</colgroup>					
					<tr>
				    	<th><label>范围</label></th>
				    	<td><select name="region" id="region" onchange="javascript:query();"></select></td>
				    	<th><label>获取时间</label></th>
				    	<td style="color:#fafafa;">
				    		<input type="text" id="catch_Tm_Start" style="width:24%;" onchange="javascript:timeQuery();"/>
				    		<select name="catch_Tm_Hour_Start" id="catch_Tm_Hour_Start" style="width:10%;" onchange="javascript:timeQuery();"></select>
				    		<select name="catch_Tm_Minute_Start" id="catch_Tm_Minute_Start" style="width:10%;" onchange="javascript:timeQuery();"></select>
				    		~
				    		<input type="text" id="catch_Tm_End" style="width:24%;" onchange="javascript:timeQuery();"/>
				    		<select name="catch_Tm_Hour_End" id="catch_Tm_Hour_End" style="width:10%;" onchange="javascript:timeQuery();"></select>
				    		<select name="catch_Tm_Minute_End" id="catch_Tm_Minute_End" style="width:10%;" onchange="javascript:timeQuery();"></select>
				    	</td>
					</tr>
				</table>
			</div>
		
			<div>
				<div id="chart" style="height:100%; width:100%; overflow:auto; border: 1px solid #e3e3e3; background:#ffffff;"></div>
			</div>
		</div>
	</div>

  </body>
</html>
