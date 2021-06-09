package com.citi.cpih.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.citi.cpih.model.User;

/**
 * @author jorge.ruiz citi.com.mx
 */
public class Request  implements UserDetails {

	private static final long serialVersionUID = 2091577321894635036L;
	
	private User user;
	private String sessionId;
	private String fullName;
	
	public Request(User user, String sessionId) {
        this.user = user;
        this.sessionId = sessionId;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getFullName() {
		fullName = user.getName() + " " + user.getLastName();
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String role = user.getRole().intValue() == 1 ? "ROLE_ADMIN" : "ROLE_USER";
		return AuthorityUtils.commaSeparatedStringToAuthorityList(role);
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}