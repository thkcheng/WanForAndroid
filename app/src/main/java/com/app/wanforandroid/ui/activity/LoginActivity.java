package com.app.wanforandroid.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.wanforandroid.R;
import com.app.wanforandroid.constant.Apis;
import com.app.wanforandroid.base.BaseActivity;
import com.app.wanforandroid.constant.PreferencesName;
import com.app.wanforandroid.model.LoginBean;
import com.app.wanforandroid.util.SPUtil;
import com.app.wanforandroid.util.ToastUtil;
import com.lib.http.HttpManager;
import com.lib.http.callback.StringCallback;
import com.lib.http.error.ErrorModel;

import java.util.LinkedHashMap;

import butterknife.BindView;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.edUserName)
    EditText edUserName;
    @BindView(R.id.edUserPwd)
    EditText edUserPwd;
    @BindView(R.id.btnLogin)
    Button btnLogin;

    @Override
    public int getLayoutResID() {
        return R.layout.app_activity_login;
    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {

        if (TextUtils.isEmpty(edUserName.getText().toString()) || TextUtils.isEmpty(edUserPwd.getText().toString())) {
            ToastUtil.showToast("请输入账号密码");
            return;
        }

        LinkedHashMap<String, Object> paramsMap = new LinkedHashMap<>();
        paramsMap.put("username", edUserName.getText().toString().trim());
        paramsMap.put("password", edUserPwd.getText().toString().trim());

        HttpManager.post(Apis.WAN_USER_LOGIN)
                .tag(this)
                .params(paramsMap)
                .build()
                .enqueue(new StringCallback<LoginBean>() {
                    @Override
                    public void onSuccess(LoginBean response, Object... obj) {
                        SPUtil.putInt(PreferencesName.APP_USER_ID, response.getData().getId());
                        Intent intent = new Intent();
                        intent.putExtra("userName", response.getData().getUsername());
                        setResult(RESULT_OK, intent);
                        finish();
                    }

                    @Override
                    public void onFailure(ErrorModel errorModel) {
                    }
                });

    }
}
