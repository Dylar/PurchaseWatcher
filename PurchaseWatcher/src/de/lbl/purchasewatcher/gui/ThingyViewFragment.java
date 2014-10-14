package de.lbl.purchasewatcher.gui;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import de.lbl.purchasewatcher.*;
import de.lbl.purchasewatcher.system.*;
import java.util.*;

public class ThingyViewFragment extends MainViewFragment
{
	
	View root;
	EditText nameText;
	EditText costText;
	View typeSpinner;
	View rankSpinner;

	Button okBtn;

	@Override
	public void setUpFragment(Bundle data)
	{
		//this.setArguments(data);
	}

	@Override
	public void setTitle(String mode)
	{
		switch(mode){
			case Constants.MODE_OVERVIEW:
				title = "Thingy overview";
				break;
			case Constants.MODE_DETAIL:
				title = "Thingy details";
				break;
			case Constants.MODE_EDIT:
				title = "Thingy editor";
				break;
		}
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		root = inflater.inflate(R.layout.fragment_thingy_editor, container, false);

		initLayout();

		return root;
	}

	private void initLayout()
	{
		nameText = App.findView(root, R.id.productNameEditText);
		costText = App.findView(root, R.id.costEditText);
		typeSpinner = App.findView(root, R.id.typeSpinner);
		rankSpinner = App.findView(root, R.id.rankSpinner);
		okBtn = App.findView(root, R.id.okButton);


	}
	public void addToSpinner(Spinner spin, List<String> list)
	{
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(App.context, android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(dataAdapter);
	}

}
