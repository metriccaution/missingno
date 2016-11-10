package com.github.metriccaution.missingno.content;

import java.util.Map.Entry;

import com.github.metriccaution.missingno.Router;
import com.github.metriccaution.missingno.files.HttpFile;
import com.github.metriccaution.missingno.files.HttpFileRequester;

public class ContentRouter extends Router {

	private HttpFileRequester _files;

	public ContentRouter(final String base, final HttpFileRequester files) {
		super(base);

		_files = files;

		get("/:context", (req, res) -> {
			res.redirect("/content/" + req.params("context") + "/");
			return null;
		});

		get("/:context/*", (req, res) -> {
			final String context = req.params("context");
			final String file = req.splat().length == 0 ? "" : req.splat()[0];
			final HttpFile fileData = _files.get(context, file);

			for (final Entry<String, String> entry : fileData.getHeaders().entrySet()) {
				res.header(entry.getKey(), entry.getValue());
			}

			return fileData.getData();
		});

	}

}
