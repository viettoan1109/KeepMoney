package vn.trungkma.money.ui.archive;

import static vn.trungkma.money.ui.main.MainActivity.getSelectList;
import static vn.trungkma.money.ui.main.MainActivity.selectList;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;

import net.sf.sevenzipjbinding.ArchiveFormat;
import net.sf.sevenzipjbinding.ExtractAskMode;
import net.sf.sevenzipjbinding.ExtractOperationResult;
import net.sf.sevenzipjbinding.IArchiveExtractCallback;
import net.sf.sevenzipjbinding.IArchiveOpenCallback;
import net.sf.sevenzipjbinding.IInArchive;
import net.sf.sevenzipjbinding.ISequentialOutStream;
import net.sf.sevenzipjbinding.PropID;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.SevenZipException;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import vn.trungkma.money.R;
import vn.trungkma.money.common.Constant;
import vn.trungkma.money.common.Event;
import vn.trungkma.money.data.model.FileData;
import vn.trungkma.money.databinding.FragmentArchiveBinding;
import vn.trungkma.money.ui.adapter.FileDataAdapter;
import vn.trungkma.money.ui.base.BaseBindingFragment;
import vn.trungkma.money.ui.main.MainActivity;
import vn.trungkma.money.utils.Utils;

public class ArchiveFragment extends BaseBindingFragment<FragmentArchiveBinding, ArchiveViewModel> implements FileDataAdapter.OnItemClickedListener {
    /*  private static final String TAG = "TestVersion";*/
    private static final String TAG = "TestList";
    private FileDataAdapter fileArchiveAdapter;
    private List<FileData> archiveList = new LinkedList<>();

    @Override
    protected Class<ArchiveViewModel> getViewModel() {
        return ArchiveViewModel.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_archive;
    }

    @Override
    protected void onCreatedView(View view, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        fileArchiveAdapter = new FileDataAdapter(getContext());
        fileArchiveAdapter.setOnItemClickedListener(this);
        observerData();
        initData();
        listener();
        search();
        binding.recyclerArchive.setAdapter(fileArchiveAdapter);
        Log.d("life", "archive: createView");

        /*File d = new File("//storage/emulated/0/Download/rar");
        File[] files = d.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (!files[i].isDirectory()) {
                testList(files[i].getAbsoluteFile());
            }
        }*/
        //testVersion();
        //  testList(d);
        //testExtract(d);
        //testSlowExtract(d);


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

                List<FileData> list = fileArchiveAdapter.getList();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getFilePath().equals(((MainActivity) requireActivity()).rename)) {
                        list.get(i).setFileName(((Event) event).getNewNameFile());
                        list.get(i).setFilePath(((Event) event).getNewPathFile());
                        break;
                    }
                }
                fileArchiveAdapter.setList(list);
                for (int i = 0; i < getSelectList().size(); i++) {
                    selectList.get(i).setChecked(false);
                }
                ((MainActivity) getActivity()).hideOptions();
                break;

        }

    }

    private void listener() {

        binding.imgSearchArchive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imgSearchArchive.setVisibility(View.GONE);
                binding.tvArchive.setVisibility(View.INVISIBLE);
                binding.tvArchiveCancel.setVisibility(View.VISIBLE);
                binding.searchArchive.setVisibility(View.VISIBLE);

                binding.searchArchive.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        //showInputMethod(v.findFocus());
                        if (hasFocus) {
                            InputMethodManager imm = (InputMethodManager)
                                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            if (imm != null) {
                                imm.showSoftInput(v, 0);
                            }
                        }
                    }
                });


            }
        });

        binding.tvArchiveCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fileArchiveAdapter.filter("");
                binding.searchArchive.setQuery("", false);
                binding.searchArchive.clearFocus();
                binding.imgSearchArchive.setVisibility(View.VISIBLE);
                binding.tvArchive.setVisibility(View.VISIBLE);
                binding.tvArchiveCancel.setVisibility(View.GONE);
                binding.searchArchive.setVisibility(View.GONE);
            }
        });
        //testVersion();
        binding.imgArchiveBack.setOnClickListener(new View.OnClickListener() {
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

    private void showInputMethod(View view) {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, 0);
        }
    }

    private void search() {
        binding.searchArchive.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fileArchiveAdapter.filter(newText);
                return false;
            }
        });

    }

    private void observerData() {
        viewModel.mutableLiveDataArchive.observe(this, new Observer<List<FileData>>() {
            @Override
            public void onChanged(List<FileData> fileData) {
                if (fileData.size() > 0) {
                    Collections.sort(fileData, new Utils.DateTimeComparator());
                    archiveList.clear();
                    archiveList.addAll(fileData);
                    fileArchiveAdapter.setList(fileData);
                    binding.progressArchive.setVisibility(View.GONE);
                }

            }
        });
    }

    @Override
    protected void onPermissionGranted() {
        initData();

    }

    private void initData() {
        viewModel.getArchive();
    }

    public void testVersion() {
        SevenZip.Version version = SevenZip.getSevenZipVersion();
        Log.i(TAG, "7-zip version: " + version.major + "." + version.minor + "." + version.build + " (" + version.version + "), " + version.date + version.copyright);
        Log.i(TAG, "7-Zip-JBinding version: " + SevenZip.getSevenZipJBindingVersion());
        Log.i(TAG, "Native library initialized: " + SevenZip.isInitializedSuccessfully());

    }

    @Override
    public void onItemClick(int position) {
        /*if (viewModel.mutableLiveDataArchive.getValue() != null) {
            viewModel.mutableLiveDataArchive.getValue().get(position).setChecked(!viewModel.mutableLiveDataArchive.getValue().get(position).isChecked());

            if (fileArchiveAdapter.listSearch.get(position).isChecked()){
                selectList.add(fileArchiveAdapter.listSearch.get(position));
            } else

            for (int i = 0; i < fileArchiveAdapter.listSearch.size(); i++) {
                if (!selectList.get(i).isChecked()) {
                    selectList.remove(i);
                }
            }
            if (getSelectList().size() > 0) {
                ((MainActivity) getActivity()).showOptions(String.valueOf(getSelectList().size()));
            } else {
                ((MainActivity) getActivity()).hideOptions();
            }
        }*/
        archiveList.get(position).setChecked(!archiveList.get(position).isChecked());
        if (archiveList.get(position).isChecked()) {
            selectList.add(archiveList.get(position));
        } else {
            if (getSelectList().size() > 0) {
                for (int i = 0; i < fileArchiveAdapter.listSearch.size(); i++) {
                    for (int j = 0; j < getSelectList().size(); j++) {
                        if (fileArchiveAdapter.listSearch.get(i).getFileName().equals(getSelectList().get(j).getFileName()) && !fileArchiveAdapter.listSearch.get(i).isChecked()) {
                            // holder.binding.imgItemCheck.setImageResource(R.drawable.ic_check_false);
                            fileArchiveAdapter.listSearch.get(i).setChecked(false);
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


    public void testList(File file) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            RandomAccessFileInStream inStream = new RandomAccessFileInStream(randomAccessFile);
            ArchiveOpenCallback callback = new ArchiveOpenCallback();
            IInArchive inArchive = SevenZip.openInArchive(ArchiveFormat.ZIP, inStream, callback);

            ArchiveFormat format = inArchive.getArchiveFormat();
            Log.i(TAG, "Archive format: " + format.getMethodName());

            int itemCount = inArchive.getNumberOfItems();
            Log.i(TAG, "Items in archive: " + itemCount);
            for (int i = 0; i < itemCount; i++) {
                Log.i(TAG, "File " + i + ": " + inArchive.getStringProperty(i, PropID.PATH) + " : " + inArchive.getStringProperty(i, PropID.SIZE));
            }

            inArchive.close();
            inStream.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG, e.getMessage());
        } catch (SevenZipException e) {
            Log.e(TAG, e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public void testExtract(File file) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            RandomAccessFileInStream inStream = new RandomAccessFileInStream(randomAccessFile);
            aArchiveOpenCallback callback = new aArchiveOpenCallback();
            IInArchive inArchive = SevenZip.openInArchive(ArchiveFormat.GZIP, inStream, callback);

            ArchiveExtractCallback extractCallback = new ArchiveExtractCallback();
            inArchive.extract(null, false, extractCallback);

            inArchive.close();
            inStream.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG, e.getMessage());
        } catch (SevenZipException e) {
            Log.e(TAG, e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private class ArchiveOpenCallback implements IArchiveOpenCallback {
        @Override
        public void setTotal(Long files, Long bytes) {
            Log.i(TAG, "Archive open, total work: " + files + " files, " + bytes + " bytes");
        }

        @Override
        public void setCompleted(Long files, Long bytes) {
            Log.i(TAG, "Archive open, completed: " + files + " files, " + bytes + " bytes");
        }
    }

    private class aArchiveOpenCallback implements IArchiveOpenCallback {
        @Override
        public void setTotal(Long files, Long bytes) {
            Log.i(TAG, "Archive open, total work: " + files + " files, " + bytes + " bytes");
        }

        @Override
        public void setCompleted(Long files, Long bytes) {
            Log.i(TAG, "Archive open, completed: " + files + " files, " + bytes + " bytes");
        }
    }

    private class ArchiveExtractCallback implements IArchiveExtractCallback {
        @Override
        public ISequentialOutStream getStream(int index, ExtractAskMode extractAskMode) throws SevenZipException {
            Log.i(TAG, "Extract archive, get stream: " + index + " to: " + extractAskMode);
            SequentialOutStream stream = new SequentialOutStream();
            return stream;
        }

        @Override
        public void prepareOperation(ExtractAskMode extractAskMode) throws SevenZipException {
            Log.i(TAG, "Extract archive, prepare to: " + extractAskMode);
        }

        @Override
        public void setOperationResult(ExtractOperationResult extractOperationResult) throws SevenZipException {
            Log.i(TAG, "Extract archive, completed with: " + extractOperationResult);
            if (extractOperationResult != ExtractOperationResult.OK) {
                throw new SevenZipException(extractOperationResult.toString());
            }
        }

        @Override
        public void setTotal(long total) throws SevenZipException {
            Log.i(TAG, "Extract archive, work planned: " + total);
        }

        @Override
        public void setCompleted(long complete) throws SevenZipException {
            Log.i(TAG, "Extract archive, work completed: " + complete);
        }
    }

    private class SequentialOutStream implements ISequentialOutStream {
        @Override
        public int write(byte[] data) throws SevenZipException {
            if (data == null || data.length == 0) {
                throw new SevenZipException("null data");
            }
            Log.i(TAG, "Data to write: " + data.length);
            return data.length;
        }
    }
   /* public void testSlowExtract(File file) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            RandomAccessFileInStream inStream = new RandomAccessFileInStream(randomAccessFile);
            ArchiveOpenCallback callback = new ArchiveOpenCallback();
            IInArchive inArchive = SevenZip.openInArchive(ArchiveFormat.ZIP, inStream, callback);

            int itemCount = inArchive.getNumberOfItems();
            SequentialOutStream outStream = new SequentialOutStream();
            for (int i = 0; i < itemCount; i++) {
                ExtractOperationResult result = inArchive.extractSlow(i, outStream);
                if (result != ExtractOperationResult.OK) {
                    Log.e(TAG, result.toString());
                }
            }

            inArchive.close();
            inStream.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG, e.getMessage());
        } catch (SevenZipException e) {
            Log.e(TAG, e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private class ArchiveOpenCallback implements IArchiveOpenCallback {
        @Override
        public void setTotal(Long files, Long bytes) {
            Log.i(TAG, "Archive open, total work: " + files + " files, " + bytes + " bytes");
        }

        @Override
        public void setCompleted(Long files, Long bytes) {
            Log.i(TAG, "Archive open, completed: " + files + " files, " + bytes + " bytes");
        }
    }

    private class SequentialOutStream implements ISequentialOutStream {
        @Override
        public int write(byte[] data) throws SevenZipException {
            if (data == null || data.length == 0) {
                throw new SevenZipException("null data");
            }
            Log.i(TAG, "Data to write: " + data.length);
            return data.length;
        }
    }*/

/*
    public void testExtract(File file) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            RandomAccessFileInStream inStream = new RandomAccessFileInStream(randomAccessFile);
            ArchiveOpenCallback callback = new ArchiveOpenCallback();
            IInArchive inArchive = SevenZip.openInArchive(ArchiveFormat.SEVEN_ZIP, inStream, callback);

            ArchiveExtractCallback extractCallback = new ArchiveExtractCallback();
            inArchive.extract(null, false, extractCallback);

            inArchive.close();
            inStream.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG, e.getMessage());
        } catch (SevenZipException e) {
            Log.e(TAG, e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
    }


    private class ArchiveOpenCallback implements IArchiveOpenCallback {
        @Override
        public void setTotal(Long files, Long bytes) {
            Log.i(TAG, "Archive open, total work: " + files + " files, " + bytes + " bytes");
        }

        @Override
        public void setCompleted(Long files, Long bytes) {
            Log.i(TAG, "Archive open, completed: " + files + " files, " + bytes + " bytes");
        }
    }

    private class ArchiveExtractCallback implements IArchiveExtractCallback {
        @Override
        public ISequentialOutStream getStream(int index, ExtractAskMode extractAskMode) throws SevenZipException {
            Log.i(TAG, "Extract archive, get stream: " + index + " to: " + extractAskMode);
            SequentialOutStream stream = new SequentialOutStream();
            return stream;
        }

        @Override
        public void prepareOperation(ExtractAskMode extractAskMode) throws SevenZipException {
            Log.i(TAG, "Extract archive, prepare to: " + extractAskMode);
        }

        @Override
        public void setOperationResult(ExtractOperationResult extractOperationResult) throws SevenZipException {
            Log.i(TAG, "Extract archive, completed with: " + extractOperationResult);
            if (extractOperationResult != ExtractOperationResult.OK) {
                throw new SevenZipException(extractOperationResult.toString());
            }
        }

        @Override
        public void setTotal(long total) throws SevenZipException {
            Log.i(TAG, "Extract archive, work planned: " + total);
        }

        @Override
        public void setCompleted(long complete) throws SevenZipException {

            Log.i(TAG, "Extract archive, work completed: " + complete);
        }
    }

    private class SequentialOutStream implements ISequentialOutStream {
        @Override
        public int write(byte[] data) throws SevenZipException {
            if (data == null || data.length == 0) {
                throw new SevenZipException("null data");
            }
            Log.i(TAG, "Data to write: " + data.length);
            return data.length;
        }
    }
*/


}
