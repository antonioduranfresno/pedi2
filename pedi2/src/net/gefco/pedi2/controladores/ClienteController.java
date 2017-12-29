package net.gefco.pedi2.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.gefco.pedi2.modelo.Cliente;
import net.gefco.pedi2.negocio.ClienteService;

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
public class ClienteController {
	
	@Autowired
	private ClienteService 				clienteService;
	
	//Nos lleva a la pagina
	@RequestMapping("/clienteLista")
	public String indice(Model model){		
		
		model.addAttribute("cliente", new Cliente()); //Al iniciar página siempre pasamos instancia vacía
		
		return "clienteLista";
	}
	
	@RequestMapping(value= "/clienteLista/carga_tabla", method = RequestMethod.POST)
	public String cargaTabla(Model model){
		
		model.addAttribute("listaClientes", clienteService.listado());
		
		return "tables/tablaClientes";
	}
	
	@RequestMapping(value = "/devuelve_cliente", method = RequestMethod.GET)
	@ResponseBody
	public void devuelveCliente(@RequestParam(value="id") Integer id, HttpServletResponse response) throws IOException{
		
		Gson 					gson 						= new Gson(); 
		JsonObject 				myObj 						= new JsonObject();
		
		JsonElement				jsCliente	 				= gson.toJsonTree(clienteService.buscarCliente(id));
		
		myObj.add("objeto", jsCliente);			
		PrintWriter out = response.getWriter();		
		out.println(myObj.toString());	
		out.close();		
	}
		
	@PostMapping(value = "/aceptar_cliente")
	@ResponseBody
	public Cliente aceptar(@ModelAttribute("cliente") @Valid Cliente cliente, BindingResult result){
		
		try{
			
			if(cliente.getId()==null || cliente.getId()==0){
				
				cliente.setId(0);	
				clienteService.guardar(cliente);				
				cliente = new Cliente();
                
			//Actualización	
			}else{				
				clienteService.actualizar(cliente);				
			}
			
			cliente.setStatus("SUCCESS");
			
		} catch (Exception e) {
			
			FieldError error;
			
            if (e.getClass().equals(DataIntegrityViolationException.class)){	             
            	
            	//Distinguir si la violación de la unicidad se produce en el código nad_by o en el código nad_pr
            	
                error = new FieldError("cliente", "clie_nadBy", "El código NAD_BY / comprador, ya existe.");                
                result.addError(error);
	        }      
			
            cliente.setStatus("FAIL");
            cliente.setResult(result.getAllErrors());
		}			
				
		return cliente;
	}
	
	@RequestMapping(value = "/clienteLista&id={idCliente}/eliminar", method = RequestMethod.POST)
	@ResponseBody
	public String eliminarDeLista(@PathVariable("idCliente") Integer idCliente){
		
		Cliente cliente = clienteService.buscarCliente(idCliente);
		
		try{
			clienteService.eliminar(cliente);	
			return "ok";			
		}catch(Exception e){			
			return "error";			
		}		
	}
	
	//Exportación a Excel
	@RequestMapping(value = "/exportarCliente", method = RequestMethod.GET)
    public ModelAndView descargarExcel() {
		
		List<Cliente> lista = clienteService.listado();
				
        return new ModelAndView("excelViewCliente", "cliente", lista);
    }	
	
}