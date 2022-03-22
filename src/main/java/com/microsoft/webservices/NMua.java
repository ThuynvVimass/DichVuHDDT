/**
 * NMua.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.webservices;

public class NMua  implements java.io.Serializable {
    private java.lang.String ten;

    private java.lang.String MST;

    private java.lang.String DChi;

    private com.microsoft.webservices.TTin[] TTKhac;

    public NMua() {
    }

    public NMua(
           java.lang.String ten,
           java.lang.String MST,
           java.lang.String DChi,
           com.microsoft.webservices.TTin[] TTKhac) {
           this.ten = ten;
           this.MST = MST;
           this.DChi = DChi;
           this.TTKhac = TTKhac;
    }


    /**
     * Gets the ten value for this NMua.
     * 
     * @return ten
     */
    public java.lang.String getTen() {
        return ten;
    }


    /**
     * Sets the ten value for this NMua.
     * 
     * @param ten
     */
    public void setTen(java.lang.String ten) {
        this.ten = ten;
    }


    /**
     * Gets the MST value for this NMua.
     * 
     * @return MST
     */
    public java.lang.String getMST() {
        return MST;
    }


    /**
     * Sets the MST value for this NMua.
     * 
     * @param MST
     */
    public void setMST(java.lang.String MST) {
        this.MST = MST;
    }


    /**
     * Gets the DChi value for this NMua.
     * 
     * @return DChi
     */
    public java.lang.String getDChi() {
        return DChi;
    }


    /**
     * Sets the DChi value for this NMua.
     * 
     * @param DChi
     */
    public void setDChi(java.lang.String DChi) {
        this.DChi = DChi;
    }


    /**
     * Gets the TTKhac value for this NMua.
     * 
     * @return TTKhac
     */
    public com.microsoft.webservices.TTin[] getTTKhac() {
        return TTKhac;
    }


    /**
     * Sets the TTKhac value for this NMua.
     * 
     * @param TTKhac
     */
    public void setTTKhac(com.microsoft.webservices.TTin[] TTKhac) {
        this.TTKhac = TTKhac;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NMua)) return false;
        NMua other = (NMua) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ten==null && other.getTen()==null) || 
             (this.ten!=null &&
              this.ten.equals(other.getTen()))) &&
            ((this.MST==null && other.getMST()==null) || 
             (this.MST!=null &&
              this.MST.equals(other.getMST()))) &&
            ((this.DChi==null && other.getDChi()==null) || 
             (this.DChi!=null &&
              this.DChi.equals(other.getDChi()))) &&
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
        if (getTen() != null) {
            _hashCode += getTen().hashCode();
        }
        if (getMST() != null) {
            _hashCode += getMST().hashCode();
        }
        if (getDChi() != null) {
            _hashCode += getDChi().hashCode();
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
        new org.apache.axis.description.TypeDesc(NMua.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "NMua"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ten");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "Ten"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MST");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "MST"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DChi");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "DChi"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
