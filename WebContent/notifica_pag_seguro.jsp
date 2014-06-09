<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="java.net.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.logging.*"%>

<%
if ("POST".equalsIgnoreCase(request.getMethod())) {
	//o método POST indica que a requisição é o retorno da validação NPI.
	Enumeration<?> en = request.getParameterNames();
	String token = "228D55AF663C4BCDADA8A4931EE83992";
	StringBuffer validaNPI = new StringBuffer("Comando=validar&Token=");
	validaNPI.append(token);
	
	while (en.hasMoreElements()) {
		String paramName = (String) en.nextElement();
		String paramValue = request.getParameter(paramName);
		validaNPI.append("&");
		validaNPI.append(paramName);
		validaNPI.append("=");
		validaNPI.append(paramValue);
	}

	URL u = new URL("https://pagseguro.uol.com.br/pagseguro-ws/checkout/NPI.jhtml");
	URLConnection uc = u.openConnection();
	uc.setDoOutput(true);
	uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	PrintWriter pw = new PrintWriter(uc.getOutputStream());
	pw.println(validaNPI.toString());
	pw.close();

	BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
	String res = in.readLine();
	in.close();

	//Verifica se o status da transação está VERIFICADO
	String transacaoID = request.getParameter("TransacaoID");
	if (res.equals("VERIFICADO")) {
		//o post foi validado
	} else if (res.equals("FALSO")) {
		//o post nao foi validado
	} else {
		//erro na integração com PagSeguro.
	}

} else if ("GET".equalsIgnoreCase(request.getMethod())) {
	//o método GET indica que a requisição é o retorno do Checkout PagSeguro para o site vendedor.
	//no término do checkout o usuário é redirecionado para este bloco.
	%>
	<h3>Obrigado por efetuar a compra</h3>
	<%
}

%>