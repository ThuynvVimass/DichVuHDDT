/**
 * NDHDon.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.webservices;

public class NDHDon  implements java.io.Serializable {
    private com.microsoft.webservices.NBan NBan;

    private com.microsoft.webservices.NMua NMua;

    private com.microsoft.webservices.HHDVu[] DSHHDVu;

    private com.microsoft.webservices.TToan TToan;

    public NDHDon() {
    }

    public NDHDon(
           com.microsoft.webservices.NBan NBan,
           com.microsoft.webservices.NMua NMua,
           com.microsoft.webservices.HHDVu[] DSHHDVu,
           com.microsoft.webservices.TToan TToan) {
           this.NBan = NBan;
           this.NMua = NMua;
           this.DSHHDVu = DSHHDVu;
           this.TToan = TToan;
    }


    /**
     * Gets the NBan value for this NDHDon.
     * 
     * @return NBan
     */
    public com.microsoft.webservices.NBan getNBan() {
        return NBan;
    }


    /**
     * Sets the NBan value for this NDHDon.
     * 
     * @param NBan
     */
    public void setNBan(com.microsoft.webservices.NBan NBan) {
        this.NBan = NBan;
    }


    /**
     * Gets the NMua value for this NDHDon.
     * 
     * @return NMua
     */
    public com.microsoft.webservices.NMua getNMua() {
        return NMua;
    }


    /**
     * Sets the NMua value for this NDHDon.
     * 
     * @param NMua
     */
    public void setNMua(com.microsoft.webservices.NMua NMua) {
        this.NMua = NMua;
    }


    /**
     * Gets the DSHHDVu value for this NDHDon.
     * 
     * @return DSHHDVu
     */
    public com.microsoft.webservices.HHDVu[] getDSHHDVu() {
        return DSHHDVu;
    }


    /**
     * Sets the DSHHDVu value for this NDHDon.
     * 
     * @param DSHHDVu
     */
    public void setDSHHDVu(com.microsoft.webservices.HHDVu[] DSHHDVu) {
        this.DSHHDVu = DSHHDVu;
    }


    /**
     * Gets the TToan value for this NDHDon.
     * 
     * @return TToan
     */
    public com.microsoft.webservices.TToan getTToan() {
        return TToan;
    }


    /**
     * Sets the TToan value for this NDHDon.
     * 
     * @param TToan
     */
    public void setTToan(com.microsoft.webservices.TToan TToan) {
        this.TToan = TToan;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NDHDon)) return false;
        NDHDon other = (NDHDon) obj;
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
            ((this.DSHHDVu==null && other.getDSHHDVu()==null) || 
             (this.DSHHDVu!=null &&
              java.util.Arrays.equals(this.DSHHDVu, other.getDSHHDVu()))) &&
            ((this.TToan==null && other.getTToan()==null) || 
             (this.TToan!=null &&
              this.TToan.equals(other.getTToan())));
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
        if (getDSHHDVu() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDSHHDVu());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDSHHDVu(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTToan() != null) {
            _hashCode += getTToan().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NDHDon.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "NDHDon"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NBan");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "NBan"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "NBan"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NMua");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "NMua"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "NMua"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DSHHDVu");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "DSHHDVu"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "HHDVu"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "HHDVu"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TToan");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "TToan"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "TToan"));
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
