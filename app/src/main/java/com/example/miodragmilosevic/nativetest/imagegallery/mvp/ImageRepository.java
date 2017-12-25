package com.example.miodragmilosevic.nativetest.imagegallery.mvp;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.example.miodragmilosevic.nativetest.imagedownloader.ImageDownloader;

import java.util.ArrayList;

/**
 * Created by miodrag.milosevic on 12/6/2017.
 */
//"http://www.vogella.de/img/lars/LarsVogelArticle7.png
//https://www.androidtutorialpoint.com/wp-content/uploads/2016/09/Beauty.jpg
public class ImageRepository implements Contract.Repository {

    private Context mContext;
    private ImageDownloader mImageDownloader;

    public ImageRepository(Context context, ImageDownloader imageDownloader) {
        mContext = context;
        mImageDownloader = imageDownloader;
    }

    @Override
    public ArrayList<String> getImagePaths() {
        ArrayList<String> result = getImagePath(mContext, (MediaStore.Images.Media.INTERNAL_CONTENT_URI));
        Boolean isSDPresent = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (isSDPresent) {
            result.addAll(getImagePath(mContext, (MediaStore.Images.Media.EXTERNAL_CONTENT_URI)));
        }
        Log.i("Miki", "getImages: " + result);
        return result;
    }

    @Override
    public void downLoadImage(String url, ImageDownloader.Callback callback) {
        //TODO remove with some good url
//        url = "https://www.androidtutorialpoint.com/wp-content/uploads/2016/09/Beauty.jpg";
        mImageDownloader.downloadImage(url, callback);
    }

    private ArrayList<String> getImagePath(Context context, Uri storageType) {
        final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
        final String orderBy = MediaStore.Images.Media._ID;
//Stores all the images from the gallery in Cursor
        Cursor cursor = context.getContentResolver().query(
                storageType, columns, null,
                null, orderBy);
//Total number of images
        int count = cursor.getCount();

//Create an array to store path to all the images
        ArrayList<String> arrPath = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            cursor.moveToPosition(i);
            int dataColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            //Store the path of the filterImage
            String path = cursor.getString(dataColumnIndex);
            arrPath.add(path);
            Log.i("Miki", path);
        }
// The cursor should be freed up after use with close()
        cursor.close();
        return arrPath;
    }


}
