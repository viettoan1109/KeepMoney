package vn.trungkma.money.ui.explorer.apk;

import static vn.trungkma.money.ui.main.MainActivity.getSelectList;
import static vn.trungkma.money.ui.main.MainActivity.selectList;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import vn.trungkma.money.R;
import vn.trungkma.money.common.Constant;
import vn.trungkma.money.common.Event;
import vn.trungkma.money.data.model.FileData;
import vn.trungkma.money.databinding.FragmentApkBinding;
import vn.trungkma.money.ui.adapter.FileDataAdapter;
import vn.trungkma.money.ui.base.BaseBindingFragment;
import vn.trungkma.money.ui.main.MainActivity;
import vn.trungkma.money.utils.Utils;

public class ApkFragment extends BaseBindingFragment<FragmentApkBinding, ApkViewModel> implements FileDataAdapter.OnItemClickedListener {

    private FileDataAdapter fileApkAdapter;
    private List<FileData> apkList = new LinkedList<>();

    @Override
    protected Class<ApkViewModel> getViewModel() {
        return ApkViewModel.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_apk;
    }

    @Override
    protected void onCreatedView(View view, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        fileApkAdapter = new FileDataAdapter(getContext());
        fileApkAdapter.setOnItemClickedListener(this);
        observerData();
        initData();
        listener();
        search();
        binding.recyclerApk.setAdapter(fileApkAdapter);
    }
    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageReceived(Event event) {
        switch (event.getTypeEvent()) {
            case Constant.EVENT_CHANGE_NAME:

                List<FileData> list=fileApkAdapter.getList();
                for (int i = 0; i < list.size(); i++) {
                    if(list.get(i).getFilePath().equals(((MainActivity)requireActivity()).rename)){
                        list.get(i).setFileName(((Event)event).getNewNameFile());
                        list.get(i).setFilePath(((Event) event).getNewPathFile());
                        break;
                    }
                }
                fileApkAdapter.setList(list);
                for (int i = 0; i < getSelectList().size(); i++) {
                    selectList.get(i).setChecked(false);
                }

                ((MainActivity)getActivity()).hideOptions();
                break;

        }

    }
    private void observerData() {
        viewModel.mutableLiveDataApk.observe(this, new Observer<List<FileData>>() {
            @Override
            public void onChanged(List<FileData> fileData) {
                if (fileData.size() > 0) {
                    Collections.sort(fileData, new Utils.DateTimeComparator());
                    apkList.clear();
                    apkList.addAll(fileData);
                    fileApkAdapter.setList(fileData);
                    binding.progressApk.setVisibility(View.GONE);
                } else {
                    binding.tvApkNoData.setVisibility(View.VISIBLE);
                    binding.progressApk.setVisibility(View.GONE);
                }
            }
        });

    }

    private void listener(){

        binding.imgSearchApk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imgSearchApk.setVisibility(View.GONE);
                binding.tvApk.setVisibility(View.INVISIBLE);
                binding.tvApkCancel.setVisibility(View.VISIBLE);
                binding.searchApk.setVisibility(View.VISIBLE);
            }
        });

        binding.tvApkCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.searchApk.setQuery("", false);
                binding.searchApk.clearFocus();
                binding.imgSearchApk.setVisibility(View.VISIBLE);
                binding.tvApk.setVisibility(View.VISIBLE);
                binding.tvApkCancel.setVisibility(View.GONE);
                binding.searchApk.setVisibility(View.GONE);
            }
        });

        binding.imgApkBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (requireActivity() instanceof MainActivity) {
                    requireActivity().onBackPressed();
                }
            }
        });
    }

    private void search(){
        binding.searchApk.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fileApkAdapter.filter(newText);
                return false;
            }
        });
    }

    @Override
    protected void onPermissionGranted() {
        initData();
    }


    private void initData() {
        viewModel.getApk();
    }

    @Override
    public void onItemClick(int position) {
        apkList.get(position).setChecked(!apkList.get(position).isChecked());
        if (apkList.get(position).isChecked()){
            selectList.add(apkList.get(position));
        }else {
            if (getSelectList().size() > 0) {
                for (int i = 0; i < fileApkAdapter.listSearch.size(); i++) {
                    for (int j = 0; j < getSelectList().size(); j++) {
                        if (fileApkAdapter.listSearch.get(i).getFileName().equals(getSelectList().get(j).getFileName()) && !fileApkAdapter.listSearch.get(i).isChecked()) {
                            // holder.binding.imgItemCheck.setImageResource(R.drawable.ic_check_false);
                            fileApkAdapter.listSearch.get(i).setChecked(false);
                            selectList.remove(j);
                        }
                    }
                }
            }
        }


        if (getSelectList().size() > 0){
            ((MainActivity) getActivity()).showOptions(String.valueOf(getSelectList().size()));
        } else {
            ((MainActivity) getActivity()).hideOptions();
        }
    }

    @Override
    public void onFolderClick(int position) {

    }

}