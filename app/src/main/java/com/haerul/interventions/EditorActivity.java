package com.haerul.interventions;



import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorActivity extends AppCompatActivity {

    private Spinner mTechSpinner;
    private EditText mRequest_info;
    private EditText mRequest_desc;
    private EditText mRequester_name;
    private EditText mAssign_date;
    private EditText mMobile;
    private EditText mRequester_city;
    private EditText mRequester_add1;
    private EditText mRequest_id;
    private EditText mRequester_zip;
    private EditText mRequester_email ;


    Calendar myCalendar = Calendar.getInstance();

    private int mTech = 0;

    public static final int reda = 0;
    public static final int ali = 1;
    public static final int anwar = 2;
    public static final int radouane = 3;

    private String request_info, request_id ,request_desc,requester_zip, requester_name, mobile, assign_date, requester_city, requester_add1, requester_email;
    private int id, tech;

    private Menu action;
    private Bitmap bitmap;

    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mRequest_info = findViewById(R.id.request_info);
        mRequest_id = findViewById(R.id.request_id);
        mRequest_desc = findViewById(R.id.request_desc);
        mRequester_name = findViewById(R.id.requester_name);
        mAssign_date = findViewById(R.id.assign_date);
        mRequester_city = findViewById(R.id.requester_city);
        mRequester_add1 = findViewById(R.id.requester_add1);
        mRequester_email = findViewById(R.id.requester_email);
        mMobile = findViewById(R.id.mobile);
        mRequester_zip = findViewById(R.id.requester_zip);


        mTechSpinner = findViewById(R.id.tech);
        mAssign_date = findViewById(R.id.assign_date);

        mAssign_date.setFocusableInTouchMode(false);
        mAssign_date.setFocusable(false);
        mAssign_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditorActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        setupSpinner();

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        request_info = intent.getStringExtra("request_info");
        request_id = intent.getStringExtra("request_id");
        request_desc = intent.getStringExtra("request_desc");
        requester_name = intent.getStringExtra("requester_name");
        assign_date = intent.getStringExtra("assign_date");
        requester_city = intent.getStringExtra("requester_city");
        requester_add1 = intent.getStringExtra("requester_add1");
        requester_zip = intent.getStringExtra("requester_zip");
        requester_email = intent.getStringExtra("requester_email");
        mobile = intent.getStringExtra("mobile");
        tech = intent.getIntExtra("tech", 0);
        mobile = intent.getStringExtra("mobile");
        setDataFromIntentExtra();

    }

    private void setDataFromIntentExtra() {

        if (id != 0) {

            readMode();
            getSupportActionBar().setTitle("Modifier l'interv N: " + request_id.toString());

            mRequest_info.setText(request_info);
            mRequest_id.setText(request_id);
            mRequest_desc.setText(request_desc);
            mRequester_name.setText(requester_name);
            mAssign_date.setText(assign_date);
            mMobile.setText(mobile);
            mRequester_city.setText(requester_city);
            mRequester_add1.setText(requester_add1);
            mRequester_zip.setText(requester_zip);
            mRequester_email.setText(requester_email);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.skipMemoryCache(true);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            requestOptions.placeholder(R.drawable.logo);
            requestOptions.error(R.drawable.logo);


            switch (tech) {
                case ali:
                    mTechSpinner.setSelection(1);
                    break;
                case radouane:
                    mTechSpinner.setSelection(2);
                    break;
                case anwar:
                    mTechSpinner.setSelection(3);
                default:
                    mTechSpinner.setSelection(0);
                    break;
            }

        } else {
            getSupportActionBar().setTitle("Ajouter une intervention");
        }
    }

    private void setupSpinner(){
        ArrayAdapter techSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_tech_options, android.R.layout.simple_spinner_item);
        techSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mTechSpinner.setAdapter(techSpinnerAdapter);

        mTechSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.ali))) {
                        mTech = ali;
                    } else if (selection.equals(getString(R.string.radouane))) {
                        mTech = radouane;
                    } else if (selection.equals(getString(R.string.anwar))){
                        mTech = anwar;
                    } else{
                        mTech = reda;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mTech = 0;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor, menu);
        action = menu;
        action.findItem(R.id.menu_save).setVisible(false);

        if (id == 0){

            action.findItem(R.id.menu_edit).setVisible(false);
            action.findItem(R.id.menu_delete).setVisible(false);
            action.findItem(R.id.menu_save).setVisible(true);

        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                this.finish();

                return true;
            case R.id.menu_edit:
                //Edit

                editMode();

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(mRequest_info, InputMethodManager.SHOW_IMPLICIT);

                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_delete).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(true);

                return true;
            case R.id.menu_save:
                //Save

                if (id == 0) {

                    if (TextUtils.isEmpty(mRequest_info.getText().toString()) ||
                            TextUtils.isEmpty(mRequest_id.getText().toString()) ||
                            TextUtils.isEmpty(mRequest_desc.getText().toString()) ||
                            TextUtils.isEmpty(mRequester_name.getText().toString()) ||
                            TextUtils.isEmpty(mMobile.getText().toString()) ||
                            TextUtils.isEmpty(mAssign_date.getText().toString()) ||
                            TextUtils.isEmpty(mRequester_add1.getText().toString())||
                            TextUtils.isEmpty(mRequester_city.getText().toString())||
                            TextUtils.isEmpty(mRequester_email.getText().toString())){
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                        alertDialog.setMessage("Please complete the field!");
                        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    }

                    else {

                        postData("insert");
                        action.findItem(R.id.menu_edit).setVisible(true);
                        action.findItem(R.id.menu_save).setVisible(false);
                        action.findItem(R.id.menu_delete).setVisible(true);


                        readMode();

                    }

                } else {

                    updateData("update", id);
                    action.findItem(R.id.menu_edit).setVisible(true);
                    action.findItem(R.id.menu_save).setVisible(false);
                    action.findItem(R.id.menu_delete).setVisible(true);

                    readMode();
                }

                return true;
            case R.id.menu_delete:

                AlertDialog.Builder dialog = new AlertDialog.Builder(EditorActivity.this);
                dialog.setMessage("Supprimer cette intervention?");
                dialog.setPositiveButton("Yes" ,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        deleteData("delete", id);
                    }
                });
                dialog.setNegativeButton("Cencel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setAssign_date();
        }

    };

    private void setAssign_date() {
        String myFormat = "dd MMMM yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mAssign_date.setText(sdf.format(myCalendar.getTime()));
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();


        }
    }

    private void postData(final String key) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        progressDialog.show();

        readMode();

        String request_info = mRequest_info.getText().toString().trim();
        String request_id = mRequest_id.getText().toString().trim();
        String request_desc = mRequest_desc.getText().toString().trim();
        String requester_name = mRequester_name.getText().toString().trim();
        String requester_city = mRequester_city.getText().toString().trim();
        String requester_email = mRequester_email.getText().toString().trim();
        String requester_add1 = mRequester_add1.getText().toString().trim();
        String requester_zip = mRequester_zip.getText().toString().trim();
        int tech = mTech;
        String assign_date = mAssign_date.getText().toString().trim();
        String mobile = mMobile.getText().toString().trim();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Interventions> call = apiInterface.insertIntervention(key, request_info, request_id,request_desc, requester_name, tech , assign_date,requester_city,requester_add1,requester_zip,requester_email, mobile);

        call.enqueue(new Callback<Interventions>() {
            @Override
            public void onResponse(Call<Interventions> call, Response<Interventions> response) {

                progressDialog.dismiss();

                Log.i(EditorActivity.class.getSimpleName(), response.toString());

                String value = response.body().getValue();
                String message = response.body().getMassage();

                if (value.equals("1")){
                    finish();
                } else {
                    Toast.makeText(EditorActivity.this, message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Interventions> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditorActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateData(final String key, final int id) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        readMode();

        String request_info = mRequest_info.getText().toString().trim();
        String request_desc = mRequest_desc.getText().toString().trim();
        String request_id = mRequest_id.getText().toString().trim();
        String requester_add1= mRequester_add1.getText().toString().trim();
        String requester_name = mRequester_name.getText().toString().trim();
        String requester_email = mRequester_email.getText().toString().trim();
        String requester_zip = mRequester_zip.getText().toString().trim();
        String requester_city = mRequester_city.getText().toString().trim();
        int tech = mTech;
        String assign_date = mAssign_date.getText().toString().trim();
        String mobile = mMobile.getText().toString().trim();


        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Interventions> call = apiInterface.updateIntervention(key, id,request_info,request_id,request_desc, requester_name, tech, assign_date,requester_city,requester_add1,requester_zip,requester_email,  mobile);

        call.enqueue(new Callback<Interventions>() {
            @Override
            public void onResponse(Call<Interventions> call, Response<Interventions> response) {

                progressDialog.dismiss();

                Log.i(EditorActivity.class.getSimpleName(), response.toString());

                String value = response.body().getValue();
                String message = response.body().getMassage();

                if (value.equals("1")){
                    Toast.makeText(EditorActivity.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditorActivity.this, message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Interventions> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditorActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteData(final String key, final int id) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Deleting...");
        progressDialog.show();

        readMode();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Interventions> call = apiInterface.deleteIntervention(key, id);

        call.enqueue(new Callback<Interventions>() {
            @Override
            public void onResponse(Call<Interventions> call, Response<Interventions> response) {

                progressDialog.dismiss();

                Log.i(EditorActivity.class.getSimpleName(), response.toString());

                String value = response.body().getValue();
                String message = response.body().getMassage();

                if (value.equals("1")){
                    Toast.makeText(EditorActivity.this, message, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditorActivity.this, message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Interventions> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditorActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    void readMode(){

        mRequest_info.setFocusableInTouchMode(false);
        mRequest_desc.setFocusableInTouchMode(false);
        mRequest_id.setFocusableInTouchMode(false);
        mRequester_name.setFocusableInTouchMode(false);
        mRequester_city.setFocusableInTouchMode(false);
        mRequester_add1.setFocusableInTouchMode(false);
        mRequester_email.setFocusableInTouchMode(false);
        mRequester_zip.setFocusableInTouchMode(false);
        mRequest_info.setFocusable(false);
        mRequest_desc.setFocusable(false);
        mRequest_id.setFocusable(false);
        mRequester_city.setFocusable(false);
        mRequester_add1.setFocusable(false);
        mRequester_email.setFocusable(false);
        mRequest_desc.setFocusable(false);
        mRequester_name.setFocusable(false);
        mRequester_zip.setFocusable(false);

        mTechSpinner.setEnabled(false);
        mAssign_date.setEnabled(false);


        mMobile.setFocusableInTouchMode(false);
        mMobile.setFocusable(false);
    }

    private void editMode(){

        mRequest_info.setFocusableInTouchMode(true);
        mRequest_desc.setFocusableInTouchMode(true);
        mRequest_id.setFocusable(false);
        mRequester_name.setFocusableInTouchMode(true);
        mRequester_zip.setFocusableInTouchMode(true);
        mRequester_city.setFocusableInTouchMode(true);
        mRequester_add1.setFocusableInTouchMode(true);
        mRequester_email.setFocusableInTouchMode(true);
        mTechSpinner.setEnabled(true);
        mAssign_date.setEnabled(true);

        mMobile.setFocusableInTouchMode(true);


    }

}
