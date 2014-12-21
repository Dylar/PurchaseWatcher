package de.lbl.purchasewatcher.model;

import android.content.Intent;
import android.os.Bundle;
import de.lbl.purchasewatcher.system.App;
import de.lbl.purchasewatcher.system.Constants;
import de.lbl.purchasewatcher.system.Storeable;

public class ThingyManager implements MainManager<Thingy>
{
	public static ThingyManager	manager;
	private DataManager				dh;


	public ThingyManager()
	{
		dh = DataManager.manager;
	}


	@Override
	public void save(Thingy t)
	{
		dh.update(t);

		Intent intent = new Intent(Constants.BROADCAST_UPDATED_THINGY);
		intent.putExtra(Storeable.VAR_DATABASE_ID, t.getDatabaseId());
		App.ctx.sendBroadcast(intent);
	}


	@Override
	public Thingy load(long id)
	{
		return dh.getThingyById(id);
	}


	@Override
	public Thingy reload(Thingy s)
	{
		return Thingy.FACTORY_THINGY.reloadFromDatabase(s);
	}


	@Override
	public void createNew(Bundle b)
	{
		Thingy t = new Thingy();
		t.setPurchaseId(b.getInt(Storeable.VAR_DATABASE_ID));
		dh.update(t);

		Intent intent = new Intent(Constants.BROADCAST_NEW_THINGY);
		intent.putExtra(Storeable.VAR_DATABASE_ID, t.getDatabaseId());
		App.ctx.sendBroadcast(intent);
	}


	@Override
	public void delete(Thingy t)
	{
		dh.deleteThingy(t.getDatabaseId());

		Intent intent = new Intent(Constants.BROADCAST_DELETED_THINGY);
		intent.putExtra(Storeable.VAR_DATABASE_ID, t.getDatabaseId());
		App.ctx.sendBroadcast(intent);
	}
}
