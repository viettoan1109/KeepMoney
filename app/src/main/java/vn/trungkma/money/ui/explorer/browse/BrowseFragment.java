package vn.trungkma.money.ui.explorer.browse;

import static vn.trungkma.money.common.Constant.STORAGE_ROOT;
import static vn.trungkma.money.ui.main.MainActivity.getSelectList;

import android.os.Bundle;

import androidx.lifecycle.Observer;

import android.view.View;

import java.util.LinkedList;
import java.util.List;

import vn.trungkma.money.R;
import vn.trungkma.money.data.model.FileData;
import vn.trungkma.money.databinding.FragmentBrowseBinding;
import vn.trungkma.money.ui.adapter.BrowseAdapter;
import vn.trungkma.money.ui.base.BaseBindingFragment;
import vn.trungkma.money.ui.dialog.DialogNewFolder;
import vn.trungkma.money.ui.main.MainActivity;


public class BrowseFragment extends BaseBindingFragment<FragmentBrowseBinding, BrowseViewModel> implements BrowseAdapter.OnItemClickedListener {


    private BrowseAdapter browseAdapter;
    private List<FileData> explorerList = new LinkedList<>();

    @Override
    protected Class<BrowseViewModel> getViewModel() {
        return BrowseViewModel.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_browse;
    }

    @Override
    protected void onCreatedView(View view, Bundle savedInstanceState) {
        browseAdapter = new BrowseAdapter(getContext());
        browseAdapter.setOnItemClickedListener(this);
        observerData();
        initData();
        listener();
        binding.recyclerBrowse.setAdapter(browseAdapter);

    }

    private void observerData() {
        viewModel.mutableLiveDataBrowse.observe(this, new Observer<List<FileData>>() {
            @Override
            public void onChanged(List<FileData> fileData) {
                if (fileData.size() > 0) {
                    explorerList.clear();
                    explorerList.addAll(fileData);
                    browseAdapter.setList(fileData);

                }
            }
        });
    }

    private void listener() {
        binding.imgBrowseAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogNewFolder dialogNewFolder = new DialogNewFolder(getContext());
                dialogNewFolder.show();
            }
        });

        binding.imgBrowseBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (requireActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).showOptions(String.valueOf(getSelectList().size()));
                    requireActivity().onBackPressed();
                }
            }
        });
    }

    @Override
    protected void onPermissionGranted() {
        initData();
    }

    private void initData() {
        viewModel.getBrowse(STORAGE_ROOT);
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onFolderClick(int position) {
        viewModel.getBrowse(STORAGE_ROOT + "/" + explorerList.get(position).getFileName());
        browseAdapter.notifyDataSetChanged();
    }
}