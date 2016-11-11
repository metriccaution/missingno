package com.github.metriccaution.missingno.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ContextEntry {

	private final String _label;
	private final String _location;

	@JsonCreator
	public ContextEntry(@JsonProperty("label") final String label,
			@JsonProperty("location") final String location) {
		_label = label;
		_location = location;
	}

	public String getLabel() {
		return _label;
	}

	public String getLocation() {
		return _location;
	}

}
