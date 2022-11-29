/**
 * HD_ApprovalWebServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kr.co.haesungds.tempuri;

public interface HD_ApprovalWebServiceSoap extends java.rmi.Remote {

    /**
     * 해성DS 결재상신(Xml 문자열)
     */
    public String approvalSubmitXmlString(String xmlstring) throws java.rmi.RemoteException;

    /**
     * 해성DS 결재상신(Xml)
     */
    public String approvalSubmit(ApprovalSubmitXmldom xmldom) throws java.rmi.RemoteException;

    /**
     * 해성DS 결재취소(Xml 문자열)
     */
    public String approvalCancelXmlString(String xmlstring) throws java.rmi.RemoteException;

    /**
     * 해성DS 결재취소(Xml)
     */
    public String approvalCancel(ApprovalCancelXmldom xmldom) throws java.rmi.RemoteException;

    /**
     * 해성DS 결재조회 By MisID(Xml 문자열)
     */
    public String approvalStatusByMisIDXmlString(String xmlstring) throws java.rmi.RemoteException;

    /**
     * 해성DS 결재조회 By MisID(Xml)
     */
    public String approvalStatusByMisID(ApprovalStatusByMisIDXmldom xmldom) throws java.rmi.RemoteException;
}
