package com.jfshare.finagle;

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


        ScoreResult scoreByUserId = scoreClient.getScore(129);

        System.out.println(scoreByUserId);


    }


}
