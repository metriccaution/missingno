package com.github.metriccaution.missingno.http.content.route;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import com.github.metriccaution.missingno.context.Context;
import com.github.metriccaution.missingno.context.NoSuchContextException;
import com.github.metriccaution.missingno.http.Answer;
import com.github.metriccaution.missingno.http.ResponseMapping;
import com.github.metriccaution.missingno.http.content.ContentTypeGuesser;
import com.github.metriccaution.missingno.http.content.fileOps.FileBytes;
import com.github.metriccaution.missingno.http.content.fileOps.FileExists;
import com.github.metriccaution.missingno.http.content.fileOps.NoSuchFileException;
import com.google.common.collect.Maps;

/**
 * Does the legwork - Serves files, taking a best guess to HTTP headers
 */
public class StaticFilesRoute extends ResponseMapping {

	private final Context _context;
	private final FileBytes _reader;
	private final FileExists _exists;

	public StaticFilesRoute(final Context context,
			final FileBytes reader,
			final FileExists exists) {
		_context = context;
		_reader = reader;
		_exists = exists;
	}

	@Override
	protected Answer answer(final Map<String, String> parameters, final List<String> splats) {
		try {
			final String context = parameters.get(":context");
			checkNotNull(context, "No context provided");

			final String path = splats.size() == 0 ? "" : splats.get(0);

			final Path location = getLocation(context, path);

			if (!_exists.fileExists(location)) {
				throw new NoSuchFileException("No such file %s", location);
			}

			return new Answer(200, new ContentTypeGuesser().apply(location), _reader.getBytes(location));
		} catch (final NullPointerException ex) {
			return new Answer(400, Maps.newHashMap(), ex.getMessage().getBytes());
		} catch (final NoSuchContextException ex) {
			return new Answer(404, Maps.newHashMap(), ex.getMessage().getBytes());
		} catch (final NoSuchFileException ex) {
			return new Answer(404, Maps.newHashMap(), ex.getMessage().getBytes());
		} catch (final IOException ex) {
			return new Answer(500, Maps.newHashMap(), "Could not read file".getBytes());
		}
	}

	public Path getLocation(final String context, final String path) {
		final Path resolved = _context.get(context).resolve(path);

		if (_exists.isDirectory(resolved)) {
			return resolved.resolve("index.html");
		}

		return resolved;
	}

}
