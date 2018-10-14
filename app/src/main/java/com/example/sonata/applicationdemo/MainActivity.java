package com.example.sonata.applicationdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private Button button0,button1,button2,button3,button4,button5,button6,button7,button8,button9,buttonPoint,buttonAdd,buttonSub,buttonMul,buttonDiv,buttonEqual,buttonCE,buttonDelete;
    private Button buttonPercent,buttonEvo,buttonLeft,buttonRight,buttonSin,buttonCos,buttonTan,buttonxy,buttonOpt,buttonLog,buttonCsin,buttonCcos,buttonCtan,buttonLn,buttonPI,buttonZF,buttonSquare;
    private TextView textView,textView2;
    private double numberOne = 0;
    private int bracket = 0; //1代表左括号，2代表右括号
    private String def = "",def2 = "";//def存储整个运算,def2存储每次数字,def3是textView2的显示;
    private boolean last = true, point = false,flag = true;//false默认为无小数点，true为有小数点
    private Stack<Double> numberStack = new Stack<Double>(); //存数字
    private Stack<Character> characterStack = new Stack<Character>();//存运算符

    //"." -> point = true, last = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);

        textView.setText(def);
        textView2.setText(def);

        button0 = (Button) findViewById(R.id.button0);

        button0.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                if(def!="" && def.charAt(def.length()-1)=='÷')
                {
                    Toast.makeText(MainActivity.this,"警告：除数不能为0！",Toast.LENGTH_LONG).show();
                }
                else {
                    String number = button0.getText().toString();
                    last = true;
                    def = def + number;
                    textView.setText(def);}
            }
        });

        button1 = (Button) findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                getButtonId(button1);
            }
        });

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getButtonId(button2);
            }
        });

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getButtonId(button3);
            }
        });

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getButtonId(button4);
            }
        });

        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getButtonId(button5);
            }
        });

        button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getButtonId(button6);
            }
        });

        button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getButtonId(button7);
            }
        });

        button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getButtonId(button8);
            }
        });

        button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getButtonId(button9);
            }
        });

        buttonPoint = (Button) findViewById(R.id.buttonPoint);
        buttonPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = buttonPoint.getText().toString();
                if(point!=true)
                {
                    def = def + number;
                    point = last = true;
                }
                textView.setText(def);
            }
        });

        buttonEvo = (Button) findViewById(R.id.buttonEvo);
        buttonEvo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double result = Double.parseDouble(def);
                result = Math.sqrt(result);
                Function(result);
            }
        });

        buttonSquare = (Button) findViewById(R.id.buttonSquare);
        buttonSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double result = Double.parseDouble(def);
                result = Math.pow(result,2);
                Function(result);
            }
        });

        buttonxy = (Button) findViewById(R.id.buttonxy);
        buttonxy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double result = Double.parseDouble(def);
                result = Math.pow(10,result);
                Function(result);
            }
        });

        //有点问题
        buttonPercent = (Button) findViewById(R.id.buttonPercent);
        buttonPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double result = Double.parseDouble(def);
                NumberFormat nf = NumberFormat.getNumberInstance();
                nf.setMaximumFractionDigits(4);
                def = nf.format(result);
                textView.setText(def);
                textView2.setText(def);
            }
        });

        buttonSin = (Button) findViewById(R.id.buttonSin);
        buttonSin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double result = Double.parseDouble(def);
                result = Math.sin(Math.PI*result/180);
                Function(result);
            }
        });

        buttonCos = (Button) findViewById(R.id.buttonCos);
        buttonCos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double result = Double.parseDouble(def);
                result = Math.cos(Math.PI*result/180);
                Function(result);
            }
        });

        buttonTan = (Button) findViewById(R.id.buttonTan);
        buttonTan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double result = Double.parseDouble(def);
                result = Math.tan(Math.PI*result/180);
                Function(result);
            }
        });

        buttonOpt = (Button) findViewById(R.id.buttonOpt);
        buttonOpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double result = Double.parseDouble(def);
                result = Math.pow(result,1/2);
                Function(result);
            }
        });

        buttonLog = (Button) findViewById(R.id.buttonLog);
        buttonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double result = Double.parseDouble(def);
                result = Math.log(result);
                Function(result);
            }
        });

        buttonPI = (Button) findViewById(R.id.buttonPI);
        buttonPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                def = Double.toString(Math.PI);
                textView.setText(def);
                textView2.setText(def);
            }
        });

        buttonZF = (Button) findViewById(R.id.buttonZF);
        buttonZF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double result = Double.parseDouble(def);
                result = 0 - result;
                Function(result);
            }
        });

        buttonCsin = (Button) findViewById(R.id.buttonCsin);
        buttonCsin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double result = Double.parseDouble(def);
                result = Math.asin(result);
                Function(result);
            }
        });

        buttonCcos = (Button) findViewById(R.id.buttonCcos);
        buttonCcos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double result = Double.parseDouble(def);
                result = Math.acos(result);
                Function(result);
            }
        });

        buttonCtan = (Button) findViewById(R.id.buttonCtan);
        buttonCtan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double result = Double.parseDouble(def);
                result = Math.atan(result);
                Function(result);
            }
        });

        buttonCE = (Button) findViewById(R.id.buttonCE);
        buttonCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                def = def2 = "";
                textView.setText("");
                textView2.setText("");
                last = true; point = false;
            }
        });

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = buttonAdd.getText().toString();
                if(def!="")
                {
                    if (last==true)
                    {
                        def = def + number;
                        point = false;
                        last = true;
                    }
                }
                textView.setText(def);
            }
        });

        buttonSub = (Button) findViewById(R.id.buttonSub);
        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = buttonSub.getText().toString();
                if(def!="")
                {
                    if (last==true)
                    {
                        def = def + number;
                        point = false;
                        last = true;
                    }
                }
                textView.setText(def);
            }
        });

        buttonDiv = (Button) findViewById(R.id.buttonDiv);
        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = buttonDiv.getText().toString();
                if(def!="")
                {
                    if (last==true)
                    {
                        def = def + number;
                        point = false;
                        last = true;
                    }
                }
                textView.setText(def);
            }
        });

        buttonMul = (Button) findViewById(R.id.buttonMul);
        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = buttonMul.getText().toString();
                if(def!="")
                {
                    if (last==true)
                    {
                        def = def + number;
                        point = false;
                        last = true;
                    }
                }
                textView.setText(def);
            }
        });

        buttonLeft = (Button) findViewById(R.id.buttonLeft);
        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = buttonLeft.getText().toString();
                if(bracket==0)
                {
                    def = def + number;
                    bracket = 1;
                }
                textView.setText(def);
            }
        });

        buttonRight = (Button) findViewById(R.id.buttonRight);
        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = buttonRight.getText().toString();
                if(bracket==1)
                {
                    def = def +number;
                    bracket = 2;
                }
                textView.setText(def);
                bracket = 0;
            }
        });

        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                def = def.substring(0,def.length()-1);
                textView.setText(def);
            }
        });

        buttonEqual = (Button) findViewById(R.id.buttonEqual);
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = buttonEqual.getText().toString();
                def = def + number; //加入等号，作为运算结尾，并且不加入栈

                double result = Calculate(def);
                def = Double.toString(result);

                textView.setText(def);
                textView2.setText(def);
                def2 ="";last=true;point=false;
            }
        });
    }

    public void Function(double result)
    {
        def = Double.toString(result);
        textView.setText(def);
        textView2.setText(def);
    }
    public void getButtonId(Button button)
    {
        String number = button.getText().toString();
        last = true;
        def = def + number;
        textView.setText(def);
    }

    public double Calculate(String def)//点击等号触发函数
    {
        for(int i=0;i<def.length();i++)
        {
            char ch = def.charAt(i);
            if(Character.isDigit(ch) || ch=='.') {
                def2 = def2 + ch;
            }
            else
            {
                if(def2!="") {
                    numberOne = Double.parseDouble(def2);
                    numberStack.push(numberOne);
                }
                    while (!characterStack.isEmpty() && Compare(ch))//符号栈不为空，且当前符号优先级低于栈顶。直到高于。
                    {
                        char character = characterStack.pop();
                        double secNum = numberStack.pop();
                        double fisNum = numberStack.pop();
                        switch (character) {
                            case '+':
                                numberStack.push(secNum + fisNum);
                                break;
                            case '-':
                                numberStack.push(fisNum - secNum);
                                break;
                            case '×':
                                numberStack.push(fisNum * secNum);
                                break;
                            case '÷':
                                numberStack.push(fisNum / secNum);
                                break;
                            default:
                                break;
                        }
                    }

                if(ch != '=')
                {
                    characterStack.push(ch);
                    if(ch == ')')
                    {
                        characterStack.pop(); //弹出右括号
                        characterStack.pop(); //弹出左括号
                    }
                }

                def2 = "";
            }
        }
        return numberStack.pop();
    }

    public boolean Compare(char operate) //可进行取出操作的为true,operate是后一运算符
    {
        if(characterStack.empty())
        {
            flag = false;
        }

        char stackTop = characterStack.peek();
        if (stackTop == '(')//此时栈顶元素级别最高，但并不能取出
        {
            flag = false;
            return flag;
        }

        switch (operate) //平级的运算符，默认operate更低一级。
        {
            case '(':
                flag = false;
                break;
            case '×':{
                if(stackTop == '+' || stackTop == '-'){
                    flag = false;
                    break;
                }
                else{
                    flag = true;
                    break;
                }
            }
            case '÷':{
                if(stackTop == '+' || stackTop == '-'){
                    flag = false;
                    break;
                }
                else{
                    flag = true;
                    break;
                }
            }
            case '+':{
                flag = true;
                break;
            }
            case '-':{
                flag = true;
                break;
            }
            case ')':{
                flag = true;
                break;
            }
            case '=': //等号等级最低
            {
                flag = true;
                break;
            }
            default:break;
        }

        return flag;
    }

}
