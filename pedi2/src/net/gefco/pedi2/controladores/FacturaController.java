package net.gefco.pedi2.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.gefco.pedi2.edi.FicheroEdi;
import net.gefco.pedi2.modelo.Factura;
import net.gefco.pedi2.modelo.Pedido;
import net.gefco.pedi2.modelo.Usuario;
import net.gefco.pedi2.negocio.AgenciaService;
import net.gefco.pedi2.negocio.ClienteService;
import net.gefco.pedi2.negocio.FacturaService;
import net.gefco.pedi2.negocio.NodoService;
import net.gefco.pedi2.negocio.PedidoService;
import net.gefco.pedi2.util.CfgUtil;
import net.gefco.pedi2.util.Fechas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Controller
@SessionAttributes("usuarioSesion")
public class FacturaController {
	
	@Autowired
	private FacturaService 				facturaService;
	
	@Autowired
	private AgenciaService 				agenciaService;
	
	@Autowired
	private ClienteService 				clienteService;
	
	@Autowired
	private NodoService 				nodoService;
	
	@Autowired
	private PedidoService 				pedidoService;
	
	@RequestMapping("/facturaLista")
	public String indice(Model model){		
		
		model.addAttribute("factura", new Factura()); //Al iniciar página siempre pasamos instancia vacía
		
		model.addAttribute("listaAgencias", agenciaService.listado());
		model.addAttribute("listaClientes", clienteService.listado());
		model.addAttribute("listaNodos", nodoService.listado());
		
		return "facturaLista";
	}
	
	@RequestMapping(value= "/facturaLista/carga_tabla", method = RequestMethod.POST)
	public String cargaTablaFacturas(Model model){
		
		Usuario usuarioSesion = (Usuario) model.asMap().get("usuarioSesion");
		
		model.addAttribute("listaFacturas", facturaService.listadoFacturas(usuarioSesion.getAgencia()));

		return "tables/tablaFacturas";
	}
	
	@RequestMapping(value= "/prefacturaLista/carga_tabla", method = RequestMethod.POST)
	public String cargaTablaPrefacturas(Model model){
	
		Usuario usuarioSesion = (Usuario) model.asMap().get("usuarioSesion");
		
		model.addAttribute("listaFacturas", facturaService.listadoPrefacturas(usuarioSesion.getAgencia()));
		
		return "tables/tablaFacturas";
	}
	
	@RequestMapping(value = "/devuelve_factura", method = RequestMethod.GET)
	@ResponseBody
	public void devuelveFactura(@RequestParam(value="id") Integer id, HttpServletResponse response) throws IOException{
		
		Factura 				factura						= facturaService.buscar(id);
		
		Gson 					gson 						= new Gson(); 
		JsonObject 				myObj 						= new JsonObject();
		
		JsonElement				jsFactura	 				= gson.toJsonTree(factura);
		
		//Para editar fechas necesitamos sacar la propiedad fecha (formato fecha) y agregarla al objeto formateada
		
		jsFactura.getAsJsonObject().remove("fact_fechaFactura");
		jsFactura.getAsJsonObject().addProperty("fact_fechaFactura", factura.getFact_fechaFacturaFormateada());

		jsFactura.getAsJsonObject().remove("fact_fechaPrefactura");
		jsFactura.getAsJsonObject().addProperty("fact_fechaPrefactura", factura.getFact_fechaPrefacturaFormateada());
		
		jsFactura.getAsJsonObject().remove("fact_fechaVencimiento");
		jsFactura.getAsJsonObject().addProperty("fact_fechaVencimiento", factura.getFact_fechaVencimientoFormateada());
		
		jsFactura.getAsJsonObject().remove("fact_fechaRespuesta");
		jsFactura.getAsJsonObject().addProperty("fact_fechaRespuesta", factura.getFact_fechaRespuestaFormateada());
		
		jsFactura.getAsJsonObject().remove("fact_fechaLimite");
		jsFactura.getAsJsonObject().addProperty("fact_fechaLimite", factura.getFact_fechaLimiteFormateada());
		
		myObj.add("objeto", jsFactura);			
		PrintWriter out = response.getWriter();		
		out.println(myObj.toString());	
		out.close();		
	}
		
	@PostMapping(value = "/aceptar_factura")
	@ResponseBody
	public Factura aceptar(@ModelAttribute("factura") @Valid Factura factura, BindingResult result){
		
		try{
			
			if(factura.getId()==null || factura.getId()==0){
				
				factura.setId(0);	
				facturaService.guardar(factura);				
				factura = new Factura();
                
			//Actualización	
			}else{				
				facturaService.actualizar(factura);				
			}
			
			factura.setStatus("SUCCESS");
			
		} catch (Exception e) {
			
			FieldError error;
			
            if (e.getClass().equals(DataIntegrityViolationException.class)){	                	   
                error = new FieldError("factura", "fact_numeroFactura", "El número de factura ya existe.");                
                result.addError(error);
	        }      
			
            factura.setStatus("FAIL");
            factura.setResult(result.getAllErrors());
		}			
				
		return factura;
	}
	
	@RequestMapping(value = "/facturaLista&id={idFactura}/eliminar", method = RequestMethod.POST)
	@ResponseBody
	public void eliminarDeLista(@PathVariable("idFactura") Integer idFactura, HttpServletResponse response) throws IOException{
		
		Factura factura = facturaService.buscar(idFactura);
		
		Gson 					gson 						= new Gson(); 
		JsonObject 				myObj 						= new JsonObject();
		
		String					tipoDocumento				= "";
		
		try{
			
			if(factura.getFact_numeroFactura()!=null){
				tipoDocumento = "factura";
			}else{
				tipoDocumento = "prefactura";
			}
			
			facturaService.eliminar(factura);
		
		}catch(Exception e){		
			
			tipoDocumento = "Error";
			
		}	
		
		JsonElement		jsTipoDocumento				= gson.toJsonTree(tipoDocumento);
		
		myObj.add("tipoDocumento", jsTipoDocumento);
		
		PrintWriter out = response.getWriter();		
		out.println(myObj.toString());	
		out.close();	
	}
	
	@RequestMapping(value = "/facturar", method = RequestMethod.GET)
	@ResponseBody
	public void facturar(@RequestParam(value="lista") String lista, @RequestParam(value="fecha") String fechaFactura, HttpServletResponse response) throws IOException, ParseException{
		
		Gson 					gson 						= new Gson(); 
		JsonObject 				myObj 						= new JsonObject();
		
		SimpleDateFormat		sdf							= new SimpleDateFormat("dd/MM/yyyy");
		
		Integer					anyoFactura					= new Integer(fechaFactura.substring(6)); //Si es hasta el final (11) no hace falta poner el ultimo indice
		
		String					mensaje						= "";
		Boolean					error						= false;
		
		//Reemplaza los elementos propios del array para dejarlo como una lista de numeros (pero es un string)
		String listaLimpia = lista.replace("[", "").replace("]", "").replace("\"", "");
				
		//El string lo convierte en lista de strings
		List<String> items = Arrays.asList(listaLimpia.split("\\s*,\\s*"));
		
		//Obtenemos el id de cada string de la lista de strings y generamos la lista de prefacturas a facturar
		List<Factura> listaPrefacturas = new ArrayList<Factura>();
		
		for(String item : items){			
			Integer id = new Integer(item);						
			listaPrefacturas.add(facturaService.buscar(id));
		}
		
		for(Factura factura : listaPrefacturas){
			
			if(factura.getFact_estadoPrefactura()!=1){
				mensaje = "Error: Existen prefacturas no validadas por el cliente"; 
				error = true;
				break;
			}
			
		}
		
		if(!error){
			
			for(Factura factura : listaPrefacturas){
								
				factura.setFact_fechaFactura(sdf.parse(fechaFactura));
				factura.setFact_fechaVencimiento(Fechas.sumarDiasFecha(sdf.parse(fechaFactura), factura.getCliente().getClie_diasVencimiento()));
				factura.setFact_numeroFactura(facturaService.ultimaFactura(factura.getAgencia(), anyoFactura));
				facturaService.actualizar(factura);
			}
			
			mensaje = "Documentos facturados satisfactoriamente"; 
		}
		
		JsonElement				jsMensaje					= gson.toJsonTree(mensaje);
		
		myObj.add("mensaje", jsMensaje);
		
		PrintWriter out = response.getWriter();		
		out.println(myObj.toString());	
		out.close();	
		
	}
	
	@RequestMapping(value = "/generar_edi", method = RequestMethod.GET)
	@ResponseBody
	public void generarEdi(@RequestParam(value="lista") String lista, HttpServletResponse response) throws IOException, ParseException{
		
		Gson 					gson 						= new Gson(); 
		JsonObject 				myObj 						= new JsonObject();
		
		String					mensaje						= "";
		
		//Reemplaza los elementos propios del array para dejarlo como una lista de numeros (pero es un string)
		String listaLimpia = lista.replace("[", "").replace("]", "").replace("\"", "");
				
		//El string lo convierte en lista de strings
		List<String> items = Arrays.asList(listaLimpia.split("\\s*,\\s*"));

		List<Factura> listaFacturas = new ArrayList<Factura>();
		
		for(String item : items){			
			Integer id = new Integer(item);			
			listaFacturas.add(facturaService.buscar(id));
		}
		
		SimpleDateFormat formatoMesAnyo = new SimpleDateFormat("yyMM");
	
		for(Factura factura : listaFacturas){						
			FicheroEdi ficheroEdi = new FicheroEdi();			
			List<Pedido> listaPedidos = pedidoService.listadoPedidosFactura(factura.getId());
			
			String nombreFichero = "";
			String ruta 		 = "";
			
			if(factura.getFact_numeroFactura()!=null){
				nombreFichero 	= factura.getFact_numeroFactura() + "_" + formatoMesAnyo.format(factura.getFact_fechaFactura());
				ruta			= CfgUtil.RUTA_FACTURAS;
			}else{
				nombreFichero 	= factura.getFact_numeroPrefactura() + "_" + formatoMesAnyo.format(factura.getFact_fechaPrefactura());
				ruta			= CfgUtil.RUTA_PREFACTURAS;
			}
			
			ficheroEdi.generarFicheroEDI(factura, listaPedidos, ruta,  nombreFichero);
		}
		
		mensaje = "Documentos facturados satisfactoriamente";
		
		JsonElement				jsMensaje					= gson.toJsonTree(mensaje);
		
		myObj.add("mensaje", jsMensaje);
		
		PrintWriter out = response.getWriter();		
		out.println(myObj.toString());	
		out.close();	
		
	}
	
}
