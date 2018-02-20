package com.example.ravit.homework2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;   // Package used for logging used during the testing process
import android.widget.EditText; //Package used for Editext
import android.widget.TextView; //Package used for TextView
import android.widget.SeekBar; //Package used for SeekBar
import android.text.TextWatcher; //Package for TextWatcher
import android.text.Editable; //Package for Editext listener
import android.widget.SeekBar.OnSeekBarChangeListener; // Package for Seek bar changed listener

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(); // Number format object for currency format
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance(); // Number format object for percent format
    private double billAmount= 0.0; //local variables
    private double percent = 0.15;
    private int person = 0;
    private double splitAmount = 0.0;
    private TextView amountTextView; //Textview Initialization
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalTextview;
    private TextView splitAmtLabelTextView;
    private TextView splitAmtTextView;
    private TextView splitTextView;
    private EditText splitEditText; //Edittext Initialization

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountTextView = (TextView) findViewById(R.id.amounTextView); //Reference for Textview
        percentTextView = (TextView) findViewById(R.id.percentTextView);
        tipTextView = (TextView) findViewById(R.id.tipTextView);
        totalTextview = (TextView) findViewById(R.id.totalTextView);
        splitAmtLabelTextView = (TextView) findViewById(R.id.splitAmtLabelTextView);
        splitAmtTextView = (TextView) findViewById(R.id.splitAmtTextView);
        splitTextView = (TextView) findViewById(R.id.splitTextView);


        tipTextView.setText(currencyFormat.format(0)); //setting the tip
        totalTextview.setText(currencyFormat.format(0)); //setting the total
        splitAmtTextView.setText(currencyFormat.format(0)); //setting the splitamount

        EditText amountEditText= (EditText)findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(amountEditTextWatcher); //textchanged listener

        splitEditText = (EditText) findViewById(R.id.splitEditText);
        splitEditText.addTextChangedListener(splitEditTextWatcher); //textchanged listener

        SeekBar percentSeekBar = (SeekBar) findViewById(R.id.percentSeekBar);
        percentSeekBar.setOnSeekBarChangeListener(seekBarListener); //seekbar listener

    }

    //Method for calculating the bill amount, total and if number of people are given,
    // this method splits the total bill among number of specified number of persons
    private void calculate(){

        Log.i("300", "Calculate");
        System.out.println(percent);
        percentTextView.setText(percentFormat.format(percent));

        double tip = billAmount * percent ;
        double total = billAmount+tip;


        if(person>0)
        {
            splitAmount = total / person ;
        }

        tipTextView.setText(currencyFormat.format(tip));
        totalTextview.setText(currencyFormat.format(total));
        splitAmtTextView.setText(currencyFormat.format(splitAmount));
        Log.i("400", "Calculation done");


    }

    private final OnSeekBarChangeListener seekBarListener = new OnSeekBarChangeListener() {


        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            //Log.i("100", "Seek Bar1");

           System.out.println("Progress value:"+progress);

            percent = progress /100.0;

            //Log.i("100", "Seek Bar2");
            System.out.println("Percent value:"+percent);

            calculate();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private final TextWatcher amountEditTextWatcher= new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try{
                billAmount = Double.parseDouble(s.toString())/100.0;
                amountTextView.setText(currencyFormat.format(billAmount));
            }
            catch(NumberFormatException e){
                amountTextView.setText("");
                billAmount = 0.0;
            }

            calculate();

         }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private final TextWatcher splitEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.i("500","person");
                try{
                person = Integer.parseInt(s.toString());

                //splitEditText.setText(s.toString());
                System.out.println("No of persons:"+person);
            }
            catch(NumberFormatException e){
                splitEditText.setText("");
                person = 0;
            }
            Log.i("600","person done");
            calculate();
        };



        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
