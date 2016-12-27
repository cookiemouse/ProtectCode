package com.mouse.cookie.protectcode.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mouse.cookie.protectcode.R;
import com.mouse.cookie.protectcode.customview.SwipeListLayout;
import com.mouse.cookie.protectcode.data.AdapterContentData;
import com.mouse.cookie.protectcode.flag.FlagData;

import java.util.List;

/**
 * Created by cookie on 2016/12/16.
 */

public class ContentAdapter extends BaseAdapter {

    private List<AdapterContentData> mAdapterContentDataList;
    private Context mContext;

    private OnEditListener mOnEditListener;
    private OnDeleteListener mOnDeleteListener;

//    private int mIntScreenWidth = 0;
//    private int mIntWidth = 0;
//    private int mIntHeight = 0;
//    private float mFloatX = 0;
//    private float mFloatY = 0;
//    private float mFloatTouchX = 0;
//    private float mFloatDistance = 0;

    public ContentAdapter(List<AdapterContentData> mAdapterContentDataList, Context mContext) {
        this.mAdapterContentDataList = mAdapterContentDataList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mAdapterContentDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAdapterContentDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        AdapterContentData data = mAdapterContentDataList.get(position);

        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_content, null);

            viewHolder = new ViewHolder();

            viewHolder.mSwipeListLayout = (SwipeListLayout) convertView.findViewById(R.id.sll_adapter_content);

            viewHolder.mImageViewType = (ImageView) convertView.findViewById(R.id.iv_adapter_content_type);
            viewHolder.mTextViewTitle = (TextView) convertView.findViewById(R.id.tv_adapter_content_title);
            viewHolder.mTextViewDescrip = (TextView) convertView.findViewById(R.id.tv_adapter_content_descrip);
            viewHolder.mTextViewPassword = (TextView) convertView.findViewById(R.id.tv_adapter_content_password);
            viewHolder.mButtonEdit = (Button) convertView.findViewById(R.id.btn_adapter_content_edit);
            viewHolder.mButtonDelete = (Button) convertView.findViewById(R.id.btn_adapter_content_delete);

            convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return false;
                }
            });

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mTextViewTitle.setText(data.getTitle());
        viewHolder.mTextViewDescrip.setText(data.getDescrip());
        viewHolder.mTextViewPassword.setText(data.getPassword());
        switch (data.getType()) {
            case FlagData.TYPE_DEFAULT: {
                viewHolder.mImageViewType.setImageResource(R.color.colorAccent);
                break;
            }
            case FlagData.TYPE_MONEY: {
                viewHolder.mImageViewType.setImageResource(R.color.colorAccent);
                break;
            }
            case FlagData.TYPE_LIFE: {
                viewHolder.mImageViewType.setImageResource(R.color.colorAccent);
                break;
            }
            case FlagData.TYPE_NET: {
                viewHolder.mImageViewType.setImageResource(R.color.colorAccent);
                break;
            }
            default: {
                viewHolder.mImageViewType.setImageResource(R.color.colorAccent);
            }
        }

        if (data.getStatu()) {
            viewHolder.mSwipeListLayout.setStatus(SwipeListLayout.Status.Open, true);
        } else {
            viewHolder.mSwipeListLayout.setStatus(SwipeListLayout.Status.Close, true);
        }

        viewHolder.mSwipeListLayout.setOnSwipeStatusListener(new SwipeListLayout.OnSwipeStatusListener() {
            @Override
            public void onStatusChanged(SwipeListLayout.Status status) {
                if (status == SwipeListLayout.Status.Open) {
                    mAdapterContentDataList.get(position).setStatu(true);
                } else {
                    mAdapterContentDataList.get(position).setStatu(false);
                }
            }

            @Override
            public void onStartCloseAnimation() {
            }

            @Override
            public void onStartOpenAnimation() {
                closeAll();
            }
        });

        viewHolder.mButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnEditListener) {
                    mOnEditListener.onEdit(position);
                }
            }
        });

        viewHolder.mButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnDeleteListener) {
                    mOnDeleteListener.onDelete(position);
                }
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        SwipeListLayout mSwipeListLayout;
        ImageView mImageViewType;
        TextView mTextViewTitle;
        TextView mTextViewDescrip;
        TextView mTextViewPassword;
        Button mButtonEdit, mButtonDelete;
    }

    public void closeAll() {
        for (AdapterContentData data : mAdapterContentDataList) {
            data.setStatu(false);
            notifyDataSetChanged();
        }
    }


    public void setOnEditListener(OnEditListener listener) {
        this.mOnEditListener = listener;
    }

    public void setOnDeleteListener(OnDeleteListener listener) {
        this.mOnDeleteListener = listener;
    }
}