package com.github.metriccaution.missingno.context;

/**
 * Thrown when a context is asked for that doesn't exist
 */
public class NoSuchContextException extends RuntimeException {
	private static final long serialVersionUID = -4250674296790886786L;

	public NoSuchContextException(final String message, final Object... args) {
		super(String.format(message, args));
	}
}
