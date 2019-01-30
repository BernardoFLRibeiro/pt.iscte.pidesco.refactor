package pt.iscte.pidesco.refactorMain;

public class Nodeobject {

	private int lineNumber, startPosition;
	/**
	 * Estrutura criada para guardar os dados necessarios de cada ocorrencia de
	 * forma a ser possivel substituir mais tarde e não de cada vez que ha um match
	 * entre uma ocorrencia e o texto selecionado
	 * 
	 * @param lineNumber
	 * @param startPosition
	 */
	public Nodeobject(int lineNumber, int startPosition) {
		this.lineNumber = lineNumber;
		this.startPosition = startPosition;
	}

	public int getstartPosition() {
		return startPosition;
	}

	public int getLineNumber() {
		return lineNumber;
	}

}
