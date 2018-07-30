package com.jfshare.finagle;

import com.jfshare.finagle.thrift.result.StringResult;
import com.jfshare.finagle.thrift.score.ScoreResult;
import com.jfshare.mvp.es.test.TestProductRepository;
import com.jfshare.mvp.server.finagle.server.ScoreClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by chiwenheng on 2018/7/26.
 */
public class test extends TestProductRepository {


    @Autowired
    ScoreClient scoreClient;

    @Test
    public void testFinagle(){


        ScoreResult scoreByUserId = scoreClient.getScore(535668);

        System.out.println(scoreByUserId);


    }
    
    @Test
    public void testIncomeScore() {
    	StringResult incomeScore = scoreClient.incomeScore(535668, 1, 6, "123456");
    	System.out.println(incomeScore);
    }

    @Test
    public void testReduceScore() {
    	StringResult reduceScore = scoreClient.reduceScore(535668, 1, 6, "123456");
    	System.out.println(reduceScore);
    }

}
