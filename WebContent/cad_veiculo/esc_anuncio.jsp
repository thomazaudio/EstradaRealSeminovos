<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title></title>
</head>
<body>
<h2><strong>Escolha de plano</strong></h2>
<div class="message">
<p>Na promoção de lançamento <strong>Estrada Real seminovos</strong> você cadastrada seu veículo gratuitamente!.</p>
</div>

<% 




%>

<img alt="" width="350" src="images/gratuito.png">

<form action="ServAnuncio" method="POST">
<input type="hidden" name="STEP" value="1">
<input type="hidden" name="tipo_plano" value="1">
<input type="hidden" name="tipo_veiculo" value="1">
<input type="image" src="images/bt_proximo.png">
</form>

</body>
</html>