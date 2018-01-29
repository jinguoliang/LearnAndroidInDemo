package com.example.jinux.mydemo.facedetact;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.FaceDetector;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.jinux.mydemo.R;
import com.example.jinux.mydemo.common.Utils;

/**
 * Created by Jinux on 17/1/6.
 */
public class FaceDetectDemo extends Activity {

    private static final int MAX_FACE_COUNT = 10;
    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_face_detact);
        mImage = (ImageView)findViewById(R.id.img);
    }

    public void onDetectClick(View view) {

        BitmapDrawable bitmapDrawable = (BitmapDrawable) mImage.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();

        mImage.setImageDrawable(new BitmapDrawable(bitmap));
        android.media.FaceDetector fdet_ = new android.media.FaceDetector(bitmap.getWidth(), bitmap.getHeight(), MAX_FACE_COUNT);
        android.media.FaceDetector.Face[] fullResults = new android.media.FaceDetector.Face[MAX_FACE_COUNT];
        fdet_.findFaces(bitmap, fullResults);

        for (FaceDetector.Face face : fullResults) {
            Log.e("test", "onDetectClick: " + face);
            if (face != null) {
                Utils.showToast(this, "face = " + face.eyesDistance());
            }
        }
    }
}
