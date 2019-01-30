package pa.iscde.codeRefactoringVisitor;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.eclipse.jface.text.ITextSelection;

import pt.iscte.pidesco.refactorMain.Nodeobject;

public class controlerVisitor {

	private ArrayList<Nodeobject> nodelist;

	private visitor visitor;

	public controlerVisitor(File file, ITextSelection textSelected) {
		nodelist = new ArrayList<Nodeobject>();
		run(file, textSelected);
	}

	/**
	 * Função principal desta classe responsavel por chamar o visitor que vai
	 * percorrer a classe a analizar e popular uma lista com as ocorrencias
	 * 
	 * Para alem de chamar o visitor ainda ordena a lista pela ordem que cada
	 * ocorrencia toma dentro da classe
	 * 
	 * @param file
	 * @param textSelected
	 */
	private void run(File file, ITextSelection textSelected) {
		visitor = new visitor(textSelected.getText(), nodelist);

		javaParser.parse(file, visitor);

		Collections.sort(nodelist, new Comparator<Nodeobject>() {

			@Override
			public int compare(Nodeobject arg0, Nodeobject arg1) {
				return arg0.getstartPosition() - arg1.getstartPosition();
			}
		});
	}
	/**
	 * Função auxiliar que devolve a lista com todas as ocorrencias encontradas
	 * 
	 */
	public ArrayList<Nodeobject> getList() {
		return nodelist;
	}

	public boolean getchangeClass() {
		return visitor.getchangeClass();
	}

}
