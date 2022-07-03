package vn.trungkma.money.ui.explorer.video;

import static vn.trungkma.money.ui.main.MainActivity.getSelectList;
import static vn.trungkma.money.ui.main.MainActivity.selectList;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;

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
import vn.trungkma.money.databinding.FragmentVideoBinding;
import vn.trungkma.money.ui.adapter.MediaAdapter;
import vn.trungkma.money.ui.base.BaseBindingFragment;
import vn.trungkma.money.ui.main.MainActivity;
import vn.trungkma.money.utils.Utils;


public class VideoFragment extends BaseBindingFragment<FragmentVideoBinding, VideoViewModel> implements MediaAdapter.OnItemClickedListener {

    private MediaAdapter fileVideoAdapter;
    private List<FileData> videoList = new LinkedList<>();


    @Override
    protected Class<VideoViewModel> getViewModel() {
        return VideoViewModel.class;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_video;
    }


    @Override
    protected void onCreatedView(View view, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        fileVideoAdapter = new MediaAdapter(getContext());
        fileVideoAdapter.setOnItemClickedListener(this);
        observerData();
        initData();
        listener();
        search();
        binding.recyclerVideo.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.recyclerVideo.setAdapter(fileVideoAdapter);
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

                List<FileData> list = fileVideoAdapter.getList();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getFilePath().equals(((MainActivity) requireActivity()).rename)) {
                        list.get(i).setFileName(((Event) event).getNewNameFile());
                        list.get(i).setFilePath(((Event) event).getNewPathFile());
                        break;
                    }
                }
                fileVideoAdapter.setList(list);
                for (int i = 0; i < getSelectList().size(); i++) {
                    selectList.get(i).setChecked(false);
                }
                ((MainActivity) getActivity()).hideOptions();
                break;

        }

    }

    private void listener() {

        binding.imgVideoSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imgVideoSearch.setVisibility(View.GONE);
                binding.tvVideo.setVisibility(View.INVISIBLE);
                binding.tvVideoCancel.setVisibility(View.VISIBLE);
                binding.searchVideo.setVisibility(View.VISIBLE);
            }
        });

        binding.tvVideoCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.searchVideo.setQuery("", false);
                binding.searchVideo.clearFocus();
                binding.imgVideoSearch.setVisibility(View.VISIBLE);
                binding.tvVideo.setVisibility(View.VISIBLE);
                binding.tvVideoCancel.setVisibility(View.GONE);
                binding.searchVideo.setVisibility(View.GONE);
            }
        });
        binding.imgVideoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                if ((requireActivity() instanceof MainActivity)) {
                    requireActivity().onBackPressed();
                }

            }
        });
    }

    private void search() {
        binding.searchVideo.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fileVideoAdapter.filter(newText);
                return false;
            }
        });
    }

    private void observerData() {
        viewModel.mutableLiveDataVideo.observe(this, new Observer<List<FileData>>() {
            @Override
            public void onChanged(List<FileData> fileData) {
                if (fileData.size() > 0) {
                    Collections.sort(fileData, new Utils.DateTimeComparator());
                    videoList.clear();
                    videoList.addAll(fileData);
                    fileVideoAdapter.setList(fileData);
                    binding.progressVideo.setVisibility(View.GONE);
                } else {
                    binding.tvVideoNoData.setVisibility(View.VISIBLE);
                    binding.progressVideo.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void onPermissionGranted() {
        initData();
    }

    private void initData() {
        viewModel.getVideo();
    }

    @Override
    public void onItemClick(int position) {
        videoList.get(position).setChecked(!videoList.get(position).isChecked());
        if (videoList.get(position).isChecked()) {
            selectList.add(videoList.get(position));
        } else {
            if (getSelectList().size() > 0) {
                for (int i = 0; i < fileVideoAdapter.listSearch.size(); i++) {
                    for (int j = 0; j < getSelectList().size(); j++) {
                        if (fileVideoAdapter.listSearch.get(i).getFileName().equals(getSelectList().get(j).getFileName()) && !fileVideoAdapter.listSearch.get(i).isChecked()) {
                            // holder.binding.imgItemCheck.setImageResource(R.drawable.ic_check_false);
                            fileVideoAdapter.listSearch.get(i).setChecked(false);
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