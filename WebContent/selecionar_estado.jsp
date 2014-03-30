<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
<div align="center" class="message">
<form action="" method="Post">

<div class="message" >
<h4><strong>Já Possue Cadastro?</strong></h4>
<div class="input_wrapper">
<label><span>* </span><strong>Cpf ou Cnpj: </strong></label>
<p><input type="text" class="txb" value="" name="login" /></p>

</div>

<div class="input_wrapper">
<label><span>* </span><strong>Senha: </strong></label>
<p><input type="password" class="txb" value="" name="senha" /></p>
</div>

<div class="input_wrapper">

<p><input type="submit" class="txb" value="Login"  /></p>
</div>

</div>
</form>

<form action="index.jsp" method="POST">
<div class="message" >
<h4><strong>Cadastro de pessoa Física</strong></h4>
<div class="input_wrapper">
<input type="hidden" name="page" value="cadastro_pessoa_fisica.jsp">
<label><span>* </span><strong>Cpf: </strong></label>
<p><input type="text" class="txb" value="" name="cpf" /></p>
</div>
<div class="input_wrapper">

<p><input type="submit" class="txb" value="Próximo"  /></p>
</div>

</div>

</form>
<form action="" method="Post">
<div  class="message">
<h4><strong>Cadastro de pessoa Jurídica</strong></h4>

<div class="input_wrapper">
<label><span>* </span><strong>Cnpj: </strong></label>
<p><input type="text" class="txb" value="" name="nome" /></p>
</div>

<div class="input_wrapper">

<p><input type="submit" class="txb" value="Próximo"  /></p>
</div>

</div>

</form>
</div>
</body>
</html>