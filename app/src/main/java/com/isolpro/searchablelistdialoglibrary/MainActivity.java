package com.isolpro.searchablelistdialoglibrary;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.isolpro.library.searchablelistdialog.SearchableListDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    SearchableListDialog<String> dialogSearchableList = new SearchableListDialog<>(this, new SearchableListAdapter(this));

    dialogSearchableList.setSearchMatcher((item, keyword) -> item.toLowerCase().contains(keyword));

    dialogSearchableList.setOnItemSelectedListener(item ->
      Toast.makeText(this, item + " selected!", Toast.LENGTH_SHORT).show()
    );

    List<String> items = new ArrayList<>();

    items.add("A");
    items.add("B");

    dialogSearchableList.setItems(items);

    dialogSearchableList.show();
  }
}