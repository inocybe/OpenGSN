/**
 * Copyright 2009-2011 École de technologie supérieure,
 * Communication Research Centre Canada,
 * Inocybe Technologies Inc. and 6837247 CANADA Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * 
 */

package com.greenstarnetwork.services.cloudmanager;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.4.0
 * 2011-12-19T09:20:04.010-05:00
 * Generated source version: 2.4.0
 * 
 */


@WebServiceClient(name = "CloudManagerServiceService", 
                  wsdlLocation = "file:/home/inocult/Projects/gsn-devel/services-client/src/main/resources/wsdl/CloudManagerService.wsdl",
                  targetNamespace = "http://cloudmanager.services.greenstarnetwork.com/") 
public class CloudManagerServiceService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://cloudmanager.services.greenstarnetwork.com/", "CloudManagerServiceService");
    public final static QName CloudManagerServicePort = new QName("http://cloudmanager.services.greenstarnetwork.com/", "CloudManagerServicePort");
    static {
        URL url = null;
        try {
            url = new URL("file:/home/inocult/Projects/gsn-devel/services-client/src/main/resources/wsdl/CloudManagerService.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(CloudManagerServiceService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/home/inocult/Projects/gsn-devel/services-client/src/main/resources/wsdl/CloudManagerService.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public CloudManagerServiceService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public CloudManagerServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CloudManagerServiceService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     * 
     * @return
     *     returns ICloudManagerService
     */
    @WebEndpoint(name = "CloudManagerServicePort")
    public ICloudManagerService getCloudManagerServicePort() {
        return super.getPort(CloudManagerServicePort, ICloudManagerService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ICloudManagerService
     */
    @WebEndpoint(name = "CloudManagerServicePort")
    public ICloudManagerService getCloudManagerServicePort(WebServiceFeature... features) {
        return super.getPort(CloudManagerServicePort, ICloudManagerService.class, features);
    }

}
