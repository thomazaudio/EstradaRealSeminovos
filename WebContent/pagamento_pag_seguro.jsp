<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="application/x-www-form-urlencoded; charset=ISO-8859-1">


<title></title>
</head>
<body>


<form method="post" target="pagseguro"  
action="https://pagseguro.uol.com.br/v2/checkout/payment.html">  
          
        <!-- Campos obrigatórios -->  
        <input name="receiverEmail" value="thomaz-guitar@hotmail.com" type="hidden">  
        <input name="currency" value="BRL" type="hidden">  
  
        <!-- Itens do pagamento (ao menos um item é obrigatório) -->  
        <input name="itemId1" value="0001" type="hidden">  
        <input name="itemDescription1" value="Notebook Prata" type="hidden">  
        <input name="itemAmount1" value="24300.00" type="hidden">  
        <input name="itemQuantity1" value="1" type="hidden">  
        <input name="itemWeight1" value="1000" type="hidden">  
        <input name="itemId2" value="0002" type="hidden">  
        <input name="itemDescription2" value="Notebook Rosa" type="hidden">  
        <input name="itemAmount2" value="25600.00" type="hidden">  
        <input name="itemQuantity2" value="2" type="hidden">  
        <input name="itemWeight2" value="750" type="hidden">  
  
        <!-- Código de referência do pagamento no seu sistema (opcional) -->  
        <input name="reference" value="REF1234" type="hidden">  
          
        <!-- Informações de frete (opcionais) -->  
        <input name="shippingType" value="1" type="hidden">  
        <input name="shippingAddressPostalCode" value="01452002" type="hidden">  
        <input name="shippingAddressStreet" value="Av. Brig. Faria Lima" type="hidden">  
        <input name="shippingAddressNumber" value="1384" type="hidden">  
        <input name="shippingAddressComplement" value="5o andar" type="hidden">  
        <input name="shippingAddressDistrict" value="Jardim Paulistano" type="hidden">  
        <input name="shippingAddressCity" value="Sao Paulo" type="hidden">  
        <input name="shippingAddressState" value="SP" type="hidden">  
        <input name="shippingAddressCountry" value="BRA" type="hidden">  
  
        <!-- Dados do comprador (opcionais) -->  
        <input name="senderName" value="José Comprador" type="hidden">  
        <input name="senderAreaCode" value="11" type="hidden">  
        <input name="senderPhone" value="56273440" type="hidden">  
        <input name="senderEmail" value="comprador@uol.com.br" type="hidden">  
  
        <!-- submit do form (obrigatório) -->  
        <input alt="Pague com PagSeguro" name="submit"  type="image"  
src="https://p.simg.uol.com.br/out/pagseguro/i/botoes/pagamentos/120x53-pagar.gif"/>  
          
</form> 

</body>
</html>