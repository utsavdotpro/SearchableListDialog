package com.isolpro.library.searchablelistdialog;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.isolpro.library.searchablelistdialog.databinding.DialogSearchableListBinding;

import java.util.ArrayList;
import java.util.List;

public class SearchableListDialog<T> extends BottomSheetDialog {

  private final Context context;
  private final SearchableListAdapter<T> adapter;
  private String searchKeyword = "";
  private List<T> items;
  private DialogSearchableListBinding binding;
  private OnItemSelectedListener<T> onItemSelectedListener;
  private OnNothingSelectedListener onNothingSelectedListener;
  private SearchMatcher<T> searchMatcher;

  public SearchableListDialog(@NonNull Context context, @NonNull SearchableListAdapter<T> adapter) {
    super(context);

    this.context = context;
    this.adapter = adapter;

    instantiate();
    initialize();
    listen();
  }

  public void dismiss() {
    binding.metSearch.setText("");
    dismiss(false);
  }

  private void dismiss(boolean wasItemSelected) {
    if (!wasItemSelected && onNothingSelectedListener != null)
      onNothingSelectedListener.exec();

    super.dismiss();
  }

  private void instantiate() {
    binding = DialogSearchableListBinding.inflate(LayoutInflater.from(context));
    setItems(null);
  }

  private void initialize() {
    setContentView(binding.getRoot());

    binding.lvItems.setAdapter(adapter);
    binding.lvItems.setEmptyView(binding.mevItems);
  }

  private void listen() {
    binding.metSearch.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        searchKeyword = s.toString().toLowerCase();
        populateList();
      }

      @Override
      public void afterTextChanged(Editable s) {
      }
    });

    binding.lvItems.setOnItemClickListener((parent, view, position, id) -> {
      if (onItemSelectedListener != null)
        onItemSelectedListener.exec((T) binding.lvItems.getAdapter().getItem(position));

      dismiss(true);
    });
  }

  public void setItems(List<T> items) {
    this.items = items != null ? items : new ArrayList<>();
    populateList();
  }

  private void populateList() {
    if (searchKeyword.isEmpty() || searchMatcher == null) {
      adapter.setItems(items);
      return;
    }

    List<T> filteredItems = new ArrayList<>();

    for (T item : items) {
      if (searchMatcher.compare(item, searchKeyword))
        filteredItems.add(item);
    }

    adapter.setItems(filteredItems);
    adapter.notifyDataSetChanged();
  }

  public void setOnItemSelectedListener(OnItemSelectedListener<T> onItemSelectedListener) {
    this.onItemSelectedListener = onItemSelectedListener;
  }

  public void setOnNothingSelectedListener(OnNothingSelectedListener onNothingSelectedListener) {
    this.onNothingSelectedListener = onNothingSelectedListener;
  }

  public void setSearchMatcher(SearchMatcher<T> searchMatcher) {
    this.searchMatcher = searchMatcher;
  }

}
