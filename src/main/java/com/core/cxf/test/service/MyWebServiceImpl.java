package com.core.cxf.test.service;


import javax.jws.WebService;

/**
 * Created by GSN on 2017/12/29.
 */
@WebService(endpointInterface = "com.core.cxf.test.service.MyWebService")
public class MyWebServiceImpl implements MyWebService {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int minus(int a, int b) {
        return a - b;
    }
}
