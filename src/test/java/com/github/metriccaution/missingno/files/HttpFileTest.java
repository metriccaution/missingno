package com.github.metriccaution.missingno.files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;

public class HttpFileTest {

	@Test
	public void dataImmutablityTest() {
		final byte[] data = new byte[] { 1, 2 };

		final HttpFile file = new HttpFile(Maps.newHashMap(), data);

		final byte[] copy = file.getData();
		copy[0] = 3;

		data[0] = 4;

		assertTrue(Arrays.equals(new byte[] { 1, 2 }, file.getData()));
	}

	@Test
	public void headerCopyTest() {
		final Map<String, String> headers = Maps.newHashMap();
		headers.put("A", "B");

		final HttpFile file = new HttpFile(headers, new byte[] {});
		headers.put("E", "F");

		final Map<String, String> expected = Maps.newHashMap();
		expected.put("A", "B");

		assertEquals(expected, file.getHeaders());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void headerImmutablityTest() {
		final Map<String, String> headers = Maps.newHashMap();
		headers.put("A", "B");

		final HttpFile file = new HttpFile(headers, new byte[] {});

		final Map<String, String> copy = file.getHeaders();
		copy.put("C", "D");
	}

}
