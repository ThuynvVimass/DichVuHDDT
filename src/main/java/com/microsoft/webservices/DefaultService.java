/**
 * DefaultService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.webservices;

public interface DefaultService extends javax.xml.rpc.Service {
    public java.lang.String getDefaultServiceSoapAddress();

    public com.microsoft.webservices.DefaultServiceSoap getDefaultServiceSoap() throws javax.xml.rpc.ServiceException;

    public com.microsoft.webservices.DefaultServiceSoap getDefaultServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
