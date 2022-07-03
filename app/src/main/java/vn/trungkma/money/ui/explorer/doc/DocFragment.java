package vn.trungkma.money.ui.explorer.doc;

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
import vn.trungkma.money.databinding.FragmentDocBinding;
import vn.trungkma.money.ui.adapter.FileDataAdapter;
import vn.trungkma.money.ui.base.BaseBindingFragment;
import vn.trungkma.money.ui.main.MainActivity;
import vn.trungkma.money.utils.Utils;

public class DocFragment extends BaseBindingFragment<FragmentDocBinding, DocViewModel> implements FileDataAdapter.OnItemClickedListener {
    private FileDataAdapter fileDocAdapter;
    private List<FileData> documentList = new LinkedList<>();

    @Override
    protected Class<DocViewModel> getViewModel() {
        return DocViewModel.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_doc;
    }

    @Override
    protected void onCreatedView(View view, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        fileDocAdapter = new FileDataAdapter(getContext());
        fileDocAdapter.setOnItemClickedListener(this);
        observerData();
        initData();
        listener();
        search();
        binding.recyclerDoc.setAdapter(fileDocAdapter);

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
                List<FileData> list = fileDocAdapter.getList();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getFilePath().equals(((MainActivity) requireActivity()).rename)) {
                        list.get(i).setFileName(((Event) event).getNewNameFile());
                        list.get(i).setFilePath(((Event) event).getNewPathFile());
                        break;
                    }
                }
                fileDocAdapter.setList(list);
                for (int i = 0; i < getSelectList().size(); i++) {
                    selectList.get(i).setChecked(false);
                }

                ((MainActivity) getActivity()).hideOptions();
                break;

        }

    }

    private void listener() {

        binding.imgSearchDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imgSearchDoc.setVisibility(View.GONE);
                binding.tvDoc.setVisibility(View.INVISIBLE);
                binding.tvDocCancel.setVisibility(View.VISIBLE);
                binding.searchDoc.setVisibility(View.VISIBLE);
            }
        });

        binding.tvDocCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.searchDoc.setQuery("", false);
                binding.searchDoc.clearFocus();
                binding.imgSearchDoc.setVisibility(View.VISIBLE);
                binding.tvDoc.setVisibility(View.VISIBLE);
                binding.tvDocCancel.setVisibility(View.GONE);
                binding.searchDoc.setVisibility(View.GONE);
            }
        });

        binding.imgDocBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                if (requireActivity() instanceof MainActivity) {
                    requireActivity().onBackPressed();
                }
            }
        });
    }

    private void search() {
        binding.searchDoc.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fileDocAdapter.filter(newText);
                return false;
            }
        });
    }

    private void observerData() {
        viewModel.mutableLiveDataDocument.observe(this, new Observer<List<FileData>>() {
            @Override
            public void onChanged(List<FileData> fileData) {
                if (fileData.size() > 0) {
                    Collections.sort(fileData, new Utils.DateTimeComparator());
                    documentList.clear();
                    documentList.addAll(fileData);
                    fileDocAdapter.setList(fileData);
                    binding.progressDoc.setVisibility(View.INVISIBLE);
                } else {
                    binding.tvDocNoData.setVisibility(View.VISIBLE);
                    binding.progressDoc.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    protected void onPermissionGranted() {
        initData();
    }

    private void initData() {
        viewModel.getDocument();
    }

    @Override
    public void onItemClick(int position) {
        documentList.get(position).setChecked(!documentList.get(position).isChecked());
        if (documentList.get(position).isChecked()) {
            selectList.add(documentList.get(position));
        } else {
            if (getSelectList().size() > 0) {
                for (int i = 0; i < fileDocAdapter.listSearch.size(); i++) {
                    for (int j = 0; j < getSelectList().size(); j++) {
                        if (fileDocAdapter.listSearch.get(i).getFileName().equals(getSelectList().get(j).getFileName()) && !fileDocAdapter.listSearch.get(i).isChecked()) {
                            // holder.binding.imgItemCheck.setImageResource(R.drawable.ic_check_false);
                            fileDocAdapter.listSearch.get(i).setChecked(false);
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