package com.amido.stacks.dynamodb.config;

import static com.amazonaws.util.StringUtils.isNullOrEmpty;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "amazon.dynamodb")
@EnableDynamoDBRepositories(basePackages = "com.amido.stacks.workloads.menu.repository")
public class DynamoDBConfig {

  @Value("${amazon.dynamodb.endpoint}")
  private String dynamoDbEndpoint;

  @Value("${amazon.dynamodb.signingRegion}")
  private String signingRegion;

  @Bean
  public AmazonDynamoDB amazonDynamoDB() {

    AmazonDynamoDBClientBuilder clientBuilder =
        AmazonDynamoDBClientBuilder.standard()
            .withCredentials(new DefaultAWSCredentialsProviderChain());

    if (!isNullOrEmpty(dynamoDbEndpoint)) {
      clientBuilder.withEndpointConfiguration(
          new EndpointConfiguration(dynamoDbEndpoint, signingRegion));
    }

    return clientBuilder.build();
  }
}
