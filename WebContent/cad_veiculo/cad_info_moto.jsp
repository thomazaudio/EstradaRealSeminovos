<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="util.Carro"%>
<%@page import="util.Moto"%>
<%@page import="util.Anuncio"%>
<%@page import="util.Item"%>
<%@page import="java.util.ArrayList"%>
<%@page import="util.Usuario"%>

<head>

<!-- Page Title -->
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="../css/style.css" />
	<link rel="stylesheet" type="text/css" href="../css/style980.css" />
	<link rel="stylesheet" type="text/css" href="../css/style800.css" />
	<link rel="stylesheet" type="text/css" href="../css/style700.css" />
	<link rel="stylesheet" type="text/css" href="../css/style600.css" />
	<link rel="stylesheet" type="text/css" href="../css/style500.css" />
	<link rel="stylesheet" type="text/css" href="../css/style400.css" />
	<link rel="stylesheet" type="text/css" href="../css/jquery.fancybox-1.3.4.css" media="screen" />
	<!--[if IE]>
	<link href="css/style_ie.css" rel="stylesheet" type="text/css">
	<![endif]-->
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../js/jquery.easing.1.3.js"></script>
	<script type="text/javascript" src="../js/jquery.bxslider.js"></script>
	<script type="text/javascript" src="../js/jquery.mousewheel.js"></script>
	<script type="text/javascript" src="../js/jquery.selectik.js"></script>
	<script type="text/javascript" src="../js/jquery.mousewheel-3.0.4.pack.js"></script>
	<script type="text/javascript" src="../js/jquery.fancybox-1.3.4.pack.js"></script>
	<script type="text/javascript" src="../js/jquery.countdown.js"></script>
	<script type="text/javascript" src="../js/jquery.checkbox.js"></script>
	<script type="text/javascript" src="../js/js.js"></script>
<script src="js/jquery.maskMoney.js"></script>
<script src="dist/jquery.validate.js"></script>


<script>
		
	$(document)
		.ready(
				function() {
	           
				
					
				
				$("#cadInfoForm")
						.validate(
								{
									
									
									rules : {
										
										placa:{
											
											required:true
											
										},
										
										fabricante:{
											
											required:true
										},
										
										model:{
							            	required: true
							            },
							            
							            combustivel:{
							            	
							            	required: true
							            },
							            
							            anoFabricacao:{
							            	
							            	required: true,
							            	number: true
							            },
							            
                                        anoModelo:{
							            	
                                        	
							            	required: true,
							            	number: true
							            	
							            },
							            quantPortas:{
							            	
							            	required:true
							            },
							            
							            motor:{
							            	
							            	required: true
							            },
							            
							            valvulas:{
							            	
							            	required: true
							            	
							            },
							            
 										transmissao:{
							            	
							            	required: true
							            	
							            },
							            
 										cor:{
							            	
							            	required: true
							            	
							            },
							            
 										descricao:{
							            	
							            	required: true
							            	
							            },
							            
							          
							            
							            condicaoTroca:{
							            	
							            	required: true
							            },
										
							            valor:{
							            	
							            	required: true
							                
							            },
							            
							            quilometragem:{
							            	
							            	required: true
							        			
				
							            	
							            },
							            
							            
										/*
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
										*/

									},
									messages : {
										placa : "Insira a placa do ve�culo.",
										fabricante :"Por favor, Escolha o fabricante do ve�culo.",
										model :"Por favor, escolha o modelo do ve�culo.",
										combustivel:"Por favor, escolha o tipo de combust�vel do ve�culo.",
										
										anoFabricacao:
										{
										   	
										   required:"Por favor, preencha o ano de fabrica��o do ve�culo",
										   number:"Por favor, entre com um n�mero v�lido.",
										},
										
										anoModelo:
										{
										required: "Por favor, preencha o ano de modelo do ve�culo",
										 number:"Por favor, entre com um n�mero v�lido.",
										 
										},
										
										quantPortas:"Por favor, escolha a quantidade de portas do ve�culo.",
										motor:"Por favor, escolha o tipo de motor do ve�culo.",
										valvulas: "Por favor, escolha a quantidade de portas do ve�culo",
										transmissao:"Por favor, escolha o tipo de transmiss�o do ve�culo.",
										cor:"Por favor, escolha a cor do ve�culo.",
										descricao:"Por favor, preencha a descri��o do ve�culo",
										estadoConservacao:"Por favor, escolha o estado do ve�culo.",
										condicaoTroca:"Por favor, escolha a condi��o de troca do ve�culo.",
										valor:"Por favor, preencha o valor do ve�culo",
										quilometragem:"Por favor, preencha a quilometragem do ve�culo",
										/*
										cpf : "Insira  seu cpf.", 
										username : {
											required : "Please enter a username",
											minlength : "Your username must consist of at least 2 characters"
										
										},
										
										
										senha : {
											required : "Escolha uma senha.",
											minlength : "A senha deve conter no m�nimo 5 caracteres."
										},
										senha2 : {
											required : "Confirme a senha.",
											minlength : "A senha deve conter no m�nimo 5 caracteres.",
											equalTo : "As senhas n�o conferem."
										},
										email : "Insira um email v�lido.",
										*/
									}
									
								});
				
				});
		
		
		
		 
		 
		 
								</script>


<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body class="sell">
	<!--BEGIN HEADER-->
	<div id="header"></div>


	<!--EOF HEADER-->
	<!--BEGIN CONTENT-->

	<div class="main_wrapper">
		<div class="steps">

			<% 
					
					//Autoriza��o de usu�rio
					Usuario user = (Usuario) session.getAttribute("usuario");
					
					if(user==null)
					request.getRequestDispatcher("index.jsp?page=esc_login.jsp").forward(request,response);	
					
				    //Verifica se ir� ou n�o mostrar o step
				    int mostra_step = Integer.parseInt(request.getParameter("mostra_step"));
					
					//Nome do bot�o salvar ou avan�ar
					String nome_botao="";
					
					if(mostra_step==1)
					nome_botao = "images/bt_proximo.png";	
					else
					nome_botao = "images/bt_salvar.png";	
						
					
					//Itens
					String versao="",anoFabricacao="",anoModelo="",placa="",descricao="",quilometragem="",valor="";
					
					Carro carro=null; 
				    carro = (Carro) session.getAttribute("carro");
					
					if(carro!=null)
					{
					
					//Seta os valores para serem preenchidos no formulario	
					versao = carro.getVersao();
					anoFabricacao = String.format("%d",carro.getAnoFabricacao());
					anoModelo = String.format("%d",carro.getAnoModelo());
					placa = carro.getPlaca();
					descricao = carro.getDescricao();
					quilometragem =  carro.getQuilometragem();
					valor = String.format("%.2f",carro.getPreco());
						
					}
					
					
					if(mostra_step==1)
					{
					//recupera o step
					StringBuffer step = (StringBuffer) session.getAttribute("step");
					if(step==null)
					step = new StringBuffer();	
					out.write(step.toString());
					}
					
					%>

		</div>
	</div>
	<div class="content">

		<h1>
			<strong>Informa��es</strong> b�sicas
		</h1>
		<div class="message">

			<p>Por favor, preencha os campos com dados reais do ve�culo.
			<p>
			<p>Os campos marcados com '*' s�o de preenchimento obrigat�rio.</p>
			<p>Nem todos os campos s�o de preenchimento obrigat�rio mas �
				recomend�vel que insira o m�ximo de informa��es poss�vel. .</p>

			<!-- Adicionar a mensagem aqui -->

		</div>

		<form id="cadInfoForm" action="ServAnuncio" method="POST">
			<div class="sell_submit_wrapper">

				<input type="image" src=<%=nome_botao%> />
			</div>
			<input type="hidden" name="STEP" value="2"> <input
				type="hidden" name="mostra_step" value="<%=mostra_step%>">
			<div class="sell_box sell_box_1">
				<h2>
					<strong>Dados do</strong> ve�culo-
				</h2>

				<div class="select_wrapper">
					<label for="fabricante"><span>* </span><strong>Fabricante:</strong></label>
					<select id="fabricante" name="fabricante" class="n">
						<option value="">Selecionar</option>
						
						<option value="23">Chevrolet</option>
						<option value="21">Fiat</option>
						<option value="22">Ford</option>
						<option value="25">Honda</option>
						<option value="41">Mitsubishi</option>
						<option value="44">Peugeot</option>
						<option value="48">Renault</option>
						<option value="56">Toyota</option>
						<option value="59">Volkswagen</option>
						<option value="">-------------------</option>


					</select>
				</div>
				<div class="select_wrapper">
					<label><span>* </span><strong>Modelo: </strong></label> <select
						id="model" name="modelo" class="n">
						

					</select>
				</div>
				<div class="select_wrapper">
					<label><span> </span><strong>Vers�o:</strong></label> <input
						name="versao" type="text" class="txb" value="<%=versao%>" />
				</div>
				<div class="select_wrapper last">
					<label><span>* </span><strong>Combustivel:</strong></label> <select
						id="combustivel" name="combustivel" class="n">
						<option value="">Selecione</option>
						<option value="�lcool">�lcool</option>
						<option value="Bi-Combust�vel">Bi-Combust�vel</option>
						<option value="Diesel">Diesel</option>
						<option value="Gasolina">Gasolina</option>
						<option value="Gasolina + Kit G�s">Gasolina + Kit G�s</option>
						<option value="Kit G�s">Kit G�s</option>
						<option value="Tetra Fuel">Tetra Fuel</option>
					</select>
				</div>
				<div class="clear"></div>
			</div>

			<div class="sell_box sell_box_1">
				<div class="select_wrapper">
					<label><span>* </span><strong>Ano Fabrica��o:</strong></label> <input
						name="anoFabricacao" type="text" class="txb"
						value="<%=anoFabricacao%>" />
				</div>
				<div class="select_wrapper">
					<label><span>* </span><strong>Ano Modelo</strong></label> <input
						name="anoModelo" type="text" class="txb" value="<%=anoModelo%>" />
				</div>
				
				

			

			

				<div class="select_wrapper">
					<label><span>* </span><strong>Placa:</strong></label> <input
						id="placa" name="placa" type="text" class="txb" value="<%=placa%>" />
				</div>
				

				
				<div class="select_wrapper last">
					<label><span>* </span><strong>Cor:</strong></label> <select
						id="cor" name="cor" class="n">
						<option value="">Selecione</option>
						<option value="Amarelo">Amarelo</option>
						<option value="Azul">Azul</option>
						<option value="Bege">Bege</option>
						<option value="Branco">Branco</option>
						<option value="Bronze">Bronze</option>
						<option value="Cinza">Cinza</option>
						<option value="Dourado">Dourado</option>
						<option value="Laranja">Laranja</option>
						<option value="Marrom">Marrom</option>
						<option value="Prata">Prata</option>
						<option value="Preto">Preto</option>
						<option value="Rosa">Rosa</option>
						<option value="Roxo">Roxo</option>
						<option value="Verde">Verde</option>
						<option value="Vermelho">Vermelho</option>
						<option value="Vinho">Vinho</option>

					</select>
					<div class="clear"></div>



				</div>
				
					
			</div>

				<div class="clear"></div>
			


			<div class="sell_box sell_box_1">

				<div class="input_wrapper">
					<label><span>* </span><strong>Descri��o:</strong></label>
					<textarea class="n_area" id="descricao" name="descricao" rows="10"
						cols="70"><%=descricao%></textarea>
				</div>


				<div class="clear"></div>
			</div>

			<div class="sell_box sell_box_2">
				<h2>
					<strong>Acess�rios</strong>
				</h2>
				<p>
					<input type="button" value="Completo" class="btn_comp" />
				</p>
				<jsp:include page="get_acessorios.jsp"></jsp:include>
				<div class="clear"></div>
			</div>

			<div class="sell_box sell_box_1">
				<h2>
					<strong>Condi��es do</strong> ve�culo
				</h2>
				
				<div class="select_wrapper">
					<label><span>* </span><strong>Quilometragem: </strong></label> <input
						id="quilometragem" name="quilometragem" type="text" class="txb" />
				</div>


				<div class="clear"></div>
			</div>
			<div class="sell_box sell_box_3">
				<h2>
					<strong>Valor</strong>
				</h2>
				<div class="select_wrapper">
					<label><span>* </span><strong>Valor: </strong></label> <input
						id="valor" name="valor" type="text" class="txb" value="<%=valor%>"
						onblur="if(this.value=='') this.value='0.00';"
						onfocus="if(this.value=='0.00') this.value='';" />
				</div>

				<div class="select_wrapper">
					<label><span>* </span><strong>Condi��o de troca:</strong></label> <select
						id="condicaoTroca" name="condicaoTroca" class="n">

						<option value="">Selecione</option>
						<option value="N�o Aceita Troca">N�o Aceita Troca</option>
						<option value="Troca Maior Valor">Troca Maior Valor</option>
						<option value="Troca Menor Valor">Troca Menor Valor</option>
						<option value="Aceita Troca">Aceita Troca</option>
					</select>
				</div>



				<div class="single_chb_wrapper">
					<span class="custom_chb_wrapper"> <span class="custom_chb">
							<input id="preco_negociavel" type="checkbox" value="1"
							name="preco_negociavel" />
					</span> <label><strong><span>Pre�o Negoci�vel</span></strong></label>
					</span>
				</div>
				<div class="clear"></div>
			</div>




			<div class="sell_submit_wrapper">

				<input type="image" src=<%=nome_botao%> />
			</div>
			<div class="clear"></div>
		</form>
	</div>


	<script>
	
	
	 $(document).ready(function() {
    	
    	
		
	
		 //Mudan�a do estado de conserva��o do ve�culo
		 $('#estadoConservacao').change(function(event) {  
	        
			 //Recupera o elemento estadoConservacao
			 var est= document.getElementById("estadoConservacao");
			 
			 //Recupera o elemento quilometragem
			 var qui = document.getElementById("quilometragem");
			 
			
			
			
			 if(est.value=='Novo')
			 {
			
			 qui.value='0';	
			 qui.disabled=true;
			
				 
			 } 
			 else
			{
			 qui.disabled=false;
			 qui.value='1';
			}
			 
			 if(qui.value=='null')
			 qui.value=='0';	 
			 	 
	        	
		    });   
		    
		 
		 
		 
		 
    	
    	 $('#fabricante').change(function(event) {  
        	
        	
            var $fab=$("select#fabricante").val();
        
       
           $.get('JSONItem',{fabricante:$fab,soli:'1'},function(responseJson) {   
           	 
        	   var $select = $('#model');                           
               $select.find('option').remove();    
                $('<option>').val('').text('Selecione um modelo').appendTo($select);                      
               $.each(responseJson, function(key, value) {               
                   $('<option>').val(key).text(value).appendTo($select);      
                    });
            });
        });
    });   
    
  
  
  
  
  //Recupera os modelos de uma marca
    	
       function getModelos(){
        var $fab=$("select#fabricante").val();
        
       
           $.get('JSONItem',{fabricante:$fab,soli:'1'},function(responseJson) {   
        	 
        	 
            var $select = $('#model');                           
               $select.find('option').remove();      
               $('<option>').val('').text('Selecione um modelo').appendTo($select);
               $.each(responseJson, function(key, value) {               
                   $('<option>').val(key).text(value).appendTo($select);      
                    });
            });
  }
  
  
     
      
      
  
     
  
     
   	//Marca os itens de completo
       $('.btn_comp').click(function(){
       		 
       		   
                   
       			$("input[type='checkbox'][value=1]").attr('checked','checked');
       			$("input[type='checkbox'][value=2]").attr('checked','checked');
       			$("input[type='checkbox'][value=3]").attr('checked','checked');
       			$("input[type='checkbox'][value=4]").attr('checked','checked');
       			$("input[type='checkbox'][value=5]").attr('checked','checked');
       			$("input[type='checkbox'][value=6]").attr('checked','checked');            
            
            
        });
  
  //Marca��o dos selects de acordo com os dados em sess�o
  //Seleciona os Acessorios marcados anteriormente
  <%
  if(carro!=null)
  {
	  ArrayList<Item> itens = new ArrayList<Item>(carro.getItens());
	  
	  if(itens!=null)
	  {
	  for(int i=0;i<itens.size();i++)
	  out.write("$(\"input[type='checkbox'][value="+itens.get(i).getId()+"]\").attr('checked','checked');");
	  out.write("\n");
	  }
	  
	  //Fabricante
	  out.write("$('#fabricante').val('"+carro.getCod_fabricante()+"');\n");
	  
	  out.write("getModelos();");
	  
      
	
	  
	  
	  //Combusive
	  out.write("$('#combustivel').val('"+carro.getCombustivel()+"');\n");
	  
	 
	  
	  
	  //Portas
	  out.write("$('#quantPortas').val('"+carro.getQuantPortas()+"');\n");
	  
	  //Motor
	  out.write("$('#motor').val('"+carro.getMotor()+"');\n");
	  
	  //Valvulas
	  out.write("$('#valvulas').val('"+carro.getQuantValvulas()+"');\n");
	  
	  //Condi��o de troca
	  out.write("$('#condicaoTroca').val('"+carro.getCondicaoTroca()+"');\n");
	  
	  //Cor
	  out.write("$('#cor').val('"+carro.getCor()+"');\n");
	  
	  //Automatico
	  out.write("$('#transmissao').val('"+carro.getTransmissao()+"');\n");
	  
	  
	 
	  
	  
	 //Estado de conserva��o
	 out.write("$('#estadoConservacao').val('"+carro.getEstadoConservacao()+"');\n");
	 
	 //Quilometragem
	  out.write("$('#quilometragem').val('"+carro.getQuilometragem()+"');\n");
	 
	 
	
	 //Preco negociavel
	 
	 if(carro.getPreco_negociavel()==1)
	 out.write("document.getElementById(\"preco_negociavel\").checked = true;\n");    	 
	 
	 else
		 out.write("document.getElementById(\"preco_negociavel\").checked = false;\n");    	 
	
  }
  

  
  %>
 

				

			

			
				//Mascara de valor
				  $("#valor").maskMoney({symbol:"",decimal:",",thousands:"."});
		
		
		    	
    
</script>

<script>




<%
		
		
		
		
		%>		

</script>



	<!--EOF CONTENT-->
	<!--BEGIN FOOTER-->
	<div id="footer"></div>
	<!--EOF FOOTER-->
</body>
