package net.gefco.pedi2.controladores;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.gefco.pedi2.modelo.Cliente;
import net.gefco.pedi2.modelo.Nodo;
import net.gefco.pedi2.modelo.TarifaVenta;
import net.gefco.pedi2.modelo.Usuario;
import net.gefco.pedi2.negocio.AgenciaService;
import net.gefco.pedi2.negocio.ClienteNodoService;
import net.gefco.pedi2.negocio.ClienteService;
import net.gefco.pedi2.negocio.NodoService;
import net.gefco.pedi2.negocio.TarifaVentaService;

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
public class TarifaVentaController {
	
	@Autowired
	private TarifaVentaService 				tarifaVentaService;
		
	@Autowired
	private AgenciaService 					agenciaService;
	
	@Autowired
	private ClienteService 					clienteService;
	
	@Autowired
	private NodoService 					nodoService;
	
	@Autowired
	private ClienteNodoService 				clienteNodoService;
	
	//Nos lleva a la pagina
	@RequestMapping("/tarifaVentaLista")
	public String indice(Model model){		
		
		model.addAttribute("tarifaVenta", new TarifaVenta()); //Al iniciar página siempre pasamos instancia vacía
		model.addAttribute("listaAgencias", agenciaService.listado());
		model.addAttribute("listaClientes", clienteService.listado());
				
		return "tarifaVentaLista";
	}
	
	@RequestMapping(value= "/tarifaVentaLista/carga_tabla", method = RequestMethod.POST)
	public String cargaTabla(Model model){
		
		Usuario usuarioSesion = (Usuario) model.asMap().get("usuarioSesion");
				
		model.addAttribute("listaTarifaVentas", tarifaVentaService.listado(usuarioSesion.getAgencia()));
		
		return "tables/tablaTarifaVentas";
	}
	
	@RequestMapping(value = "/devuelve_tarifaVenta", method = RequestMethod.GET)
	@ResponseBody
	public void devuelveTarifaVenta(@RequestParam(value="id") Integer id, HttpServletResponse response) throws IOException{
		
		TarifaVenta 			tarifaVenta 				= tarifaVentaService.buscar(id);
		
		Gson 					gson 						= new Gson(); 
		JsonObject 				myObj 						= new JsonObject();
		
		JsonElement				jsTarifaVenta	 			= gson.toJsonTree(tarifaVenta);
						
		//Para editar fechas necesitamos sacar la propiedad fecha (formato fecha) y agregarla al objeto formateada
		
		jsTarifaVenta.getAsJsonObject().remove("tave_fechaDesde");
		jsTarifaVenta.getAsJsonObject().addProperty("tave_fechaDesde", tarifaVenta.getTave_fechaDesdeFormateada());

		jsTarifaVenta.getAsJsonObject().remove("tave_fechaHasta");
		jsTarifaVenta.getAsJsonObject().addProperty("tave_fechaHasta", tarifaVenta.getTave_fechaHastaFormateada());
		
		myObj.add("objeto", jsTarifaVenta);			
		PrintWriter out = response.getWriter();		
		out.println(myObj.toString());	
		out.close();		
	}
		
	@PostMapping(value = "/aceptar_tarifaVenta")
	@ResponseBody
	public TarifaVenta aceptar(@ModelAttribute("tarifaVenta") @Valid TarifaVenta tarifaVenta, BindingResult result){

		if (tarifaVenta.getTave_fechaDesde() == null) {
			FieldError error = new FieldError("tarifaVenta", "tave_fechaDesde", "Debe rellenar la fecha de comienzo");
			result.addError(error);
		}
		
		if (tarifaVenta.getTave_fechaHasta() == null) {
			FieldError error = new FieldError("tarifaVenta", "tave_fechaHasta", "Debe rellenar la fecha de fin");
			result.addError(error);
		}
		
		if (tarifaVenta.getAgencia().getId() == 0) {
			FieldError error = new FieldError("tarifaVenta", "error_agencia", "Debe seleccionar la agencia");
			result.addError(error);
		}
		
		if (tarifaVenta.getCliente().getId() == 0) {
			FieldError error = new FieldError("tarifaVenta", "error_cliente", "Debe seleccionar el cliente");
			result.addError(error);
		}
		
		if (tarifaVenta.getNodoOrigen().getId() == 0) {
			FieldError error = new FieldError("tarifaVenta", "error_nodoOrigen", "Debe seleccionar el origen");
			result.addError(error);
		}
		
		if (tarifaVenta.getNodoDestino().getId() == 0) {
			FieldError error = new FieldError("tarifaVenta", "error_nodoDestino", "Debe seleccionar el destino");
			result.addError(error);
		}
		
		if (tarifaVentaService.solapada(tarifaVenta)) {
			FieldError error = new FieldError("tarifaVenta", "tave_fechaDesde", "Esta tarifa se solapa con otra de la base de datos");
			result.addError(error);
		}
		
		if (tarifaVenta.getTave_fechaDesde() != null && tarifaVenta.getTave_fechaHasta() != null) {			
			if (tarifaVenta.getTave_fechaDesde().after(tarifaVenta.getTave_fechaHasta())) {
				FieldError error = new FieldError("tarifaVenta", "tave_fechaDesde", "Fecha incoherente");
				result.addError(error);
			}
		}
		
		if (tarifaVenta.getTave_importeCamionCompleto() == null) {
			if(!tarifaVenta.getTave_soloTranchas()){				
				FieldError error = new FieldError("tarifaVenta", "error_tave_importeCamionCompleto", "Campo obligatorio");
				result.addError(error);			
			}
		}
		
		/*
		if(tarifaVenta.getTave_numeroMaxT2() != null && tarifaVenta.getTave_numeroMaxT1() != null){
			if (tarifaVenta.getTave_numeroMaxT2() != 0 && tarifaVenta.getTave_numeroMaxT2() <= tarifaVenta.getTave_numeroMaxT1()){
				FieldError error = new FieldError("tarifaVenta", "tave_numeroMaxT2", "!");
				result.addError(error);
			}	
		}
		
		if(tarifaVenta.getTave_numeroMaxT3() != null && tarifaVenta.getTave_numeroMaxT2() != null){
			if (tarifaVenta.getTave_numeroMaxT3() != 0 && tarifaVenta.getTave_numeroMaxT3() <= tarifaVenta.getTave_numeroMaxT2()){
				FieldError error = new FieldError("tarifaVenta", "tave_numeroMaxT3", "!");
				result.addError(error);
			}			
		}
		
		if(tarifaVenta.getTave_numeroMaxT4() != null && tarifaVenta.getTave_numeroMaxT3() != null){			
			if (tarifaVenta.getTave_numeroMaxT4() != 0 && tarifaVenta.getTave_numeroMaxT4() <= tarifaVenta.getTave_numeroMaxT3()){
				FieldError error = new FieldError("tarifaVenta", "tave_numeroMaxT4", "!");
				result.addError(error);
			}
		}
		
		if(tarifaVenta.getTave_numeroMaxT5() != null && tarifaVenta.getTave_numeroMaxT4() != null){		
			if (tarifaVenta.getTave_numeroMaxT5() != 0 && tarifaVenta.getTave_numeroMaxT5() <= tarifaVenta.getTave_numeroMaxT4()){
				FieldError error = new FieldError("tarifaVenta", "tave_numeroMaxT5", "!");
				result.addError(error);
			}			
		}*/
		
		if (result.hasErrors()){
			tarifaVenta.setStatus("FAIL");
            tarifaVenta.setResult(result.getAllErrors());
            return tarifaVenta;
		}
		
		try{
			
			if(tarifaVenta.getId()==null || tarifaVenta.getId()==0){
				
				tarifaVenta.setId(0);	
				tarifaVentaService.guardar(tarifaVenta);				
				tarifaVenta = new TarifaVenta();
                
			//Actualización	
			}else{				
				tarifaVentaService.actualizar(tarifaVenta);				
			}
			
			tarifaVenta.setStatus("SUCCESS");
			
		} catch (Exception e) {
			
			FieldError error;
			
            if (e.getClass().equals(DataIntegrityViolationException.class)){	                	   
                error = new FieldError("tarifaVenta", "agen_codigo", "El código de tarifaVenta ya existe.");                
                result.addError(error);
	        }      
			
            tarifaVenta.setStatus("FAIL");
            tarifaVenta.setResult(result.getAllErrors());
		}			
				
		return tarifaVenta;
		
	}
	
	@RequestMapping(value = "/tarifaVentaLista&id={idTarifaVenta}/eliminar", method = RequestMethod.POST)
	@ResponseBody
	public String eliminarDeLista(@PathVariable("idTarifaVenta") Integer idTarifaVenta){
		
		TarifaVenta tarifaVenta = tarifaVentaService.buscar(idTarifaVenta);
		
		try{
			tarifaVentaService.eliminar(tarifaVenta);	
			return "ok";			
		}catch(Exception e){			
			return "error";			
		}		
	}
		
	@RequestMapping(value = "/tarifaVentaLista/selCliente={idCliente}") 
	@ResponseBody 
	public String seleccionCliente(@PathVariable("idCliente") Integer idCliente){
	
		Cliente cliente = clienteService.buscarCliente(idCliente);
		
		Gson 					gson 						= new Gson(); 
		JsonObject 				myObj 						= new JsonObject();
				
		StringBuilder cadenaOrigenes = new StringBuilder();
		
		cadenaOrigenes.append("<option value='0'>Selección</option>");
		
		for(Nodo nodo : clienteNodoService.listadoOrigenesCliente(cliente)){		
			cadenaOrigenes.append("<option value='"+nodo.getId()+"'>"+nodo.getProveedor().getProv_nombre()+" - " + nodo.getNodo_nombre() + "</option>");			
		}
		
		StringBuilder cadenaDestinos = new StringBuilder();
		
		cadenaDestinos.append("<option value='0'>Selección</option>");
		
		for(Nodo nodo : clienteNodoService.listadoDestinosCliente(cliente)){		
			cadenaDestinos.append("<option value='"+nodo.getId()+"'>"+nodo.getProveedor().getProv_nombre()+" - " + nodo.getNodo_nombre() + "</option>");			
		}
		
		JsonElement				jsListaOrigenes		= gson.toJsonTree(cadenaOrigenes.toString());
		JsonElement				jsListaDestinos		= gson.toJsonTree(cadenaDestinos.toString());
			
		myObj.add("listaOrigenes", jsListaOrigenes);
		myObj.add("listaDestinos", jsListaDestinos);
		
		return myObj.toString();		
	}	
	
}