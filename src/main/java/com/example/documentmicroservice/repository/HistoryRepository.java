package com.example.documentmicroservice.repository;

import com.example.documentmicroservice.model.HistoryDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends ElasticsearchRepository<HistoryDocument,Long> {
}
