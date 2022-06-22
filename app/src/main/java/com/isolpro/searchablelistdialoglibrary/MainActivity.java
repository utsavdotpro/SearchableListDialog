package com.isolpro.searchablelistdialoglibrary;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.isolpro.library.searchablelistdialog.SearchableListDialog;
import com.isolpro.searchablelistdialoglibrary.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private ActivityMainBinding binding;
  private SearchableListDialog<String> dialogSearchableList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    // Open dialog on button click
    binding.bChooseItem.setOnClickListener(v -> dialogSearchableList.show());

    // Initiate SearchListDialog
    dialogSearchableList = new SearchableListDialog<>(this, new SearchableListAdapter(this));
    dialogSearchableList.setSearchMatcher((item, keyword) -> item.toLowerCase().contains(keyword));

    // Callback on item selection
    dialogSearchableList.setOnItemSelectedListener(item -> binding.bChooseItem.setText(item));

    // Callback on nothing selected
    dialogSearchableList.setOnNothingSelectedListener(() -> binding.bChooseItem.setText("Choose Item"));

    List<String> items = new ArrayList<>();
    items.add("A");
    items.add("B");

    dialogSearchableList.setItems(items);
  }
}