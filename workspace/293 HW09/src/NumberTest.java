import junit.framework.TestCase;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class NumberTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testStandard()
	{
		EnglishNumber number = new EnglishNumber();
		
	}
	
	public void testNumberToken()
	{
		NumberToken nt = new NumberToken("ten");
		
	}
	
	public void testTokenType()
	{
		NumberToken nt = new NumberToken("ten");
		//TokenType tt = new TokenType("");
		
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
