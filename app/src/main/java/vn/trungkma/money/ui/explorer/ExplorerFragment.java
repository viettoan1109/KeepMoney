package vn.trungkma.money.ui.explorer;

import static vn.trungkma.money.common.Constant.SCREEN_APK;
import static vn.trungkma.money.common.Constant.SCREEN_DOC;
import static vn.trungkma.money.common.Constant.SCREEN_DOWNLOAD;
import static vn.trungkma.money.common.Constant.SCREEN_MUSIC;
import static vn.trungkma.money.common.Constant.SCREEN_PHOTO;
import static vn.trungkma.money.common.Constant.SCREEN_VIDEO;
import static vn.trungkma.money.common.Constant.STORAGE_ROOT;
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
import vn.trungkma.money.databinding.FragmentExplorerBinding;
import vn.trungkma.money.ui.adapter.FileDataAdapter;
import vn.trungkma.money.ui.base.BaseBindingFragment;
import vn.trungkma.money.ui.main.MainActivity;


public class ExplorerFragment extends BaseBindingFragment<FragmentExplorerBinding, ExplorerViewModel> implements FileDataAdapter.OnItemClickedListener {

    private FileDataAdapter explorerAdapter;
    private List<FileData> explorerList = new LinkedList<>();

    @Override
    protected Class<ExplorerViewModel> getViewModel() {
        return ExplorerViewModel.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_explorer;
    }

    @Override
    protected void onCreatedView(View view, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        explorerAdapter = new FileDataAdapter(getContext());
        explorerAdapter.setOnItemClickedListener(this);
        observerData();
        initData();
        listener();
        search();
        binding.recyclerExplorer.setAdapter(explorerAdapter);
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

                List<FileData> list = explorerAdapter.getList();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getFilePath().equals(((MainActivity) requireActivity()).rename)) {
                        list.get(i).setFileName(((Event) event).getNewNameFile());
                        list.get(i).setFilePath(((Event) event).getNewPathFile());
                        break;
                    }
                }
                explorerAdapter.setList(list);
                for (int i = 0; i < getSelectList().size(); i++) {
                    selectList.get(i).setChecked(false);
                }
                ((MainActivity) getActivity()).hideOptions();
                break;

        }

    }

    private void observerData() {
        viewModel.mutableLiveDataExplorer.observe(this, new Observer<List<FileData>>() {
            @Override
            public void onChanged(List<FileData> fileData) {
                if (fileData.size() > 0) {
                    explorerList.clear();
                    explorerList.addAll(fileData);
                    explorerAdapter.setList(fileData);
                    binding.progressExplorer.setVisibility(View.GONE);
                } else {
                    binding.tvExplorerNoData.setVisibility(View.VISIBLE);
                    binding.progressExplorer.setVisibility(View.GONE);
                }
            }
        });
    }


    private void listener() {
        binding.imgSearchExplorer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imgSearchExplorer.setVisibility(View.GONE);
                binding.tvExplorer.setVisibility(View.INVISIBLE);
                binding.tvExplorerCancel.setVisibility(View.VISIBLE);
                binding.searchExplorer.setVisibility(View.VISIBLE);

            }
        });

        binding.tvExplorerCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.searchExplorer.setQuery("", false);
                binding.searchExplorer.clearFocus();
                binding.imgSearchExplorer.setVisibility(View.VISIBLE);
                binding.tvExplorer.setVisibility(View.VISIBLE);
                binding.tvExplorerCancel.setVisibility(View.GONE);
                binding.searchExplorer.setVisibility(View.GONE);
            }
        });
        binding.imgExplorerBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);

                if (requireActivity() instanceof MainActivity) {
                    requireActivity().onBackPressed();
                }

            }
        });

        binding.rlExplorerDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeScreenExplorer(SCREEN_DOC);
            }
        });
        binding.rlExplorerPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).changeScreenExplorer(SCREEN_PHOTO);

            }
        });
        binding.rlExplorerApk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).changeScreenExplorer(SCREEN_APK);

            }
        });
        binding.rlExplorerDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).changeScreenExplorer(SCREEN_DOWNLOAD);

            }
        });
        binding.rlExplorerMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).changeScreenExplorer(SCREEN_MUSIC);

            }
        });
        binding.rlExplorerVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).changeScreenExplorer(SCREEN_VIDEO);

            }
        });
    }

    private void search() {
        binding.searchExplorer.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                explorerAdapter.filter(newText);
                return false;
            }
        });
    }


    @Override
    protected void onPermissionGranted() {
        initData();
    }

    private void initData() {
        viewModel.getExplorer(STORAGE_ROOT);
    }


    @Override
    public void onItemClick(int position) {
        explorerList.get(position).setChecked(!explorerList.get(position).isChecked());
        if (explorerList.get(position).isChecked()) {
            selectList.add(explorerList.get(position));
        } else {
            if (getSelectList().size() > 0) {
                for (int i = 0; i < explorerAdapter.listSearch.size(); i++) {
                    for (int j = 0; j < getSelectList().size(); j++) {
                        if (explorerAdapter.listSearch.get(i).getFileName().equals(getSelectList().get(j).getFileName()) && !explorerAdapter.listSearch.get(i).isChecked()) {
                            // holder.binding.imgItemCheck.setImageResource(R.drawable.ic_check_false);
                            explorerAdapter.listSearch.get(i).setChecked(false);
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
        viewModel.getExplorer(STORAGE_ROOT + "/" + explorerList.get(position).getFileName());
        explorerAdapter.notifyDataSetChanged();
    }


}