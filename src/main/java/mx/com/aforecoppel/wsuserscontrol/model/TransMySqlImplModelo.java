package mx.com.aforecoppel.wsuserscontrol.model;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mx.com.aforecoppel.wsuserscontrol.model.interfaz.ITransMySqlModelo;
import mx.com.aforecoppel.wsuserscontrol.objetos.Usuario;


@Repository
public class TransMySqlImplModelo implements ITransMySqlModelo {

	private static final Logger logger 	   = Logger.getLogger(TransMySqlImplModelo.class);
	java.lang.String cMessageLog           = "::WSUSERSCONTROL:: ";
	
	@Autowired
	JdbcTemplate JdbcTemplateMySQL;
	
	
	public JdbcTemplate getJdbcTemplateMySQL() {
		return JdbcTemplateMySQL;
	}

	public void setJdbcTemplateMySQL(JdbcTemplate jdbcTemplateMySQL) {
		JdbcTemplateMySQL = jdbcTemplateMySQL;
	}

	@Override
	public Usuario getUsuarioLogin(String email, String password) {
		String sql = "SELECT * FROM usuarios WHERE email=? AND password = ?;";		
		
		Usuario usuario = null;
		try {
			usuario = getJdbcTemplateMySQL().queryForObject(sql,new Object[]{email,password}, new BeanPropertyRowMapper<Usuario>(Usuario.class));
		} catch (DataAccessException e) {
			logger.error(cMessageLog + e.getMessage());
		}
		
		return usuario;
	}

}
