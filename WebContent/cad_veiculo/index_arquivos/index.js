var comHTTPS = "https://";
var semHTTPS = "http://";
var sitePath = "www.webmotors.com.br/";

function ValidaBusca() {

    if (document.getElementById('cboVeiculo').value == "CU" || document.getElementById('cboVeiculo').value == "CN") {
        if (document.getElementById('cboMarca').value == "") {
            alert("Selecione uma marca.");

        }
        if (document.getElementById('cboVeiculo').value == "CN")
            document.FormBusca.action = semHTTPS + sitePath + "Webmotors/ssCompra/pgBuscaNovosResultado/pgBuscaNovosResultado.aspx?marca=" + document.getElementById('cboMarca').value + "&modelo=" + document.getElementById('cboModelo').value + "&descrModelo=" + document.getElementById('cboModelo').options[document.getElementById('cboModelo').selectedIndex].text.replace('&', '%26') + "&precoinicial=" + document.getElementById('cboFaixaPrecoInicial').value + "&precofinal=" + document.getElementById('cboFaixaPrecoFinal').value + "&uf=" + document.getElementById('cboEstado').value;
        else if (document.getElementById('cboVeiculo').value == "CU")
            document.FormBusca.action = semHTTPS + sitePath + "Webmotors/Compra/carrosResultado/carros-resultado.aspx?marca=" + document.getElementById('cboMarca').value + "&modelo=" + document.getElementById('cboModelo').value + "&descrModelo=" + document.getElementById('cboModelo').options[document.getElementById('cboModelo').selectedIndex].text.replace('&', '%26') + "&precoinicial=" + document.getElementById('cboFaixaPrecoInicial').value + "&precofinal=" + document.getElementById('cboFaixaPrecoFinal').value + "&uf=" + document.getElementById('cboEstado').value + "&anoInicial=" + document.getElementById('cboAnoModeloInicial').value + "&anoFinal=" + document.getElementById('cboAnoModeloFinal').value;
        document.FormBusca.submit();
    } else if (document.getElementById('cboVeiculo').value == "MU" || document.getElementById('cboVeiculo').value == "MN") {
        if (document.getElementById('cboMarca').value == "") {
            alert("Selecione uma marca.");
        }
        if (document.getElementById('cboVeiculo').value == "MU")
            document.FormBusca.action = semHTTPS + sitePath + "Webmotors/Compra/motosResultado/motos-resultado.aspx?precode=" + document.getElementById('cboFaixaPrecoInicial').value + "&precoate=" + document.getElementById('cboFaixaPrecoFinal').value + "&anoate=" + document.getElementById('cboAnoModeloFinal').value + "&descrmarca=" + document.getElementById('cboMarca').options[document.getElementById('cboMarca').selectedIndex].text + "&marca=" + document.getElementById('cboMarca').value + "&part=S&anode=" + document.getElementById('cboAnoModeloInicial').value + "&estilo=&avanc=0&uf=" + document.getElementById('cboEstado').value + "&descrestilo=&descrmodelo=" + document.getElementById('cboModelo').options[document.getElementById('cboModelo').selectedIndex].text.replace('&', '%26') + "&revend=S&modelo=" + document.getElementById('cboModelo').value;
        else if (document.getElementById('cboVeiculo').value == "MN")
            document.FormBusca.action = semHTTPS + sitePath + "Webmotors/ssCompra/pgBuscaMotosNovasResultado/pgBuscaMotosNovasResultado.aspx?loja=S&estilo=&precoate=" + document.getElementById('cboFaixaPrecoFinal').value + "&descrmarca=" + document.getElementById('cboMarca').options[document.getElementById('cboMarca').selectedIndex].text + "&marca=" + document.getElementById('cboMarca').value + "&descrmodelo=" + document.getElementById('cboModelo').options[document.getElementById('cboModelo').selectedIndex].text.replace('&', '%26') + "&modelo=" + document.getElementById('cboModelo').value + "&conc=S&avanc=0&uf=" + document.getElementById('cboEstado').value + "&descrestilo=&precode=" + document.getElementById('cboFaixaPrecoInicial').value;

        document.FormBusca.submit();
    }
}

function ValidaBuscaSEO() {

    var vSEO = false;
    if (document.getElementById('cboFaixaPrecoInicial').value == "" &&
    document.getElementById('cboFaixaPrecoFinal').value == "" &&
    document.getElementById('cboEstado').value == "" &&
    document.getElementById('cboAnoModeloInicial').value == "" &&
    document.getElementById('cboAnoModeloFinal').value == "") {
        vSEO = false;
    }

    if (document.getElementById('cboVeiculo').value == "CU" || document.getElementById('cboVeiculo').value == "CN") {
        if (document.getElementById('cboMarca').value == "" && document.getElementById('cboVeiculo').value == "CN") {
            alert("Selecione uma marca.");
        } else {
            if (document.getElementById('cboVeiculo').value == "CN" && vSEO == false) {
                document.FormBusca.action = semHTTPS + sitePath + "Webmotors/ssCompra/pgBuscaNovosResultado/pgBuscaNovosResultado.aspx?marca=" + document.getElementById('cboMarca').value + "&modelo=" + document.getElementById('cboModelo').value + "&descrModelo=" + document.getElementById('cboModelo').options[document.getElementById('cboModelo').selectedIndex].text.replace('&', '%26') + "&precoinicial=" + document.getElementById('cboFaixaPrecoInicial').value + "&precofinal=" + document.getElementById('cboFaixaPrecoFinal').value + "&uf=" + document.getElementById('cboEstado').value;
            } else if (document.getElementById('cboVeiculo').value == "CN" && vSEO == true) {
                if (document.getElementById('cboMarca').value != "" && document.getElementById('cboModelo').value == "") {
                    document.FormBusca.action = semHTTPS + sitePath + "Webmotors/comprar/carros/novos/" + formatStringSEO(document.getElementById('cboMarca').options[document.getElementById('cboMarca').selectedIndex].text) + "/veiculo.wm";
                } else if (document.getElementById('cboMarca').value != "" && document.getElementById('cboModelo').value != "") {
                    document.FormBusca.action = semHTTPS + sitePath + "Webmotors/comprar/carros/novos/" + formatStringSEO(document.getElementById('cboMarca').options[document.getElementById('cboMarca').selectedIndex].text) + "/" + formatStringSEO(document.getElementById('cboModelo').options[document.getElementById('cboModelo').selectedIndex].text) + "/modelo.wm";
                }
            } else if (document.getElementById('cboVeiculo').value == "CU" && vSEO == false) {
                if (document.getElementById('cboModelo').selectedIndex != -1) {
                    document.FormBusca.action = semHTTPS + sitePath + "Webmotors/Compra/carrosResultado/carros-resultado.aspx?marca=" + document.getElementById('cboMarca').value + "&modelo=" + document.getElementById('cboModelo').value + "&descrModelo=" + document.getElementById('cboModelo').options[document.getElementById('cboModelo').selectedIndex].text.replace('&', '%26') + "&precoinicial=" + document.getElementById('cboFaixaPrecoInicial').value + "&precofinal=" + document.getElementById('cboFaixaPrecoFinal').value + "&uf=" + document.getElementById('cboEstado').value + "&anoInicial=" + document.getElementById('cboAnoModeloInicial').value + "&anoFinal=" + document.getElementById('cboAnoModeloFinal').value;
                } else {
                    document.FormBusca.action = semHTTPS + sitePath + "Webmotors/Compra/carrosResultado/carros-resultado.aspx?marca=" + document.getElementById('cboMarca').value + "&precoinicial=" + document.getElementById('cboFaixaPrecoInicial').value + "&precofinal=" + document.getElementById('cboFaixaPrecoFinal').value + "&uf=" + document.getElementById('cboEstado').value + "&anoInicial=" + document.getElementById('cboAnoModeloInicial').value + "&anoFinal=" + document.getElementById('cboAnoModeloFinal').value;
                }
            } else if (document.getElementById('cboVeiculo').value == "CU" && vSEO == true) {
                if (document.getElementById('cboMarca').value != "" && document.getElementById('cboModelo').value == "") {
                    document.FormBusca.action = semHTTPS + sitePath + "Webmotors/comprar/carros/usados/" + formatStringSEO(document.getElementById('cboMarca').options[document.getElementById('cboMarca').selectedIndex].text) + "/veiculo.wm";
                } else if (document.getElementById('cboMarca').value != "" && document.getElementById('cboModelo').value != "") {
                    document.FormBusca.action = semHTTPS + sitePath + "Webmotors/comprar/carros/usados/" + formatStringSEO(document.getElementById('cboMarca').options[document.getElementById('cboMarca').selectedIndex].text) + "/" + formatStringSEO(document.getElementById('cboModelo').options[document.getElementById('cboModelo').selectedIndex].text) + "/modelo.wm";
                } else {
                    document.FormBusca.action = semHTTPS + sitePath + "Webmotors/Compra/carrosResultado/carros-resultado.aspx?marca=" + document.getElementById('cboMarca').value + "&precoinicial=" + document.getElementById('cboFaixaPrecoInicial').value + "&precofinal=" + document.getElementById('cboFaixaPrecoFinal').value + "&uf=" + document.getElementById('cboEstado').value + "&anoInicial=" + document.getElementById('cboAnoModeloInicial').value + "&anoFinal=" + document.getElementById('cboAnoModeloFinal').value;
                }
            }
            document.FormBusca.submit();
        }

    } else if (document.getElementById('cboVeiculo').value == "MU" || document.getElementById('cboVeiculo').value == "MN") {
        if (document.getElementById('cboMarca').value == "" && document.getElementById('cboVeiculo').value == "MN") {
            alert("Selecione uma marca.");
        } else {
            if (document.getElementById('cboVeiculo').value == "MU" && vSEO == false) {

                if (document.getElementById('cboModelo').selectedIndex != -1) {
                    document.FormBusca.action = semHTTPS + sitePath + "Webmotors/Compra/motosResultado/motos-resultado.aspx?precode=" + document.getElementById('cboFaixaPrecoInicial').value + "&precoate=" + document.getElementById('cboFaixaPrecoFinal').value + "&anoate=" + document.getElementById('cboAnoModeloFinal').value + "&descrmarca=" + document.getElementById('cboMarca').options[document.getElementById('cboMarca').selectedIndex].text + "&marca=" + document.getElementById('cboMarca').value + "&anode=" + document.getElementById('cboAnoModeloInicial').value + "&estilo=&avanc=0&uf=" + document.getElementById('cboEstado').value + "&descrestilo=&descrmodelo=" + document.getElementById('cboModelo').options[document.getElementById('cboModelo').selectedIndex].text.replace('&', '%26') + "&revend=S&part=S&modelo=" + document.getElementById('cboModelo').value;
                } else {
                    document.FormBusca.action = semHTTPS + sitePath + "Webmotors/Compra/motosResultado/motos-resultado.aspx?precode=" + document.getElementById('cboFaixaPrecoInicial').value + "&precoate=" + document.getElementById('cboFaixaPrecoFinal').value + "&anoate=" + document.getElementById('cboAnoModeloFinal').value + "&descrmarca=" + document.getElementById('cboMarca').options[document.getElementById('cboMarca').selectedIndex].text + "&marca=" + document.getElementById('cboMarca').value + "&anode=" + document.getElementById('cboAnoModeloInicial').value + "&estilo=&revend=S&part=S&avanc=0&uf=" + document.getElementById('cboEstado').value;
                }

            } else if (document.getElementById('cboVeiculo').value == "MU" && vSEO == true) {
                if (document.getElementById('cboMarca').value != "" && document.getElementById('cboModelo').value == "") {
                    document.FormBusca.action = semHTTPS + sitePath + "Webmotors/comprar/motos/usadas/" + formatStringSEO(document.getElementById('cboMarca').options[document.getElementById('cboMarca').selectedIndex].text) + "/veiculo.wm";
                } else if (document.getElementById('cboMarca').value != "" && document.getElementById('cboModelo').value != "") {
                    document.FormBusca.action = semHTTPS + sitePath + "Webmotors/comprar/motos/usadas/" + formatStringSEO(document.getElementById('cboMarca').options[document.getElementById('cboMarca').selectedIndex].text) + "/" + formatStringSEO(document.getElementById('cboModelo').options[document.getElementById('cboModelo').selectedIndex].text) + "/modelo.wm";
                } else {
                document.FormBusca.action = semHTTPS + sitePath + "Webmotors/Compra/motosResultado/motos-resultado.aspx?precode=" + document.getElementById('cboFaixaPrecoInicial').value + "&precoate=" + document.getElementById('cboFaixaPrecoFinal').value + "&anoate=" + document.getElementById('cboAnoModeloFinal').value + "&descrmarca=" + document.getElementById('cboMarca').options[document.getElementById('cboMarca').selectedIndex].text + "&marca=" + document.getElementById('cboMarca').value + "&anode=" + document.getElementById('cboAnoModeloInicial').value + "&estilo=&revend=S&part=S&avanc=0&uf=" + document.getElementById('cboEstado').value + "&descrestilo=";
                }

            } else if (document.getElementById('cboVeiculo').value == "MN" && vSEO == false) {
            document.FormBusca.action = semHTTPS + sitePath + "Webmotors/ssCompra/pgBuscaMotosNovasResultado/pgBuscaMotosNovasResultado.aspx?loja=S&estilo=&precoate=" + document.getElementById('cboFaixaPrecoFinal').value + "&descrmarca=" + document.getElementById('cboMarca').options[document.getElementById('cboMarca').selectedIndex].text + "&marca=" + document.getElementById('cboMarca').value + "&descrmodelo=" + document.getElementById('cboModelo').options[document.getElementById('cboModelo').selectedIndex].text.replace('&', '%26') + "&modelo=" + document.getElementById('cboModelo').value + "&conc=S&avanc=0&uf=" + document.getElementById('cboEstado').value + "&descrestilo=&precode=" + document.getElementById('cboFaixaPrecoInicial').value;
            } else if (document.getElementById('cboVeiculo').value == "MN" && vSEO == true) {
                if (document.getElementById('cboMarca').value != "" && document.getElementById('cboModelo').value == "") {
                    document.FormBusca.action = semHTTPS + sitePath + "Webmotors/comprar/motos/novas/" + formatStringSEO(document.getElementById('cboMarca').options[document.getElementById('cboMarca').selectedIndex].text) + "/veiculo.wm";
                } else if (document.getElementById('cboMarca').value != "" && document.getElementById('cboModelo').value != "") {
                    document.FormBusca.action = semHTTPS + sitePath + "Webmotors/comprar/motos/novas/" + formatStringSEO(document.getElementById('cboMarca').options[document.getElementById('cboMarca').selectedIndex].text) + "/" + formatStringSEO(document.getElementById('cboModelo').options[document.getElementById('cboModelo').selectedIndex].text) + "/modelo.wm";
                }
            }
            document.FormBusca.submit();
        }
    }
}

function formatStringSEO(texto) {

    text = texto.toLowerCase();
    text = text.replace("ü", "u");
    text = text.replace("ö", "o");
    text = text.replace("ï", "i");
    text = text.replace("ë", "e");
    text = text.replace("ä", "a");
    text = text.replace("û", "u");
    text = text.replace("ô", "o");
    text = text.replace("î", "i");
    text = text.replace("ê", "e");
    text = text.replace("â", "a");
    text = text.replace("ç", "c");
    text = text.replace("õ", "o");
    text = text.replace("ã", "a");
    text = text.replace("ù", "u");
    text = text.replace("ò", "o");
    text = text.replace("ì", "i");
    text = text.replace("è", "e");
    text = text.replace("à", "a");
    text = text.replace("ú", "u");
    text = text.replace("ó", "o");
    text = text.replace("í", "i");
    text = text.replace("é", "e");
    text = text.replace("á", "a");
    text = text.replace(" ", "-");
    text = text.replace("&-", "");
    text = text.replace("&", "");

    if (text.length == (text.lastIndexOf(".") + 1))
        text = text.substring(0, text.length - 1);

    return text;
}

function ValidaBuscaAnuncioExportado() {
    if (document.getElementById('txtAnuncioExportado').value == "") {
        alert("Digite o código de um anúncio no jornal.");
    } else {
        document.FormBuscaAnuncioExportado.action = semHTTPS + sitePath + "/Webmotors/Compra/carrosResultado/carros-resultado.aspx?codExp=" + document.getElementById('txtAnuncioExportado').value;
        document.FormBuscaAnuncioExportado.submit();
    }

}

function selecionaComboModelo() {
	
	window.alert("Chegou");
    limpaCombo('cboModelo');
    if (document.getElementById('cboVeiculo').value == "MU" || document.getElementById('cboVeiculo').value == "MN") {
        bindModeloMarcaMoto('FormBusca', 'cboModelo', document.getElementById('cboMarca').value, document.getElementById('cboModelo').value);
    } else {
        bindModelo('FormBusca', 'cboModelo', document.getElementById('cboMarca').value, document.getElementById('cboModelo').value);
    }
}

function selecionaCombo() {
    if (document.getElementById('cboVeiculo').value == "MU" || document.getElementById('cboVeiculo').value == "MN") {
        limpaCombo('cboModelo');
        bindMarcaMoto('FormBusca', 'cboMarca', '');
    } else {
        limpaCombo('cboModelo');
        bindMarca('FormBusca', 'cboMarca', 'cboModelo', '');
    }
}
function carroMoto() {
    if (document.getElementById('rdoCarro').checked == true) {
        document.getElementById('comprarCarro').style.visibility = 'visible'
        document.getElementById('comprarMoto').style.visibility = 'hidden'
    } else if (document.getElementById('rdoMoto').checked == true) {
        document.getElementById('comprarCarro').style.visibility = 'hidden';
        document.getElementById('comprarMoto').style.visibility = 'visible'
    }
}
function carroMotoAnuncie() {
    if (document.getElementById('carroVender').checked == true) {
        document.getElementById('marcaVender').style.visibility = 'visible';
        document.getElementById('marcaVenderMoto').style.visibility = 'hidden'
    } else if (document.getElementById('motoVender').checked == true) {
        document.getElementById('marcaVender').style.visibility = 'hidden';
        document.getElementById('marcaVenderMoto').style.visibility = 'visible'
    }
}
function carroMotoAvaliacao() {
    if (document.getElementById('carroAvaliar').checked == true) {
        document.getElementById('avaliacaoCarro').style.visibility = 'visible';
        document.getElementById('avaliacaoMoto').style.visibility = 'hidden'
    } else if (document.getElementById('motoAvaliar').checked == true) {
        document.getElementById('avaliacaoCarro').style.visibility = 'hidden';
        document.getElementById('avaliacaoMoto').style.visibility = 'visible'
    }
}
function ValidaAvaliacao() {
    if (document.getElementById('carroAvaliar').checked == true) {
        if (FormAvaliacao.cboMarca.value == "") {
            window.alert("Selecione a marca.");
            return false;
        }
        if (FormAvaliacao.cboModelo.value == "") {
            window.alert("Selecione o modelo.");
            return false;
        }
        document.FormAvaliacao.submit();
    } else if (document.getElementById('motoAvaliar').checked == true) {
        if (document.getElementById('ddlMarca').value == "") {
            window.alert("Selecione a marca.");
            return false;
        }
        document.FormAvaliacaoMoto.submit();
    }
}

function ValidaManutencao() {
    if (!VerificaEmail(document.getElementById('txtEmail').value)) {
        alert("E-mail inválido"); return false;
    }
    if ((document.getElementById('cboDiaNasc').value == "") && (document.getElementById('cboMesNasc').value == "") && (document.getElementById('cboAnoNasc').value == "")) {
        window.alert("Informe sua data de nascimento.");
        return false
    }
    document.FormManutencao.action = "/webmotors/ssManutencao/pgAgendadoCarro/AgendadoCarro.asp?email=" + document.getElementById('txtEmail').value + "&dia=" + document.getElementById('cboDiaNasc').value + "&mes=" + document.getElementById('cboMesNasc').value + "&ano=" + document.getElementById('cboAnoNasc').value;
    return true;
}
function ValidaAnuncie() {
    if (document.getElementById('carroVender').checked == true) {
        if (document.getElementById('cboMarcaAnuncio').value == "") {
            alert("Selecione uma marca.");
            return false;
        } else {
            document.FormAnuncie.action = comHTTPS + sitePath + "webmotors/ssVenda/pgHomeVenda/pgHomeVenda.aspx?CodMarca=" + document.FormAnuncie.cboMarcaAnuncio.value;
        }
    } else if (document.getElementById('motoVender').checked == true) {
        if (document.getElementById('cboMarcaMotoAnuncio').value == "") {
            alert("Selecione uma marca.");
            return false;
        } else {
            document.FormAnuncie.action = comHTTPS + sitePath + "Webmotors/ssVenda/pgHomeVenda/pgHomeVendaMoto.aspx?CodMarca=" + document.FormAnuncie.cboMarcaMotoAnuncio.value;
        }
    }
}

function carregaAno(formName, cboAno, cboAnoFinal, anoInicial, anoFinal, texto) {
    eval("document.getElementById(formName)." + cboAno + ".options[0] = new Option('" + texto + "', '')");
    eval("document.getElementById(formName)." + cboAnoFinal + ".options[0] = new Option('" + texto + "', '')");
    anoFinal = new Date().getFullYear() + 1;
    for (i = 1; i <= anoFinal - anoInicial; i++) {
        eval("document.getElementById(formName)." + cboAno + ".options[i] = new Option(" + (anoFinal - i) + ", " + (anoFinal - i) + ")");
    }
    var AnoFinalComboFinal = new Date().getFullYear() + 2;
    for (i = 1; i <= AnoFinalComboFinal - anoInicial; i++) {
        eval("document.getElementById(formName)." + cboAnoFinal + ".options[i] = new Option(" + (AnoFinalComboFinal - i) + ", " + (AnoFinalComboFinal - i) + ")");
    }
}
function carregaPreco(formName, cboPreco, cboRrecoFinal) {
    eval("document.getElementById(formName)." + cboPreco + ".options[0] = new Option('', '')");
    eval("document.getElementById(formName)." + cboRrecoFinal + ".options[0] = new Option('', '')");
    for (i = 0; i < 16; i++) {
        eval("document.getElementById(formName)." + cboPreco + ".options[i+1] = new Option('" + (i * 2) + ".000', '" + (i * 2) + "000')");
        eval("document.getElementById(formName)." + cboRrecoFinal + ".options[i+1] = new Option('" + (i * 2) + ".000', '" + (i * 2) + "000')");
    }
    for (i = 16; i < 20; i++) {
        eval("document.getElementById(formName)." + cboPreco + ".options[i+1] = new Option('" + ((i - 9) * 5) + ".000', '" + ((i - 9) * 5) + "000')");
        eval("document.getElementById(formName)." + cboRrecoFinal + ".options[i+1] = new Option('" + ((i - 9) * 5) + ".000', '" + ((i - 9) * 5) + "000')");
    }
    for (i = 20; i < 25; i++) {
        eval("document.getElementById(formName)." + cboPreco + ".options[i+1] = new Option('" + ((i - 14) * 10) + ".000', '" + ((i - 14) * 10) + "000')");
        eval("document.getElementById(formName)." + cboRrecoFinal + ".options[i+1] = new Option('" + ((i - 14) * 10) + ".000', '" + ((i - 14) * 10) + "000')");
    }
    eval("document.getElementById(formName)." + cboRrecoFinal + ".options[26] = new Option('999.000', '999000')");
}

function carregaAnoManutencao(formName, cboAno, anoInicial, anoFinal, texto) {
    eval("document.getElementById(formName)." + cboAno + ".options[0] = new Option('" + texto + "', '')");
    for (i = 1; i < anoFinal - anoInicial; i++) {
        eval("document.getElementById(formName)." + cboAno + ".options[i] = new Option(" + (anoFinal - i) + ", " + (anoFinal - i) + ")");
    }
}
function carregaNumeros(formName, cboAno, valorInicial, valorFinal, texto) {
    eval("document.getElementById(formName)." + cboAno + ".options[0] = new Option('" + texto + "', '')");
    for (i = 1; i < valorFinal; i++) {
        if (i < 10)
            eval("document.getElementById(formName)." + cboAno + ".options[i] = new Option('0" + (i) + "', " + (i) + ")");
        else
            eval("document.getElementById(formName)." + cboAno + ".options[i] = new Option('" + (i) + "', " + (i) + ")");
    }
}
function carregaUF(formName, cboUF, texto) {
    aUF = new Array(); aUF = new Array(); aUF[0] = texto; aUF[1] = 'AC'; aUF[2] = 'AL'; aUF[3] = 'AP'; aUF[4] = 'AM'; aUF[5] = 'BA'; aUF[6] = 'CE'; aUF[7] = 'DF'; aUF[8] = 'ES';
    aUF[9] = 'GO'; aUF[10] = 'MA'; aUF[11] = 'MG'; aUF[12] = 'MS'; aUF[13] = 'MT'; aUF[14] = 'PA'; aUF[15] = 'PB'; aUF[16] = 'PE'; aUF[17] = 'PI'; aUF[18] = 'PR';
    aUF[19] = 'RJ'; aUF[20] = 'RN'; aUF[21] = 'RO'; aUF[22] = 'RR'; aUF[23] = 'RS'; aUF[24] = 'SC'; aUF[25] = 'SE'; aUF[26] = 'SP'; aUF[27] = 'TO';
    for (i = 0; i < aUF.length; i++)
        if (i == 0)
        eval("document.getElementById(formName)." + cboUF + ".options[i] = new Option('', '')");
    else
        eval("document.getElementById(formName)." + cboUF + ".options[i] = new Option(aUF[i], aUF[i])");
}
function soNumero(objeto) {
    var keypress = event.keyCode;
    var campo = eval(objeto);
    var caracteres = '01234567890';
    if ((caracteres.indexOf(String.fromCharCode(keypress)) != -1))
        event.returnValue = true;
    else
        event.returnValue = false;
}
function validaCasasDecimal(objeto, maxlength, casas, valor) {
    if ((objeto.value.indexOf(',') != -1)) {
        if ((objeto.value.length - objeto.value.indexOf(',')) > casas + 1)
            objeto.value = objeto.value.substr(0, objeto.value.length - 1);
    }
    else {
        if ((objeto.value.length) >= maxlength - (casas + valor))
            objeto.value = objeto.value.substr(0, (maxlength - (casas + valor)));
    }
}

function buscaOficinas() {
    document.FormManu.action = semHTTPS + sitePath + "Webmotors/ssManutencao/pgResultadoBuscaOficina/pgResultadoBuscaOficina.aspx?uf=" + document.getElementById('cboEstadoManu').value;
    document.FormManu.submit();
}
