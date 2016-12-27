package com.mouse.cookie.protectcode.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mouse.cookie.protectcode.R;
import com.mouse.cookie.protectcode.adapter.ContentAdapter;
import com.mouse.cookie.protectcode.adapter.OnDeleteListener;
import com.mouse.cookie.protectcode.adapter.OnEditListener;
import com.mouse.cookie.protectcode.customview.SwipeListLayout;
import com.mouse.cookie.protectcode.data.AdapterContentData;
import com.mouse.cookie.protectcode.datawork.DataWork;
import com.mouse.cookie.protectcode.dataworkinterface.OnDataCompleteListener;
import com.mouse.cookie.protectcode.flag.FlagData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContentActivity extends AppCompatActivity implements OnDataCompleteListener {

    private FloatingActionButton mFloatingActionButtonAdd;
    private ListView mListView;

    private List<AdapterContentData> mAdapterContentDataList;
    private ContentAdapter mContentAdapter;

    //数据库
    private DataWork mDataWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        initialize();

        setEventListener();

    }

    @Override
    protected void onResume() {
        super.onResume();
        flushData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //加一个状态，是否打开AddActivity，如果没有，则退出，如果打开，则不退出
    }

    //初始化
    private void initialize() {
        mFloatingActionButtonAdd = (FloatingActionButton) findViewById(R.id.fab);
        mListView = (ListView) findViewById(R.id.lv_content);

        mAdapterContentDataList = new ArrayList<>();
        mContentAdapter = new ContentAdapter(mAdapterContentDataList, this);

        mListView.setAdapter(mContentAdapter);

        mDataWork = new DataWork(ContentActivity.this);
    }

    //设置事件监听
    private void setEventListener() {
        mFloatingActionButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                jumpToAddActivity();
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                return true;
            }
        });

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    //当listview开始滑动时，若有item的状态为Open，则Close，然后移除
                    case SCROLL_STATE_TOUCH_SCROLL:
                        mContentAdapter.closeAll();
                        break;

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });

        mContentAdapter.setOnEditListener(new OnEditListener() {
            @Override
            public void onEdit(int position) {
                jumpToEditActivity(position);
            }
        });

        mContentAdapter.setOnDeleteListener(new OnDeleteListener() {
            @Override
            public void onDelete(int position) {
                mDataWork.deleteContentData(mAdapterContentDataList.get(position).getRowid());
            }
        });
    }

    //刷新数据
    private void flushData() {
        mAdapterContentDataList.clear();

        mAdapterContentDataList.addAll(mDataWork.getContentData());

        if (mAdapterContentDataList.size() > 0) {
            Log.e("Tag", "descrip-->" + mAdapterContentDataList.get(0).getDescrip());
        }

        mContentAdapter.notifyDataSetChanged();
    }

    //跳转到添加页面
    private void jumpToAddActivity() {
        Intent intent = new Intent(ContentActivity.this, AddActivity.class);
        startActivity(intent);
    }

    //跳转到添加页面
    private void jumpToEditActivity(int position) {
        Intent intent = new Intent(ContentActivity.this, EditActivity.class);
        intent.putExtra(FlagData.FLAG_EDIT, position);
        startActivity(intent);
    }

    @Override
    public void onComplete() {
        flushData();
    }

    @Override
    public void onFailure() {
        showMessageDialog("操作失败！");
    }

    private void showMessageDialog(String msg) {
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
}
