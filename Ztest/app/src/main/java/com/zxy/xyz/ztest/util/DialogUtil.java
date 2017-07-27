package com.zxy.xyz.ztest.util;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.zxy.xyz.ztest.biz.MainPhotoItem;

import org.litepal.crud.DataSupport;


/**
 * Created by ZXY on 2017/7/24.
 */

public class DialogUtil {
    public static void dialog(Context context, String guide, String confirm, String reset, final View view, final String position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(guide);

        builder.setTitle("提示");

        builder.setPositiveButton(confirm, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                ImageUtil.saveImage(ImageUtil.getViewBitmap(view),position);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(reset, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    public static void addDialog(Context context, final MainPhotoItem mainPhotoItem, final Handler mHandler){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("确认添加吗？");

        builder.setTitle("提示");

        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainPhotoItem photoItem=new MainPhotoItem();
                photoItem.setStatus("1");
                photoItem.updateAll("title = ?",mainPhotoItem.getTitle());
                mHandler.sendEmptyMessage(0);
                mHandler.sendEmptyMessage(1);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }
}
