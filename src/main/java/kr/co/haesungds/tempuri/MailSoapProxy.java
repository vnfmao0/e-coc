/**
 * MailSoapProxy.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kr.co.haesungds.tempuri;

public interface MailSoapProxy extends javax.xml.rpc.Service {
    public String getMailSoapProxySoapAddress();

    public MailSoapProxySoap getMailSoapProxySoap() throws javax.xml.rpc.ServiceException;

    public MailSoapProxySoap getMailSoapProxySoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
