package vn.trungkma.money.ui.policy;

import android.os.Bundle;

import android.view.View;

import vn.trungkma.money.R;
import vn.trungkma.money.databinding.FragmentPolicyBinding;
import vn.trungkma.money.ui.base.BaseBindingFragment;


public class PolicyFragment extends BaseBindingFragment<FragmentPolicyBinding, PolicyViewModel> {

    @Override
    protected Class<PolicyViewModel> getViewModel() {
        return PolicyViewModel.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_policy;
    }

    @Override
    protected void onCreatedView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void onPermissionGranted() {

    }
}