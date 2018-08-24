package com.nixbyte.project.activities.registration;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.github.gorbin.asne.core.SocialNetworkManager;
import com.github.gorbin.asne.facebook.FacebookSocialNetwork;
import com.github.gorbin.asne.odnoklassniki.OkSocialNetwork;
import com.github.gorbin.asne.vk.VkSocialNetwork;
import com.google.android.material.textfield.TextInputLayout;
import com.nixbyte.project.R;
import com.nixbyte.project.modules.actionbar.ActionBarConstructor;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.modules.activity.Viewable;
import com.vk.sdk.VKScope;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.fragment.app.FragmentTransaction;
import ru.ok.android.sdk.util.OkScope;

import static com.nixbyte.project.activities.autorization.AutorizationFragment.mSocialNetworkManager;


/**
 * Created by nixbyte on 07.09.16.
 */
public class RegistrationView implements Viewable<String> {

    private ActionBarConstructor.Builder actionBarBulder;
    View rootView;
    public ActionBarConstructor actionBar;
    static Context context;

    public EditText password,email;
    public RegistrationView(AbstractActivity activity, View rootView) {
        context = rootView.getContext();

        FragmentManager manager=activity.getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.container, new RegistrationFragment()).commit();

        this.actionBarBulder = new ActionBarConstructor.Builder(activity)
            .title(R.string.registratiom)
            .menu(R.menu.empty);
        this.rootView=rootView;
    }


    public void onAttach(String authorizationData) {
        this.actionBar = actionBarBulder.build();
        this.actionBar.setActionBar();
    }
}
