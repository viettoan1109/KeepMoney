package vn.trungkma.money.ui.explorer.music;

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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import vn.trungkma.money.R;
import vn.trungkma.money.common.Constant;
import vn.trungkma.money.common.Event;
import vn.trungkma.money.data.model.FileData;
import vn.trungkma.money.databinding.FragmentMusicBinding;
import vn.trungkma.money.ui.adapter.FileDataAdapter;
import vn.trungkma.money.ui.base.BaseBindingFragment;
import vn.trungkma.money.ui.main.MainActivity;
import vn.trungkma.money.utils.Utils;


public class MusicFragment extends BaseBindingFragment<FragmentMusicBinding, MusicViewModel> implements FileDataAdapter.OnItemClickedListener {

    private FileDataAdapter fileMusicAdapter;
    private List<FileData> musicList = new LinkedList<>();

    @Override
    protected Class<MusicViewModel> getViewModel() {
        return MusicViewModel.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_music;
    }

    @Override
    protected void onCreatedView(View view, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        fileMusicAdapter = new FileDataAdapter(getContext());
        fileMusicAdapter.setOnItemClickedListener(this);
        observerData();
        initData();
        listener();
        search();
        binding.recyclerMusic.setAdapter(fileMusicAdapter);
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

                List<FileData> list = fileMusicAdapter.getList();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getFilePath().equals(((MainActivity) requireActivity()).rename)) {
                        list.get(i).setFileName(((Event) event).getNewNameFile());
                        list.get(i).setFilePath(((Event) event).getNewPathFile());
                        break;
                    }
                }
                fileMusicAdapter.setList(list);
                for (int i = 0; i < getSelectList().size(); i++) {
                    selectList.get(i).setChecked(false);
                }
                ((MainActivity) getActivity()).hideOptions();
                break;

        }

    }

    private void listener() {
        binding.imgMusicSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imgMusicSearch.setVisibility(View.GONE);
                binding.tvMusic.setVisibility(View.INVISIBLE);
                binding.tvMusicCancel.setVisibility(View.VISIBLE);
                binding.searchMusic.setVisibility(View.VISIBLE);
            }
        });

        binding.tvMusicCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.searchMusic.setQuery("", false);
                binding.searchMusic.clearFocus();
                binding.imgMusicSearch.setVisibility(View.VISIBLE);
                binding.tvMusic.setVisibility(View.VISIBLE);
                binding.tvMusicCancel.setVisibility(View.GONE);
                binding.searchMusic.setVisibility(View.GONE);
            }
        });
        binding.imgMusicBack.setOnClickListener(new View.OnClickListener() {
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
        binding.searchMusic.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fileMusicAdapter.filter(newText);
                return false;
            }
        });
    }

    private void observerData() {
        viewModel.mutableLiveDataMusic.observe(this, new Observer<List<FileData>>() {
            @Override
            public void onChanged(List<FileData> fileData) {
                if (fileData.size() > 0) {
                    Collections.sort(fileData, new Utils.DateTimeComparator());
                    musicList.clear();
                    musicList.addAll(fileData);
                    fileMusicAdapter.setList(fileData);
                    binding.progressMusic.setVisibility(View.GONE);
                } else {
                    binding.tvMusicNoData.setVisibility(View.VISIBLE);
                    binding.progressMusic.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void onPermissionGranted() {
        initData();
    }

    private void initData() {
        viewModel.getMusic();
    }

    @Override
    public void onItemClick(int position) {
        musicList.get(position).setChecked(!musicList.get(position).isChecked());
        if (musicList.get(position).isChecked()) {
            selectList.add(musicList.get(position));
        } else {
            if (getSelectList().size() > 0) {
                for (int i = 0; i < fileMusicAdapter.listSearch.size(); i++) {
                    for (int j = 0; j < getSelectList().size(); j++) {
                        if (fileMusicAdapter.listSearch.get(i).getFileName().equals(getSelectList().get(j).getFileName()) && !fileMusicAdapter.listSearch.get(i).isChecked()) {
                            // holder.binding.imgItemCheck.setImageResource(R.drawable.ic_check_false);
                            fileMusicAdapter.listSearch.get(i).setChecked(false);
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