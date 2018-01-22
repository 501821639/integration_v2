package com.core.cxf.test.service;

import javax.xml.ws.Endpoint;

/**
 * Created by GSN on 2017/12/29.
 */
public class MainServiceTest {

    public static void main(String[] args) {

        String address = "http://localhost:8888/ms";
        Endpoint.publish(address, new MyWebServiceImpl());


    }

}
