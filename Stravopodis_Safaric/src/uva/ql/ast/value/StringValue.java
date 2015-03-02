package uva.ql.ast.value;

public class StringValue extends GenericValue<String> {

	private String value;
	
	public StringValue(String _value){
		this.value = _value;
	}
	
	@Override
	public String getValue() {
		return this.value;
	}
	
	@Override
	public int intValue() {return 0;}
	
	@Override
	public float floatValue() {return 0;}
}