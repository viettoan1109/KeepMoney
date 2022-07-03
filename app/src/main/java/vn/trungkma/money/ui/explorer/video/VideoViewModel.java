package vn.trungkma.money.ui.explorer.video;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import vn.trungkma.money.data.model.FileData;
import vn.trungkma.money.data.repository.VideoRepository;
import vn.trungkma.money.ui.base.BaseViewModel;

@HiltViewModel
public class VideoViewModel extends BaseViewModel {

    private VideoRepository videoRepository;
    public MutableLiveData<List<FileData>> mutableLiveDataVideo = new MutableLiveData<>();

    @Inject
    public VideoViewModel(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public void getVideo() {
        videoRepository.getVideo().subscribe(new SingleObserver<List<FileData>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull List<FileData> fileData) {
                mutableLiveDataVideo.postValue(fileData);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }
}
