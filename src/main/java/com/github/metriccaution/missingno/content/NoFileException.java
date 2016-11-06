package com.github.metriccaution.missingno.content;

class NoFileException extends RuntimeException {
	private static final long serialVersionUID = 785202694572137886L;

	private final String _context;
	private final String _file;

	public NoFileException(final String context, final String file) {
		_context = context;
		_file = file;
	}

	public String getContext() {
		return _context;
	}

	public String getFile() {
		return _file;
	}

}
