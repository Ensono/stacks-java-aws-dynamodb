package com.amido.stacks.workloads.menu.service.impl;

import static org.springframework.data.domain.PageRequest.of;

import com.amido.stacks.workloads.menu.domain.Menu;
import com.amido.stacks.workloads.menu.service.MenuQueryService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DynamoDbMenuQueryService implements MenuQueryService {

  private static final String NAME = "name";

  private final MenuRepository menuRepository;

  @Override
  public Optional<Menu> findById(UUID id) {
    return menuRepository.findById(id.toString());
  }

  @Override
  public List<Menu> findAll(int pageNumber, int pageSize) {

    Page<Menu> page = menuRepository.findAll(of(0, pageSize));

    // This is specific and needed due to the way in which CosmosDB handles pagination
    // using a continuationToken and a limitation in the Swagger Specification.
    // See https://github.com/Azure/azure-sdk-for-java/issues/12726
    int currentPage = 0;
    while (currentPage < pageNumber && page.hasNext()) {
      currentPage++;
      Pageable nextPageable = page.nextPageable();
      page = menuRepository.findAll(nextPageable);
    }
    return page.getContent();
  }

  @Override
  public List<Menu> findAllByRestaurantId(UUID restaurantId, Integer pageSize, Integer pageNumber) {

    return menuRepository
        .findAllByRestaurantId(restaurantId.toString(), of(0, pageSize))
        .getContent();
  }

  @Override
  public List<Menu> findAllByNameContaining(
      String searchTerm, Integer pageSize, Integer pageNumber) {

    return menuRepository.findAllByNameContaining(searchTerm, of(0, pageSize)).getContent();
  }

  @Override
  public List<Menu> findAllByRestaurantIdAndNameContaining(
      UUID restaurantId, String searchTerm, Integer pageSize, Integer pageNumber) {

    return menuRepository
        .findAllByRestaurantIdAndNameContaining(
            restaurantId.toString(), searchTerm, of(0, pageSize))
        .getContent();
  }
}
