<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../../global.jsp"%>
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
		
    	var initModel;
    	var isEnter = true;
    
		function callback(trnId, data) {
			
			if (trnId == 'INSERT') {
    			if (data.result == 'false') {
    				alert('新增失败');
    			} else {
    				alert('新增成功');
    				top.closeDialog(true);
    			}
			} else if (trnId == 'UPDATE') {
    			if (data.result == 'false') {
    				alert('修改失败');
    			} else {
    				alert('修改成功');
    				top.closeDialog(true);
    			}
			}
		}
    
		function init() {
			
			$('#save').button().click(function() {
				save()
			});
			$('#cancel').button().click(function() {
				top.closeDialog(false);
			});
			
			var urlParamObject = getURLParamObject();
			if (urlParamObject != null) {				
				$('#form :input').each(function(i){
					if (urlParamObject[$(this).attr('id')] != null) {					
						$(this).val(urlParamObject[$(this).attr('id')]);
					}
				});
				initModel = 'UPDATE';
			} else {
				initModel = 'INSERT';
			}
		}
		
		function check() {
			
			var notNullArray = [
			                    'server_Name',
			                    'manual_Status',
			                    'system_Status',
			                    'ip,'
			                    'port',
			                    'url',
			                    'server_Type',
			                    'serialize_Type',
			                    'weight',
			                    'service_Name',
			                    'group_Name'
			                    ];
			
			for (var i=0; i<notNullArray.length; i++) {
				var id = notNullArray[i];
				if ($('#' + id).val() == '') {
					alert($('#' + id + '_Label').text() + "不能为空");
					return false;
				}
			}
			
			return true;
		}
		
		function save() {
			
			if (!check()) {
				return;
			}
			
			var vo = new Object();
			$('#form :input').each(function(i){
				vo[$(this).attr('id')] = $(this).val();
			});
			
			var jsonSet = new JsonSet();
			jsonSet.put('input', vo);
			
			if (initModel == 'INSERT') {				
				transaction({
					id: 'INSERT',
					url: '<%=request.getContextPath()%>/console/server/insert',
					jsonSet: jsonSet
				});
			} else {
				transaction({
					id: 'UPDATE',
					url: '<%=request.getContextPath()%>/console/server/update',
					jsonSet: jsonSet
				});				
			}
		}
		
		function onEnterDownLocal(event) {
    		if (event.keyCode == 13) {
    			if (isEnter) {
    				save();
    			}
    		}
    	}
		
	</script>
  </head>

  <body onload="init();" onkeydown="onEnterDownLocal(event);" >
	<div>
		<button id="save">保存</button>
		<button id="cancel">取消</button>
	</div>
	<div>
		<div id="form" style="width: auto;">
			<table width="100%" border="1" cellspacing="0" cellpadding="0" class="s_layout">
				<colgroup>
					<col width="20%" />
					<col width="30%" />
					<col width="20%" />
					<col width="30%" />
				</colgroup>					
			    <tr>
			    	<th><label id="server_Name_Label">服务端名称</label></th>
			    	<td><input type="text" id="server_Name" maxlength="128"/></td>
					<th><label id="manual_Status_Label">状态</label></th>
			    	<td>
			    		<select id="manual_Status" style="width: 83.5%;">
			    			<option value="">--select--</option>
			    			<option value="1">有效</option>
			    			<option value="2">无效</option>
			    		</select>
			    	</td>
				</tr>
				<tr>
			    	<th><label id="system_Status_Label">在线状态</label></th>
			    	<td>
			    		<select id="system_Status" style="width: 83.5%;">
			    			<option value="">--select--</option>
			    			<option value="1">在线</option>
			    			<option value="2">离线</option>
			    		</select>
			    	</td>
			    	<th><label id="ip_Label">IP</label></th>
			    	<td><input type="text" id="ip" maxlength="15"/></td>
				</tr>
				<tr>
			    	<th><label id="port_Label">PORT</label></th>
			    	<td><input type="text" id="port" maxlength="5"/></td>
			    	<th><label id="url_Label">URL</label></th>
			    	<td><input type="text" id="url" maxlength="512"/></td>
				</tr>
				<tr>
			    	<th><label id="server_Type_Label">服务器类型</label></th>
			    	<td>
			    		<select id="server_Type" style="width: 83.5%;">
			    			<option value="">--select--</option>
			    			<option value="JETTY">JETTY</option>
			    			<option value="SERVLET">SERVLET</option>
			    			<option value="NETTY">NETTY</option>
			    		</select>
			    	</td>
			    	<th><label id="serialize_Type_Label">序列化类型</label></th>
			    	<td>
			    		<select id="serialize_Type" style="width: 83.5%;">
			    			<option value="">--select--</option>
			    			<option value="JSON">JSON</option>
			    			<option value="HESSIAN">HESSIAN</option>
			    		</select>
			    	</td>
				</tr>
				<tr>
			    	<th><label id="weight_Label">权重</label></th>
			    	<td><input type="text" id="weight" maxlength="5"/></td>
			    	<th><label id="service_Name_Label">服务名称</label></th>
			    	<td><input type="text" id="service_Name" maxlength="255"/></td>
				</tr>
				<tr>
			    	<th><label id="group_Name_Label">分组名称</label></th>
			    	<td><input type="text" id="group_Name" maxlength="128"/></td>
			    	<th><label>&nbsp;</label></th>
			    	<td>&nbsp;</td>
				</tr>
			</table>
			<input type="hidden" id="server_Id"/>
		</div>
	</div>
  </body>
</html>
