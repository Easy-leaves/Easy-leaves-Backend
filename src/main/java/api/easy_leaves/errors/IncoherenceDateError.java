package api.easy_leaves.errors;

/**
 * Class représentant une exception levée lorsqu'une incohérence est détectée dans les dates fournies.
 * 
 * Cette exception est utilisée pour signaler des erreurs liées à des dates invalides
 * ou incohérentes, comme une date de début postérieure à une date de fin.
 *
 * @see RuntimeException
 */
public class IncoherenceDateError extends RuntimeException{
	
    /**
     * Constructeur.
     * 
     * @param message le message décrivant l'erreur d'incohérence de date.
     */
	public IncoherenceDateError(String message) {
		super(message);
	}
}
