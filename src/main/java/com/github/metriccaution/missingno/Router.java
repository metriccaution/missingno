package com.github.metriccaution.missingno;

import spark.Route;
import spark.Spark;
import spark.TemplateEngine;
import spark.TemplateViewRoute;

public abstract class Router {

	private final String _base;

	public Router(final String base) {
		_base = base;
	}

	public void get(final String pattern, final Route route) {
		Spark.get(_base + pattern, route);
	}

	public void get(final String pattern, final TemplateViewRoute route, final TemplateEngine templating) {
		Spark.get(_base + pattern, route, templating);
	}

}
