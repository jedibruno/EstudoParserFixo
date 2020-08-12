
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import utils.Constants;

public class Parser {

	private int countRepresenta = 0;
	private int flag = 0;
	
	/**
	This method checks whether the String "representa" is found more than seven times
	and writes the error message if it does. 
	@param currentLine the line that is being read by the scanner;
	@param writer the writer object
	@param conversor the HashMap with the alien/roman dictionary 
	
	*/
	public void representa(String currentLine, FileWriter writer, HashMap<String, String> conversor) throws IOException, RuntimeException {
		if (currentLine.contains("representa")) {
			
			this.countRepresenta++;
			
			//If dictionary has more than 7 entries
			if (this.countRepresenta > Constants.LIMIT_REPRESENTA) {
				writer.write(Constants.INVALID_ENTRY);
				throw new RuntimeException();
			}
			String[] checagemMoeda = currentLine.split(" ");
			conversor.put(checagemMoeda[0], checagemMoeda[2]);
		}
	}
	
	/**
	This method checks whether the String "valem" is found at currentLine and, if it does,
	feeds the valorMetais hashMap. 
	@param currentLine the line that is being read by the scanner;
	@param writer the writer object;
	@param conversor the HashMap with the alien/roman dictionary;
	@param valorMetais the HashMap with the metal/value dictionary; 
	
	*/

	//Observação: tendo em vista que a descrição do problema não fez referência à necessidade de
	//traduzir entre PT-BR e ENG, esse método foi construído levando em conta que os valores a 
	//serem armazenados em valorMetais serão "Gold" e "Silver" e não "Ouro" e "Prata".
	
	public void valem(String currentLine, FileWriter writer, HashMap<String, String> conversor,
			List<String> line, HashMap<String, Double> valorMetais) throws IOException {
		if (currentLine.contains("valem")) {
			List<String> subarray = line.subList(0, 2);
			String alienToRoman = "";
			
			  for(String alienValue : subarray) {
			  
				  String romanValue = conversor.get(alienValue); 
			  //If key is not located, writes error
				  if(romanValue == null) {
			  		  writer.write(Constants.INVALID_ENTRY); 
					 break; 
				  } 
				  alienToRoman += romanValue; 
			  }
			  String convertedValue = ConvertRomantoStringOfInt.RomanToInt(alienToRoman);
				if (convertedValue == null) {
					writer.write(Constants.INVALID_ENTRY);
				} else {
					double convertedValueDouble = Double.parseDouble(convertedValue);					
					String[] arrayValorMetais = currentLine.split(" ");
					valorMetais.put(arrayValorMetais[2], Double.parseDouble(arrayValorMetais[4])/convertedValueDouble);
				}
		}
	}
	/**
	This method checks whether the String "quanto vale" is found at currentLine and, if it does,
	writes the value of alien in common language. 
	@param currentLine the line that is being read by the scanner;
	@param writer the writer object;
	@param conversor the HashMap with the alien/roman dictionary ;
	@param line the List made from the line String; 
	
	*/
		
	public void quantoVale(String currentLine, FileWriter writer, HashMap<String, String> conversor, List<String> line) throws IOException {
		if (currentLine.contains("quanto vale")) {
			List<String> subarray = line.subList(2, 6);

			String alienToRoman = "";

			for (String alienValue : subarray) {

				String romanValue = conversor.get(alienValue);
				//If key is not located, writes error
				if (romanValue == null) {
				
					writer.write(Constants.INVALID_ENTRY);
					flag = 1;
					break;
				}
				alienToRoman += romanValue;
			}
			if (flag != 1) {
				convertValue(alienToRoman, writer, subarray);
			}
		}
	}
	public static void convertValue(String alienToRoman, FileWriter writer, List<String> subarray) throws IOException {
		String convertedValue = ConvertRomantoStringOfInt.RomanToInt(alienToRoman);

		if (convertedValue == null) {
			writer.write(Constants.INVALID_ENTRY);
		} else {
			String alienValues = String.join(" ", subarray);
			String valueOne = (alienValues + " vale " + convertedValue + " \n");
			writer.write(valueOne);
		}
	}
	/**
	This method checks whether the String "quantos créditos são" is found at currentLine and, if it does,
	writes the value of the metal in credits
	@param currentLine the line that is being read by the scanner;
	@param writer the writer object;
	@param conversor the HashMap with the alien/roman dictionary; 
	@param line the List made from the line String;
	@param valorMetais the HashMap with the metal/value dictionary; 
	*/
		
	public void quantosCreditos(String currentLine, FileWriter writer, HashMap<String, String> conversor,
			List<String> line, HashMap<String, Double> valorMetais) throws IOException {
		if (currentLine.contains("quantos créditos são")) {
			List<String> subarray = line.subList(3, 5);

			String alienToRoman = "";

			for (String alienValue : subarray) {

				String romanValue = conversor.get(alienValue);
				if (romanValue == null) {

					writer.write(Constants.INVALID_ENTRY);
					break;
				}
				alienToRoman += romanValue;
			}
			String convertedValue = ConvertRomantoStringOfInt.RomanToInt(alienToRoman);
			double convertedValueToDouble = Double.parseDouble(convertedValue);
			double valorFinal = convertedValueToDouble*valorMetais.get(line.get(5)); 
			String alienValues = String.join(" ", subarray);
			String valueTwo = (alienValues +" " + line.get(5) + " custa " + valorFinal + " créditos" + " \n");
			writer.write(valueTwo);
		}
	}

}
