package com.hammar.mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyInt;

import java.util.List;

import org.junit.Test;

public class JavaListMockTest {

	@Test
	public void testListMock_SizeCheck() {
		List listMock = mock(List.class);
		
		//Matcher for arguments
		when(listMock.size()).thenReturn(5);
		
		assertEquals(5, listMock.size());
	}
	
	@Test
	public void testListMock_SizeCheckMultiple() {
		List listMock = mock(List.class);
		
		//Matcher for arguments
		when(listMock.size()).thenReturn(5).thenReturn(3).thenReturn(1);
		
		assertEquals(5, listMock.size());
		assertEquals(3, listMock.size());
		assertEquals(1, listMock.size());
	}
	
	@Test
	public void testListMock_Get() {
		List listMock = mock(List.class);
		
		//Matcher for arguments
		when(listMock.get(0)).thenReturn("Stubbing the value");
		
		assertEquals("Stubbing the value", listMock.get(0));
	}
	
	@Test(expected=RuntimeException.class)
	public void testListMock_ThrowException() {
		List listMock = mock(List.class);
		
		//Matcher for arguments
		when(listMock.get(anyInt())).thenThrow(new RuntimeException("Success"));
		
		listMock.get(234);
		
	}

}
