package com.brevitaz;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.search.SearchHit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PerformOperation {

    ElasticsearchOperations eso = new ElasticsearchOperations();
    List<Object> employees = new ArrayList<Object>();
    IndexResponse indexResponse;

    public int getIndexResponse(){
        try {

            Employee anjali = new Employee("1","Anjali","Sharma","anjalisharma@gmail.com","Development","9876543210","25000","java");
            Employee misha = new Employee("2","Misha","Chaudhary","mishachaudhary@gmail.com","Design","9876543211","20000","Wordpress");
            Employee ruhi = new Employee("3","Ruhi","Rajput","ruhirajput@gmail.com","Testing","9874563210","30000","JUnit");
            Employee mahi = new Employee("4","Mahi","Kapoor","mahikapoor@gmail.com","HR","9874123650","20000","Account");

            employees.add(anjali);
            employees.add(misha);
            employees.add(ruhi);
            employees.add(mahi);

            indexResponse=eso.makeIndex(employees);

            System.out.print("\n****************************************************************");
            System.out.print(" GET INDEX RESPONSE");
            System.out.println("***************************************************************\n");
            System.out.println("Index response is: "+indexResponse.status());



        } catch (IOException e) {
            e.printStackTrace();
        }

        return indexResponse.status().getStatus();
    }

    public void getDataByIdResponse(){

        try {
            GetResponse getResponse = eso.getDataById("5");
            System.out.print("\n****************************************************************");
            System.out.print(" GET DATA BY ID");
            System.out.println("***************************************************************\n");
            System.out.println("Employee response is: "+getResponse.getSource());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getAllDataResponse(){

        try {

            SearchResponse searchResponse = eso.getAllData(Constant.employeeIndex);
            SearchHit[] hits = searchResponse.getHits().getHits();
            System.out.println(hits.length);
            System.out.print("\n****************************************************************");
            System.out.print(" GET ALL DATA ");
            System.out.println("***************************************************************\n");
            for (SearchHit hit : hits) {
                System.out.println(hit.getSourceAsString());

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getDeleteByIdResponse(){

        try {
            DeleteResponse deleteResponse = eso.deleteDataById("3");

            System.out.print("\n****************************************************************");
            System.out.print(" GET DELETE STATUS");
            System.out.println("***************************************************************\n");
            System.out.println("Delete response is: "+deleteResponse.status());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getUpdateByIdResponse(){

        try {
            Employee anjali  = new Employee();
            anjali.setSurname("Kapoor");
            anjali.setEmailId("anjalikapoor@gmail.com");
            UpdateResponse updateResponse =  eso.updateDataById("1",anjali);
            System.out.print("\n****************************************************************");
            System.out.print(" GET UPDATED ID");
            System.out.println("***************************************************************\n");
            System.out.println("Update response is: "+updateResponse.getId());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getMatchQueryResponse(){


        try {
            SearchResponse searchResponse = eso.matchQuery();
            SearchHit[] hits = searchResponse.getHits().getHits();
            if(hits.length==0){
                System.out.print("\n****************************************************************");
                System.out.print(" GET MATCH DATA ");
                System.out.println("***************************************************************\n");
                System.out.println("Sorry No Data Available");
            }else {
                System.out.print("\n****************************************************************");
                System.out.print(" GET MATCH DATA ");
                System.out.println("***************************************************************\n");
                for (SearchHit hit : hits) {
                    System.out.println(hit.getId());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void getTermQueryResponse(){


        try {
            SearchResponse searchResponse = eso.termQuery();
            SearchHit[] hits = searchResponse.getHits().getHits();
            if(hits.length==0){
                System.out.print("\n****************************************************************");
                System.out.print(" GET DATA USING TERM ");
                System.out.println("***************************************************************\n");
                System.out.println("Sorry No Data Available");
            }else {
                System.out.print("\n****************************************************************");
                System.out.print(" GET DATA USING TERM ");
                System.out.println("***************************************************************\n");
                for (SearchHit hit : hits) {
                    System.out.println(hit.getId());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void bulkResponse() {

        try {
            BulkResponse bulkResponse = eso.bulkQuery();

            System.out.print("\n****************************************************************");
            System.out.print(" GET DATA USING BULK ");
            System.out.println("***************************************************************\n");
            for (BulkItemResponse bulkItemResponse : bulkResponse) {
                DocWriteResponse itemResponse = bulkItemResponse.getResponse();

                if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.INDEX
                        || bulkItemResponse.getOpType() == DocWriteRequest.OpType.CREATE) {
                    IndexResponse indexResponse = (IndexResponse) itemResponse;
                    System.out.println(indexResponse);

                } else if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.UPDATE) {
                    UpdateResponse updateResponse = (UpdateResponse) itemResponse;
                    System.out.println(updateResponse);

                } else if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.DELETE) {
                    DeleteResponse deleteResponse = (DeleteResponse) itemResponse;
                    System.out.println(deleteResponse);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
