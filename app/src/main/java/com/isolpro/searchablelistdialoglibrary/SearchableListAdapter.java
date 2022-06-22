package com.isolpro.searchablelistdialoglibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.isolpro.searchablelistdialoglibrary.databinding.RvListItemBinding;

import java.util.ArrayList;
import java.util.List;

public class SearchableListAdapter extends com.isolpro.library.searchablelistdialog.SearchableListAdapter<String> {

  private final Context context;
  private List<String> items = new ArrayList<>();

  public SearchableListAdapter(Context context) {
    this.context = context;
  }

  @Override
  public int getCount() {
    return items.size();
  }

  @Override
  public String getItem(int position) {
    return items.get(position);
  }

  @Override
  public long getItemId(int position) {
    // don't use random number for id, I am only using it for the sake of this example
    return (long) (Math.random() * (999999 - 99999 + 1) + 99999);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder holder = createNewOrGetExistingViewHolder(convertView);

    holder.populateView(getItem(position));

    convertView = holder.getBinding().getRoot();
    convertView.setTag(holder);

    return convertView;
  }

  private ViewHolder createNewOrGetExistingViewHolder(View view) {
    if (view != null)
      return (ViewHolder) view.getTag();

    RvListItemBinding binding = RvListItemBinding.inflate(LayoutInflater.from(context));

    return new ViewHolder(binding);
  }

  @Override
  public List<String> getItems() {
    return items;
  }

  @Override
  public void setItems(List<String> categories) {
    this.items = categories;
    notifyDataSetChanged();
  }

  public class ViewHolder {

    private final RvListItemBinding binding;

    public ViewHolder(RvListItemBinding binding) {
      this.binding = binding;
    }

    public RvListItemBinding getBinding() {
      return binding;
    }

    public void populateView(String item) {
      binding.tvText.setText(item);
    }
  }
}
