package com.hammar.mockito;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class HamcrestMatchersTest {

	@Test
	public void testHamcrestMatchers_List() {
		
		List<Integer> scores = Arrays.asList(99,100,101,105);
		
		//is size 4
		assertThat(scores, hasSize(4));
		assertThat(scores, hasItems(99,100));
		
		//Condition on every item
		assertThat(scores, everyItem(greaterThan(90)));
		assertThat(scores, everyItem(lessThan(120)));
		
	}
	
	@Test
	public void testHamcrestMatchers_Strings() {
		
		assertThat("", isEmptyString());
		assertThat("", isEmptyOrNullString());
		
		
	}
	@Test
	public void testHamcrestMatchers_Arrays() {
		Integer [] scores = {1,4,7,9};
		
		assertThat(scores, arrayWithSize(4));
		assertThat(scores, arrayContaining(1,4,7,9));
		assertThat(scores, arrayContainingInAnyOrder(4,1,9,7));
		
		
	}

}
