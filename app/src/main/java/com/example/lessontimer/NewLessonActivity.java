package com.example.lessontimer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewLessonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_lesson);
    }

    public void back(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void makeNotification(String text) {
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }

    @SuppressLint("CutPasteId")
    public void add(View view) {
        EditText start_time_edit = findViewById(R.id.start_time);
        EditText end_time_edit = findViewById(R.id.end_time);
        CheckBox[] checkboxes = {findViewById(R.id.mondayCheckBox),
                findViewById(R.id.tuesdayCheckBox),
                findViewById(R.id.wednesdayCheckBox),
                findViewById(R.id.thursdayCheckBox),
                findViewById(R.id.fridayCheckBox),
                findViewById(R.id.saturdayCheckBox),
                findViewById(R.id.sundayCheckBox)};
        String[] weekdays = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"};
        @SuppressLint("SimpleDateFormat") DateFormat formatter = new SimpleDateFormat("hh:mm");
        Date start_date;
        Date end_date;

        try {
            start_date = formatter.parse(start_time_edit.getText() + ":00");
            end_date = formatter.parse(end_time_edit.getText() + ":00");
            assert start_date != null;
            assert end_date != null;
        } catch (Exception e) {
            makeNotification("Неверное время!");
            return;
        }
        String start_time = start_date.getHours() + ":" + start_date.getMinutes();
        String end_time = end_date.getHours() + ":" + end_date.getMinutes();

        boolean isCheckboxes = false;
        for (CheckBox checkBox : checkboxes) {
            if (checkBox.isChecked()) {
                isCheckboxes = true;
            }
        }

        if (!isCheckboxes) {
            makeNotification("Не выбраны дни недели!");
            return;
        }

        System.out.println("----------");
        System.out.println("Start time:" + start_time);
        System.out.println("End time:" + end_time);
        for (byte i = 0; i < 7; i++) {
            System.out.println(weekdays[i] + " " + checkboxes[i].isChecked());
        }
        System.out.println("----------");
        back(view);
    }
}
