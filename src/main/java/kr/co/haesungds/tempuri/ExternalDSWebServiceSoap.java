/**
 * ExternalDSWebServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kr.co.haesungds.tempuri;

public interface ExternalDSWebServiceSoap extends java.rmi.Remote {
    public String employeeSearchByNumber(String pEmpNo) throws java.rmi.RemoteException;
    public String employeeSearchByMail(String pMailAddress) throws java.rmi.RemoteException;
    public String employeeSearchByName(String pName) throws java.rmi.RemoteException;
}
