package vn.trungkma.money.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import vn.trungkma.money.ui.main.MainViewModel;

public abstract class BaseBindingBottomSheetFragment<B extends ViewDataBinding, T extends BaseViewModel> extends BaseBottomSheetFragment{

    public B binding;
    public T viewModel;
    public MainViewModel mainViewModel;
    private boolean loaded = false;

    protected abstract Class<T> getViewModel();

    public abstract int getLayoutId();

    protected abstract void onCreatedView(View view, Bundle savedInstanceState);

    protected abstract void onPermissionGranted();

    private long lastClick = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!loaded) {
            binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(getViewModel());
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        if (!needToKeepView()) {
            onCreatedView(view, savedInstanceState);
        } else {
            if (!loaded) {
                onCreatedView(view, savedInstanceState);
                loaded = true;
            }
        }

    }
    private long lastClickTime2 = 0;

    protected boolean checkTime() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - lastClickTime2) < 600) {
            return false;
        }
        lastClickTime2 = currentTime;
        return true;
    }

    public boolean needToKeepView() {
        return false;
    }
}
