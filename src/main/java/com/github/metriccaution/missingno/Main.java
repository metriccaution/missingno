package com.github.metriccaution.missingno;

import static spark.Spark.before;
import static spark.Spark.halt;

import java.util.HashMap;
import java.util.Map;

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

		new ContentRouter("/content", contexts);
		new ContentListingRouter("/listing");
	}

}
