/**
 * HD_ApprovalWebServiceSoapStub.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kr.co.haesungds.tempuri;

public class HD_ApprovalWebServiceSoapStub extends org.apache.axis.client.Stub implements HD_ApprovalWebServiceSoap {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc[] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[6];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1() {
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ApprovalSubmitXmlString");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "xmlstring"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ApprovalSubmitXmlStringResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ApprovalSubmit");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "xmldom"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tempuri.org/", ">>ApprovalSubmit>xmldom"), ApprovalSubmitXmldom.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ApprovalSubmitResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ApprovalCancelXmlString");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "xmlstring"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ApprovalCancelXmlStringResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ApprovalCancel");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "xmldom"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tempuri.org/", ">>ApprovalCancel>xmldom"), ApprovalCancelXmldom.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ApprovalCancelResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ApprovalStatusByMisIDXmlString");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "xmlstring"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ApprovalStatusByMisIDXmlStringResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ApprovalStatusByMisID");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "xmldom"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://tempuri.org/", ">>ApprovalStatusByMisID>xmldom"), ApprovalStatusByMisIDXmldom.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "ApprovalStatusByMisIDResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

    }

    public HD_ApprovalWebServiceSoapStub() throws org.apache.axis.AxisFault {
        this(null);
    }

    public HD_ApprovalWebServiceSoapStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        this(service);
        super.cachedEndpoint = endpointURL;
    }

    public HD_ApprovalWebServiceSoapStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service) super.service).setTypeMappingVersion("1.2");
        Class cls;
        javax.xml.namespace.QName qName;
        javax.xml.namespace.QName qName2;
        Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
        Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
        Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
        Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
        Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
        Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
        Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
        Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
        Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
        Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
        qName = new javax.xml.namespace.QName("http://tempuri.org/", ">>ApprovalCancel>xmldom");
        cachedSerQNames.add(qName);
        cls = ApprovalCancelXmldom.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://tempuri.org/", ">>ApprovalStatusByMisID>xmldom");
        cachedSerQNames.add(qName);
        cls = ApprovalStatusByMisIDXmldom.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://tempuri.org/", ">>ApprovalSubmit>xmldom");
        cachedSerQNames.add(qName);
        cls = ApprovalSubmitXmldom.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ApprovalCancel");
        cachedSerQNames.add(qName);
        cls = ApprovalCancel.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ApprovalCancelResponse");
        cachedSerQNames.add(qName);
        cls = ApprovalCancelResponse.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ApprovalCancelXmlString");
        cachedSerQNames.add(qName);
        cls = ApprovalCancelXmlString.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ApprovalCancelXmlStringResponse");
        cachedSerQNames.add(qName);
        cls = ApprovalCancelXmlStringResponse.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ApprovalStatusByMisID");
        cachedSerQNames.add(qName);
        cls = ApprovalStatusByMisID.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ApprovalStatusByMisIDResponse");
        cachedSerQNames.add(qName);
        cls = ApprovalStatusByMisIDResponse.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ApprovalStatusByMisIDXmlString");
        cachedSerQNames.add(qName);
        cls = ApprovalStatusByMisIDXmlString.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ApprovalStatusByMisIDXmlStringResponse");
        cachedSerQNames.add(qName);
        cls = ApprovalStatusByMisIDXmlStringResponse.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://tempuri.org/", ">ApprovalSubmitResponse");
        cachedSerQNames.add(qName);
        cls = ApprovalSubmitResponse.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                String key = (String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        Class cls = (Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            Class sf = (Class)
                                    cachedSerFactories.get(i);
                            Class df = (Class)
                                    cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        } else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                    cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                    cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        } catch (Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public String approvalSubmitXmlString(String xmlstring) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/ApprovalSubmitXmlString");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ApprovalSubmitXmlString"));

        setRequestHeaders(_call);
        setAttachments(_call);
        try {
            Object _resp = _call.invoke(new Object[]{xmlstring});

            if (_resp instanceof java.rmi.RemoteException) {
                throw (java.rmi.RemoteException) _resp;
            } else {
                extractAttachments(_call);
                try {
                    return (String) _resp;
                } catch (Exception _exception) {
                    return (String) org.apache.axis.utils.JavaUtils.convert(_resp, String.class);
                }
            }
        } catch (org.apache.axis.AxisFault axisFaultException) {
            throw axisFaultException;
        }
    }

    public String approvalSubmit(ApprovalSubmitXmldom xmldom) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/ApprovalSubmit");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ApprovalSubmit"));

        setRequestHeaders(_call);
        setAttachments(_call);
        try {
            Object _resp = _call.invoke(new Object[]{xmldom});

            if (_resp instanceof java.rmi.RemoteException) {
                throw (java.rmi.RemoteException) _resp;
            } else {
                extractAttachments(_call);
                try {
                    return (String) _resp;
                } catch (Exception _exception) {
                    return (String) org.apache.axis.utils.JavaUtils.convert(_resp, String.class);
                }
            }
        } catch (org.apache.axis.AxisFault axisFaultException) {
            throw axisFaultException;
        }
    }

    public String approvalCancelXmlString(String xmlstring) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/ApprovalCancelXmlString");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ApprovalCancelXmlString"));

        setRequestHeaders(_call);
        setAttachments(_call);
        try {
            Object _resp = _call.invoke(new Object[]{xmlstring});

            if (_resp instanceof java.rmi.RemoteException) {
                throw (java.rmi.RemoteException) _resp;
            } else {
                extractAttachments(_call);
                try {
                    return (String) _resp;
                } catch (Exception _exception) {
                    return (String) org.apache.axis.utils.JavaUtils.convert(_resp, String.class);
                }
            }
        } catch (org.apache.axis.AxisFault axisFaultException) {
            throw axisFaultException;
        }
    }

    public String approvalCancel(ApprovalCancelXmldom xmldom) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/ApprovalCancel");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ApprovalCancel"));

        setRequestHeaders(_call);
        setAttachments(_call);
        try {
            Object _resp = _call.invoke(new Object[]{xmldom});

            if (_resp instanceof java.rmi.RemoteException) {
                throw (java.rmi.RemoteException) _resp;
            } else {
                extractAttachments(_call);
                try {
                    return (String) _resp;
                } catch (Exception _exception) {
                    return (String) org.apache.axis.utils.JavaUtils.convert(_resp, String.class);
                }
            }
        } catch (org.apache.axis.AxisFault axisFaultException) {
            throw axisFaultException;
        }
    }

    public String approvalStatusByMisIDXmlString(String xmlstring) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/ApprovalStatusByMisIDXmlString");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ApprovalStatusByMisIDXmlString"));

        setRequestHeaders(_call);
        setAttachments(_call);
        try {
            Object _resp = _call.invoke(new Object[]{xmlstring});

            if (_resp instanceof java.rmi.RemoteException) {
                throw (java.rmi.RemoteException) _resp;
            } else {
                extractAttachments(_call);
                try {
                    return (String) _resp;
                } catch (Exception _exception) {
                    return (String) org.apache.axis.utils.JavaUtils.convert(_resp, String.class);
                }
            }
        } catch (org.apache.axis.AxisFault axisFaultException) {
            throw axisFaultException;
        }
    }

    public String approvalStatusByMisID(ApprovalStatusByMisIDXmldom xmldom) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/ApprovalStatusByMisID");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ApprovalStatusByMisID"));

        setRequestHeaders(_call);
        setAttachments(_call);
        try {
            Object _resp = _call.invoke(new Object[]{xmldom});

            if (_resp instanceof java.rmi.RemoteException) {
                throw (java.rmi.RemoteException) _resp;
            } else {
                extractAttachments(_call);
                try {
                    return (String) _resp;
                } catch (Exception _exception) {
                    return (String) org.apache.axis.utils.JavaUtils.convert(_resp, String.class);
                }
            }
        } catch (org.apache.axis.AxisFault axisFaultException) {
            throw axisFaultException;
        }
    }

}
