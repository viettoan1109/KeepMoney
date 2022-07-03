package vn.trungkma.money.ui.main;

import static android.os.Build.VERSION.SDK_INT;
import static vn.trungkma.money.common.Constant.SCREEN_APK;
import static vn.trungkma.money.common.Constant.SCREEN_DOC;
import static vn.trungkma.money.common.Constant.SCREEN_DOWNLOAD;
import static vn.trungkma.money.common.Constant.SCREEN_MUSIC;
import static vn.trungkma.money.common.Constant.SCREEN_PHOTO;
import static vn.trungkma.money.common.Constant.SCREEN_VIDEO;
import static vn.trungkma.money.utils.Utils.showDialogPermissionManageExternalStorage;
import static vn.trungkma.money.utils.Utils.showDialogPermissionWriteExternalStorage;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.multidex.BuildConfig;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.NavHostFragment;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import timber.log.Timber;
import vn.trungkma.money.R;
import vn.trungkma.money.common.Constant;
import vn.trungkma.money.common.models.EventPermission;
import vn.trungkma.money.data.model.FileData;
import vn.trungkma.money.databinding.ActivityMainBinding;
import vn.trungkma.money.ui.base.BaseBindingActivity;
import vn.trungkma.money.ui.dialog.DialogCompress;
import vn.trungkma.money.ui.dialog.DialogDelete;
import vn.trungkma.money.ui.dialog.DialogExtract;
import vn.trungkma.money.ui.dialog.DialogRename;

public class MainActivity extends BaseBindingActivity<ActivityMainBinding, MainViewModel> {
    public static final List<FileData> selectList = new LinkedList<>();
    private final static String[] permissions = {"android.permission.MANAGE_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE",};
    public NavController navControllerMain;
    public NavHostFragment navHostFragmentMain;
    public boolean check = false;
    public String rename;
    public String pathFile;
    private boolean isManageExternalStorage = false;
    private Handler handler;
    private NavGraph graph;

    public static List<FileData> getSelectList() {
        return selectList;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public Class<MainViewModel> getViewModel() {
        return MainViewModel.class;
    }

    @Override
    public void setupView(Bundle savedInstanceState) {
        checkPermission();
        navHostFragmentMain = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_main_fragment);
        if (navHostFragmentMain != null) {
            navControllerMain = navHostFragmentMain.getNavController();
        }

        //   superBottomSheetFragment.sheetContainer.layout();
        //superBottomSheetFragment.show(getSupportFragmentManager(), "bottomsheet");
        handler = new Handler(this.getMainLooper());
        handler.postDelayed(() -> changeMainScreen(R.id.homeFragment, null), 2000);
        createFolder();
        listener();
    }

    @Override
    public void setupData() {
        if (isAllPermissionGranted()) {
            getFileRecent();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (SDK_INT > Build.VERSION_CODES.Q) {
            if ((!Environment.isExternalStorageManager())) {
                EventBus.getDefault().post(new EventPermission(false));
                showDialogPermissionManageExternalStorage(this, getResources().getString(R.string.permission_manage_external_storage));
            } else if ((Environment.isExternalStorageManager())) {
                EventBus.getDefault().post(new EventPermission(true));
                if (!isManageExternalStorage) {
                    isManageExternalStorage = true;
                    checkPermissionWriteExternalStorage();
                }
            } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                EventBus.getDefault().post(new EventPermission(false));
            } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                EventBus.getDefault().post(new EventPermission(false));
            } else {
                getFileRecent();
            }
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                EventBus.getDefault().post(new EventPermission(false));
            } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                EventBus.getDefault().post(new EventPermission(false));
            } else {
                getFileRecent();
            }
        }
    }

    private void createFolder() {

        File directory = new File(Environment.getExternalStorageDirectory() + File.separator + "Zazip/Extracted");
        directory.mkdirs();
        File directory1 = new File(Environment.getExternalStorageDirectory() + File.separator + "Zazip/Compressed");
        directory1.mkdirs();
    }

    private void listener() {

        binding.tvMainDeselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideOptions();
            }
        });

        binding.lnExplorerReName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getSelectList().size() == 1) {
                    rename = selectList.get(0).getFilePath();
                    Toast.makeText(getApplicationContext(), rename, Toast.LENGTH_SHORT).show();
                    DialogRename dialogRename = new DialogRename(view.getContext(), selectList.get(0).getFilePath(), selectList.get(0).getFileName());
                    dialogRename.show();

                }
            }
        });

        binding.lnExplorerCoppy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBrowseScreen(R.id.browseFragment);
                hideOptions();
            }
        });

        binding.lnExplorerDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogDelete dialogDelete = new DialogDelete(view.getContext(), selectList);
                dialogDelete.show();
                dialogDelete.setOnItemClickedListener(new DialogDelete.OnItemClickedListener() {
                    @Override
                    public void onYesClick(View view) {
                        // hideOptions();
                        //changeMainScreen(R.id.homeFragment, null);
                    }
                });
            }
        });

        binding.lnExplorerExtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getSelectList().size() == 1) {
                    DialogExtract dialogExtract = new DialogExtract(view.getContext(), selectList);
                    dialogExtract.show();

                }

            }
        });

        binding.lnExplorerCompress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogCompress dialogCompress = new DialogCompress(view.getContext(), selectList);
                dialogCompress.show();
                dialogCompress.setOnItemClickedListener(new DialogCompress.OnItemClickedListener() {
                    @Override
                    public void onClick(View view) {
                        // changeBrowseScreen(R.id.browseFragment);
                    }
                });
            }
        });

        binding.lnExplorerShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String shareMessage = "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch (Exception e) {
                    //e.toString();
                }
            }
        });

     /*   binding.rlMainSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.rlMainListSelect.setVisibility(View.VISIBLE);
            }
        });*/

    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    public void showOptions(String select) {
        binding.scrollOptions.setVisibility(View.VISIBLE);
        binding.rlMainSelect.setVisibility(View.VISIBLE);
        binding.tvMainSelect.setText("Selectecd " + String.valueOf(getSelectList().size()) + " items");
        if (getSelectList().size() > 1) {
            binding.imgMainReName.setImageResource(R.drawable.ic_rename_gray);
            binding.imgMainExtract.setImageResource(R.drawable.ic_extract_gray);

            binding.tvMainReName.setTextColor(getResources().getColor(R.color.text_gray));
            binding.tvMainExtract.setTextColor(getResources().getColor(R.color.text_gray));
        } else if (getSelectList().size() == 1) {
            binding.imgMainReName.setImageResource(R.drawable.ic_rename);
            binding.imgMainExtract.setImageResource(R.drawable.ic_extract);

            binding.tvMainReName.setTextColor(getResources().getColor(R.color.text_blue));
            binding.tvMainExtract.setTextColor(getResources().getColor(R.color.text_blue));

        }

    }

    public void reStart() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void hideOptions() {
        if (selectList.size() > 0) {
            for (int i = 0; i < selectList.size(); i++) {
                selectList.remove(i);
            }
        }
        binding.scrollOptions.setVisibility(View.GONE);
        binding.rlMainSelect.setVisibility(View.GONE);
    }

    public void showToast(String a) {
        Toast.makeText(this, a, Toast.LENGTH_SHORT).show();
    }

    /*
    public void getApk(String s) {
        EventBus.getDefault().post(new EventPermission(true));
        viewModel.getApk(getApplicationContext(), MediaStore.Files.FileColumns.DATE_ADDED + s);
    }*/

    public void getFileRecent() {
        EventBus.getDefault().post(new EventPermission(true));
        viewModel.getFileRecent();
    }

    public void switchNavGraph(int navResourceId) {
        NavHostFragment finalHost = NavHostFragment.create(navResourceId);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_main_fragment, finalHost)
                .setPrimaryNavigationFragment(finalHost)
                .commit();
    }

    public void changeScreenExplorer(int idScreen) {
        switch (idScreen) {
            case SCREEN_DOC:
                changeScreen(R.id.docFragment, null);
                break;
            case SCREEN_PHOTO:
                changeScreen(R.id.photoFragment, null);
                break;
            case SCREEN_MUSIC:
                changeScreen(R.id.musicFragment, null);
                break;
            case SCREEN_DOWNLOAD:
                changeScreen(R.id.downloadFragment, null);
                break;
            case SCREEN_VIDEO:
                changeScreen(R.id.videoFragment, null);
                break;
            case SCREEN_APK:
                changeScreen(R.id.apkFragment, null);
                break;

            default:
                changeScreen(R.id.homeFragment, null);
                break;
        }
    }

    public int getCurrentInNavGraph() {
        NavDestination currentDes = navControllerMain.getCurrentDestination();
        return currentDes != null ? currentDes.getId() : -1;
    }

    public void changeMainScreen(int idScreen, Bundle bundle) {
        if (graph == null) {
            graph = navControllerMain.getNavInflater().inflate(R.navigation.main_nav);
        }
        graph.setStartDestination(idScreen);
        navControllerMain.setGraph(graph, bundle);
    }

    public void changePlayPhotoScreen(int idScreen, String value) {
        Bundle bundle = new Bundle();
        bundle.putString("uri", value);
        changeScreen(idScreen, null);
    }

    public void changeBrowseScreen(int idScreen) {
        changeScreen(idScreen, null);
    }

    public void changeExplorerScreen(int idScreen) {
        changeScreen(idScreen, null);
    }

    public void changeArchiveScreen(int idScreen) {
        changeScreen(idScreen, null);
    }

    public void changeScreen(int idStart, Bundle bundle) {

        navControllerMain.navigate(idStart, bundle);
    }


    private void checkPermissionWriteExternalStorage() {
        if (SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    showDialogWriteExternalPermission(R.string.please_grants_permission);
                } else if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showDialogWriteExternalPermission(R.string.please_grant_permission);
                } else {
                    EventBus.getDefault().post(new EventPermission(false));
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.MANAGE_EXTERNAL_STORAGE}, 1);
                }
            } else {
                getFileRecent();
            }
        } else {
            getFileRecent();
        }
    }

    public void showDialogWriteExternalPermission(int p) {
        EventBus.getDefault().post(new EventPermission(false));
        showDialogPermissionWriteExternalStorage(this, getResources().getString(p));
    }

    private void onPermissionGranted() {
        setupData();
    }

    public void checkPermission() {
        if (SDK_INT > Build.VERSION_CODES.Q) {
            if (!Environment.isExternalStorageManager()) {
                EventBus.getDefault().post(new EventPermission(false));
                showDialogPermissionManageExternalStorage(this, getResources().getString(R.string.permission_manage_external_storage));
            } else {
                EventBus.getDefault().post(new EventPermission(true));
                if (!isManageExternalStorage) {
                    isManageExternalStorage = true;
                    checkPermissionWriteExternalStorage();
                }
            }
        } else {
            checkPermissionWriteExternalStorage();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constant.WRITE_REQUEST_CODE) {
            if (SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    getFileRecent();
                } else {
                    if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        showDialogWriteExternalPermission(R.string.please_grants_permission);
                    } else if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        showDialogWriteExternalPermission(R.string.please_grant_permission);
                    }
                }
            }
        }

    }

    protected boolean isAllPermissionGranted() {
        boolean isNotGranted = false;
        for (String permission :
                permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                isNotGranted = true;
            }
        }
        Timber.e("isAllPermissionGranted %b", isNotGranted);
        return !isNotGranted;
    }

}