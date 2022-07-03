package vn.trungkma.money.databinding;
import vn.trungkma.money.R;
import vn.trungkma.money.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentHomeBindingImpl extends FragmentHomeBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.guideline, 1);
        sViewsWithIds.put(R.id.img_home_menu, 2);
        sViewsWithIds.put(R.id.img_home_gift, 3);
        sViewsWithIds.put(R.id.rl_home_explorer, 4);
        sViewsWithIds.put(R.id.img_home_fileFolder, 5);
        sViewsWithIds.put(R.id.rl_home_archive, 6);
        sViewsWithIds.put(R.id.img_home_archive, 7);
        sViewsWithIds.put(R.id.guideline_2, 8);
        sViewsWithIds.put(R.id.rl_home_ads, 9);
        sViewsWithIds.put(R.id.tv_home_recent, 10);
        sViewsWithIds.put(R.id.tv_home_showRecent, 11);
        sViewsWithIds.put(R.id.progress_home, 12);
        sViewsWithIds.put(R.id.tv_home_noData, 13);
        sViewsWithIds.put(R.id.recycler_home_recent, 14);
        sViewsWithIds.put(R.id.nav_menu, 15);
        sViewsWithIds.put(R.id.guideline_3, 16);
        sViewsWithIds.put(R.id.img_nav_logo, 17);
        sViewsWithIds.put(R.id.tv_nav_azip, 18);
        sViewsWithIds.put(R.id.ln_nav_giff, 19);
        sViewsWithIds.put(R.id.ln_nav_policy, 20);
        sViewsWithIds.put(R.id.ln_nav_rate, 21);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentHomeBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 22, sIncludes, sViewsWithIds));
    }
    private FragmentHomeBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.constraintlayout.widget.Guideline) bindings[1]
            , (androidx.constraintlayout.widget.Guideline) bindings[8]
            , (androidx.constraintlayout.widget.Guideline) bindings[16]
            , (android.widget.ImageView) bindings[7]
            , (android.widget.ImageView) bindings[5]
            , (android.widget.ImageView) bindings[3]
            , (android.widget.ImageView) bindings[2]
            , (android.widget.ImageView) bindings[17]
            , (android.widget.LinearLayout) bindings[19]
            , (android.widget.LinearLayout) bindings[20]
            , (android.widget.LinearLayout) bindings[21]
            , (androidx.drawerlayout.widget.DrawerLayout) bindings[0]
            , (com.google.android.material.navigation.NavigationView) bindings[15]
            , (android.widget.ProgressBar) bindings[12]
            , (androidx.recyclerview.widget.RecyclerView) bindings[14]
            , (android.widget.RelativeLayout) bindings[9]
            , (android.widget.RelativeLayout) bindings[6]
            , (android.widget.RelativeLayout) bindings[4]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[10]
            , (android.widget.ToggleButton) bindings[11]
            , (android.widget.TextView) bindings[18]
            );
        this.navHomeDrawer.setTag(null);
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