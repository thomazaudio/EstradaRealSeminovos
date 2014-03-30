package IO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.mail.Part;

public class ImgIO {

	public static String getValue(Part part)  {
		
		try{
	    BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"));
	    StringBuilder value = new StringBuilder();
	    char[] buffer = new char[1024];
	    for (int length = 0; (length = reader.read(buffer)) > 0;) {
	        value.append(buffer, 0, length);
	    }
	    return value.toString();
	    
	}
	    catch(Exception e){
			return null;
		}
	}
}
