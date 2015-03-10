package typechecker;

import java.util.HashMap;
import java.util.Map;

import ast.type.Type;

public class TypeRepository {
	private final Map<String, Type> typeRepository; 
	private final Map<String, String> labelRepository; 
	
	public TypeRepository() {
		this.typeRepository = new HashMap<String, Type>();
		this.labelRepository = new HashMap<String, String>();
	}
	
	public void putID(String id, Type type) {
		typeRepository.put(id, type);
	}
	
	public void putIDLabel(String id, String label) {
		labelRepository.put(id, label);
	}
	
	public Map<String, Type> getTypeRepository() {
		return typeRepository;
	}
	
	public Map<String, String> getLabelRepository() {
		return labelRepository;
	}
	
	public boolean isDeclared(String id) {
		return typeRepository.containsKey(id);
	}
	
	public boolean isDeclaredLabel(String id) {
		return labelRepository.containsKey(id);
	}
	
	public Type getValue(String id) {
		if(isDeclared(id)) {
			return typeRepository.get(id);
		}
		return null;
	}
	
	public String getLabelValue(String id) {
		if(isDeclared(id)) {
			return labelRepository.get(id);
		}
		return null;
	}
	
	public boolean empty() {
		if(!this.getTypeRepository().isEmpty()) {
			return false;
		}
		return true;
	}
}
