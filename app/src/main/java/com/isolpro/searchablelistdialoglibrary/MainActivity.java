package com.isolpro.searchablelistdialoglibrary;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.isolpro.library.searchablelistdialog.SearchableListDialog;
import com.isolpro.searchablelistdialoglibrary.databinding.ActivityMainBinding;

import java.util.Arrays;

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

    String[] names = "Amber Farmer,David Wiggins,Amber Mack,Zachary Gomez,Elizabeth Kelly,Theresa Sanchez,Susan Martinez,Jake Allen,Sonya Gordon,Terri Bentley,Billy Lewis,Nicholas King,John Brooks,John Kennedy,Caitlin Smith,Richard Rodriguez,Cheryl Collins,Vincent Ruiz,Dylan Pugh,David Huber,Rodney Ward,Devin Coleman,Christina Kennedy,Cameron Kirby,Helen Freeman,Matthew Richardson,Adam Anderson,Hannah Harrison,Christopher Clark,Troy Barber,Sarah Hernandez,Yesenia Miller,Clinton Perez,Beth Barnes,Shannon Dunn,Julie Franklin,Samuel Turner,Donna Brooks,Lindsey Juarez,Ashley Mccarty,Ruth Galloway,Tammy Warner,Ms. Heidi Turner MD,Willie Roberts,Dr. Eileen Duncan,Mary Hawkins,Joshua Hopkins,Scott Matthews,Kevin Clark,Lisa Huffman,Jesse Taylor,Molly Mclaughlin,Charles Gutierrez,Laura Hodge,Michael Long,Victoria Espinoza,Melissa Daniels,Jennifer Durham,Robert Jones,Judy Moreno,Michelle Torres,Andrew Good,Larry Crawford,Blake Nelson,Tyler Chavez,Thomas Herrera,Derrick Cohen,Anthony Thompson,Dr. Martin Ibarra,Blake Wood,Jeremiah Daniels,Sonia Maldonado,James Kelly,Theresa Duncan,Chad Estes,Dennis Jensen,Kevin Webster,Mary Moore,Gregory Rivers,Christopher Marshall,Albert Price,John Wilcox,Darius Tate,David Wilson,Shawn Mcdonald,Rebecca York,Lauren Garrett,Donna Morris,Danielle Harrison,Suzanne Harris,Lisa Anderson,Paul Reese,Linda Fields,Leslie Frey,Gloria Gomez,Melanie Haley,Jonathon Nelson,Phillip Taylor,Patricia Lane,Caitlin Johnson".split(",");

    dialogSearchableList.setItems(Arrays.asList(names));
  }
}