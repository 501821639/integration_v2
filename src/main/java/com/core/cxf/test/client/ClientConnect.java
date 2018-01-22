package com.core.cxf.test.client;

import com.core.cxf.test.service.MyWebService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by GSN on 2017/12/29.
 */
public class ClientConnect {

    //wsdl2java -encoding utf-8 -d F:\cxf http://localhost:8888/ms?wsdl

    //wsdl2java -encoding utf-8 -d F:\cxf http://221.8.56.50:69/api/ReportService.asmx?wsdl

    //wsimport http://221.8.56.50:69/api/ReportService.asmx?wsdl  -keep -p com.llg.ws2 -s F:/cxf

    private static final String CONNECT_URL = "http://localhost:8888/ms?wsdl";
    private MyWebService myWebService;

    public ClientConnect() {

        try {
            URL url = new URL(CONNECT_URL);

            //命名空间 及 名称
            QName qName = new QName("http://service.test.cxf.core.com/", "MyWebServiceImplService");
            Service service = Service.create(url, qName);
            myWebService = service.getPort(MyWebService.class);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }

    public void connect() {

        System.out.println(myWebService.add(2, 3));
        System.out.println(myWebService.minus(2, 3));

    }

}
