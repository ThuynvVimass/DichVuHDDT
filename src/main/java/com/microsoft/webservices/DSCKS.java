/**
 * DSCKS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.webservices;

public class DSCKS  implements java.io.Serializable {
    private com.microsoft.webservices.Signature NBan;

    private com.microsoft.webservices.Signature NMua;

    private com.microsoft.webservices.Signature TCT;

    private com.microsoft.webservices.Signature CCKSKhac;

    public DSCKS() {
    }

    public DSCKS(
           com.microsoft.webservices.Signature NBan,
           com.microsoft.webservices.Signature NMua,
           com.microsoft.webservices.Signature TCT,
           com.microsoft.webservices.Signature CCKSKhac) {
           this.NBan = NBan;
           this.NMua = NMua;
           this.TCT = TCT;
           this.CCKSKhac = CCKSKhac;
    }


    /**
     * Gets the NBan value for this DSCKS.
     * 
     * @return NBan
     */
    public com.microsoft.webservices.Signature getNBan() {
        return NBan;
    }


    /**
     * Sets the NBan value for this DSCKS.
     * 
     * @param NBan
     */
    public void setNBan(com.microsoft.webservices.Signature NBan) {
        this.NBan = NBan;
    }


    /**
     * Gets the NMua value for this DSCKS.
     * 
     * @return NMua
     */
    public com.microsoft.webservices.Signature getNMua() {
        return NMua;
    }


    /**
     * Sets the NMua value for this DSCKS.
     * 
     * @param NMua
     */
    public void setNMua(com.microsoft.webservices.Signature NMua) {
        this.NMua = NMua;
    }


    /**
     * Gets the TCT value for this DSCKS.
     * 
     * @return TCT
     */
    public com.microsoft.webservices.Signature getTCT() {
        return TCT;
    }


    /**
     * Sets the TCT value for this DSCKS.
     * 
     * @param TCT
     */
    public void setTCT(com.microsoft.webservices.Signature TCT) {
        this.TCT = TCT;
    }


    /**
     * Gets the CCKSKhac value for this DSCKS.
     * 
     * @return CCKSKhac
     */
    public com.microsoft.webservices.Signature getCCKSKhac() {
        return CCKSKhac;
    }


    /**
     * Sets the CCKSKhac value for this DSCKS.
     * 
     * @param CCKSKhac
     */
    public void setCCKSKhac(com.microsoft.webservices.Signature CCKSKhac) {
        this.CCKSKhac = CCKSKhac;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DSCKS)) return false;
        DSCKS other = (DSCKS) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.NBan==null && other.getNBan()==null) || 
             (this.NBan!=null &&
              this.NBan.equals(other.getNBan()))) &&
            ((this.NMua==null && other.getNMua()==null) || 
             (this.NMua!=null &&
              this.NMua.equals(other.getNMua()))) &&
            ((this.TCT==null && other.getTCT()==null) || 
             (this.TCT!=null &&
              this.TCT.equals(other.getTCT()))) &&
            ((this.CCKSKhac==null && other.getCCKSKhac()==null) || 
             (this.CCKSKhac!=null &&
              this.CCKSKhac.equals(other.getCCKSKhac())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getNBan() != null) {
            _hashCode += getNBan().hashCode();
        }
        if (getNMua() != null) {
            _hashCode += getNMua().hashCode();
        }
        if (getTCT() != null) {
            _hashCode += getTCT().hashCode();
        }
        if (getCCKSKhac() != null) {
            _hashCode += getCCKSKhac().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DSCKS.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "DSCKS"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NBan");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "NBan"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "Signature"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NMua");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "NMua"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "Signature"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TCT");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "TCT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "Signature"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CCKSKhac");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "CCKSKhac"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "Signature"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
