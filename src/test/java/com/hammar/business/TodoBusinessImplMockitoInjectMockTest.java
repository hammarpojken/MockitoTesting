package com.hammar.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.hammar.data.api.TodoService;

@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplMockitoInjectMockTest {
	
	@Mock
	TodoService todoServiceMock;
	
	@InjectMocks
	TodoBusinessImpl todoBusinessImpl;
	
	@Captor
	ArgumentCaptor<String> argCaptor;
	
	@Test
	public void testRetrieveTodosRelatedToSpring_SizeCheck() {

		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance",
				"Go shopping for shoes", "Learn Spring boot");

		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);

		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

		assertEquals(3, filteredTodos.size());

	}

	@Test
	public void testRetrieveTodosRelatedToSpring_SizeCheck2() {

		List<String> todos = Arrays.asList();

		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);

		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

		assertEquals(0, filteredTodos.size());

	}

	// "Learn Spring MVC","Learn Spring","Learn Spring boot"
	@Test
	public void testRetrieveTodosRelatedToSpring_CorrectTodos() {
	
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance",
				"Go shopping for shoes", "Learn Spring boot");

		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);

		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

		assertEquals("Learn Spring MVC", filteredTodos.get(0));
		assertEquals("Learn Spring", filteredTodos.get(1));
		assertEquals("Learn Spring boot", filteredTodos.get(2));
	}
	
	@Test
	public void testRetrieveTodosRelatedToSpring_usingBDD() {
		
		//Given
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance",
				"Go shopping for shoes", "Learn Spring boot");
		
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		
		//When
		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		
		//Then
		assertThat(filteredTodos.size(), is(3));

	}
	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD() {
		
		//Given
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance",
				"Go shopping for shoes", "Learn Spring boot");
		
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		
		//When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		//Then
		//verify(todoServiceMock).deleteTodo("Go shopping for shoes");
		then(todoServiceMock).should().deleteTodo("Go shopping for shoes");;
		
		//verify(todoServiceMock).deleteTodo("Learn to dance");
		then(todoServiceMock).should().deleteTodo("Learn to dance");
		
		//verify(todoServiceMock, never()).deleteTodo("Learn Spring MVC");
		then(todoServiceMock).should(never()).deleteTodo("Learn Spring MVC");
		
		//verify(todoServiceMock, times(1)).deleteTodo("Learn to dance");
		then(todoServiceMock).should(times(1)).deleteTodo("Learn to dance");
		
	}
	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD_CaptureArguments() {
		//Given
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance",
				"Learn Spring boot");
		
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		
		//When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		//Then
		then(todoServiceMock).should().deleteTodo(argCaptor.capture());
		
		assertThat(argCaptor.getValue(), is("Learn to dance"));
		
		
	}

}
