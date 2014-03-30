<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="util.Anuncio"%>
<%@page import="util.Carro"%>
<%@page import="util.Contato"%>
<%@page import="util.Item"%>
<%@page import="util.Usuario"%>
<%@page import="util.Empresa"%>
<%@page import="Modelo.ImgDAO"%>
<%@page import="Modelo.ContatoDAO"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
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
<link rel="stylesheet" type="text/css"
	href="../css/jquery.fancybox-1.3.4.css" media="screen" />
<!--[if IE]>
	<link href="css/style_ie.css" rel="stylesheet" type="text/css">
	<![endif]-->
<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="../js/jquery.bxslider.js"></script>
<script type="text/javascript" src="../js/jquery.mousewheel.js"></script>
<script type="text/javascript" src="../js/jquery.selectik.js"></script>
<script type="text/javascript"
	src="../js/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="../js/jquery.fancybox-1.3.4.pack.js"></script>
<script type="text/javascript" src="../js/jquery.countdown.js"></script>
<script type="text/javascript" src="../js/jquery.checkbox.js"></script>
<script type="text/javascript" src="../js/js.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body class="car">

	<%
		//Recupea o usuario em sessão
		Usuario user =  (Usuario) session.getAttribute("usuario");
		
		//recupera o anuncio em sessao
		Anuncio an =  (Anuncio) session.getAttribute("anuncio");
		
		//Recupera os dados do carro
		Carro car = (Carro) session.getAttribute("carro");
		
		//Recupera o contato do usuario
		Contato con =  new ContatoDAO().getContato(user.getContato().getId());
		
		//Atualiza o contato do veiculo
		Contato con_veiculo =  new ContatoDAO().getContato(an.getVeiculo().getContato().getId());
	%>


	<div class="main_wrapper">

		<div class="steps">


			<a href="ServAnuncio?STEP=1&&mostra_step=1"><span>1.
					Informações básicas</span></a> <a href="ServAnuncio?STEP=2&&mostra_step=1"><span>2.
					Contato</span></a> <a href="ServAnuncio?STEP=3&&mostra_step=1"><span>3.Imagens</span></a>
			<span>4. Pré-Visualizar</span> <a href="#"><span>5. Forma
					de pagamento</span></a>


		</div>

		<div class="content">
			<h1>
				<strong>Pré-</strong>visualização
			</h1>
			<div class="message">
				<p>O seu veículo está a um passo de ser anunciado!</p>
				<p>Por favor, revise as informações cadastradas.</p>
			</div>



			<h1>
				<strong>Plano</strong>
			</h1>
			<div class="car_characteristics">

				<div class="clear"></div>
				<div class="features_table">
					<div class="line grey_area">
						<div class="left">Plano:</div>
						<div class="right">Plano teste</div>
					</div>

				</div>
				<div class="wanted_line">
					<div class="left"></div>
					<div class="right">
						<a class="iframe" href="#">Alterar Plano</a>
					</div>
				</div>
			</div>
			<div class="clear"></div>
			<div class="info_box">

				<h1>
					<strong>Informações do veículo</strong>
				</h1>
				<div class="car_characteristics">

					<div class="clear"></div>
					<div class="features_table">
						<div class="line grey_area">
							<div class="left">Modelo:</div>
							<div class="right"><%=car.getModelo()%>
							</div>
						</div>
						<div class="line">
							<div class="left">Versão:</div>
							<div class="right"><%=car.getVersao()%></div>
						</div>
						<div class="line grey_area">
							<div class="left">Combustível:</div>
							<div class="right"><%=car.getCombustivel()%></div>
						</div>
						<div class="line">
							<div class="left">Ano Fabricação:</div>
							<div class="right"><%=car.getAnoFabricacao()%></div>
						</div>
						<div class="line grey_area">
							<div class="left">Ano Modelo:</div>
							<div class="right"><%=car.getAnoModelo()%></div>
						</div>
						<div class="line">
							<div class="left">Portas:</div>
							<div class="right"><%=car.getQuantPortas()%></div>
						</div>
						<div class="line grey_area">
							<div class="left">Motor:</div>
							<div class="right"><%=car.getMotor()%></div>
						</div>
						<div class="line">
							<div class="left">Válvulas:</div>
							<div class="right"><%=car.getQuantValvulas()%></div>
						</div>

						<div class="line grey_area">
							<div class="left">Transmissão:</div>
							<div class="right"><%=car.getTransmissao()%></div>
						</div>

						<div class="line">
							<div class="left">Cor:</div>
							<div class="right"><%=car.getCor()%></div>
						</div>
						<div class="line grey_area">
							<div class="left">Descrição:</div>
							<div class="right"><%=car.getDescricao()%></div>
						</div>

						<div class="line">
							<div class="left">Acessórios:</div>
							<div class="right">
								<%
									ArrayList<Item> itens = new ArrayList<Item>(car.getItens());
																																																																																																		
																																																																																																		for(int i=0;i<itens.size();i++)
																																																																																																		out.write("<strong>"+itens.get(i).getItem()+"</strong>, ");
								%>

							</div>
						</div>

						<div class="line grey_area">
							<div class="left">Estado de conservação:</div>
							<div class="right"><%=car.getEstadoConservacao()%></div>
						</div>

						<div class="line">
							<div class="left">Condição de troca:</div>
							<div class="right"><%=car.getCondicaoTroca()%></div>
						</div>

						<div class="line grey_area">
							<div class="left">Valor:</div>
							<div class="right">
								R$ <strong><%=car.getPreco()%></strong>
							</div>
						</div>

					</div>
					<div class="wanted_line">
						<div class="left"></div>
						<div class="right">
							<a class="iframe" href="../cad_veiculo/cad_info_carro.jsp?mostra_step=0">Alterar
								informações</a>
						</div>
					</div>
				</div>


			</div>

			<div class="info_box">

				<h1>
					<strong>Contato</strong> principal
				</h1>
				<div class="car_characteristics">

					<div class="clear"></div>
					<div class="features_table">
						<div class="line grey_area">
							<div class="left">Email:</div>
							<div class="right"><%=con.getEmail()%></div>
						</div>
						<div class="line">
							<div class="left">Nome:</div>
							<div class="right"><%=con.getNome()%></div>
						</div>
						<div class="line grey_area">
							<div class="left">Cel:</div>
							<div class="right"><%=con.getCel()%></div>
						</div>
						<div class="line">
							<div class="left">Tel-1:</div>
							<div class="right"><%=con.getTel1()%></div>
						</div>
						<div class="line grey_area">
							<div class="left">Tel-2:</div>
							<div class="right"><%=con.getTel2()%></div>
						</div>

					</div>
					<div class="wanted_line">
						<div class="left"></div>
						<div class="right">
							<a class="iframe"
								href="../cad_veiculo/edit_contato.jsp?id_contato=<%=con.getId()%>&&id_contato_veiculo=0">Editar
								contato</a>
						</div>
					</div>
				</div>


			</div>


			<%
				//Verifica se o usuário é empresa
																																	if(user.getClass()==Empresa.class)
																																	{
																																	
																																	//Verifica se existe contato exclusivo para o veiculo
																																	if(con_veiculo.getComp()==1)
																																	{
																																		out.write("<div class=\"info_box\">");
																																	
																																		out.write("<h1><strong>Contato</strong> secundário</h1>");

																																		out.write("<div class=\"car_characteristics\">");

																																		out.write("<div class=\"clear\"></div>");

																																		out.write("<div class=\"features_table\">");

																																		out.write("<div class=\"line grey_area\">");

																																		out.write("<div class=\"left\">Email:</div>");

																																		out.write("<div class=\"right\">" + con_veiculo.getEmail() + "</div>");

																																		out.write("</div>");

																																		out.write("<div class=\"line\">");

																																		out.write("<div class=\"left\">Nome:</div>");

																																		out.write("<div class=\"right\">" + con_veiculo.getNome() + "</div>");

																																		out.write("</div>");

																																		out.write("<div class=\"line grey_area\">");

																																		out.write("<div class=\"left\">Cel:</div>");

																																		out.write("<div class=\"right\">" + con_veiculo.getCel() + "</div>");

																																		out.write("</div>");

																																		out.write("<div class=\"line\">");

																																		out.write("<div class=\"left\">Tel-1:</div>");

																																		out.write("	<div class=\"right\">" + con_veiculo.getTel1() + "</div>");

																																		out.write("</div>");

																																		out.write("<div class=\"line grey_area\">");

																																		out.write("<div class=\"left\">Tel-2:</div>");

																																		out.write("	<div class=\"right\">" + con_veiculo.getTel2() + "</div>");

																																		out.write("</div>");

																																		out.write("</div>");

																																		out.write("<div class=\"wanted_line\">");

																																		out.write("<div class=\"left\"></div>");

																																		out.write("	<div class=\"right\">");

																																		out.write("<a class=\"iframe\" href=\"cad_veiculo/edit_contato.jsp?id_contato="
																																				+ con_veiculo.getId()
																																				+ "&&id_contato_veiculo=0\">Editar contato secundário</a>");

																																	out.write("</div>");

																																	out.write("	</div>");

																																	out.write("</div>");

																																	out.write("</div>");
																																	out.write("</div>");

																																}

																															}
			%>

			<div class="info_box">

				<h1>
					<strong>Imagens</strong>
				</h1>
				<div class="car_characteristics">
					<div class="car_image_wrapper">


						<div class="big_image_edit">
							<div class="small_img">

								<%
									//Id do veiculo em sessão
																																																																																												 long id_veiculo =car.getId();

																																																																																												long[] ids_imgs = new ImgDAO().getIdsImgs(id_veiculo);

																																																																																												out.write("<h3><strong>"+ids_imgs.length+"</strong> imagens cadastradas.</h3>");

																																																																																												for(int i=0;i<ids_imgs.length;i++)
																																																																																												{
								%>

								<div class="teste">
									<a href="ServImg?SOLI=4&&ID_IMG=<%=ids_imgs[i]%>"
										rel="car_group"> <img
										src="ServImg?SOLI=2&&ID_IMG=<%=ids_imgs[i]%>" alt="car_group" />
									</a>
								</div>

								<%
									}
								%>

							</div>
						</div>

						<div class="wanted_line">
							<div class="left"></div>
							<div class="right">
								<a href="ServAnuncio?STEP=3&&mostra_step=0">Editar imagens</a>
							</div>
						</div>
					</div>


				</div>

			</div>

			<div class="clear"></div>
			<div class="recent_cars"></div>


			<script>
				$(document).ready(function() {

					$(".teste a").fancybox({
						'type' : 'image'
					});

					$(".iframe").fancybox({
						'width' : '100%',
						'height' : '100%',
						'autoScale' : false,
						'transitionIn' : 'none',
						'transitionOut' : 'none',
						'type' : 'iframe',
						'onClosed' : function() {

							window.location.reload();

						}

					});
				});
			</script>


			<!--EOF CONTENT-->
			<!--BEGIN FOOTER-->

			<!--EOF FOOTER-->
		</div>
	</div>
</body>
</html>