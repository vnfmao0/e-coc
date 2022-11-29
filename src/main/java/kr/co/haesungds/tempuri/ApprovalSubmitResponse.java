/**
 * ApprovalSubmitResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kr.co.haesungds.tempuri;

public class ApprovalSubmitResponse  implements java.io.Serializable {
    private String approvalSubmitResult;

    public ApprovalSubmitResponse() {
    }

    public ApprovalSubmitResponse(
           String approvalSubmitResult) {
           this.approvalSubmitResult = approvalSubmitResult;
    }


    /**
     * Gets the approvalSubmitResult value for this ApprovalSubmitResponse.
     * 
     * @return approvalSubmitResult
     */
    public String getApprovalSubmitResult() {
        return approvalSubmitResult;
    }


    /**
     * Sets the approvalSubmitResult value for this ApprovalSubmitResponse.
     * 
     * @param approvalSubmitResult
     */
    public void setApprovalSubmitResult(String approvalSubmitResult) {
        this.approvalSubmitResult = approvalSubmitResult;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ApprovalSubmitResponse)) return false;
        ApprovalSubmitResponse other = (ApprovalSubmitResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.approvalSubmitResult==null && other.getApprovalSubmitResult()==null) || 
             (this.approvalSubmitResult!=null &&
              this.approvalSubmitResult.equals(other.getApprovalSubmitResult())));
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
        if (getApprovalSubmitResult() != null) {
            _hashCode += getApprovalSubmitResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ApprovalSubmitResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">ApprovalSubmitResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("approvalSubmitResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ApprovalSubmitResult"));
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
