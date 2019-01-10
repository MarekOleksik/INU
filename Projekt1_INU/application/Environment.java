package application;

public class Environment {
	public String name;
	
	public Environment() {
	}

	public Environment(String name) {
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
}
