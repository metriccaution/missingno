package com.github.metriccaution.missingno.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ServerConfig {

	private final int _port;

	@JsonCreator
	public ServerConfig(@JsonProperty("port") final int port) {
		_port = port;
	}

	public int getPort() {
		return _port;
	}

}
