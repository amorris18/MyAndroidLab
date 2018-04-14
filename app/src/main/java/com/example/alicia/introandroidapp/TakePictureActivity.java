package com.example.alicia.introandroidapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class TakePictureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);
    }
    private static final int REQUEST_TAKE_PIC = 1;

    private void takePic() {

        // create an implicit intent to take a picture
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Call startActivityForResult to send the intent
        if (null != getPackageManager().resolveActivity(intent,
                PackageManager.MATCH_DEFAULT_ONLY)) {
            startActivityForResult(intent, REQUEST_TAKE_PIC); //call back once work is done
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent returnIntent) {
        // If request succeeded, get thumbnail from the return intent
        if (requestCode == REQUEST_TAKE_PIC && resultCode == RESULT_OK
                && returnIntent != null) {
            Bitmap bitmap = (Bitmap) returnIntent.getExtras().get("data");
            ImageView imageView = (ImageView) findViewById(R.id.imgFavoriteItem);
            imageView.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, returnIntent);
    }
}
