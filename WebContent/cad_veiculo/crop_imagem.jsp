<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="Controle.ServAnuncio"%>
<html lang="en">
<head>
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

<div class="container">
<div class="row">
<div class="span12">
<div class="jc-demo-box">

 <% 
  
  //----------------------------------------------------//
 //----------------------------------------------------//
 
 long id_veiculo = Long.parseLong(request.getParameter("ID_VEICULO"));
 
 
 //Página posterior
 String page_pos="";
 int tipo_veiculo= (Integer) session.getAttribute("tipo_veiculo");
 
 if(tipo_veiculo==1)
 page_pos = ServAnuncio.PAGE_STEP_4+"?mostra_step=1";
 else if(tipo_veiculo==2)
 page_pos = ServAnuncio.PAGE_STEP_5+"?mostra_step=1";	 
  
  
  %>
  

  <img src="../ServImg?SOLI=10&&mostra_step=0&&ID_VEICULO=<%=id_veiculo%>" id="target" alt="[Jcrop Example]" />

  <div id="preview-pane">
    <div class="preview-container">
     
  </div>
  </div>
  
 
  <form action="../ServBanner" method="get" onsubmit="return checkCoords();">

                        <input type="hidden" id="x" name="l" />
                        <input type="hidden" id="y" name="t" />
                        <input type="hidden" id="w" name="w" />
                        <input type="hidden" id="h" name="h" />
                         <input type="hidden"  name="soli" value="1" />
                        <input type="hidden"  id="ID_VEICULO" name="ID_VEICULO" value="<%=id_veiculo %>" />
                        <input type="hidden"  id="page_pos" name="page_pos" value="<%=page_pos%>" />
                        <input type="hidden"  name="page_pos" value="1" />
                        <input type="submit" class="button" value="Próximo" />
                        
                       
                        </form>

  <div class="description">
  
  </div>



<div class="clearfix"></div>

</div>
</div>
</div>
</div>

</body>
</html>