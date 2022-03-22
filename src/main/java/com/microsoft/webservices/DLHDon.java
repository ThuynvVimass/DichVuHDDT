/**
 * DLHDon.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.webservices;

public class DLHDon  implements java.io.Serializable {
    private java.lang.String key;

    private java.lang.String keyOld;

    private java.math.BigDecimal id;

    private com.microsoft.webservices.TTChung TTChung;

    private com.microsoft.webservices.NDHDon NDHDon;

    private com.microsoft.webservices.TTin[] TTKhac;

    public DLHDon() {
    }

    public DLHDon(
           java.lang.String key,
           java.lang.String keyOld,
           java.math.BigDecimal id,
           com.microsoft.webservices.TTChung TTChung,
           com.microsoft.webservices.NDHDon NDHDon,
           com.microsoft.webservices.TTin[] TTKhac) {
           this.key = key;
           this.keyOld = keyOld;
           this.id = id;
           this.TTChung = TTChung;
           this.NDHDon = NDHDon;
           this.TTKhac = TTKhac;
    }


    /**
     * Gets the key value for this DLHDon.
     * 
     * @return key
     */
    public java.lang.String getKey() {
        return key;
    }


    /**
     * Sets the key value for this DLHDon.
     * 
     * @param key
     */
    public void setKey(java.lang.String key) {
        this.key = key;
    }


    /**
     * Gets the keyOld value for this DLHDon.
     * 
     * @return keyOld
     */
    public java.lang.String getKeyOld() {
        return keyOld;
    }


    /**
     * Sets the keyOld value for this DLHDon.
     * 
     * @param keyOld
     */
    public void setKeyOld(java.lang.String keyOld) {
        this.keyOld = keyOld;
    }


    /**
     * Gets the id value for this DLHDon.
     * 
     * @return id
     */
    public java.math.BigDecimal getId() {
        return id;
    }


    /**
     * Sets the id value for this DLHDon.
     * 
     * @param id
     */
    public void setId(java.math.BigDecimal id) {
        this.id = id;
    }


    /**
     * Gets the TTChung value for this DLHDon.
     * 
     * @return TTChung
     */
    public com.microsoft.webservices.TTChung getTTChung() {
        return TTChung;
    }


    /**
     * Sets the TTChung value for this DLHDon.
     * 
     * @param TTChung
     */
    public void setTTChung(com.microsoft.webservices.TTChung TTChung) {
        this.TTChung = TTChung;
    }


    /**
     * Gets the NDHDon value for this DLHDon.
     * 
     * @return NDHDon
     */
    public com.microsoft.webservices.NDHDon getNDHDon() {
        return NDHDon;
    }


    /**
     * Sets the NDHDon value for this DLHDon.
     * 
     * @param NDHDon
     */
    public void setNDHDon(com.microsoft.webservices.NDHDon NDHDon) {
        this.NDHDon = NDHDon;
    }


    /**
     * Gets the TTKhac value for this DLHDon.
     * 
     * @return TTKhac
     */
    public com.microsoft.webservices.TTin[] getTTKhac() {
        return TTKhac;
    }


    /**
     * Sets the TTKhac value for this DLHDon.
     * 
     * @param TTKhac
     */
    public void setTTKhac(com.microsoft.webservices.TTin[] TTKhac) {
        this.TTKhac = TTKhac;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DLHDon)) return false;
        DLHDon other = (DLHDon) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.key==null && other.getKey()==null) || 
             (this.key!=null &&
              this.key.equals(other.getKey()))) &&
            ((this.keyOld==null && other.getKeyOld()==null) || 
             (this.keyOld!=null &&
              this.keyOld.equals(other.getKeyOld()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.TTChung==null && other.getTTChung()==null) || 
             (this.TTChung!=null &&
              this.TTChung.equals(other.getTTChung()))) &&
            ((this.NDHDon==null && other.getNDHDon()==null) || 
             (this.NDHDon!=null &&
              this.NDHDon.equals(other.getNDHDon()))) &&
            ((this.TTKhac==null && other.getTTKhac()==null) || 
             (this.TTKhac!=null &&
              java.util.Arrays.equals(this.TTKhac, other.getTTKhac())));
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
        if (getKey() != null) {
            _hashCode += getKey().hashCode();
        }
        if (getKeyOld() != null) {
            _hashCode += getKeyOld().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getTTChung() != null) {
            _hashCode += getTTChung().hashCode();
        }
        if (getNDHDon() != null) {
            _hashCode += getNDHDon().hashCode();
        }
        if (getTTKhac() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTTKhac());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTTKhac(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DLHDon.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "DLHDon"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "Key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("keyOld");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "KeyOld"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TTChung");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "TTChung"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "TTChung"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NDHDon");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "NDHDon"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "NDHDon"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TTKhac");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "TTKhac"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "TTin"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "TTin"));
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
