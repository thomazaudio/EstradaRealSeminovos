<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Modelo.ImgDAO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" media="all" href="style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>

</head>
<body>
<%

long id_veiculo = Long.parseLong(request.getParameter("id_veiculo"));

if(id_veiculo==0)
	id_veiculo =20;

long[] ids_imgs = new ImgDAO().getIdsImgs(id_veiculo);

for(int i=0;i<ids_imgs.length;i++)
{
	
%>

 <div class="imageholder">
		<figure>
			<img src="ServImg?SOLI=2&&ID_IMG=<%=ids_imgs[i]%>"/>
			<figcaption>
				${fileName} <br/>
				<span>Tamanho original: ${fileOriSize} KB</span><br/>
				<span>Tamanho após upload: ${fileUploadSize} KB</span>
			</figcaption>
            
		</figure>
	</div>

<% 

}
%>

</body>
</html>