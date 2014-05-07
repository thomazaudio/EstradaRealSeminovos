package util;

import java.util.Calendar;

public class Util {
	
	
public static String getDataFormated(Calendar c ) {
		
		String dataIni;
		
	    dataIni = String.format("%d/%d/%d - %2d:%2d",c.get(Calendar.DAY_OF_MONTH),c.get(Calendar.MONTH)+1,c.get(Calendar.YEAR),c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE));
		
		return dataIni;
	}

}
