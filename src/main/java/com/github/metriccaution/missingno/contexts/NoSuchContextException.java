package com.github.metriccaution.missingno.contexts;

import static spark.Spark.exception;

/**
 * Thrown when a context is asked for that doesn't exist
 */
public class NoSuchContextException extends RuntimeException {
	private static final long serialVersionUID = -4250674296790886786L;

	public static void mapHandler() {
		exception(NoSuchContextException.class, (ex, req, res) -> {
			res.body(ex.getMessage());
			res.status(404);
		});
	}

	public NoSuchContextException(final String message, final Object... args) {
		super(String.format(message, args));
	}
}
