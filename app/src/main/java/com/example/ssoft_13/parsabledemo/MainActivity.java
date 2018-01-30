package com.example.ssoft_13.parsabledemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ssoft_13.parsabledemo.Model.ContactInfo;
import com.example.ssoft_13.parsabledemo.Util.MyApplicationClass;
import com.example.ssoft_13.parsabledemo.Util.MyConstant;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends MyBaseActivity implements View.OnClickListener,MyBaseActivity.OnAlertOkClick{

    Button btnOk,btnProgress,btnProgressDismiss,btnToas,btnAlert,btnSnackbar,
        btnSavePref,btnGetPref;
    MyApplicationClass application;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        application = (MyApplicationClass)getApplication();

        btnOk=(Button)findViewById(R.id.btnOk);
        btnAlert=(Button)findViewById(R.id.btnAlert);
        btnProgress=(Button)findViewById(R.id.btnProgress);
        btnProgressDismiss=(Button)findViewById(R.id.btnProgressDismiss);
        btnToas=(Button)findViewById(R.id.btnToast);
        btnSnackbar=(Button)findViewById(R.id.btnSnackbar);
        btnSavePref=(Button)findViewById(R.id.btnSavePref);
        btnGetPref=(Button)findViewById(R.id.btnGetPref);

        btnSnackbar.setOnClickListener(this);
        btnAlert.setOnClickListener(this);
        btnToas.setOnClickListener(this);
        btnProgress.setOnClickListener(this);
        btnProgressDismiss.setOnClickListener(this);
        btnSavePref.setOnClickListener(this);
        btnGetPref.setOnClickListener(this);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);

                 /* to send single value */
                /*ContactInfo ci = createContact("Francesco", "Surviving with android", 1);
                i.putExtra("contact", ci);*/

                /* to send entire arraylist */
                List<ContactInfo> cList = createContactList();

                String json = new Gson().toJson(cList);
                i.putParcelableArrayListExtra("contact", (ArrayList) cList);
                i.putExtra("json",json);
                startActivity(i);
            }
        });
    }
    private ContactInfo createContact(String name, String surname, int idx) {
        ContactInfo ci = new ContactInfo();
        ci.setName("Francesco" + idx);
        ci.setSurname("Azzola" + idx);
        ci.setIdx(idx);

        ContactInfo.Address add = new ContactInfo.Address();
        add.setCity("London");
        add.setStreet("str Surviving with android");
        add.setNumber(1);

        ci.setAddress(add);

        return ci;
    }

    private List<ContactInfo> createContactList() {
        List<ContactInfo> cList = new ArrayList<ContactInfo>();
        for (int i=0; i < 10; i++)
            cList.add(createContact("Name " + i, "Surname " + i, i));

        return cList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAlert:
                showAlert("hi this is from me");
                break;
            case R.id.btnToast:
                showToast("demo toast");
                break;
            case R.id.btnProgress:
                showProgressDialog("demo progress");
                dismissProgressDialog();
                break;
            case R.id.btnProgressDismiss:
                dismissProgressDialog();
                break;
            case R.id.btnSnackbar:
                showSnackBar(this,false,"demo snackbar");
                break;
            case R.id.btnSavePref:
                application.saveData(MyConstant.KEY_DATA,"test data");
                break;
            case R.id.btnGetPref:
                showSnackBar(this,true,application.getData(MyConstant.KEY_DATA,""));
                break;
        }
    }
    @Override
    public void clickedOkAlert() {
        showToast("clicked on ok button alert dialog");
    }
}
