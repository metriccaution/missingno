package com.github.metriccaution.missingno.config;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;

public class MissingnoConfig {

	private final ServerConfig _server;
	private final List<ContextEntry> _contexts;

	public MissingnoConfig(@JsonProperty("server") final ServerConfig server,
			@JsonProperty("contexts") final List<ContextEntry> contexts) {
		_server = server;
		_contexts = ImmutableList.copyOf(contexts);
	}

	public ServerConfig getServer() {
		return _server;
	}

	public final List<ContextEntry> getContexts() {
		return _contexts;
	}

}
