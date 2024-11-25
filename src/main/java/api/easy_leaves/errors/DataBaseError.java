package api.easy_leaves.errors;

public class DataBaseError extends RuntimeException{
	public DataBaseError(String message) {
		super(message);
	}
}
