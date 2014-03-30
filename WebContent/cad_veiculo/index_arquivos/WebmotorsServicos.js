/*
    idSource - id do source
    id - id do item no source de acordo com o tipo enviado
    tipo - tipo do source do tooltip
        1 - id
        2 - idValorItem        */

var idLiSalvar = "";
var conteudoLiSalvar = "";

function WebmotorsTooltipMessage(idSource, id, tipo) 
{
    WebMotors.ssComum.ssServicos.tooltip.BuscarMensagensTooltip(idSource, id, tipo, WebmotorsTooltipMessage_OnComplete, WebmotorsTooltipMessage_OnError, WebmotorsTooltipMessage_OnTimeOut); 
}

/*     Metodos utilizados pelo servico de verificacao de mensagens e horarios novos        */
function WebmotorsTooltipMessage_OnComplete(result)
{
    window.setTimeout(result, 0);
}

function WebmotorsTooltipMessage_OnTimeOut(arg)
{
	alert("Ocorreu Timeout na busca de mensagens no servidor.");
}

function WebmotorsTooltipMessage_OnError(arg)
{
	alert("Ocorreu erro na busca de mensagens no servidor: " + arg._message);
}


/* ------------------------------------- Salvar Minha Lista ----------------------------------------------- */

//Moto
function WebmotorsInserirAnuncioMinhaListaMoto(anuncio, idIncluir, idSalvo) {
    
    conteudoLiSalvar = document.getElementById(idIncluir).innerHTML;
    idLiSalvar = idIncluir;
    
    document.getElementById(idIncluir).innerHTML = '<a href="javascript:;" alt="Salvando em minha lista">Salvando em minha lista...</a>';
	WebMotors.ssComum.ssServicos.AtualizarMinhaLista.InserirAnuncioMoto(anuncio, idIncluir, idSalvo, WebmotorsInserirAnuncioMinhaLista_OnComplete, WebmotorsInserirAnuncioMinhaLista_OnError, WebmotorsInserirAnuncioMinhaLista_OnTimeOut);
}

//Carro
function WebmotorsInserirAnuncioMinhaListaCarro(anuncio, idIncluir, idSalvo) {

    conteudoLiSalvar = document.getElementById(idIncluir).innerHTML;
    idLiSalvar = idIncluir;
    
    document.getElementById(idIncluir).innerHTML = '<a href="javascript:;" alt="Salvando em minha lista">Salvando em minha lista...</a>';
	WebMotors.ssComum.ssServicos.AtualizarMinhaLista.InserirAnuncioCarro(anuncio, idIncluir, idSalvo, WebmotorsInserirAnuncioMinhaLista_OnComplete, WebmotorsInserirAnuncioMinhaLista_OnError, WebmotorsInserirAnuncioMinhaLista_OnTimeOut);
}

function WebmotorsInserirAnuncioMinhaLista_OnComplete(result)
{
    if (result != null && result.length == 4)
    {
        if (result[0] == "sucesso")
        {
            //Oculta li inclusao na minha lista
            document.getElementById(result[1]).style.display = 'none';
            //Mostra li salvo em minha lista
            document.getElementById(result[2]).style.display = '';

            if (typeof(WebmotorsInserirAnuncioMinhaLista_Pagina) == 'function')
                WebmotorsInserirAnuncioMinhaLista_Pagina("sucesso");
        }
        else
        {
            document.getElementById(result[1]).innerHTML = '<a id="lnkMinhaLista" href="javascript:{WebmotorsInserirAnuncioMinhaListaCarro(\''+
                result[3]+'\',\''+result[1]+'\',\''+result[2]+'\');};" title="Salvar em Minha Lista">Salvar em Minha Lista</a>';
            
            if (result[0] == "limite")
            {
                if (typeof(WebmotorsInserirAnuncioMinhaLista_Pagina) == 'function')
                    WebmotorsInserirAnuncioMinhaLista_Pagina("limite");
            }
            else
            {
                if (typeof(WebmotorsInserirAnuncioMinhaLista_Pagina) == 'function')
                    WebmotorsInserirAnuncioMinhaLista_Pagina("erro");
            }
        }
    }
    else {
        if (typeof (WebmotorsInserirAnuncioMinhaLista_Pagina) == 'function') {
            WebmotorsInserirAnuncioMinhaLista_Pagina("erro");

            if (idLiSalvar != null & idLiSalvar != "" && conteudoLiSalvar != null && conteudoLiSalvar != "")
                document.getElementById(idLiSalvar).innerHTML = conteudoLiSalvar;
        }
    }
}

function WebmotorsInserirAnuncioMinhaLista_OnTimeOut(arg)
{
	alert("Ocorreu Timeout ao tentar inserir o anúncio na sua lista.");
}

function WebmotorsInserirAnuncioMinhaLista_OnError(arg)
{
	alert("Ocorreu erro no momento de inserir o anúncio na sua lista: " + arg._message);
}


/* ------------------------------------- Excluir Minha Lista ----------------------------------------------- */

//Moto
function WebmotorsExcluirAnuncioMinhaListaMoto(anuncio, idIncluir, idSalvo)
{
	WebMotors.ssComum.ssServicos.AtualizarMinhaLista.ExcluirAnuncioMoto(anuncio, idIncluir, idSalvo, WebmotorsExcluirAnuncioMinhaLista_OnComplete, WebmotorsExcluirAnuncioMinhaLista_OnError, WebmotorsExcluirAnuncioMinhaLista_OnTimeOut);
}

//Carro
function WebmotorsExcluirAnuncioMinhaListaCarro(anuncio, idIncluir, idSalvo)
{
	WebMotors.ssComum.ssServicos.AtualizarMinhaLista.ExcluirAnuncioCarro(anuncio, idIncluir, idSalvo, WebmotorsExcluirAnuncioMinhaLista_OnComplete, WebmotorsExcluirAnuncioMinhaLista_OnError, WebmotorsExcluirAnuncioMinhaLista_OnTimeOut);
}

function WebmotorsExcluirAnuncioMinhaLista_OnComplete(result)
{
    if (typeof(WebmotorsInserirAnuncioMinhaLista_Pagina) == 'function')
        WebmotorsExcluirAnuncioMinhaLista_Pagina();
}

function WebmotorsExcluirAnuncioMinhaLista_OnTimeOut(arg)
{
	alert("Ocorreu Timeout ao tentar excluir o anúncio na sua lista.");
}

function WebmotorsExcluirAnuncioMinhaLista_OnError(arg)
{
	alert("Ocorreu erro no momento de excluir o anúncio na sua lista: " + arg._message);
}


/* ------------------------------------- Verificar anuncio jornal ----------------------------------------------- */

function WebmotorsValidarAnuncioJornal(anuncio) {
    if (anuncio != null && anuncio != '' && anuncio != '0') {
        WebMotors.ssComum.ssServicos.InformacaoCarro.BuscarAnuncioJornal(anuncio, WebmotorsValidarAnuncioJornal_OnComplete, WebmotorsValidarAnuncioJornal_OnError, WebmotorsValidarAnuncioJornal_OnTimeOut);
    } else {    
        tb_showThickbox('CodigoJornalErro', '#TB_inline?width=243&amp;height=145&amp;inlineId=modalCodigoJornalErro', false, 'frmUI');
    }
}

function WebmotorsValidarAnuncioJornal_OnComplete(result)
{
    if (result != null && result.length == 2)
    {
        if (result[0] == "true")
            document.location.href = result[1];
        else
		    tb_showThickbox('CodigoJornal', '#TB_inline?width=243&amp;height=145&amp;inlineId=modalCodigoJornal', false, 'frmUI');
    }
    else
        alert("Ocorreu erro no momento de verificar código de anúncio no jornal.");
}

function WebmotorsValidarAnuncioJornal_OnTimeOut(arg)
{
	alert("Ocorreu Timeout no momento de verificar código de anúncio no jornal.");
}

function WebmotorsValidarAnuncioJornal_OnError(arg)
{
	alert("Ocorreu erro no momento de verificar código de anúncio no jornal: " + arg._message);
}

/* ------------------------------------- Gravar Log Anuncio Detalhe aba carro ----------------------------------------------- */

function WebmotorsInserirLogAbaDetalheCarro(anuncio, aba)
{
    if (anuncio != null && 
        anuncio != '' && 
        anuncio != '0' &&
        aba != null && 
        aba != '')
	    WebMotors.ssComum.ssServicos.InformacaoCarro.GravarLogAbaDetalhe(anuncio, aba, WebmotorsInserirLogAbaDetalheCarro_OnComplete, WebmotorsInserirLogAbaDetalheCarro_OnError, WebmotorsInserirLogAbaDetalheCarro_OnTimeOut);
}

function WebmotorsInserirLogAbaDetalheCarro_OnComplete(result)
{
}

function WebmotorsInserirLogAbaDetalheCarro_OnTimeOut(arg)
{
}

function WebmotorsInserirLogAbaDetalheCarro_OnError(arg)
{
}

/* ------------------------------------- Gravar Log Anuncio Detalhe aba moto ----------------------------------------------- */

function WebmotorsInserirLogAbaDetalheMoto(anuncio, aba)
{
    if (anuncio != null && 
        anuncio != '' && 
        anuncio != '0' &&
        aba != null && 
        aba != '')
	    WebMotors.ssComum.ssServicos.InformacaoMoto.GravarLogAbaDetalhe(anuncio, aba, WebmotorsInserirLogAbaDetalheMoto_OnComplete, WebmotorsInserirLogAbaDetalheMoto_OnError, WebmotorsInserirLogAbaDetalheMoto_OnTimeOut);
}

function WebmotorsInserirLogAbaDetalheMoto_OnComplete(result)
{
}

function WebmotorsInserirLogAbaDetalheMoto_OnTimeOut(arg)
{
}

function WebmotorsInserirLogAbaDetalheMoto_OnError(arg)
{
}


if (typeof(Sys) !== "undefined") Sys.Application.notifyScriptLoaded();

