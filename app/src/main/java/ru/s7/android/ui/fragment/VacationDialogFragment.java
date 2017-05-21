package ru.s7.android.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pchmn.materialchips.ChipsInput;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import ru.s7.android.R;
import ru.s7.android.ui.mvp.view.FVacationDialogMvpView;

/**
 * Created by celikindv on 21.05.17.
 * <p>
 * This fragment responsible for show achievement
 */
public class VacationDialogFragment extends android.support.v4.app.DialogFragment implements FVacationDialogMvpView {


    private TextView descTextView;
    private TextView labelTextView;
    private TextView dateTimeTextView;
    private ChipsInput chipsInput;
    private Button cancelBtn;
    private Button dateTimeButton;
    FragmentManager fragmentManager;


    /**
     * @param manager the manager
     * @return the bottom sheet attach fragment
     */
    public static VacationDialogFragment with(FragmentManager manager) {
        VacationDialogFragment fragment = new VacationDialogFragment();
        fragment.fragmentManager = manager;
        return fragment;
    }


    /**
     * Show.
     */
    public void show() {
        show(fragmentManager, getTag());
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
        setRetainInstance(true);
        setStyle(STYLE_NORMAL, android.R.style.Theme_Material_Light_Dialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle(R.string.my_achievement_title);
        View v = inflater.inflate(R.layout.vacation_dialog, container, false);
        descTextView = (TextView) v.findViewById(R.id.descTextView);
        labelTextView = (TextView) v.findViewById(R.id.labelTextView);
        cancelBtn = (Button) v.findViewById(R.id.vacation_cancel);
        dateTimeButton = (Button) v.findViewById(R.id.dateTimeButton);
        dateTimeTextView = (TextView) v.findViewById(R.id.dateTimeTextView);
        chipsInput = (ChipsInput) v.findViewById(R.id.chips_input);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        dateTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                java.util.Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog datePickerDialog, int year, int monthOfYear, int dayOfMonth) {

                            }
                        },
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );

                dpd.show(fragmentManager, "Datepickerdialog");
            }
        });

        return v;
    }

}
