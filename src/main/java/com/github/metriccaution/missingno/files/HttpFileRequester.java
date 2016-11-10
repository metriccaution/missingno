package com.github.metriccaution.missingno.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import com.github.metriccaution.missingno.contexts.Context;

/**
 * Handles serving files
 */
public class HttpFileRequester {

	private final Context _context;
	private final HeaderGenerator _headers;

	public HttpFileRequester(final Context context) {
		_context = context;
		_headers = new ContentTypeGuesser();
	}

	public HttpFile get(final String context, final String file) {
		final Path constructedPath = createPath(_context.get(context), file);

		if (!Files.exists(constructedPath)) {
			throw new NoSuchFileException("No file %s", constructedPath);
		}

		try {
			final byte[] data = Files.readAllBytes(constructedPath);
			final Map<String, String> headers = _headers.apply(constructedPath);
			return new HttpFile(headers, data);
		} catch (final IOException e) {
			throw new IllegalStateException("Could not read file", e);
		}
	}

	public Path createPath(final Path dir, final String path) {
		final Path constructedPath = dir.resolve(path);

		if (Files.isDirectory(constructedPath)) {
			return constructedPath.resolve("index.html");
		} else {
			return constructedPath;
		}
	}

}
