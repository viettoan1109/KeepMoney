package vn.trungkma.money.ui.explorer.doc;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import vn.trungkma.money.data.model.FileData;
import vn.trungkma.money.data.repository.DocumentRepository;
import vn.trungkma.money.ui.base.BaseViewModel;

@HiltViewModel
public class DocViewModel extends BaseViewModel {
    private DocumentRepository documentRepository;
    public MutableLiveData<List<FileData>> mutableLiveDataDocument = new MutableLiveData<>();
    @Inject
    public DocViewModel(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public void getDocument() {
       documentRepository.getDocument().subscribe(new SingleObserver<List<FileData>>() {
           @Override
           public void onSubscribe(@NonNull Disposable d) {
               compositeDisposable.add(d);
           }

           @Override
           public void onSuccess(@NonNull List<FileData> fileData) {
mutableLiveDataDocument.postValue(fileData);
           }

           @Override
           public void onError(@NonNull Throwable e) {

           }
       });
    }
}
