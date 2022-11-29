/**
 * ExternalDSWebServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kr.co.haesungds.tempuri;

public class ExternalDSWebServiceLocator extends org.apache.axis.client.Service implements ExternalDSWebService {

    public ExternalDSWebServiceLocator() {
    }


    public ExternalDSWebServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ExternalDSWebServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ExternalDSWebServiceSoap
    private String ExternalDSWebServiceSoap_address = "https://hds.haesunggroup.com/Website/Base/ExternalDSWebservice.asmx";

    public String getExternalDSWebServiceSoapAddress() {
        return ExternalDSWebServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private String ExternalDSWebServiceSoapWSDDServiceName = "ExternalDSWebServiceSoap";

    public String getExternalDSWebServiceSoapWSDDServiceName() {
        return ExternalDSWebServiceSoapWSDDServiceName;
    }

    public void setExternalDSWebServiceSoapWSDDServiceName(String name) {
        ExternalDSWebServiceSoapWSDDServiceName = name;
    }

    public ExternalDSWebServiceSoap getExternalDSWebServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ExternalDSWebServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getExternalDSWebServiceSoap(endpoint);
    }

    public ExternalDSWebServiceSoap getExternalDSWebServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ExternalDSWebServiceSoapStub _stub = new ExternalDSWebServiceSoapStub(portAddress, this);
            _stub.setPortName(getExternalDSWebServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setExternalDSWebServiceSoapEndpointAddress(String address) {
        ExternalDSWebServiceSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (ExternalDSWebServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                ExternalDSWebServiceSoapStub _stub = new ExternalDSWebServiceSoapStub(new java.net.URL(ExternalDSWebServiceSoap_address), this);
                _stub.setPortName(getExternalDSWebServiceSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("ExternalDSWebServiceSoap".equals(inputPortName)) {
            return getExternalDSWebServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "ExternalDSWebService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "ExternalDSWebServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("ExternalDSWebServiceSoap".equals(portName)) {
            setExternalDSWebServiceSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
