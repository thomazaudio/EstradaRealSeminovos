Type.registerNamespace('WebMotors.ssComum.ssServicos');
WebMotors.ssComum.ssServicos.InformacaoCarro=function() {
WebMotors.ssComum.ssServicos.InformacaoCarro.initializeBase(this);
this._timeout = 0;
this._userContext = null;
this._succeeded = null;
this._failed = null;
}
WebMotors.ssComum.ssServicos.InformacaoCarro.prototype={
FindMarca:function(codMarca,succeededCallback, failedCallback, userContext) {
return this._invoke(WebMotors.ssComum.ssServicos.InformacaoCarro.get_path(), 'FindMarca',true,{codMarca:codMarca},succeededCallback,failedCallback,userContext); },
FindModelo:function(codMarca,codModelo,succeededCallback, failedCallback, userContext) {
return this._invoke(WebMotors.ssComum.ssServicos.InformacaoCarro.get_path(), 'FindModelo',true,{codMarca:codMarca,codModelo:codModelo},succeededCallback,failedCallback,userContext); },
FindVersao:function(codModelo,codVersao,anoModeloDe,anoModeloAte,succeededCallback, failedCallback, userContext) {
return this._invoke(WebMotors.ssComum.ssServicos.InformacaoCarro.get_path(), 'FindVersao',true,{codModelo:codModelo,codVersao:codVersao,anoModeloDe:anoModeloDe,anoModeloAte:anoModeloAte},succeededCallback,failedCallback,userContext); },
FindAnoUsados:function(codModelo,anoModelo,succeededCallback, failedCallback, userContext) {
return this._invoke(WebMotors.ssComum.ssServicos.InformacaoCarro.get_path(), 'FindAnoUsados',true,{codModelo:codModelo,anoModelo:anoModelo},succeededCallback,failedCallback,userContext); },
FindVersaoUsados:function(codModelo,anoModelo,codVersao,succeededCallback, failedCallback, userContext) {
return this._invoke(WebMotors.ssComum.ssServicos.InformacaoCarro.get_path(), 'FindVersaoUsados',true,{codModelo:codModelo,anoModelo:anoModelo,codVersao:codVersao},succeededCallback,failedCallback,userContext); },
BuscarAnuncioJornal:function(codAnuncioJornal,succeededCallback, failedCallback, userContext) {
return this._invoke(WebMotors.ssComum.ssServicos.InformacaoCarro.get_path(), 'BuscarAnuncioJornal',false,{codAnuncioJornal:codAnuncioJornal},succeededCallback,failedCallback,userContext); },
GravarLogAbaDetalhe:function(codAnuncio,aba,succeededCallback, failedCallback, userContext) {
return this._invoke(WebMotors.ssComum.ssServicos.InformacaoCarro.get_path(), 'GravarLogAbaDetalhe',false,{codAnuncio:codAnuncio,aba:aba},succeededCallback,failedCallback,userContext); }}
WebMotors.ssComum.ssServicos.InformacaoCarro.registerClass('WebMotors.ssComum.ssServicos.InformacaoCarro',Sys.Net.WebServiceProxy);
WebMotors.ssComum.ssServicos.InformacaoCarro._staticInstance = new WebMotors.ssComum.ssServicos.InformacaoCarro();
WebMotors.ssComum.ssServicos.InformacaoCarro.set_path = function(value) { WebMotors.ssComum.ssServicos.InformacaoCarro._staticInstance._path = value; }
WebMotors.ssComum.ssServicos.InformacaoCarro.get_path = function() { return WebMotors.ssComum.ssServicos.InformacaoCarro._staticInstance._path; }
WebMotors.ssComum.ssServicos.InformacaoCarro.set_timeout = function(value) { WebMotors.ssComum.ssServicos.InformacaoCarro._staticInstance._timeout = value; }
WebMotors.ssComum.ssServicos.InformacaoCarro.get_timeout = function() { return WebMotors.ssComum.ssServicos.InformacaoCarro._staticInstance._timeout; }
WebMotors.ssComum.ssServicos.InformacaoCarro.set_defaultUserContext = function(value) { WebMotors.ssComum.ssServicos.InformacaoCarro._staticInstance._userContext = value; }
WebMotors.ssComum.ssServicos.InformacaoCarro.get_defaultUserContext = function() { return WebMotors.ssComum.ssServicos.InformacaoCarro._staticInstance._userContext; }
WebMotors.ssComum.ssServicos.InformacaoCarro.set_defaultSucceededCallback = function(value) { WebMotors.ssComum.ssServicos.InformacaoCarro._staticInstance._succeeded = value; }
WebMotors.ssComum.ssServicos.InformacaoCarro.get_defaultSucceededCallback = function() { return WebMotors.ssComum.ssServicos.InformacaoCarro._staticInstance._succeeded; }
WebMotors.ssComum.ssServicos.InformacaoCarro.set_defaultFailedCallback = function(value) { WebMotors.ssComum.ssServicos.InformacaoCarro._staticInstance._failed = value; }
WebMotors.ssComum.ssServicos.InformacaoCarro.get_defaultFailedCallback = function() { return WebMotors.ssComum.ssServicos.InformacaoCarro._staticInstance._failed; }
WebMotors.ssComum.ssServicos.InformacaoCarro.set_path("/Webmotors/ssComum/ssServicos/InformacaoCarro.asmx");
WebMotors.ssComum.ssServicos.InformacaoCarro.FindMarca= function(codMarca,onSuccess,onFailed,userContext) {WebMotors.ssComum.ssServicos.InformacaoCarro._staticInstance.FindMarca(codMarca,onSuccess,onFailed,userContext); }
WebMotors.ssComum.ssServicos.InformacaoCarro.FindModelo= function(codMarca,codModelo,onSuccess,onFailed,userContext) {WebMotors.ssComum.ssServicos.InformacaoCarro._staticInstance.FindModelo(codMarca,codModelo,onSuccess,onFailed,userContext); }
WebMotors.ssComum.ssServicos.InformacaoCarro.FindVersao= function(codModelo,codVersao,anoModeloDe,anoModeloAte,onSuccess,onFailed,userContext) {WebMotors.ssComum.ssServicos.InformacaoCarro._staticInstance.FindVersao(codModelo,codVersao,anoModeloDe,anoModeloAte,onSuccess,onFailed,userContext); }
WebMotors.ssComum.ssServicos.InformacaoCarro.FindAnoUsados= function(codModelo,anoModelo,onSuccess,onFailed,userContext) {WebMotors.ssComum.ssServicos.InformacaoCarro._staticInstance.FindAnoUsados(codModelo,anoModelo,onSuccess,onFailed,userContext); }
WebMotors.ssComum.ssServicos.InformacaoCarro.FindVersaoUsados= function(codModelo,anoModelo,codVersao,onSuccess,onFailed,userContext) {WebMotors.ssComum.ssServicos.InformacaoCarro._staticInstance.FindVersaoUsados(codModelo,anoModelo,codVersao,onSuccess,onFailed,userContext); }
WebMotors.ssComum.ssServicos.InformacaoCarro.BuscarAnuncioJornal= function(codAnuncioJornal,onSuccess,onFailed,userContext) {WebMotors.ssComum.ssServicos.InformacaoCarro._staticInstance.BuscarAnuncioJornal(codAnuncioJornal,onSuccess,onFailed,userContext); }
WebMotors.ssComum.ssServicos.InformacaoCarro.GravarLogAbaDetalhe= function(codAnuncio,aba,onSuccess,onFailed,userContext) {WebMotors.ssComum.ssServicos.InformacaoCarro._staticInstance.GravarLogAbaDetalhe(codAnuncio,aba,onSuccess,onFailed,userContext); }
var gtc = Sys.Net.WebServiceProxy._generateTypedConstructor;
Type.registerNamespace('AjaxControlToolkit');
if (typeof(AjaxControlToolkit.CascadingDropDownNameValue) === 'undefined') {
AjaxControlToolkit.CascadingDropDownNameValue=gtc("AjaxControlToolkit.CascadingDropDownNameValue");
AjaxControlToolkit.CascadingDropDownNameValue.registerClass('AjaxControlToolkit.CascadingDropDownNameValue');
}
