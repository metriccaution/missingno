package com.github.metriccaution.missingno.http;

import java.util.Arrays;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * The response to a HTTP request
 */
public class Answer {

	private final int _code;
	private final Map<String, String> _headers;
	private final byte[] _data;

	public Answer(final int code, final Map<String, String> headers, final byte[] data) {
		_code = code;
		_headers = Maps.newHashMap(headers);
		_data = Arrays.copyOf(data, data.length);
	}

	public int getCode() {
		return _code;
	}

	public Map<String, String> getHeaders() {
		return _headers;
	}

	public byte[] getData() {
		return _data;
	}

}
