package net.gefco.dia.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Numeros {
	
	public static String formatea(Double valor){
		
		DecimalFormat format = new DecimalFormat("#,###,###,##0.00");
		
		return format.format(valor);
	}

	/*
	public static double round(double value, int places) {
		
	    if (places < 0) throw new IllegalArgumentException();

	    Double valor = Math.round(value * 1000.0) / 1000.0;
	    
	    BigDecimal bd = new BigDecimal((valor));
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	    
	}*/
	
	public static double redondea(double value){ //Siempre a dos decimales
		
		String valorCadena 		= Double.toString(value);
		String valorDecimales	= "";
		Double valorFinal		= 0.0;
		
		if(valorCadena.contains(".")){
			
			Integer posicionPunto = valorCadena.indexOf(".");
			
			valorDecimales = valorCadena.substring(posicionPunto+1, valorCadena.length());
			
			if(valorDecimales.length()>2){				
				if(valorDecimales.charAt(2)=='5'){					
					valorFinal = value + 0.001;
				}else{				
					valorFinal = value;				
				}			
			}else{			
				valorFinal = value;			
			}
			
		}else{			
			valorFinal = value;			
		}
		
	    Double valor = Math.round(valorFinal * 1000.0) / 1000.0;
	    
	    BigDecimal bd = new BigDecimal((valor));
	    bd = bd.setScale(2, RoundingMode.HALF_UP);
	    return bd.doubleValue();				
	}
	
}
