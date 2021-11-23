// Generated by view binder compiler. Do not edit!
package com.example.chattingapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.chattingapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityFindBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button buttonFindEmail;

  @NonNull
  public final Button buttonFindPassword;

  private ActivityFindBinding(@NonNull LinearLayout rootView, @NonNull Button buttonFindEmail,
      @NonNull Button buttonFindPassword) {
    this.rootView = rootView;
    this.buttonFindEmail = buttonFindEmail;
    this.buttonFindPassword = buttonFindPassword;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityFindBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityFindBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_find, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityFindBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button_find_email;
      Button buttonFindEmail = ViewBindings.findChildViewById(rootView, id);
      if (buttonFindEmail == null) {
        break missingId;
      }

      id = R.id.button_find_password;
      Button buttonFindPassword = ViewBindings.findChildViewById(rootView, id);
      if (buttonFindPassword == null) {
        break missingId;
      }

      return new ActivityFindBinding((LinearLayout) rootView, buttonFindEmail, buttonFindPassword);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
