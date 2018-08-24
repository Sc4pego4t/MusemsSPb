package com.nixbyte.project.activities.autorization;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.view.View;

import com.github.gorbin.asne.core.SocialNetworkManager;
import com.github.gorbin.asne.facebook.FacebookSocialNetwork;
import com.github.gorbin.asne.odnoklassniki.OkSocialNetwork;
import com.github.gorbin.asne.vk.VkSocialNetwork;
import com.nixbyte.project.R;
import com.nixbyte.project.modules.actionbar.ActionBarConstructor;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.modules.activity.Viewable;
import com.vk.sdk.VKScope;

import java.util.ArrayList;
import java.util.Arrays;

import ru.ok.android.sdk.util.OkScope;
import static com.nixbyte.project.activities.autorization.AutorizationFragment.mSocialNetworkManager;


/**
 * Created by nixbyte on 07.09.16.
 */
public class AutorizationView implements Viewable<String> {

    static FragmentManager manager;
    private ActionBarConstructor.Builder actionBarBulder;
    public ActionBarConstructor actionBar;
    static Context context;
    static Fragment fr;

    View rootView;

    public AutorizationView(AbstractActivity activity, View rootView, FragmentManager manager) {
        AutorizationView.manager =manager;
        fr = new AutorizationFragment();
        context = rootView.getContext();
        this.actionBarBulder = new ActionBarConstructor.Builder(activity)
            .title(R.string.autorize)
            .menu(R.menu.empty);
        manager.beginTransaction().replace(R.id.container, fr).commit();
        this.rootView=rootView;
    }

    public static SocialNetworkManager initManager(String tag, int which) {
        //Get Keys for initiate SocialNetworks
        String VK_KEY = context.getString(R.string.vk_app_id);
        String OK_APP_ID = context.getString(R.string.ok_app_id);
        String OK_PUBLIC_KEY = context.getString(R.string.ok_public_key);
        String OK_SECRET_KEY = context.getString(R.string.ok_secret_key);

        //Chose permissions

        String[] vkScope = new String[]{
                VKScope.FRIENDS,
                VKScope.WALL,
                VKScope.PHOTOS,
                VKScope.NOHTTPS,
                VKScope.STATUS,
        };

        String[] okScope = new String[]{
                OkScope.VALUABLE_ACCESS
        };
        ArrayList<String> fbScope = new ArrayList<String>();
        fbScope.addAll(Arrays.asList("public_profile, email, user_friends"));


        //Use manager to manage SocialNetworks
        mSocialNetworkManager = new SocialNetworkManager();

        //Init and add to manager VkSocialNetwork
        VkSocialNetwork vkNetwork = new VkSocialNetwork(fr, VK_KEY, vkScope);
        OkSocialNetwork okNetwork = new OkSocialNetwork(fr, OK_APP_ID, OK_PUBLIC_KEY, OK_SECRET_KEY, okScope);
        FacebookSocialNetwork faceNetwork = new FacebookSocialNetwork(fr, fbScope);

        mSocialNetworkManager.addSocialNetwork(vkNetwork);
        mSocialNetworkManager.addSocialNetwork(okNetwork);
        mSocialNetworkManager.addSocialNetwork(faceNetwork);

        //Initiate every network from mSocialNetworkManager
        manager.beginTransaction().add(mSocialNetworkManager, tag).commit();
        return mSocialNetworkManager;

    }

    public void onAttach(String authorizationData) {
        this.actionBar = actionBarBulder.build();
        this.actionBar.setActionBar();
    }
}
