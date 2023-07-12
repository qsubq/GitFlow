// Generated by view binder compiler. Do not edit!
package com.example.search.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.search.R;
import com.google.android.material.textview.MaterialTextView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class NewsRecyclerViewItemLayoutBinding implements ViewBinding {
  @NonNull
  private final LinearLayoutCompat rootView;

  @NonNull
  public final AppCompatImageView imgNews;

  @NonNull
  public final LinearLayoutCompat linearLayoutMain;

  @NonNull
  public final MaterialTextView tvDate;

  @NonNull
  public final MaterialTextView tvDesc;

  @NonNull
  public final MaterialTextView tvTitle;

  private NewsRecyclerViewItemLayoutBinding(@NonNull LinearLayoutCompat rootView,
      @NonNull AppCompatImageView imgNews, @NonNull LinearLayoutCompat linearLayoutMain,
      @NonNull MaterialTextView tvDate, @NonNull MaterialTextView tvDesc,
      @NonNull MaterialTextView tvTitle) {
    this.rootView = rootView;
    this.imgNews = imgNews;
    this.linearLayoutMain = linearLayoutMain;
    this.tvDate = tvDate;
    this.tvDesc = tvDesc;
    this.tvTitle = tvTitle;
  }

  @Override
  @NonNull
  public LinearLayoutCompat getRoot() {
    return rootView;
  }

  @NonNull
  public static NewsRecyclerViewItemLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static NewsRecyclerViewItemLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.news_recycler_view_item_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static NewsRecyclerViewItemLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.img_news;
      AppCompatImageView imgNews = ViewBindings.findChildViewById(rootView, id);
      if (imgNews == null) {
        break missingId;
      }

      LinearLayoutCompat linearLayoutMain = (LinearLayoutCompat) rootView;

      id = R.id.tv_date;
      MaterialTextView tvDate = ViewBindings.findChildViewById(rootView, id);
      if (tvDate == null) {
        break missingId;
      }

      id = R.id.tv_desc;
      MaterialTextView tvDesc = ViewBindings.findChildViewById(rootView, id);
      if (tvDesc == null) {
        break missingId;
      }

      id = R.id.tv_title;
      MaterialTextView tvTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvTitle == null) {
        break missingId;
      }

      return new NewsRecyclerViewItemLayoutBinding((LinearLayoutCompat) rootView, imgNews,
          linearLayoutMain, tvDate, tvDesc, tvTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
