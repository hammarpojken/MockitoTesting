package com.hammar.powermock;

import static org.junit.Assert.*;

import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

@RunWith(PowerMockRunner.class)
public class MockingPrivateMethodTest {

	@Mock
	Dependency dependency;

	@InjectMocks
	SystemUnderTest systemUnderTest;

	@Test
	public void testSystemUnderTest_StaticMethod() throws Exception {

		List<Integer> stats = Arrays.asList(1, 2, 3);

		when(dependency.retrieveAllStats()).thenReturn(stats);

		// Using whitebox to mock a private method
		long result = Whitebox.invokeMethod(systemUnderTest, "privateMethodUnderTest");

		assertEquals(6, result);

	}

}
