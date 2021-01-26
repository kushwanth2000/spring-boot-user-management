package com.example.TransactionManagementElastic.Repository;

import com.example.TransactionManagementElastic.Model.TransactionElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface transactionElasticSearchRepository extends ElasticsearchRepository<TransactionElastic, String> {

    public List<TransactionElastic> findBysenderPhone(long senderPhone);
    public List<TransactionElastic> findByreceiverPhone(long receiverPhone);
}
