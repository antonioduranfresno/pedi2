package net.gefco.pedi2.util;

import java.util.Calendar;
import java.util.Date;

public class Fechas {

	public static Calendar toCalendar(Date date){ 
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  cal.set(Calendar.HOUR, 0);
		  cal.set(Calendar.MINUTE, 0);
		  cal.set(Calendar.SECOND, 0);
		  return cal;
	}
	
	 public static Date sumarDiasFecha(Date fecha, int dias){

	      Calendar calendar = Calendar.getInstance();
	      calendar.setTime(fecha); // Configuramos la fecha que se recibe
	      calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
	      return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos

	 }
	
}
