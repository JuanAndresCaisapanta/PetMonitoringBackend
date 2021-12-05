package com.MonitoreoMascotas.Security.Jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.MonitoreoMascotas.Security.Service.UsuariosDetalleServiceImp;

// comprobar token y permite acceso

public class FiltroTokenJwt extends OncePerRequestFilter {
	private final static Logger logger = LoggerFactory.getLogger(FiltroTokenJwt.class);

	@Autowired
	ProveedorJwt proveedorJwt;

	@Autowired
	UsuariosDetalleServiceImp usuariosDetallesService;

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		try {
			String token = getToken(req);
			if (token != null && proveedorJwt.validateToken(token)) {
				String email = proveedorJwt.getEmailFromToken(token);
				UserDetails userDetails = usuariosDetallesService.loadUserByUsername(email);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null,
						userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (Exception e) {
			logger.error("fail en el método doFilter " + e.getMessage());
		}
		filterChain.doFilter(req, res);
	}

	private String getToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer"))
			return header.replace("Bearer ", "");
		return null;
	}
}
