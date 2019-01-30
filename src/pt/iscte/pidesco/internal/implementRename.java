package pt.iscte.pidesco.internal;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.jface.text.ITextSelection;

import pa.iscde.codeRefactoringVisitor.controlerVisitor;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
import pt.iscte.pidesco.refactor.Activator;
import pt.iscte.pidesco.refactor.Nodeobject;

public class implementRename implements refactoringServices {

	private JavaEditorServices javaServ;

	@Override
	public void rename(String newName) {
		
		javaServ = Activator.getJavaEditorServices();
		File f = javaServ.getOpenedFile();

		ITextSelection iText = javaServ.getTextSelected(f);
		String textSelected = iText.getText();
		if (!(textSelected.isEmpty()) && !(textSelected.equals(null))) {

			
			controlerVisitor ctrlVisitor = new controlerVisitor(f, iText);
			
			 writeinFile(ctrlVisitor.getList(), iText, newName);
			
			if(ctrlVisitor.getchangeClass()) {
				
				String path = f.getAbsolutePath();
				path = path.replace(textSelected + ".java", newName + ".java");
				File nfile = new File(path);
				f.renameTo(nfile);
				
			}
			javaServ.saveFile(javaServ.getOpenedFile());
			
			
			
			
		}
	}

	private void writeinFile(ArrayList<Nodeobject> list, ITextSelection iText, String text) {
		int diff = 0;
		int itextLength = iText.getText().length();
		int textLength = text.length();
		boolean first = true;

		for (Nodeobject n : list) {
			if (!first) {
				diff = diff + textLength - itextLength;
			}
			int position = n.getstartPosition();

			javaServ.insertText(javaServ.getOpenedFile(), text, position + diff, iText.getLength());
			first = false;
		}

		//javaServ.saveFile(javaServ.getOpenedFile());
	}

}