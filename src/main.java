import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import utils.Constants;

public class main {

	public static void main(String[] args) throws IOException {
		
		File file = new File(Constants.FILE);
		Scanner scan = new Scanner(file);
		FileWriter writer = new FileWriter(Constants.WRITER);
		HashMap<String, String> conversor = new HashMap<String, String>();
		HashMap<String, Double> valorMetais = new HashMap<String, Double>();
		Parser parser = new Parser();
		
		while (scan.hasNextLine()) {
			String currentLine = scan.nextLine();
			List<String> line = Arrays.asList(currentLine.split(" "));
			
			//Checks initial entries and counts the "representa" String
			try {
				parser.representa(currentLine, writer, conversor);
			} catch (Exception e) {
				System.out.println(e);
				break;
		    } 
			//Checks for String "valem" and stores the value of each metal
			parser.valem(currentLine, writer, conversor, line, valorMetais);
			
			//Checks for String "quanto vale" and writes the appropriate message
			parser.quantoVale(currentLine, writer, conversor, line);

			//Checks for String "quantos créditos são" and writes the appropriate message
			parser.quantosCreditos(currentLine, writer, conversor, line, valorMetais);
		}
		scan.close();
		writer.close();
	}
}


