package com.example.test14062024;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {

    private Button One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Zero;

    private Button Minus, Plus, Division, Multiply, Result, Root, Square, Percent, Return;

    private TextView Formula, EndResult;

    private char Action;

    private double ResultValue = Double.NaN;

    private double Value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setupView();

        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                Formula.setText(Formula.getText().toString() + button.getText().toString());
            }
        };
        One.setOnClickListener(numberClickListener);
        Two.setOnClickListener(numberClickListener);
        Three.setOnClickListener(numberClickListener);
        Four.setOnClickListener(numberClickListener);
        Five.setOnClickListener(numberClickListener);
        Six.setOnClickListener(numberClickListener);
        Seven.setOnClickListener(numberClickListener);
        Eight.setOnClickListener(numberClickListener);
        Nine.setOnClickListener(numberClickListener);
        Zero.setOnClickListener(numberClickListener);

        View.OnClickListener actionClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Button button = (Button) view;
                if (Formula.getText().length() != 0 && Character.isDigit(Formula.getText().toString().charAt(Formula.getText().toString().length()-1)) || Formula.getText().length() == 0){
                    calculate();
                    Action = button.getText().charAt(0);
                    Formula.setText(String.valueOf(ResultValue) + Action);
                    EndResult.setText(null);
                }
            }
        };
        Plus.setOnClickListener(actionClickListener);
        Minus.setOnClickListener(actionClickListener);
        Division.setOnClickListener(actionClickListener);
        Multiply.setOnClickListener(actionClickListener);
        Percent.setOnClickListener(actionClickListener);
        Root.setOnClickListener(actionClickListener);
        Square.setOnClickListener(actionClickListener);

        Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Formula.getText().length() != 0 && Character.isDigit(Formula.getText().toString().charAt(Formula.getText().toString().length()-1))) {
                    calculate();
                    Action = '=';
                    EndResult.setText(String.valueOf(ResultValue));
                    Formula.setText(null);
                }
            }
        });

        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Formula.setText("");
                ResultValue = Double.NaN;
                EndResult.setText("0");
            }
        });
    }

    private void setupView(){
        One = (Button) findViewById(R.id.One);
        Two = (Button) findViewById(R.id.Two);
        Three = (Button) findViewById(R.id.Three);
        Four = (Button) findViewById(R.id.Four);
        Five = (Button) findViewById(R.id.Five);
        Six = (Button) findViewById(R.id.Six);
        Seven = (Button) findViewById(R.id.Seven);
        Eight = (Button) findViewById(R.id.Eight);
        Nine = (Button) findViewById(R.id.Nine);
        Zero = (Button) findViewById(R.id.Zero);
        Minus = (Button) findViewById(R.id.Minus);
        Plus = (Button) findViewById(R.id.Plus);
        Result = (Button) findViewById(R.id.Result);
        Division = (Button) findViewById(R.id.Division);
        Multiply = (Button) findViewById(R.id.Multiply);
        Root = (Button) findViewById(R.id.Root);
        Square = (Button) findViewById(R.id.Square);
        Percent = (Button) findViewById(R.id.Percent);
        Return = (Button) findViewById(R.id.Return);
        EndResult = (TextView) findViewById(R.id.EndResult);
        Formula = (TextView) findViewById(R.id.Formula);
    }

    private void calculate()
    {
        if (!Double.isNaN(ResultValue)) {
            String textFormula = Formula.getText().toString();
            int index = textFormula.indexOf(Action);
            if (index != -1) {
                String numberValue = textFormula.substring(index + 1);
                Value = Double.parseDouble(numberValue);
                switch (Action) {
                    case '/':
                        if (Value == 0) {
                            ResultValue = 0.0;
                        } else {
                            ResultValue /= Value;
                        }
                        break;
                    case '*':
                        ResultValue *= Value;
                        break;
                    case '+':
                        ResultValue += Value;
                        break;
                    case '-':
                        ResultValue -= Value;
                        break;
                    case '%':
                        ResultValue = ResultValue/100 * Value;
                        break;
                    case '=':
                        ResultValue = Value;
                        break;
                    case '√':
                        ResultValue = Math.sqrt(Value);
                        break;
                    case '²':
                        ResultValue = Math.pow(Value, 2);
                        break;
                }
            }
        }
        else
        {
            try {
                ResultValue = Double.parseDouble(Formula.getText().toString());
            } catch (Exception e){
                e.printStackTrace();
                ResultValue = 0.0;
            }
        }
        EndResult.setText(String.valueOf(ResultValue));
    }
}