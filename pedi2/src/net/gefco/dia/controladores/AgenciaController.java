package net.gefco.dia.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.gefco.dia.modelo.Agencia;
import net.gefco.dia.negocio.AgenciaService;

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
public class AgenciaController {
	
	@Autowired
	private AgenciaService 				agenciaService;
	
	//Nos lleva a la pagina
	@RequestMapping("/agenciaLista")
	public String indice(Model model){		
		
		model.addAttribute("agencia", new Agencia()); //Al iniciar página siempre pasamos instancia vacía
		
		return "agenciaLista";
	}
	
	@RequestMapping(value= "/agenciaLista/carga_tabla", method = RequestMethod.POST)
	public String cargaTabla(Model model){
		
		model.addAttribute("listaAgencias", agenciaService.listado());
		
		return "tables/tablaAgencias";
	}
	
	@RequestMapping(value = "/devuelve_agencia", method = RequestMethod.GET)
	@ResponseBody
	public void devuelveAgencia(@RequestParam(value="id") Integer id, HttpServletResponse response) throws IOException{
		
		Gson 					gson 						= new Gson(); 
		JsonObject 				myObj 						= new JsonObject();
		
		JsonElement				jsAgencia	 				= gson.toJsonTree(agenciaService.buscar(id));
		
		myObj.add("objeto", jsAgencia);			
		PrintWriter out = response.getWriter();		
		out.println(myObj.toString());	
		out.close();		
	}
		
	@PostMapping(value = "/aceptar_agencia")
	@ResponseBody
	public Agencia aceptar(@ModelAttribute("agencia") @Valid Agencia agencia, BindingResult result){
				
		try{
			
			if(agencia.getId()==null || agencia.getId()==0){
				
				agencia.setId(0);	
				agenciaService.guardar(agencia);				
				agencia = new Agencia();
                
			//Actualización	
			}else{				
				agenciaService.actualizar(agencia);				
			}
			
			agencia.setStatus("SUCCESS");
			
		} catch (Exception e) {
			
			FieldError error;
			
            if (e.getClass().equals(DataIntegrityViolationException.class)){	                	   
                error = new FieldError("agencia", "agen_codigo", "El código de agencia ya existe.");                
                result.addError(error);
	        }      
			
            agencia.setStatus("FAIL");
            agencia.setResult(result.getAllErrors());
		}			
				
		return agencia;
	}
	
	@RequestMapping(value = "/agenciaLista&id={idAgencia}/eliminar", method = RequestMethod.POST)
	@ResponseBody
	public String eliminarDeLista(@PathVariable("idAgencia") Integer idAgencia){
		
		Agencia agencia = agenciaService.buscar(idAgencia);
		
		try{
			agenciaService.eliminar(agencia);	
			return "ok";			
		}catch(Exception e){			
			return "error";			
		}		
	}
	
	//Exportación a Excel
	@RequestMapping(value = "/exportarAgencia", method = RequestMethod.GET)
    public ModelAndView descargarExcel() {
		
		List<Agencia> lista = agenciaService.listado();
				
        return new ModelAndView("excelViewAgencia", "agencia", lista);
    }	
	
}