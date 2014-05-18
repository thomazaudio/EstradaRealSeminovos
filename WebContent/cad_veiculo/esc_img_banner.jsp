<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <script type="text/javascript">
        
        
 function verifica_tamanho(form){

window.alert("teste");

var imagem=new Image();
imagem.src=form.arq.value;

window.alert(form.arq);
tamanho_imagem = imagem.fileSize;
img_tan = tamanho_imagem;
if (tamanho_imagem < 0)
 {
 
 window.alert("Imagem muito pequena!!");
 return false;

 }
else if (tamanho_imagem > 100)
{

window.alert("O tamanho da Imagem é muito grande ...  "+tamanho_imagem+" Bytes!!");

//java script:location.reload()

return false;

}
else 
{

window.alert("Ativa figura!");
//ativafigura(form);

return true;

}

}

function comprova_extensao(formulario) {
   extensoes_permitidas = new Array(".gif", ".jpg", ".doc", ".pdf");
   meuerro = "";
   
   var arquivo = formulario.arq.value;
   
   window.alert(arquivo);
   
   if (!arquivo) {
      //Se não tenho arquivo, é porque não se selecionou um arquivo no formulário.
      window.alert("Não foi selecionado nenhum arquivo");
       
       return false;
   }else{
      //recupero a extensão deste nome de arquivo
      extensao = (arquivo.substring(arquivo.lastIndexOf("."))).toLowerCase();
      //alert (extensao);
      //comprovo se a extensão está entre as permitidas
      permitida = false;
      for (var i = 0; i < extensoes_permitidas.length; i++) {
         if (extensoes_permitidas[i] == extensao) {
         permitida = true;
         break;
         }
      }
      if (!permitida) {
           window.alert("O arquivo selecionado não é suportado. \nExtensões suportadas: " + extensoes_permitidas.join());
          return false;
        
       }else{
       
         return verifica_tamanho(formulario.arq.value);  
         
       }
   }
  
} 



function valida_arquivo(form){


window.alert("Validação");

   if(comprova_extensao(form))
   {
   
   return verifica_tamanho(form);
   
   }
   else
   {
   
   return false;
   
   }

}
        
        
        </script>
        
        
    </head>
    <body>
    
    <%
    
    long id_veiculo = Long.parseLong(request.getParameter("ID_VEICULO"));
    
    session.setAttribute("id_veiculo",id_veiculo);
    
    //Cadastro de banner(Ato de cadastro de veiculo)
    session.setAttribute("soli",1);
    
    
    %>   


<h2><strong>Escolha uma imagem.</strong></h2>
<form action="../ServBanner" method="POST" enctype="multipart/form-data" onsubmit="return valida_arquivo(this);">
<input type="hidden" name="id_veiculo" value="<%=id_veiculo%>"/>
<input class="btn red" name="arq" type="file" id="file"/>
<input class="btn_2 blue" type="image" value="Enviar<%=id_veiculo %>"/>
</form>
        
        
    </body>
</html>
