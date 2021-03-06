package com.github.metriccaution.missingno.context;

import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;

import org.junit.Test;

public class ContextTest {

	@Test
	public void matchInContext() {
		final Context context = new Context.ContextBuilder().add("test", "ABC").build();
		assertEquals(context.get("test"), Paths.get("ABC"));
	}

	@Test
	public void replacesTildeWithHome() {
		final Context context = new Context.ContextBuilder().add("test", "~").add("test2", "~/home").build();
		assertEquals(context.get("test"), Paths.get(System.getProperty("user.home")));
		assertEquals(context.get("test2"), Paths.get(System.getProperty("user.home"), "home"));
	}

	@Test(expected = NoSuchContextException.class)
	public void noMatchInContext() {
		final Context context = new Context.ContextBuilder().add("test", "ABC").build();
		context.get("test-1");
	}

	@Test
	public void constructEmpty() {
		new Context.ContextBuilder().build();
	}

}
