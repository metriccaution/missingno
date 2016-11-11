package com.github.metriccaution.missingno;

import static spark.Spark.before;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.metriccaution.missingno.content.ContentRouter;
import com.github.metriccaution.missingno.contexts.Context;
import com.github.metriccaution.missingno.contexts.Context.ContextBuilder;
import com.github.metriccaution.missingno.contexts.ContextLister;
import com.github.metriccaution.missingno.contexts.NoSuchContextException;
import com.github.metriccaution.missingno.files.HttpFileRequester;
import com.github.metriccaution.missingno.files.NoSuchFileException;

public class Main {

	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

	public static void main(final String[] args) {

		final Context ctx = new ContextBuilder()
				// Folders go here
				.add("home", "~")
				.add("downloads", "~/Downloads")
				.build();

		before("*", (req, res) -> {
			if (!req.host().matches("localhost(:\\d+)?")) {
				//				halt(403);
			}
		});

		before("*", (req, res) -> {
			LOGGER.info(req.uri());
		});

		new ContentRouter("/content", new HttpFileRequester(ctx));
		new ContextLister(ctx).start();

		NoSuchContextException.mapHandler();
		NoSuchFileException.mapHandler();
	}

}
