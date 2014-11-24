package de.lbl.purchasewatcher.gui;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import de.lbl.purchasewatcher.*;
import de.lbl.purchasewatcher.system.*;
import java.util.*;
import de.lbl.purchasewatcher.model.*;

public class ThingyViewFragment extends MainViewFragment
{
	
	View root;
	EditText nameText;
	EditText costText;
	View typeSpinner;
	View rankSpinner;

	Button okBtn;
	
	Purchase purchase;

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
		nameText = (EditText) App.findView(root, R.id.productNameEditText);
		costText = (EditText) App.findView(root, R.id.costEditText);
		typeSpinner = App.findView(root, R.id.typeSpinner);
		rankSpinner = App.findView(root, R.id.rankSpinner);
		okBtn = (Button) App.findView(root, R.id.okButton);


	}
	public void addToSpinner(Spinner spin, List<String> list)
	{
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(App.context, android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(dataAdapter);
	}
	
	
	

	@Override
	public void changeMode(Bundle data)
	{
//		String mode = data.getString(Constants.KEY_VIEW_MODE);
//		if(!this.mode.equals(mode)){
//			switch(mode){
//				case Constants.MODE_OVERVIEW:
//					adapt = new PurchaseOverviewAdapter(null);
//					root.setAdapter(adapt);
//					this.mode = mode;
//					break;
//				case Constants.MODE_DETAIL:
//					setPurchase(data);
//					adapt = new PurchaseDetailAdapter(purchase);
//					root.setAdapter(adapt);
//					this.mode = mode;
//					break;
//				case Constants.MODE_EDIT:
//					break;
//			}
//			setTitle(mode);
//		}
	}

	private void setPurchase(Bundle data)
	{
		purchase = SystemController.dataHandler.getPurchase(data.getInt(Constants.KEY_ID));
	}

	@Override
	public void updateView()
	{
		//adapt.update();
	}

	@Override
	public void handleActionbarClick(MenuItem item)
	{
		int id = item.getItemId();
//		switch(id){
//			case R.id.create_purchase:
//				createNewPurchase();
//				break;
//			case R.id.create_thingy:
//				createNewThingy();
//				break;
//			case R.id.delete_purchase:
//				deleteThisPurchase();
//				break;
//			case R.id.btn3:
//
//				break;
//		}
	}

	@Override
	public int getViewMenuLayoutId()
	{
		switch(mode){
			case Constants.MODE_OVERVIEW:
				return R.menu.viewmenu_purchase_overview;
			case Constants.MODE_DETAIL:
				return R.menu.viewmenu_purchase_detail;
			default:
				return super.getViewMenuLayoutId();
		}
	}

//	private void deleteThisPurchase()
//	{
//		// TODO: Implement this method
//		Bundle data = new Bundle();
//		int[] p = {purchase.id};
//		data.putIntArray(Constants.KEY_DATA_PURCHASE,p);
//		SystemController.tryAction(Constants.ACTION_DELETE_PURCHASE,data);
//		gui.back();
//	}
//
//	private void deleteSelectedPurchase()
//	{
//		Bundle data = new Bundle();
//		//TODO data.putIntArray();
//		int[] array = {purchase.id};
//		data.putIntArray(Constants.KEY_DATA_PURCHASE, array);
//		SystemController.tryAction(Constants.ACTION_DELETE_PURCHASE,data);
//	}
//
//	private void createNewPurchase()
//	{
//		SystemController.tryAction(Constants.ACTION_CREATE_PURCHASE, null);
//	}
//
//	private void createNewThingy()
//	{
//		Bundle b = new Bundle();
//		b.putInt(Constants.KEY_ID,purchase.id);
//		SystemController.tryAction(Constants.ACTION_CREATE_THINGY, b);
//	}

	@Override
	public void setUpFragment(Bundle data)
	{
		setPurchase(data);
	}
}
