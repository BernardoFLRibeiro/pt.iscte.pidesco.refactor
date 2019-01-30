package pt.iscte.pidesco.refactorMain;

import java.io.File;
import java.util.Map;

import org.eclipse.jface.text.ITextSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import pt.iscte.pidesco.extensibility.PidescoView;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;

public class RefactorView implements PidescoView {
	private JavaEditorServices javaServ;

	/**
	 * Classe responsavel por fazer a view desta componente e por chamar o serviço
	 * de rename, quando o utilizador carrega no botão Rename, atraves do listener.
	 * 
	 * Só chama a função se o utilizador escreveu alguma coisa na caixa de texto e
	 * selecionou algo, caso contrario mostra uma caixa de texto com uma mensagem de
	 * erro
	 * 
	 * 
	 * @param viewArea
	 * @param imageMap
	 */
	@Override
	public void createContents(Composite viewArea, Map<String, Image> imageMap) {

		Label text = new Label(viewArea, SWT.NONE);
		text.setText("Refactoring View");

		viewArea.setLayout(new RowLayout(SWT.VERTICAL));

		Text textBox = new Text(viewArea, 20);
		Button button = new Button(viewArea, SWT.PUSH);
		button.setText(" Rename");

		javaServ = Activator.getJavaEditorServices();

		button.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// Activator.getRefService().rename(textBox.getText());
				File f = javaServ.getOpenedFile();
				ITextSelection iText = javaServ.getTextSelected(f);
				String textSelected = iText.getText();

				boolean iTextisValid = (!(textSelected.isEmpty()) && !(textSelected.equals(null)));
				boolean textisNotEmpty = !(textBox.getText().isEmpty());
				String error = "";

				if (textisNotEmpty) {
					if (iTextisValid) {
						Activator.getRefService().rename(textBox.getText());
					} else {
						error = "Não selecionou corretamente!";
					}
				} else {
					error = "Não escreveu uma palavra!";

				}

				if (!(error.isEmpty())) {
					MessageBox box = new MessageBox(viewArea.getShell(), SWT.OK);
					box.setText("Error");
					box.setMessage(error);
					box.open();
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

	}

}
