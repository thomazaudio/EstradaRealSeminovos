<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="Modelo.DestaqueDAO" %>
<html lang="en">
<head>

 <link rel="stylesheet" type="text/css" href="../css/style.css" />
	<link rel="stylesheet" type="text/css" href="../css/style980.css" />
	<link rel="stylesheet" type="text/css" href="../css/style800.css" />
	<link rel="stylesheet" type="text/css" href="../css/style700.css" />
	<link rel="stylesheet" type="text/css" href="../css/style600.css" />
	<link rel="stylesheet" type="text/css" href="../css/style500.css" />
	<link rel="stylesheet" type="text/css" href="../css/style400.css" />
	<link rel="stylesheet" type="text/css"
		href="../css/jquery.fancybox-1.3.4.css" media="screen" />

<meta http-equiv="pragma" content="no-cache"></meta>
  <title></title>
  <meta http-equiv="Content-type" content="text/html;charset=UTF-8" />

<script src="js/jquery.min.js"></script>
<script src="js/jquery.Jcrop.js"></script>
<script type="text/javascript">
  jQuery(function($){

      $('#target').Jcrop({
      // start off with jcrop-light class
      bgOpacity: 0.5,
      bgColor: 'white',
      addClass: 'jcrop-light',
       onSelect: updateCoords
    },
    function(){
      api = this;
      api.setSelect([130,65,130+350,65+285]);
      api.setOptions({ bgFade: true });
      api.ui.selection.addClass('jcrop-selection');
    });


    // Create variables (in this scope) to hold the API and image size
    var jcrop_api,
        boundx,
        boundy,

        // Grab some information about the preview pane
        $preview = $('#preview-pane'),
        $pcnt = $('#preview-pane .preview-container'),
        $pimg = $('#preview-pane .preview-container img'),

        xsize = $pcnt.width()+200,
        ysize = $pcnt.height();
    
    console.log('init',[xsize,ysize]);
    $('#target').Jcrop({
      
      aspectRatio: xsize / ysize
    },function(){
      // Use the API to get the real image size
      var bounds = this.getBounds();
      boundx = bounds[0];
      boundy = bounds[1];
      // Store the API in the jcrop_api variable
      jcrop_api = this;

      // Move the preview into the jcrop container for css positioning
      $preview.appendTo(jcrop_api.ui.holder);
    });

   
   

  });
  
  //getting the cordinats of the box
                function updateCoords(c){
                    $('#x').val(c.x);
                    $('#y').val(c.y);
                    $('#w').val(c.w);
                    $('#h').val(c.h);
                };


</script>
<link rel="stylesheet" href="demo_files/main.css" type="text/css" />
<link rel="stylesheet" href="demo_files/demos.css" type="text/css" />
<link rel="stylesheet" href="css/jquery.Jcrop.css" type="text/css" />
<style type="text/css">

/* Apply these styles only when #preview-pane has
   been placed within the Jcrop widget */
.jcrop-holder #preview-pane {
  display: block;
  position: absolute;
  z-index: 2000;
  top: 10px;
  right: -280px;
  padding: 6px;
  border: 1px rgba(0,0,0,.4) solid;
  background-color: white;

  -webkit-border-radius: 6px;
  -moz-border-radius: 6px;
  border-radius: 6px;

  -webkit-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
  -moz-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
  box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
}

/* The Javascript code will set the aspect ratio of the crop
   area based on the size of the thumbnail preview,
   specified here */
#preview-pane .preview-container {
  width: 250px;
  height: 170px;
  overflow: hidden;
}

</style>

</head>
<body>






 <% 
  
  //----------------------------------------------------//
 //----------------------------------------------------//
 
 long id_veiculo = Long.parseLong(request.getParameter("ID_VEICULO"));
 
 String page_pos ="arearestritausuario/destaque_sucesso.jsf";
 
 String soli;
 //Verifica se já existe um destaque do tipo banner ativo para este veículo no sistema
 
 if(new DestaqueDAO().existeBannerDestaque(id_veiculo))
	 soli="2";//Editar
 else
	 soli="1";//Cadastrar
 
  
  %>
  <jsp:include page="../simple_header.jsp"></jsp:include>
  
  <div id="content">
  
  
<a class="btn blue" href="esc_img_banner.jsp?ID_VEICULO=<%=id_veiculo%>"> << Escolher outra image</a>
<div class="content">
<div class="message">
<h3>Por favor, recorte a imagem para adaptação no banner</h3>
<p>Dicas: </p>
<p>*Tente selecionar as partes mais importantes do seu veículo.</p>
<p>*O objetivo do banner-destaque não é mostrar a imagem em sua totalidade mas sim destacar determinada região de maior valor no seu veículo.</p>
<p>*Tente destacar rodas, painél.</p>
<p>*Você poderá alterar a imagem do <strong>banner-destaque</strong> a qualquer momento!</p>

</div>
  

  <img  src="../ServImg?SOLI=10&&mostra_step=0&&ID_VEICULO=<%=id_veiculo%>" id="target" alt="[Jcrop Example]" />

  <form action="../ServBanner" method="get" onsubmit="return checkCoords();">

                        <input type="hidden" id="x" name="l" />
                        <input type="hidden" id="y" name="t" />
                        <input type="hidden" id="w" name="w" />
                        <input type="hidden" id="h" name="h" />
                         <input type="hidden"  name="soli" value="<%=soli%>" />
                        
                          <input type="hidden"  name="page_pos" value="<%=page_pos%>" />
                        <input type="hidden"  id="ID_VEICULO" name="ID_VEICULO" value="<%=id_veiculo %>" />
                      
                        <input type="submit" class="btn blue" value="Próximo" />
                        
                        <input type="hidden" id="itemName" name="itemName" value="teste" />
                        </form>

  <div id="preview-pane">
    <div class="preview-container">
     
  </div>
  </div>
  
 
  

  <div class="description">
  
  </div>



<div class="clearfix"></div>

</div>
</div>

</body>
</html>