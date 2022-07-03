package vn.trungkma.money.ui.main.home;

import static vn.trungkma.money.ui.main.MainActivity.getSelectList;
import static vn.trungkma.money.ui.main.MainActivity.selectList;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;

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
import vn.trungkma.money.databinding.FragmentHomeBinding;
import vn.trungkma.money.ui.adapter.FileDataAdapter;
import vn.trungkma.money.ui.base.BaseBindingFragment;
import vn.trungkma.money.ui.main.MainActivity;
import vn.trungkma.money.utils.Utils;

public class HomeFragment extends BaseBindingFragment<FragmentHomeBinding, HomeViewModel> implements FileDataAdapter.OnItemClickedListener {

    private final List<FileData> fileRecentList = new LinkedList<>();
    NavHostFragment navHostFragment;
    private FileDataAdapter fileRecentAdapter;

    @Override
    protected Class<HomeViewModel> getViewModel() {
        return HomeViewModel.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onCreatedView(View view, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        fileRecentAdapter = new FileDataAdapter(getContext());
        fileRecentAdapter.setOnItemClickedListener(this);
        observerData();
        binding.recyclerHomeRecent.setAdapter(fileRecentAdapter);
        listener();
        Log.d("life", "Home: createView");

    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageReceived(Event event) {
        switch (event.getTypeEvent()) {
            case Constant.EVENT_CHANGE_NAME:

                List<FileData> list = fileRecentAdapter.getList();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getFilePath().equals(((MainActivity) requireActivity()).rename)) {
                        list.get(i).setFileName(((Event) event).getNewNameFile());
                        list.get(i).setFilePath(((Event) event).getNewPathFile());
                        break;
                    }
                }
                fileRecentAdapter.setList(list);
                for (int i = 0; i < getSelectList().size(); i++) {
                    selectList.get(i).setChecked(false);
                }
                ((MainActivity) getActivity()).hideOptions();
                break;

            case Constant.EVENT_DELETE:
                List<FileData> list1 = fileRecentAdapter.getList();
                for (int i = 0; i < list1.size(); i++) {
                    if (list1.get(i).getFilePath().equals(((MainActivity) requireActivity()).rename)) {
                        list1.remove(i);
                        break;
                    }
                }
                fileRecentAdapter.setList(list1);
                break;
        }

    }

    private void listener() {
        binding.tvHomeShowRecent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.tvHomeShowRecent.isChecked()) {
                    binding.rlHomeAds.setVisibility(View.GONE);
                    binding.rlHomeExplorer.setVisibility(View.GONE);
                    binding.rlHomeArchive.setVisibility(View.GONE);
                    /*observerData(fileRecentList.size());
                    fileRecentAdapter.notifyDataSetChanged();*/
                } else {
                    binding.rlHomeAds.setVisibility(View.VISIBLE);
                    binding.rlHomeExplorer.setVisibility(View.VISIBLE);
                    binding.rlHomeArchive.setVisibility(View.VISIBLE);
                }

            }

        });

        binding.rlHomeExplorer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeExplorerScreen(R.id.explorerFragment);
            }
        });

        binding.rlHomeArchive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeArchiveScreen(R.id.archiveFragment);
            }
        });
        binding.imgHomeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.navHomeDrawer.open();
            }
        });


    }

    private void observerData() {
        mainViewModel.mutableLiveDataFileRecent.observe(this, new Observer<List<FileData>>() {
            @Override
            public void onChanged(List<FileData> fileData) {
                if (fileData.size() > 0) {
                    Collections.sort(fileData, new Utils.DateTimeComparator());
                    fileRecentList.clear();
                    fileRecentList.addAll(fileData);
                    fileRecentAdapter.setList(fileData);

                    binding.progressHome.setVisibility(View.GONE);
                }
            }
        });


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    protected void onPermissionGranted() {

    }

    @Override
    public void onItemClick(int position) {
        fileRecentList.get(position).setChecked(!fileRecentList.get(position).isChecked());
        if (fileRecentList.get(position).isChecked()) {
            selectList.add(fileRecentList.get(position));
        }

        for (int i = 0; i < selectList.size(); i++) {
            if (!selectList.get(i).isChecked()) {
                selectList.remove(i);
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
       // ((MainActivity) getActivity()).changePlayPhotoScreen(R.id.playPhotoFragment, fileRecentList.get(position).getFilePath());
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("life", "Home: stop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("life", "Home: destroy");

    }
}