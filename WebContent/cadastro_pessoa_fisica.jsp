<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title></title>

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
												nome : "required",
												cpf : "required",
												username : {
													required : true,
													minlength : 2
												},
												senha : {
													required : true,
													minlength : 5
												},
												senha2 : {
													required : true,
													minlength : 5,
													equalTo : "#senha"
												},
												
												
													
											
												
												email : {
													required : true,
													email : true
										
													
												},

											},
											messages : {
												nome : "Insira seu nome.",
												cpf : "Insira  seu cpf.",
												username : {
													required : "Please enter a username",
													minlength : "Your username must consist of at least 2 characters"
												},
												
												
												senha : {
													required : "Escolha uma senha.",
													minlength : "A senha deve conter no mínimo 5 caracteres."
												},
												senha2 : {
													required : "Confirme a senha.",
													minlength : "A senha deve conter no mínimo 5 caracteres.",
													equalTo : "As senhas não conferem."
												},
												email : "Insira um email válido.",

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


	<%
		String email = request.getParameter("email");
	    String cpf = request.getParameter("cpf"); 
	    String nome = request.getParameter("nome");
	   
	    if(nome==null)
	    	nome="";
	    
	    if(cpf==null)
	    	cpf="";
	    
		if (email == null)
			email = "";
	%>

	<div align="center">
		<form class="cmxform" id="signupForm" method="POST"
			action="ServPessoa" onsubmit="return TestaCPF(this);" >
			<fieldset>
				<legend>
				<h2>
					<strong>Cadastro de pessoa física</strong></h2>
				</legend>
				<p>Precisamos de algumas informações básicas para começar.</p>
				<p>Os Campos '*' são de preenchimento obrigatório.</p>
				
				<div class="message">
				<%
				
				StringBuffer result = (StringBuffer)request.getAttribute("result");
				
				if(result==null)
				result = new StringBuffer();
				out.write(result.toString());
				%>
				
				</div>
				<input type="hidden" name="SOLI" value="1">
				<div align="left" class="input_wrapper">
					<label for="nome"><span>* </span><strong>Nome: </strong></label>
					<p>
						<input size="100" id="nome" type="text" class="txb" value="<%=nome %>" name="nome" />
					</p>
				</div>

				<div align="left" class="input_wrapper">
					<label><span>* </span><strong>Cpf:</strong></label>
					<p>
						<input size="100" id="cpf" onblur="TestaCPF(this);" type="text" class="txb" name="cpf" value="<%=cpf%>" />
					</p>
				</div>



				<div align="left" class="input_wrapper">
					<label for="email"><span>* </span><strong>Email: </strong></label>
					<p>
						<input  size="100" id="email" type="text" class="txb" name="email"
							value="<%=email%>" />
					</p>
				</div>

				<div align="left" class="input_wrapper">
					<label><span>* </span><strong>Senha: </strong></label>
					<p>
						<input size="100" id="senha" type="password" class="txb" value="" name="senha" />
					</p>
				</div>

				<div align="left" class="input_wrapper">
					<label><span>* </span><strong>Confirmação de
							Senha: </strong></label>
					<p>
						<input size="100" id="senha2" type="password" class="txb" value="" name="senha2" />
					</p>
				</div>
				<div class="input_wrapper"></div>

			</fieldset>
			<p>
				<input type="submit" value="Enviar">
			</p>
		</form>

	</div>

</body>
</html>