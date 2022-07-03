package vn.trungkma.money.ui.main.select;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import vn.trungkma.money.R;
import vn.trungkma.money.databinding.FragmentSelectBinding;
import vn.trungkma.money.ui.base.BaseBindingBottomSheetFragment;


public class SelectFragment extends BaseBindingBottomSheetFragment<FragmentSelectBinding, SelectViewModel> {

    DisplayMetrics displayMetrics = new DisplayMetrics();
    int screenHeight;

    @Override
    protected Class<SelectViewModel> getViewModel() {
        return SelectViewModel.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_select;
    }

    @Override
    protected void onCreatedView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void onPermissionGranted() {

    }

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            switch (newState) {

                case BottomSheetBehavior.STATE_COLLAPSED: {
                    screenHeight = displayMetrics.heightPixels;
                    Log.d("BSB", "collapsed");
                }
                case BottomSheetBehavior.STATE_SETTLING: {

                    Log.d("BSB", "settling");
                }
                case BottomSheetBehavior.STATE_EXPANDED: {

                    Log.d("BSB", "expanded");
                }
                case BottomSheetBehavior.STATE_HIDDEN: {

                    Log.d("BSB", "hidden");
                    dismiss();
                }
                case BottomSheetBehavior.STATE_DRAGGING: {

                    Log.d("BSB", "dragging");
                }
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            Log.d("BSB", "sliding " + slideOffset);
        }
    };

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(@NonNull Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.fragment_select, null);

        dialog.setContentView(contentView);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }

        View parent = (View) contentView.getParent();
        parent.setFitsSystemWindows(true);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(parent);
        contentView.measure(0, 0);

        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels/2;
        bottomSheetBehavior.setPeekHeight(screenHeight);
        if (params.getBehavior() instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) params.getBehavior()).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
        params.height = screenHeight;
        parent.setLayoutParams(params);
    }

}