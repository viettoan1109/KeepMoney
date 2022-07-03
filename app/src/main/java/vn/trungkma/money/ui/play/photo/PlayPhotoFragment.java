package vn.trungkma.money.ui.play.photo;

import android.net.Uri;
import android.os.Bundle;

import android.view.View;

import java.io.File;

import vn.trungkma.money.R;
import vn.trungkma.money.databinding.FragmentPlayPhotoBinding;
import vn.trungkma.money.ui.base.BaseBindingFragment;

public class PlayPhotoFragment extends BaseBindingFragment<FragmentPlayPhotoBinding, PlayPhotoViewModel> {

    private String uri;

    @Override
    protected Class<PlayPhotoViewModel> getViewModel() {
        return PlayPhotoViewModel.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_play_photo;
    }



    @Override
    protected void onCreatedView(View view, Bundle savedInstanceState) {

        uri = "/storage/emulated/0/MyLibDirectoryExample/Foto/FB_IMG_1636556854763.jpg";

            File file = new File(uri);
            Uri uri = Uri.fromFile(file);
            binding.photoView.setImageURI(uri);


    }

    @Override
    protected void onPermissionGranted() {

    }
}