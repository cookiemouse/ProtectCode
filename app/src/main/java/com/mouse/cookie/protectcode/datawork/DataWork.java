package com.mouse.cookie.protectcode.datawork;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mouse.cookie.protectcode.data.AdapterContentData;
import com.mouse.cookie.protectcode.dataworkinterface.OnDataCompleteListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cookie on 2016/12/17.
 */

public class DataWork {

    private static final String TABLE_NAME_CONTENT = "content_table";

    private SQLiteDatabase mSqLiteDatabase;

    private OnDataCompleteListener mOnDataCompleteListener;

    public DataWork(Context context) {
        mSqLiteDatabase = DataBaseHelper.getInstance(context);
        try {
            mOnDataCompleteListener = (OnDataCompleteListener) context;
        } catch (Exception e) {
            Log.e("Tag", e + "context转OnDataCompleteListener错误，请实现该接口");
        }
    }

    //添加Content
    public long addContentData(AdapterContentData data) {
        ContentValues mContentValues = new ContentValues();
        mContentValues.put("type", data.getType());
        mContentValues.put("title", data.getTitle());
        mContentValues.put("descrip", data.getDescrip());
        mContentValues.put("password", data.getPassword());
        long add = mSqLiteDatabase.insert(TABLE_NAME_CONTENT, null, mContentValues);
        if (add > 0) {
            mOnDataCompleteListener.onComplete();
        } else {
            mOnDataCompleteListener.onFailure();
        }

        return add;
    }

    //删除Content
    public int deleteContentData(int rowid) {
        int delete = mSqLiteDatabase.delete(TABLE_NAME_CONTENT, "rowid=?", new String[]{"" + rowid});

        if (delete > 0) {
            mOnDataCompleteListener.onComplete();
        } else {
            mOnDataCompleteListener.onFailure();
        }

        return delete;
    }

    //更新Content
    public int updateContentData(int rowid, AdapterContentData data) {
        ContentValues mContentValues = new ContentValues();
        mContentValues.put("type", data.getType());
        mContentValues.put("title", data.getTitle());
        mContentValues.put("descrip", data.getDescrip());
        mContentValues.put("password", data.getPassword());

        int update = mSqLiteDatabase.update(TABLE_NAME_CONTENT
                , mContentValues
                , "rowid=?"
                , new String[]{"" + rowid});

        if (update > 0) {
            mOnDataCompleteListener.onComplete();
        } else {
            mOnDataCompleteListener.onFailure();
        }

        return update;
    }

    //查询Content
    public List<AdapterContentData> getContentData() {
        Cursor mCursor = mSqLiteDatabase.query(TABLE_NAME_CONTENT
                , new String[]{"type", "title", "descrip", "password", "rowid"}
                , null, null, null, null, null);

        List<AdapterContentData> mAdapterContentDataList = new ArrayList<>();

        while (mCursor.moveToNext()) {
            int type = mCursor.getInt(0);
            String title = mCursor.getString(1);
            String descrip = mCursor.getString(2);
            String password = mCursor.getString(3);
            int rowid = mCursor.getInt(4);

            mAdapterContentDataList.add(new AdapterContentData(rowid, type, title, descrip, password));
        }

        mCursor.close();
        return mAdapterContentDataList;
    }

    //添加password
    public void addPassword() {
    }

    //删除password
    public void deletePassword() {
    }

    //更新password
    public void updatePassword() {
    }

    //查询password
    public void getPassword() {
    }

    //设置接口
    public void setOnDataCompleteListener(OnDataCompleteListener listener) {
        this.mOnDataCompleteListener = listener;
    }
}
