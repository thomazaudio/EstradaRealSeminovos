package util;

public class SystemUtil {
	
	public StringBuffer getHtmlErroSystem(){
		
		StringBuffer buffer;
		buffer = new StringBuffer();	 
		buffer.append("<h3>O sistema encontra-se em manutenção, tente novamente mais tarde.</h3>");	 
		
		return buffer;	 
	}
	
}
