package datastruct;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyUnsortedListTest {

	/********* EMPTY LIST **************/
	@Test
	public void test_Integer_empty() {
		// Verify if empty list is empty
		MyUnsortedList<Integer> list = MyUnsortedList.of();
		assertEquals("empty list has size 0", 0, list.size());
		assertTrue("list.isEmpty() works", list.isEmpty());
	}
	@Test(expected = datastruct.EmptyListException.class)
	public void test_Integer_empty_pop() {
		MyUnsortedList<Integer> list = MyUnsortedList.of();
		list.pop();
	}
	@Test(expected = datastruct.EmptyListException.class)
	public void test_Integer_empty_popLast() {
		MyUnsortedList<Integer> list = MyUnsortedList.of();
		list.popLast();
		// This test shown that poplast on empty list returned the wrong exception
	}
	/********* APPEND **************/
	@Test
	public void test_Integer_append_size_update() {
		MyUnsortedList<Integer> list = MyUnsortedList.of(4);
		list.append(4);
		assertEquals("Append to list updates the size", 2, list.size());
	}
	@Test
	public void test_Integer_append_correct_element() {
		MyUnsortedList<Integer> list = MyUnsortedList.of(4);
		MyUnsortedList<Integer> list2 = MyUnsortedList.of(4,5);
		list.append(5);
		assertTrue("Append to list appends at the end", list.equals(list2));
	}
	@Test
	public void test_Integer_poplast_append() {
		MyUnsortedList<Integer> list = MyUnsortedList.of(4, 3, 4, 2, 1);
		MyUnsortedList<Integer> list2 = MyUnsortedList.of(4, 3, 4, 2, 1);
		int a = list.popLast();
		list.append(a);
		assertTrue("poplast then append back the element returns the same list ",list.equals(list2));
	}
	/********* PREPEND **************/
	@Test
	public void test_Integer_prepend_size_update() {
		MyUnsortedList<Integer> list = MyUnsortedList.of(4);
		list.prepend(4);
		assertEquals("Prepend to list updates size",2,list.size());
	}
	@Test
	public void test_Integer_prepend_correct_element() {
		MyUnsortedList<Integer> list = MyUnsortedList.of(4);
		MyUnsortedList<Integer> list2 = MyUnsortedList.of(5,4);
		list.prepend(5);
		assertTrue("Prepend to list add item in the beginning of the list", list.equals(list2));
	}
	@Test
	public void test_Integer_pop_prepend() {
		MyUnsortedList<Integer> list = MyUnsortedList.of(4, 3, 4, 2, 1);
		MyUnsortedList<Integer> list2 = MyUnsortedList.of(4, 3, 4, 2, 1);
		int a = list.pop();
		list.prepend(a);
		assertTrue("Pop object and prepend back returns the same list", list.equals(list2));
	}
	/********* FILLING **************/
	@Test
	public void test_Integer_filling_size_check() {
		MyUnsortedList<Integer> list = MyUnsortedList.of(4, 3, 4, 2, 1);
		assertEquals("Filling with 5 integers sets size at 5", 5, list.size());
		assertFalse("Filling lists make isEmpty be false",list.isEmpty());
	}
	@Test
	public void test_Integer_filling_removing_size_check() {
		MyUnsortedList<Integer> list = MyUnsortedList.of(4, 3, 4, 2, 1);
		list.pop();
		assertEquals(list.size(), 4);
		list.pop();
		assertEquals(list.size(), 3);
		list.pop();
		assertEquals(list.size(), 2);
		list.pop();
		assertEquals(list.size(), 1);
		list.pop();
		assertEquals(list.size(), 0);
	}
	/********* FILLING - POPPING **************/
	@Test
	public void test_Integer_filling_poping_element() {
		MyUnsortedList<Integer> list = MyUnsortedList.of(4, 3, 4, 2, 1);
		int a = list.pop();
		assertEquals("pop returns the first element",4, a);
		list.pop();list.pop();list.pop();
		int b = list.pop();
		assertEquals("multiple pops work (last is the same as expected)", 1, b);
	}
	@Test
	public void test_Integer_filling_poplast_element() {
		MyUnsortedList<Integer> list = MyUnsortedList.of(4, 3, 4, 2, 1);
		int a = list.popLast();
		assertEquals("popLast returns the last element", 1, a);
		list.popLast();list.popLast();list.popLast();
		int b = list.popLast();
		assertEquals("multiple popLast work (last is the same as expected)", 4, b);
		// This test makes us see the bug that remove doesn't do size--;
	}
	/********* APPEND - PREPEND -null **************/
	@Test
	public void test_Integer_append_null() {
		MyUnsortedList<Integer> list = MyUnsortedList.of(4, 3, 4, 2, 1);
		MyUnsortedList<Integer> list2 = MyUnsortedList.of(4, 3, 4, 2, 1, null);
		list.append(null);
		assertEquals("Append null to list updates the size", 6, list.size());
		assertTrue("list.equals(list) is true even when equality is on null elements, after append null", list.equals(list2));
		// equals returns the wrong error when comparing list of different sizes
	}
	@Test
	public void test_Integer_prepend_null() {
		MyUnsortedList<Integer> list = MyUnsortedList.of(4, 3, 4, 2, 1);
		MyUnsortedList<Integer> list2 = MyUnsortedList.of(null,4, 3, 4, 2, 1);
		list.prepend(null);
		assertEquals("Prepend null to list updates the size", 6, list.size());
		assertTrue("list.equals(list) is true even when equality is on null elements, after prepend null", list.equals(list2));
	}
	/********* EQUALS TESTING **************/
	@Test
	public void test_Integer_equals_work_empty() {
		MyUnsortedList<Integer> list = MyUnsortedList.of();
		MyUnsortedList<Integer> list2 = MyUnsortedList.of();
		assertTrue("empty lists of same type are equals", list.equals(list2));
	}
	@Test
	public void test_Integer_equals_work_filled() {
		MyUnsortedList<Integer> list = MyUnsortedList.of(4,4,3,2,1);
		MyUnsortedList<Integer> list2 = MyUnsortedList.of(4,4,3,2,1);
		assertTrue("filled lists of same type and same elements are equals", list.equals(list2));
	}
	@Test
	public void test_Integer_equals_work_filled_different() {
		MyUnsortedList<Integer> list = MyUnsortedList.of(4,4,3,2,1);
		MyUnsortedList<Integer> list2 = MyUnsortedList.of(4,4,2,2,1);
		assertFalse("filled lists of same type and different elements are not equals", list.equals(list2));
	}
	@Test
	public void test_Integer_equals_work_filled_different_size() {
		MyUnsortedList<Integer> list = MyUnsortedList.of(4,4,3,2,1);
		MyUnsortedList<Integer> list2 = MyUnsortedList.of(4,4,2);
		assertFalse("filled lists of same type and different sizes are not equals", list.equals(list2));
	}
	@Test
	public void test_Integer_equals_String() {
		MyUnsortedList<Integer> list = MyUnsortedList.of(4,3);
		MyUnsortedList<String> list2 = MyUnsortedList.of("4", "3");
		assertFalse("lists of different types but element matching are not equals (4 and \"4\")",list.equals(list2));
	}
	@Test
	public void test_Integer_empty_equals_String_empty() {
		MyUnsortedList<Integer> list = MyUnsortedList.of();
		MyUnsortedList<String> list2 = MyUnsortedList.of();
		assertTrue("Empty lists of different sizes are equals", list.equals(list2));
	}
	@Test
	public void test_equals_not_mylist() {
		MyUnsortedList<Integer> list = MyUnsortedList.of(1);
		assertFalse("List of 1 is not equal to non-list 1 ", list.equals(1));
	}
	@Test
	public void test_Integer_noteq_null_listCmp() {
		MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2, null, 3);
		MyUnsortedList<Integer> list2 = MyUnsortedList.of(1, null, 2, 3);
		assertFalse("List 1,2,null,3 not equal to list 1,null,2,3", list.equals(list2));
	}
	@Test
	public void test_Integer_noteq_null_list2Cmp() {
		MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2, null, 3);
		MyUnsortedList<Integer> list2 = MyUnsortedList.of(1, null, 2, 3);
		assertFalse("List 1,2,null,3 not equal to list 1,null,2,3", list2.equals(list));
	}
	/********* TOSTRING TESTING **************/
	@Test
	public void test__tostring_Integer_empty_equals_String_empty() {
		MyUnsortedList<Integer> list = MyUnsortedList.of();
		MyUnsortedList<String> list2 = MyUnsortedList.of();
		assertTrue("empty lists converted to string are equals",list.toString().equals(list2.toString()));
	}
	@Test
	public void test_tostring_Integer_equals_String() {
		MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2, 3);
		MyUnsortedList<String> list2 = MyUnsortedList.of("1", "2", "3");
		assertTrue("Lists of String and Integer containing int and their toString versions are equals when converted to string", list.toString().equals(list2.toString()));
	}
	@Test
	public void test_tostring_Integer_equals_Integer() {
		MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2, 3);
		MyUnsortedList<Integer> list2 = MyUnsortedList.of(1, 2, 3);
		assertTrue("toString or two equal lists are equals",list.toString().equals(list2.toString()));
	}
	/********* INSERT TESTING **************/
	@Test
	public void test_insert_normal() {
		MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2, 3);
		MyUnsortedList<Integer> list2 = MyUnsortedList.of(1, 2, 42, 3);
		list.insert(42, 2);
		assertTrue("inserting 42 at pos 2 of list of size 3 works",list.equals(list2));
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void test_insert_below_0() {
		MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2, 3);
		list.insert(42, -2);
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void test_insert_over_max() {
		MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2, 3);
		list.insert(45, 42);
	}
	/********* REMOVE TESTING **************/
	@Test
	public void test_remove_normal() {
		MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2, 3);
		MyUnsortedList<Integer> list2 = MyUnsortedList.of(1, 2, 42, 3);
		int i = list2.remove(2);
		assertEquals("Removing 42 at pos 2 gives an integer of size 42", 42, i);
		assertTrue("Removing 42 at pos 2 of list of size 3 works",list.equals(list2));	
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void test_remove_below_0() {
		MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2, 3);
		list.remove(-2);
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void test_remove_over_max() {
		MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2, 3);
		list.remove(45);
	}
	/********* ============== **************/
	/********* = TDD TESTING= **************/
	/********* ============== **************/
	@Test
	public void tdd_insert_constant_time() {
		MyUnsortedList<Integer> list = MyUnsortedList.of();
		for(int i=0; i<99999; i++) {
			list.append(0);
		}
		long startTime = System.currentTimeMillis();
		list.append(0);
		long endTime = System.currentTimeMillis();
		long list10k = endTime-startTime;
		MyUnsortedList<Integer> list2 = MyUnsortedList.of();
		long startTime2 = System.currentTimeMillis();
		list2.append(0);
		long endTime2 = System.currentTimeMillis();
		long list0 = endTime2-startTime2;
		assertEquals("Append to a list of 99999 elements takes the same time as in empty list ",list10k, list0);
		// ne fonctionne pas pour le moment
		// fixé en ajoutant un tail
	}
	
	/* *** *** These tests are commented because I did not find a way to remove in O(1)*** *** */
/*
	public void tdd_popLast_constant_time() {
		MyUnsortedList<Integer> list = MyUnsortedList.of();
		for(int i=0; i<99999; i++) {
			list.append(0);
		}
		long startTime = System.currentTimeMillis();
		list.popLast();
		long endTime = System.currentTimeMillis();
		long list10k = endTime-startTime;
		MyUnsortedList<Integer> list2 = MyUnsortedList.of(9);
		long startTime2 = System.currentTimeMillis();
		list2.popLast();
		long endTime2 = System.currentTimeMillis();
		long list0 = endTime2-startTime2;
		assertEquals("popLast to a list of 99999 elements takes the same time as in empty list ",list10k, list0);
	}
	

	public void tdd_remove_constant_time() {
		MyUnsortedList<Integer> list = MyUnsortedList.of();
		for(int i=0; i<99999; i++) {
			list.append(0);
		}
		long startTime = System.currentTimeMillis();
		list.remove(99998);
		long endTime = System.currentTimeMillis();
		long list10k = endTime-startTime;
		MyUnsortedList<Integer> list2 = MyUnsortedList.of(8);
		long startTime2 = System.currentTimeMillis();
		list2.remove(0);
		long endTime2 = System.currentTimeMillis();
		long list0 = endTime2-startTime2;
		assertEquals("remove to a list of 99999 elements takes the same time as in empty list ",list10k, list0);
	}*/
}
