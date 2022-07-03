package vn.trungkma.money.databinding;
import vn.trungkma.money.R;
import vn.trungkma.money.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentExplorerBindingImpl extends FragmentExplorerBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.img_explorer_back, 1);
        sViewsWithIds.put(R.id.tv_explorer, 2);
        sViewsWithIds.put(R.id.img_search_explorer, 3);
        sViewsWithIds.put(R.id.tv_explorer_cancel, 4);
        sViewsWithIds.put(R.id.search_explorer, 5);
        sViewsWithIds.put(R.id.constraint_explorer_frame, 6);
        sViewsWithIds.put(R.id.rl_explorer_photo, 7);
        sViewsWithIds.put(R.id.img_explorer_photo, 8);
        sViewsWithIds.put(R.id.rl_explorer_doc, 9);
        sViewsWithIds.put(R.id.img_explorer_doc, 10);
        sViewsWithIds.put(R.id.rl_explorer_music, 11);
        sViewsWithIds.put(R.id.img_explorer_music, 12);
        sViewsWithIds.put(R.id.rl_explorer_video, 13);
        sViewsWithIds.put(R.id.img_explorer_video, 14);
        sViewsWithIds.put(R.id.rl_explorer_download, 15);
        sViewsWithIds.put(R.id.img_explorer_download, 16);
        sViewsWithIds.put(R.id.rl_explorer_apk, 17);
        sViewsWithIds.put(R.id.img_explorer_apk, 18);
        sViewsWithIds.put(R.id.progress_explorer, 19);
        sViewsWithIds.put(R.id.tv_explorer_noData, 20);
        sViewsWithIds.put(R.id.recycler_explorer, 21);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentExplorerBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 22, sIncludes, sViewsWithIds));
    }
    private FragmentExplorerBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[6]
            , (android.widget.ImageView) bindings[18]
            , (android.widget.ImageView) bindings[1]
            , (android.widget.ImageView) bindings[10]
            , (android.widget.ImageView) bindings[16]
            , (android.widget.ImageView) bindings[12]
            , (android.widget.ImageView) bindings[8]
            , (android.widget.ImageView) bindings[14]
            , (android.widget.ImageView) bindings[3]
            , (android.widget.ProgressBar) bindings[19]
            , (androidx.recyclerview.widget.RecyclerView) bindings[21]
            , (android.widget.RelativeLayout) bindings[17]
            , (android.widget.RelativeLayout) bindings[9]
            , (android.widget.RelativeLayout) bindings[15]
            , (android.widget.RelativeLayout) bindings[11]
            , (android.widget.RelativeLayout) bindings[7]
            , (android.widget.RelativeLayout) bindings[13]
            , (androidx.appcompat.widget.SearchView) bindings[5]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[20]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}