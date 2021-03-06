<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="util.Anuncio"%>
<%@page import="util.Moto"%>
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
		//Recupea o usuario em sess�o
		Usuario user = (Usuario) session.getAttribute("usuario");

		//recupera o anuncio em sessao
		Anuncio an = (Anuncio) session.getAttribute("anuncio");

		//Recupera os dados da moto
		Moto moto = (Moto) session.getAttribute("moto");

		//Recupera o contato do usuario
		Contato con = new ContatoDAO()
				.getContato(user.getContato().getId());

		//Atualiza o contato do veiculo
		Contato con_veiculo = new ContatoDAO().getContato(an.getVeiculo()
				.getContato().getId());

		//Mostra step
		int mostra_step = Integer.parseInt(request
				.getParameter("mostra_step"));
	%>


	<div class="main_wrapper">

		<div class="steps">

			<%
				if (mostra_step == 1) {
			%>
			<a href="ServAnuncio?STEP=1&&mostra_step=1"><span>1.
					Informa��es b�sicas</span></a> <a href="ServAnuncio?STEP=2&&mostra_step=1"><span>2.
					Contato</span></a> <a href="ServAnuncio?STEP=3&&mostra_step=1"><span>3.Imagens</span></a>
			<span>4. Pr�-Visualizar</span> <a href="#"><span>5.Conclu�do
					com sucesso! </span></a>

			<%
				}
			%>
		</div>

		<br></br>

		<div class="content">

			<%
				if (mostra_step == 1) {
			%>
			<h1>
				<strong>Pr�-</strong>visualiza��o
			</h1>
			<div class="message">
				<p>O seu ve�culo est� a um passo de ser anunciado!</p>
				<p>Por favor, revise as informa��es cadastradas.</p>
			</div>

			<form action="ServAnuncio">
				<input type="hidden" value="5" name="STEP" /> <input type="hidden"
					value="0" name="mostra_step" /> <input type="submit"
					value="Finalizar" class="btn_2 blue">
			</form>

			<%
				}

				else {
			%>

			<h1>
				<strong>Editar </strong>an�ncio
			</h1>
			<div class="message">
				<p>Aqui voc� altera as informa��es do an�ncio.</p>
				<p>Por favor, revise as informa��es cadastradas.</p>
			</div>

			<%
				}
			%>
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
						<a class="iframe" href="cad_veiculo/esc_plano.xhtml">Alterar
							Plano</a>
					</div>
				</div>
			</div>
			<div class="clear"></div>
			<div class="info_box">

				<h1>
					<strong>Informa��es do ve�culo</strong>
				</h1>
				<div class="car_characteristics">

					<div class="clear"></div>
					<div class="features_table">
						<div class="line grey_area">
							<div class="left">Modelo:</div>
							<div class="right"><%=moto.getModelo()%>
							</div>
						</div>
						<div class="line">
							<div class="left">Vers�o:</div>
							<div class="right"><%=moto.getVersao()%></div>
						</div>
						<div class="line grey_area">
							<div class="left">Combust�vel:</div>
							<div class="right"><%=moto.getCombustivel()%></div>
						</div>
						<div class="line">
							<div class="left">Ano Fabrica��o:</div>
							<div class="right"><%=moto.getAnoFabricacao()%></div>
						</div>
						<div class="line grey_area">
							<div class="left">Ano Modelo:</div>
							<div class="right"><%=moto.getAnoModelo()%></div>
						</div>

						<div class="line grey_area">
							<div class="left">Cilindradas:</div>
							<div class="right"><%=moto.getCilindradas()%></div>
						</div>




						<div class="line">
							<div class="left">Cor:</div>
							<div class="right"><%=moto.getCor()%></div>
						</div>
						<div class="line grey_area">
							<div class="left">Descri��o:</div>
							<div class="right"><%=moto.getDescricao()%></div>
						</div>

						<div class="line">
							<div class="left">Acess�rios:</div>
							<div class="right">
								<%
									ArrayList<Item> itens = new ArrayList<Item>(moto.getItens());

									for (int i = 0; i < itens.size(); i++)
										out.write("<strong>" + itens.get(i).getItem() + "</strong>, ");
								%>

							</div>
						</div>



						<div class="line">
							<div class="left">Condi��o de troca:</div>
							<div class="right"><%=moto.getCondicaoTroca()%></div>
						</div>

						<div class="line grey_area">
							<div class="left">Valor:</div>
							<div class="right">
								R$ <strong><%=moto.getPreco()%></strong>
							</div>
						</div>

					</div>
					<div class="wanted_line">
						<div class="left"></div>
						<div class="right">
							<a class="iframe" href="ServAnuncio?STEP=1&&mostra_step=0">Alterar
								informa��es</a>
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
							<div class="right"><%=user.getNome()%></div>
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
						<div class="right"></div>
					</div>
				</div>


			</div>


			<%
				//Verifica se o usu�rio � empresa
				if (user.getClass() == Empresa.class) {

					//Verifica se existe contato exclusivo para o veiculo
					if (con_veiculo.getComp() == 1) {
						out.write("<div class=\"info_box\">");

						out.write("<h1><strong>Contato</strong> secund�rio</h1>");

						out.write("<div class=\"car_characteristics\">");

						out.write("<div class=\"clear\"></div>");

						out.write("<div class=\"features_table\">");

						out.write("<div class=\"line grey_area\">");

						out.write("<div class=\"left\">Email:</div>");

						out.write("<div class=\"right\">" + con_veiculo.getEmail()
								+ "</div>");

						out.write("</div>");

						out.write("<div class=\"line\">");

						out.write("<div class=\"left\">Nome:</div>");

						out.write("<div class=\"right\">" + con_veiculo.getNome()
								+ "</div>");

						out.write("</div>");

						out.write("<div class=\"line grey_area\">");

						out.write("<div class=\"left\">Cel:</div>");

						out.write("<div class=\"right\">" + con_veiculo.getCel()
								+ "</div>");

						out.write("</div>");

						out.write("<div class=\"line\">");

						out.write("<div class=\"left\">Tel-1:</div>");

						out.write("	<div class=\"right\">" + con_veiculo.getTel1()
								+ "</div>");

						out.write("</div>");

						out.write("<div class=\"line grey_area\">");

						out.write("<div class=\"left\">Tel-2:</div>");

						out.write("	<div class=\"right\">" + con_veiculo.getTel2()
								+ "</div>");

						out.write("</div>");

						out.write("</div>");

						out.write("<div class=\"wanted_line\">");

						out.write("<div class=\"left\"></div>");

						out.write("	<div class=\"right\">");

						out.write("<a class=\"iframe\" href=\"cad_veiculo/edit_contato.jsp?id_contato="
								+ con_veiculo.getId()
								+ "&&id_contato_veiculo=0\">Editar contato secund�rio</a>");

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
									//Id do veiculo em sess�o
									long id_veiculo = moto.getId();
									long[] ids_imgs = new ImgDAO().getIdsImgs(id_veiculo);

									out.write("<h3><strong>" + ids_imgs.length
											+ "</strong> imagens cadastradas.</h3>");

									for (int i = 0; i < ids_imgs.length; i++) {
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
								<a class="iframe" href="ServAnuncio?STEP=3&&mostra_step=0">Editar
									imagens</a>
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