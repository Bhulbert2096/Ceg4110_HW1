package com.example.admin.hw1_hulbert;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import me.priyesh.chroma.ChromaDialog;
import me.priyesh.chroma.ColorMode;
import me.priyesh.chroma.ColorSelectListener;



public class DrawingActivity extends AppCompatActivity {

    private Bitmap bmp;
    private Context mContext=DrawingActivity.this;

    private static final int REQUEST = 112;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);


        if (Build.VERSION.SDK_INT >= 23) {
            String[] PERMISSIONS = {android.Manifest.permission.READ_EXTERNAL_STORAGE,android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (!hasPermissions(mContext, PERMISSIONS)) {
                ActivityCompat.requestPermissions((Activity) mContext, PERMISSIONS, REQUEST );
            } else {
                //do here
            }
        } else {
            //do here
        }

        int nColor;

        RelativeLayout parentView = findViewById(R.id.parentView);
        final Canvas2 canvas = new Canvas2(this);
        parentView.addView(canvas);

        Button btColor = findViewById(R.id.btColor);
        btColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new ChromaDialog.Builder()
                        .initialColor(Color.GREEN)
                        .colorMode(ColorMode.RGB) // There's also ARGB and HSV
                        .onColorSelected(new ColorSelectListener() {
                                             @Override
                                             public void onColorSelected(int i) {
                                                 canvas.SetColor(i);
                                             }
                                         }
                        )
                        .create()
                        .show(getSupportFragmentManager(), "ChromaDialog");
            }
        });




        Button btErase = findViewById(R.id.btErase);
        btErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.clearCanvas();
            }
        });

        Button btSave = findViewById(R.id.btSave);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileOutputStream out = null;
                String path = Environment.getExternalStorageDirectory().toString();
                File myFilePath = new File(path);

                try {
                    if(!myFilePath.exists()) {
                        myFilePath.mkdirs();
                        // File file = new File(myFilePath,"myDrawing.png");
                    }
                    File myFile = new File(path,"test.jpeg");
                        myFile.createNewFile();
                            //System.out.println("GOT HERE");
                            out = new FileOutputStream(myFile);
                   // BufferedOutputStream bos = new BufferedOutputStream(out);
                            bmp = canvas.getBitmap();
                            bmp.compress(Bitmap.CompressFormat.JPEG, 85,out); // bmp is your Bitmap instance
                            // PNG is a lossless format, the compression factor (100) is ignored
                        out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (out != null) {
                            out.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }

    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //do here
                } else {
                    Toast.makeText(mContext, "The app was not allowed to read your store.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}


