package com.amido.stacks.workloads.menu.repository;

import com.amido.stacks.dynamodb.repository.StacksDynamoDbRepository;
import com.amido.stacks.workloads.menu.domain.Menu;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@EnableScan
@EnableScanCount
@Repository
public interface MenuRepository extends StacksDynamoDbRepository<Menu> {

  /**
   * Query is constructed OOTB- out of the box, executed and results are fetched based param
   * restaurantId. Pagination and sorting is done by spring data JPA.
   *
   * @param restaurantId tenantID/RestaurantId
   * @param pageable pagination
   * @return page of menu
   */
  Page<Menu> findAllByRestaurantId(String restaurantId, Pageable pageable);

  /**
   * Query is constructed OOTB - out of the box, executed and results are fetched based param
   * searchTerm. Pagination and sorting is done by spring data JPA.
   *
   * @param searchTerm Menu name
   * @param pageable pagination
   * @return page of menu
   */
  Page<Menu> findAllByNameContaining(String searchTerm, Pageable pageable);

  /**
   * Query is constructed OOTB - out of the box, executed and results are fetched based param
   * restaurantId and searchTerm. Pagination and sorting is done by spring data JPA.
   *
   * @param restaurantId tenantID/RestaurantId
   * @param searchTerm Menu name
   * @param pageable pagination
   * @return page of menu
   */
  Page<Menu> findAllByRestaurantIdAndNameContaining(
      String restaurantId, String searchTerm, Pageable pageable);

  /**
   * Query is constructed OOTB - out of the box, executed and results are fetched based param
   * restaurantId and searchTerm. Pagination and sorting is done by spring data JPA.
   *
   * @param restaurantId tenantID/RestaurantId
   * @param name Menu name
   * @param pageable pagination
   * @return page of menu
   */
  Page<Menu> findAllByRestaurantIdAndName(String restaurantId, String name, Pageable pageable);
}
