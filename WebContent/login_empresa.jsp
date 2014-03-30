<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">


<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/style980.css" />
<link rel="stylesheet" type="text/css" href="css/style800.css" />
<link rel="stylesheet" type="text/css" href="css/style700.css" />
<link rel="stylesheet" type="text/css" href="css/style600.css" />
<link rel="stylesheet" type="text/css" href="css/style500.css" />
<link rel="stylesheet" type="text/css" href="css/style400.css" />
<link rel="stylesheet" type="text/css"
	href="css/jquery.fancybox-1.3.4.css" media="screen" />
<link rel="stylesheet" href="css/screen.css" />

<script src="js2/jquery.js"></script>
<script src="dist/jquery.validate.js"></script>

<script> function TestaCPF(form) {
 var Soma; 
var Resto;
var strCPF = form.cpf.value;
 Soma = 0; 
if (strCPF == "00000000000") 
	{
	
return false; 
	}
for (i=1; i<=9; i++)
 Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (11 - i); 
Resto = (Soma * 10) % 11;
 if ((Resto == 10) || (Resto == 11)) 
Resto = 0;
 if (Resto != parseInt(strCPF.substring(9, 10)) )
	 {
	 window.alert("O cpf que você digitou é inválido!");
 return false; 
	 }
 Soma = 0;
 for (i = 1; i <= 10; i++) Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (12 - i); Resto = (Soma * 10) % 11; if ((Resto == 10) || (Resto == 11)) Resto = 0;
 if (Resto != parseInt(strCPF.substring(10, 11) ) ) 
{

window.alert("O cpf que você digitou é inválido!");
return false;
}
 
 return true; 
}


 </script>
<script>
	$()
			.ready(
					function() {

						$("#commentForm").validate();

						$("#signupForm")
								.validate(
										{
											rules : {
												cnpj : "required",
												senha : "required",

											},
											messages : {
												cnpj : "Por favor, insira o cnpj.",
												senha : "Por favor, insira a senha.",
											}
										});

					});
</script>

<style type="text/css">
#commentForm {
	width: 500px;
}

#commentForm label {
	width: 250px;
}

#commentForm label.error,#commentForm input.submit {
	margin-left: 253px;
}

#signupForm {
	width: 670px;
}

#signupForm label.error {
	margin-left: 10px;
	width: auto;
	display: inline;
}

#newsletter_topics label.error {
	display: none;
	margin-left: 103px;
}
</style>

</head>
<body>


	<div align="center">
		<form class="cmxform" id="signupForm" method="POST"
			action="ServEmpresa">
			<fieldset>
				<legend>
					<h2>
						<strong>Login</strong>
					</h2>
				</legend>

				<div class="message">
					<p>Por favor, insira o cnpj e senha da empresa para login.</p>


				</div>
				<%
				
				StringBuffer result = (StringBuffer)request.getAttribute("result");
				
				if(result==null)
				result = new StringBuffer();
				out.write(result.toString());
				%>

				<input type="hidden" name="SOLI" value="2">
				<div align="left" class="input_wrapper">
					<label for="cnpj"><span>* </span><strong>Cnpj: </strong></label>
					<p>
						<input size="100" id="cnpj" type="text" class="txb" name="cnpj" />
					</p>
				</div>

				<div align="left" class="input_wrapper">
					<label for="senha"><span>* </span><strong>Senha: </strong></label>
					<p>
						<input size="100" id="senha" type="password" class="txb"
							name="senha" />
					</p>
				</div>




				<div class="input_wrapper"></div>

			</fieldset>
			<p>
				<input class="send" type="submit" value="Logar">
			</p>
		</form>

	</div>
	<canvas>
	
	</canvas>

</body>
</html>