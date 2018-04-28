package com.amirahmed.mykidsforteachers.Activities;


import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.amirahmed.mykidsforteachers.R;
import com.amirahmed.mykidsforteachers.Utils.MultiSelectionSpinner;
import com.amirahmed.mykidsforteachers.Utils.TinyDB;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.amirahmed.mykidsforteachers.R.id.classs;

public class NotificationsandMessagesActivity extends Activity implements AdapterView.OnItemSelectedListener,MultiSelectionSpinner.OnMultipleItemsSelectedListener {

    Spinner levelSpinner,classsSpinner,classroomSpinner,studentSpinner;
    MultiSelectionSpinner spinnerMulti;
    EditText date,no1,no2;
    TinyDB tinyDB;
    String state,selectedLevel,selectedClass,selectedClassroom,selectedStudent;

    List<String> levels = new ArrayList<>();
    List<String> classes = new ArrayList<>();
    List<String> classrooms = new ArrayList<>();
    List<String> students = new ArrayList<>();

    ArrayAdapter<String> adapter,adapter2,adapter3,adapter4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificationsandmessages);

        tinyDB = new TinyDB(getApplicationContext());
        state = tinyDB.getString("state");

        levelSpinner = findViewById(R.id.level);
        classsSpinner = findViewById(classs);
        classroomSpinner = findViewById(R.id.classroom);
        //studentSpinner = findViewById(R.id.student);
        spinnerMulti = findViewById(R.id.student);
        date = findViewById(R.id.dateedit);
        no1 = findViewById(R.id.edit5);
        no2 = findViewById(R.id.edit6);

        showMessage(FirebaseInstanceId.getInstance().getToken());
        Log.e("tokeeen", FirebaseInstanceId.getInstance().getToken());

        if(state.equals("messages"))
        {
            date.setVisibility(View.GONE);
            spinnerMulti.setVisibility(View.VISIBLE);
            no1.setHint("عنوان الرسالة");
            no2.setHint("تفاصيل الرسالة");

        }else
            {
                date.setVisibility(View.VISIBLE);
                spinnerMulti.setVisibility(View.GONE);
                no1.setHint("عنوان الاشعار");
                no2.setHint("تفاصيل الاشعار");
            }

        levels.add("Kids");
        levels.add("Primary");
        levels.add("Preparatory");
        levels.add("Secondary");


        /*classes.add("1st");
        classes.add("2nd");
        classes.add("3rd");
        classes.add("4th");

        classrooms.add("1/2");
        classrooms.add("1/1");
        classrooms.add("1/3");
        classrooms.add("1/4");

        students.add("student 1");
        students.add("student 2");
        students.add("student 3");
        students.add("student 4");*/


        adapter = new ArrayAdapter<>(NotificationsandMessagesActivity.this, android.R.layout.simple_spinner_item, levels);
        adapter2 = new ArrayAdapter<>(NotificationsandMessagesActivity.this, android.R.layout.simple_spinner_item, classes);
        adapter3 = new ArrayAdapter<>(NotificationsandMessagesActivity.this, android.R.layout.simple_spinner_item, classrooms);
        adapter4 = new ArrayAdapter<>(NotificationsandMessagesActivity.this, android.R.layout.simple_spinner_item, students);



        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelSpinner.setAdapter(adapter);
        levelSpinner.setOnItemSelectedListener(this);

        adapter.notifyDataSetChanged();

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classsSpinner.setAdapter(adapter2);
        classsSpinner.setOnItemSelectedListener(this);

        adapter2.notifyDataSetChanged();

        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classroomSpinner.setAdapter(adapter3);
        classroomSpinner.setOnItemSelectedListener(this);

        adapter3.notifyDataSetChanged();

        /*adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMulti.setAdapter(adapter4);
        spinnerMulti.setOnItemSelectedListener(this);

        adapter4.notifyDataSetChanged();*/


        spinnerMulti.setListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch (adapterView.getId())
        {
            case R.id.level:
                selectedLevel = levelSpinner.getSelectedItem().toString();
                if(selectedLevel.equals("Kids"))
                {
                    classes.clear();
                    classes.add("1st");
                    classes.add("2nd");
                    classes.add("3rd");
                    classes.add("4th");
                }else
                    {
                        classes.clear();
                        classes.add("damn1");
                        classes.add("damn2");
                        classes.add("damn3");
                        classes.add("damn4");
                    }
                    adapter2.notifyDataSetChanged();
                levelSpinner.setSelection(i);
                break;
            case classs:
                selectedClass = classsSpinner.getSelectedItem().toString();
                if(selectedClass.equals("1st"))
                {
                    classrooms.clear();
                    classrooms.add("1/2");
                    classrooms.add("1/1");
                    classrooms.add("1/3");
                    classrooms.add("1/4");
                }else
                    {
                        classrooms.clear();
                        classrooms.add("2/2");
                        classrooms.add("2/1");
                        classrooms.add("2/3");
                        classrooms.add("2/4");
                    }
                    adapter3.notifyDataSetChanged();
                classsSpinner.setSelection(i);
                break;
            case R.id.classroom:
                selectedClassroom = classroomSpinner.getSelectedItem().toString();
                if(selectedClassroom.equals("1/2"))
                {
                    students.clear();
                    students.add("student 1");
                    students.add("student 2");
                    students.add("student 3");
                    students.add("student 4");
                }else
                {
                    students.clear();
                    students.add("student 5");
                    students.add("student 6");
                    students.add("student 7");
                    students.add("student 8");
                }
                spinnerMulti.setItems(students);
                spinnerMulti.setSelection(0);
                classroomSpinner.setSelection(i);
                break;
            /*case R.id.student:
                selectedStudent = spinnerMulti.getSelectedItemsAsString();
                spinnerMulti.setSelection(i);
                break;*/

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

        switch (adapterView.getId())
        {
            case R.id.level:
                showMessage("No Levels Selected");
                break;
            case classs:
                showMessage("No Classes Selected");
                break;
            case R.id.classroom:
                showMessage("No Classrooms Selected");
                break;
            /*case R.id.student:
                showMessage("No Student Selected");
                break;*/

        }

    }


    // created automatically
    private void showMessage(String _s) {
        Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


    @Override
    public void selectedIndices(List<Integer> indices) {

    }

    @Override
    public void selectedStrings(List<String> strings) {

        JSONArray array = new JSONArray();
        String arrr = "";

        for(int i=0;i<strings.size();i++){
            JSONObject obj=new JSONObject();
            try {
                obj.put("studentID",i);
                obj.put("studentName",strings.get(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            array.put(obj);
            arrr = array.toString();
        }

        showMessage(arrr);
    }
}
