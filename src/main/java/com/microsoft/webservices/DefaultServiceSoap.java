/**
 * DefaultServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.webservices;

public interface DefaultServiceSoap extends java.rmi.Remote {
    public java.lang.String apiPHanhHDon(java.lang.String b_nsd, java.lang.String b_mk, com.microsoft.webservices.HDon[] b_LHDon) throws java.rmi.RemoteException;
    public java.lang.String apiHuyHDon(java.lang.String b_nsd, java.lang.String b_mk, java.lang.String b_KeyHDon) throws java.rmi.RemoteException;
    public java.lang.String apiTTHDon(java.lang.String b_nsd, java.lang.String b_mk, java.lang.String b_KeyHDon) throws java.rmi.RemoteException;
    public java.lang.String apiKyHDon(java.lang.String b_nsd, java.lang.String b_mk, java.lang.String b_KeyHDon) throws java.rmi.RemoteException;
    public java.lang.String apiInHoadon(java.lang.String b_nsd, java.lang.String b_mk, java.lang.String b_KeyHDon) throws java.rmi.RemoteException;
    public java.lang.String apiLayCTietHDon(java.lang.String b_nsd, java.lang.String b_mk, java.lang.String b_KeyHDon) throws java.rmi.RemoteException;
    public java.lang.String apiLayTTinMSKHHDon(java.lang.String b_nsd, java.lang.String b_mk, java.lang.String b_loai, double b_ngayph) throws java.rmi.RemoteException;
    public java.lang.String apiLayTTinMst(java.lang.String b_nsd, java.lang.String b_mk, java.lang.String b_mst) throws java.rmi.RemoteException;
}
