/*
 * Ant Group
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.weibo.dip.analysis.view;

import com.weibo.dip.analysis.view.dynamic.ViewLoader;
import com.weibo.dip.analysisql.AnalysisQl;
import com.weibo.dip.analysisql.connector.Metadata;
import com.weibo.dip.analysisql.response.Response;

import java.util.List;

/**
 * @author zuojinfang
 * @version Application.java, v 0.1 2022年08月22日 1:22 PM zuojinfang
 */
public class Application {



    public static void main(String[] args) {
        String topic="CDN服务质量分析";
        String url="jdbcurl";
        String username="root";
        String password="password";
        String table="test";

        DefaultConnector connector=new DefaultConnector();
        Metadata metadata=new DefaultView(topic,url,username,password,table);
        connector.register( metadata);
//        connector.enableDynamic();
        AnalysisQl analysisQl = new AnalysisQl(connector);
        String dsl=" {\n" +
            "     \"type\": \"query\",\n" +
            "     \"topic\": \"CDN服务质量分析\",\n" +
            "     \"interval\": {\n" +
            "         \"start\": \"2020-03-16 00:00:00\",\n" +
            "         \"end\": \"2020-03-16 23:59:59\"\n" +
            "     },\n" +
            "     \"granularity\": {\n" +
            "         \"data\": 1,\n" +
            "         \"unit\": \"h\"\n" +
            "     },\n" +
            "     \"metric\": \"download_speed_avg\",\n" +
            "     \"where\": {\n" +
            "         \"operator\": \"regex\",\n" +
            "         \"name\": \"domain\",\n" +
            "         \"pattern\": \"^.*weibo.*$\"\n" +
            "     },\n" +
            "     \"groups\": [\n" +
            "         \"isp\"\n" +
            "     ], \n" +
            "     \"having\": {\n" +
            "         \"operator\": \"gt\",\n" +
            "         \"name\": \"download_speed_avg\",\n" +
            "         \"value\": 2000\n" +
            "     },\n" +
            "     \"orders\": [\n" +
            "         {\n" +
            "             \"name\": \"download_speed_avg\",\n" +
            "             \"sort\": \"desc\"\n" +
            "         }\n" +
            "     ], \n" +
            "     \"limit\": 100\n" +
            " }\n";
        Response response = analysisQl.request(dsl);
        System.out.println(response);
    }
}