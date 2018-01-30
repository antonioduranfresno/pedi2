package net.gefco.pedi2.controladores;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.gefco.pedi2.modelo.Pais;
import net.gefco.pedi2.negocio.PaisService;

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
public class PaisController {
	
	@Autowired
	private PaisService 				paisService;
	
	//Nos lleva a la pagina
	@RequestMapping("/paisLista")
	public String indice(Model model){		
		
		model.addAttribute("pais", new Pais()); //Al iniciar página siempre pasamos instancia vacía
		
		return "paisLista";
	}
	
	@RequestMapping(value= "/paisLista/carga_tabla", method = RequestMethod.POST)
	public String cargaTabla(Model model){
		
		model.addAttribute("listaPaises", paisService.listado());
		
		return "tables/tablaPaises";
	}
	
	@RequestMapping(value = "/devuelve_pais", method = RequestMethod.GET)
	@ResponseBody
	public void devuelvePais(@RequestParam(value="id") Integer id, HttpServletResponse response) throws IOException{
		
		Gson 					gson 						= new Gson(); 
		JsonObject 				myObj 						= new JsonObject();
		
		JsonElement				jsPais	 				= gson.toJsonTree(paisService.buscar(id));
		
		myObj.add("objeto", jsPais);			
		PrintWriter out = response.getWriter();		
		out.println(myObj.toString());	
		out.close();		
	}
		
	@PostMapping(value = "/aceptar_pais")
	@ResponseBody
	public Pais aceptar(@ModelAttribute("pais") @Valid Pais pais, BindingResult result){
				
		try{
			
			if(pais.getId()==null || pais.getId()==0){
				
				pais.setId(0);	
				paisService.guardar(pais);				
				pais = new Pais();
                
			//Actualización	
			}else{				
				paisService.actualizar(pais);				
			}
			
			pais.setStatus("SUCCESS");
			
		} catch (Exception e) {
			
			FieldError error;
			
            if (e.getClass().equals(DataIntegrityViolationException.class)){	                	   
                error = new FieldError("pais", "pais_codigo", "El código de pais ya existe.");                
                result.addError(error);
	        }      
			
            pais.setStatus("FAIL");
            pais.setResult(result.getAllErrors());
		}			
				
		return pais;
	}
	
	@RequestMapping(value = "/paisLista&id={idPais}/eliminar", method = RequestMethod.POST)
	@ResponseBody
	public String eliminarDeLista(@PathVariable("idPais") Integer idPais){
		
		Pais pais = paisService.buscar(idPais);
		
		try{
			paisService.eliminar(pais);	
			return "ok";			
		}catch(Exception e){			
			return "error";			
		}		
	}
	
	
}