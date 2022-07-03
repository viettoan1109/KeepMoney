package vn.trungkma.money.databinding;
import vn.trungkma.money.R;
import vn.trungkma.money.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityMainBindingImpl extends ActivityMainBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.nav_main_fragment, 1);
        sViewsWithIds.put(R.id.rl_main_list_select, 2);
        sViewsWithIds.put(R.id.recycler_main_list_select, 3);
        sViewsWithIds.put(R.id.rl_main_select, 4);
        sViewsWithIds.put(R.id.img_main_show, 5);
        sViewsWithIds.put(R.id.tv_main_select, 6);
        sViewsWithIds.put(R.id.tv_main_deselect, 7);
        sViewsWithIds.put(R.id.scroll_options, 8);
        sViewsWithIds.put(R.id.ln_main_options, 9);
        sViewsWithIds.put(R.id.ln_explorer_reName, 10);
        sViewsWithIds.put(R.id.img_main_reName, 11);
        sViewsWithIds.put(R.id.tv_main_reName, 12);
        sViewsWithIds.put(R.id.ln_explorer_coppy, 13);
        sViewsWithIds.put(R.id.img_main_copy, 14);
        sViewsWithIds.put(R.id.tv_main_copy, 15);
        sViewsWithIds.put(R.id.ln_explorer_extract, 16);
        sViewsWithIds.put(R.id.img_main_extract, 17);
        sViewsWithIds.put(R.id.tv_main_extract, 18);
        sViewsWithIds.put(R.id.ln_explorer_compress, 19);
        sViewsWithIds.put(R.id.img_main_compress, 20);
        sViewsWithIds.put(R.id.tv_main_compress, 21);
        sViewsWithIds.put(R.id.ln_explorer_share, 22);
        sViewsWithIds.put(R.id.img_main_share, 23);
        sViewsWithIds.put(R.id.tv_main_share, 24);
        sViewsWithIds.put(R.id.ln_explorer_delete, 25);
        sViewsWithIds.put(R.id.img_main_delete, 26);
        sViewsWithIds.put(R.id.tv_main_delete, 27);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityMainBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 28, sIncludes, sViewsWithIds));
    }
    private ActivityMainBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageView) bindings[20]
            , (android.widget.ImageView) bindings[14]
            , (android.widget.ImageView) bindings[26]
            , (android.widget.ImageView) bindings[17]
            , (android.widget.ImageView) bindings[11]
            , (android.widget.ImageView) bindings[23]
            , (android.widget.ImageView) bindings[5]
            , (android.widget.LinearLayout) bindings[19]
            , (android.widget.LinearLayout) bindings[13]
            , (android.widget.LinearLayout) bindings[25]
            , (android.widget.LinearLayout) bindings[16]
            , (android.widget.LinearLayout) bindings[10]
            , (android.widget.LinearLayout) bindings[22]
            , (android.widget.LinearLayout) bindings[9]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[0]
            , (androidx.fragment.app.FragmentContainerView) bindings[1]
            , (androidx.recyclerview.widget.RecyclerView) bindings[3]
            , (android.widget.RelativeLayout) bindings[2]
            , (android.widget.RelativeLayout) bindings[4]
            , (android.widget.HorizontalScrollView) bindings[8]
            , (android.widget.TextView) bindings[21]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[27]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[18]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[24]
            );
        this.motionLayoutContainer.setTag(null);
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