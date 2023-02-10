package sv.com.bandesal.pruebatecnica.exceptions;

public class ModelNotFoundException extends RuntimeException {

	public ModelNotFoundException(String message){
		super(message);
	}
}