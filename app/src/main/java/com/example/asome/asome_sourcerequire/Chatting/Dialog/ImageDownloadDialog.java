package com.example.asome.asome_sourcerequire.Chatting.Dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static com.example.asome.asome_sourcerequire.Chatting.Etc.DateFormat.date_full;

/**
 * [OUTLINE]
 * 이미지 다운로드 다이얼로그
 *
 *
 * Created by anfrh on 2017-07-17.
 */
public class ImageDownloadDialog {
    String dirPath;
    Context context;
    Bitmap bitmapImage;

    public ImageDownloadDialog(String dirPath, Context context, Bitmap bitmapImage) {
        this.dirPath = dirPath;
        this.context = context;
        this.bitmapImage = bitmapImage;
    }

    public void show() {
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(context);
        alt_bld.setMessage("이미지를 다운로드 합니다").setCancelable(
                false).setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        String extStorageDirectory = Environment.getExternalStorageState();

                        if (extStorageDirectory.equals(Environment.MEDIA_MOUNTED)) {

                            dirPath = "/sdcard/DCIM/giljabee/downloads";
                            File file = new File(dirPath);
                            if (!file.exists())  // 원하는 경로에 폴더가 있는지 확인
                                file.mkdirs();
                        } else {
                            Toast.makeText(context, "SD Card 인식 실패", Toast.LENGTH_SHORT).show();
                        }

                        OutputStream outStream = null;
                        String fileName = date_full();
                        File file = new File(dirPath, fileName + ".PNG");
                        try {
                            outStream = new FileOutputStream(file);
                            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                            outStream.flush();
                            outStream.close();

                            Toast.makeText(context,
                                    "Saved", Toast.LENGTH_LONG).show();

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            Toast.makeText(context,
                                    e.toString(), Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(context,
                                    e.toString(), Toast.LENGTH_LONG).show();

                        }
                    }
                }).setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Action for 'NO' Button
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alt_bld.create();
        alert.show();
    }


}
