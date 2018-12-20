package com.hammar.mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SpyExampleTest {
	
	//Using a spy instead allows you to stub and override methods
	// the spy is treated as a real object of the "spied" class
	// returns real values instead of default values like the mock
	
	@Test
	public void test() {
		List arrayListSpy = spy(ArrayList.class);
		assertEquals(0,arrayListSpy.size());
		
		arrayListSpy.add("Test");
		assertEquals(1, arrayListSpy.size());
		verify(arrayListSpy).add("Test");
		
		stub(arrayListSpy.size()).toReturn(5);
		assertEquals(5, arrayListSpy.size());
	}

}
