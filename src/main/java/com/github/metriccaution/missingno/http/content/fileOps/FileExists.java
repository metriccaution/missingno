package com.github.metriccaution.missingno.http.content.fileOps;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileExists {

	public boolean fileExists(final Path p) {
		return Files.exists(p);
	}

	public boolean isDirectory(final Path p) {
		return Files.isDirectory(p);
	}

}
