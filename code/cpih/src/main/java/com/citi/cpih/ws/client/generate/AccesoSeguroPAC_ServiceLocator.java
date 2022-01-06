/**
 * AccesoSeguroPAC_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.citi.cpih.ws.client.generate;

public class AccesoSeguroPAC_ServiceLocator extends org.apache.axis.client.Service implements com.citi.cpih.ws.client.generate.AccesoSeguroPAC_Service {

    public AccesoSeguroPAC_ServiceLocator() {
    }


    public AccesoSeguroPAC_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AccesoSeguroPAC_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AccesoSeguroPACPort
    private java.lang.String AccesoSeguroPACPort_address = "http://localhost:9080/PACServiciosWeb/AccesoSeguroPAC";

    public java.lang.String getAccesoSeguroPACPortAddress() {
        return AccesoSeguroPACPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AccesoSeguroPACPortWSDDServiceName = "AccesoSeguroPACPort";

    public java.lang.String getAccesoSeguroPACPortWSDDServiceName() {
        return AccesoSeguroPACPortWSDDServiceName;
    }

    public void setAccesoSeguroPACPortWSDDServiceName(java.lang.String name) {
        AccesoSeguroPACPortWSDDServiceName = name;
    }

    public com.citi.cpih.ws.client.generate.AccesoSeguroPAC_PortType getAccesoSeguroPACPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AccesoSeguroPACPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAccesoSeguroPACPort(endpoint);
    }

    public com.citi.cpih.ws.client.generate.AccesoSeguroPAC_PortType getAccesoSeguroPACPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.citi.cpih.ws.client.generate.AccesoSeguroPACPortBindingStub _stub = new com.citi.cpih.ws.client.generate.AccesoSeguroPACPortBindingStub(portAddress, this);
            _stub.setPortName(getAccesoSeguroPACPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAccesoSeguroPACPortEndpointAddress(java.lang.String address) {
        AccesoSeguroPACPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.citi.cpih.ws.client.generate.AccesoSeguroPAC_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.citi.cpih.ws.client.generate.AccesoSeguroPACPortBindingStub stub = new com.citi.cpih.ws.client.generate.AccesoSeguroPACPortBindingStub(new java.net.URL(AccesoSeguroPACPort_address), this);
                stub.setPortName(getAccesoSeguroPACPortWSDDServiceName());
                return stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("AccesoSeguroPACPort".equals(inputPortName)) {
            return getAccesoSeguroPACPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicios.wspac.crm.sds.telcel.com.mx/", "AccesoSeguroPAC");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicios.wspac.crm.sds.telcel.com.mx/", "AccesoSeguroPACPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AccesoSeguroPACPort".equals(portName)) {
            setAccesoSeguroPACPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
