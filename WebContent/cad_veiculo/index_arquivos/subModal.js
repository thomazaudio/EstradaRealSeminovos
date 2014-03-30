var gPopupMask = null;
var gPopupContainer = null;
var gPopFrame = null;
var gReturnFunc;
var gPopupIsShown = false;
var gDefaultPage = "popup.html";
var gHideSelects = false;
var gReturnVal = null;

var gTabIndexes = new Array();
var gTabbableTags = new Array("A","BUTTON","TEXTAREA","INPUT","IFRAME");	

if (!document.all)
{
	document.onkeypress = keyDownHandler;
}
function initPopUp() {
	theBody = document.getElementsByTagName('BODY')[0];
	popmask = document.createElement('div');
	popmask.id = 'popupMask';
	popcont = document.createElement('div');
	popcont.id = 'popupContainer';
	popcont.innerHTML = '' +
		'<div id="popupInner">' +
			'<iframe src="'+ gDefaultPage +'"  style="width:100%;height:100%;background-color:transparent;" frameborder="0" allowtransparency="true" id="popupFrame" name="popupFrame" width="100%" height="100%"></iframe>' +
		'</div>';
	theBody.appendChild(popmask);
	theBody.appendChild(popcont);
	
	gPopupMask = document.getElementById("popupMask");
	gPopupContainer = document.getElementById("popupContainer");
	gPopFrame = document.getElementById("popupFrame");	
	
	var brsVersion = parseInt(window.navigator.appVersion.charAt(0), 10);
	if (brsVersion <= 6 && window.navigator.userAgent.indexOf("MSIE") > -1) {
		gHideSelects = true;
	}
	
	var elms = document.getElementsByTagName('a');
	for (i = 0; i < elms.length; i++) {
		if (elms[i].className.indexOf("submodal") == 0) { 

			elms[i].onclick = function()
			{

				var width = 400;
				var height = 200;

				params = this.className.split('-');
				if (params.length == 3)
				{
					width = parseInt(params[1]);
					height = parseInt(params[2]);
				}
				showPopWin(this.href,width,height,null); 
				return false;
			}
		}
	}
}


function showPopWin(url, width, height, returnFunc, showCloseBox)
{
    initPopUp();
	gPopupIsShown = true;
	disableTabIndexes();
	gPopupMask.style.display = "block";
	gPopupContainer.style.display = "block";

	centerPopWin(width, height);
	
	gPopupContainer.style.width = width + "px";
	gPopupContainer.style.height = (height) + "px";
	//gPopupContainer.style.
	
	setMaskSize();

	gPopFrame.style.height = (height) + "px";
	
	gPopFrame.src = url;
	
	gReturnFunc = returnFunc;

	if (gHideSelects == true) {
		hideSelectBoxes();
	}
	
}

var gi = 0;

function centerPopWin(width, height) {
	if (gPopupIsShown == true) {
		if (width == null || isNaN(width)) {
			width = gPopupContainer.offsetWidth;
		}
		if (height == null) {
			height = gPopupContainer.offsetHeight;
		}
		
		//var theBody = document.documentElement;
		var theBody = document.getElementsByTagName("BODY")[0];
		theBody.style.overflow = "hidden";
		var scTop = parseInt(getScrollTop(),10);
		var scLeft = parseInt(theBody.scrollLeft,10);
	
		setMaskSize();
		
		var fullHeight = getViewportHeight();
		var fullWidth = getViewportWidth();
		
		gPopupContainer.style.top = (scTop + ((fullHeight - (height)) / 2)) + "px";
		gPopupContainer.style.left =  (scLeft + ((fullWidth - width) / 2)) + "px";
	}
}

addEvent(window, "resize", centerPopWin);
addEvent(window, "scroll", centerPopWin);
window.onscroll = centerPopWin;

function setMaskSize() {
	var theBody = document.getElementsByTagName("BODY")[0];
			
	var fullHeight = getViewportHeight();
	var fullWidth = getViewportWidth();
	
	if (fullHeight > theBody.scrollHeight) {
		popHeight = fullHeight;
	} else {
		popHeight = theBody.scrollHeight;
	}
	
	if (fullWidth > theBody.scrollWidth) {
		popWidth = fullWidth;
	} else {
		popWidth = theBody.scrollWidth;
	}
	
	gPopupMask.style.height = popHeight + "px";
	gPopupMask.style.width = popWidth + "px";
}
function hidePopWin(callReturnFunc) {
	gPopupIsShown = false;
	var theBody = document.getElementsByTagName("BODY")[0];
	theBody.style.overflow = "";
	restoreTabIndexes();
	if (gPopupMask == null) {
		return;
	}
	gPopupMask.style.display = "none";
	gPopupContainer.style.display = "none";
	if (callReturnFunc == true && gReturnFunc != null)
	{
		gReturnVal = window.frames["popupFrame"].returnVal;
		window.setTimeout('gReturnFunc(gReturnVal);', 1);
	}
	gPopFrame.src = gDefaultPage;
	if (gHideSelects == true) {
		displaySelectBoxes();
	}
}
function keyDownHandler(e) {
    if (gPopupIsShown && e.keyCode == 9)  return false;
}
function disableTabIndexes() {
	if (document.all) {
		var i = 0;
		for (var j = 0; j < gTabbableTags.length; j++) {
			var tagElements = document.getElementsByTagName(gTabbableTags[j]);
			for (var k = 0 ; k < tagElements.length; k++) {
				gTabIndexes[i] = tagElements[k].tabIndex;
				tagElements[k].tabIndex="-1";
				i++;
			}
		}
	}
}
function restoreTabIndexes() {
	if (document.all) {
		var i = 0;
		for (var j = 0; j < gTabbableTags.length; j++) {
			var tagElements = document.getElementsByTagName(gTabbableTags[j]);
			for (var k = 0 ; k < tagElements.length; k++) {
				tagElements[k].tabIndex = gTabIndexes[i];
				tagElements[k].tabEnabled = true;
				i++;
			}
		}
	}
}
function hideSelectBoxes() {
	for(var i = 0; i < document.forms.length; i++) {
		for(var e = 0; e < document.forms[i].length; e++){
			if(document.forms[i].elements[e].tagName == "SELECT") {
				document.forms[i].elements[e].style.visibility="hidden";
			}
		}
	}
}
function displaySelectBoxes() {
	for(var i = 0; i < document.forms.length; i++) {
		for(var e = 0; e < document.forms[i].length; e++){
			if(document.forms[i].elements[e].tagName == "SELECT") {
			document.forms[i].elements[e].style.visibility="visible";
			}
		}
	}
}
function addEvent(obj, evType, fn){
 if (obj.addEventListener){
    obj.addEventListener(evType, fn, false);
    return true;
 } else if (obj.attachEvent){
    var r = obj.attachEvent("on"+evType, fn);
    return r;
 } else {
    return false;
 }
}
function removeEvent(obj, evType, fn, useCapture){
  if (obj.removeEventListener){
    obj.removeEventListener(evType, fn, useCapture);
    return true;
  } else if (obj.detachEvent){
    var r = obj.detachEvent("on"+evType, fn);
    return r;
  } else {
    alert("Handler could not be removed");
  }
}
function getViewportHeight() {
	if (window.innerHeight!=window.undefined) return window.innerHeight;
	if (document.compatMode=='CSS1Compat') return document.documentElement.clientHeight;
	if (document.body) return document.body.clientHeight; 

	return window.undefined; 
}
function getViewportWidth() {
	var offset = 17;
	var width = null;
	if (window.innerWidth!=window.undefined) return window.innerWidth; 
	if (document.compatMode=='CSS1Compat') return document.documentElement.clientWidth; 
	if (document.body) return document.body.clientWidth; 
}
function getScrollTop() {
	if (self.pageYOffset)
	{
		return self.pageYOffset;
	}
	else if (document.documentElement && document.documentElement.scrollTop)
	{
		return document.documentElement.scrollTop;
	}
	else if (document.body)
	{
		return document.body.scrollTop;
	}
}
function getScrollLeft() {
	if (self.pageXOffset)
	{
		return self.pageXOffset;
	}
	else if (document.documentElement && document.documentElement.scrollLeft)
	{
		return document.documentElement.scrollLeft;
	}
	else if (document.body)
	{
		return document.body.scrollLeft;
	}
}
