package com.github.metriccaution.missingno.context;

import java.util.function.Function;

class TildeAsHomeDirectory implements Function<String, String> {

	@Override
	public String apply(final String path) {
		return path.replaceAll("^~", System.getProperty("user.home"));
	}

}
