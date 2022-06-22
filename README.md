[![](https://jitpack.io/v/utsavdotpro/SearchableListDialog.svg)](https://jitpack.io/#utsavdotpro/SearchableListDialog)
# SearchableListDialog
Bottom Dialog Searchable List built with Material.io with custom search bar and custom list item view.

## Sample
<img  src="./samples/preview1.jpg" height="350">

## Features

 - Works with literally any type of data
 - One-on-one comparison
 - Auto empty view
 - Portable
 - Clean UI
 - Pre-optimized
 - Easy to use
 - Lightweight

---

## Implementation
**Step 1:** Add to project level build.gradle

```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

**Step 2:** Add to app level build.gradle

```gradle
dependencies {
  implementation 'com.github.utsavdotpro:SearchableListDialog:VERSION'
}
```

---

## How to use?
In this example, I am creating a <i>dialog search list for a list of customers with their name and business name</i>. You can easily understand how it works from the example and implement according to your requirements.    

- You can use any kind of object to populate and search for in the list
- Here, I have created a simple object `Customer` with basic setters and getters

> You can also check out the [example app](/app/src/main/java/com/utsavdotpro/searchabledialog/MainActivity.java)


### Creating List Adapter
SearchableListAdapter is built from BaseAdapter with only addition of `setItems()` and `getItems()` methods

```java
public class CustomersSearchableListAdapter extends SearchableListAdapter<Customer> {
  private final Context context;
  private List<Customer> customerList = new ArrayList<>();

  public CustomersSearchableListAdapter(Context context) {
    this.context = context;
  }

  @Override
  public int getCount() {
    return customerList.size();
  }

  @Override
  public Customer getItem(int position) {
    return customerList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return customerList.get(position).getId();
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

    ListCustomerItemBinding binding = ListCustomerItemBinding.inflate(LayoutInflater.from(context));

    return new ViewHolder(binding);
  }

  @Override
  public List<Customer> getItems() {
    return customerList;
  }

  @Override
  public void setItems(List<Customer> customerList) {
    this.customerList = customerList;
    notifyDataSetChanged();
  }

  public class ViewHolder {

    private final ListCustomerItemBinding binding;

    public ViewHolder(ListCustomerItemBinding binding) {
      this.binding = binding;
    }

    public ListCustomerItemBinding getBinding() {
      return binding;
    }

    public void populateView(Customer customer) {
      binding.tvName.setText(customer.getName());
      binding.tvBusinessName.setText(customer.getBusinessName());
    }
  }
}
```

### Creating Dialog

```java
private CustomersSearchableListAdapter customersListAdapter;
private SearchableListDialog<Customer> dialogListCustomers;

customersListAdapter = new CustomersSearchableListAdapter(activity);
dialogListCustomers = new SearchableListDialog<>(activity, customersListAdapter);
```

### Setting Items
Careful! Make sure you set the items to you `dialogListCustomers` and not `customersListAdapter`

```java
    dialogListCustomers.setItems(customerList);
```

### Defining Search Matcher
Since SLD doesn't work with any specific object type, you have to define your own matcher(). This gives SLD its portability and you freedom to compare any way you like.

```java
dialogListCustomers.setSearchMatcher((customer, keyword) -> {
  return customer.getName().toLowerCase().contains(keyword);
  // return true if matched, else false
});
```

### Listening to Item Selection

```java
dialogListCustomers.setOnItemSelectedListener(customer -> {
  // to whatever you like with the selected item (customer in this case)
});
```

---

> P.s. If you need me to write a better documentation, ping me at contact@utsav.pro
