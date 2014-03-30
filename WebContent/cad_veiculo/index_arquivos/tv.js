
var atual = 0;
	var conteudo = '';
	var conteudoAtual = '';
	var parar=false;
	var timeoutId=0;
	function montarPainel(){
	
		conteudo = document.getElementById("dadosTv").value.split("###");
		//conteudo = window.frames[0].document.getElementById("dadosTv").value;
		montarConteudoProximo();
	}
	
	function renderPainel(){
		document.write('<a href="javascript:abrir()" class="linkConteudoAtual"><img id="imgConteudo" width="196" height="119" src="_img/fundo-tv.gif"/></a>');
		document.write(' <div id="textoConteudo"><h3><a href="javascript:abrir()" class="linkConteudoAtual"><span id="tituloConteudo"></span></a></h3><P><a href="javascript:abrir()" class="linkConteudoAtual"><span id="subTituloConteudo"></a></span></P></div>');
		document.write('<div id="bannerTV"></div>');
		document.getElementById('bannerTV').style.visibility='hidden';
		//iniciarSlide();
	}
	
	function startTv(cont){
	    conteudo = cont.split("###")
		setTimeout('pegarConteudo();montarConteudo();iniciarSlide();',2000)

	}

	
	function abrir(){
		if (conteudoAtual[4] == "M"){
		    location.href=conteudoAtual[3];
		}else{
		    window.open(conteudoAtual[0], '_blank');
		}
	}
	

	function setarConteudoPrincipal(){
		try{
			//conteudo = document.getElementById("dadosTv").value.split("#");
			conteudo = window.frames[1].document.getElementById("dadosTv").value.split("###");
		} catch(e) {
			conteudo = '';
		}

		
	}

	function montarConteudo(){
		if (conteudoAtual[4] == "M"){
			document.getElementById('destaqueTv').style.visibility = 'visible';
			document.getElementById('destaqueTvBanner').style.visibility = 'hidden';
			document.getElementById('titualPublicidade').style.display='none';
			document.getElementById('titualEditorial').style.display='block';
			document.getElementById('imgConteudo').style.height = 120;
			document.getElementById('imgConteudo').style.width = 196;
			
			document.getElementById('imgConteudo').src = conteudoAtual[0];
			document.getElementById('tituloConteudo').innerHTML = conteudoAtual[1];
			document.getElementById('subTituloConteudo').innerHTML = conteudoAtual[2];
		}else {
		
		    //alert('1-'+conteudoAtual[0] + ' 2-'+conteudoAtual[1] + ' 3-'+conteudoAtual[2] + ' 4-'+conteudoAtual[3])
		    
			document.getElementById('imgBanner').src = conteudoAtual[1];
			document.getElementById('imgBannerImpressao').src = conteudoAtual[2];
			document.getElementById('imgBanner').style.height = 183;
			document.getElementById('imgBanner').style.width = 217;
			
			document.getElementById('destaqueTv').style.visibility = 'hidden';
			document.getElementById('destaqueTvBanner').style.visibility = 'visible';
			document.getElementById('titualPublicidade').style.display='block';
			document.getElementById('titualEditorial').style.display='none';
		}
	}
	
	function efeito(){
		if(document.all && navigator.userAgent.indexOf('Opera')==-1){
			document.getElementById('destaqueTv').style.filter="blendTrans(duration=0.7)";
			document.getElementById('destaqueTv').filters.blendTrans.apply();
			document.getElementById('destaqueTv').filters.blendTrans.play();
			
			document.getElementById('destaqueTvBanner').style.filter="blendTrans(duration=0.7)";
			document.getElementById('destaqueTvBanner').filters.blendTrans.apply();
			document.getElementById('destaqueTvBanner').filters.blendTrans.play();
			

			montarConteudo();
		}else{
			efeitoMozila();
		}
	}
	
	function setarConteudoAtual(){
		if (atual<conteudo.length-1 )
			atual = atual + 1;
		else
			atual = 0;
		pegarConteudo();
		
	}
	
	function setarConteudoAnterior(){
		if (atual!=0 )
			atual = atual - 1;
		else
			atual = conteudo.length-1;
		pegarConteudo();
	}
	
	function pegarConteudo(){
		conteudoAtual = conteudo[atual].split('|');	
	}
	
	function montarConteudoProximo(){
		clearTimeout(timeoutId);
		setarConteudoAtual();
		efeito();
		iniciarSlide();	
	}
	
	function montarConteudoAnterior(){
		clearTimeout(timeoutId);
		setarConteudoAnterior();
		efeito();
		iniciarSlide();
	}
	
	function parando(){
		if (parar==false){
			document.getElementById('btnPlay').className  = "pararTvPlay";
			parar = true;
			clearTimeout(timeoutId);
		}
		else
		{
			document.getElementById('btnPlay').className  = "pararTv";		
			parar = false;
			iniciarSlide()
		}
	}
	
	function iniciarSlide(){
		if (parar==false) timeoutId=setTimeout('montarConteudoProximo()',6000);
	}

	// funcao exclusiva para o FireFox
	function efeitoMozila(){
		var speed=7;
		var timer=0;
			for(i=100;i>0;i-=2) setTimeout('mudarTransparencia('+i+')', timer++ * speed);
			setTimeout('montarConteudo()', timer * speed);
			for(i=0;i<=100;i+=2) setTimeout('mudarTransparencia('+i+')', timer++ * speed);
	} 
	
	function mudarTransparencia(opacity){
		document.getElementById('destaqueTv').style.opacity=(opacity/101);
		document.getElementById('destaqueTvBanner').style.opacity=(opacity/101);
		document.getElementById('destaqueTv').style.obj.MozOpacity=(opacity/101);
		document.getElementById('destaqueTvBanner').style.obj.MozOpacity=(opacity/101);
		document.getElementById('destaqueTv').style.obj.MozOpacity=(opacity/101);
		document.getElementById('destaqueTvBanner').style.obj.MozOpacity=(opacity/101);
	} 

	function mostrarBanner(conteudo){
		return MaestroAd(conteudo);
	}
	
	function abrePaginaCombo(targ,selObj,restore){ //v3.0
	  	eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
	  	if (restore) selObj.selectedIndex=0;
	}