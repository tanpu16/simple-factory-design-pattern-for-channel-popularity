package channelpopularity.util;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.InvalidPathException;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	String path;
	StringBuffer finalResult=new StringBuffer(); 
	
	//parameterized constructor
	public Results(String FilePath) 
	{
		path = FilePath;
	}

	@Override
	public String toString()
	{
			return "Class Results [Path is -> "+path+"]";
	}
	
	/*store is void method,in which finalResult stores the final output in StringBuffer
	which we can write into the file and stdout
	@param str - rotated words or metrics values
	@return void
	@see print nothing but append the rotated word or append the metrics output
	*/
	public void store(String str)
	{
				
		finalResult.append(str);				
	
	}
	

	/*this is generic void method, which write output to output.txt or matrics.txt depends on
	the instace (WordRotator/MatricsCalculator) used to call this method.
	@param NA
	@return void
	@see write content of the StringBuffer into the file.
	*/
	public void writeToFile() throws IOException
	{
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path)));
		
		try
		{
			bw.write(finalResult.toString());
			bw.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		finally
		{
			bw.close();
		}

	}
	
	/*this is void method wich is used to write output to console.
	@param NA
	@return NA
	@see content of StringBuffer (rotated words or metrics output of input.txt file)
	 */
	public void writeToStdout()
	{
		System.out.println(finalResult);	
	}
}
