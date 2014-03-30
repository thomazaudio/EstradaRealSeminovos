var UsadosV1IFrameObj;
var UsadosV2IFrameObj;
var UsadosV3IFrameObj;

function bindMarcaUsadosV1(formName, comboMarca, comboModelo, comboVersao, comboAnoModelo, comboAnoFabricacao, valorMarca) 
{
	
	limpaCombo(comboModelo);
	limpaCombo(comboVersao);
	limpaCombo(comboAnoModelo);
	limpaCombo(comboAnoFabricacao);
	
	eval("document.getElementById(formName)."+comboMarca+".options[0] = new Option('', '')");
	
	for (i=0;i<twm.length;i++)
	{
		eval("document.getElementById(formName)."+comboMarca+".options[i+1] = new Option(twm[i], vwm[i])");
		if(valorMarca!='')
		{		
			if (eval("document.getElementById(formName)."+comboMarca+".options[i+1].value") == valorMarca)		
				eval("document.getElementById(formName)."+comboMarca+".options[i+1].selected=true");	
		}
	}
	if(valorMarca=='')
	{		
		eval("document.getElementById(formName)."+comboMarca+".options[0].selected=true");	
	}
}

function bindModeloUsadosV1(formName, comboModelo, comboVersao, comboAnoModelo, comboAnoFabricacao, valorMarca, valorModelo) {
	var j=0;

	limpaCombo(comboModelo);
	limpaCombo(comboVersao);
	limpaCombo(comboAnoModelo);
	limpaCombo(comboAnoFabricacao);
	eval("document.getElementById(formName)."+comboModelo+".options[0] = new Option('', '')");
	
	for (i = 0; i < xwm.length; i++)
	{
		if (valorMarca == zwm[i]) {
			j++;
			eval("document.getElementById(formName)."+comboModelo+".options[j] = new Option(xwm[i], ywm[i])");
			if (eval("document.getElementById(formName)."+comboModelo+".options[j].value") == valorModelo)
				eval("document.getElementById(formName)."+comboModelo+".options[j].selected=true");				
		}
	}
}

function buscaVersaoUsadosV1(formName, comboModelo, comboVersao, comboAnoModelo, comboAnoFabricacao, valorModelo, valorVersao, valorAnoModelo, valorAnoFabricacao)
{
	var UsadosV1IFrameDoc;
	form = document.getElementById(formName);
	if (!document.createElement) 
		return true;
	
	combo = eval("document.getElementById(formName)."+comboModelo);
	
	limpaCombo(comboVersao);
	limpaCombo(comboAnoModelo);
	limpaCombo(comboAnoFabricacao);
	
	// verifica se existe modelo selecionado
	if (valorModelo != '')
	{		
		URL = semHTTPS + sitePath + semHTTPSComum+"/pgBuscaPorVersaoUsado/pgBuscaPorVersaoUsado.aspx?formName=" + formName + "&comboVersao=" + comboVersao + "&comboAnoModelo=" + comboAnoModelo + "&comboAnoFabricacao=" + comboAnoFabricacao + "&valorModelo=" + valorModelo + "&valorVersao=" + valorVersao + "&valorAnoModelo=" + valorAnoModelo + "&valorAnoFabricacao=" + valorAnoFabricacao;
		
		if (!UsadosV1IFrameObj && document.createElement) {
			try {
				var tempIFrame=document.createElement('iframe');
				tempIFrame.setAttribute('id','UsadosV1IFrame');
				tempIFrame.setAttribute('src','blank.htm');
				tempIFrame.style.border='0px';
				tempIFrame.style.width='0px';
				tempIFrame.style.height='0px';
				UsadosV1IFrameObj = document.body.appendChild(tempIFrame);
				
				if (document.frames) {
					UsadosV1IFrameObj = document.frames['UsadosV1IFrame'];
				}
			} catch(exception) {
				iframeHTML='<iframe id="UsadosV1IFrame" style="';
				iframeHTML+='border:0px;';
				iframeHTML+='width:0px;';
				iframeHTML+='height:0px;';
				iframeHTML+='"><\/iframe>';
				document.body.innerHTML+=iframeHTML;
				UsadosV1IFrameObj = new Object();
				UsadosV1IFrameObj.document = new Object();
				UsadosV1IFrameObj.document.location = new Object();
				//UsadosV1IFrameObj.document.location.iframe = form('UsadosV1IFrame');
				UsadosV1IFrameObj.document.location.replace = function(location) {
					this.iframe.src = location;
				}
			}
		}
		if (navigator.userAgent.indexOf('Gecko') !=-1 && !UsadosV1IFrameObj.contentDocument) {
			// we have to give NS6 a fraction of a second
			// to recognize the new IFrame
			setTimeout('buscaVersaoUsadosV1("'+formName+'")',10);
			return false;
		}
	
		if (UsadosV1IFrameObj.contentDocument) {
			// For NS6
			UsadosV1IFrameDoc = UsadosV1IFrameObj.contentDocument; 
		} else if (UsadosV1IFrameObj.contentWindow) {
			// For IE5.5 and IE6
			UsadosV1IFrameDoc = UsadosV1IFrameObj.contentWindow.document;
		} else if (UsadosV1IFrameObj.document) {
			// For IE5
			UsadosV1IFrameDoc = UsadosV1IFrameObj.document;
		} else {
			return true;
		}
		UsadosV1IFrameDoc.location.replace(URL);
		return false;
	}
}

function bindVersaoUsadosV1(comboVersao, comboAnoModelo, comboAnoFabricacao, valorVersao, valorAnoModelo, valorAnoFabricacao) {
	var array = new Array;
	
	array = document.getElementById('UsadosV1IFrame').contentWindow.AnoMod;
	
	limpaCombo(comboAnoModelo);
	
	for (i = 0; i < array.length; i++)
	{
		document.getElementById(comboAnoModelo).options[i] = array[i];
		if (array[i].value == valorAnoModelo)
			document.getElementById(comboAnoModelo).options.selectedIndex = i;
	}
	
	bindAnoModUsadosV1(comboVersao, valorAnoModelo, valorVersao);
	
	if (valorAnoModelo != "")
		bindAnoFabUsadosV1(comboAnoFabricacao, valorAnoModelo, valorAnoFabricacao);
}

function bindAnoModUsadosV1(comboVersao, valorAnoModelo, valorVersao) {
	var array = new Array;
	
	document.getElementById('UsadosV1IFrame').contentWindow.carregaCombos(valorAnoModelo);
	array = document.getElementById('UsadosV1IFrame').contentWindow.Versao;
	
	if (document.getElementById(comboVersao) !=null)
	{
		limpaCombo(comboVersao);
		
		for (i = 0; i < array.length; i++)
		{
			document.getElementById(comboVersao).options[i] = array[i];
			if (array[i].value == valorVersao)
				document.getElementById(comboVersao).options.selectedIndex = i;
		}
	}
}
function bindAnoFabUsadosV1(comboAnoFabricacao, valorAnoModelo, valorAnoFabricacao) 
{
	limpaCombo(comboAnoFabricacao);
	
	if (!(valorAnoModelo == "0" || valorAnoModelo == ""))
	{
		document.getElementById(comboAnoFabricacao).options[0] = new Option('', '');
		document.getElementById(comboAnoFabricacao).options[1] = new Option(valorAnoModelo, valorAnoModelo);
		document.getElementById(comboAnoFabricacao).options[2] = new Option(valorAnoModelo - 1, valorAnoModelo - 1);
		
		for (i = 0; i < document.getElementById(comboAnoFabricacao).length; i++)
		{
			if (document.getElementById(comboAnoFabricacao).options[i].value == valorAnoFabricacao)
			{
				document.getElementById(comboAnoFabricacao).options.selectedIndex = i;
				break;
			}
		}
	}	
}

/*======================================================================*/

function bindMarcaUsadosV2(formName, comboMarca, comboModelo, comboVersao, comboAnoModeloDe, comboAnoModeloAte, valorMarca) 
{
	limpaCombo(comboModelo);
	limpaCombo(comboVersao);
	limpaCombo(comboAnoModeloDe);
	limpaCombo(comboAnoModeloAte);
	eval("document.getElementById(formName)."+comboMarca+".options[0] = new Option('', '')");	
	for (i=0;i<twm.length;i++)
	{
		eval("document.getElementById(formName)."+comboMarca+".options[i+1] = new Option(twm[i], vwm[i])");
		if (valorMarca!='')
		{	
			if (eval("document.getElementById(formName)."+comboMarca+".options[i+1].value") == valorMarca)		
				eval("document.getElementById(formName)."+comboMarca+".options[i+1].selected=true");	
		}
	}
	if (valorMarca=='')
		eval("document.getElementById(formName)."+comboMarca+".options[0].selected=true");	
}

function bindModeloUsadosV2(formName, comboModelo, comboVersao, comboAnoModeloDe, comboAnoModeloAte, valorMarca, valorModelo) {
	var j=0;
	
	limpaCombo(comboModelo);
	limpaCombo(comboVersao);
	limpaCombo(comboAnoModeloDe);
	limpaCombo(comboAnoModeloAte);
	eval("document.getElementById(formName)."+comboModelo+".options[0] = new Option('', '')");
	
	for (i = 0; i < xwm.length; i++)
	{
		if (valorMarca == zwm[i]) {
			j++;
			eval("document.getElementById(formName)."+comboModelo+".options[j] = new Option(xwm[i], ywm[i])");
			if (eval("document.getElementById(formName)."+comboModelo+".options[j].value") == valorModelo)
				eval("document.getElementById(formName)."+comboModelo+".options[j].selected=true");		
		}
	}
}

function buscaVersaoUsadosV2(formName, comboModelo, comboVersao, comboAnoModeloDe, comboAnoModeloAte, valorModelo, valorVersao, valorAnoModeloDe, valorAnoModeloAte)
{
	var UsadosV2IFrameDoc;
	form = document.getElementById(formName);
	if (!document.createElement) 
		return true;
	
	limpaCombo(comboVersao);
	limpaCombo(comboAnoModeloDe);
	limpaCombo(comboAnoModeloAte);
	  
	combo = eval("document.getElementById(formName)."+comboModelo);
	
	// verifica se existe modelo selecionado
	if (valorModelo != "")
	{	
		URL = semHTTPS + sitePath + semHTTPSComum + "/pgBuscaPorVersaoUsado/pgBuscaPorVersaoUsadoV2.aspx?formName=" + formName + "&comboVersao=" + comboVersao + "&comboAnoModeloDe=" + comboAnoModeloDe + "&comboAnoModeloAte=" + comboAnoModeloAte + "&valorModelo=" + valorModelo + "&valorVersao=" + valorVersao + "&valorAnoModeloDe=" + valorAnoModeloDe + "&valorAnoModeloAte=" + valorAnoModeloAte;
		
		if (!UsadosV2IFrameObj && document.createElement) {
			try {
				var tempIFrame=document.createElement('iframe');
				tempIFrame.setAttribute('id','UsadosV2IFrame');
				tempIFrame.setAttribute('src','blank.htm');
				tempIFrame.style.border='0px';
				tempIFrame.style.width='0px';
				tempIFrame.style.height='0px';
				UsadosV2IFrameObj = document.body.appendChild(tempIFrame);
				
				if (document.frames) {
					UsadosV2IFrameObj = document.frames['UsadosV2IFrame'];
				}
			} catch(exception) {
				iframeHTML='<iframe id="UsadosV2IFrame" style="';
				iframeHTML+='border:0px;';
				iframeHTML+='width:0px;';
				iframeHTML+='height:0px;';
				iframeHTML+='"><\/iframe>';
				document.body.innerHTML+=iframeHTML;
				UsadosV2IFrameObj = new Object();
				UsadosV2IFrameObj.document = new Object();
				UsadosV2IFrameObj.document.location = new Object();
				//UsadosV2IFrameObj.document.location.iframe = form('UsadosV2IFrame');
				UsadosV2IFrameObj.document.location.replace = function(location) {
					this.iframe.src = location;
				}
			}
		}
		if (navigator.userAgent.indexOf('Gecko') !=-1 && !UsadosV2IFrameObj.contentDocument) {
			// we have to give NS6 a fraction of a second
			// to recognize the new IFrame
			setTimeout('buscaVersaoUsadosV2("'+formName+'")',10);
			return false;
		}
		
		if (UsadosV2IFrameObj.contentDocument) {
			// For NS6
			UsadosV2IFrameDoc = UsadosV2IFrameObj.contentDocument; 
		} else if (UsadosV2IFrameObj.contentWindow) {
			// For IE5.5 and IE6
			UsadosV2IFrameDoc = UsadosV2IFrameObj.contentWindow.document;
		} else if (UsadosV2IFrameObj.document) {
			// For IE5
			UsadosV2IFrameDoc = UsadosV2IFrameObj.document;
		} else {
			return true;
		}
		UsadosV2IFrameDoc.location.replace(URL);
		return false;
	}
}

function bindVersaoUsadosV2(comboVersao, comboAnoModeloDe, comboAnoModeloAte, valorVersao, valorAnoModeloDe, valorAnoModeloAte) {
	var array = new Array;
	
	array = document.getElementById('UsadosV2IFrame').contentWindow.Versao;
	limpaCombo(comboVersao);
	
	for (i = 0; i < array.length; i++)
	{
		document.getElementById(comboVersao).options[i] = array[i];
		if (array[i].value == valorVersao)
			document.getElementById(comboVersao).options.selectedIndex = i;
	}
	
	bindAnoModUsadosV2(comboAnoModeloDe, valorVersao, valorAnoModeloDe);
	bindAnoModUsadosV2(comboAnoModeloAte, valorVersao, valorAnoModeloAte);
}

function bindAnoModUsadosV2(comboAnoModelo, valorVersao, valorAnoModelo) {
	var array = new Array;
	
	document.getElementById('UsadosV2IFrame').contentWindow.carregaCombos(valorVersao);
	array = document.getElementById('UsadosV2IFrame').contentWindow.AnoMod;
	
	if (document.getElementById(comboAnoModelo) !=null)
	{
		limpaCombo(comboAnoModelo);
		
		for (i = 0; i < array.length; i++)
		{
			document.getElementById(comboAnoModelo).options[i] = array[i];
			if (array[i].value == valorAnoModelo)
				document.getElementById(comboAnoModelo).options.selectedIndex = i;
		}
	}
}

/*======================================================================*/

function bindMarcaUsadosV3(formName, comboMarca, comboModelo, comboVersao, comboAnoModelo, valorMarca) 
{    
	limpaCombo(comboModelo);
	limpaCombo(comboVersao);
	limpaCombo(comboAnoModelo);
	eval("document.getElementById(formName)."+comboMarca+".options[0] = new Option('', '')");	
	for (i=0;i<twm.length;i++)
	{
	
		eval("document.getElementById(formName)."+comboMarca+".options[i+1] = new Option(twm[i], vwm[i])");
		if (valorMarca!='')
		{
			if (eval("document.getElementById(formName)."+comboMarca+".options[i+1].value") == valorMarca)
				eval("document.getElementById(formName)."+comboMarca+".options[i+1].selected=true");
		}
	}
	if (valorMarca=='')
		eval("document.getElementById(formName)."+comboMarca+".options[0].selected=true");
		
}

function bindModeloUsadosV3(formName, comboModelo, comboVersao, comboAnoModelo, valorMarca, valorModelo) {
	var j=0;

	limpaCombo(comboModelo);
	limpaCombo(comboVersao);
	limpaCombo(comboAnoModelo);
	
	eval("document.getElementById(formName)."+comboModelo+".options[0] = new Option('', '')");
	
	for (i = 0; i < xwm.length; i++)
	{
		if (valorMarca == zwm[i]) {
			j++;
			eval("document.getElementById(formName)."+comboModelo+".options[j] = new Option(xwm[i], ywm[i])");
			if (eval("document.getElementById(formName)."+comboModelo+".options[j].value") == valorModelo)
				eval("document.getElementById(formName)."+comboModelo+".options[j].selected=true");	
		}
	}
}

function buscaVersaoUsadosV3(formName, comboModelo, comboVersao, comboAnoModelo, valorModelo, valorVersao, valorAnoModelo)
{
	var UsadosV3IFrameDoc;
	form = document.getElementById(formName);
	if (!document.createElement) 
		return true;
	
	limpaCombo(comboVersao);
	limpaCombo(comboAnoModelo);

	combo = eval("document.getElementById(formName)."+comboModelo);
	
	// verifica se existe modelo selecionado
	if (valorModelo != "")
	{	
		URL = semHTTPS + sitePath + semHTTPSComum+"/pgBuscaPorVersaoUsado/pgBuscaPorVersaoUsadoV3.aspx?formName=" + formName + "&comboVersao=" + comboVersao + "&comboAnoModelo=" + comboAnoModelo + "&valorModelo=" + valorModelo + "&valorVersao=" + valorVersao + "&valorAnoModelo=" + valorAnoModelo;
		
		if (!UsadosV3IFrameObj && document.createElement) {
			try {
				var tempIFrame=document.createElement('iframe');
				tempIFrame.setAttribute('id','UsadosV3IFrame');
				tempIFrame.setAttribute('src','blank.htm');
				tempIFrame.style.border='0px';
				tempIFrame.style.width='0px';
				tempIFrame.style.height='0px';
				UsadosV3IFrameObj = document.body.appendChild(tempIFrame);
				
				if (document.frames) {
					UsadosV3IFrameObj = document.frames['UsadosV3IFrame'];
				}
			} catch(exception) {
				iframeHTML='<iframe id="UsadosV3IFrame" style="';
				iframeHTML+='border:0px;';
				iframeHTML+='width:0px;';
				iframeHTML+='height:0px;';
				iframeHTML+='"><\/iframe>';
				document.body.innerHTML+=iframeHTML;
				UsadosV3IFrameObj = new Object();
				UsadosV3IFrameObj.document = new Object();
				UsadosV3IFrameObj.document.location = new Object();
				//UsadosV3IFrameObj.document.location.iframe = form('UsadosV3IFrame');
				UsadosV3IFrameObj.document.location.replace = function(location) {
					this.iframe.src = location;
				}
			}
		}
		if (navigator.userAgent.indexOf('Gecko') !=-1 && !UsadosV3IFrameObj.contentDocument) {
			// we have to give NS6 a fraction of a second
			// to recognize the new IFrame
			setTimeout('buscaVersaoUsadosV3("'+formName+'")',10);
			return false;
		}
		
		if (UsadosV3IFrameObj.contentDocument) {
			// For NS6
			UsadosV3IFrameDoc = UsadosV3IFrameObj.contentDocument; 
		} else if (UsadosV3IFrameObj.contentWindow) {
			// For IE5.5 and IE6
			UsadosV3IFrameDoc = UsadosV3IFrameObj.contentWindow.document;
		} else if (UsadosV3IFrameObj.document) {
			// For IE5
			UsadosV3IFrameDoc = UsadosV3IFrameObj.document;
		} else {
			return true;
		}
		UsadosV3IFrameDoc.location.replace(URL);
		return false;
	}
}

function bindVersaoUsadosV3(comboVersao, comboAnoModelo, valorVersao, valorAnoModelo) {
	var array = new Array;

	array = document.getElementById('UsadosV3IFrame').contentWindow.AnoMod;
	limpaCombo(comboAnoModelo);
	
	for (i = 0; i < array.length; i++)
	{
		document.getElementById(comboAnoModelo).options[i] = array[i];
		if (array[i].value == valorAnoModelo)
			document.getElementById(comboAnoModelo).options.selectedIndex = i;
	}
	
	bindAnoModUsadosV3(comboVersao, valorAnoModelo, valorVersao);
}

function bindAnoModUsadosV3(comboVersao, valorAnoModelo, valorVersao) {
	var array = new Array;
	
	document.getElementById('UsadosV3IFrame').contentWindow.carregaCombos(valorAnoModelo);
	array = document.getElementById('UsadosV3IFrame').contentWindow.Versao;
	
	if (document.getElementById(comboVersao) !=null)
	{
		limpaCombo(comboVersao);
		
		for (i = 0; i < array.length; i++)
		{
			document.getElementById(comboVersao).options[i] = array[i];
			if (array[i].value == valorVersao)
				document.getElementById(comboVersao).options.selectedIndex = i;
		}
	}
}

/*======================================================================*/

function limpaCombo(combo)
{
	if (document.getElementById(combo) != null)
		document.getElementById(combo).options.length = 0;
}