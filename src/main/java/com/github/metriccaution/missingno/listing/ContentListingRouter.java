package com.github.metriccaution.missingno.listing;

import java.util.HashMap;

import com.github.metriccaution.missingno.Router;
import com.google.common.collect.Maps;

import spark.ModelAndView;
import spark.template.jade.JadeTemplateEngine;

public class ContentListingRouter extends Router {

	public ContentListingRouter(final String base) {
		super(base);

		// TODO - Templating
		get("/*", (req, res) -> {
			final HashMap<Object, Object> data = Maps.newHashMap();

			data.put("context", "Context Data");

			return new ModelAndView(data, "no-context");
		}, new JadeTemplateEngine());
	}

}
