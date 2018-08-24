package com.nixbyte.project.activities.registration;

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
import com.google.android.material.textfield.TextInputEditText;
import com.nixbyte.project.R;
import com.nixbyte.project.activities.autorization.AutorizationActivity;
import com.nixbyte.project.activities.autorization.AutorizationView;
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.StartRegistration;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.modules.activity.FragmentParent;
import com.nixbyte.project.utils.ApiFactory;
import com.nixbyte.project.utils.creatingObservers.CreatingObservers;


public class RegistrationFragment extends FragmentParent {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("gg","prob");
        View rootView = inflater.inflate(R.layout.registration_fragment, container, false);
        TextView accept=rootView.findViewById(R.id.accept);
        RegistrationActivity activity=(RegistrationActivity)getActivity();
        accept.setOnClickListener(e->{
            TextInputEditText et1=rootView.findViewById(R.id.et1)
                    ,et2=rootView.findViewById(R.id.et2);

            String email=et1.getText().toString();
            String password=et2.getText().toString();
            Log.e("waaaat",email+"/"+password);
            ApiFactory.register(email,password).subscribe(
                    CreatingObservers.registrationSuccessObserver(
                            activity,
                            null,
                            "Данный email адрес уже используется",
                            false)
            );
        });
        Log.e("gg",getActivity().getClass().getName());
        return rootView;
    }

}

