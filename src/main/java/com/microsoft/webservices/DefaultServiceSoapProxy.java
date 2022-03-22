package com.microsoft.webservices;

public class DefaultServiceSoapProxy implements com.microsoft.webservices.DefaultServiceSoap {
  private String _endpoint = null;
  private com.microsoft.webservices.DefaultServiceSoap defaultServiceSoap = null;
  
  public DefaultServiceSoapProxy() {
    _initDefaultServiceSoapProxy();
  }
  
  public DefaultServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initDefaultServiceSoapProxy();
  }
  
  private void _initDefaultServiceSoapProxy() {
    try {
      defaultServiceSoap = (new com.microsoft.webservices.DefaultServiceLocator()).getDefaultServiceSoap();
      if (defaultServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)defaultServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)defaultServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (defaultServiceSoap != null)
      ((javax.xml.rpc.Stub)defaultServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.microsoft.webservices.DefaultServiceSoap getDefaultServiceSoap() {
    if (defaultServiceSoap == null)
      _initDefaultServiceSoapProxy();
    return defaultServiceSoap;
  }
  
  public java.lang.String apiPHanhHDon(java.lang.String b_nsd, java.lang.String b_mk, com.microsoft.webservices.HDon[] b_LHDon) throws java.rmi.RemoteException{
    if (defaultServiceSoap == null)
      _initDefaultServiceSoapProxy();
    return defaultServiceSoap.apiPHanhHDon(b_nsd, b_mk, b_LHDon);
  }
  
  public java.lang.String apiHuyHDon(java.lang.String b_nsd, java.lang.String b_mk, java.lang.String b_KeyHDon) throws java.rmi.RemoteException{
    if (defaultServiceSoap == null)
      _initDefaultServiceSoapProxy();
    return defaultServiceSoap.apiHuyHDon(b_nsd, b_mk, b_KeyHDon);
  }
  
  public java.lang.String apiTTHDon(java.lang.String b_nsd, java.lang.String b_mk, java.lang.String b_KeyHDon) throws java.rmi.RemoteException{
    if (defaultServiceSoap == null)
      _initDefaultServiceSoapProxy();
    return defaultServiceSoap.apiTTHDon(b_nsd, b_mk, b_KeyHDon);
  }
  
  public java.lang.String apiKyHDon(java.lang.String b_nsd, java.lang.String b_mk, java.lang.String b_KeyHDon) throws java.rmi.RemoteException{
    if (defaultServiceSoap == null)
      _initDefaultServiceSoapProxy();
    return defaultServiceSoap.apiKyHDon(b_nsd, b_mk, b_KeyHDon);
  }
  
  public java.lang.String apiInHoadon(java.lang.String b_nsd, java.lang.String b_mk, java.lang.String b_KeyHDon) throws java.rmi.RemoteException{
    if (defaultServiceSoap == null)
      _initDefaultServiceSoapProxy();
    return defaultServiceSoap.apiInHoadon(b_nsd, b_mk, b_KeyHDon);
  }
  
  public java.lang.String apiLayCTietHDon(java.lang.String b_nsd, java.lang.String b_mk, java.lang.String b_KeyHDon) throws java.rmi.RemoteException{
    if (defaultServiceSoap == null)
      _initDefaultServiceSoapProxy();
    return defaultServiceSoap.apiLayCTietHDon(b_nsd, b_mk, b_KeyHDon);
  }
  
  public java.lang.String apiLayTTinMSKHHDon(java.lang.String b_nsd, java.lang.String b_mk, java.lang.String b_loai, double b_ngayph) throws java.rmi.RemoteException{
    if (defaultServiceSoap == null)
      _initDefaultServiceSoapProxy();
    return defaultServiceSoap.apiLayTTinMSKHHDon(b_nsd, b_mk, b_loai, b_ngayph);
  }
  
  public java.lang.String apiLayTTinMst(java.lang.String b_nsd, java.lang.String b_mk, java.lang.String b_mst) throws java.rmi.RemoteException{
    if (defaultServiceSoap == null)
      _initDefaultServiceSoapProxy();
    return defaultServiceSoap.apiLayTTinMst(b_nsd, b_mk, b_mst);
  }
  
  
}