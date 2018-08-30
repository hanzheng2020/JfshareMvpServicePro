package com.jfshare.mvp.server.elasticsearch.repository;


import com.jfshare.mvp.server.elasticsearch.ESProduct;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESProductRepository extends ElasticsearchRepository<ESProduct, String> {
//	Page<ESProduct> findByProductNameLike(String productName, Pageable pageable);
}