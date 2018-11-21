function testAjax(){
	var XMLHttp = null;
	if (window.XMLHttpRequest) {
	  XMLHttp = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
	  XMLHttp = new ActiveXObject("Microsoft.XMLHTTP"); //IE5/IE6
	}
	if (XMLHttp !== null) {
	  XMLHttp.open("post", "xml?__requestId=123&t="+new Date(), true);
	  XMLHttp.send();
	  XMLHttp.onreadystatechange = function() {
	    if (XMLHttp.readyState === 4) {
	      if (XMLHttp.status === 200 || XMLHttp.status === 304) {
	        // var XMLDom = XMLHttp.responseXML; 
	        var XMLDoc = XMLHttp.responseText; 
	        var XMLDom = (new DOMParser()).parseFromString(XMLDoc, "text/xml");
	        console.log(XMLDom);
	      }else{
	    	  alert.log("request fail"); 
	      }
	    }
	  };
	}else{
		console.log("not support XMLHttp");
	}
}