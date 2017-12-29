package net.gefco.pedi2.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.gefco.pedi2.modelo.Proveedor;
import net.gefco.pedi2.negocio.ProveedorService;

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
public class ProveedorController {
	
	@Autowired
	private ProveedorService 				proveedorService;
		
	//Nos lleva a la pagina
	@RequestMapping("/proveedorLista")
	public String indice(Model model){		
		
		model.addAttribute("proveedor", new Proveedor()); //Al iniciar página siempre pasamos instancia vacía
		
		return "proveedorLista";
	}
	
	@RequestMapping(value= "/proveedorLista/carga_tabla", method = RequestMethod.POST)
	public String cargaTabla(Model model){
		
		model.addAttribute("listaProveedores", proveedorService.listado());
		
		return "tables/tablaProveedores";
	}
	
	@RequestMapping(value = "/devuelve_proveedor", method = RequestMethod.GET)
	@ResponseBody
	public void devuelveProveedor(@RequestParam(value="id") Integer id, HttpServletResponse response) throws IOException{
		
		Gson 					gson 						= new Gson(); 
		JsonObject 				myObj 						= new JsonObject();
		
		JsonElement				jsProveedor	 				= gson.toJsonTree(proveedorService.buscar(id));
		
		myObj.add("objeto", jsProveedor);			
		PrintWriter out = response.getWriter();		
		out.println(myObj.toString());	
		out.close();		
	}
		
	@PostMapping(value = "/aceptar_proveedor")
	@ResponseBody
	public Proveedor aceptar(@ModelAttribute("proveedor") @Valid Proveedor proveedor, BindingResult result){
				
		try{
			
			if(proveedor.getId()==null || proveedor.getId()==0){
				
				proveedor.setId(0);	
				proveedorService.guardar(proveedor);				
				proveedor = new Proveedor();
                
			//Actualización	
			}else{				
				proveedorService.actualizar(proveedor);				
			}
			
			proveedor.setStatus("SUCCESS");
			
		} catch (Exception e) {
			
			FieldError error;
			
            if (e.getClass().equals(DataIntegrityViolationException.class)){	                	   
                error = new FieldError("proveedor", "agen_codigo", "El código de proveedor ya existe.");                
                result.addError(error);
	        }      
			
            proveedor.setStatus("FAIL");
            proveedor.setResult(result.getAllErrors());
		}			
				
		return proveedor;
	}
	
	@RequestMapping(value = "/proveedorLista&id={idProveedor}/eliminar", method = RequestMethod.POST)
	@ResponseBody
	public String eliminarDeLista(@PathVariable("idProveedor") Integer idProveedor){
		
		Proveedor proveedor = proveedorService.buscar(idProveedor);
		
		try{
			proveedorService.eliminar(proveedor);	
			return "ok";			
		}catch(Exception e){			
			return "error";			
		}		
	}
	
	//Exportación a Excel
	@RequestMapping(value = "/exportarProveedor", method = RequestMethod.GET)
    public ModelAndView descargarExcel() {
		
		List<Proveedor> lista = proveedorService.listado();
				
        return new ModelAndView("excelViewProveedor", "proveedor", lista);
    }	
	
}