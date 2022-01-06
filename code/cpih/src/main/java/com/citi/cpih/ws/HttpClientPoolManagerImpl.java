package com.citi.cpih.ws;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class HttpClientPoolManagerImpl implements HttpClientPoolManager {
	private Log logger = LogFactory.getLog(this.getClass());
	private int maxLimitConnections;
	private int maxLimitHostConnections;
	private int idleTimeout;
	PoolingHttpClientConnectionManager cm;
	
	private void createPoolManager(){
		TrustManager[] trustAllCerts = getTrustManager();
		SSLContext sslcontext = null;
		try {
			sslcontext = SSLContext.getInstance("SSL");
			sslcontext.init(null, trustAllCerts, new java.security.SecureRandom());
		} catch (NoSuchAlgorithmException | KeyManagementException e) {
			logger.info("Error to create Pool Manager :: " + e);
		}
		
		cm = new PoolingHttpClientConnectionManager(RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", new SSLConnectionSocketFactory(sslcontext, new NoopHostnameVerifier()))
                .build());
		
		if( maxLimitConnections > 0 ){
			cm.setMaxTotal(maxLimitConnections);
			logger.info("Max Limit Connection :: " + maxLimitConnections);
		}
		
		if( maxLimitHostConnections > 0 ){
			cm.setDefaultMaxPerRoute(maxLimitHostConnections);
			logger.info("Max Limit Host Connection :: " + maxLimitHostConnections);
		}
		cm.closeIdleConnections(idleTimeout, TimeUnit.MILLISECONDS);
	}
	
	@Override
	public PoolingHttpClientConnectionManager getPoolingManager() {
		if(cm == null)
			createPoolManager();

		return cm;
	}
	
	@Override
	public void evictConnections(){
		if(cm != null){
			cm.closeIdleConnections(idleTimeout, TimeUnit.MILLISECONDS);
			cm.closeExpiredConnections();
		}
	}

	public void setMaxLimitConnections(int maxLimitConnections) {
		this.maxLimitConnections = maxLimitConnections;
	}

	public void setMaxLimitHostConnections(int maxLimitHostConnections) {
		this.maxLimitHostConnections = maxLimitHostConnections;
	}
	
	public void setIdleTimeout(int idleTimeout) {
		this.idleTimeout = idleTimeout;
	}

	private TrustManager[] getTrustManager() {
		 
		return new TrustManager[] {
 
			new X509TrustManager() {
	 
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				
				public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
					// cert
				}
				
				public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
					// cert
				}
				
			}
		
		};
 
	}
	
}
