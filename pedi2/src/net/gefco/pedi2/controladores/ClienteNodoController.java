package net.gefco.pedi2.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.gefco.pedi2.modelo.ClienteNodo;
import net.gefco.pedi2.negocio.ClienteNodoService;
import net.gefco.pedi2.negocio.ClienteService;
import net.gefco.pedi2.negocio.NodoService;

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
public class ClienteNodoController {
	
	@Autowired
	private ClienteNodoService 			clienteNodoService;
	
	@Autowired
	private ClienteService 				clienteService;
	
	@Autowired
	private NodoService 				nodoService;
	
	//Nos lleva a la pagina
	@RequestMapping("/clienteNodoLista")
	public String indice(Model model){		
		
		model.addAttribute("clienteNodo", new ClienteNodo()); //Al iniciar página siempre pasamos instancia vacía
		model.addAttribute("listaClientes", clienteService.listado());
		model.addAttribute("listaNodos", nodoService.listado());
		
		return "clienteNodoLista";
	}
	
	@RequestMapping(value= "/clienteNodoLista/carga_tabla", method = RequestMethod.POST)
	public String cargaTabla(Model model){
		
		model.addAttribute("listaClienteNodos", clienteNodoService.listado());
		
		return "tables/tablaClienteNodos";
	}
	
	@RequestMapping(value = "/devuelve_clienteNodo", method = RequestMethod.GET)
	@ResponseBody
	public void devuelveClienteNodo(@RequestParam(value="id") Integer id, HttpServletResponse response) throws IOException{
		
		Gson 					gson 						= new Gson(); 
		JsonObject 				myObj 						= new JsonObject();
		
		JsonElement				jsClienteNodo	 			= gson.toJsonTree(clienteNodoService.buscar(id));
		
		myObj.add("objeto", jsClienteNodo);			
		PrintWriter out = response.getWriter();		
		out.println(myObj.toString());	
		out.close();		
	}
		
	@PostMapping(value = "/aceptar_clienteNodo")
	@ResponseBody
	public ClienteNodo aceptar(@ModelAttribute("clienteNodo") @Valid ClienteNodo clienteNodo, BindingResult result){
			
		if (clienteNodo.getCliente().getId() == 0) {
			FieldError error = new FieldError("nodo", "error_cliente", "Debe seleccionar cliente");
			result.addError(error);
		}

		if (clienteNodo.getNodo().getId() == 0) {
			FieldError error = new FieldError("nodo", "error_nodo", "Debe seleccionar nodo");
			result.addError(error);
		}
		
		if (result.hasErrors()){
			clienteNodo.setStatus("FAIL");
			clienteNodo.setResult(result.getAllErrors());
            return clienteNodo;
		}
		
		try{
			
			if(clienteNodo.getId()==null || clienteNodo.getId()==0){
				
				clienteNodo.setId(0);	
				clienteNodoService.guardar(clienteNodo);				
				clienteNodo = new ClienteNodo();
                
			//Actualización	
			}else{				
				clienteNodoService.actualizar(clienteNodo);				
			}
			
			clienteNodo.setStatus("SUCCESS");
			
		} catch (Exception e) {
			
			FieldError error;
			
            if (e.getClass().equals(DataIntegrityViolationException.class)){	                	   
                error = new FieldError("clienteNodo", "clno_codigo", "El código ya existe.");                
                result.addError(error);
	        }      
			
            clienteNodo.setStatus("FAIL");
            clienteNodo.setResult(result.getAllErrors());
		}			
				
		return clienteNodo;
	}
	
	@RequestMapping(value = "/clienteNodoLista&id={idClienteNodo}/eliminar", method = RequestMethod.POST)
	@ResponseBody
	public String eliminarDeLista(@PathVariable("idClienteNodo") Integer idClienteNodo){
		
		ClienteNodo clienteNodo = clienteNodoService.buscar(idClienteNodo);
		
		try{
			clienteNodoService.eliminar(clienteNodo);	
			return "ok";			
		}catch(Exception e){			
			return "error";			
		}		
	}
	
	//Exportación a Excel
	@RequestMapping(value = "/exportarClienteNodo", method = RequestMethod.GET)
    public ModelAndView descargarExcel() {
		
		List<ClienteNodo> lista = clienteNodoService.listado();
				
        return new ModelAndView("excelViewClienteNodo", "clienteNodo", lista);
    }	
	
}