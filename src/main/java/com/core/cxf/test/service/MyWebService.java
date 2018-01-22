package com.core.cxf.test.service;

import javax.jws.WebService;

/**
 * Created by GSN on 2017/12/29.
 */
@WebService
public interface MyWebService {

    int add(int a, int b);

    int minus(int a, int b);


}
