
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class NumberTesting {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testDefault()
	{
		System.out.println("Test Default");
	}
	
	@Test
	public void testStandard()
	{
		EnglishNumber number = new EnglishNumber();
		assertNotNull(number);
		
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
