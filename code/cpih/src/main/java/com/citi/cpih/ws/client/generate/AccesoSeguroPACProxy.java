package com.citi.cpih.ws.client.generate;

public class AccesoSeguroPACProxy implements com.citi.cpih.ws.client.generate.AccesoSeguroPAC_PortType {
  private String _endpoint = null;
  private com.citi.cpih.ws.client.generate.AccesoSeguroPAC_PortType accesoSeguroPAC_PortType = null;
  
  public AccesoSeguroPACProxy() {
    _initAccesoSeguroPACProxy();
  }
  
  public AccesoSeguroPACProxy(String endpoint) {
    _endpoint = endpoint;
    _initAccesoSeguroPACProxy();
  }
  
  private void _initAccesoSeguroPACProxy() {
    try {
      accesoSeguroPAC_PortType = (new com.citi.cpih.ws.client.generate.AccesoSeguroPAC_ServiceLocator()).getAccesoSeguroPACPort();
      if (accesoSeguroPAC_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)accesoSeguroPAC_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)accesoSeguroPAC_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {
    	
    }
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (accesoSeguroPAC_PortType != null)
      ((javax.xml.rpc.Stub)accesoSeguroPAC_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.citi.cpih.ws.client.generate.AccesoSeguroPAC_PortType getAccesoSeguroPAC_PortType() {
    if (accesoSeguroPAC_PortType == null)
      _initAccesoSeguroPACProxy();
    return accesoSeguroPAC_PortType;
  }
  
  public java.lang.String validaToken(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException, com.citi.cpih.ws.client.generate.Exception{
    if (accesoSeguroPAC_PortType == null)
      _initAccesoSeguroPACProxy();
    return accesoSeguroPAC_PortType.validaToken(arg0, arg1);
  }
  
  
}