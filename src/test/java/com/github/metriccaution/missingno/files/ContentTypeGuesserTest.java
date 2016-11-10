package com.github.metriccaution.missingno.files;

import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;

public class ContentTypeGuesserTest {

	@Test
	public void noExtensionTest() {
		final HeaderGenerator guesser = new ContentTypeGuesser();
		assertEquals(guesser.apply(Paths.get("test")), Maps.newHashMap());
	}

	@Test
	public void endsInDotTest() {
		final HeaderGenerator guesser = new ContentTypeGuesser();
		assertEquals(guesser.apply(Paths.get("test.")), Maps.newHashMap());
	}

	@Test
	public void htmlTest() {
		final HeaderGenerator guesser = new ContentTypeGuesser();
		final Map<String, String> expected = Maps.newHashMap();
		expected.put("Content-Type", "text/html");
		assertEquals(guesser.apply(Paths.get("test.one.html")), expected);
	}

	@Test
	public void cssTest() {
		final HeaderGenerator guesser = new ContentTypeGuesser();
		final Map<String, String> expected = Maps.newHashMap();
		expected.put("Content-Type", "text/css");
		assertEquals(guesser.apply(Paths.get("test.css")), expected);
	}

	@Test
	public void jsTest() {
		final HeaderGenerator guesser = new ContentTypeGuesser();
		final Map<String, String> expected = Maps.newHashMap();
		expected.put("Content-Type", "text/javascript");
		assertEquals(guesser.apply(Paths.get("test.js")), expected);
	}

	@Test
	public void jsonTest() {
		final HeaderGenerator guesser = new ContentTypeGuesser();
		final Map<String, String> expected = Maps.newHashMap();
		expected.put("Content-Type", "application/json");
		assertEquals(guesser.apply(Paths.get("test.json")), expected);
	}

	@Test
	public void pngTest() {
		final HeaderGenerator guesser = new ContentTypeGuesser();
		final Map<String, String> expected = Maps.newHashMap();
		expected.put("Content-Type", "image/png");
		assertEquals(guesser.apply(Paths.get("test.png")), expected);
	}

	@Test
	public void gifTest() {
		final HeaderGenerator guesser = new ContentTypeGuesser();
		final Map<String, String> expected = Maps.newHashMap();
		expected.put("Content-Type", "image/gif");
		assertEquals(guesser.apply(Paths.get("test.gif")), expected);
	}

	@Test
	public void jpgTest() {
		final HeaderGenerator guesser = new ContentTypeGuesser();
		final Map<String, String> expected = Maps.newHashMap();
		expected.put("Content-Type", "image/jpg");
		assertEquals(guesser.apply(Paths.get("test.jpg")), expected);
		assertEquals(guesser.apply(Paths.get("test.jpeg")), expected);
	}

	@Test
	public void bitmapTest() {
		final HeaderGenerator guesser = new ContentTypeGuesser();
		final Map<String, String> expected = Maps.newHashMap();
		expected.put("Content-Type", "image/bmp");
		assertEquals(guesser.apply(Paths.get("test.bmp")), expected);
	}

}
