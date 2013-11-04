
import java.util.ArrayList;
import java.util.Map;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class NumberTesting {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testExitCode()
	{
		//Ensures ErrorManager returns correct exit codes
		ErrorManager emt = ErrorManager.getInstance();
		emt.setErrorOutput(System.err);
		assertEquals(0, emt.getExitCode());
		emt.error("Testing ExitCode");
		assertEquals(7, emt.getExitCode());
		emt.error("Testing ExitCode");
		assertEquals(7, emt.getExitCode());
	}
	
	@Test
	public void testStandard()
	{
		//Just very basic nominal use
		EnglishNumber number = new EnglishNumber();
		assertNotNull(number);
		ArrayList<String> words = new ArrayList<String>();
	    words.add("one");
	    words.add("hundred");
	    boolean success = number.initialize(words);
        assertTrue(success);
        System.out.println(number.toInt());
        assertEquals(100, number.toInt());   
	}
	
	@Test
	public void testMinEntry()
	{
		//Also tests min number
		EnglishNumber number = new EnglishNumber();
		assertNotNull(number);
		ArrayList<String> words = new ArrayList<String>();
	    words.add("minus");
		words.add("nine");
	    words.add("hundred");
	    words.add("ninety");
	    words.add("nine");
	    words.add("million");
	    words.add("nine");
	    words.add("hundred");
	    words.add("ninety");
	    words.add("nine");
	    words.add("thousand");
	    words.add("nine");
	    words.add("hundred");
	    words.add("ninety");
	    words.add("nine");
	    boolean success = number.initialize(words);
        assertTrue(success);
        System.out.println(number.toInt());
        assertEquals(-999999999, number.toInt());
	}
	
	@Test
	public void testReinitialize()
	{
		//Tests what happens if you initialize something again
		EnglishNumber number = new EnglishNumber();
		assertNotNull(number);
		ArrayList<String> words = new ArrayList<String>();
		words.add("one");
		words.add("hundred");
		boolean success = number.initialize(words);
        assertTrue(success);
        System.out.println(number.toInt());
        assertEquals(100, number.toInt());
        words.set(0, "twenty");
        words.set(1, "five");
        success = number.initialize(words);
        assertTrue(success);
        System.out.println(number.toInt());
        assertEquals(25, number.toInt());
	}
	
	@Test
	public void testDigitsAndNTY()
	{
		EnglishNumber number = new EnglishNumber();
		assertNotNull(number);
		ArrayList<String> words = new ArrayList<String>();
		words.add("one");
	    words.add("hundred");
	    words.add("twenty");
	    words.add("three");
	    words.add("million");
	    words.add("four");
	    words.add("hundred");
	    words.add("fifty");
	    words.add("six");
	    words.add("thousand");
	    words.add("seven");
	    words.add("hundred");
	    words.add("eighty");
	    words.add("nine");
	    boolean success = number.initialize(words);
        assertTrue(success);
        System.out.println(number.toInt());
        assertEquals(123456789, number.toInt());
        
        words = new ArrayList<String>();
		words.add("two");
	    words.add("hundred");
	    words.add("thirty");
	    words.add("one");
	    words.add("million");
	    words.add("five");
	    words.add("hundred");
	    words.add("sixty");
	    words.add("four");
	    words.add("thousand");
	    words.add("eight");
	    words.add("hundred");
	    words.add("ninety");
	    words.add("seven");
	    success = number.initialize(words);
        assertTrue(success);
        System.out.println(number.toInt());
        assertEquals(231564897, number.toInt());
        
        words = new ArrayList<String>();
		words.add("three");
	    words.add("hundred");
	    words.add("twelve");
	    words.add("million");
	    words.add("six");
	    words.add("hundred");
	    words.add("forty");
	    words.add("five");
	    words.add("thousand");
	    words.add("nine");
	    words.add("hundred");
	    words.add("seventy");
	    words.add("eight");
	    success = number.initialize(words);
        assertTrue(success);
        System.out.println(number.toInt());
        assertEquals(312645978, number.toInt());
		
	}
	
	@Test
	public void testTeens()
	{
		//Test every value in Teen.
		EnglishNumber number = new EnglishNumber();
		assertNotNull(number);
		ArrayList<String> words = new ArrayList<String>();
		Map<String, Integer> teenValues = TokenType.TEEN.getValues();

		words.add("");
	    for(Map.Entry<String,Integer> entry : teenValues.entrySet())
	    {
	    	words.set(0, entry.getKey());
	        assertTrue(number.initialize(words));
	        System.out.println(number.toInt());
	        assertEquals(10+entry.getValue(), number.toInt());
	    }
	}
	
	@Test
	public void testZeroes()
	{
		//Test the zeroes
		EnglishNumber number = new EnglishNumber();
		assertNotNull(number);
		ArrayList<String> words = new ArrayList<String>();
		words.add("naught");
        assertTrue(number.initialize(words));
        System.out.println(number.toInt());
        assertEquals(0, number.toInt());
        words.set(0, "zero");
        assertTrue(number.initialize(words));
        System.out.println(number.toInt());
        assertEquals(0, number.toInt());
	}
	
	@Test
	public void testNegatives()
	{
		//Test the negation values
		EnglishNumber number = new EnglishNumber();
		assertNotNull(number);
		ArrayList<String> words = new ArrayList<String>();
		words.add("negative");
		words.add("ten");
        assertTrue(number.initialize(words));
        System.out.println(number.toInt());
        assertEquals(-10, number.toInt());
        words.set(0, "minus");
        assertTrue(number.initialize(words));
        System.out.println(number.toInt());
        assertEquals(-10, number.toInt());
	}
        
	@Test
	public void testUpperCase()
	{
		//Ensure running uppercase breaks this properly (bad data)
		EnglishNumber number = new EnglishNumber();
		assertNotNull(number);
		ArrayList<String> words = new ArrayList<String>();
		words.add("Ten");
        assertFalse(number.initialize(words));
        assertEquals(Integer.MIN_VALUE, number.toInt());
	}
	
	@Test
	public void testGibberish()
	{
		//Ensure running gibberish breaks this properly (bad data)
		EnglishNumber number = new EnglishNumber();
		assertNotNull(number);
		ArrayList<String> words = new ArrayList<String>();
		words.add("ajfhadsjlfah");
        assertFalse(number.initialize(words));
        assertEquals(Integer.MIN_VALUE, number.toInt());
	}
	
	@Test
	public void testBlank()
	{
		//Ensure running a blank token breaks this properly (bad data)
		EnglishNumber number = new EnglishNumber();
		assertNotNull(number);
		ArrayList<String> words = new ArrayList<String>();
		words.add("");
        assertFalse(number.initialize(words));
        assertEquals(Integer.MIN_VALUE, number.toInt());
	}
	
	@Test
	public void testEmpty()
	{
		EnglishNumber number = new EnglishNumber();
		assertNotNull(number);
		ArrayList<String> words = new ArrayList<String>();
        assertFalse(number.initialize(words));
        assertEquals(Integer.MIN_VALUE, number.toInt());
	}
	
	@Test
	public void testMisordered()
	{
		//Ensure unexpected tokens breaks this properly (bad data)
		EnglishNumber number = new EnglishNumber();
		assertNotNull(number);
		ArrayList<String> words = new ArrayList<String>();
		words.add("hundred");
		words.add("five");
        assertFalse(number.initialize(words));
        assertEquals(Integer.MIN_VALUE, number.toInt());
	}
	
	
	@After
	public void tearDown() throws Exception {
	}

}
