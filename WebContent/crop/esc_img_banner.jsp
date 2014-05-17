<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import ="Modelo.DestaqueDAO" %>
<!DOCTYPE html>
<html>
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
	
    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>-----</title>
        
        
        <script type="text/javascript">
        
        


function comprova_extensao(formulario) {
   extensoes_permitidas = new Array(".png", ".jpg");
   meuerro = "";
   
   var arquivo = formulario.arq.value;
   
 
   
   if (!arquivo) {
      //Se n�o tenho arquivo, � porque n�o se selecionou um arquivo no formul�rio.
      window.alert("N�o foi selecionado nenhum arquivo");
       
       return false;
   }else{
      //recupero a extens�o deste nome de arquivo
      extensao = (arquivo.substring(arquivo.lastIndexOf("."))).toLowerCase();
      //alert (extensao);
      //comprovo se a extens�o est� entre as permitidas
      permitida = false;
      for (var i = 0; i < extensoes_permitidas.length; i++) {
         if (extensoes_permitidas[i] == extensao) {
         permitida = true;
         break;
         }
      }
      if (!permitida) {
           window.alert("O arquivo selecionado n�o � suportado. \nExtens�es suportadas: " + extensoes_permitidas.join());
          return false;
        
       }else{
       
         return verifica_tamanho(formulario.arq.value);  
         
       }
   }
  
} 



        
        
        </script>
        
        
    </head>
    <body>
    
    <%
    
    long id_veiculo = Long.parseLong(request.getParameter("ID_VEICULO"));
    
    session.setAttribute("id_veiculo",id_veiculo);
    
    session.setAttribute("soli",0);
    
    boolean existeBannerDestaque = new DestaqueDAO().existeBannerDestaque(id_veiculo);
    
 
    
    %>   
    
    
    


<jsp:include page="../simple_header.jsp"></jsp:include>
<div id="content">

<div class="content">


<h2><strong>Escolha uma imagem para o Banner</strong></h2>

<br></br>

<% 
    if(existeBannerDestaque)
    {
    
    %>
<h3><strong>Imagem anterior</strong></h3>



<img width="400"  src="../ServImg?SOLI=11&&mostra_step=0&&ID_VEICULO=<%=id_veiculo%>"   />
   <% }%>

	
	                             <p>Por favor, escolha a imagem do ve�culo a ser exibida no banner da p�gina inicial.</p>
	                             <p>Dicas: </p>
	                             <p>*N�o escolha imagens com resolu��es muito baixas.</p>
	                             <p>*Escolha a imagem que melhor descreve o perfil do ve�culo.</p>
	                            
	                           <div class="message">
	                         
	                          
	                            
                               <form name="form" action="../ServBanner" method="POST" enctype="multipart/form-data" onsubmit="return comprova_extensao(this);">
								<input type="hidden" name="id_veiculo" value="<%=id_veiculo%>"/>
								<p><input class="btn red"  name="arq"   type="file" id="arq"/></p>
								
								<p><input  class="btn blue" type="image" value="Pr�ximo"/></p>
								</form>
								</div>
        
                              
                                                              </div> 
                                                              
                              
                              
                            
                               
                                
                        
                        
                        



</div>









        
    </body>
</html>
