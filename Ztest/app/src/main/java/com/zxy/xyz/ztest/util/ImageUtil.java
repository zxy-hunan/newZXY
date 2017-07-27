package com.zxy.xyz.ztest.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Environment;
import android.view.View;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by ZXY on 2017/7/24.
 */

public class ImageUtil {
    /**
     * 某些机型直接获取会为null,在这里处理一下防止国内某些机型返回null
     */
    public static Bitmap getViewBitmap(View view) {
        if (view == null) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }
    public static void saveImage(Bitmap bm,String path){
        File imageFile = new File(Environment.getExternalStorageDirectory(), path+".jpg");
        FileOutputStream outStream;
        try {
            outStream = new FileOutputStream(imageFile);
            BufferedOutputStream bos = new BufferedOutputStream(outStream);
            bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bos.flush();
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
     public static String getSDPath()  
    {  
        boolean hasSDCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);  
        if(hasSDCard)  
        {  
            return Environment.getExternalStorageDirectory().toString() + "/saving_picture";  
        }  
        else  
            return "/data/data/com.example.imageviewsave2bitmap/saving_picture";  
    }


    public static Bitmap convertViewToBitmap(View view)
    {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();

        return bitmap;
    }

}
