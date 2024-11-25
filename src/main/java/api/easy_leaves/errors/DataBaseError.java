package api.easy_leaves.errors;

/**
 * Classe représentant une exception levée lorsqu'une erreur survient lors d'une interaction avec la base de données.
 * 
 * Cette exception est utilisée pour signaler des problèmes liés à l'accès ou à la manipulation des données. 
 * Elle hérite de {@link RuntimeException}.
 *
 * @see RuntimeException
 */
public class DataBaseError extends RuntimeException{
	
    /**
     * Constructeur.
     * 
     * @param message le message détaillant l'erreur survenue
     */
	public DataBaseError(String message) {
		super(message);
	}
}
