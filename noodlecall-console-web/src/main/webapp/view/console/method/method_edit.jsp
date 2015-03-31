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
			                    'method_Name',
			                    'manual_Status',
			                    'cluster_Type',
			                    'route_Type',
			                    'service_Name',
			                    'is_Downgrade'
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
					url: '<%=request.getContextPath()%>/console/method/insert',
					jsonSet: jsonSet
				});
			} else {
				transaction({
					id: 'UPDATE',
					url: '<%=request.getContextPath()%>/console/method/update',
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
			    	<th><label id="method_Name_Label">方法名称</label></th>
			    	<td><input type="text" id="method_Name" maxlength="32"/></td>
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
			    	<th><label id="cluster_Type_Label">集群类型</label></th>
			    	<td>
			    		<select id="cluster_Type" style="width: 83.5%;">
			    			<option value="">--select--</option>
			    			<option value="FAILOVER">重试</option>
			    			<option value="ONCE">只一次</option>
			    			<option value="ALL">全部</option>
			    		</select>
			    	</td>
			    	<th><label id="route_Type_Label">负载类型</label></th>
			    	<td>
			    		<select id="route_Type" style="width: 83.5%;">
			    			<option value="">--select--</option>
			    			<option value="RANDOM">随机</option>
			    			<option value="WEIGHT">权重</option>
			    			<option value="RESPONSE">响应时间</option>
			    		</select>
			    	</td>
				</tr>
				<tr>
			    	<th><label id="service_Name_Label">服务名称</label></th>
			    	<td><input type="text" id="service_Name" maxlength="255"/></td>
					<th><label id="is_Downgrade_Label">是否降级</label></th>
			    	<td>
			    		<select id="is_Downgrade" style="width: 83.5%;">
			    			<option value="">--select--</option>
			    			<option value="1">是</option>
			    			<option value="2">否</option>
			    		</select>
			    	</td>
				</tr>
				<tr>
					<th><label id="downgrade_Type_Label">降级类型</label></th>
			    	<td>
			    		<select id="downgrade_Type" style="width: 83.5%;">
			    			<option value="">--select--</option>
			    			<option value="1">平均响应时间</option>
			    			<option value="2">超时次数</option>
			    		</select>
			    	</td>
					<th><label id="return_Type_Label">返回值类型</label></th>
			    	<td>
			    		<select id="return_Type" style="width: 83.5%;">
			    			<option value="">--select--</option>
			    			<option value="1">抛出异常</option>
			    			<option value="2">返回NULL</option>
			    		</select>
			    	</td>
				</tr>
				<tr>
			    	<th><label id="avgTime_Limit_Threshold_Label">平均时间限制</label></th>
			    	<td><input type="text" id="avgTime_Limit_Threshold" maxlength="8"/></td>
			    	<th><label id="overtime_Threshold_Label">超时阀值</label></th>
			    	<td><input type="text" id="overtime_Threshold" maxlength="8"/></td>
				</tr>
				<tr>
			    	<th><label id="overtime_Limit_Threshold_Label">超时次数限制</label></th>
			    	<td><input type="text" id="overtime_Limit_Threshold" maxlength="8"/></td>
					<th><label>&nbsp;</label></th>
			    	<td>&nbsp;</td>
				</tr>
			</table>
			<input type="hidden" id="method_Id"/>
		</div>
	</div>
  </body>
</html>
