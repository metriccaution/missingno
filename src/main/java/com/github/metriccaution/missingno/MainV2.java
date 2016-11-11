package com.github.metriccaution.missingno;

import static spark.Spark.get;
import static spark.Spark.port;

import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.metriccaution.missingno.config.ContextEntry;
import com.github.metriccaution.missingno.config.MissingnoConfig;
import com.github.metriccaution.missingno.context.Context;
import com.github.metriccaution.missingno.context.Context.ContextBuilder;
import com.github.metriccaution.missingno.http.content.FileBytes;
import com.github.metriccaution.missingno.http.content.FileExists;
import com.github.metriccaution.missingno.http.content.StaticFilesRoute;
import com.github.metriccaution.missingno.http.listing.ContextListingRoute;

public class MainV2 {

	public static void main(final String[] args) throws Exception {
		final InputStream resourceAsStream = MainV2.class.getClassLoader().getResourceAsStream("config.yaml");
		final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
		final MissingnoConfig config = objectMapper.readValue(resourceAsStream, MissingnoConfig.class);

		final ContextBuilder builder = new ContextBuilder();
		for (final ContextEntry entry : config.getContexts()) {
			builder.add(entry.getLabel(), entry.getLocation());
		}

		final Context context = builder.build();

		port(config.getServer().getPort());

		get("/", new ContextListingRoute(context));

		get("/:context", (req, res) -> {
			res.redirect("/" + req.params("context") + "/");
			return null;
		});
		get("/:context/*", new StaticFilesRoute(context, new FileBytes(), new FileExists()));
	}

}
