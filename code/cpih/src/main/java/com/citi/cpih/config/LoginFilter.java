
package com.citi.cpih.config;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.citi.cpih.util.Constants;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	private  final Logger log = LogManager.getLogger(this.getClass().getName());
    
	public LoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager); 
    }

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {
		User user = new User();
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		try {

			if (req.getParameter(Constants.ACCESSO_SEGURO) != null) {
				log.info("Ingresando por PAC Acceso Seguro ");

				user.setUsername(Constants.USERNAME_PAC);
				user.setPassword(Constants.PASS_PAC);

			} else {

				log.info("Ingresando por Formulario LOGIN");

				user.setUsername(req.getParameter("username"));
				user.setPassword(req.getParameter("password"));
			}

			String role = "ROLE_USER";

			grantedAuthorities.add(new SimpleGrantedAuthority(role));

		} catch (AuthenticationException e) {
			log.error("Error en la autenticacion del susario : {}", e.getMessage());
		}

		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), grantedAuthorities));

	}

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {
    	
        SecurityContextHolder.getContext().setAuthentication(auth);
        chain.doFilter(req, res);
    }
    
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
			throws IOException, ServletException {
    	response.getWriter().write("ACCESO RESTRINGIDO");
    	
    	log.error("Credenciales incorrectas...");
    	response.sendRedirect(request.getContextPath()+"/login");
    }
}

class User {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}