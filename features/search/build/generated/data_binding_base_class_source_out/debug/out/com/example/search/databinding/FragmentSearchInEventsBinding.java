// Generated by view binder compiler. Do not edit!
package com.example.search.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.search.R;
import com.google.android.material.divider.MaterialDivider;
import com.google.android.material.textview.MaterialTextView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentSearchInEventsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatImageView imgSearch;

  @NonNull
  public final MaterialDivider materialDivider;

  @NonNull
  public final MaterialDivider materialDivider1;

  @NonNull
  public final RecyclerView rcViewSearch;

  @NonNull
  public final MaterialTextView tvExample;

  @NonNull
  public final MaterialTextView tvHelpText;

  private FragmentSearchInEventsBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppCompatImageView imgSearch, @NonNull MaterialDivider materialDivider,
      @NonNull MaterialDivider materialDivider1, @NonNull RecyclerView rcViewSearch,
      @NonNull MaterialTextView tvExample, @NonNull MaterialTextView tvHelpText) {
    this.rootView = rootView;
    this.imgSearch = imgSearch;
    this.materialDivider = materialDivider;
    this.materialDivider1 = materialDivider1;
    this.rcViewSearch = rcViewSearch;
    this.tvExample = tvExample;
    this.tvHelpText = tvHelpText;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSearchInEventsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSearchInEventsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_search_in_events, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSearchInEventsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.img_search;
      AppCompatImageView imgSearch = ViewBindings.findChildViewById(rootView, id);
      if (imgSearch == null) {
        break missingId;
      }

      id = R.id.materialDivider;
      MaterialDivider materialDivider = ViewBindings.findChildViewById(rootView, id);
      if (materialDivider == null) {
        break missingId;
      }

      id = R.id.materialDivider1;
      MaterialDivider materialDivider1 = ViewBindings.findChildViewById(rootView, id);
      if (materialDivider1 == null) {
        break missingId;
      }

      id = R.id.rc_view_search;
      RecyclerView rcViewSearch = ViewBindings.findChildViewById(rootView, id);
      if (rcViewSearch == null) {
        break missingId;
      }

      id = R.id.tv_example;
      MaterialTextView tvExample = ViewBindings.findChildViewById(rootView, id);
      if (tvExample == null) {
        break missingId;
      }

      id = R.id.tv_help_text;
      MaterialTextView tvHelpText = ViewBindings.findChildViewById(rootView, id);
      if (tvHelpText == null) {
        break missingId;
      }

      return new FragmentSearchInEventsBinding((ConstraintLayout) rootView, imgSearch,
          materialDivider, materialDivider1, rcViewSearch, tvExample, tvHelpText);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
