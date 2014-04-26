<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="util.Destaque"%>
<%@page import="java.util.ArrayList"%>
<%@page import="util.Veiculo"%>
<%@page import="Modelo.DestaqueDAO"%>
<%@page import="Modelo.VeiculoDAO"%>

<% 
  
  
  long t = System.currentTimeMillis();
  
  //Destaques 
  ArrayList<Destaque> destaques;
  
  //Veiculo dos destaques
  ArrayList<Veiculo> veiculos =  new ArrayList<Veiculo>();
  
  //Recupera os destaques do tipo banner
  destaques = new DestaqueDAO().getDestaques(Destaque.DESTAQUE_BANNER);
  
  //Recupera os veiculos dos destaques
  
  for(int i=0;i<destaques.size();i++)
   veiculos.add((Veiculo)new VeiculoDAO().getVeiculo(destaques.get(i).getCodVeiculo(),Veiculo.class));
  
  
  
  
  out.write("Tempo: "+(System.currentTimeMillis()-t));
  
  %>

<html class="csstransforms csstransforms3d csstransitions">
<!--<![endif]-->
<head>
<!-- Basic Page Needs
 				================================================== -->
<meta charset="utf-8">
<title></title>
<meta name="description" content="">
<meta name="author" content="">

<!-- Mobile Specific Metas
  				================================================== -->
<meta name="viewport" content="width=device-width">

<!-- CSS do Banner Destaque
 				================================================== -->
<link
	href="http://fonts.googleapis.com/css?family=Lato:100,300,400,700,900,400italic"
	rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Droid+Sans:400,700"
	rel="stylesheet" type="text/css">

<link rel="stylesheet" href="rs-plugin/css/settings.css" media="screen">
<link rel="stylesheet" href="css/style_2.css">
<!--Your Styles-->







<!-- Favicons
				================================================== -->
<link rel="shortcut icon" href="images/favicon.png">

<!-- Twitter Share Script
				================================================== -->
<script id="twitter-wjs" src="../platform.twitter.com/widgets.js"></script>
<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="../platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>

<!--[if lt IE 9]>
						<script src="js/html5shiv.js"></script>
						<link rel="stylesheet" href="css/ie.css">
				<![endif]-->
<script src="google_analytics_auto.html"></script>
</head>
<body>
	<!-- HEADER -->
	<header>
		<div class="header-topwrapper clearfix">
			<div class="login">
				<p></p>

			</div>
		</div>
		<div class="header-wrapper clearfix"></div>
	</header>
	<!--End HEADER -->


	<div class="fullwidthbanner-container slider-wrapper">

		<div class="fullwidthbanner slider tp-simpleresponsive hovered"
			style="height: 402px;">
			<ul>

				<%for(int i=0;i<veiculos.size();i++){ %>


				<li class="slide" data-transition="boxfade" data-slotamount="7"
					style="visibility: visible; left: 0px; top: 0px; z-index: 18; opacity: 1;">
					<div class="slotholder">
						<img src="ServImg?SOLI=7&&ID_VEICULO=<%=veiculos.get(i).getId()%>"
							alt="pic" class="defaultimg"
							style="width: 1354px; height: 562.8515928515928px; position: absolute; left: -25px; opacity: 0;">
					</div>
					<h2 class="caption sft heading-a start" data-x="0" data-y="100"
						data-start="1700"
						style="font-size: 26px; padding: 0px; margin: 0px; border: 0px; line-height: 0px; white-space: nowrap; opacity: 0; left: 172px; top: 50px;">
						<span><%=veiculos.get(i).getTitulo()%></span>
					</h2>
					<h2 class="caption lfb heading-b start" data-x="0" data-y="150"
						data-start="2500"
						style="font-size: 26px; padding: 8px 29px 8px 8px; margin: 0px; border: 0px; line-height: 0px; white-space: nowrap; opacity: 0.800000011920929; left: 172px; top: 407px;"><%=veiculos.get(i).getAnoFabricacao()%>/<%=veiculos.get(i).getAnoModelo()%></h2>
					<h2 class="caption lfr heading-c start" data-x="0" data-y="200"
						data-start="3000"
						style="font-size: 16px; padding: 0px; margin: 0px; border: 0px; line-height: 0px; white-space: nowrap; opacity: 1; left: 1309px; top: 200px;">
						<span>R$ <%=veiculos.get(i).getPreco()%></span>
					</h2>
				</li>

				<%} %>

			</ul>
			<div class="tp-loader" style="display: none;"></div>
		</div>

		<div class="tp-leftarrow tparrows round large"
			style="visibility: visible; position: absolute; left: 0px; top: 201px; opacity: 1;"></div>
		<div class="tp-rightarrow tparrows round large"
			style="visibility: visible; position: absolute; left: 1250px; top: 201px; opacity: 1;"></div>
	</div>


	<!-- CONTAINER-->






	<!--Footer-->

	<!--End Footer-->








	<!-- Scripts -->
	<script src="js/jquery-1.7.1.min.js"></script>
	<script src="rs-plugin/js/jquery.themepunch.plugins.min.js"></script>
	<script src="rs-plugin/js/jquery.themepunch.revolution.js"></script>
	<script src="js/jquery.isotope.min.js"></script>
	<script src="js/jquery-cycle.js"></script>
	<script src="js/jquery-ui.js"></script>
	<script src="js/jquery.selectBox.js"></script>
	<script src="js/timeline.js"></script>
	<script src="js/custom.js"></script>






	<ul class="selectBox-dropdown-menu selectBox-options"
		style="display: none;">
		<li class="selectBox-selected"><a rel="Sedans">Sedans</a></li>
		<li><a rel="Coupes">Coupes</a></li>
		<li><a rel="Wagons">Wagons</a></li>
		<li><a rel="Convertibles">Convertibles</a></li>
		<li><a rel="Sports Cars">Sports Cars</a></li>
		<li><a rel="Mini-Vans">Mini-Vans</a></li>
		<li><a rel="Pickup Trucks">Pickup Trucks</a></li>
	</ul>
	<ul class="selectBox-dropdown-menu selectBox-options"
		style="display: none;">
		<li class="selectBox-selected"><a rel="ANY">A1</a></li>
		<li><a rel="ANY">A9</a></li>
		<li><a rel="ANY">TT</a></li>
		<li><a rel="ANY">S10</a></li>
	</ul>
	<ul class="selectBox-dropdown-menu selectBox-options"
		style="display: none;">
		<li class="selectBox-selected"><a rel="ANY">Nissan</a></li>
		<li><a rel="ANY">Volkswagen</a></li>
		<li><a rel="ANY">Hyundai</a></li>
		<li><a rel="ANY">Toyota</a></li>
		<li><a rel="ANY">Jeep</a></li>
		<li><a rel="ANY">Porsche</a></li>
		<li><a rel="ANY">Ford</a></li>
		<li><a rel="ANY">Chevrolet</a></li>
		<li><a rel="ANY">BMW</a></li>
		<li><a rel="ANY">Audi</a></li>
		<li><a rel="ANY">Maserati</a></li>
	</ul>
	<ul class="selectBox-dropdown-menu selectBox-options"
		style="display: none;">
		<li class="selectBox-selected"><a rel="ANY">- 2008</a></li>
		<li><a rel="ANY">2008</a></li>
		<li><a rel="ANY">2009</a></li>
		<li><a rel="ANY">2010</a></li>
		<li><a rel="ANY">2011</a></li>
		<li><a rel="ANY">2002</a></li>
		<li><a rel="ANY">2013</a></li>
		<li><a rel="ANY">2014</a></li>
		<li><a rel="ANY">2015</a></li>
		<li><a rel="ANY">2016</a></li>
	</ul>
	<ul class="selectBox-dropdown-menu selectBox-options"
		style="display: none;">
		<li class="selectBox-selected"><a rel="ANY">2,000</a></li>
		<li><a rel="ANY">5,000</a></li>
		<li><a rel="ANY">10,000</a></li>
		<li><a rel="ANY">20,000</a></li>
		<li><a rel="ANY">30,000</a></li>
		<li><a rel="ANY">40,000</a></li>
		<li><a rel="ANY">50,000</a></li>
		<li><a rel="ANY">100,000</a></li>
		<li><a rel="ANY">+ 100,000</a></li>
	</ul>
</body>
</html>


