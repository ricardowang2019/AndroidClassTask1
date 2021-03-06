package com.example.testtask1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    //定义好需要获取到的元素
    public TextView ce,percent,change_sign,division,nine,eight,seven,six,five,four,three,two,one,plus,sub,multi,euqal,point,zero,square,sin,cos;
    public EditText my_result;
    public int temp = 0;
    boolean is_result=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init_view();
        //设置点击事件
        init_click_event();
    }

    /**
     * 设置点击
     */
    private void init_click_event() {
        ce.setOnClickListener(this);
        percent.setOnClickListener(this);
        change_sign.setOnClickListener(this);
        change_sign.setOnClickListener(this);
        division.setOnClickListener(this);
        nine.setOnClickListener(this);
        eight.setOnClickListener(this);
        seven.setOnClickListener(this);
        six.setOnClickListener(this);
        five.setOnClickListener(this);
        four.setOnClickListener(this);
        three.setOnClickListener(this);
        two.setOnClickListener(this);
        one.setOnClickListener(this);
        plus.setOnClickListener(this);
        sub.setOnClickListener(this);
        multi.setOnClickListener(this);
        euqal.setOnClickListener(this);
        point.setOnClickListener(this);
        zero.setOnClickListener(this);
        square.setOnClickListener(this);
        sin.setOnClickListener(this);
        cos.setOnClickListener(this);
    }

    /**
     * 在这个函数里找到控件
     */
    private void init_view() {
        ce = (TextView) this.findViewById(R.id.cancel);
        percent = (TextView) this.findViewById(R.id.percent);
        change_sign = (TextView) this.findViewById(R.id.change_sign);
        division = (TextView) this.findViewById(R.id.division);
        nine = (TextView) this.findViewById(R.id.nine);
        eight = (TextView) this.findViewById(R.id.eight);
        seven = (TextView) this.findViewById(R.id.seven);
        six = (TextView) this.findViewById(R.id.six);
        five = (TextView) this.findViewById(R.id.five);
        four = (TextView) this.findViewById(R.id.four);
        three = (TextView) this.findViewById(R.id.three);
        two = (TextView) this.findViewById(R.id.two);
        one = (TextView) this.findViewById(R.id.one);
        plus = (TextView) this.findViewById(R.id.plus);
        sub = (TextView) this.findViewById(R.id.sub);
        multi = (TextView) this.findViewById(R.id.multi);
        euqal = (TextView) this.findViewById(R.id.euqal);
        point = (TextView) this.findViewById(R.id.point);
        my_result = (EditText) this.findViewById(R.id.my_result);
        zero = (TextView) this.findViewById(R.id.zero);
        square = (TextView) this.findViewById(R.id.square);
        sin = (TextView) this.findViewById(R.id.sin);
        cos = (TextView) this.findViewById(R.id.cos);
        //...

    }


    @Override
    public void onClick(View v) {
        String result = my_result.getText().toString();
        char judge;

        //TODO 想做一个能够改变结果字体大小的功能，或者能够伸缩展示结果
        switch(v.getId()){
            case R.id.cancel:
                result = "0";
                my_result.setText(result);
                break;
            case R.id.change_sign:
                try{
                for(int i=0;i<result.length();i++){
                    judge = result.charAt(i);
                    if(judge=='/' || judge=='X' || judge=='—' || judge=='+'){
                        temp = i;
                    }
                }
                //非单独数字,给最后一个运算数更改符号
                if(temp!=0){
                    String want_to_chang = result.substring(temp+1,result.length());
                    System.out.println(want_to_chang);
                    if(want_to_chang.charAt(0)!='('){
                        want_to_chang = "(-"+want_to_chang+")";
                    }else{
                        want_to_chang = want_to_chang.replace("(-","");
                        want_to_chang = want_to_chang.replace(")","");
                    }
                    System.out.println(want_to_chang);
                    my_result.setText(result.substring(0,temp+1)+want_to_chang);
                    temp = 0;
                }else{
                    //如果是单独的数字，那么检查是否包含负号
                    if(result.contains("-")){
                        my_result.setText(result.replace("-",""));
                    }else {
                        if(result!=""){
                            if(result.equals("0") || result.equals("-0")){
                                my_result.setText("0");
                            }else {
                                my_result.setText("-" + result);
                            }
                        }else {
                            my_result.setText("0");
                        }

                    }

                }
                }catch (Exception e){
                    my_result.setText("计算错误！");
                }

                break;
            case R.id.division:
            case R.id.plus:
            case R.id.multi:
            case R.id.sub:
            case R.id.point:

                if(result.length()>=1){
                    judge = result.charAt(result.length()-1);
                    if(judge=='/' || judge=='X' || judge=='—' || judge=='+'){
                        result = result.substring(0,result.length()-1);
                    }
                    result = result + ((TextView) v).getText();
                    my_result.setText(result);
                    is_result = false;
                }else if(result.equals("")){
                    result = "0.";
                    my_result.setText(result);
                    is_result = false;
                }

                break;
            case R.id.nine:
            case R.id.eight:
            case R.id.seven:
            case R.id.six:
            case R.id.five:
            case R.id.four:
            case R.id.three:
            case R.id.two:
            case R.id.one:
            case R.id.zero:
                if(result.equals("0") ){
                    result = "";
                }else if(is_result){
                    result = "";
                    is_result = false;
                }
                result = result + ((TextView) v).getText();
                my_result.setText(result);
                break;
            case R.id.percent:
                try {
                    if(result.length()>=1){
                        judge = result.charAt(result.length()-1);
                        if(judge=='/' || judge=='X' || judge=='—' || judge=='+'){
                            result = result.substring(0,result.length()-1);
                        }
                        result = String.valueOf(Double.parseDouble(result) / 100);
                        my_result.setText(result);
                        is_result = true;
                    }else if(result.equals("")){
                        result = "0";
                        my_result.setText(result);
                        is_result = false;
                    }
                }catch (Exception e){
                    my_result.setText("计算错误！");
                }


                break;
            case R.id.square:
                try{
                        if(result.length()>=1){
                            judge = result.charAt(result.length()-1);
                            if(judge=='/' || judge=='X' || judge=='—' || judge=='+'){
                                result = result.substring(0,result.length()-1);
                            }
                            //检查是否包含
                            for(int i=0;i<result.length();i++){
                                judge = result.charAt(i);
                                if(judge=='/' || judge=='X' || judge=='—' || judge=='+'){
                                    temp = i;
                                }
                            }

                            result = result.substring(temp,result.length());
                            System.out.println(result);
                            if(Double.parseDouble(result)>0){
                                result = String.valueOf(Math.sqrt(Double.parseDouble(result)));
                                my_result.setText(result);
                            }else{
                                result = "错误";
                                my_result.setText(result);
                            }

                        }else if(result.equals("")){
                            result = "错误";
                            my_result.setText(result);
                        }
                }catch (Exception e){
                    my_result.setText("计算错误！");
                }


                break;
            case R.id.sin:
            try{
                if(result.length()>=1){
                    judge = result.charAt(result.length()-1);
                    if(judge=='/' || judge=='X' || judge=='—' || judge=='+'){
                        result = result.substring(0,result.length()-1);
                    }
                    //检查是否包含
                    temp = 0;
                    for(int i=0;i<result.length();i++){
                        judge = result.charAt(i);
                        if(judge=='/' || judge=='X' || judge=='—' || judge=='+'){
                            temp = i;
                        }
                    }
                    result = result.substring(temp,result.length());
                    result = String.valueOf(Math.sin(Math.toRadians(Double.parseDouble(result))));
                    my_result.setText(result);
                }else if(result.equals("")){
                    result = "0";
                    result = String.valueOf(Math.sin(Math.toRadians(Double.parseDouble(result))));
                    my_result.setText(result);
                }
            }catch (Exception e){
                my_result.setText("计算错误！");
            }


                break;
            case R.id.cos:
            try{
                if(result.length()>=1){
                    judge = result.charAt(result.length()-1);
                    if(judge=='/' || judge=='X' || judge=='—' || judge=='+'){
                        result = result.substring(0,result.length()-1);
                    }
                    //检查是否包含
                    temp = 0;
                    for(int i=0;i<result.length();i++){
                        judge = result.charAt(i);
                        if(judge=='/' || judge=='X' || judge=='—' || judge=='+'){
                            temp = i;
                        }
                    }
                    result = result.substring(temp,result.length());
                    result = String.valueOf(Math.cos(Math.toRadians(Double.parseDouble(result))));
                    my_result.setText(result);
                }else if(result.equals("")){
                    result = "0";
                    result = String.valueOf(Math.cos(Math.toRadians(Double.parseDouble(result))));
                    my_result.setText(result);
                }
            }catch (Exception e){
                my_result.setText("计算错误！");
            }

                break;
            case R.id.euqal:
                if(result.equals("")){
                    result = "0";
                    my_result.setText(result);
                }else{
                    result = cal_string(result);
                    my_result.setText(result);
                }

                break;

        }

    }
    public String cal_string(String expression){
        expression = expression.replace('X','*');
        expression = expression.replace('—','-');
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine engine = sem.getEngineByName("js");
        String ans="";
        try {
            ans = engine.eval(expression).toString();
        }
        catch (ScriptException e) {
            e.printStackTrace();
        }
        if (ans.equals("Infinity")){
            ans = "计算错误！";
        }
        return ans;
    }

}