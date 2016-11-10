package com.github.metriccaution.missingno.files;

import java.nio.file.Path;
import java.util.Map;

import com.google.common.collect.Maps;

public class ContentTypeGuesser implements HeaderGenerator {

	private static final String CONTENT_TYPE = "Content-Type";

	private String fileExtension(final Path location) {
		final String[] extensionParts = location.toString().split(".*\\.");

		if (extensionParts.length > 1) {
			return extensionParts[extensionParts.length - 1];
		} else {
			return null;
		}
	}

	@Override
	public Map<String, String> apply(final Path location) {
		final Map<String, String> ret = Maps.newHashMap();

		// Guess a mime type by file extension
		final String fileExtension = fileExtension(location);
		if (fileExtension == null) {
			return ret;
		}

		switch (fileExtension) {
		case "html":
			ret.put(CONTENT_TYPE, "text/html");
			break;
		case "css":
			ret.put(CONTENT_TYPE, "text/css");
			break;
		case "js":
			ret.put(CONTENT_TYPE, "text/javascript");
			break;
		case "json":
			ret.put(CONTENT_TYPE, "application/json");
			break;
		case "xml":
			ret.put(CONTENT_TYPE, "application/xml");
			break;
		case "png":
			ret.put(CONTENT_TYPE, "image/png");
			break;
		case "gif":
			ret.put(CONTENT_TYPE, "image/gif");
			break;
		case "jpg":
		case "jpeg":
			ret.put(CONTENT_TYPE, "image/jpg");
			break;
		case "bmp":
			ret.put(CONTENT_TYPE, "image/bmp");
			break;
		}

		return ret;
	}

}
