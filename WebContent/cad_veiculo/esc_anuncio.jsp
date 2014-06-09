<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title></title>

<link rel="stylesheet" type="text/css" href="../css/style.css" />
<link rel="stylesheet" type="text/css" href="../css/style980.css" />
<link rel="stylesheet" type="text/css" href="../css/style800.css" />
<link rel="stylesheet" type="text/css" href="../css/style700.css" />
<link rel="stylesheet" type="text/css" href="../css/style600.css" />
<link rel="stylesheet" type="text/css" href="../css/style500.css" />
<link rel="stylesheet" type="text/css" href="../css/style400.css" />
<link rel="stylesheet" type="text/css"
	href="../css/jquery.fancybox-1.3.4.css" media="screen" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

	<jsp:include page="../simple_header.jsp"></jsp:include>

	                 <div class="main_wrapper">
		<div class="steps">

			<a href="#"><span>1. Tipo de Veículo</span></a><span>2. Plano</span><a href="#"><span>3. Cadastro das informações</span></a><a href="#"><span>4. Escolha de Imagens</span></a>

		</div>
	     </div>    


					<h2>
						<strong>Escolha de plano</strong>
					</h2>

					<br></br>





				

							<div class="fundo_laranja">
                               <h2 class="texto_branco">Destaque <strong>Ultra</strong> + Home + Banner-Destaque</h2>
                               </div>

						<form id="d_gratuito" action="ServAnuncio" method="POST">
							<input type="hidden" name="STEP" value="1"> <input
								type="hidden" name="prioridade_anuncio" value="1"> <input
								type="hidden" name="tipo_veiculo" value="1"> <input
								width="450" src="images/destaque_ultra.png" type="image">
						</form>

						

                            
							
							

                              <br></br>


                     
                        <div class="fundo_azul">
                               <h2 class="texto_branco">Destaque Mega + Home</h2>
                               </div>
						<form id="d_mega" action="ServAnuncio" method="POST">
							<input type="hidden" name="STEP" value="1"> <input
								type="hidden" name="prioridade_anuncio" value="2"> <input
								type="hidden" name="tipo_veiculo" value="1"> <input
								width="450" src="images/destaque_mega.png" type="image">
						</form>



							<div class="fundo_laranja">
                               <h2 class="texto_branco">Anúncio  <strong>Gratuito</strong></h2>
                               </div>

							<form id="d_gratuito" action="ServAnuncio" method="POST">
								<input type="hidden" name="STEP" value="1"> <input
									type="hidden" name="prioridade_anuncio" value="5"> <input
									type="hidden" name="tipo_veiculo" value="1"> <input
									width="450" src="images/anuncio_gratuito.png" type="image">
							</form>

								
						
						
							<script type="text/javascript">
								
								//ESSA PARTE FAZ COM QUE O DESTAQUE GRATUITO SEJA SELECIONADO AUTOMATICAMENTE
								
								//document.forms["d_gratuito"].submit();
								
							</script>
						


					
















</body>
</html>