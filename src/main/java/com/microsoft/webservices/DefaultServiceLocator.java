/**
 * DefaultServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.webservices;

public class DefaultServiceLocator extends org.apache.axis.client.Service implements com.microsoft.webservices.DefaultService {

    public DefaultServiceLocator() {
    }


    public DefaultServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DefaultServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for DefaultServiceSoap
    private java.lang.String DefaultServiceSoap_address = "http://hoadon.cinvoice.vn/service/iv_v/DefaultService.asmx";

    public java.lang.String getDefaultServiceSoapAddress() {
        return DefaultServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String DefaultServiceSoapWSDDServiceName = "DefaultServiceSoap";

    public java.lang.String getDefaultServiceSoapWSDDServiceName() {
        return DefaultServiceSoapWSDDServiceName;
    }

    public void setDefaultServiceSoapWSDDServiceName(java.lang.String name) {
        DefaultServiceSoapWSDDServiceName = name;
    }

    public com.microsoft.webservices.DefaultServiceSoap getDefaultServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(DefaultServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getDefaultServiceSoap(endpoint);
    }

    public com.microsoft.webservices.DefaultServiceSoap getDefaultServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.microsoft.webservices.DefaultServiceSoapStub _stub = new com.microsoft.webservices.DefaultServiceSoapStub(portAddress, this);
            _stub.setPortName(getDefaultServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setDefaultServiceSoapEndpointAddress(java.lang.String address) {
        DefaultServiceSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.microsoft.webservices.DefaultServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.microsoft.webservices.DefaultServiceSoapStub _stub = new com.microsoft.webservices.DefaultServiceSoapStub(new java.net.URL(DefaultServiceSoap_address), this);
                _stub.setPortName(getDefaultServiceSoapWSDDServiceName());
                return _stub;
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
        if ("DefaultServiceSoap".equals(inputPortName)) {
            return getDefaultServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://microsoft.com/webservices/", "DefaultService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "DefaultServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("DefaultServiceSoap".equals(portName)) {
            setDefaultServiceSoapEndpointAddress(address);
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
