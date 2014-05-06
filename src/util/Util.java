package util;

import java.util.Calendar;

public class Util {
	
	
public static String getDataFormated(Calendar c ) {
		
		String dataIni;
		
	    dataIni = String.format("%d/%d/%d",c.get(Calendar.DAY_OF_MONTH),c.get(Calendar.MONTH)+1,c.get(Calendar.YEAR));
		
		return dataIni;
	}

}
