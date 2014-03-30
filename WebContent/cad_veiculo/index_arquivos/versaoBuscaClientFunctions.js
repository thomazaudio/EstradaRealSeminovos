var BuscaV1IFrameObj;
var BuscaV2IFrameObj;
var combosBuscaMontados = true;
function bindMarcaBuscaV1(formName, comboMarca, comboModelo, comboVersao, valorMarca) 
{
	limpaCombo(comboModelo);
	limpaCombo(comboVersao);	
	eval("document.getElementById(formName)."+comboMarca+".options[0] = new Option('', '')");
	for (i=0;i<twm.length;i++)
	{
		eval("document.getElementById(formName)."+comboMarca+".options[i+1] = new Option(twm[i], vwm[i])");
		if (eval("document.getElementById(formName)."+comboMarca+".options[i+1].value") == valorMarca)		
			eval("document.getElementById(formName)."+comboMarca+".options[i+1].selected=true");	
	}
}
function bindModeloBuscaV1(formName, comboModelo, comboVersao, valorMarca, valorModelo) {
	var j=0;
	limpaCombo(comboModelo);
	limpaCombo(comboVersao);
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
function buscaVersaoBuscaV1(formName, comboModelo, comboVersao, valorModelo, valorVersao)
{
	var BuscaV1IFrameDoc;
	form = document.getElementById(formName);
	if (!document.createElement)
		return true;	
	combo = eval("document.getElementById(formName)."+comboModelo);	
	limpaCombo(comboVersao);
	if (valorModelo != '')
	{
		combosBuscaMontados = false;	
		URL = semHTTPS + sitePath + semHTTPSComum + "/pgBuscaPorVersaoBusca/pgBuscaPorVersaoBusca.aspx?formName=" + formName + "&comboVersao=" + comboVersao + "&valorModelo=" + valorModelo + "&valorVersao=" + valorVersao;		
		if (!BuscaV1IFrameObj && document.createElement){
			try {
				var tempIFrame=document.createElement('iframe');
				tempIFrame.setAttribute('id','BuscaV1IFrame');
				tempIFrame.setAttribute('src','blank.htm');
				tempIFrame.style.border='0px';
				tempIFrame.style.width='0px';
				tempIFrame.style.height='0px';
				BuscaV1IFrameObj = document.body.appendChild(tempIFrame);				
				if (document.frames) {
					BuscaV1IFrameObj = document.frames['BuscaV1IFrame'];
				}
			} catch(exception) {
				iframeHTML='<iframe id="BuscaV1IFrame" style="';
				iframeHTML+='border:0px;';
				iframeHTML+='width:0px;';
				iframeHTML+='height:0px;';
				iframeHTML+='"><\/iframe>';
				document.body.innerHTML+=iframeHTML;
				BuscaV1IFrameObj = new Object();
				BuscaV1IFrameObj.document = new Object();
				BuscaV1IFrameObj.document.location = new Object();
				//BuscaV1IFrameObj.document.location.iframe = form('BuscaV1IFrame');
				BuscaV1IFrameObj.document.location.replace = function(location) {
					this.iframe.src = location;
				}
			}
		}
		if (navigator.userAgent.indexOf('Gecko') !=-1 && !BuscaV1IFrameObj.contentDocument) {
			setTimeout('buscaVersaoBuscaV1("'+formName+'")',10);
			return false;
		}
		if (BuscaV1IFrameObj.contentDocument) {
			BuscaV1IFrameDoc = BuscaV1IFrameObj.contentDocument;
		} else if (BuscaV1IFrameObj.contentWindow) {
			BuscaV1IFrameDoc = BuscaV1IFrameObj.contentWindow.document;
		} else if (BuscaV1IFrameObj.document) {
			BuscaV1IFrameDoc = BuscaV1IFrameObj.document;
		} else {
			return true;
		}
		BuscaV1IFrameDoc.location.replace(URL);
		return false;
	}
}
function bindVersaoBuscaV1(comboVersao, valorVersao){
	var array = new Array;	
	array = document.getElementById('BuscaV1IFrame').contentWindow.Versao;	
	limpaCombo(comboVersao);	
	for (i = 0; i < array.length; i++)
	{
		document.getElementById(comboVersao).options[i] = array[i];
		if (array[i].value == valorVersao)
			document.getElementById(comboVersao).options.selectedIndex = i;
	}
	combosBuscaMontados = true;
}
function bindMarcaBuscaV2(formName, comboMarca, comboModelo, comboVersao, comboAnoModeloInicial, comboAnoModeloFinal, valorMarca)
{
	limpaCombo(comboModelo);
	limpaCombo(comboVersao);
	limpaCombo(comboAnoModeloInicial);
	limpaCombo(comboAnoModeloFinal);	
	eval("document.getElementById(formName)."+comboMarca+".options[0] = new Option('', '')");
	for (i=0;i<twm.length;i++)
	{
		eval("document.getElementById(formName)."+comboMarca+".options[i+1] = new Option(twm[i], vwm[i])");
		if (eval("document.getElementById(formName)."+comboMarca+".options[i+1].value") == valorMarca)		
			eval("document.getElementById(formName)."+comboMarca+".options[i+1].selected=true");	
	}
}
function bindModeloBuscaV2(formName, comboModelo, comboVersao, comboAnoModeloInicial, comboAnoModeloFinal, valorMarca, valorModelo) {
	var j=0;
	limpaCombo(comboModelo);
	limpaCombo(comboVersao);
	limpaCombo(comboAnoModeloInicial);
	limpaCombo(comboAnoModeloFinal);
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
function buscaVersaoBuscaV2(formName, comboModelo, comboVersao, comboAnoModeloInicial, comboAnoModeloFinal, valorModelo, valorVersao, valorAnoModeloInicial, valorAnoModeloFinal)
{
	var BuscaV2IFrameDoc;
	form = document.getElementById(formName);
	if (!document.createElement)
		return true;	
	combo = eval("document.getElementById(formName)."+comboModelo);
	limpaCombo(comboVersao);
	limpaCombo(comboAnoModeloInicial);
	limpaCombo(comboAnoModeloFinal);	
	if (valorModelo != '')
	{
		combosBuscaMontados = false;	
		URL = semHTTPS + sitePath + semHTTPSComum + "/pgBuscaPorVersaoBusca/pgBuscaPorVersaoBuscaV2.aspx?formName=" + formName + "&comboVersao=" + comboVersao + "&comboAnoModeloInicial=" + comboAnoModeloInicial + "&comboAnoModeloFinal=" + comboAnoModeloFinal + "&valorModelo=" + valorModelo + "&valorVersao=" + valorVersao + "&valorAnoModeloInicial=" + valorAnoModeloInicial + "&valorAnoModeloFinal=" + valorAnoModeloFinal;		
		if (!BuscaV2IFrameObj && document.createElement) {
			try {
				var tempIFrame=document.createElement('iframe');
				tempIFrame.setAttribute('id','BuscaV2IFrame');
				tempIFrame.style.border='0px';
				tempIFrame.style.width='0px';
				tempIFrame.style.height='0px';
				BuscaV2IFrameObj = document.body.appendChild(tempIFrame);				
				if (document.frames) {
					BuscaIFrameObj = document.frames['VersaoIFrame'];
				}
			} catch(exception) {
				iframeHTML='<iframe id="BuscaV2IFrame" style="';
				iframeHTML+='border:0px;';
				iframeHTML+='width:0px;';
				iframeHTML+='height:0px;';
				iframeHTML+='"><\/iframe>';
				document.body.innerHTML+=iframeHTML;
				BuscaV2IFrameObj = new Object();
				BuscaV2IFrameObj.document = new Object();
				BuscaV2IFrameObj.document.location = new Object();
				//BuscaV2IFrameObj.document.location.iframe = form('BuscaV2IFrame');
				BuscaV2IFrameObj.document.location.replace = function(location) {
					this.iframe.src = location;
				}
			}
		}
		if (navigator.userAgent.indexOf('Gecko') !=-1 && !BuscaV2IFrameObj.contentDocument) {
			setTimeout('buscaVersaoBuscaV2("'+formName+'")',10);
			return false;
		}	
		if (BuscaV2IFrameObj.contentDocument) {
			BuscaV2IFrameDoc = BuscaV2IFrameObj.contentDocument; 
		} else if (BuscaV2IFrameObj.contentWindow) {
			BuscaV2IFrameDoc = BuscaV2IFrameObj.contentWindow.document;
		} else if (BuscaIFrameObj.document) {
			BuscaV2IFrameDoc = BuscaV2IFrameObj.document;
		} else {
			return true;
		}
		BuscaV2IFrameDoc.location.replace(URL);
		return false;
	}
}
function bindVersaoBuscaV2(comboVersao, comboAnoModeloInicial, comboAnoModeloFinal, valorVersao, valorAnoModeloInicial, valorAnoModeloFinal, anoCorrente) {
	var array = new Array;
	array = document.getElementById('BuscaV2IFrame').contentWindow.Versao;	
	limpaCombo(comboVersao);	
	for (i = 0; i < array.length; i++)
	{
		document.getElementById(comboVersao).options[i] = array[i];
		if (array[i].value == valorVersao)
			document.getElementById(comboVersao).options.selectedIndex = i;
	}	
	bindAnoBuscaV2(comboAnoModeloInicial, anoCorrente, valorAnoModeloInicial);
	bindAnoBuscaV2(comboAnoModeloFinal, anoCorrente, valorAnoModeloFinal);	
	combosBuscaMontados = true;
}
function bindAnoBuscaV2(comboAno, anoCorrente, valorAno)
{
	idx = 1;
	if (document.getElementById(comboAno) !=null)
	{
		limpaCombo(comboAno);
		document.getElementById(comboAno).options[0] = new Option('', '');
		for (i = anoCorrente; i >= anoCorrente - 100; i--)
		{
			document.getElementById(comboAno).options[idx] = new Option(i,i);
			idx++;	
			if (i == valorAno)
				document.getElementById(comboAno).value = i;
		}
	}
}
function limpaCombo(combo)
{
	if (document.getElementById(combo) != null)
		document.getElementById(combo).options.length = 0;
}