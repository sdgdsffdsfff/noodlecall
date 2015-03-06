<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../../../global.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>NotifyServer-Child</title>
       
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
	    	if (trnId == 'INSERTS') {
    			if (data.result == 'false') {
    				alert('新增失败');
    			} else {
    				alert('新增成功');
    				query();
    			}
			} else if (trnId == 'DELETES') {
				if (data.result == 'false') {
					alert('删除失败');
				} else {
					alert('删除成功');
					query();
				}
			}
		}
    
		function init() {
			
			$('#query_left').button().click(function() {
				queryLeft();
			});
			$('#close_left').button().click(function() {
				top.closeDialog(false);
			});
			$('#query_right').button().click(function() {
				queryRight();
			});
			$('#close_right').button().click(function() {
				top.closeDialog(false);
			});
			$('#inserts').button().click(function() {
				inserts();
			});
			$('#deletes').button().click(function() {
				deletes();
			});
			
			var urlParamObject = getURLParamObject();
			if (urlParamObject != null) {				
				$('#form_left :input').each(function(i){
					if (urlParamObject[$(this).attr('id')] != null) {					
						$(this).val(urlParamObject[$(this).attr('id')]);
					}
				});
				$('#group_Name').attr('disabled', 'disabled');
			}
			
			var paramObject = {'group_Name': urlParamObject.group_Name};
			
			$('#list_left').jqGrid({
		   		url: '<%=request.getContextPath()%>/console/client/queryincludegrouppage',
				datatype: 'json',
				mtype: 'post',
				postData:{'input': jsonToString(paramObject)}, 
				colNames: [
					'客户端ID', 
					'客户端名称',
					'状态',
					'在线状态',
					'IP',
					'服务名称',
					'分组名称'
					],
			   	colModel: [
					{name:'client_Id', index:'client_Id', width:80, align:'center'},
					{name:'client_Name', index:'client_Name', width:280, align:'center'},
					{name:'manual_Status', index:'manual_Status', width:120, align:'center', formatter:'select', editoptions:{value:'1:有效;2:无效'}},
					{name:'system_Status', index:'system_Status', width:120, align:'center', formatter:'select', editoptions:{value:'1:在线;2:离线'}},
					{name:'ip', index:'ip', width:220, align: 'center'},
					{name:'service_Name', index:'service_Name', width:280, align:'center'},
					{name:'group_Name', index:'group_Name', width:280, align:'center'}
			   	],
			   	rowNum: 10,
			   	rowList: [10,20,30,40,50,100],
			   	pager: '#pager_left',
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
			    caption: '查询结果',
			    gridComplete:function(){
			    	repaintGrid(247, "query_left_div", "list_left", "button_left_div");
			    }
			});
				
			$('#list_left').jqGrid('navGrid', '#pager_left', {search:false, edit:false, add:false, del:false});
			
			$('#list_right').jqGrid({
		   		url: '<%=request.getContextPath()%>/console/client/queryexcludegrouppage',
				datatype: 'json',
				mtype: 'post',
				postData:{'input': jsonToString(paramObject)}, 
				colNames: [
					'客户端ID', 
					'客户端名称',
					'状态',
					'在线状态',
					'IP',
					'服务名称',
					'分组名称'
					],
			   	colModel: [
					{name:'client_Id', index:'client_Id', width:80, align:'center'},
					{name:'client_Name', index:'client_Name', width:280, align:'center'},
					{name:'manual_Status', index:'manual_Status', width:120, align:'center', formatter:'select', editoptions:{value:'1:有效;2:无效'}},
					{name:'system_Status', index:'system_Status', width:120, align:'center', formatter:'select', editoptions:{value:'1:在线;2:离线'}},
					{name:'ip', index:'ip', width:220, align: 'center'},
					{name:'service_Name', index:'service_Name', width:280, align:'center'},
					{name:'group_Name', index:'group_Name', width:280, align:'center'}
			   	],
			   	rowNum: 10,
			   	rowList: [10,20,30,40,50,100],
			   	pager: '#pager_right',
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
			    caption: '查询结果',
			    gridComplete:function(){
			    	repaintGrid(247, "query_right_div", "list_right", "button_right_div");
			    }
			});
				
			$('#list_right').jqGrid('navGrid', '#pager_right', {search:false, edit:false, add:false, del:false});
			
			$(window).resize(function(){ 
				$("#list_left").setGridWidth(Number($(window).width()) * (45 / 100));
				$("#list_right").setGridWidth(Number($(window).width()) * (45 / 100));
			});
		}
		
		function query() {
			
			queryLeft();
			queryRight();
		}
		
		function queryLeft() {
			
			var vo = new Object();
			$('#form_left :input').each(function(i){
				if ($(this).val() != '') {					
					vo[$(this).attr('id').replace(/_left/, '')] = $(this).val();
				}
			 });
			
			$('#list_left').jqGrid('setGridParam', {   
				url: '<%=request.getContextPath()%>/console/client/queryincludegrouppage',
				postData:{'input': jsonToString(vo)}, 
		        page: 1   
		    }).trigger('reloadGrid');
		}
		
		function queryRight() {
			
			var vo = new Object();
			$('#form_right :input').each(function(i){
				if ($(this).val() != '') {					
					vo[$(this).attr('id').replace(/_right/, '')] = $(this).val();
				}
			 });
			
			vo['group_Name'] = $('#group_Name').val();
			
			$('#list_right').jqGrid('setGridParam', {   
				url: '<%=request.getContextPath()%>/console/client/queryexcludegrouppage',
				postData:{'input': jsonToString(vo)}, 
		        page: 1   
		    }).trigger('reloadGrid');
		}
		
		function inserts() {
			
			var index = jQuery('#list_right').jqGrid('getGridParam', 'selarrrow');
			if (index.toString() == '') {
				alert('请选择');
				return;
			}
			
			var retArray = new Array();
			var indexArray = index.toString().split(',');
			for (var i=0; i<indexArray.length; i++) {				
				var ret = jQuery('#list_right').jqGrid('getRowData', indexArray[i]);
				ret['group_Name'] = $('#group_Name').val();
				retArray.push(ret);
			}
			
			var jsonSet = new JsonSet();
			jsonSet.put('input', retArray);
			
			transaction({
				id: 'INSERTS',
				url: '<%=request.getContextPath()%>/console/client/insertsgroup',
				jsonSet: jsonSet
			});	
		}
		
		function deletes() {
			
			var index = jQuery('#list_left').jqGrid('getGridParam', 'selarrrow');
			if (index.toString() == '') {
				alert('请选择');
				return;
			}
			
			var retArray = new Array();
			var indexArray = index.toString().split(',');
			for (var i=0; i<indexArray.length; i++) {				
				var ret = jQuery('#list_left').jqGrid('getRowData', indexArray[i]);
				retArray.push(ret);
			}
			
			var jsonSet = new JsonSet();
			jsonSet.put('input', retArray);
			
			transaction({
				id: 'DELETES',
				url: '<%=request.getContextPath()%>/console/client/deletesgroup',
				jsonSet: jsonSet
			});	
		}
		
	</script>
  </head>

  <body onload="init();" onkeydown="onEnterDown(query);" >
	<div style="width: auto;">
		<table border="0" cellspacing="0" cellpadding="0" style="width:100%;">
			<colgroup>
				<col width="45%" />
				<col width="10%" />
				<col width="45%" />
			</colgroup>
			<tr>
				<td valign="top">
					<div id="button_left_div">
						<button id="query_left">查询</button>
						<button id="close_left">关闭</button>
					</div>
					<div id="query_left_div">
						<div id="form_left" style="width: auto;">
							<table width="100%" border="1" cellspacing="0" cellpadding="0" class="s_layout">
								<colgroup>
									<col width="20%" />
									<col width="30%" />
									<col width="20%" />
									<col width="30%" />
								</colgroup>					
							    <tr>
			    					<th><label>分组名称</label></th>
							    	<td><input type="text" id="group_Name" maxlength="128"/></td>
							    	<th><label>客户端ID</label></th>
							    	<td><input type="text" id="client_Id" maxlength="10"/></td>
							    </tr>		
							    <tr>
							    	<th><label>客户端名称</label></th>
							    	<td><input type="text" id="client_Name" maxlength="128"/></td>
							    	<th><label>状态</label></th>
							    	<td>
							    		<select id="manual_Status">
							    			<option value="">--all--</option>
							    			<option value="1">有效</option>
							    			<option value="2">无效</option>
							    		</select>
							    	</td>
							    </tr>		
							    <tr>
							    	<th><label>在线状态</label></th>
							    	<td>
							    		<select id="system_Status">
							    			<option value="">--all--</option>
							    			<option value="1">在线</option>
							    			<option value="2">离线</option>
							    		</select>
							    	</td>
							    	<th><label>IP</label></th>
							    	<td><input type="text" id="ip" maxlength="15"/></td>
								</tr>			
							    <tr>
							    	<th><label>服务名称</label></th>
			    					<td><input type="text" id="service_Name" maxlength="255"/></td>
							    	<th><label>&nbsp;</label></th>
							    	<td>&nbsp;</td>
								</tr>
							</table>
						</div>
					</div>
					<div id="list_left_div" style="width:auto;">
						<table id="list_left"></table>
						<div id="pager_left"></div>
					</div>
				</td>
				<td align="center">
					<div>
						<button id="inserts">&nbsp;新增&nbsp;</button>
					</div>
					<br/>
					<div>
						<button id="deletes">&nbsp;删除&nbsp;</button>
					</div>
				</td>
				<td valign="top">
					<div id="button_right_div">
						<button id="query_right">查询</button>
						<button id="close_right">关闭</button>
					</div>
					<div id="query_right_div">
						<div id="form_right" style="width: auto;">
							<table width="100%" border="1" cellspacing="0" cellpadding="0" class="s_layout">
								<colgroup>
									<col width="10%" />
									<col width="15%" />
									<col width="10%" />
									<col width="15%" />
								</colgroup>			
								<tr>
							    	<th><label>客户端ID</label></th>
							    	<td><input type="text" id="client_Id" maxlength="10"/></td>
							    	<th><label>客户端名称</label></th>
							    	<td><input type="text" id="client_Name" maxlength="128"/></td>
							    </tr>			
							    <tr>
							    	<th><label>状态</label></th>
							    	<td>
							    		<select id="manual_Status">
							    			<option value="">--all--</option>
							    			<option value="1">有效</option>
							    			<option value="2">无效</option>
							    		</select>
							    	</td>
							    	<th><label>在线状态</label></th>
							    	<td>
							    		<select id="system_Status">
							    			<option value="">--all--</option>
							    			<option value="1">在线</option>
							    			<option value="2">离线</option>
							    		</select>
							    	</td>
								</tr>			
							    <tr>
							    	<th><label>IP</label></th>
							    	<td><input type="text" id="ip" maxlength="15"/></td>
							    	<th><label>服务名称</label></th>
			    					<td><input type="text" id="service_Name" maxlength="255"/></td>
							    </tr>	
							</table>
						</div>
					</div>
					<div id="list_right_div" style="width:auto;">
						<table id="list_right"></table>
						<div id="pager_right"></div>
					</div>
				</td>
			</tr>
		</table>
	</div>
  </body>
</html>
