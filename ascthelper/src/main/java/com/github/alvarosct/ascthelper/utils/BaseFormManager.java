package com.github.alvarosct.ascthelper.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.alvarosct.ascthelper.IBaseModel;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Android-Dev on 03/05/2017.
 */

public class BaseFormManager {

    protected int resourceId;
    protected String action;
    protected Context context;

    public BaseFormManager(Context context) {
        this.context = context;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setupCalendarView(final EditText editText, String value, final IEditText iEditText) {
        editText.setFocusable(false);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar currentDate = UtilMethods.stringToCalendar(editText.getText().toString(), Constants.DATE_FORMAT);
                int year = currentDate.get(Calendar.YEAR);
                int monthOfYear = currentDate.get(Calendar.MONTH);
                int dayOfMonth = currentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar time = Calendar.getInstance();
                        time.set(year, monthOfYear, dayOfMonth);
                        iEditText.setValue(UtilMethods.calendarToString(time, Constants.BD_DATE_FORMAT));
                        editText.setText(UtilMethods.calendarToString(time, Constants.DATE_FORMAT));
                    }
                }, year, monthOfYear, dayOfMonth);
//                dialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());
                dialog.show();
            }
        });
        editText.setText(UtilMethods.calendarStringToString(value, Constants.BD_DATE_FORMAT, Constants.DATE_FORMAT));
    }

    public void setupEditTextNumber(EditText editText, String value, final IEditText iEditText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable.toString())){
                    iEditText.setValue("0");
                } else {
                    iEditText.setValue(editable.toString());
                }
            }
        });

        if (Double.parseDouble(value) == 0){
            editText.setText("");
        } else {
            editText.setText(value);
        }
    }



    public void setupEditText(EditText editText, String value, final IEditText iEditText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                iEditText.setValue(editable.toString());
            }
        });
        editText.setText(value);
    }

    public void setupLayoutEditText(EditText editText, String value, final IEditText iEditText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                iEditText.setValue(editable.toString());
            }
        });
        editText.setText(value);
    }

    public void setupEditTextNumber(EditText editText, int value, final IEditText iEditText) {
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                iEditText.setValue(editable.toString());
            }
        });
        editText.setText("" + value);
    }


    public interface ISpinner {
        void setValueId(int id);
    }

    public interface IEditText {
        void setValue(String value);
    }

    public interface IValidate {
        void validate(String value);
    }
    //endregion
}
