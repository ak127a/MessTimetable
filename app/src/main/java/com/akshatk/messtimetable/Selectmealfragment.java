package com.akshatk.messtimetable;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.akshatk.messtimetable.Homepagefragment.mealArray;

public class Selectmealfragment extends Fragment {

    static int currentWeekIndex,currentDayIndex,currentMealIndex , referenceWeekIndex=0;
    static String currentDay,currentWeek;
    TextView pleaseSelectMealTextView,btv,ltv,stv,dtv,selectedDateTextView;
    CalendarView calendarView;
    Calendar calendar;
    Date referenceDate;
    String selectedDayString;
    Calendar referenceCalendar = Calendar.getInstance(Locale.US);

    public static Selectmealfragment newInstance() {
        Selectmealfragment fragment = new Selectmealfragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.selectmealfragment, container, false);
        calendarView = view.findViewById(R.id.calendar);

        referenceCalendar.set(Calendar.MILLISECOND, 0);
        referenceCalendar.set(Calendar.YEAR , 2019);
        referenceCalendar.set(Calendar.MONTH , 02);
        referenceCalendar.set(Calendar.DAY_OF_MONTH , 31);
        referenceDate = referenceCalendar.getTime();

        pleaseSelectMealTextView = (TextView)view.findViewById(R.id.pleaseSelectMeal);

        calendar = Calendar.getInstance(Locale.US);
        calendarView.setUnfocusedMonthDateColor(getResources().getColor(R.color.textColor));

        Homepagefragment hf = new Homepagefragment();
        hf.setMealArray();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){

            @Override
            public void onSelectedDayChange(CalendarView view, int year,
                                            int month, int dayOfMonth) {
                setMeal(year , month , dayOfMonth);
            }

        });


        return view;
    }

    public void setMeal (int year , int month , int dayOfMonth) {
        currentMealIndex=1;

        Calendar selectedCalendar = Calendar.getInstance(Locale.US);
        selectedCalendar.set(Calendar.YEAR , year);
        selectedCalendar.set(Calendar.MILLISECOND , 0);
        selectedCalendar.set(Calendar.MONTH , month);
        selectedCalendar.set(Calendar.DAY_OF_MONTH , dayOfMonth);

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE d MMMM");
        selectedDayString = sdf.format(selectedCalendar.getTime());

        Date selectedDate = selectedCalendar.getTime();
        int daysBetween=(int)((selectedDate.getTime()-referenceDate.getTime())/(3600*24*1000));


        if(month==2 && dayOfMonth==30) {
            currentWeekIndex = 1;
        }
        else if(daysBetween>=0) {
            referenceWeekIndex = 2;
            currentWeekIndex = (3+referenceWeekIndex + daysBetween/7)%4 +1;
        } else {
            referenceWeekIndex = 1;
            currentWeekIndex = (4*Math.abs(daysBetween) + 3 + referenceWeekIndex + ((daysBetween)/7))%4 + 1;
        }


        switch (selectedCalendar.get(Calendar.DAY_OF_WEEK))
        {
            case 1 : currentDay="Sunday"; currentDayIndex=1;  break;
            case 2 : currentDay="Monday"; currentDayIndex=2; break;
            case 3 : currentDay="Tuesday"; currentDayIndex=3;break;
            case 4 : currentDay="Wednesday"; currentDayIndex=4; break;
            case 5 : currentDay="Thursday"; currentDayIndex=5; break;
            case 6 : currentDay="Friday"; currentDayIndex=6; break;
            case 7 : currentDay="Saturday"; currentDayIndex=7; break;
        }

        showDialog();

    }

    private void showDialog() {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.popuplayout);
        dialog.setTitle("Meals");

        btv = (TextView) dialog.findViewById(R.id.breakfast);
        btv.setText(mealArray[currentWeekIndex][currentDayIndex][1]);

        ltv = (TextView) dialog.findViewById(R.id.lunch);
        ltv.setText(mealArray[currentWeekIndex][currentDayIndex][2]);

        stv = (TextView) dialog.findViewById(R.id.snacks);
        stv.setText(mealArray[currentWeekIndex][currentDayIndex][3]);

        dtv = (TextView) dialog.findViewById(R.id.dinner);
        dtv.setText(mealArray[currentWeekIndex][currentDayIndex][4]);

        selectedDateTextView = (TextView) dialog.findViewById(R.id.selectedDate);
        selectedDateTextView.setText(selectedDayString);


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.show();
    }

}