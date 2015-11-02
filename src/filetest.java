import java.io.*;
import java.util.*;


public class filetest {

	public static void main(String[] argv)
	{
		File file = null;
		file = new File("test.txt");
		Scanner scanner = null;
		try{
			scanner =  new Scanner(file);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("file not found");
					System.exit(0);
		}
		
		try{
		while(scanner.hasNext())
		{
			String i = scanner.nextLine();
			System.out.println(i);
		}
		}
		catch(InputMismatchException e)
		{
			System.out.println("mismatch" + e);
		}
		
	}
}
