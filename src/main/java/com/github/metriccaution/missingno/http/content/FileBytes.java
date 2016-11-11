package com.github.metriccaution.missingno.http.content;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBytes {

	public byte[] getBytes(final Path p) throws IOException {
		return Files.readAllBytes(p);
	}

}
