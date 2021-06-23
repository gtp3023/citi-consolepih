package com.citi.cpih.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.citi.cpih.util.Constants;
import com.citi.cpih.ws.client.generate.AccesoSeguroPAC_PortType;
import com.citi.cpih.ws.client.generate.AccesoSeguroPAC_Service;
import com.citi.cpih.ws.client.generate.AccesoSeguroPAC_ServiceLocator;

/**
 * @author jorge.ruiz citi.com.mx
 */
@Controller
public class LoginController {
	
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Value("${ws.pac.url}")
	private String url;
	
	@Value("${ws.pac.password}")
	private String pacPass;	
	
	@RequestMapping(value={ "/getAccesoLogin" }, method = {RequestMethod.GET, RequestMethod.POST})
	public String login(HttpServletRequest request, Model model, String error, String logout, @RequestParam(required = false) String accesoSeguro) {        
        if (logout != null) {
        	logger.error("Saliendo del Portal...");
            return "redirect:/login";
        }
        
        return "welcome";
    }
	
    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }
	
    @RequestMapping(value = { "/login" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String getAccesoLogin(HttpServletResponse response, HttpServletRequest request, Model model, String error,
			String logout, @RequestParam(required = false) String accesoSeguro) {

		HttpSession sesion = request.getSession();
		
		
		logger.info("Deteccion de entrada Por PAC: {}",accesoSeguro);
		  
		String token = null;

		token = request.getParameter(Constants.ACCESSO_SEGURO);

		if (token != null) {
			logger.info("Accediendo al ENDPOINT WS: {}", this.url);
			logger.info("Accediendo al sistema por TOKEN PAC token: {}", token);
			logger.info("Password:{}", this.pacPass);
			
			try {
				AccesoSeguroPAC_Service service;
				service = new AccesoSeguroPAC_ServiceLocator();
				String userName = "";
				AccesoSeguroPAC_PortType type;
				String respuestac = null;

				type = service.getAccesoSeguroPACPort(new URL(this.url));
				respuestac = type.validaToken(token, this.pacPass);
				
				respuestac = Constants.SUCCES_WS_PAC;
				
				
				logger.info("Response WS PAC: {}", respuestac);

				if (getResponseCode(respuestac) == Constants.CORRECT_PAC) {
					if (respuestac != null && respuestac.indexOf("<Nombre>") != -1) {
						logger.info("Extrayendo Nombre de usuario PAC");
						userName = usergetUserNamePac(respuestac);
						sesion.setAttribute("userPac", userName);
					}

					logger.info("Redireccionando a HOME, usuario PAC Authenticado");
					return "redirect:/getAccesoLogin?accesoSeguro=true";
				} else {
					
					
					
					logger.info("Redireccionando a LOGIN FORM ! ");
					logger.info("Redireccionando al Formulario, se espera Usuario y ContraseÃ±a");
					return Constants.VIEW_LOGIN_PAGE;
				}
			} catch (Exception e) {
				logger.error("Error RemoteException: {}", e.getMessage());
			}

		}
		
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals(Constants.ANONYMOUS)) {
			SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
			logoutHandler.logout(request, null, null);
		}
		
        if (error != null) {
        	logger.error("Credenciales incorrectas...");
            model.addAttribute("error", Constants.INCORRECT_CREDENTIALS);
        }
		
		return Constants.VIEW_LOGIN_PAGE;
	} 
	
	private int getResponseCode(String response) {
		int resultado = -1;

		try {
			if (response != null && response.indexOf("<ProblemaId>") != -1) {	
				String responseIdProblema = response.substring(response.lastIndexOf("<ProblemaId>"));
				String responseIdProblema2 = responseIdProblema.substring(12,
						responseIdProblema.lastIndexOf("</ProblemaId>"));
				resultado = Integer.parseInt(responseIdProblema2.trim());
			}
		}catch(Exception ex) {
			logger.error("No se logro extraer el RESPONSE CODE :: {}", ex.getMessage());
		}
		
		logger.info("ProblemaId: {}", resultado);
		
		return resultado;
	}
	
	private String usergetUserNamePac(String response) {
    	StringBuilder userName = new StringBuilder();
    		
    	try {
			int nombreInit = response.lastIndexOf("<Nombre>");
			int nombreEnd  = response.indexOf("</Nombre>");
			int amatInit = response.lastIndexOf("<Apmat>");
			int amatEnd  = response.indexOf("</Apmat>");
			
			int apatInit = response.lastIndexOf("<Appat>");
			int apatEnd  = response.indexOf("</Appat>");
			
			String nombre = response.substring((nombreInit+8),nombreEnd);
			String amat = response.substring((amatInit+7),amatEnd);
			String apat = response.substring(apatInit+7, apatEnd);

			userName.append(nombre);
			userName.append(" "+apat);
			userName.append(" "+amat);
			
			
    	}catch (Exception e) {
    		userName.append("UnNamed");
    		logger.info("Error al concatenar el nombre de usuario: {}",e.getMessage());
		}
    	
    	return userName.toString();
	}
	
}