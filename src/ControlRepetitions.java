
public class ControlRepetitions {

	/**
     * Checks if repetition of roman numerals is allowed.
     *
     * @param  romanString a String of roman numerals.
     * @return 'true' if repetitions follows the rules 'false' if not.
     */
    public static boolean controlRepetitions(String romanString)
    {
        boolean control = true;
       
        if(romanString.contains("DD") || romanString.contains("VV") || romanString.contains("LL") || romanString.contains("IIII") || romanString.contains("XXXX") || romanString.contains("CCCC") || romanString.contains("MMMM"))
           	control = false;
        	
        	
        return control;
    }
}