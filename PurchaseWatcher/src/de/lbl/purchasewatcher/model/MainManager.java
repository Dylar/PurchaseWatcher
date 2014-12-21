package de.lbl.purchasewatcher.model;

import android.os.Bundle;
import de.lbl.purchasewatcher.system.Storeable;

public interface MainManager<T extends Storeable>
{
	public void createNew(Bundle b);
	public  T load(long id);
	public  T reload(T s);
	public void save(T s);
	public void delete(T s);
}
