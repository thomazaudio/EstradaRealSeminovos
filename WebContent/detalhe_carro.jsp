<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@page import="util.Carro"%>
<%@page import="util.Veiculo"%>
<%@page import="Modelo.VeiculoDAO"%>
<%@page import="Modelo.ContatoDAO"%>
<%@page import="Modelo.ImgDAO"%>
<%@page import="Modelo.LocalizacaoDAO"%>
<%@page import="Modelo.UsuarioDAO"%>
<%@page import="Modelo.AnuncioDAO"%>
<%@page import="util.Anuncio"%>
<%@page import="util.Usuario"%>
<%@page import="util.Empresa"%>
<%@page import="util.Item"%>
<%@page import="java.util.ArrayList"%>
<%@page import="util.Contato"%>
<%@page import="util.Localizacao"%>
<%@page import="util.Pergunta"%>
<%@page import="Modelo.PerguntaDAO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<% 

ArrayList<Item> itens;
long id_veiculo=0;
Carro car =null;

Anuncio an =null;

id_veiculo = Long.parseLong(request.getParameter("id_veiculo"));	




try{
//recupera o anuncio
an = (Anuncio) new AnuncioDAO().getAnuncio(id_veiculo);

if(an==null)
request.getRequestDispatcher("anuncio_erro.jsp").forward(request, response);		
	


}catch(Exception e){
	
	request.getRequestDispatcher("anuncio_erro.jsp").forward(request, response);
}
	
car=(Carro) new VeiculoDAO().getVeiculo(an.getVeiculo().getId(),Carro.class);






//Atualiza a quantidade de atualizações
new AnuncioDAO().incrementVi(an.getId());

//Recupera o usuário responsável pelo anúncio

Usuario user = new UsuarioDAO().getUser(an.getIdUsuario());

Contato con_user =  new ContatoDAO().getContato(user.getContato().getId());


Localizacao loc =  new LocalizacaoDAO().getLocalizacao(user.getLocalizacao().getId());

String cidade,estado;

cidade =  new LocalizacaoDAO().getNomeCidade(loc.getCod_cidade());


%>

<link rel="shortcut icon" href="images/favicon.ico" />
   <link rel="icon" type="image/gif" href="images/animated_favicon1.gif" />


<!-- Page Title -->
<title><%=car.getTitulo()%> - Estrada Real - Seminovos</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/style980.css" />
<link rel="stylesheet" type="text/css" href="css/style800.css" />
<link rel="stylesheet" type="text/css" href="css/style700.css" />
<link rel="stylesheet" type="text/css" href="css/style600.css" />
<link rel="stylesheet" type="text/css" href="css/style500.css" />
<link rel="stylesheet" type="text/css" href="css/style400.css" />
<link rel="stylesheet" type="text/css"
	href="css/jquery.fancybox-1.3.4.css" media="screen" />
<!--[if IE]>
	<link href="css/style_ie.css" rel="stylesheet" type="text/css">
	<![endif]-->
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="js/jquery.bxslider.js"></script>
<script type="text/javascript" src="js/jquery.mousewheel.js"></script>
<script type="text/javascript" src="js/jquery.selectik.js"></script>
<script type="text/javascript" src="js/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="js/jquery.fancybox-1.3.4.pack.js"></script>
<script type="text/javascript" src="js/jquery.countdown.js"></script>
<script type="text/javascript" src="js/jquery.checkbox.js"></script>
<script type="text/javascript" src="js/js.js"></script>


<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>

	

<jsp:include page="header.jsp"></jsp:include>
	<div id="content">
		<div class="content">
		
		
		

			<div class="main_wrapper">
				<div class="cars_id"></div>
				<h1>
					<strong><%=car.getTitulo() %></strong>
				</h1>
				
				<div class="id">
					Código do anúncio: <span><strong><%=an.getId() %></strong></span>
				</div>
				<div class="views">
					
					<strong><%=an.getQuantVisualizacoes()%></strong> visualizações.</div>
				
				<canvas id="linha_titulo" width="1900" height="3"></canvas>
							<script>
      						var canvas = document.getElementById('linha_titulo');
     					    var context = canvas.getContext('2d');
      						var rectWidth = 1880;
     					    var rectHeight = 50;

      						

      						context.fillStyle = '#f28633';
     						 context.fillRect(rectWidth / -2, rectHeight / -2, rectWidth, rectHeight);
    					</script>
				<div class="clear"></div>
				<br></br>
				
				<div class="car_image_wrapper">


					<%
					
					//Recupera as imagens do veiculo
					long[] ids_imgs = new ImgDAO().getIdsImgs(an.getVeiculo().getId());
					
				
					
					%>
					
                   
					<div class="img_c">
						<div class="big_image">
						
						
						    <% if(ids_imgs.length>0){%>
							<a href="ServImg?SOLI=2&&ID_IMG=<%=ids_imgs[0]%>" rel="car_group">
								<img src="images/zoom.png" alt="" class="zoom" /> <img
								src="ServImg?SOLI=4&&ID_IMG=<%=ids_imgs[0]%>" alt="" />
							</a>
							<% }
							
						    else {
							%>
							<a href="ServImg?SOLI=15" rel="car_group">
								<img src="images/zoom.png" alt="" class="zoom" /> <img
								src="ServImg?SOLI=15" alt="" />
							</a>
							
							
							<% }%>
							
						</div>
						<div class="small_img">
						
						

							<% for(int i=0;i<ids_imgs.length;i++){ %>

							<a href="ServImg?SOLI=4&&ID_IMG=<%=ids_imgs[i]%>" rel="car_group">
								<img src="ServImg?SOLI=2&&ID_IMG=<%=ids_imgs[i]%>" alt="" />
							</a>
							<%} %>

						</div>
					</div>
				</div>
				<div class="car_characteristics">
					
					<div class="price"><%="R$ "+car.getPreco()%><span> <% 
						if(car.getPreco_negociavel()==1)
						out.write("  *  Preço negociável");	
						
						%>

						</span>
					</div>
					<div class="clear"></div>
					<div class="features_table">
					  
					  
					  
					   <div class="line">
						
							<div class="right"><h3><strong>*<%=cidade%></strong></h3></div>
						</div>
					
					
						<div class="line grey_area">
							<div class="left">Ano:</div>
							<div class="right"><%=car.getAnoFabricacao()+"/"+car.getAnoModelo() %></div>
						</div>
						<div class="line">
							<div class="left">Quilometragem:</div>
							<div class="right"><%=car.getQuilometragem()%></div>
						</div>
						<div class="line grey_area">
							<div class="left">Combustível:</div>
							<div class="right"><%=car.getCombustivel()%></div>
						</div>
						<div class="line">
							<div class="left">Transmissão:</div>
							<div class="right"><%=car.getTransmissao()%></div>
						</div>
						<div class="line grey_area">
							<div class="left">Motor:</div>
							<div class="right"><%=car.getMotor()%></div>
						</div>
						<div class="line">
							<div class="left">Cor:</div>
							<div class="right"><%=car.getCor()%></div>
						</div>
						<div class="line grey_area">
							<div class="left">Portas:</div>
							<div class="right"><%=car.getQuantPortas()%></div>
						</div>
						<div class="line">
							<div class="left">Início da placa:</div>
							<div class="right"><%=car.getPlaca()%></div>
						</div>
						<div class="line grey_area">
							<div class="left">Condição de troca:</div>
							<div class="right"><%=car.getCondicaoTroca()%></div>
						</div>
					</div>

				</div>
				<div class="clear"></div>
				<div class="info_box">
					<div class="car_info">
						<div class="info_left">
							<h2>
								<strong>Acessórios</strong>
							</h2>


							<canvas id="linha_acessorios" width="320" height="3"></canvas>
							<script>
      var canvas = document.getElementById('linha_acessorios');
      var context = canvas.getContext('2d');
      var rectWidth = 700;
      var rectHeight = 70;

      // translate context to center of canvas
      //context.translate(canvas.width / 2, canvas.height / 2);

      context.fillStyle = 'green';
      context.fillRect(rectWidth / -2, rectHeight / -2, rectWidth, rectHeight);
    </script>
    
                            <strong>Conforto:</strong><br />
							<%
								
								//Recupera os itens de conforto
								itens = car.getItensConforto();
								
								
							
								
								for(int i=0;i<itens.size();i++)
                                out.write(itens.get(i).getItem()+", ");  									
								
								%>

							</p>



							<strong>Desempenho:</strong><br />
							<%
								
								//Recupera os itens de desempenho
								itens = car.getItensDesempenho();
								
								

								
								
								for(int i=0;i<itens.size();i++)
                                out.write(itens.get(i).getItem()+", ");  
								
								%>

							</p>

								<strong>Designer:</strong><br />
							<%
								
								//Recupera os itens de Designer
								itens = car.getItensDesigner();

								
								
								for(int i=0;i<itens.size();i++)
                               out.write(itens.get(i).getItem()+", ");  								
								
								%>
							</p>
							<p>
								<strong>Segurança:</strong><br />

								<%
								
								//Recupera os itens de segurança
								itens = car.getItensSeguranca();
								

								
								for(int i=0;i<itens.size();i++)
                               out.write(itens.get(i).getItem()+", ");  
								
								%>
							</p>
						</div>
						<div class="info_right">
							<h2>
								<strong>Observações</strong>
							</h2>
							<canvas id="linha_observacoes" width="323" height="3"></canvas>
							<script>
      						var canvas = document.getElementById('linha_observacoes');
     					    var context = canvas.getContext('2d');
      						var rectWidth = 700;
     					    var rectHeight = 70;

      						

      						context.fillStyle = '#f28633';
     						 context.fillRect(rectWidth / -2, rectHeight / -2, rectWidth, rectHeight);
    </script>


  							
                          
                          <% out.write(car.getDescricao()); %>
                           <br></br>
							<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/pt_BR/all.js#xfbml=1";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
						<div class="fb-share-button" data-href="http://www.estradarealseminovos.com.br/Auto/anuncio.jsp?id_veiculo=<%=car.getId()%>" data-type="button_count"></div>
										
						</div>
						
						<!-- Botão compartilhar no facebook -->
						
						
						<div class="clear"></div>

					</div>
					<div class="car_contacts">
						<h2>
							<strong>Informações de</strong> contato.
						</h2>
						<canvas id="linha_contato" width="900" height="3"></canvas>
							<script>
      						var canvas = document.getElementById('linha_contato');
     					    var context = canvas.getContext('2d');
      						var rectWidth = 1359;
     					    var rectHeight = 70;

      						

      						context.fillStyle = '#607586';
     						 context.fillRect(rectWidth / -2, rectHeight / -2, rectWidth, rectHeight);
    </script>
						<p>
							<strong>Nome: </strong> <%=user.getNome()%>.
						</p>
						<div class="left">
							<div class="phones detail single_line spaced">
								[ <%=con_user.getCel()%> ] [ <%=con_user.getTel1()%> ] [ <%=con_user.getTel2()%> ]
							</div>
							
							<div class="addr detail single_line"><%=cidade%>.
							</div>
						</div>
						<div class="right">
						
						
						<a href="loja/index.jsf?cod_revenda=<%=user.getId()%>"><img width="100" alt="" src="ServImg?SOLI=8&&mostra_step=0&&ID_USUARIO=<%=user.getId()%>"></a>
						<p><a href="loja/index.jsf?cod_revenda=<%=user.getId()%>"><%=user.getNome() %></a></p>
						
						
						</div>
						<div class="clear"></div>
					</div>
					
					
					<div class="car_contacts">
						<h2>
							<strong>Proposta</strong>.
						</h2>
					
						<canvas id="linha_perguntas" width="900" height="3"></canvas>
							<script>
      						var canvas = document.getElementById('linha_perguntas');
     					    var context = canvas.getContext('2d');
      						var rectWidth = 1359;
     					    var rectHeight = 70;

      						

      						context.fillStyle = '#f28633';
     						 context.fillRect(rectWidth / -2, rectHeight / -2, rectWidth, rectHeight);
     						 
    </script>
    
                       
						
							
					        <div class="left"  >
					         <br>
					         <br>
					         
							<form  id="form_pergunta">
							<label><strong>Nome:</strong></label>
						<div class="select_box_1">
							<input name="nome"  required="true" type="text" class="select_3" />


						</div>
						<label><strong>Email:</strong></label>
						<div class="select_box_1">
							<input required="true" type="email" name="email" class="select_3" />


						</div>
						<label><strong>Telefone:</strong></label>
						<div class="select_box_1">
							<input name="telefone" type="text" class="select_3" />


						</div>
							
							<input type="hidden" name="id_veiculo" value="<%=car.getId()%>"/>
							<input type="hidden" name="id_usuario" value="<%=user.getId()%>"/>
							
							
							<textarea required="true"   rows="3" cols="82" id="pg" name="msg"></textarea>
							
						     <input  type="submit"  value="Enviar Proposta"   class="btn blue" />
							
							</form>
							
						
							
							</div>
							
							<div class="right">
						
						   
						   <img width="220" src="images/mascote_proposta.png" alt="">
						
						
						</div>
						
						<div class="clear"></div>
						
						
					</div>
					
					
						
					
					
						
						
						<br></br>
<!-- Outros anuncios deste vendendor(em caso de revenda) -->
				
				
				<%
					if(user.getClass()==Empresa.class)
					{
					%>
					
					
					<h2>
						Outros veículos de <strong>'<%=user.getNome()%>'
						</strong>.
					</h2>
				   
				  	<jsp:include page="outros_anuncios_empresa.jsp">
				<jsp:param value="<%=user.getId()%>" name="id_user"/>
				<jsp:param value="<%=an.getId()%>" name="id_atual"/>
				</jsp:include>
				
				<div class="wanted_line">
						
						
						<div class="right">
							<a target="_blank" href="loja/index.jsf?cod_revenda=<%=user.getId()%>">Ver Todos veículos de <strong><%=user.getNome()%></strong></a>
						</div>
					</div>
					
					

				<% }%>



				</div>
				<div class="car_sidebar">
					
					
					
					<!-- INICIO DA BUSCA -->
					
					<!-- FIM DA BUSCA -->
					
					<div class="calculator">
					<img alt="" src="images/img_pub.jpg">
					</div>
					
					
					
				</div>
				<div class="clear"></div>
					
				
			</div>
		</div>
	</div>

	<script>
	
	
	    function limpaP(){
	    
	   
	    var p = document.getElementById('pg');
	   
	   
	    
	    
	    }

		$("#form_pergunta").bind("submit", function() {

		    $.ajax({
		        type        : "POST",
		        cache       : false,
		        url         : "EnviaProposta",
		        data        : $(this).serializeArray(),
		        success: function(data) {
		        	
		        	
		        	
		            $.fancybox(data);
		            


		    		$("#form_pergunta").fancybox({
		    		    'width'         : '100%',
		    		    'height'        : '100%',
		    		    'autoScale'     : false,
		    		    'transitionIn'  : 'none',
		    		    'transitionOut' : 'none',
		    		    'type'          : 'iframe',
		    		    'onClosed': function() {
		    		         
		    		    	
		    		    	
		    		        }
		    		      	
		    		});	
			         
		        }
		    });

		    return false;
		});
		
		
	
	
	$("#form_proposta").bind("submit", function() {

		    $.ajax({
		        type        : "GET",
		        cache       : false,
		        url         : "ServContato",
		        data        : $(this).serializeArray(),
		        success: function(data) {
		        	
		        	
		        	
		            $.fancybox(data);
		            


		    		$("#form_proposta").fancybox({
		    		    'width'         : '100%',
		    		    'height'        : '100%',
		    		    'autoScale'     : false,
		    		    'transitionIn'  : 'none',
		    		    'transitionOut' : 'none',
		    		    'type'          : 'iframe',
		    		    'onClosed': function() {
		    		         
		    		    	window.location.reload();
		    		    	
		    		        }
		    		      	
		    		});	
			         
		        }
		    });

		    return false;
		});
			


		
	
		
		      	
	
	
	


$(".img_c a").fancybox({'type' : 'image'});
	
	
</script>

	<jsp:include page="rodape.jsp"></jsp:include>

</body>
</html>