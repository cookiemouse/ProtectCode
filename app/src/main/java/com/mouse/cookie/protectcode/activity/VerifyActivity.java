package com.mouse.cookie.protectcode.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mouse.cookie.protectcode.R;

public class VerifyActivity extends AppCompatActivity {

    private EditText mEditTextPassword;
    private Button mButtonEner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        initialize();

        setEventListener();
    }

    //初始化
    private void initialize(){
        mEditTextPassword = (EditText) findViewById(R.id.et_activity_verify_password);
        mButtonEner = (Button) findViewById(R.id.btn_activity_verify_ensure);
    }

    //设置事件监听
    private void setEventListener(){
        mButtonEner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2016/12/16 验证密码并进入下一页
                jumpToActivity();
            }
        });
    }

    //跳转到ContentActivity
    private void jumpToActivity(){
        Intent intent = new Intent(VerifyActivity.this, ContentActivity.class);
        startActivity(intent);
        finish();
    }

    //弹出对话框
    private void setMessageDialog(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg);
        builder.setPositiveButton(R.string.ensure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do nothing
            }
        });
    }
}
