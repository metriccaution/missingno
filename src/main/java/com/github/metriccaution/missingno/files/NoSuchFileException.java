package com.github.metriccaution.missingno.files;

import static spark.Spark.exception;

public class NoSuchFileException extends RuntimeException {
	private static final long serialVersionUID = 6557271615816826787L;

	public static void mapHandler() {
		exception(NoSuchFileException.class, (ex, req, res) -> {
			res.body(ex.getMessage());
			res.status(404);
		});
	}

	public NoSuchFileException(final String message, final Object... args) {
		super(String.format(message, args));
	}
}
