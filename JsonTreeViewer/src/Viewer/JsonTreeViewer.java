package Viewer;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonTreeViewer extends TreeViewer implements ISelectionChangedListener {
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public JsonTreeViewer(Composite parent, int style, JsonObject moduleConfig) {
		super(parent, style);
		
		NamedObject namedObject = new NamedObject();

		Tree tree = getTree();
		setLabelProvider(new JsonLabelProvider());
		setContentProvider(new JsonContentProvider());

		namedObject.setName("ROOT");
		namedObject.setObject(moduleConfig);
		setInput(namedObject);
		
		addSelectionChangedListener(this);
	}


	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		IStructuredSelection thisSelection = (IStructuredSelection) event.getSelection();
		Object selectedNode = thisSelection.getFirstElement();

		NamedObject namedObject = (NamedObject) selectedNode; 
		JsonElement selectedElem = (JsonElement) namedObject.getObject();
			
		// Do different actions.
		if(selectedElem.isJsonArray()) {
			System.out.println("This is a JSON Array");
		} else if(selectedElem.isJsonObject() ) {
			System.out.println("This is a JSON Object");
		} else if(selectedElem.isJsonNull() ) {
			System.out.println("This is a JSON Null");
		} else if(selectedElem.isJsonPrimitive() ) {
			System.out.println("This is a JSON Primitive");
		} else {
			System.out.println("I don't know what this is.");
		}
		
	}
}
