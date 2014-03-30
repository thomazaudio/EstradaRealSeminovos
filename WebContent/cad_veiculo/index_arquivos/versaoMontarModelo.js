function bindMarca(formName, comboMarca, comboModelo, valorMarca) 
{
	limpaCombo(comboModelo);
	
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

function bindModelo(formName, comboModelo, valorMarca, valorModelo) {
	var j=0;
	
	limpaCombo(comboModelo);
	eval("document.getElementById(formName)."+comboModelo+".options[0] = new Option('', '')");
	for (i = 0; i < xwm.length; i++)
	{
		if (valorMarca == zwm[i]) {
			j++;
			eval("document.getElementById(formName)."+comboModelo+".options[j] = new Option(xwm[i], ywm[i])");
			//alert(eval("document.getElementById(formName)."+comboModelo+".options.length"));		
			if (eval("document.getElementById(formName)."+comboModelo+".options[j].value") == valorModelo)
				eval("document.getElementById(formName)."+comboModelo+".options[j].selected=true");		
		}
	}
}

/*============================================================*/

function bindMarcaSemVersaoV2(formName, comboMarca, comboModelo, textVersao, comboAnoModeloInicial, comboAnoModeloFinal, valorMarca, valorVersao, valorAnoModeloInicial, valorAnoModeloFinal, anoCorrente) 
{
	form = document.getElementById(formName);
	limpaCombo(comboModelo);
	limpaTexto(textVersao);
	
	eval("document.getElementById(formName)."+comboMarca+".options[0] = new Option('', '')");
	
	bindAnoSemVersaoV2(comboAnoModeloInicial, anoCorrente, valorAnoModeloInicial);
	bindAnoSemVersaoV2(comboAnoModeloFinal, anoCorrente, valorAnoModeloFinal);	
	
	if (document.getElementById(textVersao) != null)
		document.getElementById(textVersao).value = valorVersao;
	
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

function bindModeloSemVersaoV2(formName, comboModelo, textVersao, valorMarca, valorModelo, valorVersao) {
	var j=0;
	var temVersao = false;
	
	limpaCombo(comboModelo);
	limpaTexto(textVersao);
	
	eval("document.getElementById(formName)."+comboModelo+".options[0] = new Option('', '')");
	
	for (i = 0; i < xwm.length; i++)
	{
		if (valorMarca == zwm[i]) {
			j++;
			
			eval("document.getElementById(formName)."+comboModelo+".options[j] = new Option(xwm[i], ywm[i])");
			if (eval("document.getElementById(formName)."+comboModelo+".options[j].value") == valorModelo)
			{
				eval("document.getElementById(formName)."+comboModelo+".options[j].selected=true");	
				temVersao = true;	
			}
		}
	}
	
	if (temVersao)
		if (document.getElementById(textVersao) != null)
			document.getElementById(textVersao).value = valorVersao;
}

function bindAnoSemVersaoV2(comboAno, anoCorrente, valorAno)
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

function limpaTexto(text)
{
	if (document.getElementById(text) != null)
		document.getElementById(text).value = "";
}