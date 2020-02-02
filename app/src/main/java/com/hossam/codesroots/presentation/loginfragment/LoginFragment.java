package com.hossam.codesroots.presentation.loginfragment;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hossam.codesroots.entities.User;
import com.hossam.codesroots.helper.PreferenceHelper;
import com.hossam.codesroots.delivery24.R;
import com.hossam.codesroots.presentation.mainFragment.MainFragment;


public class LoginFragment extends Fragment implements View.OnClickListener {


    TextView login, register, loginWithLogin;
    EditText username, password;
    LoginViewModel loginViewModel;
    private FrameLayout progress;
    PreferenceHelper preferenceHelper;
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.login, container, false);
        loginViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(LoginViewModel.class);

        preferenceHelper = new PreferenceHelper(getActivity());
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        login = view.findViewById(R.id.login);
        progress = view.findViewById(R.id.progress);
        //   loginWithLogin = view.findViewById(R.id.loginWithFB);
        login.setOnClickListener(this);

        loginViewModel.loading.observe(getActivity(), loading ->
                progress.setVisibility(loading ? View.VISIBLE : View.GONE));


        loginViewModel.coderesponse.observe(getActivity(), code ->
                {
                    if (code == 401)
                        Toast.makeText(getActivity(), getString(R.string.usererror), Toast.LENGTH_LONG).show();
                }
        );

        loginViewModel.loginLiveData.observe(getActivity(), model ->
                {
                    if (model.isSuccess()) {
                        preferenceHelper.setUserId(model.getData().getId());
                        PreferenceHelper.setToken(model.getData().getToken());
                        PreferenceHelper.setUserName(model.getData().getUsername());
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                            fm.popBackStack();
                        }
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MainFragment()).addToBackStack(null).commit();

                        Toast.makeText(getActivity(), getString(R.string.loginsuccess), Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getActivity(), getString(R.string.usererror), Toast.LENGTH_LONG).show();
                    }
                }
        );

        return view;
    }

    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new LoginViewModelFactory(getActivity().getApplication());
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.login:
                if (validate()) {
                    User user = new User(username.getText().toString(), password.getText().toString());
                    loginViewModel.login(user);
                }

                break;
//
//            case R.id.register:
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new RegisterFragment()).addToBackStack(null).commit();
//                break;

        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

    }
    private boolean validate() {

        if (!(username.getText().toString().matches("")) && !(password.getText().toString().matches(""))) {
            return true;
        } else {
            Toast.makeText(getActivity(), getString(R.string.commpletefileds), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
