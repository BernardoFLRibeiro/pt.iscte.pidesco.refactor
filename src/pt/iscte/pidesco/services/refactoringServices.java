package pt.iscte.pidesco.services;

/**
 * Servi�o disponibilizado pela componente de Code Refactor
 * 
 * @author luiz4
 *
 */
public interface refactoringServices {
	/**
	 * Fun��o a chamar quando se quer fazer rename de alguma componente de uma
	 * classe que esteja aberta.
	 * 
	 * A string newName � o novo nome que o utilizador quer que a componente tome
	 * 
	 * @param newName
	 */
	void rename(String newName);

}
