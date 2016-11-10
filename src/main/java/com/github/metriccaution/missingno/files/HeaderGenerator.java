package com.github.metriccaution.missingno.files;

import java.nio.file.Path;
import java.util.Map;
import java.util.function.Function;

/**
 * Generates headers from files
 */
public interface HeaderGenerator extends Function<Path, Map<String, String>> {
}
