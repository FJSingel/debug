
import java.util.ArrayList;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;


public class NumberTesting {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testHundredMillion()
	{
		//Also tests max
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
	}
	
	@Test
	public void testStandard()
	{
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
	public void testAllTokens()
	{
		
	}
        
	@Test
	public void testNumberToken()
	{
		NumberToken nt = new NumberToken("ten");
		
	}
	
	@After
	public void tearDown() throws Exception {
	}

}
