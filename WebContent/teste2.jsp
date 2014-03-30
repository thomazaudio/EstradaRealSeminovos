<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Exibir e ocultar conteúdo com jQuery</title>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>

<style type="text/css">





</style>
<script type="text/javascript">

$(document).ready(function(){
 $('#conteudo').hide();

 $('#mostrar').click(function(event){
 event.preventDefault();
 $("#conteudo").show("slow");
 });

 $('#ocultar').click(function(event){
 event.preventDefault();
 $("#conteudo").hide("slow");
 });
 });

</script>

</head>

<body>

<p>

<a href="#" id="ocultar">Ocultar conteúdo</a>
</p>



<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>
<p>asdasdasdasdasdasdasdasdasdasd</p>


<div class="bg_top_footer" id="rodape">
<h3><a href="#" id="mostrar">Mostrar Opções>></a></h3> |

<div id="conteudo">


<div class="f_widget first">
<h3><strong>Lista de </strong>interesses</h3>
<a href="#" class="footer_logo">AutoDealer</a>
<p>Arraste para cá os veículos que deseja adicionar na lista.</p>

<!--Inicio da div com o conteudo dos veiculos  -->

<!--Fim da div com o centeudo dos veiculos  -->
</div>

</div>

</div>
</body>
</html>