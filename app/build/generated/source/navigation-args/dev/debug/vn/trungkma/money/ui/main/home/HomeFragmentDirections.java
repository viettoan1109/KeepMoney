package vn.trungkma.money.ui.main.home;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import vn.trungkma.money.R;

public class HomeFragmentDirections {
  private HomeFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionHomeFragmentToExplorerFragment() {
    return new ActionOnlyNavDirections(R.id.action_homeFragment_to_explorerFragment);
  }

  @NonNull
  public static NavDirections actionHomeFragmentToArchiveFragment() {
    return new ActionOnlyNavDirections(R.id.action_homeFragment_to_archiveFragment);
  }

  @NonNull
  public static NavDirections actionHomeFragmentToBrowsrFragment() {
    return new ActionOnlyNavDirections(R.id.action_homeFragment_to_browsrFragment);
  }

  @NonNull
  public static ActionHomeFragmentToPlayPhotoFragment actionHomeFragmentToPlayPhotoFragment() {
    return new ActionHomeFragmentToPlayPhotoFragment();
  }

  public static class ActionHomeFragmentToPlayPhotoFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionHomeFragmentToPlayPhotoFragment() {
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionHomeFragmentToPlayPhotoFragment setMyArg(@NonNull String myArg) {
      if (myArg == null) {
        throw new IllegalArgumentException("Argument \"myArg\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("myArg", myArg);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("myArg")) {
        String myArg = (String) arguments.get("myArg");
        __result.putString("myArg", myArg);
      } else {
        __result.putString("myArg", "1");
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_homeFragment_to_playPhotoFragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getMyArg() {
      return (String) arguments.get("myArg");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionHomeFragmentToPlayPhotoFragment that = (ActionHomeFragmentToPlayPhotoFragment) object;
      if (arguments.containsKey("myArg") != that.arguments.containsKey("myArg")) {
        return false;
      }
      if (getMyArg() != null ? !getMyArg().equals(that.getMyArg()) : that.getMyArg() != null) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (getMyArg() != null ? getMyArg().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionHomeFragmentToPlayPhotoFragment(actionId=" + getActionId() + "){"
          + "myArg=" + getMyArg()
          + "}";
    }
  }
}
