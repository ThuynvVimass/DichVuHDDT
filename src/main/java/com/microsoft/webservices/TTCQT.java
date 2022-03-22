/**
 * TTCQT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.webservices;

public class TTCQT  implements java.io.Serializable {
    private java.lang.String MCCQT;

    private java.lang.String MSTNBan;

    private java.lang.String KHMSHDon;

    private java.lang.String KHHDon;

    private java.lang.String SHDon;

    private java.lang.String TDLap;

    private com.microsoft.webservices.Signature TCT;

    public TTCQT() {
    }

    public TTCQT(
           java.lang.String MCCQT,
           java.lang.String MSTNBan,
           java.lang.String KHMSHDon,
           java.lang.String KHHDon,
           java.lang.String SHDon,
           java.lang.String TDLap,
           com.microsoft.webservices.Signature TCT) {
           this.MCCQT = MCCQT;
           this.MSTNBan = MSTNBan;
           this.KHMSHDon = KHMSHDon;
           this.KHHDon = KHHDon;
           this.SHDon = SHDon;
           this.TDLap = TDLap;
           this.TCT = TCT;
    }


    /**
     * Gets the MCCQT value for this TTCQT.
     * 
     * @return MCCQT
     */
    public java.lang.String getMCCQT() {
        return MCCQT;
    }


    /**
     * Sets the MCCQT value for this TTCQT.
     * 
     * @param MCCQT
     */
    public void setMCCQT(java.lang.String MCCQT) {
        this.MCCQT = MCCQT;
    }


    /**
     * Gets the MSTNBan value for this TTCQT.
     * 
     * @return MSTNBan
     */
    public java.lang.String getMSTNBan() {
        return MSTNBan;
    }


    /**
     * Sets the MSTNBan value for this TTCQT.
     * 
     * @param MSTNBan
     */
    public void setMSTNBan(java.lang.String MSTNBan) {
        this.MSTNBan = MSTNBan;
    }


    /**
     * Gets the KHMSHDon value for this TTCQT.
     * 
     * @return KHMSHDon
     */
    public java.lang.String getKHMSHDon() {
        return KHMSHDon;
    }


    /**
     * Sets the KHMSHDon value for this TTCQT.
     * 
     * @param KHMSHDon
     */
    public void setKHMSHDon(java.lang.String KHMSHDon) {
        this.KHMSHDon = KHMSHDon;
    }


    /**
     * Gets the KHHDon value for this TTCQT.
     * 
     * @return KHHDon
     */
    public java.lang.String getKHHDon() {
        return KHHDon;
    }


    /**
     * Sets the KHHDon value for this TTCQT.
     * 
     * @param KHHDon
     */
    public void setKHHDon(java.lang.String KHHDon) {
        this.KHHDon = KHHDon;
    }


    /**
     * Gets the SHDon value for this TTCQT.
     * 
     * @return SHDon
     */
    public java.lang.String getSHDon() {
        return SHDon;
    }


    /**
     * Sets the SHDon value for this TTCQT.
     * 
     * @param SHDon
     */
    public void setSHDon(java.lang.String SHDon) {
        this.SHDon = SHDon;
    }


    /**
     * Gets the TDLap value for this TTCQT.
     * 
     * @return TDLap
     */
    public java.lang.String getTDLap() {
        return TDLap;
    }


    /**
     * Sets the TDLap value for this TTCQT.
     * 
     * @param TDLap
     */
    public void setTDLap(java.lang.String TDLap) {
        this.TDLap = TDLap;
    }


    /**
     * Gets the TCT value for this TTCQT.
     * 
     * @return TCT
     */
    public com.microsoft.webservices.Signature getTCT() {
        return TCT;
    }


    /**
     * Sets the TCT value for this TTCQT.
     * 
     * @param TCT
     */
    public void setTCT(com.microsoft.webservices.Signature TCT) {
        this.TCT = TCT;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TTCQT)) return false;
        TTCQT other = (TTCQT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.MCCQT==null && other.getMCCQT()==null) || 
             (this.MCCQT!=null &&
              this.MCCQT.equals(other.getMCCQT()))) &&
            ((this.MSTNBan==null && other.getMSTNBan()==null) || 
             (this.MSTNBan!=null &&
              this.MSTNBan.equals(other.getMSTNBan()))) &&
            ((this.KHMSHDon==null && other.getKHMSHDon()==null) || 
             (this.KHMSHDon!=null &&
              this.KHMSHDon.equals(other.getKHMSHDon()))) &&
            ((this.KHHDon==null && other.getKHHDon()==null) || 
             (this.KHHDon!=null &&
              this.KHHDon.equals(other.getKHHDon()))) &&
            ((this.SHDon==null && other.getSHDon()==null) || 
             (this.SHDon!=null &&
              this.SHDon.equals(other.getSHDon()))) &&
            ((this.TDLap==null && other.getTDLap()==null) || 
             (this.TDLap!=null &&
              this.TDLap.equals(other.getTDLap()))) &&
            ((this.TCT==null && other.getTCT()==null) || 
             (this.TCT!=null &&
              this.TCT.equals(other.getTCT())));
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
        if (getMCCQT() != null) {
            _hashCode += getMCCQT().hashCode();
        }
        if (getMSTNBan() != null) {
            _hashCode += getMSTNBan().hashCode();
        }
        if (getKHMSHDon() != null) {
            _hashCode += getKHMSHDon().hashCode();
        }
        if (getKHHDon() != null) {
            _hashCode += getKHHDon().hashCode();
        }
        if (getSHDon() != null) {
            _hashCode += getSHDon().hashCode();
        }
        if (getTDLap() != null) {
            _hashCode += getTDLap().hashCode();
        }
        if (getTCT() != null) {
            _hashCode += getTCT().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TTCQT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "TTCQT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MCCQT");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "MCCQT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MSTNBan");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "MSTNBan"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("KHMSHDon");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "KHMSHDon"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("KHHDon");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "KHHDon"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SHDon");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "SHDon"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TDLap");
        elemField.setXmlName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "TDLap"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
