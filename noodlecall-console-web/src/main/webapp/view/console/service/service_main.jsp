<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../../global.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>noodlecall-Child</title>
       
    <link href="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/common/tool/jqgrid/css/ui.jqgrid.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/common/css/my.css" rel="stylesheet" type="text/css" />
    
    <script src="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/external/jquery-1.8.2.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/external/jquery-ui-1.9.1.custom.min.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/common/tool/jqgrid/js/i18n/grid.locale-en.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/common/tool/jqgrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/common/js/common.js" type="text/javascript"></script>
	
    <script type="text/javascript">
    	
	    function callback(trnId, data) {
	    	
			if (trnId == 'DELETES') {
				if (data.result == 'false') {
					alert('删除失败');
				} else {
					alert('删除成功');
					query();
				}
			}
		}
    	
		function init() {
			
			$('#query').button().click(function() {
				query();
			});
			$('#insert').button().click(function() {
				insert();
			});
			$('#update').button().click(function() {
				update();
			});
			$('#deletes').button().click(function() {
				deletes();
			});
			$('#setGroup').button().click(function() {
				setGroup();
			});
			$('#setClient').button().click(function() {
				setClient();
			});
			$('#setServer').button().click(function() {
				setServer();
			});
			$('#getGraph').button().click(function() {
				getGraph();
			});
			
			$('#list').jqGrid({
		   		url: '<%=request.getContextPath()%>/console/service/querypage',
				datatype: 'local',
				mtype: 'post',
			   	colNames: [
					'服务ID', 
					'服务名称',
					'状态',
					'接口',
					'集群类型',
					'路由类型'
					],
			   	colModel: [
					{name:'service_Id', index:'service_Id', width:80, align:'center'},
					{name:'service_Name', index:'service_Name', width:280, align:'center'},
					{name:'manual_Status', index:'manual_Status', width:120, align:'center', formatter:'select', editoptions:{value:'1:有效;2:无效'}},
					{name:'inteface_Name', index:'inteface_Name', width:480, align:'center'},
					{name:'cluster_Type', index:'cluster_Type', width:120, align:'center', formatter:'select', editoptions:{value:'FAILOVER:重试;ONCE:只一次;ALL:全部'}},
					{name:'route_Type', index:'route_Type', width:120, align:'center', formatter:'select', editoptions:{value:'RANDOM:随机;WEIGHT:权重;RESPONSE:响应时间'}}
			   	],
			   	rowNum: 10,
			   	rowList: [10,20,30,40,50,100],
			   	pager: '#pager',
			   	sortname: 'id',
			    viewrecords: true,
			    autowidth: true,
			    shrinkToFit: false,
			    height: 231,
			    sortorder: 'desc',
			    multiselect: true,
			    jsonReader: {
					repeatitems : false
				},
				ondblClickRow: false,
				subGrid: true,
				subGridRowExpanded: function(subgrid_id, row_id) {
					var subgrid_table_id, pager_id;
					subgrid_table_id = subgrid_id + "_t";
					pager_id = "p_" + subgrid_table_id;
					$("#" + subgrid_id).html("<table id='" + subgrid_table_id + "' class='scroll'></table><div id='" + pager_id + "' class='scroll'></div>");
					var ret = jQuery('#list').jqGrid('getRowData', row_id);
					var vo = new Object();
					vo['service_Name'] = ret['service_Name'];
					jQuery("#"+subgrid_table_id).jqGrid({
						url:'<%=request.getContextPath()%>/console/method/querybyservicepage',
						datatype: "json",
						mtype: 'post',
						postData:{'input': jsonToString(vo)}, 
						colNames: [
							'方法ID', 
							'方法名称',
							'状态',
							'集群类型',
							'路由类型',
							'服务名称'
							],
					   	colModel: [
							{name:'method_Id', index:'method_Id', width:80, align:'center'},
							{name:'method_Name', index:'method_Name', width:460, align:'center'},
							{name:'manual_Status', index:'manual_Status', width:120, align:'center', formatter:'select', editoptions:{value:'1:有效;2:无效'}},
							{name:'cluster_Type', index:'cluster_Type', width:120, align:'center', formatter:'select', editoptions:{value:'FAILOVER:重试;ONCE:只一次;ALL:全部'}},
							{name:'route_Type', index:'route_Type', width:120, align:'center', formatter:'select', editoptions:{value:'RANDOM:随机;WEIGHT:权重;RESPONSE:响应时间'}},
							{name:'service_Name', index:'service_Name', width:280, align:'center'}
					   	],
					   	rowNum:10,
					   	rowList: [10,20,30,40,50,100],
					   	pager: pager_id,
					   	sortname: 'method_Id',
					    sortorder: "desc",
					    height: '100%',
					    jsonReader: {
							repeatitems : false
						}
					});
					jQuery("#"+subgrid_table_id).jqGrid('navGrid', "#" + pager_id,{search:false,edit:false,add:false,del:false})
				},
			    caption: '查询结果',
			    gridComplete:function(){
			    	repaintGrid(231, "query_div", "list", "button_div");
			    }
			});
				
			$('#list').jqGrid('navGrid', '#pager', {search:false, edit:false, add:false, del:false});
			
			$(window).resize(function(){ 
				$("#list").setGridWidth($(window).width() - 14);
			});
			
			query();
		}
		
		function query() {
			
			var vo = new Object();
			$('#form :input').each(function(i){
				if ($(this).val() != '' 
						&& $(this).attr('id').indexOf("Dt") == -1) {					
					vo[$(this).attr('id')] = $(this).val();
				}
			 });
			
			$('#list').jqGrid('setGridParam', {   
				url: '<%=request.getContextPath()%>/console/service/querypage',
				datatype: 'json',
				postData:{'input': jsonToString(vo)}, 
		        page: 1   
		    }).trigger('reloadGrid');
		}
		
		function insert() {
			top.openDialog('执行机器新增', '<%=request.getContextPath()%>/view/console/service/service_edit.jsp', null, 260, 700, query);
		}
		
		function update() {
			
			var index = jQuery('#list').jqGrid('getGridParam', 'selarrrow');
			if (index.toString() == '') {
				alert('请选择');
				return;
			}
			
			var indexArray = index.toString().split(',');
			if (indexArray.length > 1) {
				alert('只能选择一行');
				return;
			}
			
			var ret = jQuery('#list').jqGrid('getRowData', index);
			top.openDialog('执行机器修改', '<%=request.getContextPath()%>/view/console/service/service_edit.jsp', ret, 260, 700, query);					
		}
		
		function deletes() {
			
			var index = jQuery('#list').jqGrid('getGridParam', 'selarrrow');
			if (index.toString() == '') {
				alert('请选择');
				return;
			}
			
			if(!window.confirm('你确定要删除吗？')){
				return false;
            }
			
			var retArray = new Array();
			var indexArray = index.toString().split(',');
			for (var i=0; i<indexArray.length; i++) {				
				var ret = jQuery('#list').jqGrid('getRowData', indexArray[i]);
				ret.beat_Time = null;
				retArray.push(ret);
			}
			
			var jsonSet = new JsonSet();
			jsonSet.put('input', retArray);
			
			transaction({
				id: 'DELETES',
				url: '<%=request.getContextPath()%>/console/service/deletes',
				jsonSet: jsonSet
			});	
		}
		
		function setGroup() {
			var index = jQuery('#list').jqGrid('getGridParam', 'selarrrow');
			if (index.toString() == '') {
				alert('请选择');
				return;
			}
			var indexArray = index.toString().split(',');
			if (indexArray.length > 1) {
				alert('只能选择一行');
				return;
			}
			var ret = jQuery('#list').jqGrid('getRowData', index);
			top.openDialog('设置分组', '<%=request.getContextPath()%>/view/console/service/service-group/service_group_main.jsp', ret, 540, 1200, null);					
		}
		
		function setClient() {
			var index = jQuery('#list').jqGrid('getGridParam', 'selarrrow');
			if (index.toString() == '') {
				alert('请选择');
				return;
			}
			var indexArray = index.toString().split(',');
			if (indexArray.length > 1) {
				alert('只能选择一行');
				return;
			}
			var ret = jQuery('#list').jqGrid('getRowData', index);
			top.openDialog('设置客户端', '<%=request.getContextPath()%>/view/console/service/service-client/service_client_main.jsp', ret, 620, 1200, null);					
		}
		
		function setServer() {
			var index = jQuery('#list').jqGrid('getGridParam', 'selarrrow');
			if (index.toString() == '') {
				alert('请选择');
				return;
			}
			var indexArray = index.toString().split(',');
			if (indexArray.length > 1) {
				alert('只能选择一行');
				return;
			}
			var ret = jQuery('#list').jqGrid('getRowData', index);
			top.openDialog('设置服务端', '<%=request.getContextPath()%>/view/console/service/service-server/service_server_main.jsp', ret, 680, 1200, null);					
		}
		
		function getGraph() {
			var index = jQuery('#list').jqGrid('getGridParam', 'selarrrow');
			if (index.toString() == '') {
				alert('请选择');
				return;
			}
			var indexArray = index.toString().split(',');
			if (indexArray.length > 1) {
				alert('只能选择一行');
				return;
			}
			var ret = jQuery('#list').jqGrid('getRowData', index);
			top.openDialog('拓扑图', '<%=request.getContextPath()%>/view/console/service/service-graph/service_graph_main.jsp', ret, 650, 1300, null);					
		}
	</script>
  </head>

  <body onload="init();" onkeydown="onEnterDown(query);" >
	<div id="button_div">
		<button id="query">查询</button>
		<button id="insert">新增</button>
		<button id="update">修改</button>
		<button id="deletes">删除</button>
		<button id="setGroup">设置分组</button>
		<button id="setClient">设置客户端</button>
		<button id="setServer">设置服务端</button>
		<button id="getGraph">拓扑图</button>
	</div>
	<div id="query_div">
		<div id="form" style="width: auto;">
			<table width="100%" border="1" cellspacing="0" cellpadding="0" class="s_layout">
				<colmethod>
					<col width="10%" />
					<col width="15%" />
					<col width="10%" />
					<col width="15%" />
					<col width="10%" />
					<col width="15%" />
					<col width="10%" />
					<col width="15%" />
				</colmethod>					
			    <tr>
			    	<th><label>服务ID</label></th>
			    	<td><input type="text" id="service_Id" maxlength="10"/></td>
			    	<th><label>服务名称</label></th>
			    	<td><input type="text" id="service_Name" maxlength="255"/></td>
			    	<th><label>状态</label></th>
			    	<td>
			    		<select id="manual_Status">
			    			<option value="">--all--</option>
			    			<option value="1">有效</option>
			    			<option value="2">无效</option>
			    		</select>
			    	</td>
			    	<th><label>服务名称</label></th>
			    	<td><input type="text" id="inteface_Name" maxlength="2048"/></td>
				</tr>			
			    <tr>
			    	<th><label>集群类型</label></th>
			    	<td>
			    		<select id="cluster_Type">
			    			<option value="">--all--</option>
			    			<option value="FAILOVER">重试</option>
			    			<option value="ONCE">只一次</option>
			    			<option value="ALL">全部</option>
			    		</select>
			    	</td>
			    	<th><label>负载类型</label></th>
			    	<td>
			    		<select id="route_Type">
			    			<option value="">--all--</option>
			    			<option value="RANDOM">随机</option>
			    			<option value="WEIGHT">权重</option>
			    			<option value="RESPONSE">响应时间</option>
			    		</select>
			    	</td>
			    	<th><label>&nbsp;</label></th>
			    	<td>&nbsp;</td>
			    	<th><label>&nbsp;</label></th>
			    	<td>&nbsp;</td>
				</tr>
			</table>
		</div>
	</div>
	<div id="list_div" style="width:auto;">
		<table id="list"></table>
		<div id="pager"></div>
	</div>
  </body>
</html>
