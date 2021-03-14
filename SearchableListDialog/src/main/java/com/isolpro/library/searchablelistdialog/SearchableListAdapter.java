package com.isolpro.library.searchablelistdialog;

import android.widget.BaseAdapter;

import java.util.List;

public abstract class SearchableListAdapter<T> extends BaseAdapter {

  public abstract List<T> getItems();

  public abstract void setItems(List<T> items);

}
