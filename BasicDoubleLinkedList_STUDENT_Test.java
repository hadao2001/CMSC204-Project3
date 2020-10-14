

/**
 * This is a student-created JUnit test for the 
 * BasicDoubleLinkedList class
 * @author Ha T Dao
 * @param <StudentComparator>
 */
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BasicDoubleLinkedList_STUDENT_Test {
	BasicDoubleLinkedList<String> linkedString;
	BasicDoubleLinkedList<Double> linkedDouble;
	BasicDoubleLinkedList<Student> linkedStudent;
	StringComparator comparator;
	DoubleComparator comparatorD;
	StudentComparator comparatorStudent;
	
	public Student a=new Student("Kathy", 18, 98);
	public Student b=new Student("Adam", 34, 70);
	public Student c=new Student("Mary", 21, 89);
	public Student d=new Student("David", 23, 85);
	public Student e=new Student("Claire", 19, 95);
	public Student f=new Student("Henry", 20, 43);


	public ArrayList<Student> fill = new ArrayList<Student>();
	

	@Before
	public void setUp() throws Exception {
		linkedString = new BasicDoubleLinkedList<String>();
		linkedString.addToEnd("Hello");
		linkedString.addToEnd("Viet Nam");
		comparator = new StringComparator();
		
		linkedDouble = new BasicDoubleLinkedList<Double>();
		linkedDouble.addToEnd(15.0);
		linkedDouble.addToEnd(100.0);
		comparatorD = new DoubleComparator();
		
		linkedStudent= new BasicDoubleLinkedList<Student>();
		linkedStudent.addToEnd(b);
		linkedStudent.addToEnd(c);
		comparatorStudent = new StudentComparator();
	}

	@After
	public void tearDown() throws Exception {
		linkedString = null;
		linkedDouble = null;
		linkedStudent = null;
		comparatorD = null;
		comparator = null;
	}

	@Test
	public void testGetSize() {
		assertEquals(2,linkedString.getSize());
		assertEquals(2,linkedDouble.getSize());
		assertEquals(2,linkedStudent.getSize());
	}
	
	@Test
	public void testAddToLast() {
		assertEquals("World", linkedString.getLast());
		linkedString.addToEnd("Last");
		assertEquals("Last", linkedString.getLast());
		
		assertEquals(c,linkedStudent.getLast());
		linkedStudent.addToEnd(d);
		assertEquals(d,linkedStudent.getLast());
	}
	
	@Test
	public void testAddToFront() {
		assertEquals("Hello", linkedString.getFirst());
		linkedString.addToFront("Front");
		assertEquals("Front", linkedString.getFirst());
		
		assertEquals(b,linkedStudent.getFirst());
		linkedStudent.addToFront(a);
		assertEquals(a,linkedStudent.getFirst());
	}
	
	@Test
	public void testGetFirst() {
		assertEquals("Hello", linkedString.getFirst());
		linkedString.addToFront("New");
		assertEquals("New", linkedString.getFirst());
		
		assertEquals(b,linkedStudent.getFirst());
		linkedStudent.addToFront(a);
		assertEquals(a,linkedStudent.getFirst());
	}

	@Test
	public void testGetLast() {
		assertEquals("World", linkedString.getLast());
		linkedString.addToEnd("New");
		assertEquals("New", linkedString.getLast());
		
		assertEquals(c,linkedStudent.getLast());
		linkedStudent.addToEnd(d);
		assertEquals(d,linkedStudent.getLast());
	}
	
	@Test
	public void testToArrayList()
	{
		ArrayList<Student> list;
		linkedStudent.addToFront(a);
		linkedStudent.addToEnd(d);
		list = linkedStudent.toArrayList();
		assertEquals(a,list.get(0));
		assertEquals(b,list.get(1));
		assertEquals(c,list.get(2));
		assertEquals(d,list.get(3));
	}
	
	@Test
	public void testIteratorSuccessfulNext() {
		linkedString.addToFront("Front");
		linkedString.addToEnd("Last");
		ListIterator<String> iterator = linkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Front", iterator.next());
		assertEquals("Hello", iterator.next());
		assertEquals("World", iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals("Last", iterator.next());
		
		linkedStudent.addToFront(a);
		linkedStudent.addToEnd(d);
		ListIterator<Student> iteratorStudent = linkedStudent.iterator();
		assertEquals(true, iteratorStudent.hasNext());
		assertEquals(a, iteratorStudent.next());
		assertEquals(b, iteratorStudent.next());
		assertEquals(c, iteratorStudent.next());
		assertEquals(true, iteratorStudent.hasNext());
		assertEquals(d, iteratorStudent.next());
	}
	
	@Test
	public void testIteratorSuccessfulPrevious() {
		linkedStudent.addToFront(a);
		linkedStudent.addToEnd(d);
		ListIterator<Student> iteratorStudent = linkedStudent.iterator();
		assertEquals(true, iteratorStudent.hasNext());
		assertEquals(a, iteratorStudent.next());
		assertEquals(b, iteratorStudent.next());
		assertEquals(c, iteratorStudent.next());
		assertEquals(d, iteratorStudent.next());
		assertEquals(true, iteratorStudent.hasPrevious());
		assertEquals(d, iteratorStudent.previous());
		assertEquals(c, iteratorStudent.previous());
		assertEquals(b, iteratorStudent.previous());
		assertEquals(a, iteratorStudent.previous());
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionNext() {
		linkedStudent.addToFront(a);
		linkedStudent.addToEnd(d);
		ListIterator<Student> iteratorStudent = linkedStudent.iterator();		
		assertEquals(true, iteratorStudent.hasNext());
		assertEquals(a, iteratorStudent.next());
		assertEquals(b, iteratorStudent.next());
		assertEquals(c, iteratorStudent.next());
		assertEquals(true, iteratorStudent.hasNext());
		assertEquals(d, iteratorStudent.next());
		
		try{
			//Empty list, should throw exception
			iteratorStudent.next();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
		
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionPrevious() {
		linkedStudent.addToFront(a);
		linkedStudent.addToEnd(d);
		ListIterator<Student> iteratorStudent = linkedStudent.iterator();		
		assertEquals(true, iteratorStudent.hasNext());
		assertEquals(a, iteratorStudent.next());
		assertEquals(b, iteratorStudent.next());
		assertEquals(c, iteratorStudent.next());
		assertEquals(d, iteratorStudent.next());
		assertEquals(true, iteratorStudent.hasPrevious());
		assertEquals(d, iteratorStudent.previous());
		assertEquals(c, iteratorStudent.previous());
		assertEquals(b, iteratorStudent.previous());
		assertEquals(a, iteratorStudent.previous());
		
		try{
			//Empty list, should throw excpetion
			iteratorStudent.previous();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
		
	}
	
	@Test
	public void testIteratorUnsupportedOperationException() {
		linkedStudent.addToFront(a);
		linkedStudent.addToEnd(d);
		ListIterator<Student> iteratorStudent = linkedStudent.iterator();		
		assertEquals(true, iteratorStudent.hasNext());
		assertEquals(a, iteratorStudent.next());
		assertEquals(b, iteratorStudent.next());
		assertEquals(c, iteratorStudent.next());
		assertEquals(true, iteratorStudent.hasNext());
		assertEquals(d, iteratorStudent.next());
		
		try{
			//Remove not supported, should throw exception
			iteratorStudent.remove();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
		
	}
	
	@Test
	public void testRemove() {
		// Removes first item
		assertEquals(b, linkedStudent.getFirst());
		assertEquals(c, linkedStudent.getLast());
		linkedStudent.addToFront(a);
		assertEquals(a, linkedStudent.getFirst());
		linkedStudent.remove(a, comparatorStudent);
		assertEquals(b, linkedStudent.getFirst());
		//Removes from end of the list
		linkedStudent.addToEnd(d);
		assertEquals(d, linkedStudent.getLast());
		linkedStudent.remove(d, comparatorStudent);
		assertEquals(c, linkedStudent.getLast());
		//Removes from middle of list
		linkedStudent.addToFront(a);
		assertEquals(a, linkedStudent.getFirst());
		assertEquals(c, linkedStudent.getLast());
		linkedStudent.remove(b, comparatorStudent);
		assertEquals(a, linkedStudent.getFirst());
		assertEquals(c, linkedStudent.getLast());
		
	}

	@Test
	public void testRetrieveFirstElement() {
		assertEquals(b, linkedStudent.getFirst());
		linkedStudent.addToFront(a);
		assertEquals(a, linkedStudent.getFirst());
		assertEquals(a, linkedStudent.retrieveFirstElement());
		assertEquals(b,linkedStudent.getFirst());
		assertEquals(b, linkedStudent.retrieveFirstElement());
		assertEquals(c,linkedStudent.getFirst());
		//Gets first item in list
		assertEquals("Hello", linkedString.getFirst());
		linkedString.addToFront("New");
		assertEquals("New", linkedString.getFirst());
		assertEquals("New", linkedString.retrieveFirstElement());
		assertEquals("Hello",linkedString.getFirst());
		assertEquals("Hello", linkedString.retrieveFirstElement());
		assertEquals("World",linkedString.getFirst());
		
	}

	@Test
	public void testRetrieveLastElement() {
		assertEquals(c, linkedStudent.getLast());
		linkedStudent.addToEnd(d);
		assertEquals(d, linkedStudent.getLast());
		assertEquals(d, linkedStudent.retrieveLastElement());
		assertEquals(c,linkedStudent.getLast());
		//Gets last item in list
		assertEquals("World", linkedString.getLast());
		linkedString.addToEnd("New");
		assertEquals("New", linkedString.getLast());
		assertEquals("New", linkedString.retrieveLastElement());
		assertEquals("World",linkedString.getLast());
	}

	private class StringComparator implements Comparator<String>
	{

		@Override
		public int compare(String arg0, String arg1) {
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class DoubleComparator implements Comparator<Double>
	{

		@Override
		public int compare(Double arg0, Double arg1) {
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class StudentComparator implements Comparator<Student>
	{

		@Override
		public int compare(Student arg0, Student arg1) {
			return arg0.toString().compareTo(arg1.toString());
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