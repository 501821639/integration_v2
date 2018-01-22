package com.core.test.service.impl;

import com.core.test.bean.TestOneBean;
import com.core.test.bean.TestTwoBean;
import com.core.test.dao.TestOneDao;
import com.core.test.dao.TestTwoDao;
import com.core.test.service.TestService;
import com.utils.RandomGenerate;
import com.utils.date.FormatType;
import com.utils.json.FormatJson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by GSN on 2017/12/4.
 * 测试业务实现
 *
 * @author 郭少男
 */

@Service
public class TestServiceImpl implements TestService {

    @Resource
    private TestOneDao testOneDao;

    @Resource
    private TestTwoDao testTwoDao;

    @Override
    public String requiredCreateTest(int beginValue, int endValue) {

        RandomGenerate randomGenerate = new RandomGenerate();

        Date d = new Date();


        for (int i = beginValue; i < endValue; i++) {

            String name = "name_" + i;
            String pwd = "password_" + i;


            TestOneBean testOneBean = new TestOneBean();
            testOneBean.setUserName(name);
            testOneBean.setPwd(pwd);
            testOneBean.setNumOne(randomGenerate.randomNumber());
            testOneBean.setNumTwo(randomGenerate.randomNumber());
            testOneBean.setNumThree(randomGenerate.randomNumber());
            testOneBean.setNumFour(randomGenerate.randomNumber());
            testOneBean.setNumFive(randomGenerate.randomNumber());
            testOneBean.setTime(d);
            testOneDao.addTestOne(testOneBean);

           /* TestTwoBean testTwoBean = new TestTwoBean();
            testTwoBean.setUserName(name);
            testTwoBean.setPwd(pwd);
            testTwoBean.setNumOne(randomGenerate.randomNumber());
            testTwoBean.setNumTwo(randomGenerate.randomNumber());
            testTwoBean.setNumThree(randomGenerate.randomNumber());
            testTwoBean.setNumFour(randomGenerate.randomNumber());
            testTwoBean.setNumFive(randomGenerate.randomNumber());
            testTwoBean.setTime(d);
            testTwoDao.addTestTwo(testTwoBean);*/

        }

        return "添加成功";
    }

    @Override
    public String queryTest(String hql) {

        long l1 = System.currentTimeMillis();
        List<?> list = testOneDao.finTestOne(hql);
        System.out.println(System.currentTimeMillis() - l1);

        return new FormatJson(new FormatType().getYmdhms()).listConversion(list);
    }

}
