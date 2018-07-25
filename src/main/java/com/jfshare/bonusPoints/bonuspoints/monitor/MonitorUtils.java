package com.jfshare.bonusPoints.bonuspoints.monitor;

import com.google.common.collect.Maps;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.Histogram;

import java.util.List;
import java.util.Map;

public class MonitorUtils {



    private static Map<String, Counter> allCounters = Maps.newConcurrentMap();
    private static Map<String, Histogram> allTimers = Maps.newConcurrentMap();
    private static final String DEFAULT_NAMESPACE = "svc";
    private static final String DEFAULT_TIMER_GROUP = "t";
    private static final String RESULT_LABEL = "result";
    private static final String RESULT_VALUE_SUCCESS = "succ";
    private static final String RESULT_VALUE_FAILURE = "fail";
    private static final String RESULT_VALUE_EXCEPTION = "ex";

    //定义直方图的bins范围
    private static final double[] DEFAULT_BUCKETS = new double[]{.1, .2, .5, 1, 2, 5, 7.5, 10};

    private static CollectorRegistry getCollectorRegistry() {
        return ContextHolder.getContext().getBean(CollectorRegistry.class);
    }

    private static Counter getCounter(String metricName) {
        return getCounter(metricName, metricName);
    }

    private static Counter getCounter(IMetricName metricName) {
        return getCounter(metricName.getName(), metricName.getDesc());
    }

    private static Counter getCounter(String metricName, String desc) {
        Counter counter = allCounters.get(metricName);
        if (counter == null) {
            counter = Counter.build(metricName, desc).namespace(DEFAULT_NAMESPACE).labelNames(RESULT_LABEL).create();
            Counter cnt = allCounters.putIfAbsent(metricName, counter);
            if (cnt == null) {
                counter.register(getCollectorRegistry());
            } else {
                counter = cnt;
            }
        }
        return counter;
    }

    /**
     * 用于预先创建Counter/Histogram缓存起来
     * @param metricNames 监控点名称
     */
    public static void init(List<IMetricName> metricNames) {
        for (IMetricName item : metricNames) {
            getCounter(item.getName(), item.getDesc());
            getHistogram(item.getName(), item.getDesc());
        }
    }

    public static void countStatus(String metricName, boolean status) {
        if (status) {
            countSuccess(metricName);
        } else {
            countFailed(metricName);
        }
    }

    /**
     * 成功计数
     * @param metricName
     */
    public static void countSuccess(String metricName) {
        getCounter(metricName).labels(RESULT_VALUE_SUCCESS).inc();
    }

    public static void countSuccess(IMetricName metricName) {
        getCounter(metricName).labels(RESULT_VALUE_SUCCESS).inc();
    }


    /**
     * 失败计数
     * @param metricName
     */
    public static void countFailed(String metricName) {
        getCounter(metricName).labels(RESULT_VALUE_FAILURE).inc();
    }

    public static void countFailed(IMetricName metricName) {
        getCounter(metricName).labels(RESULT_VALUE_FAILURE).inc();
    }

    /**
     * 异常计数
     * @param metricName
     */
    public static void countException(String metricName) {
        getCounter(metricName).labels(RESULT_VALUE_EXCEPTION).inc();
    }

    public static void countException(IMetricName metricName) {
        getCounter(metricName).labels(RESULT_VALUE_EXCEPTION).inc();
    }

    private static Histogram getHistogram(IMetricName metricName) {
        return getHistogram(metricName.getName(), metricName.getDesc());
    }

    private static Histogram getHistogram(String metricName) {
        return getHistogram(metricName, metricName);
    }

    private static Histogram getHistogram(String metricName, String desc) {
        Histogram histogram = allTimers.get(metricName);
        if (histogram == null) {
            histogram = Histogram.build(metricName, desc).namespace(DEFAULT_NAMESPACE)
                    .subsystem(DEFAULT_TIMER_GROUP).buckets(DEFAULT_BUCKETS).create();
            Histogram hist = allTimers.putIfAbsent(metricName, histogram);
            if (hist == null) {
                histogram.register(getCollectorRegistry());
            } else {
                histogram = hist;
            }
        }
        return histogram;
    }

    /**
     * 开始计时
     * @param metricName
     * @return
     */
    public static Histogram.Timer startTimer(String metricName) {
        return getHistogram(metricName).startTimer();
    }

    public static Histogram.Timer startTimer(IMetricName metricName) {
        return getHistogram(metricName).startTimer();
    }

    /**
     * 停止计时
     * @param timer
     * @return
     */
    public static double stopTimer(Histogram.Timer timer) {
        return timer.observeDuration();
    }
}
