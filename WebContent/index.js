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


function checkForm(formId){  
    var params = serializeForm(formId);  
    console.log(params); 
    return true;
}  


function getElements(formId) {    
    var form = document.getElementById(formId);    
    var elements = new Array();  
    console.log(elements);
    var tagElements = form.getElementsByTagName('input');    
    for (var j = 0; j < tagElements.length; j++){  
         elements.push(tagElements[j]);  
  
    }  
   /* var selectElements = form.getElementsByTagName('select');    
    for (var j = 0; j < selectElements.length; j++){  
    	elements.push(selectElements[j]);  
    	
    }  */
    return elements;    
}   
  

function inputSelector(element) {    
  if (element.checked)    
     return [element.name, element.value];    
}    
      
function input(element) {    
    switch (element.type.toLowerCase()) {    
      case 'submit':    
      case 'hidden':    
      case 'password':    
      case 'text':    
        return [element.name, element.value];    
      case 'checkbox':    
      case 'radio':    
        return inputSelector(element);    
    }    
    return false;    
}    
  
function serializeElement(element) {    
    var method = element.tagName.toLowerCase();    
    var parameter = input(element);    
    
    if (parameter) {    
      var key = encodeURIComponent(parameter[0]);    
      if (key.length == 0) return;    
    
      if (parameter[1].constructor != Array)    
        parameter[1] = [parameter[1]];    
          
      var values = parameter[1];    
      var results = [];    
      for (var i=0; i<values.length; i++) {    
        results.push(key + '=' + encodeURIComponent(values[i]));    
      }    
      return results.join('&');    
    }    
 }    
  

function serializeForm(formId) {    
    var elements = getElements(formId);    
    var queryComponents = new Array();    
    
    for (var i = 0; i < elements.length; i++) {    
      var queryComponent = serializeElement(elements[i]);    
      if (queryComponent)    
        queryComponents.push(queryComponent);    
    }    
    
    return queryComponents.join('&');  
}    