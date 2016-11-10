package com.github.metriccaution.missingno;

import static spark.Spark.before;
import static spark.Spark.halt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.metriccaution.missingno.content.ContentRouter;
import com.github.metriccaution.missingno.contexts.Context;
import com.github.metriccaution.missingno.contexts.Context.ContextBuilder;
import com.github.metriccaution.missingno.files.HttpFileRequester;

public class Main {

	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

	public static void main(final String[] args) {

		final Context ctx = new ContextBuilder()
				// Folders go here
				.add("home", "~")
				.build();

		before("*", (req, res) -> {
			if (!req.host().matches("localhost(:\\d+)?")) {
				halt(403);
			}
		});

		before("*", (req, res) -> {
			LOGGER.info(req.uri());
		});

		new ContentRouter("/content", new HttpFileRequester(ctx));
	}

}
