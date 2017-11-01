package com.oadex.starter.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by Malik on 01-Nov-17.
 */

public class Util
{
    public static Bitmap decodePicture(String picture)
    {
        byte[] bytes = Base64.decode(picture.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public static String encodePicture(Bitmap picture)
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        picture.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteFormat = stream.toByteArray();
        String imgString = Base64.encodeToString(byteFormat, Base64.DEFAULT);
        return imgString;
    }
}
