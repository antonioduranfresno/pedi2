package net.gefco.dia.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.gefco.dia.modelo.TarifaVenta;
import net.gefco.dia.negocio.AgenciaService;
import net.gefco.dia.negocio.ClienteService;
import net.gefco.dia.negocio.NodoService;
import net.gefco.dia.negocio.TarifaVentaService;

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
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Controller
public class TarifaVentaController {
	
	@Autowired
	private TarifaVentaService 				tarifaVentaService;
		
	@Autowired
	private AgenciaService 					agenciaService;
	
	@Autowired
	private ClienteService 					clienteService;
	
	@Autowired
	private NodoService 					nodoService;
	
	//Nos lleva a la pagina
	@RequestMapping("/tarifaVentaLista")
	public String indice(Model model){		
		
		model.addAttribute("tarifaVenta", new TarifaVenta()); //Al iniciar página siempre pasamos instancia vacía
		model.addAttribute("listaTarifas", tarifaVentaService.listado());
		model.addAttribute("listaAgencias", agenciaService.listado());
		model.addAttribute("listaClientes", clienteService.listado());
		model.addAttribute("listaNodosOrigen", nodoService.listadoOrigenes());
		model.addAttribute("listaNodosDestino", nodoService.listadoDestinos());
		
		return "tarifaVentaLista";
	}
	
	@RequestMapping(value= "/tarifaVentaLista/carga_tabla", method = RequestMethod.POST)
	public String cargaTabla(Model model){
		
		model.addAttribute("listaTarifaVentas", tarifaVentaService.listado());
		
		return "tables/tablaTarifaVentas";
	}
	
	@RequestMapping(value = "/devuelve_tarifaVenta", method = RequestMethod.GET)
	@ResponseBody
	public void devuelveTarifaVenta(@RequestParam(value="id") Integer id, HttpServletResponse response) throws IOException{
		
		TarifaVenta 			tarifaVenta 				= tarifaVentaService.buscar(id);
		
		Gson 					gson 						= new Gson(); 
		JsonObject 				myObj 						= new JsonObject();
		
		JsonElement				jsTarifaVenta	 			= gson.toJsonTree(tarifaVenta);
		
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
		
		if (result.hasErrors()){
			tarifaVenta.setStatus("FAIL");
            tarifaVenta.setResult(result.getAllErrors());
            return tarifaVenta;
		}
		
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
		
		if (tarifaVenta.getTave_fechaDesde().after(tarifaVenta.getTave_fechaHasta())) {
			FieldError error = new FieldError("tarifaVenta", "tave_fechaDesde", "La fecha de comienzo debe ser inferior a la fin");
			result.addError(error);
		}
		
		if (tarifaVenta.getTave_numeroMaxPaletT2() != 0 && tarifaVenta.getTave_numeroMaxPaletT2() <= tarifaVenta.getTave_numeroMaxPaletT1()){
			FieldError error = new FieldError("tarifaVenta", "tave_numeroMaxPaletT2", "El número de palet de la trancha 2 debe ser superior al de la trancha 1");
			result.addError(error);
		}
		
		if (tarifaVenta.getTave_numeroMaxPaletT3() != 0 && tarifaVenta.getTave_numeroMaxPaletT3() <= tarifaVenta.getTave_numeroMaxPaletT2()){
			FieldError error = new FieldError("tarifaVenta", "tave_numeroMaxPaletT3", "El número de palet de la trancha 3 debe ser superior al de la trancha 2");
			result.addError(error);
		}
		
		if (tarifaVenta.getTave_numeroMaxPaletT4() != 0 && tarifaVenta.getTave_numeroMaxPaletT4() <= tarifaVenta.getTave_numeroMaxPaletT3()){
			FieldError error = new FieldError("tarifaVenta", "tave_numeroMaxPaletT4", "El número de palet de la trancha 4 debe ser superior al de la trancha 3");
			result.addError(error);
		}
		
		if (tarifaVenta.getTave_numeroMaxPaletT5() != 0 && tarifaVenta.getTave_numeroMaxPaletT5() <= tarifaVenta.getTave_numeroMaxPaletT4()){
			FieldError error = new FieldError("tarifaVenta", "tave_numeroMaxPaletT5", "El número de palet de la trancha 5 debe ser superior al de la trancha 4");
			result.addError(error);
		}
		
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
	
	//Exportación a Excel
	@RequestMapping(value = "/exportarTarifaVenta", method = RequestMethod.GET)
    public ModelAndView descargarExcel() {
		
		List<TarifaVenta> lista = tarifaVentaService.listado();
				
        return new ModelAndView("excelViewTarifaVenta", "tarifaVenta", lista);
    }	
	
}