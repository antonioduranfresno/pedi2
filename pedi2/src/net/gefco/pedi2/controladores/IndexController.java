package net.gefco.pedi2.controladores;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.gefco.pedi2.modelo.Agencia;
import net.gefco.pedi2.modelo.Usuario;
import net.gefco.pedi2.negocio.AgenciaService;
import net.gefco.pedi2.negocio.UsuarioService;
import net.gefco.pedi2.util.Encriptacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Controller
@Scope("session")
@SessionAttributes("usuarioSesion")
public class IndexController {
	
	@Autowired
	private AgenciaService agenciaService;
	
	@Autowired
	private UsuarioService usuarioService;
			
	@RequestMapping("/")
	public String showIndex(Model model){
		
		model.addAttribute("listaAgencias", agenciaService.listado());
		
		return "index";
	}
	
	@RequestMapping("login")  
	public void login(Model model, HttpServletRequest request, HttpServletResponse response){

		try {
			
			Gson 					gson 						= new Gson(); 
			JsonObject 				myObj 						= new JsonObject();
			
			JsonElement				jsMensajeLogin 				= null;
						
			String login 	= request.getParameter("login");
			String password = request.getParameter("password");
			
			if(usuarioService.buscarMatricula(login.toUpperCase())!=null){
				
				Usuario usuarioAspirante = usuarioService.buscarMatricula(login.toUpperCase());
				
				if(Encriptacion.encriptar(password).equalsIgnoreCase(usuarioAspirante.getUsua_password())){
				
					model.addAttribute("usuarioSesion", usuarioAspirante);
					
				}else{				
					jsMensajeLogin = gson.toJsonTree("Error. Password incorrecto.");				
				}	
				
			}else{			
				jsMensajeLogin = gson.toJsonTree("Error. El usuario no existe en la base de datos.");			
			}
			
			myObj.add("mensaje", jsMensajeLogin);		
			PrintWriter out = response.getWriter();		
			out.println(myObj.toString());	
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("nuevoUsuario")  
	public void nuevoUsuario(HttpServletRequest request, HttpServletResponse response){
		
		try {
			
			Gson 					gson 						= new Gson(); 
			JsonObject 				myObj 						= new JsonObject();
			
			JsonElement				jsMensajeRegistro 			= null;
			
			String 	matricula 			= request.getParameter("matricula");		
			String 	nombre				= request.getParameter("nombre");
			String 	password			= request.getParameter("password");			
			Integer idAgencia			= new Integer(request.getParameter("agencia"));
			String 	correo	 			= request.getParameter("correo");
						
			if(usuarioService.buscarMatricula(matricula.toUpperCase())==null){
						
				Usuario usuario = new Usuario(0, matricula.toUpperCase(), nombre.toUpperCase(),  
												Encriptacion.encriptar(password), correo, new Agencia(idAgencia), false);				
				usuarioService.guardar(usuario);				
																
			}else{			
				jsMensajeRegistro = gson.toJsonTree("Error. El usuario "+matricula+" ya existe.");	
			}
					
			myObj.add("mensaje", jsMensajeRegistro);
			PrintWriter out = response.getWriter();		
			out.println(myObj.toString());	
			out.close();
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/inicio", method = RequestMethod.GET)
	public String mostrarInicio(){		
		return "inicio";
	}
	
		
}