package net.gefco.pedi2.edi;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.gefco.pedi2.modelo.Factura;
import net.gefco.pedi2.modelo.Pedido;

import org.apache.commons.io.FileUtils;

public class FicheroEdi {
	
	public void generarFicheroEDI(Factura factura, List<Pedido> listaPedidos, String ruta, String nombreFichero) throws IOException {
		
		Map<String, String> 	conf				= new HashMap<String, String>();
		List<String> 			ltexto 				= new ArrayList<String>();
		Integer					contLineas			= 0;
		Integer					contPalets			= 0;
		Integer					contLineasFichero	= 0;
		
		String					tipoDoc				= (factura.getFact_numeroFactura()!=null ? "factura" : "prefactura");		
						
		conf.put ("prefactura-BGM"				, "325");
		conf.put ("factura-BGM"					, "380");
		conf.put ("prefactura-saltoLinea"		, "'");
		conf.put ("factura-saltoLinea"			, "'");
		conf.put ("formatoFechaYYYYMMDD"		, "102");
		conf.put ("formatoFechaYYYYMMDDhhmm"	, "203");
		conf.put ("datosGefco"					, "GEFCO ESPAÑA, S.A.:Inscrito - Madrid tomo 749 general,:726 -secc3º  folio1 hoja 67561+C/ MANISES, 3+MADRID++28224");
				
		//cabecera del fichero
		ltexto.add("UNH+" 
			+ (tipoDoc.equals("factura") ?  factura.getFact_numeroFactura() : factura.getFact_numeroPrefactura() ) 
			+ "+INVOIC:D:93A:UN:EAN007" 
			+ conf.get(tipoDoc + "-saltoLinea") );
		contLineasFichero ++;
			
		ltexto.add("BGM+" 
			+ conf.get(tipoDoc + "-BGM") + "+" 
			+ (tipoDoc.equals("factura") ?  factura.getFact_numeroFactura() : factura.getFact_numeroPrefactura() ) 
			+ conf.get(tipoDoc + "-saltoLinea"));
		contLineasFichero ++;
		
		ltexto.add("DTM+137:" 
			+ new SimpleDateFormat("yyyyMMdd").format( (tipoDoc.equals("factura") ?  factura.getFact_fechaFactura() : factura.getFact_fechaPrefactura() ) )  
			+ ":" + conf.get("formatoFechaYYYYMMDD") 
			+ conf.get(tipoDoc + "-saltoLinea"));
		contLineasFichero ++;
		
		ltexto.add("FTX+AAI+++"
			+(factura.getFact_esTransferencia() ? "Transferencia" : "Macrogrupo" )  + " "
			+ factura.getCliente().getClie_alias() + " " 
			+ new SimpleDateFormat("yyyyMMdd").format(factura.getFact_fechaLimite())
			+ conf.get(tipoDoc + "-saltoLinea"));
		contLineasFichero ++;
			
		ltexto.add("NAD+SU+0000780843651::9++" + conf.get("datosGefco") 
			+ conf.get(tipoDoc + "-saltoLinea"));
		contLineasFichero ++;
		
		ltexto.add("RFF+VA:A78084365" 
			+ conf.get(tipoDoc + "-saltoLinea"));
		contLineasFichero ++;
		
		ltexto.add("NAD+II+0000780843651::9++" + conf.get("datosGefco")
			+ conf.get(tipoDoc + "-saltoLinea"));
		contLineasFichero ++;
			
		ltexto.add("RFF+VA:A78084365" 
			+ conf.get(tipoDoc + "-saltoLinea"));
		contLineasFichero ++;
			
		ltexto.add("NAD+SCO+0000780843651::9++" + conf.get("datosGefco")
			+ conf.get(tipoDoc + "-saltoLinea"));
		contLineasFichero ++;
			
		ltexto.add("RFF+VA:A78084365" 
			+ conf.get(tipoDoc + "-saltoLinea"));
		contLineasFichero ++;
		
		ltexto.add("NAD+BY+" 
			+ factura.getCliente().getClie_nadBy()
			+ "::9++" 
			+ subdivideTexto(factura.getCliente().getClie_nombre(), 35) 
			+ "+" + subdivideTexto(factura.getNodoDestino().getNodo_direccion(), 35)
			+ "+" + subdivideTexto(factura.getNodoDestino().getNodo_direccion(), 35)
			+ conf.get(tipoDoc + "-saltoLinea"));
		contLineasFichero ++;
			
		ltexto.add("RFF+VA:" 
			+ factura.getCliente().getClie_nif()
			+ conf.get(tipoDoc + "-saltoLinea"));	
		contLineasFichero ++;
			
		ltexto.add("NAD+BCO+"	
			+ factura.getFact_codigoNodoCliente()
			+ "::9++" 
			+ subdivideTexto(factura.getCliente().getClie_nombre(), 35) 
			+ "+" + subdivideTexto(factura.getNodoDestino().getNodo_direccion(), 35)
			+ "+" + subdivideTexto(factura.getNodoDestino().getNodo_poblacion(), 35)
			+ "+" + factura.getNodoDestino().getNodo_codigoPostal()
			+ conf.get(tipoDoc + "-saltoLinea"));
		contLineasFichero ++;
		
		ltexto.add("RFF+VA:" 
			+ factura.getCliente().getClie_nif()
			+ conf.get(tipoDoc + "-saltoLinea"));		
		contLineasFichero ++;
		
		ltexto.add("NAD+PR+" 
			+ factura.getCliente().getClie_nadPr()
			+ "::9" 
			+ conf.get(tipoDoc + "-saltoLinea"));
		contLineasFichero ++;
		
		ltexto.add("NAD+IV+"	
			+ factura.getFact_codigoNodoCliente()
			+ "::9++" 
			+ subdivideTexto(factura.getCliente().getClie_nombre(), 35) 
			+ "+" + subdivideTexto(factura.getNodoDestino().getNodo_direccion(), 35)
			+ "+" + subdivideTexto(factura.getNodoDestino().getNodo_poblacion(), 35)
			+ "+" + factura.getNodoDestino().getNodo_codigoPostal()
			+ conf.get(tipoDoc + "-saltoLinea"));
		contLineasFichero ++;
			
		ltexto.add("NAD+DP+"	
			+ factura.getFact_codigoNodoCliente()
			+ "::9++" 
			+ subdivideTexto(factura.getCliente().getClie_nombre(), 35) 
			+ "+" + subdivideTexto(factura.getNodoDestino().getNodo_direccion(), 35)
			+ "+" + subdivideTexto(factura.getNodoDestino().getNodo_poblacion(), 35)
			+ "+" + factura.getNodoDestino().getNodo_codigoPostal()
			+ conf.get(tipoDoc + "-saltoLinea"));
		contLineasFichero ++;
		
		ltexto.add("CUX+2:EUR:4"
			+ conf.get(tipoDoc + "-saltoLinea"));
		contLineasFichero ++;
		
		ltexto.add("PAT+35"
			+ conf.get(tipoDoc + "-saltoLinea"));
		contLineasFichero ++;
			
		ltexto.add("DTM+13:"
			+ new SimpleDateFormat("yyyyMMdd").format( factura.getFact_fechaVencimiento())
			+ ":" + conf.get("formatoFechaYYYYMMDD") 
			+ conf.get(tipoDoc + "-saltoLinea"));
		contLineasFichero ++;
		
		ltexto.add("MOA+23:"
			+ factura.getFact_totalDocumento()
			+ conf.get(tipoDoc + "-saltoLinea"));
		contLineasFichero ++;

		//Detalle
		for (Pedido p : listaPedidos) {
			
			contLineas 	= contLineas + 1;
			contPalets	= contPalets + p.getPedi_totalPalets();
			
			ltexto.add("LIN+"
				+ contLineas
				+ conf.get(tipoDoc + "-saltoLinea"));								
			contLineasFichero ++;
		
			ltexto.add("QTY+46:"
				+ p.getPedi_totalPalets() 
				+ conf.get(tipoDoc + "-saltoLinea"));
			contLineasFichero ++;
		
			ltexto.add("DTM+35:"
				+ new SimpleDateFormat("yyyyMMddhhmm").format( p.getPedi_fechaEntrega() )  
				+ ":" + conf.get("formatoFechaYYYYMMDDhhmm") 
				+ conf.get(tipoDoc + "-saltoLinea"));	
			contLineasFichero ++;
		
			ltexto.add("DTM+200:"
				+ new SimpleDateFormat("yyyyMMddhhmm").format( p.getPedi_fechaRecogida() )  
				+ ":" + conf.get("formatoFechaYYYYMMDDhhmm") 
				+ conf.get(tipoDoc + "-saltoLinea"));					
			contLineasFichero ++;
		
			ltexto.add("MOA+66:"
				+ p.getPedi_importe() 
				+ conf.get(tipoDoc + "-saltoLinea"));
			contLineasFichero ++;
		
			ltexto.add("RFF+ON:"
				+ p.getPedi_codigo() 
				+ conf.get(tipoDoc + "-saltoLinea"));
			contLineasFichero ++;
			
			ltexto.add("PAC+++201" 
				+ conf.get(tipoDoc + "-saltoLinea"));
			contLineasFichero ++;
			
			ltexto.add("TDT+20++30+++++:::"
				+ p.getPedi_matricula() 
				+ conf.get(tipoDoc + "-saltoLinea"));
			contLineasFichero ++;
		
		}
		
		//Pie fichero
		ltexto.add("UNS+S" 
			+ conf.get(tipoDoc + "-saltoLinea"));
		contLineasFichero ++;
				
		ltexto.add("CNT+2:"
			+ contLineas
			+ conf.get(tipoDoc + "-saltoLinea"));
		contLineasFichero ++;
		
		ltexto.add("MOA+139:"
			+ factura.getFact_totalDocumento()
			+ conf.get(tipoDoc + "-saltoLinea"));
		contLineasFichero ++;
		
		ltexto.add("MOA+79:"
			+ factura.getFact_baseImponible()
			+ conf.get(tipoDoc + "-saltoLinea"));
		contLineasFichero ++;
		
		ltexto.add("MOA+125:"
			+ factura.getFact_baseImponible()
			+ conf.get(tipoDoc + "-saltoLinea"));
		contLineasFichero ++;
		
		ltexto.add("MOA+98:"
			+ factura.getFact_baseImponible()
			+ conf.get(tipoDoc + "-saltoLinea"));
		contLineasFichero ++;
		
		ltexto.add("MOA+176:"
			+ factura.getFact_cuotaIva()
			+ conf.get(tipoDoc + "-saltoLinea"));
		contLineasFichero ++;
		
		ltexto.add("MOA+260:0" 
			+ conf.get(tipoDoc + "-saltoLinea"));
		contLineasFichero ++;
			
		ltexto.add("TAX+7+VAT+++:::21" 
			+ conf.get(tipoDoc + "-saltoLinea"));
		contLineasFichero ++;
		
		ltexto.add("UNT" 
			+ "+" + (contLineasFichero+1) //sumamos 1 para contar la propia línea
			+ "+" + (tipoDoc.equals("factura") ?  factura.getFact_numeroFactura() : factura.getFact_numeroPrefactura() ) 
			+ conf.get(tipoDoc + "-saltoLinea") );		
		
		exportarFicheroTexto(ltexto, nombreFichero +".txt", ruta, "Cp1252"); //ANSI
	}
	
	public static void exportarFicheroTexto(List<String> texto, String fichero, String ruta, String formato) throws IOException {

		//Si no existe la carpeta, la creamos
		File carpeta = new File(ruta);
		
		if (!carpeta.isDirectory()) {
			carpeta.mkdirs();
		}
		
		File f = new File(ruta + fichero);
			
		String saltoLinea = System.getProperty("line.separator");
		
		String data = "";
		for (String linea : texto) {
			data = data + linea + saltoLinea;
		}
		
		FileUtils.writeStringToFile(f,data,formato);		
		
    }
	
	public String subdivideTexto(String texto, Integer longitud){
		
		String textoFormateado = texto.replaceAll("\r", " ").replaceAll("\n", " ");
		
		StringBuilder cadenas = new StringBuilder();
		
		int i = 0;
		
		while (i < textoFormateado.length()) {
			cadenas.append(textoFormateado.substring(i, Math.min(i + longitud, textoFormateado.length()))+":");
		    i+= longitud;
		}

		return cadenas.toString().substring(0, cadenas.toString().length()-1); //Para quitar el último caracter que es :
	}
	
}
