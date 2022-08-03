package com.amido.stacks.workloads.menu.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBDocument
public class Item {

  private String id;

  private String name;

  private String description;

  private Double price;

  private Boolean available;
}
