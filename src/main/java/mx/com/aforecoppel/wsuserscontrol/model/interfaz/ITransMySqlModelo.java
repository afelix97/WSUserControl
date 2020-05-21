package mx.com.aforecoppel.wsuserscontrol.model.interfaz;

import mx.com.aforecoppel.wsuserscontrol.objetos.Usuario;

public interface ITransMySqlModelo {
	public Usuario getUsuarioLogin(String email, String password);
}
