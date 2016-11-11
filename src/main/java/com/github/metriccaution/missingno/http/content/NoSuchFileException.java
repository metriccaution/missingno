package com.github.metriccaution.missingno.http.content;

public class NoSuchFileException extends RuntimeException {
	private static final long serialVersionUID = -8580328108638205440L;

	public NoSuchFileException(final String message, final Object... args) {
		super(String.format(message, args));
	}
}
