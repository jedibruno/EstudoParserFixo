import java.util.HashMap;

public class ConvertRomantoStringOfInt {
   
    /**
     * Converts a String of roman numerals to Integer.
     *
     * @param  romanString  A String of roman numerals.
     * @return romanToInt  The conversion of the String of roman numerals to Integer.
     */
    
    public static String RomanToInt(String romanString)
    {
    	 //First, it checks if romanString follows the rules.
    	if(!ControlRepetitions.controlRepetitions(romanString)) {
    		return null;
    	////Second, it converts to int.	
    	} else { 
    		
    		HashMap<Character, Integer> romanHash = new HashMap<Character, Integer>();
        	romanHash.put('I',1);
        	romanHash.put('X',10);
        	romanHash.put('C',100);
        	romanHash.put('M',1000);
        	romanHash.put('V',5);
        	romanHash.put('L',50);
        	romanHash.put('D',500);
        	
        	int romanToInt = 0;
        	int previousChar = 0;
        	
        	for(int i = romanString.length()-1; i >=0; i--) {
        		
        		int checkedChar = romanHash.get(romanString.charAt(i));
        		if(checkedChar < previousChar)
        			romanToInt -= checkedChar;
        		else
        			romanToInt += checkedChar;
        		previousChar = checkedChar;
        	}
        	return Integer.toString(romanToInt);
    	}
    }
 }
