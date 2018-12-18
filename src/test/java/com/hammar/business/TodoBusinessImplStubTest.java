package com.hammar.business;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hammar.data.api.TodoService;
import com.hammar.data.api.TodoServiceStub;

public class TodoBusinessImplStubTest {
	
	private static TodoService todoServiceStub = new TodoServiceStub();
	private static TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceStub);
	private static List<String> filteredTodos;
	
	@BeforeClass
	public static void setup() {
		filteredTodos= todoBusinessImpl.retrieveTodosRelatedToSpring("dummy");
	}
	
	@Test
	public void testRetrieveTodosRelatedToSpring_SizeCheck() {
		assertEquals(3, filteredTodos.size());
	}
	
	// "Learn Spring MVC","Learn Spring","Learn Spring boot"
	@Test
	public void testRetrieveTodosRelatedToSpring_CorrectTodos() {
		assertEquals("Learn Spring MVC", filteredTodos.get(0));
		assertEquals("Learn Spring", filteredTodos.get(1));
		assertEquals("Learn Spring boot", filteredTodos.get(2));
	}

}
