<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	

	<meta name="keywords" content="Car-Dealer, auto-salon, automobile, business, car, car-gallery, car-selling-template, cars, dealer, marketplace, mobile, real estate, responsive, sell, vehicle" />
	<meta name="description" content="Auto Dealer HTML - Responsive HTML Auto Dealer Template" />

	<!-- Page Title -->
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<link rel="stylesheet" type="text/css" href="css/style980.css" />
	<link rel="stylesheet" type="text/css" href="css/style800.css" />
	<link rel="stylesheet" type="text/css" href="css/style700.css" />
	<link rel="stylesheet" type="text/css" href="css/style600.css" />
	<link rel="stylesheet" type="text/css" href="css/style500.css" />
	<link rel="stylesheet" type="text/css" href="css/style400.css" />
	<link rel="stylesheet" type="text/css" href="css/jquery.fancybox-1.3.4.css" media="screen" />
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body >
	<!--BEGIN HEADER-->
		<div id="header">
			<div class="top_info">
				<div class="logo">
					<a href="#">Estrada<span>Real</span></a>
				</div>
				<div class="header_contacts">
					<div class="phone">+1 (800) 455-55-95</div>
					<div>WinterJuice, LLC, 1875 South Grant Street, Suite 680, San Mateo, CA 94402</div>
				</div>
				<div class="socials">
					<a href="#"><img src="images/fb_icon.png" alt="" /></a>
					<a href="#"><img src="images/twitter_icon.png" alt="" /></a>
					<a href="#"><img src="images/in_icon.png" alt="" /></a>
				</div>
			</div>
			<div class="bg_navigation">
				<div class="navigation_wrapper">
					<div id="navigation">
						<span>Início</span>
						<ul>
							<li class="current"><a href="./">INÍCIO</a></li>
							<li><a href="about">ANUNCIE SEU VEÍCULO</a></li>
							<li><a href="blog">PLANOS</a></li>
							<li><a href="#">REVENDAS</a></li>
							<li><a href="add">AJUDA</a></li>
							<li><a href="contacts">FALE CONOSCO</a></li>
						</ul>
					</div>
					<div id="search_form">
						<form method="get" action="./catalog" />
							<input type="text" onblur="if(this.value=='') this.value='Search on site';" onfocus="if(this.value=='Search on site') this.value='';" value="Search on site" class="txb_search" />
							<input type="submit" value="Search" class="btn_search" />
						</form>
					</div>
				</div>
			</div>
		</div>
	<!--EOF HEADER-->
	<!--BEGIN CONTENT-->
		<div id="content">
			<div class="content">
				<div class="wrapper_1">
					
					<div class="search_auto_wrapper">
						
						<jsp:include page="componente_busca.jsf"></jsp:include>
						
						
					</div>
					
					
						
							
				<div>	
				<jsp:include page="banner_destaque.jsp"></jsp:include>
					</div>				
							
							
							
							
						
					
					
					
					<div class="clear"></div>
				</div>
				<div class="recent">
					<h2><strong>Recent</strong> listings</h2>
					<div class="recent_carousel">
						<div class="slide">
							<a href="product">
								<img src="images/pics/recent_1.jpg" alt="" />
								<div class="description">
									<p>
										Registration 2010<br />3.0 Diesel<br />230 HP<br />Body Coupe<br />80 000 Miles
									</p>
								</div>
								<div class="title">
									<p>
										<span>Mercedes-Benz</span>
										<span class="price">$ 115 265</span>
									</p>
								</div>
							</a>
						</div>
						<div class="slide">
							<a href="product">
								<img src="images/pics/recent_2.jpg" alt="" />
								<div class="description">
									<p>
										Registration 2010<br />3.0 Diesel<br />230 HP<br />Body Coupe<br />80 000 Miles
									</p>
								</div>
								<div class="title">
									<p>
										<span>Mercedes-Benz</span>
										<span class="price">$ 115 265</span>
									</p>
								</div>
							</a>
						</div>
						<div class="slide">
							<a href="product">
								<img src="images/pics/recent_3.jpg" alt="" />
								<div class="description">
									<p>
										Registration 2010<br />3.0 Diesel<br />230 HP<br />Body Coupe<br />80 000 Miles
									</p>
								</div>
								<div class="title">
									<p>
										<span>Mercedes-Benz</span>
										<span class="price">$ 115 265</span>
									</p>
								</div>
							</a>
						</div>
						<div class="slide">
							<a href="product">
								<img src="images/pics/recent_4.jpg" alt="" />
								<div class="description">
									<p>
										Registration 2010<br />3.0 Diesel<br />230 HP<br />Body Coupe<br />80 000 Miles
									</p>
								</div>
								<div class="title">
									<p>
										<span>Mercedes-Benz</span>
										<span class="price">$ 115 265</span>
									</p>
								</div>
							</a>
						</div>
						<div class="slide">
							<a href="product">
								<img src="images/pics/recent_1.jpg" alt="" />
								<div class="description">
									<p>
										Registration 2010<br />3.0 Diesel<br />230 HP<br />Body Coupe<br />80 000 Miles
									</p>
								</div>
								<div class="title">
									<p>
										<span>Mercedes-Benz</span>
										<span class="price">$ 115 265</span>
									</p>
								</div>
							</a>
						</div>
						<div class="slide">
							<a href="product">
								<img src="images/pics/recent_2.jpg" alt="" />
								<div class="description">
									<p>
										Registration 2010<br />3.0 Diesel<br />230 HP<br />Body Coupe<br />80 000 Miles
									</p>
								</div>
								<div class="title">
									<p>
										<span>Mercedes-Benz</span>
										<span class="price">$ 115 265</span>
									</p>
								</div>
							</a>
						</div>
						<div class="slide">
							<a href="product">
								<img src="images/pics/recent_3.jpg" alt="" />
								<div class="description">
									<p>
										Registration 2010<br />3.0 Diesel<br />230 HP<br />Body Coupe<br />80 000 Miles
									</p>
								</div>
								<div class="title">
									<p>
										<span>Mercedes-Benz</span>
										<span class="price">$ 115 265</span>
									</p>
								</div>
							</a>
						</div>
						<div class="slide">
							<a href="product">
								<img src="images/pics/recent_4.jpg" alt="" />
								<div class="description">
									<p>
										Registration 2010<br />3.0 Diesel<br />230 HP<br />Body Coupe<br />80 000 Miles
									</p>
								</div>
								<div class="title">
									<p>
										<span>Mercedes-Benz</span>
										<span class="price">$ 115 265</span>
									</p>
								</div>
							</a>
						</div>
						<div class="slide">
							<a href="product">
								<img src="images/pics/recent_1.jpg" alt="" />
								<div class="description">
									<p>
										Registration 2010<br />3.0 Diesel<br />230 HP<br />Body Coupe<br />80 000 Miles
									</p>
								</div>
								<div class="title">
									<p>
										<span>Mercedes-Benz</span>
										<span class="price">$ 115 265</span>
									</p>
								</div>
							</a>
						</div>
						<div class="slide">
							<a href="product">
								<img src="images/pics/recent_2.jpg" alt="" />
								<div class="description">
									<p>
										Registration 2010<br />3.0 Diesel<br />230 HP<br />Body Coupe<br />80 000 Miles
									</p>
								</div>
								<div class="title">
									<p>
										<span>Mercedes-Benz</span>
										<span class="price">$ 115 265</span>
									</p>
								</div>
							</a>
						</div>
						<div class="slide">
							<a href="product">
								<img src="images/pics/recent_3.jpg" alt="" />
								<div class="description">
									<p>
										Registration 2010<br />3.0 Diesel<br />230 HP<br />Body Coupe<br />80 000 Miles
									</p>
								</div>
								<div class="title">
									<p>
										<span>Mercedes-Benz</span>
										<span class="price">$ 115 265</span>
									</p>
								</div>
							</a>
						</div>
						<div class="slide">
							<a href="product">
								<img src="images/pics/recent_4.jpg" alt="" />
								<div class="description">
									<p>
										Registration 2010<br />3.0 Diesel<br />230 HP<br />Body Coupe<br />80 000 Miles
									</p>
								</div>
								<div class="title">
									<p>
										<span>Mercedes-Benz</span>
										<span class="price">$ 115 265</span>
									</p>
								</div>
							</a>
						</div>
					</div>
				</div>
				<div class="banners">
					<div class="banner_1 main_banner">
						<div class="text_wrapper">
							<p class="title"><strong>Looking</strong> for a car?</p>
							<p class="desc">1.000 new offers every day. 35.000 offers on site</p>
						</div>
						<a href="./catalog">Search</a>
					</div>
					<div class="banner_2 main_banner">
						<div class="text_wrapper">
							<p class="title"><strong>Want</strong> to sell a car?</p>
							<p class="desc">200.000 visitors every day. Add your offer now!</p>
						</div>
						<a href="./add">Sell</a>
					</div>
				</div>
				<div class="wrapper_2">
					<div class="left">
						<div class="recent_blog">
							<h2><strong>Recent</strong> from the blog</h2>
							<div class="post_block">
								<a href="product" class="thumb"><img src="images/pics/recent_blog_1.jpg" alt="" /></a>
								<h5><a href="example">THE IMPORTANCE OF LUXURY SUV SALES EXPLAINED</a></h5>
								<div class="date"><span>November 1, 2012</span></div>
								<div class="post"><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit... Aliquam hendrerit sagittis urna,</p></div>
							</div>
							<div class="post_block">
								<a href="product" class="thumb"><img src="images/pics/recent_blog_2.jpg" alt="" /></a>
								<h5><a href="example">THE IMPORTANCE OF LUXURY SUV SALES EXPLAINED</a></h5>
								<div class="date"><span>November 1, 2012</span></div>
								<div class="post"><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit... Aliquam hendrerit sagittis urna,</p></div>
							</div>
							<div class="post_block last">
								<a href="product" class="thumb"><img src="images/pics/recent_blog_3.jpg" alt="" /></a>
								<h5><a href="example">THE IMPORTANCE OF LUXURY SUV SALES EXPLAINED</a></h5>
								<div class="date"><span>November 1, 2012</span></div>
								<div class="post"><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit... Aliquam hendrerit sagittis urna,</p></div>
							</div>
							<div class="clear"></div>
						</div>
						<div class="tabs_wrapper home_tabs section">
						  	<ul class="tabs">
						    	<li class="current">Dealers</li>
						    	<li>Service Stations</li>
						    	<li>Insurance</li>
						 	</ul>
						 	<div class="box visible">
						 		<a href="#" class="all">Show all...</a>
						   		<div class="results"><span>Found 433 dealers</span></div>
						   		<div class="clear"></div>
						   		<div class="tabs_carousel">
						   			<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_1.jpg" alt="" /></a>
										<a href="dealer" class="title">St. Louis Auto Dealers</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_2.jpg" alt="" /></a>
										<a href="dealer" class="title">Executive Cars Group</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_3.jpg" alt="" /></a>
										<a href="dealer" class="title">Rim Wear</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_4.jpg" alt="" /></a>
										<a href="dealer" class="title">Dj Cruz’s Auto Sales</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_1.jpg" alt="" /></a>
										<a href="dealer" class="title">St. Louis Auto Dealers</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_2.jpg" alt="" /></a>
										<a href="dealer" class="title">Executive Cars Group</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_3.jpg" alt="" /></a>
										<a href="dealer" class="title">Rim Wear</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_4.jpg" alt="" /></a>
										<a href="dealer" class="title">Dj Cruz’s Auto Sales</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_1.jpg" alt="" /></a>
										<a href="dealer" class="title">St. Louis Auto Dealers</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_2.jpg" alt="" /></a>
										<a href="dealer" class="title">Executive Cars Group</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_3.jpg" alt="" /></a>
										<a href="dealer" class="title">Rim Wear</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_4.jpg" alt="" /></a>
										<a href="dealer" class="title">Dj Cruz’s Auto Sales</a>
									</div>
								</div>
						  	</div>
						  	<div class="box">
						    	<a href="dealer" class="all">Show all...</a>
						   		<div class="results"><span>Found 50 stations</span></div>
						   		<div class="clear"></div>
						   		<div class="tabs_carousel">
						   			<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_1.jpg" alt="" /></a>
										<a href="dealer" class="title">St. Louis Auto Dealers</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_2.jpg" alt="" /></a>
										<a href="dealer" class="title">Executive Cars Group</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_3.jpg" alt="" /></a>
										<a href="dealer" class="title">Rim Wear</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_4.jpg" alt="" /></a>
										<a href="dealer" class="title">Dj Cruz’s Auto Sales</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_1.jpg" alt="" /></a>
										<a href="dealer" class="title">St. Louis Auto Dealers</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_2.jpg" alt="" /></a>
										<a href="dealer" class="title">Executive Cars Group</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_3.jpg" alt="" /></a>
										<a href="dealer" class="title">Rim Wear</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_4.jpg" alt="" /></a>
										<a href="dealer" class="title">Dj Cruz’s Auto Sales</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_1.jpg" alt="" /></a>
										<a href="dealer" class="title">St. Louis Auto Dealers</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_2.jpg" alt="" /></a>
										<a href="dealer" class="title">Executive Cars Group</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_3.jpg" alt="" /></a>
										<a href="dealer" class="title">Rim Wear</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_4.jpg" alt="" /></a>
										<a href="dealer" class="title">Dj Cruz’s Auto Sales</a>
									</div>
								</div>
						  	</div>
						  	<div class="box">
						    	<a href="dealer" class="all">Show all...</a>
						   		<div class="results"><span>Found 10 incurances</span></div>
						   		<div class="clear"></div>
						   		<div class="tabs_carousel">
						   			<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_1.jpg" alt="" /></a>
										<a href="dealer" class="title">St. Louis Auto Dealers</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_2.jpg" alt="" /></a>
										<a href="dealer" class="title">Executive Cars Group</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_3.jpg" alt="" /></a>
										<a href="dealer" class="title">Rim Wear</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_4.jpg" alt="" /></a>
										<a href="dealer" class="title">Dj Cruz’s Auto Sales</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_1.jpg" alt="" /></a>
										<a href="dealer" class="title">St. Louis Auto Dealers</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_2.jpg" alt="" /></a>
										<a href="dealer" class="title">Executive Cars Group</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_3.jpg" alt="" /></a>
										<a href="dealer" class="title">Rim Wear</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_4.jpg" alt="" /></a>
										<a href="dealer" class="title">Dj Cruz’s Auto Sales</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_1.jpg" alt="" /></a>
										<a href="dealer" class="title">St. Louis Auto Dealers</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_2.jpg" alt="" /></a>
										<a href="dealer" class="title">Executive Cars Group</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_3.jpg" alt="" /></a>
										<a href="dealer" class="title">Rim Wear</a>
									</div>
									<div class="slide">
										<a href="dealer" class="img"><img src="images/pics/dealer_4.jpg" alt="" /></a>
										<a href="dealer" class="title">Dj Cruz’s Auto Sales</a>
									</div>
								</div>
						  	</div>
						</div>
						<div class="video_box">
							<h2><strong>Video</strong> reviews</h2>
							<div class="post_block">
								<div class="preview">
									<a href="http://player.vimeo.com/video/54510052">
										<img src="images/pics/video_1.jpg" alt="" />
										<span class="hover"></span>
										<img src="images/video_play.png" alt="" class="video_play" />
									</a>
								</div>
								<h5><a href="#">THE IMPORTANCE OF LUXURY SUV SALES EXPLAINED</a></h5>
								<div class="post"><p>10 min 31 sec (12,4 Mb)</p></div>
							</div>
							<div class="post_block">
								<div class="preview">
									<a href="http://player.vimeo.com/video/13412780">
										<img src="images/pics/video_2.jpg" alt="" />
										<span class="hover"></span>
										<img src="images/video_play.png" alt="" class="video_play" />
									</a>
								</div>
								<h5><a href="#">THE IMPORTANCE OF LUXURY SUV SALES EXPLAINED</a></h5>
								<div class="post"><p>10 min 31 sec (12,4 Mb)</p></div>
							</div>
							<div class="post_block last">
								<div class="preview">
									<a href="http://player.vimeo.com/video/22884674">
										<img src="images/pics/video_3.jpg" alt="" />
										<span class="hover"></span>
										<img src="images/video_play.png" alt="" class="video_play" />
									</a>
								</div>
								<h5><a href="#">THE IMPORTANCE OF LUXURY SUV SALES EXPLAINED</a></h5>
								<div class="post"><p>10 min 31 sec (12,4 Mb)</p></div>
							</div>
							<div class="clear"></div>
						</div>
					</div>
					<div class="right">
						<div class="news_wrapper">
							<div class="news">
								<h2><strong>Auto</strong> news</h2>
								<div class="news_box">
									<a href="#" class="thumb">
										<img src="images/pics/news_1.jpg" alt="" />
									</a>
									<h5><a href="#">UNOFFICIAL PORSCHE 918 SPYDER PRICING POPS UP</a></h5>
									<div class="date"><span>November 1, 2012</span></div>
									<div class="post">
										<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit... </p>
									</div>
								</div>
								<div class="news_box bottom">
									<a href="#" class="thumb">
										<img src="images/pics/news_2.jpg" alt="" />
									</a>
									<h5><a href="#">UNOFFICIAL PORSCHE 918 SPYDER PRICING POPS UP</a></h5>
									<div class="date"><span>November 1, 2012</span></div>
									<div class="post">
										<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit... </p>
									</div>
								</div>
								<div class="all_wrapper"><a href="#" class="all_news">All news</a></div>
							</div>
						</div>
						<div class="banners_wrapper">
							<div class="get_news_box">
								<h3><strong>Get</strong> daily news</h3>
								<form method="get" action="./catalog" />
									<input type="text" onblur="if(this.value=='') this.value='Enter your email';" onfocus="if(this.value=='Enter your email') this.value='';" value="Enter your email" class="txb" />
									<input type="submit" value="subscribe" class="btn_subscribe" />
								</form>
							</div>
							<div class="side_banners">
								<a href="#"><img src="images/banners/banner.jpg" alt="" /></a>
							</div>
						</div>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
	<!--EOF CONTENT-->
	<!--BEGIN FOOTER-->
		
	<!--EOF FOOTER-->
</body>
</html>