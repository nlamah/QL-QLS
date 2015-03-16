package nl.uva.se.gui.validators;

public class IntegerValidator extends Validator<String>{
	
	@Override
	public boolean isValid(String input) {
	    try {
	        Integer.parseInt( input );
	        return true;
	    }
	    catch( Exception e ) {
	        return false;
	    }
	}	
}
