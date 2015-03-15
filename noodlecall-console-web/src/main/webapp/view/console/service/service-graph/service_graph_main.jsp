<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../../../global.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>NotifyServer-Child</title>
       
    <link href="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/common/tool/jsplumb/css/jsplumb.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/common/css/my.css" rel="stylesheet" type="text/css" />
    
    <script src="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/external/jquery-1.8.2.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/external/jquery-ui-1.9.1.custom.min.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/common/tool/jsplumb/js/dom.jsPlumb-1.7.2-min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/common/js/common.js" type="text/javascript"></script>
	
	<style type='text/css'>
		
		.flowchart .window-client { border:1px solid #346789;
			box-shadow: 2px 2px 19px #aaa;
			   -o-box-shadow: 2px 2px 19px #aaa;
			   -webkit-box-shadow: 2px 2px 19px #aaa;
			   -moz-box-shadow: 2px 2px 19px #aaa;
			-moz-border-radius:0.5em;
			border-radius:0.5em;
			opacity:0.8;
			filter:alpha(opacity=80);
			
			text-align:center;
			z-index:20; position:absolute;
			background-color:#eeeeef;
			color:black;
			font-family:helvetica;padding:0.5em;
			font-size:0.9em;
			
			padding-top:0.8em;
			line-height:1.3em;
			height:3.8em;
			width:18em;
		}
		
		.flowchart .window-server { border:1px solid #346789;
			box-shadow: 2px 2px 19px #aaa;
			   -o-box-shadow: 2px 2px 19px #aaa;
			   -webkit-box-shadow: 2px 2px 19px #aaa;
			   -moz-box-shadow: 2px 2px 19px #aaa;
			-moz-border-radius:0.5em;
			border-radius:0.5em;
			opacity:0.8;
			filter:alpha(opacity=80);
			
			text-align:center;
			z-index:20; position:absolute;
			background-color:#eeeeef;
			color:black;
			font-family:helvetica;padding:0.5em;
			font-size:0.9em;
			
			padding-top:0.8em;
			line-height:1.3em;
			height:3.8em;
			width:30em;
		}
		
		.flowchart .window-service { border:1px solid #346789;
			box-shadow: 2px 2px 19px #aaa;
			   -o-box-shadow: 2px 2px 19px #aaa;
			   -webkit-box-shadow: 2px 2px 19px #aaa;
			   -moz-box-shadow: 2px 2px 19px #aaa;
			-moz-border-radius:0.5em;
			border-radius:0.5em;
			opacity:0.8;
			filter:alpha(opacity=80);
			
			text-align:center;
			z-index:20; position:absolute;
			background-color:#eeeeef;
			color:black;
			font-family:helvetica;padding:0.5em;
			font-size:0.9em;
			
			padding-top:1.1em;
			line-height:1.3em;
			height:3em;
			width:23em;
		}
		
		.flowchart .window-group { border:1px solid #346789;
			box-shadow: 2px 2px 19px #aaa;
			   -o-box-shadow: 2px 2px 19px #aaa;
			   -webkit-box-shadow: 2px 2px 19px #aaa;
			   -moz-box-shadow: 2px 2px 19px #aaa;
			-moz-border-radius:0.5em;
			border-radius:0.5em;
			opacity:0.8;
			filter:alpha(opacity=80);
			
			text-align:center;
			z-index:20; position:absolute;
			background-color:#eeeeef;
			color:black;
			font-family:helvetica;padding:0.5em;
			font-size:0.9em;
			
			padding-top:1.1em;
			line-height:1.3em;
			height:3em;
			width:13em;
		}
		
		.flowchart .window-client:hover {
			box-shadow: 2px 2px 19px #444;
		   -o-box-shadow: 2px 2px 19px #444;
		   -webkit-box-shadow: 2px 2px 19px #444;
		   -moz-box-shadow: 2px 2px 19px #444;
		    opacity:0.6;
			filter:alpha(opacity=60);
		}
		
		.flowchart .window-server:hover {
			box-shadow: 2px 2px 19px #444;
		   -o-box-shadow: 2px 2px 19px #444;
		   -webkit-box-shadow: 2px 2px 19px #444;
		   -moz-box-shadow: 2px 2px 19px #444;
		    opacity:0.6;
			filter:alpha(opacity=60);
		}
		
		.flowchart .window-service:hover {
			box-shadow: 2px 2px 19px #444;
		   -o-box-shadow: 2px 2px 19px #444;
		   -webkit-box-shadow: 2px 2px 19px #444;
		   -moz-box-shadow: 2px 2px 19px #444;
		    opacity:0.6;
			filter:alpha(opacity=60);
		}
		
		.flowchart .window-group:hover {
			box-shadow: 2px 2px 19px #444;
		   -o-box-shadow: 2px 2px 19px #444;
		   -webkit-box-shadow: 2px 2px 19px #444;
		   -moz-box-shadow: 2px 2px 19px #444;
		    opacity:0.6;
			filter:alpha(opacity=60);
		}
		
		.flowchart .active {
			border:1px dotted green;
		}
		.flowchart .hover {
			border:1px dotted red;
		}
		
		.flowchart ._jsPlumb_connector { z-index:4; }
		.flowchart ._jsPlumb_endpoint, .endpointTargetLabel, .endpointSourceLabel{ z-index:21;cursor:pointer; }
		
		.flowchart .aLabel {
		 	background-color:white; 
			padding:0.4em; 
			font:12px sans-serif; 
			color:#444;
			z-index:21;
			border:1px dotted gray;
			opacity:0.8;
			filter:alpha(opacity=80);
			cursor: pointer;
		}
		.flowchart .aLabel._jsPlumb_hover {
			background-color:#5C96BC;
			color:white;	
			border:1px solid white;
		}
	</style>
	
    <script type="text/javascript">
    	
	    function callback(trnId, data) {
	    	
	    	if (trnId == 'QUERY_GRAPH') {
				draw(data);
			} else if (trnId == 'EDIT_GRAPH') {
				if (data.result == 'false') {
					//alert('修改失败');
					query();
				} else {
					//alert('修改成功');
				}
			}
		}
    
		function init() {
			
			$('#query').button().click(function() {
				query();
			});
			
			var urlParamObject = getURLParamObject();
			if (urlParamObject != null) {				
				$('#form :input').each(function(i){
					if (urlParamObject[$(this).attr('id')] != null) {					
						$(this).val(urlParamObject[$(this).attr('id')]);
					}
				});
			}
			
			$(window).resize(function(){ 
				query();
			});
			
			query();
		}
		
		function query() {
			
			var vo = new Object();
			
			vo['service_Name'] = $('#service_Name').val();
			
			var jsonSet = new JsonSet();
			jsonSet.put('input', vo);
			
			transaction({
				id: 'QUERY_GRAPH',
				url: '<%=request.getContextPath()%>/console/service/querygraph',
				jsonSet: jsonSet
			});
		}
		
		function edit(sourceId, targetId) {
			
			if ((sourceId.indexOf('Client') == -1 && sourceId.indexOf('Server') == -1)
					|| targetId.indexOf('Group') == -1) {
				query();
				return;
			}
			
			if (sourceId.indexOf('Client') != -1 && targetId.indexOf('Group') != -1) {
				
				var vo = new Object();
				
				vo['client_Id'] = sourceId.substring(sourceId.indexOf('Client') + 6)
				vo['group_Name'] = targetId.substring(targetId.indexOf('Group') + 5)
				
				var jsonSet = new JsonSet();
				jsonSet.put('input', vo);
				
				transaction({
					id: 'EDIT_GRAPH',
					url: '<%=request.getContextPath()%>/console/client/update',
					jsonSet: jsonSet
				});
				
			} else if (sourceId.indexOf('Server') != -1 && targetId.indexOf('Group') != -1) {
				
				var vo = new Object();
				
				vo['server_Id'] = sourceId.substring(sourceId.indexOf('Server') + 6)
				vo['group_Name'] = targetId.substring(targetId.indexOf('Group') + 5)
				
				var jsonSet = new JsonSet();
				jsonSet.put('input', vo);
				
				transaction({
					id: 'EDIT_GRAPH',
					url: '<%=request.getContextPath()%>/console/server/update',
					jsonSet: jsonSet
				});
			}
		}
		
		function draw(data) {
			
			jsPlumb.ready(function() {
				
				var instance = jsPlumb.getInstance({
					DragOptions : { cursor: 'pointer', zIndex:2000 },
					ConnectionOverlays : [
						[ "Arrow", { location:1 } ]
					],
					Container:"flowchart"
				});

				var connectorPaintStyle = {
					lineWidth:2,
					strokeStyle:"#61B7CF",
					joinstyle:"round",
					outlineColor:"white",
					outlineWidth:1
				},
				
				connectorHoverStyle = {
					lineWidth:2,
					strokeStyle:"#216477",
					outlineWidth:1,
					outlineColor:"white"
				},
				
				endpointHoverStyle = {
					fillStyle:"#216477",
					strokeStyle:"#216477"
				},
				
				sourceEndpoint = {
					endpoint:"Dot",
					paintStyle:{ 
						strokeStyle:"#7AB02C",
						fillStyle:"transparent",
						radius:5,
						lineWidth:2 
					},				
					isSource:true,
					connector:[ "Bezier", { stub:[40, 60], gap:10, cornerRadius:5, alwaysRespectStubs:true } ],								                
					connectorStyle:connectorPaintStyle,
					hoverPaintStyle:endpointHoverStyle,
					connectorHoverStyle:connectorHoverStyle,
			        dragOptions:{}
				},		
				
				targetEndpoint = {
					endpoint:"Dot",					
					paintStyle:{ fillStyle:"#7AB02C",radius:6 },
					hoverPaintStyle:endpointHoverStyle,
					maxConnections:-1,
					dropOptions:{ hoverClass:"hover", activeClass:"active" },
					isTarget:true
				},		
				
				init = function(connection) {			
					connection.getOverlay("label").setLabel(connection.sourceId.substring(15) + "-" + connection.targetId.substring(15));
					connection.bind("editCompleted", function(o) {
					});
				};			

				var _addEndpoints = function(toId, sourceAnchors, targetAnchors) {
					for (var i = 0; i < sourceAnchors.length; i++) {
						var sourceUUID = toId + sourceAnchors[i];
						instance.addEndpoint(toId, sourceEndpoint, { anchor:sourceAnchors[i], uuid:sourceUUID });						
					}
					for (var j = 0; j < targetAnchors.length; j++) {
						var targetUUID = toId + targetAnchors[j];
						instance.addEndpoint(toId, targetEndpoint, { anchor:targetAnchors[j], uuid:targetUUID });						
					}
				};
				
				var groupTop = 15;
				var clientTop = 14;
				var serverTop = 14;
				
				var _addWindow = function(type, typeName, id, name, ip, port, url) {
					if (type == 'service') {
						$("#flowchart").append('<div class="window-service" id="WindowService"><strong>' + typeName + '</strong><br/><strong>' + name + '</strong><br/><br/></div>');
						$("#WindowService").attr('style', 'top:7em; left:' + ($(".flowchart").width() / 12 / 2 - 40 / 2) + 'em;');
					} else if (type == 'group') {
						var groupId = 'WindowGroup' + name;
						$("#flowchart").append('<div class="window-group" id="' + groupId + '"><strong>' + typeName + '</strong><br/><strong>' + name + '</strong><br/><br/></div>');	
						$("#" + groupId).attr('style', 'top:' + groupTop + 'em; left:' + ($(".flowchart").width() / 12 / 2 - 30 / 2) + 'em;');
						_addEndpoints(groupId, [], ["RightMiddle", "LeftMiddle"]);
						groupTop += 7;
					} else if (type == 'client') {
						var clientId = 'WindowClient' + id;
						$("#flowchart").append('<div class="window-client" id="' + clientId + '"><strong>' + typeName + '</strong><br/><strong>' + name + '</strong><br/><strong>' + ip + '</strong><br/><br/></div>');						
						$("#" + clientId).attr('style', 'top:' + clientTop + 'em; left:' + ($(".flowchart").width() / 12 / 6 - 28 / 2) + 'em;');
						_addEndpoints(clientId, ["RightMiddle"], []);
						clientTop += 7;
					} else if (type == 'server') {
						var serverId = 'WindowServer' + id;
						$("#flowchart").append('<div class="window-server" id="' + serverId + '"><strong>' + typeName + '</strong><br/><strong>' + name + '</strong><br/><strong>' + ip + ':' + port + url + '</strong><br/><br/></div>');
						$("#" + serverId).attr('style', 'top:' + serverTop + 'em; left:' + ($(".flowchart").width() / 12 / 6 * 5 - 32 / 2) + 'em;');
						_addEndpoints(serverId, ["LeftMiddle"], []);
						serverTop += 7;
					}
				}
				
				var _addConnect = function(fromName, toName) {
					instance.connect({uuids:[fromName, toName], editable:true});
				}
					
				instance.doWhileSuspended(function() {
					
					$("#flowchart").empty();
					
					_addWindow('service', '服务', '', $('#service_Name').val());
					
					for(var groupName in data) {					
						
						_addWindow('group', '分组', '', groupName);
						
						var clientArray = data[groupName]['client'];
												
						for (var i=0; i<clientArray.length; i++) {
							var status = clientArray[i]['system_Status'] == 1 ? '在线' : '离线';
							_addWindow('client', '客户端: ' + status, clientArray[i]['client_Id'], clientArray[i]['client_Name'], clientArray[i]['ip']);
							_addConnect('WindowClient' + clientArray[i]['client_Id'] + 'RightMiddle', 'WindowGroup' + groupName + 'LeftMiddle');
						}
						
						var serverArray = data[groupName]['server'];
						for (var i=0; i<serverArray.length; i++) {
							var status = serverArray[i]['system_Status'] == 1 ? '在线' : '离线';
							_addWindow('server', '服务端: ' + status, serverArray[i]['server_Id'], serverArray[i]['server_Name'], serverArray[i]['ip'], serverArray[i]['port'], serverArray[i]['url']);
							_addConnect('WindowServer' + serverArray[i]['server_Id'] + 'LeftMiddle', 'WindowGroup' + groupName + 'RightMiddle');
						}
					}
					
					instance.bind("connection", function(connInfo, originalEvent) { 
						init(connInfo.connection);
					});			
					
					instance.draggable(jsPlumb.getSelector(".flowchart .window-client"), { grid: [2, 2] });	
					instance.draggable(jsPlumb.getSelector(".flowchart .window-server"), { grid: [2, 2] });	
					instance.draggable(jsPlumb.getSelector(".flowchart .window-service"), { grid: [2, 2] });
					instance.draggable(jsPlumb.getSelector(".flowchart .window-group"), { grid: [2, 2] });

					instance.bind("click", function(conn, originalEvent) {
					});	
					
					instance.bind("connectionDrag", function(connection) {
					});		
					
					instance.bind("connectionDragStop", function(connection) {
						if (connection.source == null || connection.target == null) {
							query();
						} else {							
							edit(connection.sourceId, connection.targetId);
						}
					});

					instance.bind("connectionMoved", function(params) {
					});
				});
				
				jsPlumb.fire("jsPlumbDemoLoaded", instance);
				
			});
		}
	</script>
  </head>

  <body onload="init();" onkeydown="onEnterDown(query);" >
	<div id="button_div">
		<button id="query">查询</button>
	</div>
	<div class="flowchart" id="flowchart">
    </div>
    <div id="form">
    	<input type="hidden" id="service_Name"/>
    </div>
  </body>
</html>
