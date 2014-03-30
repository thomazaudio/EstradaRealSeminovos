<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	

	<form name="formProposta" id="formProposta" action="http://www.seminovosbh.com.br/veiculo/sendmensage" method="post">
        <input name="cad" id="cad" value="17182" type="hidden">
         <input name="codigoVeiculo" id="codigoVeiculo" value="739376" type="hidden">
          <div id="formDetalhes1">
              <div class="col-group clearfix">
                  <div class="col-left">
                      <dl>
                        <dt><label for="nome">Nome:</label></dt>
                        <dd><input name="nome" id="nome" onpaste="return false" oncut="return false" oncopy="return false" type="text"></dd>
                      </dl>
                      <dl>
                        <dt><label for="email">E-mail:</label></dt>
                        <dd><input name="email" id="email" onpaste="return false" oncut="return false" oncopy="return false" type="text"></dd>
                      </dl>
                      <dl>
                        <dt><label for="telefone">Telefone:</label></dt>
                        <dd><input name="telefone" id="telefone" onpaste="return false" oncut="return false" oncopy="return false" type="text"></dd>
                      </dl>
                      <dl>
                        <dd><label><input checked="checked" name="copia" id="copia" type="checkbox">Envie-me uma cópia da proposta</label></dd>
                      </dl>
                  </div>
                  <div class="col-right">
                      <dl class="box_textarea">
                        <dt><label for="proposta">Proposta:</label></dt>
                        <dd><textarea name="proposta" id="proposta" onpaste="return false" oncut="return false" oncopy="return false"></textarea></dd>
                      </dl>
                  </div>
                                 </div>
	       <input name="enviar" class="btnSetar_site" value="" type="submit">

          </div><!--FECHA FORM DETALHES 1-->
        <!--
          <div id="formDetalhes2">
           <input type="hidden" id="captchacode" name="captchacode" value=""/>
         <strong></strong>
           	<a href="/veiculo/index/codigo/"><img src="/images/atualizarImagem.jpg" border="0" /></a>
           	<br />
            <label for="caracteresCaptcha">Digite os caracteres acima para enviar</label><textarea type="text" name="foo" id="foo"></textarea>

          </div> -->

         </form>

</body>
</html>