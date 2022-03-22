/**
 * TTin.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.webservices;

public class TTin  implements java.io.Serializable {
    private java.lang.String TTruong;

    private java.lang.String KDLieu;

    private java.lang.String DLieu;

    public TTin() {
    }

    public TTin(
           java.lang.String TTruong,
           java.lang.String KDLieu,
           java.lang.String DLieu) {
           this.TTruong = TTruong;
           this.KDLieu = KDLieu;
           this.DLieu = DLieu;
    }


    /**
     * Gets the TTruong value for this TTin.
     * 
     * @return TTruong
     */
    public java.lang.String getTTruong() {
        return TTruong;
    }


    /**
     * Sets the TTruong value for this TTin.
     * 
     * @param TTruong
     */
    public void setTTruong(java.lang.String TTruong) {
        this.TTruong = TTruong;
    }


    /**
     * Gets the KDLieu value for this TTin.
     * 
     * @return KDLieu
     */
    public java.lang.String getKDLieu() {
        return KDLieu;
    }


    /**
     * Sets the KDLieu value for this TTin.
     * 
     * @param KDLieu
     */
    public void setKDLieu(java.lang.String KDLieu) {
        this.KDLieu = KDLieu;
    }


    /**
     * Gets the DLieu value for this TTin.
     * 
     * @return DLieu
     */
    public java.lang.String getDLieu() {
        return DLieu;
    }


    /**
     * Sets the DLieu value for this TTin.
     * 
     * @param DLieu
     */
    public void setDLieu(java.lang.String DLieu) {
        this.DLieu = DLieu;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TTin)) return false;
        TTin other = (TTin) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.TTruong==null && other.getTTruong()==null) || 
             (this.TTruong!=null &&
              this.TTruong.equals(other.getTTruong()))) &&
            ((this.KDLieu==null && other.getKDLieu()==null) || 
             (this.KDLieu!=null &&
              this.KDLieu.equals(other.getKDLieu()))) &&
            ((this.DLieu==null && other.getDLieu()==null) || 
             (this.DLieu!=null &&
              this.DLieu.equals(other.getDLieu())));
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
        if (getTTruong() != null) {
            _hashCode += getTTruong().hashCode();
        }
        if (getKDLieu() != null) {
            _hashCode += getKDLieu().hashCode();
        }
        if (getDLieu() != null) {
            _hashCode += getDLieu().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TTin.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "TTin"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TTruong");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "TTruong"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("KDLieu");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "KDLieu"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DLieu");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "DLieu"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
