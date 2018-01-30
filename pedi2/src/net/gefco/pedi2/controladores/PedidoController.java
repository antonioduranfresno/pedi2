package net.gefco.pedi2.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.gefco.pedi2.modelo.Cliente;
import net.gefco.pedi2.modelo.Factura;
import net.gefco.pedi2.modelo.Nodo;
import net.gefco.pedi2.modelo.Pedido;
import net.gefco.pedi2.modelo.TarifaVenta;
import net.gefco.pedi2.modelo.Usuario;
import net.gefco.pedi2.modelo.Zona;
import net.gefco.pedi2.negocio.ClienteNodoService;
import net.gefco.pedi2.negocio.ClienteService;
import net.gefco.pedi2.negocio.FacturaService;
import net.gefco.pedi2.negocio.NodoService;
import net.gefco.pedi2.negocio.PedidoService;
import net.gefco.pedi2.negocio.ProveedorService;
import net.gefco.pedi2.negocio.TarifaVentaService;
import net.gefco.pedi2.util.Fechas;
import net.gefco.pedi2.util.Numeros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Controller
@SessionAttributes("usuarioSesion")
public class PedidoController {
	
	@Autowired
	private PedidoService 				pedidoService;
	
	@Autowired
	private ClienteService 				clienteService;
	
	@Autowired
	private ClienteNodoService 			clienteNodoService;
	
	@Autowired
	private NodoService 				nodoService;
	
	@Autowired
	private ProveedorService 			proveedorService;
	
	@Autowired
	private TarifaVentaService 			tarifaVentaService;
	
	@Autowired
	private FacturaService 				facturaService;
	
	//Nos lleva a la pagina: si existe idGrupo nos lleva a grupoLista.jsp; si no, a pedidoLista.jsp
	@RequestMapping("/pedidoLista")
	public String indice(Model model, @RequestParam(value="idGrupo",required=false) Integer idGrupo, @RequestParam(value="idFactura",required=false) Integer idFactura){		
		
		//Añadimos las listas para los filtros
		model.addAttribute("listaClientes", clienteService.listado());
		model.addAttribute("listaNodosDestino", nodoService.listadoPlataformas());
		
		model.addAttribute("factura", facturaService.buscar(idFactura));
		
		if(idGrupo!=null){
			return "grupoLista";	
		}else{
			return "pedidoLista";
		}
	}
	
	@RequestMapping(value= "/pedidoLista/carga_tabla", method = RequestMethod.POST)
	public String cargaTablaPedidos(Model model){
		
		Usuario usuarioSesion = (Usuario) model.asMap().get("usuarioSesion");
				
		//Parametros para mostrar solo los pedidos pendientes
		String paramCliente 		= "> 0";
		String paramNodoDestino 	= "> 0";
		String paramFechaEntrega	= "> '2000-01-01'";
		String paramEstadoFactura   = "and factura is null";
		
		model.addAttribute("listaPedidos", pedidoService.listadoFiltro(usuarioSesion.getAgencia(), paramCliente, paramNodoDestino, paramFechaEntrega, paramEstadoFactura));
		
		return "tables/tablaPedidos";
	}
	
	@RequestMapping(value= "/grupoLista&idGrupo={idGrupo}/carga_tabla", method = RequestMethod.POST)
	public String cargaTablaGrupoPedidos(Model model, @PathVariable("idGrupo") Integer idGrupo){
				
		model.addAttribute("listaPedidos", pedidoService.listadoPedidosGrupo(idGrupo));
		
		return "tables/tablaGrupoPedidos";
	}
	
	@RequestMapping(value= "/pedidoLista&idFactura={idFactura}/carga_tabla", method = RequestMethod.POST)
	public String cargaTablaPedidosFactura(Model model, @PathVariable("idFactura") Integer idFactura){
		
		model.addAttribute("listaPedidos", pedidoService.listadoPedidosFactura(idFactura)); //Puede ser factura o prefactura
		
		return "tables/tablaPedidos";
	}

	@RequestMapping(value= "/pedidoFiltroLista/carga_tabla", method = RequestMethod.POST)
	public String cargaTablaPedidosFiltros(Model model, @RequestParam(value="idCliente") Integer idCliente, 
			@RequestParam(value="idNodoDestino") Integer idNodoDestino, @RequestParam(value="fechaEntrega") String fechaEntrega, 
			@RequestParam(value="idEstado") Integer idEstado) throws ParseException{
		
		Usuario usuarioSesion = (Usuario) model.asMap().get("usuarioSesion");
		
		String paramCliente 		= "";
		String paramNodoDestino 	= "";
		String paramFechaEntrega	= "";
		String paramEstadoFactura   = "";
		
		if(idCliente==0){
			paramCliente = "> 0";
		}else{
			paramCliente = "= "+idCliente;
		}
		
		if(idNodoDestino==0){
			paramNodoDestino = "> 0";
		}else{
			paramNodoDestino = "= "+idNodoDestino;
		}
		
		if(fechaEntrega.equals("")){
			paramFechaEntrega = "> '2000-01-01'";
		}else{
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			Date fechaEntregaSinFormato = sdf.parse(fechaEntrega);
			
			SimpleDateFormat sdf_sql = new SimpleDateFormat("yyyy-MM-dd");
			
			paramFechaEntrega = "<= '"+sdf_sql.format(fechaEntregaSinFormato)+"'";
		}
		
		switch (idEstado) {
			case 0:
				paramEstadoFactura   = "";
				break;
			case 1:
				paramEstadoFactura = "and factura is null";
				break;
			case 2:
				paramEstadoFactura = "and factura.fact_numeroFactura is null";
				break;
			case 3:
				paramEstadoFactura = "and factura.fact_numeroFactura is not null";
				break;			
			default:
				break;
		}
		
		model.addAttribute("listaPedidos", pedidoService.listadoFiltro(usuarioSesion.getAgencia(), paramCliente, paramNodoDestino, paramFechaEntrega, paramEstadoFactura));

		return "tables/tablaPedidos";
	}
	
	@RequestMapping(value = "/pedidoForm", method = RequestMethod.GET)
	public String mostrarFormulario(Model model, @RequestParam(value="idPedido",required=false) Integer idPedido){
		
		Usuario usuarioSesion = (Usuario) model.asMap().get("usuarioSesion");
		
		Pedido pedido = new Pedido();
				
		//Nuevo pedido
		if(idPedido==null){
			
			pedido.setPedi_fechaCreacion(new Date());
			pedido.setAgencia(usuarioSesion.getAgencia()); //Al crear el pedido toma la agencia del usuario			
			pedido.setPedi_totalPalets(0);
			pedido.setPedi_totalHuecos(0);
			pedido.setPedi_importe(0.0);
			pedido.setPedi_fechaTasacion(null);
			pedido.setPedi_anulado(false);
			pedido.setPedi_incidencia(false);
			pedido.setCliente(clienteService.buscarCliente(2)); //CD SUPPLY por defecto
			
			model.addAttribute("tipo", "");
						
		//Pedido existente	
		}else{
			
			pedido = pedidoService.buscar(idPedido);
		
			//Por si en el futuro cambia la configuración Cliente - Nodo			
			List<Nodo> listaNodosOrigen = clienteNodoService.listadoOrigenesCliente(pedido.getCliente());
			
			Boolean encontradoOrigen = false;
			
			for(Nodo nodo : listaNodosOrigen){				
				if(nodo.getId()==pedido.getNodoOrigen().getId()){					
					encontradoOrigen = true;
					break;					
				}				
			}
			
			if(!encontradoOrigen){
				listaNodosOrigen.add(nodoService.buscar(pedido.getNodoOrigen().getId()));
			}
			
			List<Nodo> listaNodosDestino = clienteNodoService.listadoDestinosCliente(pedido.getCliente());
			
			Boolean encontradoDestino = false;
			
			for(Nodo nodo : listaNodosDestino){				
				if(nodo.getId()==pedido.getNodoDestino().getId()){					
					encontradoDestino = true;
					break;					
				}				
			}
			
			if(!encontradoDestino){
				listaNodosDestino.add(nodoService.buscar(pedido.getNodoDestino().getId()));
			}
			
			
			model.addAttribute("listaNodosOrigen", listaNodosOrigen);
			model.addAttribute("listaNodosDestino", listaNodosDestino);

			model.addAttribute("tipo", proveedorService.tipoProveedor(pedido.getNodoOrigen().getProveedor()));
			
		}
	
		model.addAttribute("pedido", pedido);			
		model.addAttribute("listaClientes", clienteService.listado());
				
		return "pedidoForm";
	}
	
	@RequestMapping(value = "/pedidoForm/selCliente={idCliente}") 
	@ResponseBody 
	public String seleccionCliente(@PathVariable("idCliente") Integer idCliente){
	
		Cliente cliente = clienteService.buscarCliente(idCliente);
		
		Gson 					gson 						= new Gson(); 
		JsonObject 				myObj 						= new JsonObject();
				
		StringBuilder cadenaOrigenes = new StringBuilder();
		
		cadenaOrigenes.append("<option value='0'>Selección</option>");
		
		for(Nodo nodo : clienteNodoService.listadoOrigenesCliente(cliente)){		
			cadenaOrigenes.append("<option value='"+nodo.getId()+"'>"+nodo.getProveedor().getProv_nombre()+" - " + nodo.getNodo_nombre() + "</option>");			
		}
		
		StringBuilder cadenaDestinos = new StringBuilder();
		
		cadenaDestinos.append("<option value='0'>Selección</option>");
		
		for(Nodo nodo : clienteNodoService.listadoDestinosCliente(cliente)){		
			cadenaDestinos.append("<option value='"+nodo.getId()+"'>"+nodo.getProveedor().getProv_nombre()+" - " + nodo.getNodo_nombre() + "</option>");			
		}
		
		JsonElement				jsListaOrigenes		= gson.toJsonTree(cadenaOrigenes.toString());
		JsonElement				jsListaDestinos		= gson.toJsonTree(cadenaDestinos.toString());
			
		myObj.add("listaOrigenes", jsListaOrigenes);
		myObj.add("listaDestinos", jsListaDestinos);
		
		return myObj.toString();		
	}	
	
	@RequestMapping(value = "/pedidoForm/selNodoOrigen={idNodoOrigen}") 
	@ResponseBody 
	public String seleccionOrigen(@PathVariable("idNodoOrigen") Integer idNodoOrigen){
	
		Nodo nodo = nodoService.buscar(idNodoOrigen);
		
		Gson 					gson 						= new Gson(); 
		JsonObject 				myObj 						= new JsonObject();
				
		JsonElement				jsTipo = gson.toJsonTree(proveedorService.tipoProveedor(nodo.getProveedor()));
		
		myObj.add("tipo", jsTipo);
		
		return myObj.toString();		
	}

	@RequestMapping(value = "/pedidoForm", method = RequestMethod.POST)
	public String aceptar(Model model, @ModelAttribute("pedido") @Valid Pedido pedido, BindingResult result){
		
		Usuario usuarioSesion = (Usuario) model.asMap().get("usuarioSesion");
		
		String tipo = "";
		
		//Trims
		if(pedido.getPedi_codigo()!=null){			
			pedido.setPedi_codigo(pedido.getPedi_codigo().trim());
		}
		
		if(pedido.getPedi_referencia()!=null){			
			pedido.setPedi_referencia(pedido.getPedi_referencia().trim());
		}
		
		if(pedido.getPedi_matricula()!=null){			
			pedido.setPedi_matricula(pedido.getPedi_matricula().trim());
		}
		//Fin trims		
		
		if(pedido.getCliente().getId()==0){			
			FieldError error = new FieldError("pedido", "cliente.id", "Seleccione el cliente");			
			result.addError(error);			
		}
		
		if(pedido.getNodoOrigen().getId()==0){			
			FieldError error = new FieldError("pedido", "nodoOrigen.id", "Seleccione origen");			
			result.addError(error);			
		}else{			
			pedido.setNodoOrigen(nodoService.buscar(pedido.getNodoOrigen().getId()));			
			tipo = proveedorService.tipoProveedor(pedido.getNodoOrigen().getProveedor());
		}
		
		if(pedido.getNodoDestino().getId()==0){			
			FieldError error = new FieldError("pedido", "nodoDestino.id", "Seleccione destino");			
			result.addError(error);			
		}
		
		if(pedido.getPedi_totalHuecos()>pedido.getPedi_totalPalets()){
			FieldError error = new FieldError("pedido", "pedi_totalHuecos", "!");			
			result.addError(error);
		}
		
		//Si no se pone este código intenta relacionar el pedido con una factura que no existe
		if(pedido.getFactura().getId()==null){			
			pedido.setFactura(null);			
		//Si el pedido está asociado con una factura	
		}else{
			
			Factura factura = facturaService.buscar(pedido.getFactura().getId());
			
			Calendar fechaEntrega = Fechas.toCalendar(pedido.getPedi_fechaEntrega());
			Calendar fechaLimite  = Fechas.toCalendar(factura.getFact_fechaLimite());
			
			//Solo puedo modificar la fecha Entrega de un pedido prefacturado si dicha fecha es inferior o igual a la fecha limite de la prefactura 
			if(fechaEntrega.after(fechaLimite)){
				
				FieldError error = new FieldError("pedido", "pedi_fechaEntrega", "!");			
				result.addError(error);
				
			}else{

				//Si el pedido tiene incidencia, la prefactura a la que está asociado se actualiza como rechazada
				if(pedido.getPedi_incidencia().booleanValue()==true){
					factura.setFact_estadoPrefactura(2);				
				}else{
					factura.setFact_estadoPrefactura(0);
				}
				
				facturaService.actualizar(factura);
				
			}
			
		}
		
		//Siempre se actualiza la fecha de actualización y el usuario
		pedido.setPedi_fechaActualizacion(new Date());
		pedido.setPedi_usuarioActualizacion(usuarioSesion.getUsua_matricula());
		
		if(!result.hasErrors()){			
			
			try{
				
				if(pedido.getId()==null || pedido.getId()==0){
					
					pedido.setId(0);
					pedidoService.guardar(pedido);		
					                
				//Actualización	
				}else{		
					
					if(pedido.getPedi_grupo()!=null){
					
						//Calcular tarifa proporcional del pedido en función del grupo
						Double		 nuevaTarifa	= 0.0;	
						
						List<Pedido> listaPedidos 	= pedidoService.listadoPedidosGrupo(pedido.getPedi_grupo());
						
						//1º Calculo el nº de huecos
						Integer totalHuecos = 0;			
						for (Pedido ped : listaPedidos) {			
							if(ped.getId()==pedido.getId()){
								ped.setPedi_totalHuecos(pedido.getPedi_totalHuecos());
							}				
							totalHuecos = totalHuecos + ped.getPedi_totalHuecos();
						}
						
						Double importeCamion 	= tarifaVentaService.obtenerTarifa(pedido.getAgencia().getId(), 
									pedido.getCliente().getId(), pedido.getNodoOrigen().getId(), 
									pedido.getNodoDestino().getId(), 
									pedido.getPedi_fechaEntrega()).getTave_importeCamionCompleto();
						
						//2º Aplico nuevos valores a todos los pedidos del grupo y actualizo
						for(Pedido ped : listaPedidos){
							
							//Para que el pedido que estoy viendo coja el resto de cambios (incidencia, anulado, codigo...) lo actualizo por separado
							if(ped.getId()==pedido.getId()){
								ped = pedido;
							}
							
							nuevaTarifa = Numeros.redondea((ped.getPedi_totalHuecos()*importeCamion)/totalHuecos);
							ped.setPedi_importe(nuevaTarifa);
							ped.setPedi_fechaEntrega(pedido.getPedi_fechaEntrega()); //Ya actualizo la fecha de entrega para todos
							pedidoService.actualizar(ped);							
						}
												
					}else{
						pedidoService.actualizar(pedido);	
					}
					
				}
				
				return "pedidoLista"; 
				
			} catch (Exception e) {
								
				//Key 2 es el código de pedido
				if(e.getMessage().contains("key 2")){					
					FieldError error = new FieldError("pedido", "pedi_codigo", "Código repetido");				
					result.addError(error);
				}else{
					FieldError error = new FieldError("pedido", "cliente.id", "error no controlado: " + e.getMessage());				
					result.addError(error);
				}
				
			}
			
		}
		
		//Le pasamos el valor completo de las propiedades NodoOrigen y Cliente para mostrar la info en las cajas deshabilitadas		
		pedido.setCliente(clienteService.buscarCliente(pedido.getCliente().getId()));
		
		model.addAttribute("pedido", pedido);		
				
		model.addAttribute("listaClientes", clienteService.listado());		
		model.addAttribute("listaNodosOrigen", clienteNodoService.listadoOrigenesCliente(pedido.getCliente()));
		model.addAttribute("listaNodosDestino", clienteNodoService.listadoDestinosCliente(pedido.getCliente()));
		model.addAttribute("tipo", tipo);
						
		return "pedidoForm";
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
	
	@RequestMapping(value = "/devuelve_tarifa", method = RequestMethod.GET)
	@ResponseBody
	public void devuelveTarifa(@RequestParam(value="idAgencia") Integer idAgencia, @RequestParam(value="idCliente") Integer idCliente,
			@RequestParam(value="idNodoOrigen") Integer idNodoOrigen, @RequestParam(value="idNodoDestino") Integer idNodoDestino, 
			@RequestParam(value="fecha") String fecha, @RequestParam(value="numHuecos") Integer numHuecos, @RequestParam(value="idPedido") Integer idPedido,  
			HttpServletResponse response) throws IOException, ParseException{
		
		Gson 					gson 						= new Gson(); 
		JsonObject 				myObj 						= new JsonObject();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
		JsonElement				jsTarifa					= null;
		JsonElement				jsValorTarifa				= null;
		JsonElement				jsFechaTasacion				= null;
		JsonElement				jsMensaje					= null;
		
		TarifaVenta				tarifa						= tarifaVentaService.obtenerTarifa(idAgencia, idCliente, idNodoOrigen, idNodoDestino, sdf.parse(fecha));
		
		Pedido 					pedido						= pedidoService.buscar(idPedido);
			
		Boolean					error						= false;
		String					mensaje						= null;
		
		if(pedido!=null && pedido.getPedi_grupo()!=null){
			
			//Calcular tarifa proporcional del pedido en función del grupo
			Double		 nuevaTarifa	= 0.0;	
			
			List<Pedido> listaPedidos 	= pedidoService.listadoPedidosGrupo(pedido.getPedi_grupo());
			
			//1º Calculo el nº de huecos
			Integer totalHuecos = 0;			
			for (Pedido ped : listaPedidos) {			
				if(ped.getId()==idPedido){
					ped.setPedi_totalHuecos(numHuecos);
				}				
				totalHuecos = totalHuecos + ped.getPedi_totalHuecos();
			}
			
			if(totalHuecos<=33){
				
				Double importeCamion 	= tarifaVentaService.obtenerTarifa(pedido.getAgencia().getId(), 
						pedido.getCliente().getId(), pedido.getNodoOrigen().getId(), 
						pedido.getNodoDestino().getId(), 
						pedido.getPedi_fechaEntrega()).getTave_importeCamionCompleto();
			
				//2º Calculo tarifa
				for(Pedido ped : listaPedidos){
					
					//Solo me interesa para este, solo quiero visualizar la tarifa del pedido afectado
					if(ped.getId()==idPedido){					
						nuevaTarifa = Numeros.redondea((ped.getPedi_totalHuecos()*importeCamion)/totalHuecos);										
					}
				}
				
				jsValorTarifa			= gson.toJsonTree(nuevaTarifa);
				
			}else{
				mensaje = "Error: Cantidad de huecos superior a 33: "+totalHuecos;
				error = true;				
			}
						
		}else{		
			
			jsValorTarifa			= gson.toJsonTree(tasacion(numHuecos, tarifa));	
			
		}
		
		if(!error){
			
			jsTarifa				= gson.toJsonTree(tarifa);
			jsFechaTasacion			= gson.toJsonTree(sdf.format(new Date()));
			
			myObj.add("tarifa", jsTarifa);
			myObj.add("valorTarifa", jsValorTarifa);
			myObj.add("fechaTasacion", jsFechaTasacion);
			
		}else{
			
			jsMensaje				= gson.toJsonTree(mensaje);
			
			myObj.add("mensaje", jsMensaje);
		}
		
		PrintWriter out = response.getWriter();		
		out.println(myObj.toString());	
		out.close();		
	}
	
	private Double tasacion(Integer numeroHuecos, TarifaVenta tarifa){
		
		Double valorTarifa = 0.0;
		
		if(tarifa==null){
			return 0.0; //Para que no siga leyendo
		}
		
		if(tarifa.getTave_numeroMaxT5()!=null){			
			if(numeroHuecos<=tarifa.getTave_numeroMaxT5()){
				valorTarifa = tarifa.getTave_importeT5() * numeroHuecos;
			}
		}
		
		if(tarifa.getTave_numeroMaxT4()!=null){			
			if(numeroHuecos<=tarifa.getTave_numeroMaxT4()){
				valorTarifa = tarifa.getTave_importeT4() * numeroHuecos;
			}
		}
		
		if(tarifa.getTave_numeroMaxT3()!=null){			
			if(numeroHuecos<=tarifa.getTave_numeroMaxT3()){
				valorTarifa = tarifa.getTave_importeT3() * numeroHuecos;
			}
		}
		
		if(tarifa.getTave_numeroMaxT2()!=null){			
			if(numeroHuecos<=tarifa.getTave_numeroMaxT2()){
				valorTarifa = tarifa.getTave_importeT2() * numeroHuecos;
			}
		}
		
		if(tarifa.getTave_numeroMaxT1()!=null){			
			if(numeroHuecos<=tarifa.getTave_numeroMaxT1()){
				valorTarifa = tarifa.getTave_importeT1() * numeroHuecos;
			}
		}

		if(!tarifa.getTave_soloTranchas()){
			if(numeroHuecos>=tarifa.getTave_numeroDesdeCC()){
				valorTarifa = tarifa.getTave_importeCamionCompleto();				
			}			
		}
						
		return valorTarifa;
	}
	
	@RequestMapping(value = "/agrupa_pedidos", method = RequestMethod.GET)
	@ResponseBody
	public void agrupaPedidos(@RequestParam(value="lista") String lista, HttpServletResponse response) throws IOException, ParseException{
		
		Gson 					gson 						= new Gson(); 
		JsonObject 				myObj 						= new JsonObject();
		
		String					mensaje						= "";
		SimpleDateFormat		sdf							= new SimpleDateFormat("dd/MM/yyyy");
		
		//Reemplaza los elementos propios del array para dejarlo como una lista de numeros (pero es un string)
		String listaLimpia = lista.replace("[", "").replace("]", "").replace("\"", "");
				
		//El string lo convierte en lista de strings
		List<String> items = Arrays.asList(listaLimpia.split("\\s*,\\s*"));
		
		//Obtenemos el id de cada string de la lista de strings y generamos la lista de pedidos a agrupar
		List<Pedido> listaPedidos = new ArrayList<Pedido>();
		
		for(String item : items){			
			Integer id = new Integer(item);						
			listaPedidos.add(pedidoService.buscar(id));
		}
		
		//Comprobación de que los pedidos se pueden agrupar
		Boolean error = false;

		Integer totalHuecos				= 0;	 		
		Double  importeCamionPedido 	= 0.0; //variable para usar si se cumplen todas las premisas
		
		//1)Que hay mínimo 2 pedidos
		if(listaPedidos.size()>1){
		
			for (int i = 0; i < listaPedidos.size(); i++) {

				Zona   zona 			= listaPedidos.get(i).getNodoDestino().getZona();
				Date   fechaEntrega		= listaPedidos.get(i).getPedi_fechaEntrega();
				Double importeCamion 	= tarifaVentaService.obtenerTarifa(listaPedidos.get(i).getAgencia().getId(), 
										  listaPedidos.get(i).getCliente().getId(), listaPedidos.get(i).getNodoOrigen().getId(), 
										  listaPedidos.get(i).getNodoDestino().getId(), 
										  listaPedidos.get(i).getPedi_fechaEntrega()).getTave_importeCamionCompleto();
				
				if((i+1)<listaPedidos.size() && listaPedidos.get(i+1)!=null){
				
					//2) Todos tienen misma zona de destino
					if(!zona.getId().equals(listaPedidos.get(i+1).getNodoDestino().getZona().getId())){
						mensaje = "Error: Zonas de destino distintas: "+zona.getZona_nombre() + " <> "+listaPedidos.get(i+1).getNodoDestino().getZona().getZona_nombre();						
						error = true;
						break;					
					}else{
						error = false;
					}
					
					//3) Todos tienen misma fecha entrega
					if(!sdf.format(fechaEntrega).equals(sdf.format(listaPedidos.get(i+1).getPedi_fechaEntrega()))){ //Comparo 2 strings
						mensaje = "Error: Fechas de entrega distintas: "+sdf.format(fechaEntrega) + " <> " +sdf.format(listaPedidos.get(i+1).getPedi_fechaEntrega());
						error = true;
						break;					
					}else{
						error = false;
					}
					
					//4) Las tarifas a camión completo para todos los destinos coinciden
					Double importeSiguienteCamion = tarifaVentaService.obtenerTarifa(listaPedidos.get(i+1).getAgencia().getId(), 
							  listaPedidos.get(i+1).getCliente().getId(), listaPedidos.get(i+1).getNodoOrigen().getId(), 
							  listaPedidos.get(i+1).getNodoDestino().getId(), 
							  listaPedidos.get(i+1).getPedi_fechaEntrega()).getTave_importeCamionCompleto();
					
					if(!importeCamion.equals(importeSiguienteCamion)){					
						mensaje = "Error: Importes camión completo distintos: "+importeCamion+ " <> "+importeSiguienteCamion;
						error = true;
						break;					
					}else{
						error = false;
					}
					
				}
				
				//5) Si el pedido ya está agrupado
				if(listaPedidos.get(i).getPedi_grupo()!=null){
					mensaje = "Error: El pedido "+listaPedidos.get(i).getPedi_codigo()+" ya está agrupado.";
					error = true;
					break;
				}else{
					error = false;
				}
				
				totalHuecos = totalHuecos + listaPedidos.get(i).getPedi_totalHuecos();
				
				importeCamionPedido = importeCamion; //Se supone que deben coincidir, por lo que puedo coger el importe de cualquier pedido
				
			}
			
			//6) La suma de los huecos es <=33
			if(totalHuecos>33){
				mensaje = "Error: Cantidad de huecos superior a 33: "+totalHuecos;
				error = true;
			}
			
		}else{
			mensaje = "Error: Se deben seleccionar al menos 2 pedidos";
			error = true;
		}
		
		//Agrupación: distribuir precio camión completo 
		if(!error){
			
			Integer idGrupo = pedidoService.obtenerUltimoGrupo()+1;

			for(Pedido pedido : listaPedidos){
				
				Double nuevaTarifa = Numeros.redondea((pedido.getPedi_totalHuecos()*importeCamionPedido)/totalHuecos);

				//Asignación del nuevo importe y del idGrupo a cada pedido
				pedido.setPedi_grupo(idGrupo);
				pedido.setPedi_importe(nuevaTarifa);
				pedidoService.actualizar(pedido);
				
			}
			
			mensaje = "Pedidos agrupados con éxito";
			
		}
		
		JsonElement				jsMensaje					= gson.toJsonTree(mensaje);
				
		myObj.add("mensaje", jsMensaje);
		
		PrintWriter out = response.getWriter();		
		out.println(myObj.toString());	
		out.close();		
	}
	
	@RequestMapping(value = "/desagrupa_pedidos", method = RequestMethod.GET)
	@ResponseBody
	public void desagrupaPedidos(@RequestParam(value="lista") String lista, HttpServletResponse response) throws IOException, ParseException{
		
		Gson 					gson 						= new Gson(); 
		JsonObject 				myObj 						= new JsonObject();
		
		String					mensaje						= "";
		
		//Reemplaza los elementos propios del array para dejarlo como una lista de numeros (pero es un string)
		String listaLimpia = lista.replace("[", "").replace("]", "").replace("\"", "");
				
		//El string lo convierte en lista de strings
		List<String> items = Arrays.asList(listaLimpia.split("\\s*,\\s*"));
		
		//Obtenemos el id de cada string de la lista de strings y generamos la lista de pedidos a agrupar
		List<Pedido> listaPedidos = new ArrayList<Pedido>();
		
		for(String item : items){			
			Integer id = new Integer(item);						
			listaPedidos.add(pedidoService.buscar(id));
		}
		
		StringBuilder cadena = new StringBuilder();
		
		for(Pedido pedido : listaPedidos){
			
			TarifaVenta tarifa = tarifaVentaService.obtenerTarifa(pedido.getAgencia().getId(), 
					  pedido.getCliente().getId(), pedido.getNodoOrigen().getId(), pedido.getNodoDestino().getId(), 
					  pedido.getPedi_fechaEntrega());
			
			pedido.setPedi_importe(tasacion(pedido.getPedi_totalHuecos(), tarifa));
			pedido.setPedi_grupo(null);
			pedidoService.actualizar(pedido);
			
			cadena.append(pedido.getPedi_codigo() +" ");
			
		}
		
		mensaje = "Los pedidos "+cadena.toString()+ "se han desagrupado.";
				
		JsonElement				jsMensaje					= gson.toJsonTree(mensaje);
		
		myObj.add("mensaje", jsMensaje);
		
		PrintWriter out = response.getWriter();		
		out.println(myObj.toString());	
		out.close();	
		
	}
	
	@RequestMapping(value = "/desvinculaPedido&id={idPedido}", method = RequestMethod.POST)
	@ResponseBody
	public String desvinculaPedido(@PathVariable("idPedido") Integer idPedido){
		
		Pedido pedido 	= pedidoService.buscar(idPedido);
		Factura factura = pedido.getFactura(); 
		
		try{

			//1)Si perteneciera a un grupo debemos recalcular el reparto sobre el resto de pedidos del grupo
			if(pedido.getPedi_grupo()!=null){
				
				List<Pedido> listaPedidos	 = pedidoService.listadoPedidosGrupo(pedido.getPedi_grupo());
				
				//Sacamos el pedido de la lista del grupo
				int i = 0;
				for(Pedido ped : listaPedidos){
					if(ped.getId()==idPedido){
						listaPedidos.remove(i);
					}
					i++;
				}
				
				
				Integer cantidadPedidosGrupo = listaPedidos.size();
								
				//2.1)Si quedan al menos 2 pedidos podemos mantener el grupo
				if(cantidadPedidosGrupo>=2){
					
					Integer totalHuecos				= 0;	 		
					Double  importeCamionPedido 	= tarifaVentaService.obtenerTarifa(pedido.getAgencia().getId(), 
														pedido.getCliente().getId(), pedido.getNodoOrigen().getId(), 
														pedido.getNodoDestino().getId(),pedido.getPedi_fechaEntrega()).getTave_importeCamionCompleto();
					
					for (int j = 0; j < cantidadPedidosGrupo; j++) {
						totalHuecos = totalHuecos + listaPedidos.get(j).getPedi_totalHuecos();
					}
					
					for(Pedido pedidoGrupo : listaPedidos){						
						Double nuevaTarifa = Numeros.redondea((pedidoGrupo.getPedi_totalHuecos()*importeCamionPedido)/totalHuecos);
						pedidoGrupo.setPedi_importe(nuevaTarifa);
						pedidoService.actualizar(pedidoGrupo);
					}

				//2.2) Si solo queda 1 pedido tendremos que deshacerlo y retasarlo individualmente, como estaba al principio	
				}else{
					
					Pedido pedidoUnico = listaPedidos.get(0);
					
					TarifaVenta tarifa = tarifaVentaService.obtenerTarifa(pedidoUnico.getAgencia().getId(), 
							  pedidoUnico.getCliente().getId(), pedidoUnico.getNodoOrigen().getId(), pedidoUnico.getNodoDestino().getId(), 
							  pedidoUnico.getPedi_fechaEntrega());
					
					pedidoUnico.setPedi_importe(tasacion(pedidoUnico.getPedi_totalHuecos(), tarifa));
					pedidoUnico.setPedi_grupo(null);
					pedidoService.actualizar(pedidoUnico);
					
				}
				
			}
			
			//2)Lo sacamos de la prefactura y del grupo definitivamente
			pedido.setFactura(null);
			pedido.setPedi_grupo(null);
			
			pedidoService.actualizar(pedido);
			
			//3)Recalculamos los totales prefactura
			recalculaTotalesFactura(factura);
			
			return "ok";			
		}catch(Exception e){			
			return "error";			
		}		
	}
		
	@RequestMapping(value = "/prefacturar", method = RequestMethod.GET)
	@ResponseBody
	public void prefacturar(@RequestParam(value="lista") String lista, @RequestParam(value="fechaPrefacturas") String fechaPrefacturas, 
			@RequestParam(value="fechaLimite") String fechaLimite, HttpServletResponse response) throws IOException, ParseException{
		
		Gson 					gson 						= new Gson(); 
		JsonObject 				myObj 						= new JsonObject();
		
		Integer		  			anyoPrefactura				= new Integer(fechaPrefacturas.substring(6)); //Si es hasta el final (11) no hace falta poner el ultimo indice
				
		//Reemplaza los elementos propios del array para dejarlo como una lista de numeros (pero es un string)
		String listaLimpia = lista.replace("[", "").replace("]", "").replace("\"", "");
				
		//El string lo convierte en lista de strings
		List<String> items = Arrays.asList(listaLimpia.split("\\s*,\\s*"));
		
		//Obtenemos el id de cada string de la lista de strings y generamos la lista de prefacturas a facturar
		List<Pedido> listaPedidos = new ArrayList<Pedido>();
		
		for(String item : items){			
			Integer id = new Integer(item);						
			listaPedidos.add(pedidoService.buscar(id));
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		for(Factura factura : facturaService.agrupaPedidos(listaPedidos)){
			
			String codigoNodoCliente 	= clienteNodoService.devuelveClienteNodo(factura.getCliente(), factura.getNodoDestino()).getClno_codigo();
			String numeroPrefactura 	= facturaService.ultimaPrefactura(factura.getAgencia(), anyoPrefactura);
			Date   fechaPrefactura		= sdf.parse(fechaPrefacturas);
			Date   fechaVencimiento		= Fechas.sumarDiasFecha(fechaPrefactura, factura.getCliente().getClie_diasVencimiento());
			Date   fechaLimitePedido	= sdf.parse(fechaLimite);
			Double totalCuota			= Numeros.redondea(factura.getFact_baseImponible()*0.21);
			Double totalDocumento		= Numeros.redondea(factura.getFact_baseImponible() + totalCuota);
			
			factura.setFact_numeroPrefactura(numeroPrefactura);
			factura.setFact_fechaPrefactura(fechaPrefactura);
			factura.setFact_codigoNodoCliente(codigoNodoCliente);
			factura.setFact_fechaVencimiento(fechaVencimiento);
			factura.setFact_fechaLimite(fechaLimitePedido);
			factura.setFact_estadoPrefactura(0);
			factura.setFact_cuotaIva(totalCuota);
			factura.setFact_totalDocumento(totalDocumento);
			
			Integer idFactura = facturaService.guardar(factura);
			
			//Actualizo los pedidos
			for(Pedido ped : listaPedidos){
				if(ped.getAgencia().getId().equals(factura.getAgencia().getId())){
					if(ped.getCliente().getId().equals(factura.getCliente().getId())){
						if(ped.getNodoOrigen().getProveedor().getProv_esPlataforma().equals(factura.getFact_esTransferencia())){
							if(ped.getNodoDestino().getId().equals(factura.getNodoDestino().getId())){
								ped.setFactura(new Factura(idFactura));
								pedidoService.actualizar(ped);
							}	
						}	
					}	
				}
			}
		}
		
		JsonElement				jsMensaje					= gson.toJsonTree("Prefacturas generadas con éxito");
		
		myObj.add("mensaje", jsMensaje);
		
		PrintWriter out = response.getWriter();		
		out.println(myObj.toString());	
		out.close();	
		
	}
	
	private void recalculaTotalesFactura(Factura factura){
		
		Double 	totalBases = 0.0;
				
		for(Pedido pedido : pedidoService.listadoPedidosFactura(factura.getId())){
			totalBases = totalBases + pedido.getPedi_importe();
		}
		
		Double  totalBaseImponible 	= Numeros.redondea(totalBases);
		Double  totalCuota 			= Numeros.redondea(totalBases*0.21);
		Double  totalDocumento		= Numeros.redondea(totalBaseImponible + totalCuota);
		Integer numeroPedidos		= pedidoService.listadoPedidosFactura(factura.getId()).size();
		
		factura.setFact_baseImponible(totalBaseImponible);
		factura.setFact_cuotaIva(totalCuota);
		factura.setFact_totalDocumento(totalDocumento);
		factura.setFact_estadoPrefactura(0); //Vuelve a estar pendiente
		factura.setFact_numeroPedidos(numeroPedidos);
		
		facturaService.actualizar(factura);
	}
	
}
