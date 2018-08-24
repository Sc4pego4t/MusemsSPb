package com.nixbyte.project.activities.changeInfo;

import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.gorbin.asne.core.SocialNetwork;
import com.github.gorbin.asne.core.SocialNetworkManager;
import com.github.gorbin.asne.core.persons.SocialPerson;
import com.github.gorbin.asne.facebook.FacebookSocialNetwork;
import com.github.gorbin.asne.odnoklassniki.OkSocialNetwork;
import com.github.gorbin.asne.vk.VkSocialNetwork;
import com.nixbyte.project.R;
import com.nixbyte.project.modules.activity.FragmentParent;
import com.nixbyte.project.utils.ApiFactory;
import com.nixbyte.project.utils.creatingObservers.CreatingObservers;

public class ChangeInfoFragment extends FragmentParent {
    public ChangeInfoFragment(){

    }
    public static final String CHANGE_TAG = "change";
    View rootView;
    public static SocialNetworkManager mSocialNetworkManager;
    Button vk,face,ok;
    ChangeInfoFragment fragment=this;
    String userid;

    TextView accept;

    public EditText password,email;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.user_fragment, container, false);
        vk=rootView.findViewById(R.id.vk);
        ok=rootView.findViewById(R.id.ok);
        face=rootView.findViewById(R.id.face);

        password=rootView.findViewById(R.id.et2);
        email=rootView.findViewById(R.id.et1);

        accept=rootView.findViewById(R.id.accept);

        accept.setOnClickListener(this);
        vk.setOnClickListener(this);
        ok.setOnClickListener(this);
        face.setOnClickListener(this);

        TextView tv=rootView.findViewById(R.id.usernum);
        userid= ((ChangeInfoActivity) getActivity()).autorized;
        Log.e("gg",userid+"//123213");
        tv.setText(userid);


        ApiFactory.returnInfo(userid).subscribe(
                CreatingObservers.userInfoObserver(
                        (ChangeInfoActivity) getActivity(),
                        fragment,
                        null,
                        "Ошибка")
        );

        mSocialNetworkManager=initManager(this,2,getContext(),getFragmentManager(),CHANGE_TAG  );
        mSocialNetworkManager.setOnInitializationCompleteListener(this);

        //if manager exist - get and setup login only for initialized SocialNetworks
        return rootView;
    }

    @Override
    public void onSocialNetworkManagerInitialized() {
        //when init SocialNetworks - get and setup login only for initialized SocialNetworks
        for (SocialNetwork socialNetwork : mSocialNetworkManager.getInitializedSocialNetworks()) {
            socialNetwork.setOnLoginCompleteListener(this);
        }
    }
    @Override
    public void onLoginSuccess(int networkId) {
        ChangeInfoActivity.hideProgress();
//        if((ChangeInfoActivity)getActivity()).fragm== FragmEnum.User) {
//            SocialNetwork socialNetwork = mSocialNetworkManager.getSocialNetwork(networkId);
//            socialNetwork.setOnRequestCurrentPersonCompleteListener(this);
//            socialNetwork.requestCurrentPerson();
//        }
    }

    @Override
    public void onRequestSocialPersonSuccess(int socialNetworkId, SocialPerson socialPerson) {

        ApiFactory.socialsConnect(socialPerson.id+"",socialNetworkId+"",userid).subscribe(
                CreatingObservers.changeUserInfoObserver(
                        (ChangeInfoActivity) getActivity(),
                        "Аккаунт социальной сети присоединен",
                        "Данный аккаунт социальной сети уже используется",
                        true
                )
        );
        mSocialNetworkManager.getSocialNetwork(socialNetworkId).logout();

    }
    @Override
    public void onClick(View view) {
        int networkId = 0;
        switch (view.getId()) {
            case R.id.vk:
                networkId = VkSocialNetwork.ID;
                break;
            case R.id.ok:
                networkId= OkSocialNetwork.ID;
                break;
            case R.id.face:
                networkId= FacebookSocialNetwork.ID;
                break;
            case R.id.accept: {
                String password=this.password.getText().toString(),
                        email=this.email.getText().toString();

                ApiFactory.changeInfo(userid,email,password).subscribe(
                        CreatingObservers.changeUserInfoObserver(
                                (ChangeInfoActivity)getActivity(),
                                "Данные изменены",
                                "Данный email адрес уже используется",
                                true)
                );
                return;
            }

        }
        SocialNetwork socialNetwork = mSocialNetworkManager.getSocialNetwork(networkId);
        if (!socialNetwork.isConnected()) {

            if (networkId != 0) {
                socialNetwork.requestLogin();
                ChangeInfoActivity.showProgress();
            } else {
                Toast.makeText(getActivity(), "Wrong networkId", Toast.LENGTH_LONG).show();
            }
        }
    }
}

