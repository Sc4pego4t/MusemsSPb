package com.nixbyte.project.modules.activity;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.util.Log;
import android.view.View;

import com.github.gorbin.asne.core.SocialNetworkManager;
import com.github.gorbin.asne.core.listener.OnLoginCompleteListener;
import com.github.gorbin.asne.core.listener.OnRequestSocialPersonCompleteListener;
import com.github.gorbin.asne.core.persons.SocialPerson;
import com.github.gorbin.asne.facebook.FacebookSocialNetwork;
import com.github.gorbin.asne.odnoklassniki.OkSocialNetwork;
import com.github.gorbin.asne.vk.VkSocialNetwork;
import com.nixbyte.project.R;
import com.vk.sdk.VKScope;

import java.util.ArrayList;
import java.util.Arrays;

import ru.ok.android.sdk.util.OkScope;

import static com.nixbyte.project.activities.autorization.AutorizationFragment.mSocialNetworkManager;

public class FragmentParent extends Fragment implements View.OnClickListener,SocialNetworkManager.OnInitializationCompleteListener, OnLoginCompleteListener, OnRequestSocialPersonCompleteListener {

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onSocialNetworkManagerInitialized() {

    }

    @Override
    public void onLoginSuccess(int socialNetworkID) {
        AbstractActivity.hideProgress();
    }

    @Override
    public void onRequestSocialPersonSuccess(int socialNetworkId, SocialPerson socialPerson) {

    }

    @Override
    public void onError(int socialNetworkID, String requestID, String errorMessage, Object data) {
        AbstractActivity.hideProgress();
        Log.e("gg","err");
    }
    public static SocialNetworkManager initManager(Fragment fr, int which, Context context, FragmentManager manager, String tag) {
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


}