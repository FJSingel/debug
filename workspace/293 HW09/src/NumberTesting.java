
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
	    assertTrue(number.initialize(words));
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
	    assertTrue(number.initialize(words));
        assertEquals(-999999999, number.toInt());
	}
	
	@Test
	public void testReinitialize()
	{
		//Tests reinitialization (bad data)
		EnglishNumber number = new EnglishNumber();
		assertNotNull(number);
		ArrayList<String> words = new ArrayList<String>();
		words.add("one");
		words.add("hundred");
        assertTrue(number.initialize(words));
        assertEquals(100, number.toInt());
        words.set(0, "five");
        assertFalse(number.initialize(words));
        assertEquals(100, number.toInt());	//make sure number hasn't changed
	}
	
	@Test
	public void testDigitsAndNTY()
	{
		//tests characters 1-9 and 20,30,40,50,60,70,80,90
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
	    assertTrue(number.initialize(words));
        assertEquals(123456789, number.toInt());
        
        EnglishNumber number2 = new EnglishNumber();
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
	    assertTrue(number2.initialize(words));
        assertEquals(231564897, number2.toInt());
        

        EnglishNumber number3 = new EnglishNumber();
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
	    assertTrue(number3.initialize(words));
        assertEquals(312645978, number3.toInt());
		
	}
	
	@Test
	public void testTeens()
	{
		//Test every value in Teen.
		ArrayList<String> words = new ArrayList<String>();
		Map<String, Integer> teenValues = TokenType.TEEN.getValues();

		words.add("");
	    for(Map.Entry<String,Integer> entry : teenValues.entrySet())
	    {
	    	EnglishNumber number = new EnglishNumber();
	    	assertNotNull(number);
	    	words.set(0, entry.getKey());
	        assertTrue(number.initialize(words));
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
        assertEquals(0, number.toInt());
        
        words.set(0, "zero");
        EnglishNumber zeroNumber = new EnglishNumber();
        assertTrue(zeroNumber.initialize(words));
        assertEquals(0, zeroNumber.toInt());
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
        assertEquals(-10, number.toInt());
        
        words.set(0, "minus");
        EnglishNumber minusNumber = new EnglishNumber();
        assertTrue(minusNumber.initialize(words));
        assertEquals(-10, minusNumber.toInt());
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
		//Ensure unexpected tokens breaks this properly (bad data)
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
	
	@Test
	public void testTooLarge()
	{
		//Tests numbers that are too large
		EnglishNumber number = new EnglishNumber();
		assertNotNull(number);
		ArrayList<String> words = new ArrayList<String>();
		//9,099,999,999
		words.add("nine");
	    words.add("thousand");
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
	    assertFalse(number.initialize(words));
        assertEquals(Integer.MIN_VALUE, number.toInt());
	}
	
	
	@After
	public void tearDown() throws Exception {
	}

}
