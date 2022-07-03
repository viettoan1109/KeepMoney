package vn.trungkma.money.ui.explorer.photo;

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
import vn.trungkma.money.databinding.FragmentPhotoBinding;
import vn.trungkma.money.ui.adapter.MediaAdapter;
import vn.trungkma.money.ui.base.BaseBindingFragment;
import vn.trungkma.money.ui.main.MainActivity;
import vn.trungkma.money.utils.Utils;


public class PhotoFragment extends BaseBindingFragment<FragmentPhotoBinding, PhotoViewModel> implements MediaAdapter.OnItemClickedListener {


    private MediaAdapter filePhotoAdapter;
    private List<FileData> photoList = new LinkedList<>();

    @Override
    protected Class<PhotoViewModel> getViewModel() {
        return PhotoViewModel.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_photo;
    }

    @Override
    protected void onCreatedView(View view, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        filePhotoAdapter = new MediaAdapter(getContext());
        filePhotoAdapter.setOnItemClickedListener(this);
        observerData();
        initData();
        listener();
        search();
        binding.recyclerPhoto.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.recyclerPhoto.setAdapter(filePhotoAdapter);

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

                List<FileData> list = filePhotoAdapter.getList();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getFilePath().equals(((MainActivity) requireActivity()).rename)) {
                        list.get(i).setFileName(((Event) event).getNewNameFile());
                        list.get(i).setFilePath(((Event) event).getNewPathFile());
                        break;
                    }
                }
                filePhotoAdapter.setList(list);
                for (int i = 0; i < getSelectList().size(); i++) {
                    selectList.get(i).setChecked(false);
                }
                ((MainActivity) getActivity()).hideOptions();
                break;

        }

    }

    private void listener() {
        binding.imgPhotoSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imgPhotoSearch.setVisibility(View.GONE);
                binding.tvPhoto.setVisibility(View.INVISIBLE);
                binding.tvPhotoCancel.setVisibility(View.VISIBLE);
                binding.searchPhoto.setVisibility(View.VISIBLE);
            }
        });

        binding.tvPhotoCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // filePhotoAdapter.filter("");
                binding.searchPhoto.setQuery("", false);
                binding.searchPhoto.clearFocus();
                binding.imgPhotoSearch.setVisibility(View.VISIBLE);
                binding.tvPhoto.setVisibility(View.VISIBLE);
                binding.tvPhotoCancel.setVisibility(View.GONE);
                binding.searchPhoto.setVisibility(View.GONE);
            }
        });

        binding.imgPhotoBack.setOnClickListener(new View.OnClickListener() {
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
        binding.searchPhoto.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filePhotoAdapter.filter(newText);
                return false;
            }
        });
    }

    private void observerData() {

        viewModel.mutableLiveDataPhoto.observe(this, new Observer<List<FileData>>() {
            @Override
            public void onChanged(List<FileData> fileData) {
                if (fileData.size() > 0) {

                    Collections.sort(fileData, new Utils.DateTimeComparator());

                    photoList.clear();
                    photoList.addAll(fileData);
                    filePhotoAdapter.setList(fileData);
                    binding.progressPhoto.setVisibility(View.GONE);
                } else {
                    binding.tvPhotoNoData.setVisibility(View.VISIBLE);
                    binding.progressPhoto.setVisibility(View.GONE);
                }
            }
        });
    }


    @Override
    protected void onPermissionGranted() {
        initData();
    }

    private void initData() {
        viewModel.getPhoto();
    }

    @Override
    public void onItemClick(int position) {
        photoList.get(position).setChecked(!photoList.get(position).isChecked());
        if (photoList.get(position).isChecked()) {
            selectList.add(photoList.get(position));
        } else {
            if (getSelectList().size() > 0) {
                for (int i = 0; i < filePhotoAdapter.listSearch.size(); i++) {
                    for (int j = 0; j < getSelectList().size(); j++) {
                        if (filePhotoAdapter.listSearch.get(i).getFileName().equals(getSelectList().get(j).getFileName()) && !filePhotoAdapter.listSearch.get(i).isChecked()) {
                            // holder.binding.imgItemCheck.setImageResource(R.drawable.ic_check_false);
                            filePhotoAdapter.listSearch.get(i).setChecked(false);
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