package pt.iscte.pidesco.services;

/**
 * Serviço disponibilizado pela componente de Code Refactor
 * 
 * @author luiz4
 *
 */
public interface refactoringServices {
	/**
	 * Função a chamar quando se quer fazer rename de alguma componente de uma
	 * classe que esteja aberta.
	 * 
	 * A string newName é o novo nome que o utilizador quer que a componente tome
	 * 
	 * @param newName
	 */
	void rename(String newName);

}
