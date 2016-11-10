package com.github.metriccaution.missingno.files;

import java.util.Arrays;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class HttpFile {

	private final Map<String, String> _headers;
	private final byte[] _data;

	public HttpFile(final Map<String, String> headers, final byte[] data) {
		_headers = ImmutableMap.copyOf(headers);
		_data = Arrays.copyOf(data, data.length);
	}

	public Map<String, String> getHeaders() {
		return _headers;
	}

	public byte[] getData() {
		return Arrays.copyOf(_data, _data.length);
	}

}
