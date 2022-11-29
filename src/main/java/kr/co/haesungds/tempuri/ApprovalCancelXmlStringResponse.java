/**
 * ApprovalCancelXmlStringResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kr.co.haesungds.tempuri;

public class ApprovalCancelXmlStringResponse  implements java.io.Serializable {
    private String approvalCancelXmlStringResult;

    public ApprovalCancelXmlStringResponse() {
    }

    public ApprovalCancelXmlStringResponse(
           String approvalCancelXmlStringResult) {
           this.approvalCancelXmlStringResult = approvalCancelXmlStringResult;
    }


    /**
     * Gets the approvalCancelXmlStringResult value for this ApprovalCancelXmlStringResponse.
     * 
     * @return approvalCancelXmlStringResult
     */
    public String getApprovalCancelXmlStringResult() {
        return approvalCancelXmlStringResult;
    }


    /**
     * Sets the approvalCancelXmlStringResult value for this ApprovalCancelXmlStringResponse.
     * 
     * @param approvalCancelXmlStringResult
     */
    public void setApprovalCancelXmlStringResult(String approvalCancelXmlStringResult) {
        this.approvalCancelXmlStringResult = approvalCancelXmlStringResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ApprovalCancelXmlStringResponse)) return false;
        ApprovalCancelXmlStringResponse other = (ApprovalCancelXmlStringResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.approvalCancelXmlStringResult==null && other.getApprovalCancelXmlStringResult()==null) || 
             (this.approvalCancelXmlStringResult!=null &&
              this.approvalCancelXmlStringResult.equals(other.getApprovalCancelXmlStringResult())));
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
        if (getApprovalCancelXmlStringResult() != null) {
            _hashCode += getApprovalCancelXmlStringResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ApprovalCancelXmlStringResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ApprovalCancelXmlStringResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("approvalCancelXmlStringResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ApprovalCancelXmlStringResult"));
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
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
