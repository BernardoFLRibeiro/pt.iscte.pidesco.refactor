package pt.iscte.pidesco.services;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.jface.text.ITextSelection;

import pa.iscde.codeRefactoringVisitor.controlerVisitor;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
import pt.iscte.pidesco.refactorMain.Activator;
import pt.iscte.pidesco.refactorMain.Nodeobject;

public class implementRename implements refactoringServices {

	private JavaEditorServices javaServ;

	/**
	 * Função disponibilizada pelo serviço
	 * 
	 * A String newName contem a palavra que deve ser usada para substituir todas as
	 * ocorrencias.
	 * 
	 * A variavel iText indica qual a variavel, atributo, paramentro, metodo ou
	 * classe que deve ser renomeada
	 * 
	 * 
	 * Depois de chamar a unica função axuilar, desta classe, vai confirmar se é
	 * ncessario alterar o nome do ficheiro, ou seja, se foi mudado o nome da
	 * classe.
	 * 
	 * @param newName
	 * 
	 */
	@Override
	public void rename(String newName) {

		javaServ = Activator.getJavaEditorServices();
		File f = javaServ.getOpenedFile();

		ITextSelection iText = javaServ.getTextSelected(f);
		String textSelected = iText.getText();

		controlerVisitor ctrlVisitor = new controlerVisitor(f, iText);

		writeinFile(ctrlVisitor.getList(), iText, newName);

		if (ctrlVisitor.getchangeClass()) {

			String path = f.getAbsolutePath();
			path = path.replace(textSelected + ".java", newName + ".java");
			File nfile = new File(path);
			f.renameTo(nfile);
		}
		javaServ.saveFile(javaServ.getOpenedFile());
	}

	/**
	 * Função auxiliar que é responsavel por pegar em todas as ocorrencias
	 * encontradas (contidas na list) e substituilas pela string newName
	 * 
	 * @param list
	 * @param iText
	 * @param newName
	 */
	private void writeinFile(ArrayList<Nodeobject> list, ITextSelection iText, String newName) {
		int diff = 0;
		int itextLength = iText.getText().length();
		int textLength = newName.length();
		boolean first = true;

		for (Nodeobject n : list) {
			if (!first) {
				diff = diff + textLength - itextLength;
			}
			int position = n.getstartPosition();
			javaServ.insertText(javaServ.getOpenedFile(), newName, position + diff, iText.getLength());
			first = false;
		}
	}

}