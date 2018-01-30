package net.gefco.pedi2.controladores;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.gefco.pedi2.modelo.Zona;
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
public class ZonaController {
	
	@Autowired
	private ZonaService 				zonaService;
	
	//Nos lleva a la pagina
	@RequestMapping("/zonaLista")
	public String indice(Model model){		
		
		model.addAttribute("zona", new Zona()); //Al iniciar página siempre pasamos instancia vacía
		
		return "zonaLista";
	}
	
	@RequestMapping(value= "/zonaLista/carga_tabla", method = RequestMethod.POST)
	public String cargaTabla(Model model){
		
		model.addAttribute("listaZonas", zonaService.listado());
		
		return "tables/tablaZonas";
	}
	
	@RequestMapping(value = "/devuelve_zona", method = RequestMethod.GET)
	@ResponseBody
	public void devuelveZona(@RequestParam(value="id") Integer id, HttpServletResponse response) throws IOException{
		
		Gson 					gson 						= new Gson(); 
		JsonObject 				myObj 						= new JsonObject();
		
		JsonElement				jsZona	 				= gson.toJsonTree(zonaService.buscar(id));
		
		myObj.add("objeto", jsZona);			
		PrintWriter out = response.getWriter();		
		out.println(myObj.toString());	
		out.close();		
	}
		
	@PostMapping(value = "/aceptar_zona")
	@ResponseBody
	public Zona aceptar(@ModelAttribute("zona") @Valid Zona zona, BindingResult result){
				
		try{
			
			if(zona.getId()==null || zona.getId()==0){
				
				zona.setId(0);	
				zonaService.guardar(zona);				
				zona = new Zona();
                
			//Actualización	
			}else{				
				zonaService.actualizar(zona);				
			}
			
			zona.setStatus("SUCCESS");
			
		} catch (Exception e) {
			
			FieldError error;
			
            if (e.getClass().equals(DataIntegrityViolationException.class)){	                	   
                error = new FieldError("zona", "zona_nombre", "El nombre de la zona ya existe.");                
                result.addError(error);
	        }      
			
            zona.setStatus("FAIL");
            zona.setResult(result.getAllErrors());
		}			
				
		return zona;
	}
	
	@RequestMapping(value = "/zonaLista&id={idZona}/eliminar", method = RequestMethod.POST)
	@ResponseBody
	public String eliminarDeLista(@PathVariable("idZona") Integer idZona){
		
		Zona zona = zonaService.buscar(idZona);
		
		try{
			zonaService.eliminar(zona);	
			return "ok";			
		}catch(Exception e){			
			return "error";			
		}		
	}
	
	
}