package com.amido.stacks.dynamodb.repository;

import com.amido.stacks.core.repository.StacksPersistence;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface StacksDynamoDbRepository<T>
    extends StacksPersistence<T>,
        CrudRepository<T, String>,
        PagingAndSortingRepository<T, String> {}
