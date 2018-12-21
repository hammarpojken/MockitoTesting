package com.hammar.powermock;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.hammar.data.api.TodoService;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UtilityClass.class)
public class MockingStaticMethodTest {
	
	@Mock
	Dependency dependency;
	
	@InjectMocks
	SystemUnderTest systemUnderTest;

	@Test
	public void testRetrieveTodosRelatedToSpring_SizeCheck() {

		List<Integer> stats = Arrays.asList(1,2,3);

		when(dependency.retrieveAllStats()).thenReturn(stats);
		
		//setting up the class for mocking/stubing static method
		PowerMockito.mockStatic(UtilityClass.class);
		
		when(UtilityClass.staticMethod(6)).thenReturn(150);

		int result =systemUnderTest.methodCallingAStaticMethod();
		
		assertEquals(150, result);
		
		
		//verifying that a static method is called using PowerMock
		PowerMockito.verifyStatic();
		UtilityClass.staticMethod(6);
		

	}

	
}
