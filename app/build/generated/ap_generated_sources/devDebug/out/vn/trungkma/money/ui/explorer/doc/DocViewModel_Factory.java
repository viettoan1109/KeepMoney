// Generated by Dagger (https://dagger.dev).
package vn.trungkma.money.ui.explorer.doc;

import dagger.internal.Factory;
import javax.inject.Provider;
import vn.trungkma.money.data.repository.DocumentRepository;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DocViewModel_Factory implements Factory<DocViewModel> {
  private final Provider<DocumentRepository> documentRepositoryProvider;

  public DocViewModel_Factory(Provider<DocumentRepository> documentRepositoryProvider) {
    this.documentRepositoryProvider = documentRepositoryProvider;
  }

  @Override
  public DocViewModel get() {
    return newInstance(documentRepositoryProvider.get());
  }

  public static DocViewModel_Factory create(
      Provider<DocumentRepository> documentRepositoryProvider) {
    return new DocViewModel_Factory(documentRepositoryProvider);
  }

  public static DocViewModel newInstance(DocumentRepository documentRepository) {
    return new DocViewModel(documentRepository);
  }
}