package com.github.metriccaution.missingno.http;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Lists;

import spark.Request;
import spark.Response;
import spark.Route;

public abstract class ResponseMapping implements Route {

	@Override
	public Object handle(final Request req, final Response res) throws Exception {
		final Map<String, String> params = req.params();
		final List<String> splats = Lists.newArrayList(req.splat());
		final Answer answer = answer(params, splats);

		res.status(answer.getCode());

		for (final Entry<String, String> header : answer.getHeaders().entrySet()) {
			res.header(header.getKey(), header.getValue());
		}

		return answer.getData();
	}

	protected abstract Answer answer(Map<String, String> parameters, List<String> splats);

}
