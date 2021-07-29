package com.citi.cpih.ws;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public interface HttpClientPoolManager {

	PoolingHttpClientConnectionManager getPoolingManager();

	void evictConnections();

}
