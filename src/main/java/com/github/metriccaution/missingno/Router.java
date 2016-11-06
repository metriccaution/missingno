package com.github.metriccaution.missingno;

import spark.Route;

public abstract class Router {

	private final String _base;

	public Router(final String base) {
		_base = base;
	}

	public void get(final String pattern, final Route route) {
		get(_base + pattern, route);
	}

}
