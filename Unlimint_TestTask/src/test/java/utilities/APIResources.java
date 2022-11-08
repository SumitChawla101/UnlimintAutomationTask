package utilities;

public enum APIResources {
	
	GetUserAPI("api");
	
	private String resource;
	
	
     APIResources(String resource){
		
		this.resource=resource;
	}

	
	public String getResource() {
		
		return resource;
	}
}
