package net.gefco.pedi2.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.gefco.pedi2.modelo.ClienteProveedor;
import net.gefco.pedi2.negocio.ClienteProveedorService;
import net.gefco.pedi2.negocio.ClienteService;
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
public class ClienteProveedorController {
	
	@Autowired
	private ClienteProveedorService 			clienteProveedorService;
	
	@Autowired
	private ClienteService 					clienteService;
	
	@Autowired
	private ProveedorService 				proveedorService;
	
	//Nos lleva a la pagina
	@RequestMapping("/clienteProveedorLista")
	public String indice(Model model){		
		
		model.addAttribute("clienteProveedor", new ClienteProveedor()); //Al iniciar página siempre pasamos instancia vacía
		model.addAttribute("listaClientes", clienteService.listado());
		model.addAttribute("listaProveedores", proveedorService.listado());
		
		return "clienteProveedorLista";
	}
	
	@RequestMapping(value= "/clienteProveedorLista/carga_tabla", method = RequestMethod.POST)
	public String cargaTabla(Model model){
		
		model.addAttribute("listaClienteProveedores", clienteProveedorService.listado());
		
		return "tables/tablaClienteProveedores";
	}
	
	@RequestMapping(value = "/devuelve_clienteProveedor", method = RequestMethod.GET)
	@ResponseBody
	public void devuelveClienteProveedor(@RequestParam(value="id") Integer id, HttpServletResponse response) throws IOException{
		
		Gson 					gson 						= new Gson(); 
		JsonObject 				myObj 						= new JsonObject();
		
		JsonElement				jsClienteProveedor	 				= gson.toJsonTree(clienteProveedorService.buscar(id));
		
		myObj.add("objeto", jsClienteProveedor);			
		PrintWriter out = response.getWriter();		
		out.println(myObj.toString());	
		out.close();		
	}
		
	@PostMapping(value = "/aceptar_clienteProveedor")
	@ResponseBody
	public ClienteProveedor aceptar(@ModelAttribute("clienteProveedor") @Valid ClienteProveedor clienteProveedor, BindingResult result){
				
		try{
			
			if(clienteProveedor.getId()==null || clienteProveedor.getId()==0){
				
				clienteProveedor.setId(0);	
				clienteProveedorService.guardar(clienteProveedor);				
				clienteProveedor = new ClienteProveedor();
                
			//Actualización	
			}else{				
				clienteProveedorService.actualizar(clienteProveedor);				
			}
			
			clienteProveedor.setStatus("SUCCESS");
			
		} catch (Exception e) {
			
			FieldError error;
			
            if (e.getClass().equals(DataIntegrityViolationException.class)){	                	   
                error = new FieldError("clienteProveedor", "agen_codigo", "El código de clienteProveedor ya existe.");                
                result.addError(error);
	        }      
			
            clienteProveedor.setStatus("FAIL");
            clienteProveedor.setResult(result.getAllErrors());
		}			
				
		return clienteProveedor;
	}
	
	@RequestMapping(value = "/clienteProveedorLista&id={idClienteProveedor}/eliminar", method = RequestMethod.POST)
	@ResponseBody
	public String eliminarDeLista(@PathVariable("idClienteProveedor") Integer idClienteProveedor){
		
		ClienteProveedor clienteProveedor = clienteProveedorService.buscar(idClienteProveedor);
		
		try{
			clienteProveedorService.eliminar(clienteProveedor);	
			return "ok";			
		}catch(Exception e){			
			return "error";			
		}		
	}
	
	//Exportación a Excel
	@RequestMapping(value = "/exportarClienteProveedor", method = RequestMethod.GET)
    public ModelAndView descargarExcel() {
		
		List<ClienteProveedor> lista = clienteProveedorService.listado();
				
        return new ModelAndView("excelViewClienteProveedor", "clienteProveedor", lista);
    }	
	
}