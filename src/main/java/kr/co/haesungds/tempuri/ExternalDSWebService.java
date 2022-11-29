/**
 * ExternalDSWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kr.co.haesungds.tempuri;

public interface ExternalDSWebService extends javax.xml.rpc.Service {
    public String getExternalDSWebServiceSoapAddress();

    public ExternalDSWebServiceSoap getExternalDSWebServiceSoap() throws javax.xml.rpc.ServiceException;

    public ExternalDSWebServiceSoap getExternalDSWebServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
