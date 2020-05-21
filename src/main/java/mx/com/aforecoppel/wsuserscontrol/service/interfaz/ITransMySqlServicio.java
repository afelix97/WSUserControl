package mx.com.aforecoppel.wsuserscontrol.service.interfaz;

import java.util.Properties;

import mx.com.aforecoppel.wsuserscontrol.objetos.Usuario;

public interface ITransMySqlServicio {
	public Usuario getUsuarioLogin(String email, String password);
}
