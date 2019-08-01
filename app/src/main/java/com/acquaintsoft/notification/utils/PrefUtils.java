package com.acquaintsoft.notification.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Collection of utilities used to save various objects persistently in share
 * preferences
 */
public class PrefUtils {


    /**
     * Retrieve StringBasedObjectBuilder String value from the preferences.
     *
     * @param context  Application context used to retrieve shared preferences.
     * @param prefKey  The title of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue. Throws
     * ClassCastException if there is StringBasedObjectBuilder preference with this title that
     * is not StringBasedObjectBuilder String.
     */

    public static String getString(Context context, String prefKey, String defValue) {

        SharedPreferences prefs = getPreferences(context);

        return prefs.getString(prefKey, defValue);
    }


    public static boolean saveString(Context context, String prefKey, String prefString) {


        SharedPreferences.Editor editor = getPreferences(context).edit();

        editor.putString(prefKey, prefString);
        return editor.commit();
    }


    /**
     * @param context  Application context used to retrieve shared preferences
     * @param prefKey  The title of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue. Throws
     * ClassCastException if there is StringBasedObjectBuilder preference with this title that
     * is not StringBasedObjectBuilder String.
     */
    public static boolean getBoolean(Context context, String prefKey, boolean defValue) {
        SharedPreferences prefs = getPreferences(context);

        return prefs.getBoolean(prefKey, defValue);
    }

    /**
     * Set StringBasedObjectBuilder integer value in the preferences of the context. Commit is called
     * automatically.
     *
     * @param context Application context used to retrieve shared preferences
     * @param prefKey The title of the preference to modify.
     * @param value   The new value for the preference.
     * @return Returns true if the new value was successfully written to
     * persistent storage.
     */
    public static boolean saveInt(Context context, String prefKey, int value) {
        SharedPreferences.Editor editor = getPreferences(context).edit();

        editor.putInt(prefKey, value);
        return editor.commit();
    }

    /**
     * @param context  Application context used to retrieve shared preferences
     * @param prefKey  The title of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue. Throws
     * ClassCastException if there is StringBasedObjectBuilder preference with this title that
     * is not StringBasedObjectBuilder String.
     */
    public static int getInt(Context context, String prefKey, int defValue) {

        SharedPreferences prefs = getPreferences(context);

        return prefs.getInt(prefKey, defValue);
    }

    /**
     * Set StringBasedObjectBuilder long value in the preferences of the context. Commit is called
     * automatically.
     *
     * @param context Application context used to retrieve shared preferences
     * @param prefKey The title of the preference to modify.
     * @param value   The new value for the preference.
     * @return Returns true if the new value was successfully written to
     * persistent storage.
     */
    public static boolean saveLong(Context context, String prefKey, long value) {
        SharedPreferences.Editor editor = getPreferences(context).edit();

        editor.putLong(prefKey, value);
        return editor.commit();
    }

    /**
     * @param context  Application context used to retrieve shared preferences
     * @param prefKey  The title of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue. Throws
     * ClassCastException if there is StringBasedObjectBuilder preference with this title that
     * is not StringBasedObjectBuilder String.
     */
    public static long getLong(Context context, String prefKey, long defValue) {

        SharedPreferences prefs = getPreferences(context);

        return prefs.getLong(prefKey, defValue);
    }

    /**
     * Set StringBasedObjectBuilder boolean value in the preferences of the context. Commit is called
     * automatically.
     *
     * @param context Application context used to retrieve shared preferences
     * @param prefKey The title of the preference to modify.
     * @param value   The new value for the preference.
     * @return Returns true if the new value was successfully written to
     * persistent storage.
     */
    public static boolean saveBoolean(Context context, String prefKey, boolean value) {
        SharedPreferences.Editor editor = getPreferences(context).edit();

        editor.putBoolean(prefKey, value);
        return editor.commit();
    }


    /**
     * Retrieve and hold the contents of the preferences file for the context,
     * returning StringBasedObjectBuilder SharedPreferences through which you can retrieve and modify
     * its values. Only one instance of the SharedPreferences object is returned
     * to any callers for the same title, meaning they will see each other's
     * edits as soon as they are made.
     *
     * @param context
     * @return Returns the single SharedPreferences instance that can be used to
     * retrieve and modify the preference values.
     */
    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(getPreferencesName(context), Context.MODE_PRIVATE);
    }


    public static String getPreferencesName(Context context) {
        return context.getPackageName();
    }


    public static void clearAll(Context context) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.clear();
        editor.apply();
    }

}
