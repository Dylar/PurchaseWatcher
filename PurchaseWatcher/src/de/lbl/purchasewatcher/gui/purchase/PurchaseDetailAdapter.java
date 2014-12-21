package de.lbl.purchasewatcher.gui.purchase;
import android.app.*;
import android.content.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import de.lbl.purchasewatcher.*;
import de.lbl.purchasewatcher.gui.GuiManager;
import de.lbl.purchasewatcher.model.*;
import de.lbl.purchasewatcher.system.*;
import java.util.*;
import android.os.*;

public class PurchaseDetailAdapter extends ViewAdapter implements OnClickListener
{
	@Override
	public void onClick(View v)
	{
		if (v.equals(showBtn))
		{
			showthingy = !showthingy;
			setShowThingyBtn(showthingy);
			notifyDataSetChanged();
		}
		else if (v.equals(editDateBtn))
		{
			DatePickerDialog d = new DatePickerDialog(GuiManager.manager.getCurrentActivity(), 
											datelistener,
											p.date.get(Calendar.YEAR), p.date.get(Calendar.MONTH), p.date.get(Calendar.DAY_OF_MONTH));
											d.show();
		}
	}

	Purchase p;
	List<Thingy> list = new ArrayList<Thingy>();
	View detailsView;
	TextView showBtn;
	TextView editDateBtn;
	DatePickerDialog.OnDateSetListener datelistener;
	boolean showthingy;


	public PurchaseDetailAdapter(Purchase pp)
	{
//		setPurchase(pp);
//		setPurchaseDetailView(pp);
//		this.list = SystemController.dataHandler.getAllThingyFrom(p);
//
//		datelistener = new DatePickerDialog.OnDateSetListener() {
//
//			public void onDateSet(DatePicker view, int year, 
//								  int monthOfYear, int dayOfMonth)
//			{
//				Calendar c = p.date;
//				c.set(year,monthOfYear,dayOfMonth,0,0);
//				
//				SystemController.dataHandler.updatePurchase(p);
//				update();
//				
//			}
//		};
	}
	
	public void setAndUpdatePurchase(Purchase pp){
		this.p = pp;
		detailsView = LayoutInflater.from(App.ctx).inflate(R.layout.fragment_purchase_details, null, false);
		TextView dateView = (TextView) App.findView(detailsView, R.id.date, R.id.text);
		dateView.setText(p.date.getTime().toString());
		editDateBtn = (TextView) App.findView(detailsView,R.id.date,R.id.okBtn);
		editDateBtn.setOnClickListener(this);
		
		showBtn = (TextView) App.findView(detailsView, R.id.show_thingy_btn);
		showBtn.setOnClickListener(this);
		setShowThingyBtn(true);
	}

	private void setShowThingyBtn(boolean on)
	{
		showBtn.setText(!on ? "Show" : "hide");
		showthingy = on;
	}

	@Override
	public int getCount()
	{
		return showthingy ? this.list.size() + 1 : 1;
	}

	@Override
	public Thingy getItem(int p1)
	{
		if (p1 == 0)
		{
			return null;
		}
		else
			return list.get(p1 - 1);
	}

	@Override
	public long getItemId(int p1)
	{
		return p1;
	}

	@Override
	public View getView(int pos, View p2, ViewGroup p3)
	{
		final Thingy t = getItem(pos);
		if (t == null)
		{
			return detailsView;
		}
		else
		{
			final TextView v = new TextView(App.ctx);
			v.setText("ID: " + t.id);
			v.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View p1)
					{
						Bundle b = new Bundle();
//						b.putInt(Constants.KEY_ID, t.id);
//						b.putString(Constants.KEY_VIEW_MODE,Constants.MODE_DETAIL);
//						b.putString(Constants.KEY_VIEW_NAME, Constants.VIEW_THINGY);
//						SystemController.tryAction(Constants.ACTION_DISPLAY_VIEW,b);
					}
			});
			return v;
		}
	}

	@Override
	public void update()
	{
//		this.list = SystemController.dataHandler.getAllThingyFrom(p);
		notifyDataSetChanged();
	}

}
