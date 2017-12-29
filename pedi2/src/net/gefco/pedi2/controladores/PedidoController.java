package net.gefco.pedi2.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.gefco.pedi2.modelo.Pedido;
import net.gefco.pedi2.negocio.ClienteService;
import net.gefco.pedi2.negocio.NodoService;
import net.gefco.pedi2.negocio.PedidoService;

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
public class PedidoController {
	
	@Autowired
	private PedidoService 				pedidoService;
	
	@Autowired
	private ClienteService 				clienteService;
	
	@Autowired
	private NodoService 				nodoService;
	
	//Nos lleva a la pagina
	@RequestMapping("/pedidoLista")
	public String indice(Model model){		
		
		model.addAttribute("pedido", new Pedido()); //Al iniciar página siempre pasamos instancia vacía
		model.addAttribute("listaPedidos", pedidoService.listado());
		model.addAttribute("listaNodosOrigen", nodoService.listadoOrigenes());
		model.addAttribute("listaNodosDestino", nodoService.listadoDestinos());
		
		return "pedidoLista";
	}
	
	@RequestMapping(value= "/pedidoLista/carga_tabla", method = RequestMethod.POST)
	public String cargaTabla(Model model){
		
		model.addAttribute("listaPedidos", pedidoService.listado());
		
		return "tables/tablaPedidos";
	}
	
	@RequestMapping(value = "/devuelve_pedido", method = RequestMethod.GET)
	@ResponseBody
	public void devuelveClienteNodo(@RequestParam(value="id") Integer id, HttpServletResponse response) throws IOException{
		
		Gson 					gson 						= new Gson(); 
		JsonObject 				myObj 						= new JsonObject();
		
		JsonElement				jsPedido		 			= gson.toJsonTree(pedidoService.buscar(id));
		
		myObj.add("objeto", jsPedido);			
		PrintWriter out = response.getWriter();		
		out.println(myObj.toString());	
		out.close();		
	}
		
	@PostMapping(value = "/aceptar_pedido")
	@ResponseBody
	public Pedido aceptar(@ModelAttribute("pedido") @Valid Pedido pedido, BindingResult result){
				
		try{
			
			if(pedido.getId()==null || pedido.getId()==0){
				
				pedido.setId(0);	
				pedidoService.guardar(pedido);				
				pedido = new Pedido();
                
			//Actualización	
			}else{				
				pedidoService.actualizar(pedido);				
			}
			
			pedido.setStatus("SUCCESS");
			
		} catch (Exception e) {
			
			FieldError error;
			
            if (e.getClass().equals(DataIntegrityViolationException.class)){	                	   
                error = new FieldError("pedido", "pedi_codigo", "El código ya existe.");                
                result.addError(error);
	        }      
			
            pedido.setStatus("FAIL");
            pedido.setResult(result.getAllErrors());
		}			
				
		return pedido;
	}
	
	@RequestMapping(value = "/pedidoLista&id={idPedido}/eliminar", method = RequestMethod.POST)
	@ResponseBody
	public String eliminarDeLista(@PathVariable("idPedido") Integer idPedido){
		
		Pedido pedido = pedidoService.buscar(idPedido);
		
		try{
			pedidoService.eliminar(pedido);	
			return "ok";			
		}catch(Exception e){			
			return "error";			
		}		
	}
	
	//Exportación a Excel
	@RequestMapping(value = "/exportarPedido", method = RequestMethod.GET)
    public ModelAndView descargarExcel() {
		
		List<Pedido> lista = pedidoService.listado();
				
        return new ModelAndView("excelViewPedido", "pedido", lista);
    }	
	
}