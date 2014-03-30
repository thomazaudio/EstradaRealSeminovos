<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Add</title>
	

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<link rel="stylesheet" href="css/screen.css" />

<script src="js2/jquery.js"></script>
<script src="dist/jquery.validate.js"></script>

<script>
//valida o CNPJ digitado
function ValidarCNPJ(ObjCnpj){
        var cnpj = ObjCnpj.value;
        var valida = new Array(6,5,4,3,2,9,8,7,6,5,4,3,2);
        var dig1= new Number;
        var dig2= new Number;
        
        exp = /\.|\-|\//g
        cnpj = cnpj.toString().replace( exp, "" ); 
        var digito = new Number(eval(cnpj.charAt(12)+cnpj.charAt(13)));
                
        for(i = 0; i<valida.length; i++){
                dig1 += (i>0? (cnpj.charAt(i-1)*valida[i]):0);  
                dig2 += cnpj.charAt(i)*valida[i];       
        }
        dig1 = (((dig1%11)<2)? 0:(11-(dig1%11)));
        dig2 = (((dig2%11)<2)? 0:(11-(dig2%11)));
        
        if(((dig1*10)+dig2) != digito)  
        	{
               alert('CNPJ Invalido!');
               return false;
        	}
        else
        	{
        	return true;
        	}
                
}


function txtBoxFormat(strField, sMask, evtKeyPress) {
    var i, nCount, sValue, fldLen, mskLen,bolMask, sCod, nTecla;
   
    if(document.all) { // Internet Explorer
        nTecla = evtKeyPress.keyCode;
    }
    else if(document.layers) { // Nestcape
        nTecla = evtKeyPress.which;
    }
    else if(document.getElementById) { // FireFox
        nTecla = evtKeyPress.which;
    }
   
    if (nTecla != 8) {
   
    sValue = document.getElementById(strField).value;
   
    // Limpa todos os caracteres de formatação que
    // já estiverem no campo.
    sValue = sValue.toString().replace( "-", "" );
    sValue = sValue.toString().replace( "-", "" );
    sValue = sValue.toString().replace( ".", "" );
    sValue = sValue.toString().replace( ".", "" );
    sValue = sValue.toString().replace( "/", "" );
    sValue = sValue.toString().replace( "/", "" );
    sValue = sValue.toString().replace( "(", "" );
    sValue = sValue.toString().replace( "(", "" );
    sValue = sValue.toString().replace( ")", "" );
    sValue = sValue.toString().replace( ")", "" );
    sValue = sValue.toString().replace( " ", "" );
    sValue = sValue.toString().replace( " ", "" );
    sValue = sValue.toString().replace( ":", "" );
    fldLen = sValue.length;
    mskLen = sMask.length;
   
    i = 0;
    nCount = 0;
    sCod = "";
    mskLen = fldLen;
   
    while (i <= mskLen) {
    bolMask = ((sMask.charAt(i) == "-") || (sMask.charAt(i) == ".") || (sMask.charAt(i) == "/"))
    bolMask = bolMask || ((sMask.charAt(i) == "(") || (sMask.charAt(i) == ")") || (sMask.charAt(i) == " "))
    bolMask = bolMask || (sMask.charAt(i) == ":")
   
    if (bolMask) {
    sCod += sMask.charAt(i);
    mskLen++; }
    else {
    sCod += sValue.charAt(nCount);
    nCount++;
    }
   
    i++;
    }
   
    //objForm[strField].value = sCod;
    document.getElementById(strField).value = sCod;
   
    if (nTecla != 8) { // backspace
        if (sMask.charAt(i-1) == "9") { // apenas números...
            return ((nTecla > 47) && (nTecla < 58)); } // números de 0 a 9
        else { // qualquer caracter...
            return true;
        }
    }
    else {
        return true;
    }
    }
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
												cnpj : "required",
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
												
												tel:{
													
													required:true,
													number:true
													
												}

											},
											messages : {
												nome : "Insira seu nome.",
												cnpj : "Insira  o cnpj da empresa.",
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
												
												tel:{
													required:"Por favor, insira um telefone para contato",
													number:"Por favor, insira um telefone válido "
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
	    String cnpj= request.getParameter("cnpj");
	    String nome = request.getParameter("nome");
	    String tel = request.getParameter("tel");
	    
	    
	    
	    if(nome==null)
	    	nome="";
	    
	    if(cnpj==null)
	    	cnpj="";

		if (email == null)
			email = "";
		
		if(tel==null)
			tel="";
	%>

	<div align="center">
		<form class="cmxform" id="signupForm" method="POST"
			action="ServEmpresa" onsubmit="return ValidarCNPJ(signupForm.cnpj);">
			<fieldset>
				<legend>
					<h2><strong>Cadastro de pessoa jurídica</strong></h2>
				</legend>
				<p>Preencha os campos com as informações referentes a concessionária.</p>
				<p>Você poderá editar  e adicionar outras informações no painel do usuário.</p>
					
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
					<label for="nome"><span>* </span><strong>Nome da empresa: </strong></label>
					<p>
						<input value="<%=nome %>" size="100" id="nome" type="text" class="txb" value="" name="nome" />
					</p>
				</div>

				<div  align="left" class="input_wrapper">
					<label><span>* </span><strong>Cnpj:</strong></label>
					<p><input value="<%=cnpj%>" size="100" name="cnpj" type="text" id="cnpj" onBlur="ValidarCNPJ(signupForm.cnpj);"  onKeyPress="return txtBoxFormat(this.id, '99.999.999/9999-99',event);" maxlength="18" /></p>
				</div>



				<div  align="left" class="input_wrapper">
					<label for="email"><span>* </span><strong>Email: </strong></label>
					<p>
						<input  size="100" id="email" type="text" class="txb" name="email"
							value="<%=email%>" />
					</p>
				</div>
				
				
				<div  align="left" class="input_wrapper">
					<label for="tel"><span>* </span><strong>Telefone para contato: </strong></label>
					<p>
						<input  size="100" id="tel" type="text" class="txb" name="tel"
							value="<%=tel%>" />
					</p>
				</div>

				<div  align="left" class="input_wrapper">
					<label><span>* </span><strong>Senha: </strong></label>
					<p>
						<input  size="100" id="senha" type="password" class="txb" value="" name="senha" />
					</p>
				</div>

				<div  align="left" class="input_wrapper">
					<label><span>* </span><strong>Confirmação de
							Senha: </strong></label>
					<p>
						<input  size="100" id="senha2" type="password" class="txb" value="" name="senha2" />
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

