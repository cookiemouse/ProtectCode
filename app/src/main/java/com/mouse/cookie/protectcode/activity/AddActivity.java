package com.mouse.cookie.protectcode.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mouse.cookie.protectcode.R;
import com.mouse.cookie.protectcode.data.AdapterContentData;
import com.mouse.cookie.protectcode.datawork.DataWork;
import com.mouse.cookie.protectcode.dataworkinterface.OnDataCompleteListener;

public class AddActivity extends AppCompatActivity implements OnDataCompleteListener {

    private EditText mEditTextTitle, mEditTextDescrip, mEditTextPassword;
    private Button mButtonEnsure;

    private int mIntType = 0;

    //数据操作
    private DataWork mDataWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initialize();

        setEventListener();
    }

    //初始化
    private void initialize(){
        mEditTextTitle = (EditText) findViewById(R.id.et_activity_add_title);
        mEditTextDescrip = (EditText) findViewById(R.id.et_activity_add_descrip);
        mEditTextPassword = (EditText) findViewById(R.id.et_activity_add_password);

        mButtonEnsure = (Button) findViewById(R.id.btn_activity_add_ensure);

        mDataWork = new DataWork(this);
    }

    //设置事件监听
    private void setEventListener(){
        mButtonEnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mStringTitle = mEditTextTitle.getText().toString();
                String mStringDescrip = mEditTextDescrip.getText().toString();
                String mStringPassword = mEditTextPassword.getText().toString();

                mDataWork.addContentData(new AdapterContentData(mIntType
                        , mStringTitle
                        , mStringDescrip
                        , mStringPassword));
            }
        });
    }

    @Override
    public void onComplete() {
        showSuccessDialog();
    }

    @Override
    public void onFailure() {
        showMessageDialog("数据保存失败！");
    }

    //信息对话框
    private void showMessageDialog(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg);
        builder.setPositiveButton(R.string.ensure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do nothing
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //保存成功对话框
    private void showSuccessDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("保存成功！");
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.ensure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
