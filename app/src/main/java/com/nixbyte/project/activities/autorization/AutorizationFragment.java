package com.nixbyte.project.activities.autorization;

import android.os.Bundle;
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
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.StartRegistration;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.modules.activity.FragmentParent;
import com.nixbyte.project.utils.ApiFactory;
import com.nixbyte.project.utils.creatingObservers.CreatingObservers;


public class AutorizationFragment extends FragmentParent {
     /* SocialNetwork Ids in ASNE:
     * 1 - Twitter
     * 2 - LinkedIn
     * 3 - Google Plus
     * 4 - Facebook
     * 5 - Vkontakte
     * 6 - Odnoklassniki
     * 7 - Instagram
     */
     public static final String LOG_TAG = "login";
    public static SocialNetworkManager mSocialNetworkManager;
    private Button vk;
    private Button ok;
    private Button tw;
    private TextView reg;
    private TextView accept, map;

    AutorizationActivity activity;

    EditText login,password;
    AutorizationFragment fragment=null;

    public AutorizationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("gg","prob");
        View rootView = inflater.inflate(R.layout.autorization_fragment, container, false);
        Log.e("gg",getActivity().getClass().getName());
        // init buttons and set Listener
        vk = rootView.findViewById(R.id.vk);
        ok = rootView.findViewById(R.id.ok);
        tw = rootView.findViewById(R.id.face);
        reg = rootView.findViewById(R.id.reg);
        accept = rootView.findViewById(R.id.accept);
        accept.setOnClickListener(loginClick);
        vk.setOnClickListener(loginClick);
        reg.setOnClickListener(loginClick);
        ok.setOnClickListener(loginClick);
        tw.setOnClickListener(loginClick);

        fragment=this;

        login=rootView.findViewById(R.id.et1);
        password=rootView.findViewById(R.id.et2);

        activity=(AutorizationActivity) getActivity();

        mSocialNetworkManager=AutorizationView.initManager(LOG_TAG,0);
        mSocialNetworkManager.setOnInitializationCompleteListener(this);
        return rootView;
    }

    private View.OnClickListener loginClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int networkId = 0;
            switch (view.getId()) {
                case R.id.vk:
                    networkId = VkSocialNetwork.ID;
                    break;
                case R.id.ok:
                    networkId= OkSocialNetwork.ID;
                    Log.e("GG","HERE");
                    break;
                case R.id.face:
                    networkId= FacebookSocialNetwork.ID;
                    break;
                case R.id.reg:
                    new StartRegistration((AutorizationActivity)getActivity()).perform();
                    return;
                case R.id.accept: {
                    String email=login.getText().toString(),
                            pas=password.getText().toString();
                    ApiFactory.autorize(email,pas).subscribe(
                            CreatingObservers.loginSuccessObserver(
                                    activity,
                                    null,
                                    "Пользователя с такими данными не существует",
                                    false)
                    );
                    return;
                }
            }
            SocialNetwork socialNetwork = mSocialNetworkManager.getSocialNetwork(networkId);
            socialNetwork.logout();
                if (networkId != 0) {
                    socialNetwork.requestLogin();
                    AbstractActivity.showProgress();
                } else {
                    Toast.makeText(getActivity(), "Wrong networkId", Toast.LENGTH_LONG).show();
                }
        }
    };


    @Override
    public void onSocialNetworkManagerInitialized() {
        //when init SocialNetworks - get and setup login only for initialized SocialNetworks
        for (SocialNetwork socialNetwork : mSocialNetworkManager.getInitializedSocialNetworks()) {
            socialNetwork.setOnLoginCompleteListener(this);
        }
    }

    @Override
    public void onLoginSuccess(int networkId) {
        AbstractActivity.hideProgress();
        Log.e("GG","2");
        SocialNetwork socialNetwork = mSocialNetworkManager.getSocialNetwork(networkId);
        socialNetwork.setOnRequestCurrentPersonCompleteListener(this);
        socialNetwork.requestCurrentPerson();

    }

    @Override
    public void onRequestSocialPersonSuccess(int socialNetworkId, SocialPerson socialPerson) {
        ApiFactory.socialAutorize(socialPerson.id+"",socialNetworkId+"").subscribe(
                CreatingObservers.loginSuccessObserver(
                        activity,
                        null,
                        "Ошибка",
                        false)
        );
        mSocialNetworkManager.getSocialNetwork(socialNetworkId).logout();
    }
}




 /*
                       ------------------
                        keys for socials
                       ------------------

 public void printHashKey() {
        try {
            PackageInfo info = getActivity().getPackageManager().getPackageInfo("ru.scapegoats.checkme",
                    PackageManager.GET_SIGNATURES);
                         String[] fingerprints = VKUtil.getCertificateFingerprint(getActivity(), getActivity().getPackageName());
        Log.e("KEY FOR VK",fingerprints[0]);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KEY FOR :", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }
    }*/