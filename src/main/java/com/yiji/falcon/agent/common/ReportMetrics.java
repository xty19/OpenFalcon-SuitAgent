package com.yiji.falcon.agent.common;/**
 * Copyright 2014-2015 the original ql
 * Created by QianLong on 16/4/26.
 */

import com.yiji.falcon.agent.falcon.FalconReportObject;
import com.yiji.falcon.agent.util.HttpUtil;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by QianLong on 16/4/26.
 */
public class ReportMetrics {

    private static final Logger log = LoggerFactory.getLogger(ReportMetrics.class);

    /**
     * 推送数据到falcon
     * @param falconReportObjectList
     */
    public static void push(Collection<FalconReportObject> falconReportObjectList){
        JSONArray jsonArray = new JSONArray();
        for (FalconReportObject falconReportObject : falconReportObjectList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("endpoint",falconReportObject.getEndpoint());
            jsonObject.put("metric",falconReportObject.getMetric());
            jsonObject.put("timestamp",falconReportObject.getTimestamp());
            jsonObject.put("step",falconReportObject.getStep());
            jsonObject.put("value",falconReportObject.getValue());
            jsonObject.put("counterType",falconReportObject.getCounterType());
            jsonObject.put("tags",falconReportObject.getTags() == null ? "" : falconReportObject.getTags());
            jsonArray.put(jsonObject);
        }
        log.debug("报告Falcon : [{}]",jsonArray.toString());
        HttpResponse result = null;
        try {
            result = HttpUtil.postJSON(AgentConfiguration.INSTANCE.getAgentPushUrl(),jsonArray.toString());
        } catch (IOException e) {
            log.error("post请求异常",e);
        }
        log.info("push回执:" + result);
    }

    /**
     * 推送数据到falcon
     * @param falconReportObject
     */
    public static void push(FalconReportObject falconReportObject){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("endpoint",falconReportObject.getEndpoint());
        jsonObject.put("metric",falconReportObject.getMetric());
        jsonObject.put("timestamp",falconReportObject.getTimestamp());
        jsonObject.put("step",falconReportObject.getStep());
        jsonObject.put("value",falconReportObject.getValue());
        jsonObject.put("counterType",falconReportObject.getCounterType());
        jsonObject.put("tags",falconReportObject.getTags() == null ? "" : falconReportObject.getTags());
        jsonArray.put(jsonObject);
        log.debug("报告Falcon : [{}]",jsonArray.toString());
        HttpResponse result = null;
        try {
            result = HttpUtil.postJSON(AgentConfiguration.INSTANCE.getAgentPushUrl(),jsonArray.toString());
        } catch (IOException e) {
            log.error("post请求异常",e);
        }
        log.info("push回执:" + result);
    }

}