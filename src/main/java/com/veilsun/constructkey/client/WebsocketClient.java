package com.veilsun.constructkey.client;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.apigatewaymanagementapi.AmazonApiGatewayManagementApi;
import com.amazonaws.services.apigatewaymanagementapi.AmazonApiGatewayManagementApiClient;
import com.amazonaws.services.apigatewaymanagementapi.model.PostToConnectionRequest;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.veilsun.constructkey.domain.ChannelConnection;
import com.veilsun.constructkey.repository.ChannelConnectionRepository;

@Component
public class WebsocketClient {

	private AmazonApiGatewayManagementApi client;
	
	@Autowired
	private ChannelConnectionRepository channelConnectionRepository;
	
	@Value("${aws.websocket.endpoint.url}")
	private String endpointURL;
	
	@PostConstruct
	void construct() {
		EndpointConfiguration endpoint = new EndpointConfiguration(endpointURL, Regions.US_EAST_1.getName());
		this.client = AmazonApiGatewayManagementApiClient.builder().withEndpointConfiguration(endpoint).build();
	}
	
	public boolean sendMessage(UUID channel, JSONObject json) {
		List<ChannelConnection> connections = channelConnectionRepository.findAllByChannel(channel);
		connections.forEach(c -> {
			sendMessage(c.getConnectionId(), json.toJSONString());
		});
		return true;
	}
	
	public boolean sendMessage(String connectionId, String message) {
		PostToConnectionRequest request = new PostToConnectionRequest();
		request.setConnectionId(connectionId);
		request.setData(ByteBuffer.wrap(message.getBytes()));
		client.postToConnection(request);
		return true;
	}
}
