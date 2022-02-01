package com.playermaker.config;


import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class IpFiltering implements Filter{

	@Value("#{'${valuesArray}'.split(',')}")
	private List<String> valuesArray;	

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;

		log.info("************  Filter via IpFilterWithAccessLog execute **************");

		String remoteIp4 = request.getRemoteAddr();
		String currSrvPath = request.getServletPath();
		String method = request.getMethod();
		String protocol = request.getProtocol();
		String remotePort = ""+request.getRemotePort();

		
		log.info("************   Remote server curren access details are: "
				+ "[ remote ip-"+remoteIp4+" method-"+method +" protocol-"+protocol
				+" request path-"+currSrvPath+ "  remotePort-"+remotePort+ " ]     *****");
		if(valuesArray.isEmpty()) {
			response.sendError(401, "The array of IPs to allow is empty");
		}else if(!(valuesArray.contains(remoteIp4))) {
			log.info("************   The remote Ip: "+remoteIp4+" is NOT ALLOW   **************");
			response.sendError(401, "Host is not allow to access");
		}else {
			log.info("************  Filter DONE the IP: "+remoteIp4+" is ALLOW -> continue with the process**************");
			filterChain.doFilter(req, res);
			// ///// ///    every thing is OK to continue with process 	/// ////	//	////
		}

	}


	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {

	}
}
