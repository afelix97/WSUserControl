package mx.com.aforecoppel.wsuserscontrol.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages="mx.com.aforecoppel.wsuserscontrol")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter{

	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	/*BEAN PARA CREAR DATASOURCE COMO SU NOMBRE LO INDICA ES PARA SABER LA CONEXION A PERSISTENCIA*/
	@Bean 
	public DataSource getDataSource(String DRIVERCLASSNAME,String SGBD,String IP, int PUERTO,String DB,String USUARIO,String PASSWORD) 
	{
		DriverManagerDataSource dataSource= new DriverManagerDataSource();
		dataSource.setDriverClassName(DRIVERCLASSNAME); //paquete donde se encuentra la clase del driver
		//dataSource.setUrl("jdbc:mysql://localhost:3306/ventas?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");//URL DE CONEXION JDBC ESTA LA OBTIENEN DESDE LA DOCUMENTACION DE CADA MANEJADOR DE BASE DE DATOS
		if (IP.equals("localhost")) 
		{
			dataSource.setUrl("jdbc:" + SGBD + "://"+ IP +":"+ PUERTO +"/" + DB + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");//URL DE CONEXION JDBC ESTA LA OBTIENEN DESDE LA DOCUMENTACION DE CADA MANEJADOR DE BASE DE DATOS
		} 
		else 
		{
			dataSource.setUrl("jdbc:" + SGBD + "://"+ IP +":"+ PUERTO +"/" + DB);//URL DE CONEXION JDBC ESTA LA OBTIENEN DESDE LA DOCUMENTACION DE CADA MANEJADOR DE BASE DE DATOS
		}
		dataSource.setUsername(USUARIO); //USUARIO DE LA BASE DE DATOS
		dataSource.setPassword(PASSWORD);//CONTRASENA DE LA BASE DE DATOS
		
		return dataSource;
	}
	/*BEAN PARA CREAR JDBCTEMPLATE Y SIMPLIFICAR EL USO DEL DATASOURCE Y TENER ACCESO A DATOS*/
	@Bean
	public JdbcTemplate JdbcTemplatePostgreSQL() 
	{
		java.lang.String driverClassName = null;
		java.lang.String ip              = null;
		int puerto             = 0;
		java.lang.String db              = null;
		java.lang.String sgbd            = null;
		java.lang.String user            = null;
		java.lang.String pass            = null;
		
		try 
		{
			driverClassName = "org.postgresql.Driver";
			ip              = "10.44.172.234";
			puerto          = 5432;
			db              = "aforeglobal";
			sgbd            = "postgresql";
			user            = "sysaforeglobal";
			pass            = "a2901ba525795f1fd311642f98c9f160";
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new JdbcTemplate(getDataSource(driverClassName,sgbd,ip,puerto,db,user,pass));
	}
	/*BEAN PARA CREAR JDBCTEMPLATE Y SIMPLIFICAR EL USO DEL DATASOURCE Y TENER ACCESO A DATOS*/
	@Bean
	public JdbcTemplate JdbcTemplateMySQL() 
	{
		java.lang.String driverClassName = null;
		java.lang.String ip              = null;
		int puerto             = 0;
		java.lang.String db              = null;
		java.lang.String sgbd            = null;
		java.lang.String user            = null;
		java.lang.String pass            = null;
		
		try 
		{
			driverClassName = "com.mysql.cj.jdbc.Driver";
			ip              = "localhost";
			puerto          = 3306;
			db              = "ventas";
			sgbd            = "mysql";
			user            = "root";
			pass            = "";
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new JdbcTemplate(getDataSource(driverClassName,sgbd,ip,puerto,db,user,pass));
	}
	
}
