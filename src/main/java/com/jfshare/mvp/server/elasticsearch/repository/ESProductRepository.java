package com.jfshare.mvp.server.elasticsearch.repository;


import com.jfshare.mvp.server.elasticsearch.ESProduct;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESProductRepository extends ElasticsearchRepository<ESProduct, String> {
}