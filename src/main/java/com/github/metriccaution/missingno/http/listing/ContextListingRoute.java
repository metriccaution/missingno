package com.github.metriccaution.missingno.http.listing;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.github.metriccaution.missingno.context.Context;
import com.github.metriccaution.missingno.http.Answer;
import com.github.metriccaution.missingno.http.ResponseMapping;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Lists registered contexts
 */
public class ContextListingRoute extends ResponseMapping {

	// TODO - To HTML or template

	private final Context _context;

	public ContextListingRoute(final Context context) {
		_context = context;
	}

	@Override
	protected Answer answer(final Map<String, String> parameters, final List<String> splats) {

		final StringBuilder sb = new StringBuilder();

		final List<String> contexts = Lists.newArrayList(_context.listContexts());
		Collections.sort(contexts);

		for (final String context : contexts) {
			sb.append(context);
		}

		return new Answer(200, Maps.newHashMap(), sb.toString().getBytes());
	}

}
