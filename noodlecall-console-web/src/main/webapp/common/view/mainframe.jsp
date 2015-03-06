<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../global.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>noodlecall</title>
    
    <link href="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/themes/wijmo/jquery.wijmo.wijsuperpanel.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/themes/wijmo/jquery.wijmo.wijmenu.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/themes/wijmo/jquery.wijmo.wijdialog.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/themes/wijmo/jquery.wijmo.wijtabs.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/themes/wijmo/jquery.wijmo.wijsplitter.css" rel="stylesheet" type="text/css" />

	<script src="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/external/jquery-1.8.2.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/external/jquery-ui-1.9.1.custom.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/external/jquery.mousewheel.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/external/jquery.bgiframe-2.1.3-pre.js" type="text/javascript"></script>
    
    <script src="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/wijmo/jquery.wijmo.wijutil.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/wijmo/jquery.wijmo.wijsuperpanel.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/wijmo/jquery.wijmo.wijmenu.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/wijmo/jquery.wijmo.wijdialog.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/wijmo/jquery.wijmo.wijtabs.js" type="text/javascript"></script>  
    <script src="<%=request.getContextPath()%>/common/tool/wijmo-open/development-bundle/wijmo/jquery.wijmo.wijsplitter.js" type="text/javascript"></script>  
    <script src="<%=request.getContextPath()%>/common/js/common.js" type="text/javascript"></script>    
    

    <style type="text/css">
    	html
    	{
			margin:0; 
			padding:0;
		}
		*{
			margin:0;
			padding:0;
		}
		body
		{
			margin:0;
			padding:0;
			height: 100%;
		}
		.container
	    {
	    	width: 100%;
	    	height: 100%;
	    }
	    .ui-widget 
		{ 
			font-size: 0.9em; 
		}
		.header
		{
			margin:0;
			padding:5px 0 5px 10px;
		}
    	.menuer
		{
		}
		.wijmo-wijmenu
		{
		    margin-bottom: 8px;
		}
		#tabs
		{
			margin-top: 0em;
			background:#ffffff;
		}
		#tabs li .ui-icon-close
		{
			float: left;
			margin: 0.2em 0.2em 0 0;
			cursor: pointer;
		}
		#add_tab
		{
			cursor: pointer;
		}
        #splitter
        {
            width: 100%;
        }
        #splitter .ui-widget-content 
        {
        	background: #ffffff;
        }
        h1 
        {
        	font-family: 'Bookman Old Style', serif;
        }
        .ui-tabs
		{
		    position: relative;
		    padding: .1em;
		    zoom: 1;
		    border: 0px;
		}
		.ui-tabs .ui-tabs-nav
		{
		    margin: 0;
		    padding: .1em .1em 0;
		}
		.ui-tabs .ui-tabs-nav li a
		{
		    float: left;
		    padding: .2em 1em;
		    text-decoration: none;
		}
		.ui-tabs .ui-tabs-panel
		{
		    display: block;
		    border-width: 0;
		    padding: 0em 0em;
		    background: none;
		}
		.wijmo-wijmenu 
        {
		    margin-bottom: 6px;
		    padding: 0.2em 0.3em 0.2em 0.3em;
		}
		.wijmo-wijmenu-horizontal .wijmo-wijmenu-link 
		{
			padding: 0.3em 0.4em;
		}
		.ui-widget-content 
		{
			background: #ffffff;
		}
	</style>
    <script type="text/javascript">
    	
    	var $tabs;
    	
    	var iframeHeight;
    	var treeIframeHeight;
    	
	    $(document).ready(function() {
	    	
	        $("#menu").wijmenu();
	        
	    	var windowHeight = Number($(window).height());
	        var headerHeight = Number($(".header").css("height").slice(0, -2));
	        var menuerHeight = Number($(".menuer").css("height").slice(0, -2))
	        var splitterHeight = windowHeight - headerHeight - menuerHeight - 18;
	        $("#splitter").css("height", splitterHeight + "px");
	        
	        $("#splitter").wijsplitter({ 
	        	orientation: "vertical", 
	        	fullSplit: false,
	        	splitterDistance: 210,
	        	panel1: {
					collapsed: true
				},
	        	expanded: function () { 
		        	$("#tree-iframe").css("width", "100%");
	        		$("#tree-iframe").css("width", $("#tree-iframe").parent().css("width"));
	        		$("#tree-iframe").css("height", treeIframeHeight + "px");
	        	}
	        });

	        var tabsHeight = splitterHeight - 8;
	        $("#tabs").css("height", tabsHeight + "px");
	        
	        iframeHeight = splitterHeight - 56;
	        if (Sys.ie) {
	        	iframeHeight = iframeHeight - 5;
	        } else {
	        	iframeHeight = iframeHeight - 0;
	        }
	        $("iframe").css("height", iframeHeight + "px");
	        
	        treeIframeHeight = splitterHeight;
	        if (Sys.ie) {
	        	treeIframeHeight = splitterHeight - 6;
	        } else {
	        	treeIframeHeight = splitterHeight - 2;
	        }
	        $("#tree-iframe").css("height", treeIframeHeight + "px");
			
			$("#tabs").wijtabs({ scrollable: false });
			
			$tabs = $('#tabs').wijtabs({
				tabTemplate: '<li><a href="#' + '{href}">#' + '{label}</a><span class="ui-icon ui-icon-close">Remove Tab</span></li>'
			});
			
			$('#tabs span.ui-icon-close').live('click', function () {
				var index = $('li', $tabs).index($(this).parent());
				$tabs.wijtabs('remove', index);
			});
	    });
	    
		function addTab(tabId, title, url) {
			var tabId = '#tab-' + tabId;
			if ($(tabId).size() == 0) {
				$tabs.wijtabs('add', tabId, title);
				$(tabId).html('<iframe src="' + url + '" width="100%" height="' + iframeHeight + '" frameborder="0"></iframe>');
				$tabs.find('[href="' + tabId + '"]').click();
			} else {
				$tabs.find('[href="' + tabId + '"]').click();
			}
		}
		
		var dialogCallback = null;
		
		function openDialog(title, url, urlParam, height, width, callback) {
			height = height != null && height != '' ? height : window.screen.height - 190;
			width = width != null && width != '' ? width : window.screen.width - 300;
			if (urlParam != null && urlParam != '') {
				var strParam = jsonToString(urlParam);
				url = appendURLParam(url, 'urlParam', strParam);
			}
			$('#dialog').wijdialog({
				showStatus: false,
				showControlBox: false,
				autoOpen: false,
				modal: true,
				height: height,
				width: width,
				title: title,
				buttons: {
					'Cancel': function () {
						$(this).wijdialog('close');
					}
				},
				close: function () {
					$('#dialog').empty();
				}
			});
			$('#dialog').append('<iframe src="'+ url +'" width="100%" height="100%" frameborder="0"></iframe>');
			$('#dialog').wijdialog('open');
			dialogCallback = callback;
		}
		
		function closeDialog(isCallback) {
			$('#dialog').wijdialog('close');
			if (isCallback) {
				if (dialogCallback != null) {
					dialogCallback();
				}
			}
		}
	    
		var dialogCallbackChild = null;
		
		function openDialogChild(title, url, urlParam, height, width, callback) {
			height = height != null && height != '' ? height : window.screen.height - 190;
			width = width != null && width != '' ? width : window.screen.width - 300;
			if (urlParam != null && urlParam != '') {
				var strParam = jsonToString(urlParam);
				url = appendURLParam(url, 'urlParam', strParam);
			}
			$('#dialogChild').wijdialog({
				showStatus: false,
				showControlBox: false,
				autoOpen: false,
				modal: true,
				height: height,
				width: width,
				title: title,
				buttons: {
					'Cancel': function () {
						$(this).wijdialog('close');
					}
				},
				close: function () {
					$('#dialogChild').empty();
				}
			});
			$('#dialogChild').append('<iframe src="'+ url +'" width="100%" height="100%" frameborder="0"></iframe>');
			$('#dialogChild').wijdialog('open');
			dialogCallbackChild = callback;
		}
		
		function closeDialogChild(isCallback, param) {
			$('#dialogChild').wijdialog('close');
			if (isCallback) {
				if (dialogCallbackChild != null) {
					dialogCallbackChild(param);
				}
			}
		}
		
	</script>
  </head>
  <body>
	<div class="container">
		<div class="header">
            <h1>noodlecall</h1>
        </div>
		<div class="menuer">
            <ul id="menu">
                <li><a href="#" onclick="javascript:addTab('mainframe_home', 'Home', '<%=request.getContextPath()%>/common/view/welcome.jsp');">HOME</a>
                </li>
                <li><a href="#">配置</a>
                    <ul>
			        	<li><a href="#" onclick="javascript:addTab('mainframe_1_1', '服务', '<%=request.getContextPath()%>/view/console/service/service_main.jsp');">服务</a></li>
			      		<li><a href="#" onclick="javascript:addTab('mainframe_1_2', '方法', '<%=request.getContextPath()%>/view/console/method/method_main.jsp');">方法</a></li>
			      		<li><a href="#" onclick="javascript:addTab('mainframe_1_3', '分组', '<%=request.getContextPath()%>/view/console/group/group_main.jsp');">分组</a></li>
			      		<li><a href="#" onclick="javascript:addTab('mainframe_1_4', '客户端', '<%=request.getContextPath()%>/view/console/client/client_main.jsp');">客户端</a></li>
			      		<li><a href="#" onclick="javascript:addTab('mainframe_1_5', '服务端', '<%=request.getContextPath()%>/view/console/server/server_main.jsp');">服务端</a></li>
                   </ul>
                </li>
                <li><a href="#">监控</a>
                	<ul>
						<li><a href="#">服务</a>
							<ul>
		                        <li><a href="#" onclick="javascript:addTab('mainframe_2_1_1', '服务-超时次数', '<%=request.getContextPath()%>/view/monitor/service/overtime/overtime_chart.jsp');">超时次数</a></li>
		                        <li><a href="#" onclick="javascript:addTab('mainframe_2_1_2', '服务-平均响应时间', '<%=request.getContextPath()%>/view/monitor/service/avgtime/avgtime_chart.jsp');">平均响应时间</a></li>
								<li><a href="#" onclick="javascript:addTab('mainframe_2_1_3', '服务-失败次数', '<%=request.getContextPath()%>/view/monitor/service/success/success_chart.jsp');">错误次数</a></li>
							</ul>
						</li>
						<li><a href="#">服务端</a>
							<ul>
		                        <li><a href="#" onclick="javascript:addTab('mainframe_2_2_1', '服务端-超时次数', '<%=request.getContextPath()%>/view/monitor/server/overtime/overtime_chart.jsp');">超时次数</a></li>
		                        <li><a href="#" onclick="javascript:addTab('mainframe_2_2_2', '服务端-平均响应时间', '<%=request.getContextPath()%>/view/monitor/server/avgtime/avgtime_chart.jsp');">平均响应时间</a></li>
								<li><a href="#" onclick="javascript:addTab('mainframe_2_2_3', '服务端-失败次数', '<%=request.getContextPath()%>/view/monitor/server/success/success_chart.jsp');">错误次数</a></li>
							</ul>
						</li>
					</ul>
                </li>
                <%-- <li><a href="#">报警</a>
                    <ul>
			        	<li><a href="#" onclick="javascript:addTab('mainframe_3_1', '服务报警', '<%=request.getContextPath()%>/view/alarm/service/service_alarm_main.jsp');">服务报警</a></li>
		 	 			<li><a href="#" onclick="javascript:addTab('mainframe_3_2', '报警人配置', '<%=request.getContextPath()%>/view/alarm/alarmpeople/alarmpeople_main.jsp');">报警人配置</a></li>
                   </ul>
                </li> --%>
            </ul>
        </div>
		<div id="splitter">
			<div>
				<iframe id="tree-iframe"
					src="<%=request.getContextPath()%>/common/view/menutree.jsp"
					width="100%" height="480px" frameborder="0"></iframe>
			</div>
			<div>
				<div id="tabs">
					<ul>
						<li><a href="#tab-mainframe_home">Home</a></li>
					</ul>
					<div id="tab-mainframe_home">
						<iframe
							src="<%=request.getContextPath()%>/common/view/welcome.jsp"
							width="100%" height="480px" frameborder="0"></iframe>
					</div>
				</div>
			</div>
		</div>
		<div id="dialog" style="width:100%;"></div>
		<div id="dialogChild" style="width:100%;"></div>
	</div>
  </body>
</html>
