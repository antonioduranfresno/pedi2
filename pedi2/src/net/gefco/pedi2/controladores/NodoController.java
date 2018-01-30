package net.gefco.pedi2.controladores;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.gefco.pedi2.modelo.Nodo;
import net.gefco.pedi2.negocio.NodoService;
import net.gefco.pedi2.negocio.PaisService;
import net.gefco.pedi2.negocio.ProveedorService;
import net.gefco.pedi2.negocio.ZonaService;

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

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Controller
public class NodoController {
	
	@Autowired
	private NodoService 				nodoService;
	
	@Autowired
	private ProveedorService 			proveedorService;
	
	@Autowired
	private PaisService 				paisService;
	
	@Autowired
	private ZonaService 				zonaService;
	
	//Nos lleva a la pagina
	@RequestMapping("/nodoLista")
	public String indice(Model model){		
		
		model.addAttribute("nodo", new Nodo()); //Al iniciar página siempre pasamos instancia vacía
		model.addAttribute("listaProveedores", proveedorService.listado());
		model.addAttribute("listaPaises", paisService.listado());
		model.addAttribute("listaZonas", zonaService.listado());
		
		return "nodoLista";
	}
	
	@RequestMapping(value= "/nodoLista/carga_tabla", method = RequestMethod.POST)
	public String cargaTabla(Model model){
		
		model.addAttribute("listaNodos", nodoService.listado());
		
		return "tables/tablaNodos";
	}
	
	@RequestMapping(value = "/devuelve_nodo", method = RequestMethod.GET)
	@ResponseBody
	public void devuelveNodo(@RequestParam(value="id") Integer id, HttpServletResponse response) throws IOException{
		
		Gson 					gson 						= new Gson(); 
		JsonObject 				myObj 						= new JsonObject();
		
		JsonElement				jsNodo	 				= gson.toJsonTree(nodoService.buscar(id));
		
		myObj.add("objeto", jsNodo);			
		PrintWriter out = response.getWriter();		
		out.println(myObj.toString());	
		out.close();		
	}
		
	@PostMapping(value = "/aceptar_nodo")
	@ResponseBody
	public Nodo aceptar(@ModelAttribute("nodo") @Valid Nodo nodo, BindingResult result){
				
		if (nodo.getProveedor().getId() == 0) {
			FieldError error = new FieldError("nodo", "error_proveedor", "Debe seleccionar proveedor");
			result.addError(error);
		}

		if (nodo.getPais().getId() == 0) {
			FieldError error = new FieldError("nodo", "error_pais", "Debe seleccionar pais");
			result.addError(error);
		}
		
		if (nodo.getZona().getId() == 0) {
			FieldError error = new FieldError("nodo", "error_zona", "Debe seleccionar zona");
			result.addError(error);
		}
		
		if (result.hasErrors()){
			nodo.setStatus("FAIL");
            nodo.setResult(result.getAllErrors());
            return nodo;
		}
		
		try{
			
			if(nodo.getId()==null || nodo.getId()==0){
				
				nodo.setId(0);	
				nodoService.guardar(nodo);				
				nodo = new Nodo();
                
			//Actualización	
			}else{				
				nodoService.actualizar(nodo);				
			}
			
			nodo.setStatus("SUCCESS");
			
		} catch (Exception e) {
			
			FieldError error;
			
            if (e.getClass().equals(DataIntegrityViolationException.class)){	                	   
                error = new FieldError("nodo", "nodo_nombre", "El nombre del nodo ya existe.");                
                result.addError(error);
	        }      
			
            nodo.setStatus("FAIL");
            nodo.setResult(result.getAllErrors());
		}			
				
		return nodo;
	}
	
	@RequestMapping(value = "/nodoLista&id={idNodo}/eliminar", method = RequestMethod.POST)
	@ResponseBody
	public String eliminarDeLista(@PathVariable("idNodo") Integer idNodo){
		
		Nodo nodo = nodoService.buscar(idNodo);
		
		try{
			nodoService.eliminar(nodo);	
			return "ok";			
		}catch(Exception e){			
			return "error";			
		}		
	}
		
}