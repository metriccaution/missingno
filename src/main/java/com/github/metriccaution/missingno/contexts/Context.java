package com.github.metriccaution.missingno.contexts;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class Context {

	private final Map<String, String> _contexts;

	private static final Function<String, String> FILE_NAME_TRANSFORM = new TildeAsHomeDirectory();

	private Context(final Map<String, String> contexts) {
		_contexts = ImmutableMap.copyOf(contexts);
	}

	public Set<String> listContexts() {
		return Sets.newHashSet(_contexts.keySet());
	}

	public Path get(final String context) {
		if (_contexts.containsKey(context)) {
			final String match = _contexts.get(context);
			return Paths.get(FILE_NAME_TRANSFORM.apply(match));
		}

		throw new NoSuchContextException("No context %s in map", context);
	}

	public static class ContextBuilder {

		private final Map<String, String> _contexts;

		public ContextBuilder() {
			_contexts = Maps.newHashMap();
		}

		public ContextBuilder add(final String name, final String value) {
			_contexts.put(name, value);
			return this;
		}

		public Context build() {
			return new Context(_contexts);
		}

	}

}
