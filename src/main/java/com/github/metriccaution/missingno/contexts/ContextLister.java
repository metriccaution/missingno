package com.github.metriccaution.missingno.contexts;

import static spark.Spark.get;

public class ContextLister {

	private final Context _context;

	public ContextLister(final Context context) {
		_context = context;
	}

	public void start() {
		get("/", (req, res) -> {
			final StringBuilder sb = new StringBuilder();
			for (final String context : _context.listContexts()) {
				sb.append(context).append(" == ").append(_context.get(context)).append("\n");
			}
			return sb.toString();
		});
	}

}
