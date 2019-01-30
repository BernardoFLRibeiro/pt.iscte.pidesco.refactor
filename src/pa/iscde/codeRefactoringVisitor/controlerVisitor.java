package pa.iscde.codeRefactoringVisitor;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.eclipse.jface.text.ITextSelection;

import pt.iscte.pidesco.refactor.Nodeobject;

public class controlerVisitor {

	private ArrayList<Nodeobject> nodelist;

	private visitor visitor;

	public controlerVisitor(File file, ITextSelection textSelected) {
		nodelist = new ArrayList<Nodeobject>();
		run(file, textSelected);
	}

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

	public boolean getchangeClass() {
		return visitor.getchangeClass();
	}

	public ArrayList<Nodeobject> getList() {
		return nodelist;
	}

}
