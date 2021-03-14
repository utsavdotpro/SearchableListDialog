package com.isolpro.library.searchablelistdialog;

public interface SearchMatcher<T> {
  boolean compare(T item, String keyword);
}
