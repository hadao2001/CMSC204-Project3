
/**
 * This is a student-created JUnit test for the 
 * BasicDoubleLinkedList class
 * @author Ha T Dao
 * @param <StudentComparator>
 */

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SortedDoubleLinkedList_STUDENT_Test {
	SortedDoubleLinkedList<String> sortedLinkedString;
	SortedDoubleLinkedList<Double> sortedLinkedDouble;
	SortedDoubleLinkedList<Student> sortedLinkedStudent;
	StringComparator comparator;
	DoubleComparator comparatorD;
	StudentComparator comparatorStudent;

	
	public Student a=new Student("Kathy", 18, 98);
	public Student b=new Student("Adam", 34, 70);
	public Student c=new Student("Mary", 21, 89);
	public Student d=new Student("David", 23, 85);
	public Student e=new Student("Claire", 19, 95);
	public Student f=new Student("Henry", 20, 43);
	
	@Before
	public void setUp() throws Exception {
		comparator = new StringComparator();
		sortedLinkedString = new SortedDoubleLinkedList<String>(comparator);

		comparatorD = new DoubleComparator();
		sortedLinkedDouble = new SortedDoubleLinkedList<Double>(comparatorD);

		comparatorStudent = new StudentComparator();
		sortedLinkedStudent = new SortedDoubleLinkedList<Student>(comparatorStudent);

	}

	@After
	public void tearDown() throws Exception {
		comparator = null;
		comparatorD = null;
		comparatorStudent = null;
		sortedLinkedString = null;
		sortedLinkedDouble = null;
		sortedLinkedStudent = null;
	}

	@Test
	public void testAddToEnd() {
		try {
			sortedLinkedString.addToEnd("Hello");
			assertTrue("Did not throw an UnsupportedOperationException", false);
		} catch (UnsupportedOperationException e) {
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		} catch (Exception e) {
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testAddToFront() {
		try {
			sortedLinkedString.addToFront("Hello");
			assertTrue("Did not throw an UnsupportedOperationException", false);
		} catch (UnsupportedOperationException e) {
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		} catch (Exception e) {
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testIteratorSuccessfulNext() {
		sortedLinkedStudent.add(a);
		sortedLinkedStudent.add(b);
		sortedLinkedStudent.add(c);
		sortedLinkedStudent.add(d);
		ListIterator<Student> iterator = sortedLinkedStudent.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(a, iterator.next());
		assertEquals(c, iterator.next());
		assertEquals(b, iterator.next());
		assertEquals(true, iterator.hasNext());

	}

	@Test
	public void testIteratorSuccessfulStringPrevious() {
		sortedLinkedString.add("Begin");
		sortedLinkedString.add("World");
		sortedLinkedString.add("Hello");
		sortedLinkedString.add("End");
		ListIterator<String> iterator = sortedLinkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Begin", iterator.next());
		assertEquals("End", iterator.next());
		assertEquals("Hello", iterator.next());
		assertEquals("World", iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals("World", iterator.previous());
		assertEquals("Hello", iterator.previous());
		assertEquals("End", iterator.previous());
	}

	@Test
	public void testIteratorSuccessfulStudentPrevious() {
		sortedLinkedStudent.add(e);
		sortedLinkedStudent.add(c);
		sortedLinkedStudent.add(b);
		sortedLinkedStudent.add(d);
		// Alphabetic order: e f a c b d
		ListIterator<Student> iterator = sortedLinkedStudent.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(e, iterator.next());
		assertEquals(c, iterator.next());
		assertEquals(b, iterator.next());
		assertEquals(d, iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals(d, iterator.previous());
		assertEquals(b, iterator.previous());
		assertEquals(c, iterator.previous());
	}

	@Test
	public void testIteratorSuccessfulDoubleNext() {
		sortedLinkedDouble.add(new Double(8));
		sortedLinkedDouble.add(new Double(6));
		sortedLinkedDouble.add(new Double(1));
		sortedLinkedDouble.add(new Double(2));
		ListIterator<Double> iterator = sortedLinkedDouble.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(new Double(1), iterator.next());
		assertEquals(new Double(2), iterator.next());
		assertEquals(new Double(6), iterator.next());
		assertEquals(true, iterator.hasNext());
	}

	@Test
	public void testIteratorSuccessfulDoublePrevious() {
		sortedLinkedDouble.add(new Double(5));
		sortedLinkedDouble.add(new Double(10));
		sortedLinkedDouble.add(new Double(6));
		sortedLinkedDouble.add(new Double(2));
		ListIterator<Double> iterator = sortedLinkedDouble.iterator();
		assertEquals(new Double(2), iterator.next());
		assertEquals(new Double(5), iterator.next());
		assertEquals(new Double(6), iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals(new Double(8), iterator.previous());
		assertEquals(true, iterator.hasPrevious());
	}

	@Test
	public void testIteratorNoSuchElementException() {
		sortedLinkedStudent.add(e);
		sortedLinkedStudent.add(c);
		sortedLinkedStudent.add(b);
		sortedLinkedStudent.add(d);
		// Alphabetic order: e f a c b d
		ListIterator<Student> iterator = sortedLinkedStudent.iterator();

		assertEquals(true, iterator.hasNext());
		assertEquals(d, iterator.next());
		assertEquals(b, iterator.next());
		assertEquals(e, iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals(c, iterator.next());
		try {
			//Empty list, should throw exception
			iterator.next();
			assertTrue("Did not throw a NoSuchElementException", false);
		} catch (NoSuchElementException e) {
			assertTrue("Successfully threw a NoSuchElementException", true);
		} catch (Exception e) {
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
	}

	@Test
	public void testIteratorUnsupportedOperationExceptionString() {
		sortedLinkedStudent.add(e);
		sortedLinkedStudent.add(c);
		sortedLinkedStudent.add(b);
		sortedLinkedStudent.add(d);
		
		ListIterator<Student> iterator = sortedLinkedStudent.iterator();

		try {
			//Remove is not supported for the iterator, should throw unsupported exception
			iterator.remove();
			assertTrue("Did not throw a UnsupportedOperationException", false);
		} catch (UnsupportedOperationException e) {
			assertTrue("Successfully threw a UnsupportedOperationException", true);
		} catch (Exception e) {
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testAddStudent() {
		// Alphabetic order: e f a c b d
		sortedLinkedStudent.add(a);
		sortedLinkedStudent.add(b);
		sortedLinkedStudent.add(c);
		assertEquals(a, sortedLinkedStudent.getFirst());
		assertEquals(b, sortedLinkedStudent.getLast());
		sortedLinkedStudent.add(d);
		sortedLinkedStudent.add(e);
		assertEquals(e, sortedLinkedStudent.getFirst());
		assertEquals(d, sortedLinkedStudent.getLast());
		// Deletes last element
		assertEquals(d, sortedLinkedStudent.retrieveLastElement());
		assertEquals(b, sortedLinkedStudent.getLast());
	}

	@Test
	public void testRemoveFirstStudent() {
		// Alphabetic order: e f a c b d
		sortedLinkedStudent.add(b);
		sortedLinkedStudent.add(c);
		assertEquals(c, sortedLinkedStudent.getFirst());
		assertEquals(b, sortedLinkedStudent.getLast());
		sortedLinkedStudent.add(a);
		assertEquals(a, sortedLinkedStudent.getFirst());
		// Remove first item from thel ist
		sortedLinkedStudent.remove(a, comparatorStudent);
		assertEquals(c, sortedLinkedStudent.getFirst());
	}

	@Test
	public void testRemoveEndStudent() {
		// Alphabetic order: e f a c b d
		sortedLinkedStudent.add(b);
		sortedLinkedStudent.add(f);
		assertEquals(f, sortedLinkedStudent.getFirst());
		assertEquals(b, sortedLinkedStudent.getLast());
		sortedLinkedStudent.add(d);
		assertEquals(d, sortedLinkedStudent.getLast());
		// Removes from the end of the list
		sortedLinkedStudent.remove(d, comparatorStudent);
		assertEquals(b, sortedLinkedStudent.getLast());
	}

	@Test
	public void testRemoveMiddlePatient() {
		// Alphabetic order: e f a c b d
		sortedLinkedStudent.add(a);
		sortedLinkedStudent.add(b);
		assertEquals(a, sortedLinkedStudent.getFirst());
		assertEquals(b, sortedLinkedStudent.getLast());
		sortedLinkedStudent.add(f);
		assertEquals(f, sortedLinkedStudent.getFirst());
		assertEquals(b, sortedLinkedStudent.getLast());
		assertEquals(3, sortedLinkedStudent.getSize());
		// Remove from middle of list
		sortedLinkedStudent.remove(a, comparatorStudent);
		assertEquals(f, sortedLinkedStudent.getFirst());
		assertEquals(b, sortedLinkedStudent.getLast());
		assertEquals(2, sortedLinkedStudent.getSize());
	}

	private class StringComparator implements Comparator<String> {

		@Override
		public int compare(String arg0, String arg1) {
			return arg0.compareTo(arg1);
		}

	}

	private class DoubleComparator implements Comparator<Double> {

		@Override
		public int compare(Double arg0, Double arg1) {

			return arg0.compareTo(arg1);
		}

	}

	private class StudentComparator implements Comparator<Student> {

		@Override
		public int compare(Student arg0, Student arg1) {
			// Just put Patients in alphabetic order by name
			return arg0.getName().compareTo(arg1.getName());
		}
	}
	
	private class Student{
		String name;
		int age;
		int studentNumber;
		
		public Student(String name, int age, int studentNumber){
			this.name = name;
			this.age = age;
			this.studentNumber = studentNumber;
		}
		
		public String getName(){
			return name;
		}
		public int getAge(){
			return age;
		}
		public int getStudentNumber(){
			return studentNumber;
		}
		
		public String toString() {
			return (getName()+" "+getAge()+" "+getStudentNumber());
		}
	}
}
