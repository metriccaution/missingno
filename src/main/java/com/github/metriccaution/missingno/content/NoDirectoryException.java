package com.github.metriccaution.missingno.content;

class NoDirectoryException extends RuntimeException {
	private static final long serialVersionUID = 785202694572137886L;

	private final String _context;

	public NoDirectoryException(final String context) {
		_context = context;
	}

	public String getContext() {
		return _context;
	}

}
