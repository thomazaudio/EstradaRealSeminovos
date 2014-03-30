var BuscaMotoV1IFrameObj;
var BuscaMotoV2IFrameObj;
var BuscaMotoMarcaAnoIFrameObj;
var BuscaMotoAnoModeloIFrameObj;
var combosBuscaMontados = true;

function buscaModeloMotoBuscaV1(formName, comboMarca, comboModelo, valorMarca, valorModelo)
{
	var BuscaMotoV1IFrameDoc;
	form = document.getElementById(formName);
	if (!document.createElement)
		return true;	
	combo = eval("document.getElementById(formName)."+comboMarca);	
	limpaComboMoto(comboModelo);
	if (valorMarca != '')
	{
		combosBuscaMontados = false;	
		URL = semHTTPS + sitePath + semHTTPSComum + "/pgBuscaPorModeloMotoBusca/pgBuscaPorModeloMotoBusca.aspx?formName=" + formName + "&comboModelo=" + comboModelo + "&valorMarca=" + valorMarca + "&valorModelo=" + valorModelo;		
		if (!BuscaMotoV1IFrameObj && document.createElement){
			try {
				var tempIFrame=document.createElement('iframe');
				tempIFrame.setAttribute('id','BuscaMotoV1IFrame');
				tempIFrame.setAttribute('src','blank.htm');
				tempIFrame.style.border='0px';
				tempIFrame.style.width='0px';
				tempIFrame.style.height='0px';
				BuscaMotoV1IFrameObj = document.body.appendChild(tempIFrame);				
				if (document.frames) {
					BuscaMotoV1IFrameObj = document.frames['BuscaMotoV1IFrame'];
				}
			} catch(exception) {
				iframeHTML='<iframe id="BuscaMotoV1IFrame" style="';
				iframeHTML+='border:0px;';
				iframeHTML+='width:0px;';
				iframeHTML+='height:0px;';
				iframeHTML+='"><\/iframe>';
				document.body.innerHTML+=iframeHTML;
				BuscaMotoV1IFrameObj = new Object();
				BuscaMotoV1IFrameObj.document = new Object();
				BuscaMotoV1IFrameObj.document.location = new Object();
				//BuscaMotoV1IFrameObj.document.location.iframe = form('BuscaMotoV1IFrame');
				BuscaMotoV1IFrameObj.document.location.replace = function(location) {
					this.iframe.src = location;
				}
			}
		}
		if (navigator.userAgent.indexOf('Gecko') !=-1 && !BuscaMotoV1IFrameObj.contentDocument) {
			setTimeout('buscaModeloMotoBuscaV1("'+formName+'")',10);
			return false;
		}
		if (BuscaMotoV1IFrameObj.contentDocument) {
			BuscaMotoV1IFrameDoc = BuscaMotoV1IFrameObj.contentDocument;
		} else if (BuscaMotoV1IFrameObj.contentWindow) {
			BuscaMotoV1IFrameDoc = BuscaMotoV1IFrameObj.contentWindow.document;
		} else if (BuscaMotoV1IFrameObj.document) {
			BuscaMotoV1IFrameDoc = BuscaMotoV1IFrameObj.document;
		} else {
			return true;
		}
		BuscaMotoV1IFrameDoc.location.replace(URL);
		return false;
	}
}

function bindAnoMarcaApagaModeloMoto(formName, comboAno, comboModelo, valorMarca, valorAno, valorHttp, permitirNovos, permitirUsados)
{
	var BuscaMotoMarcaAnoIFrameDoc;
	form = document.getElementById(formName);
	if (!document.createElement)
		return true;
	limpaComboMoto(comboModelo);
	limpaComboMoto(comboAno);
	if (valorMarca != '')
	{
		if(valorHttp == 'true')
			URL = comHTTPS + sitePath + comHTTPSComum + "/pgBuscaPorAnoMarcaMoto/pgBuscaPorAnoMarcaMoto.aspx?formName=" + formName + "&comboAno=" + comboAno + "&valorMarca=" + valorMarca + "&valorAno=" + valorAno + "&permitirNovos=" + permitirNovos + "&permitirUsados=" + permitirUsados;
		else
			URL = semHTTPS + sitePath + semHTTPSComum + "/pgBuscaPorAnoMarcaMoto/pgBuscaPorAnoMarcaMoto.aspx?formName=" + formName + "&comboAno=" + comboAno + "&valorMarca=" + valorMarca + "&valorAno=" + valorAno + "&permitirNovos=" + permitirNovos + "&permitirUsados=" + permitirUsados;
		if (!BuscaMotoMarcaAnoIFrameObj && document.createElement){
			try {
				var tempIFrame=document.createElement('iframe');
				tempIFrame.setAttribute('id','BuscaMotoMarcaAnoIFrame');
				tempIFrame.setAttribute('src','blank.htm');
				tempIFrame.style.border='0px';
				tempIFrame.style.width='0px';
				tempIFrame.style.height='0px';
				BuscaMotoMarcaAnoIFrameObj = document.body.appendChild(tempIFrame);				
				if (document.frames) {
					BuscaMotoMarcaAnoIFrameObj = document.frames['BuscaMotoMarcaAnoIFrame'];
				}
			} catch(exception) {
				iframeHTML='<iframe id="BuscaMotoMarcaAnoIFrame" style="';
				iframeHTML+='border:0px;';
				iframeHTML+='width:0px;';
				iframeHTML+='height:0px;';
				iframeHTML+='"><\/iframe>';
				document.body.innerHTML+=iframeHTML;
				BuscaMotoMarcaAnoIFrameObj = new Object();
				BuscaMotoMarcaAnoIFrameObj.document = new Object();
				BuscaMotoMarcaAnoIFrameObj.document.location = new Object();
				//BuscaMotoMarcaAnoIFrameObj.document.location.iframe = form('BuscaMotoMarcaAnoIFrameObj');
				BuscaMotoMarcaAnoIFrameObj.document.location.replace = function(location) {
					this.iframe.src = location;
				}
			}
		}
		if (navigator.userAgent.indexOf('Gecko') !=-1 && !BuscaMotoMarcaAnoIFrameObj.contentDocument) {
			setTimeout('bindAnoMarcaApagaModeloMoto("'+formName+'")',10);
			return false;
		}
		if (BuscaMotoMarcaAnoIFrameObj.contentDocument) {
			BuscaMotoMarcaAnoIFrameDoc = BuscaMotoMarcaAnoIFrameObj.contentDocument;
		} else if (BuscaMotoMarcaAnoIFrameObj.contentWindow) {
			BuscaMotoMarcaAnoIFrameDoc = BuscaMotoMarcaAnoIFrameObj.contentWindow.document;
		} else if (BuscaMotoMarcaAnoIFrameObj.document) {
			BuscaMotoMarcaAnoIFrameDoc = BuscaMotoMarcaAnoIFrameObj.document;
		} else {
			return true;
		}
		BuscaMotoMarcaAnoIFrameDoc.location.replace(URL);
		return false;
	}
}

function bindModeloMarcaAnoMoto(formName, comboModelo, valorMarca, valorAno, valorModelo, valorHttp, permitirNovos, permitirUsados)
{
	var BuscaMotoAnoModeloIFrameDoc;
	form = document.getElementById(formName);
	if (!document.createElement)
		return true;
	limpaComboMoto(comboModelo);
	if ((valorMarca != '') && (valorAno != ''))
	{
		if(valorHttp == 'true')
			URL = comHTTPS + sitePath + comHTTPSComum + "/pgBuscaPorModeloMarcaAnoMoto/pgBuscaPorModeloMarcaAnoMoto.aspx?formName=" + formName + "&comboModelo=" + comboModelo + "&valorMarca=" + valorMarca + "&valorAno=" + valorAno + "&valorModelo=" + valorModelo + "&permitirNovos=" + permitirNovos + "&permitirUsados=" + permitirUsados;
		else
			URL = semHTTPS + sitePath + semHTTPSComum + "/pgBuscaPorModeloMarcaAnoMoto/pgBuscaPorModeloMarcaAnoMoto.aspx?formName=" + formName + "&comboModelo=" + comboModelo + "&valorMarca=" + valorMarca + "&valorAno=" + valorAno + "&valorModelo=" + valorModelo + "&permitirNovos=" + permitirNovos + "&permitirUsados=" + permitirUsados;
		if (!BuscaMotoAnoModeloIFrameObj && document.createElement){
			try {
				var tempIFrame=document.createElement('iframe');
				tempIFrame.setAttribute('id','BuscaMotoAnoModeloIFrame');
				tempIFrame.setAttribute('src','blank.htm');
				tempIFrame.style.border='0px';
				tempIFrame.style.width='0px';
				tempIFrame.style.height='0px';
				BuscaMotoAnoModeloIFrameObj = document.body.appendChild(tempIFrame);				
				if (document.frames) {
					BuscaMotoAnoModeloIFrameObj = document.frames['BuscaMotoAnoModeloIFrame'];
				}
			} catch(exception) {
				iframeHTML='<iframe id="BuscaMotoAnoModeloIFrame" style="';
				iframeHTML+='border:0px;';
				iframeHTML+='width:0px;';
				iframeHTML+='height:0px;';
				iframeHTML+='"><\/iframe>';
				document.body.innerHTML+=iframeHTML;
				BuscaMotoAnoModeloIFrameObj = new Object();
				BuscaMotoAnoModeloIFrameObj.document = new Object();
				BuscaMotoAnoModeloIFrameObj.document.location = new Object();
				//BuscaMotoMarcaAnoIFrameObj.document.location.iframe = form('BuscaMotoMarcaAnoIFrameObj');
				BuscaMotoAnoModeloIFrameObj.document.location.replace = function(location) {
					this.iframe.src = location;
				}
			}
		}
		if (navigator.userAgent.indexOf('Gecko') !=-1 && !BuscaMotoAnoModeloIFrameObj.contentDocument) {
			setTimeout('bindModeloMarcaAnoMoto("'+formName+'")',10);
			return false;
		}
		if (BuscaMotoAnoModeloIFrameObj.contentDocument) {
			BuscaMotoAnoModeloIFrameDoc = BuscaMotoAnoModeloIFrameObj.contentDocument;
		} else if (BuscaMotoAnoModeloIFrameObj.contentWindow) {
			BuscaMotoAnoModeloIFrameDoc = BuscaMotoAnoModeloIFrameObj.contentWindow.document;
		} else if (BuscaMotoAnoModeloIFrameObj.document) {
			BuscaMotoAnoModeloIFrameDoc = BuscaMotoAnoModeloIFrameObj.document;
		} else {
			return true;
		}
		BuscaMotoAnoModeloIFrameDoc.location.replace(URL);
		return false;
	}
}

function bindEstiloMoto(formName, comboEstilo, valorEstilo)
{
	limpaComboMoto(comboEstilo);
	for (i = 0; i < amwm.length; i++)
	{
		eval("document.getElementById(formName)."+comboEstilo+".options[i] = new Option(amwm[i], bmwm[i])");
		if (eval("document.getElementById(formName)."+comboEstilo+".options[i].value") == valorEstilo)
			eval("document.getElementById(formName)."+comboEstilo+".options[i].selected=true");		
	}
}

function bindMarcaMoto(formName, comboMarca, valorMarca)
{
	var MarcaSel = true;
	limpaComboMoto(comboMarca);
	for (i = 0; i < tmwm.length; i++)
	{
		eval("document.getElementById(formName)."+comboMarca+".options[i] = new Option(tmwm[i], vmwm[i])");
		if (eval("document.getElementById(formName)."+comboMarca+".options[i].value") == valorMarca) {
			if(MarcaSel) {
				eval("document.getElementById(formName)."+comboMarca+".options[i].selected=true");
				MarcaSel = false;
			}
		}
	}
}

function bindMarcaApagaModeloMoto(formName, comboMarca, comboModelo, valorMarca) 
{
	var MarcaSel = true;
	limpaComboMoto(comboModelo);
	limpaComboMoto(comboMarca);
	for (i = 0; i < tmwm.length; i++)
	{
		eval("document.getElementById(formName)."+comboMarca+".options[i] = new Option(tmwm[i], vmwm[i])");
		if (eval("document.getElementById(formName)."+comboMarca+".options[i].value") == valorMarca) {
			if(MarcaSel) {
				eval("document.getElementById(formName)."+comboMarca+".options[i].selected=true");
				MarcaSel = false;
			}
		}
	}
}

function bindMarcaApagaAnoModeloMoto(formName, comboMarca, comboAno, comboModelo, valorMarca) 
{
	var MarcaSel = true;
	limpaComboMoto(comboModelo);
	limpaComboMoto(comboAno);
	limpaComboMoto(comboMarca);
	for (i = 0; i < tmwm.length; i++)
	{
		eval("document.getElementById(formName)."+comboMarca+".options[i] = new Option(tmwm[i], vmwm[i])");
		if (eval("document.getElementById(formName)."+comboMarca+".options[i].value") == valorMarca) {
			if(MarcaSel) {
				eval("document.getElementById(formName)."+comboMarca+".options[i].selected=true");
				MarcaSel = false;
			}
		}
	}
}

function bindModeloMarcaMoto(formName, comboModelo, valorMarca, valorModelo, PermitirNovos, PermitirUsados) {
	var j=0;
	limpaComboMoto(comboModelo);
	eval("document.getElementById(formName)."+comboModelo+".options[0] = new Option('', '')");
	for (i = 0; i < xmwm.length; i++)
	{
		if (valorMarca == zmwm[i]) {
			j++;
			eval("document.getElementById(formName)."+comboModelo+".options[j] = new Option(xmwm[i], ymwm[i])");
			//alert(eval("document.getElementById(formName)."+comboModelo+".options.length"));		
			if (eval("document.getElementById(formName)."+comboModelo+".options[j].value") == valorModelo)
				eval("document.getElementById(formName)."+comboModelo+".options[j].selected=true");		
		}
	}
}

function bindVersaoMotoBuscaV1(comboModelo, valorModelo){
	var array = new Array;	
	array = document.getElementById('BuscaMotoV1IFrame').contentWindow.Modelo;	
	limpaComboMoto(comboModelo);	
	for (i = 0; i < array.length; i++)
	{
		document.getElementById(comboModelo).options[i] = array[i];
		if (array[i].value == valorModelo)
			document.getElementById(comboModelo).options.selectedIndex = i;
	}
	combosBuscaMontados = true;
}

function setAnoModeloMarcaMoto(comboAno, valorAno){
	var array = new Array;	
	array = document.getElementById('BuscaMotoMarcaAnoIFrame').contentWindow.AnoModelo;	
	limpaComboMoto(comboAno);	
	for (i = 0; i < array.length; i++)
	{
		document.getElementById(comboAno).options[i] = array[i];
		if (array[i].value == valorAno)
			document.getElementById(comboAno).options.selectedIndex = i;
	}
}

function setModeloMarcaAnoMoto(comboModelo, valorModelo){
	var array = new Array;	
	array = document.getElementById('BuscaMotoAnoModeloIFrame').contentWindow.Modelo;	
	limpaComboMoto(comboModelo);	
	for (i = 0; i < array.length; i++)
	{
		document.getElementById(comboModelo).options[i] = array[i];
		if (array[i].value == valorModelo)
			document.getElementById(comboModelo).options.selectedIndex = i;
	}
}

function limpaComboMoto(combo)
{
	if (document.getElementById(combo) != null)
		document.getElementById(combo).options.length = 0;
}