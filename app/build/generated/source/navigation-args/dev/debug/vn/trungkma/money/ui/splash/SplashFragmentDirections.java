package vn.trungkma.money.ui.splash;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import vn.trungkma.money.R;

public class SplashFragmentDirections {
  private SplashFragmentDirections() {
  }

  @NonNull
  public static ActionSplashFragmentToHomeFragment actionSplashFragmentToHomeFragment() {
    return new ActionSplashFragmentToHomeFragment();
  }

  public static class ActionSplashFragmentToHomeFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionSplashFragmentToHomeFragment() {
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionSplashFragmentToHomeFragment setMyArg(@NonNull String myArg) {
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
        __result.putString("myArg", "0");
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_splashFragment_to_homeFragment;
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
      ActionSplashFragmentToHomeFragment that = (ActionSplashFragmentToHomeFragment) object;
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
      return "ActionSplashFragmentToHomeFragment(actionId=" + getActionId() + "){"
          + "myArg=" + getMyArg()
          + "}";
    }
  }
}
