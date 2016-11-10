package com.github.metriccaution.missingno.files;

public class NoSuchFileException extends RuntimeException {
	private static final long serialVersionUID = 6557271615816826787L;

	public NoSuchFileException(final String message, final Object... args) {
		super(String.format(message, args));
	}
}
