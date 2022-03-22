/**
 * LTSuat.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.webservices;

public class LTSuat  implements java.io.Serializable {
    private java.lang.String TSuat;

    private java.math.BigDecimal TThue;

    private java.math.BigDecimal thTien;

    public LTSuat() {
    }

    public LTSuat(
           java.lang.String TSuat,
           java.math.BigDecimal TThue,
           java.math.BigDecimal thTien) {
           this.TSuat = TSuat;
           this.TThue = TThue;
           this.thTien = thTien;
    }


    /**
     * Gets the TSuat value for this LTSuat.
     * 
     * @return TSuat
     */
    public java.lang.String getTSuat() {
        return TSuat;
    }


    /**
     * Sets the TSuat value for this LTSuat.
     * 
     * @param TSuat
     */
    public void setTSuat(java.lang.String TSuat) {
        this.TSuat = TSuat;
    }


    /**
     * Gets the TThue value for this LTSuat.
     * 
     * @return TThue
     */
    public java.math.BigDecimal getTThue() {
        return TThue;
    }


    /**
     * Sets the TThue value for this LTSuat.
     * 
     * @param TThue
     */
    public void setTThue(java.math.BigDecimal TThue) {
        this.TThue = TThue;
    }


    /**
     * Gets the thTien value for this LTSuat.
     * 
     * @return thTien
     */
    public java.math.BigDecimal getThTien() {
        return thTien;
    }


    /**
     * Sets the thTien value for this LTSuat.
     * 
     * @param thTien
     */
    public void setThTien(java.math.BigDecimal thTien) {
        this.thTien = thTien;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LTSuat)) return false;
        LTSuat other = (LTSuat) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.TSuat==null && other.getTSuat()==null) || 
             (this.TSuat!=null &&
              this.TSuat.equals(other.getTSuat()))) &&
            ((this.TThue==null && other.getTThue()==null) || 
             (this.TThue!=null &&
              this.TThue.equals(other.getTThue()))) &&
            ((this.thTien==null && other.getThTien()==null) || 
             (this.thTien!=null &&
              this.thTien.equals(other.getThTien())));
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
        if (getTSuat() != null) {
            _hashCode += getTSuat().hashCode();
        }
        if (getTThue() != null) {
            _hashCode += getTThue().hashCode();
        }
        if (getThTien() != null) {
            _hashCode += getThTien().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LTSuat.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "LTSuat"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TSuat");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "TSuat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TThue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "TThue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("thTien");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "ThTien"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
