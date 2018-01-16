package com.brevitaz;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class ElasticSearchConfig {

    private static RestHighLevelClient client;

    private ElasticSearchConfig() {
    }

    public static RestHighLevelClient makeInstance(){

        if(client==null){
            client = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost("localhost", 9200, "http")).build());
            return client;
        }else {
            return client;
        }

    }

}
