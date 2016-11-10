package com.github.metriccaution.missingno.contexts;

import static org.junit.Assert.assertEquals;

import java.util.function.Function;

import org.junit.Test;

public class TildeAsHomeDirectoryTest {

	@Test
	public void replacesTilde() {
		final Function<String, String> tilde = new TildeAsHomeDirectory();
		assertEquals(tilde.apply("~"), System.getProperty("user.home"));
		assertEquals(tilde.apply("~/Downloads"), System.getProperty("user.home") + "/Downloads");
	}

	@Test
	public void ignoresNonLeadingTildes() {
		final Function<String, String> tilde = new TildeAsHomeDirectory();
		assertEquals(tilde.apply("/boot/~"), "/boot/~");
	}

	@Test
	public void handlesNoTilde() {
		final Function<String, String> tilde = new TildeAsHomeDirectory();
		assertEquals(tilde.apply("/boot"), "/boot");
	}

}
