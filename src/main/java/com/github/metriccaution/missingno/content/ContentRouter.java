package com.github.metriccaution.missingno.content;

import static spark.Spark.exception;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import com.github.metriccaution.missingno.Router;
import com.github.metriccaution.missingno.contexts.Contexts;
import com.google.common.collect.Maps;

import spark.ModelAndView;
import spark.TemplateEngine;

public class ContentRouter extends Router {

	public ContentRouter(final String base, final Contexts contexts, final TemplateEngine engine) {
		super(base);

		get("/:dir", (req, res) -> {
			res.redirect("/content/" + req.params("dir") + "/");
			return null;
		});

		get("/:dir/*", (req, res) -> {
			final String dir = req.params("dir");

			final String path = req.splat().length == 0 ? "" : req.splat()[0];
			final Path location = createPath(getPath(contexts, dir), path);
			final Optional<byte[]> result = fileContents(location);

			if (result.isPresent()) {
				for (final Entry<String, String> entry : guessHeaders(location).entrySet()) {
					res.header(entry.getKey(), entry.getValue());
				}

				return result.get();
			} else {
				throw new NoFileException();
			}
		});


		exception(NoDirectoryException.class, (ex, req, res) -> {
			res.status(404);
			final HashMap<String, String> data = Maps.newHashMap();
			data.put("context", ((NoDirectoryException)ex).getContext());
			res.body(engine.render(new ModelAndView(data, "no-context")));
		});

		exception(NoFileException.class, (ex, req, res) -> {
			res.status(404);
			res.body("No file");
		});
	}

	public static Path getPath(final Contexts ctx, final String name) {
		try {
			return ctx.get(name);
		} catch (final IllegalArgumentException e) {
			throw new NoDirectoryException(name);
		}
	}

	public static Path createPath(final Path root, final String path) {
		final Path constructedPath = root.resolve(path);

		if (Files.isDirectory(constructedPath)) {
			return constructedPath.resolve("index.html");
		} else {
			return constructedPath;
		}
	}

	public static Optional<byte[]> fileContents(final Path location) {
		try {
			if (!Files.exists(location)) {
				return Optional.empty();
			}

			if (Files.isDirectory(location)) {
				return fileContents(location.resolve("index.html"));
			} else {
				return Optional.of(Files.readAllBytes(location));
			}

		} catch (final IOException e) {
			return Optional.empty();
		}
	}

	public static Map<String, String> guessHeaders(final Path location) {
		final Map<String, String> ret = new HashMap<>();

		// Guess a mime type by file extension
		final String[] extensionParts = location.toString().split(".*\\.");
		if (extensionParts.length == 2) {
			final String extension = extensionParts[1].trim();
			switch (extension) {

			case "css":
				ret.put("Content-Type", "text/css");
				break;
			case "html":
				ret.put("Content-Type", "text/html");
				break;
			case "js":
				ret.put("Content-Type", "text/javascript");
				break;

			case "png":
				ret.put("Content-Type", "image/png");
				break;
			case "gif":
				ret.put("Content-Type", "image/gif");
				break;
			case "jpg":
			case "jpeg":
				ret.put("Content-Type", "image/jpg");
				break;
			case "bmp":
				ret.put("Content-Type", "image/bmp");
				break;

			case "json":
				ret.put("Content-Type", "application/json");
				break;
			}
		}

		return ret;
	}

}
