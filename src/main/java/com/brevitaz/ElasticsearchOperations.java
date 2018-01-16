package com.brevitaz;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.List;

import static com.brevitaz.ElasticSearchConfig.*;

public class ElasticsearchOperations {

    IndexResponse indexResponse;
    public IndexResponse makeIndex(List<Object> objects) throws IOException {

        int i = 1;

        for ( Object object : objects ) {


            IndexRequest request = new IndexRequest(Constant.employeeIndex,"doc",String.valueOf(i));

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(object);

            request.source(json, XContentType.JSON);

            indexResponse = ElasticSearchConfig.makeInstance().index(request);

            i++;
        }

        return indexResponse;
    }

    public GetResponse getDataById(String id) throws IOException {

        GetRequest getRequest = new GetRequest(Constant.employeeIndex,"doc",id);

        GetResponse getResponse = makeInstance().get(getRequest);
        
        return getResponse;
    }

    public DeleteResponse deleteDataById(String id) throws IOException {

        DeleteRequest deleteRequest = new DeleteRequest(Constant.employeeIndex,"doc",id);

        DeleteResponse deleteResponse = makeInstance().delete(deleteRequest);

        return deleteResponse;
    }

    public UpdateResponse updateDataById(String id,Employee employee) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

        UpdateRequest updateRequest = new UpdateRequest(Constant.employeeIndex,"doc",id).doc(objectMapper.writeValueAsString(employee),XContentType.JSON);

        UpdateResponse updateResponse = makeInstance().update(updateRequest);

        return updateResponse;

    }

    public SearchResponse getAllData(String index) throws IOException {

        SearchRequest searchRequest = new SearchRequest(index);

        SearchResponse searchResponse = makeInstance().search(searchRequest);

        return searchResponse;
    }

    public SearchResponse matchQuery() throws IOException {

        SearchRequest searchRequest = new SearchRequest(Constant.employeeIndex);
        searchRequest.types("doc");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchQuery("name","Misha"));
      //  sourceBuilder.query(QueryBuilders.matchAllQuery());

        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = makeInstance().search(searchRequest);

        return searchResponse;
    }


    public SearchResponse termQuery() throws IOException {

        SearchRequest searchRequest = new SearchRequest(Constant.employeeIndex);
        searchRequest.types("doc");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("name","misha"));

        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = makeInstance().search(searchRequest);

        return searchResponse;
    }


    public BulkResponse bulkQuery() throws IOException {

        Employee manik = new Employee("5","Manik","Malhotra","manikm213@gmail.com","Actor","9874563210","500000","Acting");
        Employee nandini = new Employee("6","Nandini","Murthi","nandinim@gmail.com","Scientist","9865327410","200000","Astronomy");

        ObjectMapper objectMapper = new ObjectMapper();
        String maniks = objectMapper.writeValueAsString(manik);
        String nans = objectMapper.writeValueAsString(nandini);

        manik.setEmailId("manikmalhotra@gmail.com");

        String maniku = objectMapper.writeValueAsString(manik);
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(new IndexRequest(Constant.employeeIndex,"doc","5").source(maniks,XContentType.JSON));
        bulkRequest.add(new IndexRequest(Constant.employeeIndex,"doc","6").source(nans,XContentType.JSON));
        bulkRequest.add(new UpdateRequest(Constant.employeeIndex,"doc","5").doc(maniku,XContentType.JSON));
        bulkRequest.add(new DeleteRequest(Constant.employeeIndex,"doc","4"));

        BulkResponse bulkResponse = makeInstance().bulk(bulkRequest);

        return bulkResponse;
    }

}
