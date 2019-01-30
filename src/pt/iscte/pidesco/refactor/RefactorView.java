package pt.iscte.pidesco.refactor;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import pt.iscte.pidesco.extensibility.PidescoView;
import pt.iscte.pidesco.projectbrowser.model.SourceElement;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserListener;

public class RefactorView implements PidescoView {

	@Override
	public void createContents(Composite viewArea, Map<String, Image> imageMap) {

		Text text = new Text(viewArea, SWT.BORDER);
		text.setText("Refactoring View");

		viewArea.setLayout(new RowLayout(SWT.VERTICAL));

		Activator.getProjectBrowserServices().addListener(new ProjectBrowserListener.Adapter() {

			@Override
			public void doubleClick(SourceElement element) {
				new Label(viewArea, SWT.NONE).setText(element.getName());
				viewArea.layout();
				super.doubleClick(element);
			}
		});

		Text textBox = new Text(viewArea, 20);
		Button button = new Button(viewArea, SWT.PUSH);
		button.setText("Field Rename");

		button.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!textBox.getText().isEmpty()) {
					Activator.getRefService().rename(textBox.getText());
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

	}

}
