package com.brevitaz;


public class Main {

    public static void main(String[] args) {

        ElasticSearchConfig.makeInstance();

        PerformOperation po = new PerformOperation();
      //  po.getIndexResponse();

        po.getUpdateByIdResponse();
        po.getDeleteByIdResponse();

        po.bulkResponse();
        po.getMatchQueryResponse();
        po.getTermQueryResponse();
        po.getDataByIdResponse();
        po.getAllDataResponse();


    }
}
