<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="util.Carro"%>
<%@page import="util.Anuncio"%>
<%@page import="util.Item"%>
<%@page import="java.util.ArrayList"%>
<%@page import="util.Usuario"%>

<head>

<!-- Page Title -->
	<title>Estrada Real  Seminovos - Cadastro de anúncio </title>
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
	


       

//Validação da placa do veículo
function validaPlaca()
{
	
	
	
var placa =  document.getElementById('placa').value;	


	
  var er = /[a-z]{3}-?\d{4}/gim;
  er.lastIndex = 0;
  if(er.test(placa))
  {
	//window.alert("Plca ok");  
  }	  
  //else
	  //window.alert("Placa inválida"); 
}



		
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
										
										modelo:{
							            	required: true,
							            	
							            		
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
							            	
							            	required: true,
							            	number:true
							        			
				
							            	
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
										placa : "Insira a placa do veículo.",
										fabricante :"Por favor, Escolha o fabricante do veículo.",
										modelo :"Por favor, escolha o modelo do veículo.",
										combustivel:"Por favor, escolha o tipo de combustível do veículo.",
										
										anoFabricacao:
										{
										   	
										   required:"Por favor, preencha o ano de fabricação do veículo",
										   number:"Por favor, entre com um número válido.",
										},
										
										anoModelo:
										{
										required: "Por favor, preencha o ano de modelo do veículo",
										 number:"Por favor, entre com um número válido.",
										 
										},
										
										quantPortas:"Por favor, escolha a quantidade de portas do veículo.",
										motor:"Por favor, escolha o tipo de motor do veículo.",
										valvulas: "Por favor, escolha a quantidade de portas do veículo",
										transmissao:"Por favor, escolha o tipo de transmissão do veículo.",
										cor:"Por favor, escolha a cor do veículo.",
										descricao:"Por favor, preencha a descrição do veículo",
										estadoConservacao:"Por favor, escolha o estado do veículo.",
										condicaoTroca:"Por favor, escolha a condição de troca do veículo.",
										valor:"Por favor, preencha o valor do veículo",
										quilometragem:"Por favor, preencha a quilometragem do veículo",
										/*
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
										*/
									}
									
								});
				
				});
		
		
		
		 
		 
		 
								</script>


<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body class="sell">
	
	<jsp:include page="../simple_header.jsp"></jsp:include>
	
	<div class="main_wrapper">
		<div class="steps">

			<% 
					
					//Autorização de usuário
					Usuario user = (Usuario) session.getAttribute("usuario");
					
					if(user==null)
					request.getRequestDispatcher("index.jsp?page=login.jsp").forward(request,response);	
					
				    //Verifica se irá ou não mostrar o step
				    int mostra_step = Integer.parseInt(request.getParameter("mostra_step"));
					
					//Nome do botão salvar ou avançar
					String nome_botao="";
					
					if(mostra_step==1)
					nome_botao = "Continuar";	
					else
					nome_botao = "Salvar";	
						
					
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
			<strong>Informações</strong> básicas
		</h1>
		<div class="message">

			<p>Por favor, preencha os campos com dados reais do veículo.
			<p>
			<p>Os campos marcados com '*' são de preenchimento obrigatório.</p>
			<p>Nem todos os campos são de preenchimento obrigatório mas é
				recomendável que insira o máximo de informações possível. .</p>

			<!-- Adicionar a mensagem aqui -->

		</div>

		<form id="cadInfoForm" action="ServAnuncio" method="POST"  onsubmit="validaPlaca();" >
			<div class="sell_submit_wrapper">

				<input class="btn_2 blue" type="submit" value=<%=nome_botao%> />
			</div>
			<input type="hidden" name="STEP" value="2"> <input
				type="hidden" name="mostra_step" value="<%=mostra_step%>">
			<div class="sell_box sell_box_1">
				<h2>
					<strong>Dados do</strong> veículo-
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
							<option value="">Selecione--</option>

					</select>
				</div>
				<div class="select_wrapper">
					<label><span> </span><strong>Versão:</strong></label> <input
						name="versao" type="text" class="txb" value="<%=versao%>" />
				</div>
				<div class="select_wrapper last">
					<label><span>* </span><strong>Combustivel:</strong></label> <select
						id="combustivel" name="combustivel" class="n">
						<option value="">Selecione</option>
						<option value="Álcool">Álcool</option>
						<option value="Bi-Combustível">Bi-Combustível</option>
						<option value="Diesel">Diesel</option>
						<option value="Gasolina">Gasolina</option>
						<option value="Gasolina + Kit Gás">Gasolina + Kit Gás</option>
						<option value="Kit Gás">Kit Gás</option>
						<option value="Tetra Fuel">Tetra Fuel</option>
					</select>
				</div>
				<div class="clear"></div>
			</div>

			<div class="sell_box sell_box_1">
				<div class="select_wrapper">
					<label><span>* </span><strong>Ano Fabricação:</strong></label> <input
						name="anoFabricacao" type="text" class="txb"
						value="<%=anoFabricacao%>" />
				</div>
				<div class="select_wrapper">
					<label><span>* </span><strong>Ano Modelo</strong></label> <input
						name="anoModelo" type="text" class="txb" value="<%=anoModelo%>" />
				</div>
				<div class="select_wrapper">
					<label><span>* </span><strong>Portas:</strong></label> <select
						id="quantPortas" name="quantPortas" class="n">
						<option value="">Selecione</option>
						<option value="1">1 Porta</option>
						<option value="2">2 Portas</option>
						<option value="3">3 Portas</option>
						<option value="4">4 Portas</option>
						<option value="5">5 Portas</option>
						<option value="6">6 Portas</option>
					</select>
				</div>
				<div class="select_wrapper last">
					<label><span>* </span><strong>Motor:</strong></label> <select
						id="motor" name="motor" class="n">
						<option value="">Selecione</option>
						<option value="0.8">0.8</option>
						<option value="1.0">1.0</option>
						<option value="1.2">1.2</option>
						<option value="1.3">1.3</option>
						<option value="1.4">1.4</option>
						<option value="1.5">1.5</option>
						<option value="1.6">1.6</option>
						<option value="1.7">1.7</option>
						<option value="1.8">1.8</option>
						<option value="1.9">1.9</option>
						<option value="2.0">2.0</option>
						<option value="2.1">2.1</option>
						<option value="2.2">2.2</option>
						<option value="2.3">2.3</option>
						<option value="2.4">2.4</option>
						<option value="2.5">2.5</option>
						<option value="2.6">2.6</option>
						<option value="2.7">2.7</option>
						<option value="2.8">2.8</option>
						<option value="2.9">2.9</option>
						<option value="3.0">3.0</option>
						<option value="3.1">3.1</option>
						<option value="3.2">3.2</option>
						<option value="3.3">3.3</option>
						<option value="3.4">3.4</option>
						<option value="3.5">3.5</option>
						<option value="3.6">3.6</option>
						<option value="3.7">3.7</option>
						<option value="3.8">3.8</option>
						<option value="3.9">3.9</option>
						<option value="4.0">4.0</option>
						<option value="4.1">4.1</option>
						<option value="4.2">4.2</option>
						<option value="4.3">4.3</option>
						<option value="4.4">4.4</option>
						<option value="4.6">4.6</option>
						<option value="4.7">4.7</option>
						<option value="4.8">4.8</option>

					</select>
				</div>

				<div class="clear"></div>
			</div>

			<div class="sell_box sell_box_1">

				<div class="select_wrapper">
					<label><span>* </span><strong>Placa:</strong></label> <input
						id="placa" name="placa" type="text" class="txb" value="<%=placa%>" />
						
						
						
						
				</div>
				<div class="input_wrapper">
					<label><span>* </span><strong>Válvulas:</strong></label> <select
						id="valvulas" name="valvulas" class="n">
						<option value="">Selecione</option>
						<option value="8">8</option>
						<option value="12">12</option>
						<option value="16">16</option>
						<option value="20">20</option>
						<option value="24">24</option>
						<option value="30">30</option>
						<option value="32">32</option>

					</select>
				</div>

				<div class="select_wrapper">
					<label><span>* </span><strong>Transmissão:</strong></label> <select
						id="transmissao" name="transmissao" class="n">
						<option value="">Selecione</option>
						<option value="Automático">Automático</option>
						<option value="Manual">Manual</option>

					</select>
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

				<div class="clear"></div>
			</div>


			<div class="sell_box sell_box_1">

				<div class="input_wrapper">
					<label><span>* </span><strong>Descrição:</strong></label>
					<textarea class="n_area" id="descricao" name="descricao" rows="10"
						cols="70"><%=descricao%></textarea>
				</div>


				<div class="clear"></div>
			</div>

			<div class="sell_box sell_box_2">
				<h2>
					<strong>Acessórios</strong>
				</h2>
				<p>
				  
				
					<input type="button" value="Completo" class="btn_comp btn blue" />-<input type="button" value="Completo - Ar" class="btn_comp_sem_ar btn blue" />
				</p>
				<jsp:include page="get_acessorios.jsp"></jsp:include>
				<div class="clear"></div>
			</div>

			<div class="sell_box sell_box_1">
				<h2>
					<strong>Condições do</strong> veículo
				</h2>
				<div class="select_wrapper">
					
				</div>
				<div class="input_wrapper">
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
					<label><span>* </span><strong>Condição de troca:</strong></label> <select
						id="condicaoTroca" name="condicaoTroca" class="n">

						<option value="">Selecione</option>
						<option value="Não Aceita Troca">Não Aceita Troca</option>
						<option value="Troca Maior Valor">Troca Maior Valor</option>
						<option value="Troca Menor Valor">Troca Menor Valor</option>
						<option value="Aceita Troca">Aceita Troca</option>
					</select>
				</div>



				<div class="single_chb_wrapper">
					<span class="custom_chb_wrapper"> <span class="custom_chb">
							<input id="preco_negociavel" type="checkbox" value="1"
							name="preco_negociavel" />
					</span> <label><strong><span>Preço Negociável</span></strong></label>
					</span>
				</div>
				<div class="clear"></div>
			</div>




			<div class="sell_submit_wrapper">

				<input class="btn_2 blue" type="submit" value=<%=nome_botao%> />
			</div>
			<div class="clear"></div>
		</form>
	</div>


	<script>
	
	
	 $(document).ready(function() {
    	
    	
		
	
		 //Mudança do estado de conservação do veículo
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
               $('<option>').val('').text('Selecione um modelo 2').appendTo($select);
               $.each(responseJson, function(key, value) {               
                   $('<option>').val(key).text(value).appendTo($select);      
                    });
            });
  }
  
  
     
      
      
  
  	//Marca os itens de completo -Ar
       $('.btn_comp_sem_ar').click(function(){
       		 
       		   
                   
       			$("input[type='checkbox'][value=1]").attr('checked','checked');
       			$("input[type='checkbox'][value=2]").attr('checked','checked');
       			$("input[type='checkbox'][value=3]").prop('checked', false).uniform(); 
       			$("input[type='checkbox'][value=4]").attr('checked','checked');
       			$("input[type='checkbox'][value=5]").attr('checked','checked');
       			$("input[type='checkbox'][value=6]").attr('checked','checked');            
            
            
        });
     
  
     
   	//Marca os itens de completo
       $('.btn_comp').click(function(){
       		 
       		   
                   
       			$("input[type='checkbox'][value=1]").attr('checked','checked');
       			$("input[type='checkbox'][value=2]").attr('checked','checked');
       			$("input[type='checkbox'][value=3]").attr('checked','checked');
       			$("input[type='checkbox'][value=4]").attr('checked','checked');
       			$("input[type='checkbox'][value=5]").attr('checked','checked');
       			$("input[type='checkbox'][value=6]").attr('checked','checked');            
            
            
        });
  
  //Marcação dos selects de acordo com os dados em sessão
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
	  
	 
	  out.write("$('#modelo').val('"+carro.getCodModelo()+"');\n");
	 
	  
	  
	  //Combusive
	  out.write("$('#combustivel').val('"+carro.getCombustivel()+"');\n");
	  
	 
	  
	  
	  //Portas
	  out.write("$('#quantPortas').val('"+carro.getQuantPortas()+"');\n");
	  
	  //Motor
	  out.write("$('#motor').val('"+carro.getMotor()+"');\n");
	  
	  //Valvulas
	  out.write("$('#valvulas').val('"+carro.getQuantValvulas()+"');\n");
	  
	  //Condição de troca
	  out.write("$('#condicaoTroca').val('"+carro.getCondicaoTroca()+"');\n");
	  
	  //Cor
	  out.write("$('#cor').val('"+carro.getCor()+"');\n");
	  
	  //Automatico
	  out.write("$('#transmissao').val('"+carro.getTransmissao()+"');\n");
	  
	  
	 
	  
	  
	 //Estado de conservação
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
