package com.sust.attendence.Manage;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.sust.attendence.Adapter.Show_details_adapter;
import com.sust.attendence.Database.DatabaseWork;
import com.sust.attendence.Others.Absent_Record;
import com.sust.attendence.R;

import java.util.ArrayList;

/**
 * Created by Ikhtiar on 7/28/2015.
 */
public class StudentInformationActivity extends Activity {
    TextView total_class_taken,count_present,count_absent;
    private Show_details_adapter absent_record_adapter;
    private ListView details_list_view;
    private int count_tt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information);

        setupUI(findViewById(R.id.parent_std_info));
        initialize();
        grab_data();
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
    public void setupUI(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(StudentInformationActivity.this);
                    return false;
                }

            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupUI(innerView);
            }
        }
    }




    protected void initialize(){
        total_class_taken = (TextView) findViewById(R.id.total_class_tv);
        count_present = (TextView) findViewById(R.id.present_tv);
        count_absent = (TextView) findViewById(R.id.absent_tv);

        details_list_view = (ListView) findViewById(R.id.absent_details_list);
        absent_record_adapter = null;
    }

    protected void grab_data(){
        Bundle bdl =  getIntent().getExtras();
        String title_name = bdl.getString("title_name");
        int reg_no= Integer.parseInt(bdl.getString("reg_no"));

        count_tt = new DatabaseWork(this).total_class_taken(title_name);
        total_class_taken.setText("TOTAL OCCURRENCE  : " + count_tt);


        int std_id = new DatabaseWork(this).get_student_id(reg_no,title_name);
        ArrayList<Absent_Record> list = new DatabaseWork(this).get_absent_record(std_id,title_name);

        int count_ab= list.size();
        update_present_absent(count_ab);

        absent_record_adapter=new Show_details_adapter(this,R.layout.show_details_list_view,list);
        details_list_view.setAdapter(absent_record_adapter);

    }
    public void update_present_absent(int count_ab){
        count_absent.setText("ABSENT : "+count_ab);
        count_present.setText("PRESENT : "+ (count_tt-count_ab));
    }

}