package mx.com.aforecoppel.wsuserscontrol.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import mx.com.aforecoppel.wsuserscontrol.objetos.Usuario;
import mx.com.aforecoppel.wsuserscontrol.service.interfaz.ITransMySqlServicio;

@RestController
public class HomeController {
	public String mensajeLog="::WSUSERSCONTROL:: ";
	@Autowired
	ITransMySqlServicio transMySqlServicio; /*SE INYECTA LA CAPA DE NEGOCIO*/
	
	private static final Logger logger = Logger.getLogger(HomeController.class);
	
	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		return new ModelAndView("home");
	}
	@RequestMapping(value="/getUsuarioLoginWS")
	public Map<String, Object> getUsuarioLoginWS(HttpServletRequest request){
		logger.info(mensajeLog);
		logger.info( mensajeLog + " INICIA CONSUMO DE METODO --> getUsuarioLoginWS();");
		
		Map<String,Object> map= new HashMap<String, Object>();
		String mensaje = "";
		int resultado = 0;
		Usuario usuarioResponse = null;
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		try {			
			Usuario usuario= new Usuario();
			usuario.setEmail(email);
			usuario.setPassword(password);
			
			usuarioResponse = transMySqlServicio.getUsuarioLogin(usuario.getEmail(), usuario.getPassword());
			resultado = 1;
			mensaje = "Informacion obtenida con exito!.";
			logger.info( mensajeLog + " METODO --> getUsuarioLoginWS(); Datos= "+ usuarioResponse.toString());
			
		} catch (Exception e) {
			resultado = -1;
			mensaje = "Fallo la obtencion de la informacion!. error: " + e.getMessage();
			e.printStackTrace();
			logger.error( mensajeLog + " METODO --> getUsuarioLoginWS(); Error= "+ e.getMessage());
		}
		
		logger.info( mensajeLog + "MAPEO DE DATOS :: ");
			map.put("mensaje",mensaje);
			map.put("resultadoOperacion", resultado);
			map.put("respuesta", usuarioResponse);
		logger.info( mensajeLog + " FINALIZA CONSUMO DE METODO --> getUsuarioLoginWS();");
		logger.info( mensajeLog);
		return map;
	}
}
