package com.applandeo.materialcalendarsampleapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.applandeo.materialcalendarsampleapp.utils.DrawableUtils;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.utils.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Mateusz Kornakiewicz on 23.10.2017.
 */

public class ManyDaysPickerActivity extends AppCompatActivity {
    ArrayList<String> mOrderList = new ArrayList<String>();
    ArrayList<String> mOrderListdate = new ArrayList<String>();
    ArrayList<String> mOrderListmonth = new ArrayList<String>();
    ArrayList<String> mOrderListyear = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.many_days_picker_activity);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);

        calendarView.setOnForwardPageChangeListener(() ->
                Toast.makeText(getApplicationContext(), "Forward", Toast.LENGTH_SHORT).show());

        calendarView.setOnPreviousPageChangeListener(() ->
                Toast.makeText(getApplicationContext(), "Previous", Toast.LENGTH_SHORT).show());

       calendarView.setSelectedDates(getSelectedDays());

        // List<EventDay> events = new ArrayList<>();

//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.DAY_OF_MONTH, 0);
//        cal.set(Calendar.DAY_OF_MONTH,7);
//        cal.set(Calendar.YEAR, 2020);
//
//       // cal.add(Calendar.DAY_OF_MONTH, 7);
//        events.add(new EventDay(cal, R.drawable.sample_four_icons));
//event

        List<EventDay> events = new ArrayList<>();
        Calendar calendarr[] = new Calendar[2];
        for (int i=0;i<2;i++) {
            calendarr[i] = Calendar.getInstance();
            calendarr[i].set(Calendar.DAY_OF_MONTH, 0);
            calendarr[i].set(Calendar.DAY_OF_MONTH, i+7);
            calendarr[i].set(Calendar.YEAR, 2020);
            events.add(0, new EventDay(calendarr[i], DrawableUtils.getCircleDrawableWithText(this, "✅")));
        }
//        Calendar calendarrr = Calendar.getInstance();
//        calendarrr.set(Calendar.DAY_OF_MONTH, 0);
//        calendarrr.set(Calendar.DAY_OF_MONTH,8);
//        calendarrr.set(Calendar.YEAR, 2020);
//        events.add(0,new EventDay(calendarrr, DrawableUtils.getCircleDrawableWithText(this, "✅")));
//
//        Calendar calendarrrr = Calendar.getInstance();
//        calendarrrr.set(Calendar.DAY_OF_MONTH, 0);
//        calendarrrr.set(Calendar.DAY_OF_MONTH,9);
//        calendarrrr.set(Calendar.YEAR, 2020);
//        events.add(0,new EventDay(calendarrrr, DrawableUtils.getCircleDrawableWithText(this, "✅")));
//


        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH,23);

        cal.set(Calendar.YEAR, 2020);

        // cal.add(Calendar.DAY_OF_MONTH, 7);
        events.add(new EventDay(cal, R.drawable.sample_four_icons));
        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.DAY_OF_MONTH, 0);
        cal2.set(Calendar.DAY_OF_MONTH,24);

        cal2.set(Calendar.YEAR, 2020);

        // cal.add(Calendar.DAY_OF_MONTH, 7);
        events.add(new EventDay(cal2, R.drawable.sample_four_icons));

        calendarView.setEvents(events);
        //event



        Toast.makeText(getApplicationContext(),
                "c "+ManyDaysPickerActivity.getArrayPrefsDate("dat",getApplicationContext()).get(0)+" "+
                        ManyDaysPickerActivity.getArrayPrefsMonth("mon",getApplicationContext()).get(0)+" year "
                +ManyDaysPickerActivity.getArrayPrefsYear("yea",getApplicationContext()).get(0),
                Toast.LENGTH_SHORT).show();
        Button getDateButton = (Button) findViewById(R.id.getDateButton);
        getDateButton.setOnClickListener(v -> {
            int i=0;

            for (Calendar calendar : calendarView.getSelectedDates()) {
                System.out.println(calendar.getTime().toString());

                mOrderList.add(i,calendar.getTime().toString());
                mOrderListdate.add(i,String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
                int j = Integer.valueOf(calendar.get(Calendar.MONTH))+1;
                mOrderListmonth.add(i,String.valueOf(j));
                mOrderListyear.add(i, String.valueOf(calendar.get(Calendar.YEAR)));

                i++;

                Toast.makeText(getApplicationContext(),
                        calendar.getTime().toString(),
                        Toast.LENGTH_SHORT).show();
            }
            ManyDaysPickerActivity.setArrayPrefsDate("dat",mOrderListdate,getApplicationContext());
            ManyDaysPickerActivity.setArrayPrefsMonth("mon",mOrderListmonth,getApplicationContext());
            ManyDaysPickerActivity.setArrayPrefsYear("yea",mOrderListyear,getApplicationContext());



        });
    }

    private List<Calendar> getSelectedDays() {
        List<Calendar> calendars = new ArrayList<>();
        int size = ManyDaysPickerActivity.getArrayPrefsDate("dat",getApplicationContext()).size();
        Log.i("sizeeee","s "+size);
        for (int i = 0; i <size; i++) {
            Calendar calendar = DateUtils.getCalendar();
            //calendar.add(Calendar.DAY_OF_MONTH, 0);
           // calendar.set(2020,12,23);
        //calendar.add(Calendar.DAY_OF_MONTH, Calendar.MONTH);
       // Calendar calendar = Calendar.getInstance();
      //  calendar.add(Calendar.DAY_OF_MONTH, 5);
      //  calendar.set(2020,12,22);
        //calendar.set(Calendar.YEAR, 2020);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(ManyDaysPickerActivity.getArrayPrefsDate("dat",getApplicationContext()).get(i)));
        calendar.set(Calendar.YEAR, Integer.valueOf(ManyDaysPickerActivity.getArrayPrefsYear("yea",getApplicationContext()).get(i)));

        //event
            calendars.add(calendar);
       }

        return calendars;
    }

    public static void setArrayPrefs(String arrayName, ArrayList<String> array, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName +"_size", array.size());
        for(int i=0;i<array.size();i++)
            editor.putString(arrayName + "_" + i, array.get(i));
        editor.apply();
    }

    public static void setArrayPrefsDate(String arrayName, ArrayList<String> array, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("preferencenamedate", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName +"_size", array.size());
        for(int i=0;i<array.size();i++)
            editor.putString(arrayName + "_" + i, array.get(i));
        editor.apply();
    }

    public static void setArrayPrefsMonth(String arrayName, ArrayList<String> array, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("preferencenamemonth", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName +"_size", array.size());
        for(int i=0;i<array.size();i++)
            editor.putString(arrayName + "_" + i, array.get(i));
        editor.apply();
    }

    public static void setArrayPrefsYear(String arrayName, ArrayList<String> array, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("preferencenameyear", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName +"_size", array.size());
        for(int i=0;i<array.size();i++)
            editor.putString(arrayName + "_" + i, array.get(i));
        editor.apply();
    }

    public static ArrayList<String> getArrayPrefs(String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        ArrayList<String> array = new ArrayList<>(size);
        for(int i=0;i<size;i++)
            array.add(prefs.getString(arrayName + "_" + i, null));
        return array;
    }

    public static ArrayList<String> getArrayPrefsDate(String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("preferencenamedate", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        ArrayList<String> array = new ArrayList<>(size);
        for(int i=0;i<size;i++)
            array.add(prefs.getString(arrayName + "_" + i, null));
        return array;
    }
    public static ArrayList<String> getArrayPrefsMonth(String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("preferencenamemonth", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        ArrayList<String> array = new ArrayList<>(size);
        for(int i=0;i<size;i++)
            array.add(prefs.getString(arrayName + "_" + i, null));
        return array;
    }
    public static ArrayList<String> getArrayPrefsYear(String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("preferencenameyear", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        ArrayList<String> array = new ArrayList<>(size);
        for(int i=0;i<size;i++)
            array.add(prefs.getString(arrayName + "_" + i, null));
        return array;
    }
}