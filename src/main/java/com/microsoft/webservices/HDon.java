/**
 * HDon.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.webservices;

public class HDon  implements java.io.Serializable {
    private com.microsoft.webservices.DLHDon DLHDon;

    private com.microsoft.webservices.TTCQT TTCQT;

    private com.microsoft.webservices.DSCKS DSCKS;

    public HDon() {
    }

    public HDon(
           com.microsoft.webservices.DLHDon DLHDon,
           com.microsoft.webservices.TTCQT TTCQT,
           com.microsoft.webservices.DSCKS DSCKS) {
           this.DLHDon = DLHDon;
           this.TTCQT = TTCQT;
           this.DSCKS = DSCKS;
    }


    /**
     * Gets the DLHDon value for this HDon.
     * 
     * @return DLHDon
     */
    public com.microsoft.webservices.DLHDon getDLHDon() {
        return DLHDon;
    }


    /**
     * Sets the DLHDon value for this HDon.
     * 
     * @param DLHDon
     */
    public void setDLHDon(com.microsoft.webservices.DLHDon DLHDon) {
        this.DLHDon = DLHDon;
    }


    /**
     * Gets the TTCQT value for this HDon.
     * 
     * @return TTCQT
     */
    public com.microsoft.webservices.TTCQT getTTCQT() {
        return TTCQT;
    }


    /**
     * Sets the TTCQT value for this HDon.
     * 
     * @param TTCQT
     */
    public void setTTCQT(com.microsoft.webservices.TTCQT TTCQT) {
        this.TTCQT = TTCQT;
    }


    /**
     * Gets the DSCKS value for this HDon.
     * 
     * @return DSCKS
     */
    public com.microsoft.webservices.DSCKS getDSCKS() {
        return DSCKS;
    }


    /**
     * Sets the DSCKS value for this HDon.
     * 
     * @param DSCKS
     */
    public void setDSCKS(com.microsoft.webservices.DSCKS DSCKS) {
        this.DSCKS = DSCKS;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof HDon)) return false;
        HDon other = (HDon) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.DLHDon==null && other.getDLHDon()==null) || 
             (this.DLHDon!=null &&
              this.DLHDon.equals(other.getDLHDon()))) &&
            ((this.TTCQT==null && other.getTTCQT()==null) || 
             (this.TTCQT!=null &&
              this.TTCQT.equals(other.getTTCQT()))) &&
            ((this.DSCKS==null && other.getDSCKS()==null) || 
             (this.DSCKS!=null &&
              this.DSCKS.equals(other.getDSCKS())));
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
        if (getDLHDon() != null) {
            _hashCode += getDLHDon().hashCode();
        }
        if (getTTCQT() != null) {
            _hashCode += getTTCQT().hashCode();
        }
        if (getDSCKS() != null) {
            _hashCode += getDSCKS().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(HDon.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "HDon"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DLHDon");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "DLHDon"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "DLHDon"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TTCQT");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "TTCQT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "TTCQT"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DSCKS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "DSCKS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "DSCKS"));
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
