package com.works.repositories.elastic;

import com.works.documents.ElasticCustomer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticSearchRepository extends ElasticsearchRepository<ElasticCustomer, String> {

    @Query("{\"bool\":{\"must\":[],\"must_not\":[],\"should\":[{\"match\":{\"cname\":\"?0\"}},{\"match\":{\"csurname\":\"?0\"}}]}},\"from\":0,\"size\":10,\"sort\":[],\"aggs\":{}")
    Page<ElasticCustomer> findBySearch(String search, Pageable pageable);




}
