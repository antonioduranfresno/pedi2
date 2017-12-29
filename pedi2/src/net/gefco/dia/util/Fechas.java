package net.gefco.dia.util;

import java.util.Calendar;
import java.util.Date;

public class Fechas {
	
	public static Date getUltimaFecha(int mes, int anyo) {
				
	    Calendar calendar = Calendar.getInstance();

	    calendar.set(anyo, mes - 1, 1);
	    calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
	    
	    return calendar.getTime();
	}

}
