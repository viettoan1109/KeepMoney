package vn.trungkma.money.ui.explorer.download;

import static vn.trungkma.money.common.Constant.STORAGE_DOWNLOAD;
import static vn.trungkma.money.ui.main.MainActivity.getSelectList;
import static vn.trungkma.money.ui.main.MainActivity.selectList;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.LinkedList;
import java.util.List;

import vn.trungkma.money.R;
import vn.trungkma.money.common.Constant;
import vn.trungkma.money.common.Event;
import vn.trungkma.money.data.model.FileData;
import vn.trungkma.money.databinding.FragmentDownloadBinding;
import vn.trungkma.money.ui.adapter.FileDataAdapter;
import vn.trungkma.money.ui.base.BaseBindingFragment;
import vn.trungkma.money.ui.main.MainActivity;


public class DownloadFragment extends BaseBindingFragment<FragmentDownloadBinding, DownloadViewModel> implements FileDataAdapter.OnItemClickedListener {

    private FileDataAdapter downloadAdapter;
    private List<FileData> downloadList = new LinkedList<>();

    @Override
    protected Class<DownloadViewModel> getViewModel() {
        return DownloadViewModel.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_download;
    }

    @Override
    protected void onCreatedView(View view, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        downloadAdapter = new FileDataAdapter(getContext());
        downloadAdapter.setOnItemClickedListener(this);
        observerData();
        initData();
        listener();
        search();
        binding.recyclerDownload.setAdapter(downloadAdapter);
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

                List<FileData> list = downloadAdapter.getList();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getFilePath().equals(((MainActivity) requireActivity()).rename)) {
                        list.get(i).setFileName(((Event) event).getNewNameFile());
                        list.get(i).setFilePath(((Event) event).getNewPathFile());
                        break;
                    }
                }
                downloadAdapter.setList(list);
                for (int i = 0; i < getSelectList().size(); i++) {
                    selectList.get(i).setChecked(false);
                }
                ((MainActivity) getActivity()).hideOptions();
                break;

        }

    }


    private void listener() {
        binding.imgDownloadSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imgDownloadSearch.setVisibility(View.GONE);
                binding.tvDownload.setVisibility(View.INVISIBLE);
                binding.tvDownloadCancel.setVisibility(View.VISIBLE);
                binding.searchDownload.setVisibility(View.VISIBLE);
            }
        });

        binding.tvDownloadCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.searchDownload.setQuery("", false);
                binding.searchDownload.clearFocus();
                binding.imgDownloadSearch.setVisibility(View.VISIBLE);
                binding.tvDownload.setVisibility(View.VISIBLE);
                binding.tvDownloadCancel.setVisibility(View.GONE);
                binding.searchDownload.setVisibility(View.GONE);
            }
        });

        binding.imgDownloadBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                if ((requireActivity() instanceof MainActivity)) {
                    requireActivity().onBackPressed();
                }

            }
        });
    }

    private void search() {
        binding.searchDownload.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                downloadAdapter.filter(newText);
                return false;
            }
        });
    }

    private void observerData() {
        viewModel.mutableLiveDataDownload.observe(this, new Observer<List<FileData>>() {
            @Override
            public void onChanged(List<FileData> dowwnload) {
                if (dowwnload.size() > 0) {
                    downloadList.clear();
                    downloadList.addAll(dowwnload);
                    downloadAdapter.setList(dowwnload);
                    binding.progressDownload.setVisibility(View.GONE);
                } else {
                    binding.tvDownloadNoData.setVisibility(View.VISIBLE);
                    binding.progressDownload.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void onPermissionGranted() {
        initData();
    }

    private void initData() {
        viewModel.getDownload(STORAGE_DOWNLOAD);
    }

    @Override
    public void onItemClick(int position) {
        downloadList.get(position).setChecked(!downloadList.get(position).isChecked());
        if (downloadList.get(position).isChecked()) {
            selectList.add(downloadList.get(position));
        } else {
            if (getSelectList().size() > 0) {
                for (int i = 0; i < downloadAdapter.listSearch.size(); i++) {
                    for (int j = 0; j < getSelectList().size(); j++) {
                        if (downloadAdapter.listSearch.get(i).getFileName().equals(getSelectList().get(j).getFileName()) && !downloadAdapter.listSearch.get(i).isChecked()) {
                            // holder.binding.imgItemCheck.setImageResource(R.drawable.ic_check_false);
                            downloadAdapter.listSearch.get(i).setChecked(false);
                            selectList.remove(j);
                        }
                    }
                }
            }
        }
        if (getSelectList().size() > 0) {
            ((MainActivity) getActivity()).showOptions(String.valueOf(getSelectList().size()));
        } else {
            ((MainActivity) getActivity()).hideOptions();
        }
    }

    @Override
    public void onFolderClick(int position) {

    }
}