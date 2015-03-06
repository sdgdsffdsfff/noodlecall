
function Tree() {
	
	this.divId;
	
	this.tree;
	
	this.buildTree = function(object) {
		
		this.divId = object.divId;
		
		var loadNow = object.loadNow != null ? object.loadNow : true;
		
		var urlArray = [];
	    urlArray.push(object.reqUrl);
	    urlArray.push("?1=1");
	    
	    if (object.paramObject != null) {
	    	
			for (name in object.paramObject) {
				urlArray.push("&");
				urlArray.push(name);
				urlArray.push("=");
				urlArray.push(object.paramObject[name]);
			}
		} 
	    
	    var reqUrl = urlArray.join("");

		this.tree = new YAHOO.widget.TreeView(this.divId);
		
		this.tree.setDynamicLoad(this.loadNodeData, 1);
		
		var tmp_node = {
			label: object.rootName,
			id: object.rootId,
			baseUrl: object.baseUrl,
			reqUrl: reqUrl,
			other: object.rootOther,
			treeType: object.treeType,
			loadNow: loadNow
		};
		
		if (object.treeType === 'checkbox') {
	    	
			new YAHOO.widget.TextNode(tmp_node, this.tree.getRoot(), true);

			if (object.highlightFunc != null) {
				
		    	this.tree.subscribe("highlightEvent", function(node){object.highlightFunc(node.data, node.label, node);});
	    	}
			if (object.dblClickFunc != null) {
				
				this.tree.subscribe("dblClickEvent", function(event){object.dblClickFunc(event.node.data, event.node.label, event.node);});
	    	}
			
	    } else if (object.treeType === 'checkbox-someone') {
	    	
			var textNode = new YAHOO.widget.TextNode(tmp_node, this.tree.getRoot(), true);
			
			textNode.enableHighlight = false;

			if (object.highlightFunc != null) {
				
		    	this.tree.subscribe("highlightEvent", function(node){object.highlightFunc(node.data, node.label, node);});
	    	}
			if (object.dblClickFunc != null) {
				
				this.tree.subscribe("dblClickEvent", function(event){object.dblClickFunc(event.node.data, event.node.label, event.node);});
	    	}
			
	    } else if (object.treeType === 'menu') {
	    	
	    	new YAHOO.widget.MenuNode(tmp_node, this.tree.getRoot(), true);
	    	
	    	if (object.clickFunc != null) {
	    		
		    	this.tree.subscribe("clickEvent", function(event){object.clickFunc(event.node.data, event.node.label, event.node);});
				this.tree.subscribe("enterKeyPressed", function(event){object.clickFunc(event.node.data, event.node.label, event.node);});			
	    	}
			
	    } else {
	    	
	    	new YAHOO.widget.TextNode(tmp_node, this.tree.getRoot(), true);
	    	
	    	if (object.clickFunc != null) {
	    		
		    	this.tree.subscribe("clickEvent", function(event){object.clickFunc(event.node.data, event.node.label, event.node);});
				this.tree.subscribe("enterKeyPressed", function(event){object.clickFunc(event.node.data, event.node.label, event.node);});				
	    	}
	    	if (object.dblClickFunc != null) {
				
				this.tree.subscribe("dblClickEvent", function(event){object.dblClickFunc(event.node.data, event.node.label, event.node);});
	    	}
	    }
		
		this.tree.render();
	}
	
	
	this.loadNodeData = function(node, fnLoadComplete)  {

		var urlArray = [];

		urlArray.push(node.data.baseUrl);
    	urlArray.push(node.data.reqUrl);
    	urlArray.push("?1=1");

	    if(typeof(node.data.id) === "string"){
	 	    urlArray.push("&pid=");
	 	    urlArray.push(encodeURI(node.data.id));
	    }
	    
	    if(typeof(node.data.other) === "string"){
	 	    urlArray.push("&other=");
	 	    urlArray.push(encodeURI(node.data.other));
	    }
	    
	    if(typeof(node.data.pid) === "string"){
	 	    urlArray.push("&ppid=");
	 	    urlArray.push(encodeURI(node.data.pid));
	    }
	    
	    if (node.parent != null) {
	    	if(typeof(node.parent.data.pid) === "string"){
		 	    urlArray.push("&pppid=");
		 	    urlArray.push(encodeURI(node.parent.data.pid));
		    }
	    }
	    
	    if (node.parent.parent != null) {
	    	if(typeof(node.parent.parent.data.pid) === "string"){
		 	    urlArray.push("&ppppid=");
		 	    urlArray.push(encodeURI(node.parent.parent.data.pid));
		    }
	    }
	    
	    if($("#queryInfo") != undefined && $("#queryInfo").val() != ""){
    		urlArray.push("&queryInfo=");
    		urlArray.push($("#queryInfo").val());
    	}
	    
	    var sUrl = urlArray.join("");
	    
	    var callback = {
	    		
	    	cache: false,
	    	
	        success: function(oResponse) {
	        	
	            var oResults = oResponse.responseText;
	            
	            if(typeof(oResults) === "string" && oResults != ""){
	            	
		           	oResults = eval(oResults);
		           	
		           	for (var i=0; i<oResults.length; i++) {
		           		
						var tmp_node = {
							id: oResults[i].id,
							label: oResults[i].label,
							pid: oResults[i].pid,
							other: oResults[i].other,
							reqUrl: oResults[i].url,
							baseUrl: node.data.baseUrl,
							treeType: node.data.treeType,
							loadNow: node.data.loadNow
						};
						
						var loadNow = node.data.loadNow;
						if (oResults[i].load == 'true'
							|| oResults[i].load == 'false') {
							loadNow = oResults[i].load = 'true' ? true : false
						}
						
						if (node.data.treeType === 'checkbox') {
					    	
							new YAHOO.widget.TextNode(tmp_node, node, loadNow);
							
					    } else if (node.data.treeType === 'checkbox-someone') {
					    	
					    	var textNode = new YAHOO.widget.TextNode(tmp_node, node, loadNow);
					    	
					    	if (oResults[i].enableHighlight === 'true') {
					    		
					    		textNode.enableHighlight = true;
					    		
					    	} else {
					    		
					    		textNode.enableHighlight = false;
					    	}
							
					    } else if (node.data.treeType === 'menu') {
					    	
					    	new YAHOO.widget.MenuNode(tmp_node, node, loadNow);
					    								
					    } else {
					    	
					    	new YAHOO.widget.TextNode(tmp_node, node, loadNow);
					    }
			           	
					}
	            }
	            
	            oResponse.argument.fnLoadComplete();
	        },
	        
	        failure: function(oResponse) {
	        	
	            oResponse.argument.fnLoadComplete();
	        },
	        
	        argument: {
	        	
	            "node": node,
	            "fnLoadComplete": fnLoadComplete
	        },
	        
	        timeout: 7000
	    };
	    
	    YAHOO.util.Connect.asyncRequest('GET', sUrl, callback);
	    
	    if (node.data.treeType === 'checkbox') {
	    	
	    	this.tree.setNodesProperty('propagateHighlightUp', true);
			this.tree.setNodesProperty('propagateHighlightDown', true);
			this.tree.subscribe('clickEvent', this.tree.onEventToggleHighlight);
			
	    } else if (node.data.treeType === 'checkbox-someone') {
	    	
	    	this.tree.setNodesProperty('propagateHighlightUp', true);
			this.tree.setNodesProperty('propagateHighlightDown', true);
			this.tree.subscribe('clickEvent', this.tree.onEventToggleHighlight);
			
	    } else if (node.data.treeType === 'menu') {
	    	
	    	var rootNode = this.tree.getNodeByIndex(1);
	    	rootNode.collapseAll();
	    	rootNode.expand();
			
	    } else {
	    	
	    }
	}
	
	this.getNodesByProperty = function(property, value) {
		
		return this.tree.getNodesByProperty(property, value);
	}
	
	this.getNodesBy = function(a) {
		
		return this.tree.getNodesBy(a);
	}
	
	this.getNodesById = function(id) {
		
		return this.tree.getNodesBy(function(node){
			
			return node.data.id == id;
		});
	}
	
	this.isNull = function(nodes) {
		
		return YAHOO.lang.isNull(nodes);
	}
	
	this.setHeight = function(height) {
		
		var treeDiv = document.getElementById(this.divId);
		treeDiv.parentNode.style.height = height + 'px';
	}
}
