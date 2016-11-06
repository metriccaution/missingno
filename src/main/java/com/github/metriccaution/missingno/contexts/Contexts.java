package com.github.metriccaution.missingno.contexts;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class Contexts {

	private final Map<String, String> _contexts;

	public Contexts(final Map<String, String> contexts) {
		_contexts = ImmutableMap.copyOf(contexts);
	}

	public Path get(final String context) {
		if (_contexts.containsKey(context)) {
			return Paths.get(context.replaceAll("^~", System.getProperty("user.home")));
		}

		throw new IllegalArgumentException(String.format("No context %s in map", context));
	}

}
