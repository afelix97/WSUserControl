package mx.com.aforecoppel.wsuserscontrol.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.aforecoppel.wsuserscontrol.model.interfaz.ITransMySqlModelo;
import mx.com.aforecoppel.wsuserscontrol.objetos.Usuario;
import mx.com.aforecoppel.wsuserscontrol.service.interfaz.ITransMySqlServicio;

@Service
public class TransMySqlImplServicio implements ITransMySqlServicio {

	private static final Logger logger 	   = Logger.getLogger(TransMySqlImplServicio.class);
	java.lang.String cMessageLog           = null;
	
	@Autowired
	ITransMySqlModelo transMySqlModelo; //CAPA DEL MODELO INYECTADA
	
	public ITransMySqlModelo getTransMySqlModelo() {
		return transMySqlModelo;
	}

	public void setTransMySqlModelo(ITransMySqlModelo transMySqlModelo) {
		this.transMySqlModelo = transMySqlModelo;
	}

	@Override
	public Usuario getUsuarioLogin(String email, String password) {

		Usuario usuarioModeloResp = null;
		try {
			usuarioModeloResp = getTransMySqlModelo().getUsuarioLogin(email, password);
		} catch (Exception e) {
			usuarioModeloResp.setId(0);
			usuarioModeloResp.setNombre("");
			usuarioModeloResp.setApellido("");
			usuarioModeloResp.setEmail("");
			usuarioModeloResp.setPassword("");
			logger.error(cMessageLog + e.getMessage());
		}
		
		return usuarioModeloResp;
	}

}
