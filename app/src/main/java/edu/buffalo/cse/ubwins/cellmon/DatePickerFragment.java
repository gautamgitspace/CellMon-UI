package edu.buffalo.cse.ubwins.cellmon;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Gautam on 10/28/16.
 * MBP111.0138.B16
 * agautam2@buffalo.edu
 * University at Buffalo, The State University of New York.
 * Copyright © 2016 Gautam. All rights reserved.
 */

public class DatePickerFragment extends BaseActivity
{
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datepicker);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view)
    {
        showDialog(555);
    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        if (id == 555)
        {
            DatePickerDialog datePickerDialog =  new DatePickerDialog(this, myDateListener, year, month, day);
            datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
            Date today = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(today);
            /*set min date to 6 months back*/
            c.add( Calendar.MONTH, -6);
            long minDate = c.getTime().getTime();
            datePickerDialog.getDatePicker().setMinDate(minDate);
            return  datePickerDialog;
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener()
    {

        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3)
        {
            // TODO manipulate arguments
            showDate(arg1, arg2+1, arg3);
        }
    };

    private void showDate(int year, int month, int day)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(year).append('.').append(month).append('.').append(day);
        Toast.makeText(getApplicationContext(), sb.toString(), Toast.LENGTH_SHORT).show();
    }
}
