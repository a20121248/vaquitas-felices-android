package com.github.alvarosct.happycows.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;

import com.github.alvarosct.ascthelper.IBaseModel;
import com.github.alvarosct.ascthelper.utils.BaseFormManager;
import com.github.alvarosct.ascthelper.utils.Constants;
import com.github.alvarosct.ascthelper.utils.SpinnerModelAdapter;
import com.github.alvarosct.ascthelper.utils.UtilMethods;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.models.SpinnerModel;
import com.github.alvarosct.happycows.utils.views.CustomEditText;
import com.github.alvarosct.happycows.utils.views.CustomSpinner;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Android-Dev on 03/05/2017.
 */

public class FormManager extends BaseFormManager {

    private static final String MANDATORY_FIELD_MESSAGE = "Este campo es obligatorio";

    public FormManager(Context context) {
        super(context);
    }


//    ---------------------------
//    BASE METHODS
//    ---------------------------


    //    BASE CALENDAR
//    ---------------------------
    private void baseSetupCalendarView(@NonNull CustomEditText customEditText, boolean mandatory,
                                       String value, IEditText iEditText, @Nullable ICustomError iCustomError) {
//        TODO: Handle Mandatory Cases
//        Disable Text Input
        customEditText.getEditText().setFocusable(false);

//        Set Listener
        customEditText.getEditText().setOnClickListener(view -> {
//            Get Current Date
            Calendar currentDate = UtilMethods.stringToCalendar(
                    customEditText.getEditText().getText().toString(), Constants.DATE_FORMAT);
            int year = currentDate.get(Calendar.YEAR);
            int monthOfYear = currentDate.get(Calendar.MONTH);
            int dayOfMonth = currentDate.get(Calendar.DAY_OF_MONTH);

//            Setup Dialog
            DatePickerDialog dialog = new DatePickerDialog(context, (datePicker, yy, my, dm) -> {
                Calendar time = Calendar.getInstance();
                time.set(yy, my, dm);
                iEditText.setValue(UtilMethods.calendarToString(time, Constants.BD_DATE_FORMAT));
                customEditText.getEditText().setText(UtilMethods.calendarToString(time, Constants.DATE_FORMAT));
            }, year, monthOfYear, dayOfMonth);

//            Show Dialog
            dialog.show();
        });

//        Set Edit Text (first time)
        Calendar dateValue = UtilMethods.stringToCalendar(value, Constants.BD_DATE_FORMAT);
        customEditText.getEditText().setText(UtilMethods.calendarToString(
                dateValue, Constants.DATE_FORMAT));

//        Set Value to Entity (set to "Now" the null values)
        iEditText.setValue(UtilMethods.calendarToString(
                dateValue, Constants.BD_DATE_FORMAT));
    }

    public void setupCalendarView(@NonNull CustomEditText customEditText, boolean mandatory,
                                  String value, IEditText iEditText) {
        baseSetupCalendarView(customEditText, mandatory, value, iEditText, null);
    }

    public void setupMandatoryCalendarView(@NonNull CustomEditText customEditText,
                                           String value, IEditText iEditText, @Nullable ICustomError iCustomError) {
        baseSetupCalendarView(customEditText, true, value, iEditText, iCustomError);
    }


    //    BASE NUMBER EDIT TEXT
//    ---------------------------

    private void baseSetupEditTextNumber(@NonNull CustomEditText customEditText, boolean mandatory,
                                         float value, int inputType,
                                         final IEditText iEditText, @Nullable ICustomError iCustomError) {

//        Setup Input Type (number, decimal, etc)
        customEditText.getEditText().setInputType(inputType);
        customEditText.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String value = editable.toString();
                if (value.equals("-")) return;

//                Prevent empty string
                iEditText.setValue(value.equals("") ? "0" : value);

//                Validation
                if (mandatory && customEditText.getEditText().isEnabled()) {
                    if (iCustomError != null) {
                        customEditText.setErrorEnable(iCustomError.isError(value.trim()), iCustomError.getError());
                    } else {
                        boolean flag = !TextUtils.isEmpty(value.trim()) &&
                                Float.parseFloat(value.trim()) != 0;
                        customEditText.setErrorEnable(!flag, MANDATORY_FIELD_MESSAGE);
                    }
                }
            }
        });
        if (inputType == InputType.TYPE_CLASS_NUMBER) {
            int intValue = (int) value;
            customEditText.getEditText().setText("" + intValue);
        } else {
            customEditText.getEditText().setText("" + value);
        }

    }

    public void setupEditTextNumber(@NonNull CustomEditText customEditText, boolean mandatory,
                                    float value, int inputType, IEditText iEditText) {
        baseSetupEditTextNumber(customEditText, mandatory, value, inputType,
                iEditText, null);
    }

    public void setupMandatoryEditTextNumber(@NonNull CustomEditText customEditText,
                                             float value, int inputType, IEditText iEditText) {
        baseSetupEditTextNumber(customEditText, true, value, inputType, iEditText, null);
    }

    public void setupMandatoryEditTextNumber(@NonNull CustomEditText customEditText,
                                             float value, int inputType, IEditText iEditText, @NonNull ICustomError iCustomError) {
        baseSetupEditTextNumber(customEditText, true, value, inputType, iEditText, iCustomError);
    }

    //    BASE EDIT TEXT
//    ---------------------------

    public void setupEditText(@NonNull CustomEditText customEditText, boolean mandatory,
                              String value, IEditText iEditText, @Nullable ICustomError iCustomError) {

        customEditText.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

//                Assign text value
                String value = editable.toString();
                iEditText.setValue(value);

//                Validation
                if (mandatory && customEditText.getEditText().isEnabled()) {

                    boolean flag;
                    String errorMessage = "";

                    if (flag = TextUtils.isEmpty(value.trim()))
                        errorMessage = MANDATORY_FIELD_MESSAGE;
                    else if ((iCustomError != null) && (flag = iCustomError.isError(value.trim())))
                        errorMessage = iCustomError.getError();

                    customEditText.setErrorEnable(flag, errorMessage);

                }
            }
        });

//        Set text value
        if (value != null && value.trim().equals("-")) {
            customEditText.getEditText().setText("");
        } else {
            customEditText.getEditText().setText(value);
        }
    }

    public interface ICustomError {

        String getError();

        boolean isError(String currentText);
    }

    public void setupEditText(@NonNull CustomEditText customEditText, boolean mandatory,
                              String value, IEditText iEditText) {
        setupEditText(customEditText, mandatory, value, iEditText, null);
    }

    public void setupMandatoryEditText(@NonNull CustomEditText customEditText,
                                       String value, IEditText iEditText) {
        setupEditText(customEditText, true, value, iEditText, null);
    }

    public void setupMandatoryEditText(@NonNull CustomEditText customEditText,
                                       String value, IEditText iEditText, @NonNull ICustomError iCustomError) {
        setupEditText(customEditText, true, value, iEditText, iCustomError);
    }

    //    BASE SPINNER
//    ---------------------------
    public void baseSetupSpinner(@NonNull CustomSpinner customSpinner, boolean mandatory,
                                  List<IBaseModel> recordList, int valueId, ISpinner iSpinner,
                                  @Nullable IUbigeo iUbigeo) {

//        Add Default option
//        recordList.add(0, new SpinnerModel());

//        Set default error
        customSpinner.setError(MANDATORY_FIELD_MESSAGE);

//        Set adapter
        final SpinnerModelAdapter<IBaseModel> adapter = (iUbigeo != null) ?
                iUbigeo.setupAdapter(recordList) :
                new SpinnerModelAdapter<>(context, R.layout.cows_spinner_item, recordList);
        adapter.setDropDownViewResource(R.layout.cows_spinner_item_dropdown);
        customSpinner.getSpinner().setAdapter(adapter);

//        Add listener
        customSpinner.getSpinner().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Display error if default selected
                if (mandatory && customSpinner.getSpinner().isEnabled()) {
                    customSpinner.setErrorEnable(i == 0);
                }

                if (i == 0) {
                    iSpinner.setValueId(0);
                    return;
                }

//                Asign value to entity
                if (iUbigeo != null) {
                    iUbigeo.setValueToEntity(adapter, i);
                } else {
                    IBaseModel sr = adapter.getItem(i);
                    if (sr != null) {
                        iSpinner.setValueId(sr.getModelId());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        Set selection
        if (iUbigeo != null) {
            customSpinner.getSpinner().setSelection(iUbigeo.getSelection(adapter, valueId));
        } else {
            customSpinner.getSpinner().setSelection(adapter.getPosById(valueId));
        }
    }

    interface IUbigeo {
        SpinnerModelAdapter setupAdapter(List<IBaseModel> records);

        void setValueToEntity(SpinnerModelAdapter adapter, int i);

        int getSelection(SpinnerModelAdapter adapter, int id);
    }


    public void baseSetupSpinner(@NonNull CustomSpinner customSpinner, boolean mandatory,
                                  List<IBaseModel> recordList, int valueId, ISpinner iSpinner) {
        baseSetupSpinner(customSpinner, mandatory, recordList, valueId, iSpinner, null);
    }

//    private void setupSpinner(@NonNull CustomSpinner customSpinner, List<IBaseModel> recordList, int valueId, ISpinner iSpinner) {
//        baseSetupSpinner(customSpinner, false, recordList, valueId, iSpinner);
//    }

    private void setupMandatorySpinner(CustomSpinner customSpinner, List<IBaseModel> recordList, int valueId, ISpinner iSpinner) {
        baseSetupSpinner(customSpinner, true, recordList, valueId, iSpinner);
    }


//    ---------------------------
//    CUSTOM SPINNER METHODS
//    ---------------------------

}
