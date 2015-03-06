<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../../../global.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>NotifyServer-Child</title>
       
    <link href="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/common/tool/treeview/assets/skins/sam/treeview.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/common/css/my.css" rel="stylesheet" type="text/css" />
    
    <script src="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/external/jquery-1.8.2.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/external/jquery-ui-1.9.1.custom.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/common/tool/treeview/yahoo-dom-event.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/common/tool/treeview/connection-min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/common/tool/treeview/treeview-min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/common/tool/highcharts/js/highcharts.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/common/tool/highcharts/js/modules/exporting.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/common/tool/highcharts/js/themes/mytheme.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/wijmo/jquery.wijmo.wijutil.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/wijmo/jquery.wijmo.wijsuperpanel.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/wijmo/jquery.wijmo.wijexpander.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/common/js/common.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/common/js/tree.js" type="text/javascript"></script>
    
    <style>
		.filterTableTr {
			height: 32px;
		}
	</style>
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
			$('#filterBtn').button().click(function() {
				if(chartInfoMap){
					for(var key in chartInfoMap.getEntry()){
						if ($('#' + key).length > 0) {
							destroyChart({
								'renderTo': key
							});
							chartInfoMap.remove(key);
						}
					}
				}
				treeLeft = new Tree();
				treeLeft.buildTree({
					divId: 'treeDivLeft',
					treeType: 'checkbox-someone',
					rootName: '平均响应时间',
					rootId: 'CONNECT',
					rootOther: 'CONNECT',
					loadNow: false,
					baseUrl: '<%=request.getContextPath()%>/',
					reqUrl: 'monitor/server/tree/queryservicelist',
					highlightFunc: treeHighlightEvent
				});

			});
			
			$('#query').button().click(function() {
				query();
			});
			$('#fullscreen').button().click(function() {
				fullscreen();
			});
			
			chartInfoMap = new Map();
			optionsMap = new Map();
		    intervalProcessMap = new Map();
		    intervalLastTimeMap = new Map();
			
			Highcharts.setOptions({
   	            global: {
   	                useUTC: false
   	            }
   	        });
			
			treeLeft = new Tree();
			treeLeft.buildTree({
				divId: 'treeDivLeft',
				treeType: 'checkbox-someone',
				rootName: '平均响应时间',
				rootId: 'CONNECT',
				rootOther: 'CONNECT',
				loadNow: false,
				baseUrl: '<%=request.getContextPath()%>/',
				reqUrl: 'monitor/server/tree/queryservicelist',
				highlightFunc: treeHighlightEvent
			});
			
			var box = document.getElementById('box');
			var left = document.getElementById('left');
			var right = document.getElementById('right');
			var line = document.getElementById('line');
			var treeDiv = document.getElementById('treeDiv');
			var filterDiv = document.getElementById("filterDiv");

			line.onmousedown = function(e) {
				
				var disX = (e || event).clientX;
				line.left = line.offsetLeft;
				
				document.onmousemove = function(e) {  
					
					iT = line.left + ((e || event).clientX - disX);
		            var e=e||window.event,tarnameb=e.target||e.srcElement;
					var maxT = box.clientWidth - 50;
					line.style.margin = 0;
					iT < 50 && (iT = 50);
					iT > maxT && (iT = maxT);
					line.style.left = left.style.width = iT + 'px';
					treeDiv.style.width = iT - 8 + 'px';
					filterDiv.style.width = iT - 7 + 'px';
					right.style.width = box.clientWidth - iT - 3 + 'px';
					return false
				};	

				document.onmouseup = function() {
					document.onmousemove = null;
					document.onmouseup = null;	
					line.releaseCapture && line.releaseCapture()
				};

				line.setCapture && line.setCapture();

				return false
			};

			
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
			$('#treeDiv').height($(window).height() - 110);
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
		
		function treeHighlightEvent(data, label, node) {
			
			var pppppid = node.parent.parent.parent.parent.parent.data.id;			
			var ppppid = node.parent.parent.parent.parent.data.id;
			var pppid = node.parent.parent.data.pid;
			var ppid = node.parent.data.pid;
			var pid = data.pid;
			var id = data.id;
			
			var divId = pppppid.replace(/\W/g, '') + '_' + 
						ppppid.replace(/\W/g, '') + '_' + 
						pppid.replace(/\W/g, '') + '_' + 
						ppid.replace(/\W/g, '') + '_' + 
						pid.replace(/\W/g, '') + '_' + 
						id.replace(/\W/g, '');
			var title = node.parent.parent.parent.label.substring(0, node.parent.parent.parent.label.indexOf('(')) + '-' + 
						node.parent.parent.label + '-' + 
						node.parent.label + '-' + 
						label;
			
			if (node.highlightState == 1) {
				if ($('#' + divId).length == 0) {			
					buildChart({
						containerId: 'chart',
						renderTo: divId,
						title: title,
						input: {
							monitorType: pppppid,
							themeName: pppid,
							selfModuleType: node.parent.data.other,
							selfModuleId: pid,
							moduleType: data.other,
							moduleId: id
						}
					});
					chartInfoMap.put(divId, {
						containerId: 'chart',
						renderTo: divId,
						title: title,
						input: {
							monitorType: pppppid,
							themeName: pppid,
							selfModuleType: node.parent.data.other,
							selfModuleId: pid,
							moduleType: data.other,
							moduleId: id
						}
					});
				}					
			} else {
				if ($('#' + divId).length > 0) {
					destroyChart({
						renderTo: divId
					});
					chartInfoMap.remove(divId);
				}
			}
		}
		
		function buildChart(obj) {
			
			$('#' + obj.containerId).append('<div id="' 
					+ obj.renderTo 
					+ '" style="min-width:400px; width:900px; height:150px; margin:0 auto; margin-top:5px; margin-bottom:6px;"></div>');
			
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
	                y: 45,
	                borderWidth: 1
	            },
	            tooltip: {
	                crosshairs: true,
	                shared: true
	            },
	            plotOptions: {
	            	spline: {
	            		lineWidth: 1,
	            		states: {
	                        hover: {
	                            lineWidth: 1
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
	                name: 'AvgTm',
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
		
		function fullscreen() {
			
			openChildWindowPost(
					'<%=request.getContextPath()%>/view/monitor/server/avgtime/avgtime_chart_fullscreen.jsp', 
					'urlParam',
					jsonToString(chartInfoMap.getEntry())
			);
		}
		
		var iT;
		function resize() {
			
			if (!Sys.ie) {
				
				var box = document.getElementById('box');
				var left = document.getElementById('left');
				var right = document.getElementById('right');
				var line = document.getElementById('line');
				var treeDiv = document.getElementById('treeDiv');
				var filterDiv = document.getElementById("filterDiv");
				
				line.style.left = left.style.width = iT + 'px';
				treeDiv.style.width = iT - 8 + 'px';
				filterDiv.style.width = iT - 7 + 'px';
				right.style.width = box.clientWidth - iT - 3 + 'px';
				
				window.onresize = resize;
			}
		}
		
	</script>

  </head>

  <body onload="init();" onkeydown="onEnterDown(query);" onresize="resize();">
	
	<div id="box">
	    <div id="right" style="width:80%;float:right;">
			<div id="button">
				<button id="query">查询</button>
				<button id="fullscreen">全屏</button>
			</div>
		
			<div id="forms">
			  <table width="100%" border="1" cellspacing="0" cellpadding="0" class="s_layout">
					<colgroup>
						<col width="10%" />
						<col width="15%" />
						<col width="10%" />
						<col width="65%" />
					</colgroup>					
					<tr class="filterTableTr">
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
				<div id="chart" style="height:350px; width:100%; overflow:auto; border: 1px solid #e3e3e3; background:#ffffff;"></div>
			</div>
		</div>
	    <div id="left" style="width:20%;">
	    	<button id="filterBtn">刷新</button>
			
			<div id="filterDiv" style="width:97%;">
				<div id="form" style="width: auto;">
					<table width="100%" border="1" cellspacing="0" cellpadding="0" class="s_layout">
						<colgroup>
							<col width="40%" />
							<col width="60%" />
						</colgroup>					
						<tr class="filterTableTr">
					    	<th><label>服务.方法</label></th>
					    	<td><input type="text" id="queryInfo" /></td>
					    </tr>
					</table>
				</div>
			</div>
			<div id="tree">
				<div id="treeDiv" style="background:#ffffff; border: 1px solid #e3e3e3; height:350px; width:96%; overflow:auto; color:#222222;">
					<div id="treeDivLeft" class="whitebg ygtv-checkbox"></div>
				</div>
			</div>
		</div>
		<div id="line" style="position:absolute;top:0;left:19.7%;height:100%;width:8px;overflow:hidden;cursor:w-resize;"></div>
		
	</div>

  </body>
</html>
