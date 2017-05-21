package ru.s7.android.utils;

import android.content.Context;
import android.content.Intent;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import ru.s7.android.R;

/**
 * Created by celikindv on 20/05/2017.
 */

public class Utils {

    public static int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    public static boolean randBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    public static String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(Constants.HERMES_LOCATION_JSON);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static String loadTxtFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(Constants.RUSSIA_CITIES);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    public static void share(Context context,String text){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.share)));
    }

}
