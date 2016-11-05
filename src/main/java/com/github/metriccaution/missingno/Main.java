package com.github.metriccaution.missingno;

import static spark.Spark.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

	public static void main(final String[] args) {
		final Map<String, String> contexts = new HashMap<>();
		// Folders go here

		before("*", (req, res) -> {
			if (!req.host().matches("localhost(:\\d+)?")) {
				halt(403);
			}
		});

		before("*", (req, res) -> {
			LOGGER.info(req.uri());
		});

		get("/content/:dir", (req, res) -> {
			res.redirect("/content/" + req.params("dir") + "/");
			return null;
		});

		get("/content/:dir/*", (req, res) -> {
			final String dir = req.params("dir");

			if (!contexts.containsKey(dir)) {
				throw new NoDirectoryException();
			}

			final String path = req.splat().length == 0 ? "" : req.splat()[0];
			final Path location = createPath(contexts.get(dir), path);
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
			res.body("No directory");
		});

		exception(NoFileException.class, (ex, req, res) -> {
			res.status(404);
			res.body("No file");
		});
	}

	public static Path createPath(final String root, final String path) {
		final Path constructedPath = Paths.get(root.replaceAll("^~", System.getProperty("user.home")), path);

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

	public static class NoDirectoryException extends Exception {
		private static final long serialVersionUID = 785202694572137886L;
	}

	public static class NoFileException extends Exception {
		private static final long serialVersionUID = 785202694572137886L;
	}

}
