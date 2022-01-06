package com.example.testclientjodit2.database;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.widget.Toast;

import com.example.testclientjodit2.api.ServerApi;
import com.example.testclientjodit2.models.User;
import com.example.testclientjodit2.models.UserMission;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataLoader {
    public final static String FILE_NAME_USER = "user.txt";
    public final static String FILE_NAME_LIST_EXECUTORS = "executors.txt";
    public final static String FILE_NAME_SESSION = "session.txt";


    public static void saveUser(User user, Context context) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    context.openFileOutput(FILE_NAME_USER, MODE_PRIVATE)));
            String jsonString = JSONHelper.exportUserToJSON(user);
            bw.write(jsonString);
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User getUserFromStorage(Context context) {
        FileInputStream fin = null;
        try {
            fin = context.openFileInput(DataLoader.FILE_NAME_USER);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String jsonUser = new String(bytes);
            return JSONHelper.importUserFromJSON(jsonUser);
        } catch (IOException ex) {

            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
                Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        return null;
    }

    public static void saveListMissionsExecutors(List<UserMission> userMissions, Context context) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    context.openFileOutput(FILE_NAME_LIST_EXECUTORS, MODE_PRIVATE)));
            String jsonString = JSONHelper.exportListUserMissionsToJSON(userMissions);
            bw.write(jsonString);
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<UserMission> getListMissionsExecutors(Context context) {
        FileInputStream fin = null;
        try {
            fin = context.openFileInput(DataLoader.FILE_NAME_LIST_EXECUTORS);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String jsonUser = new String(bytes);
            return JSONHelper.importListUserMissionsFromJSON(jsonUser);
        } catch (IOException ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
                Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        return new ArrayList<>();
    }
}
