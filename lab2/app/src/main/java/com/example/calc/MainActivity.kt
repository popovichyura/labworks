package com.example.calc

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    val LOG_TAG = "myLogs"
    override fun onSaveInstanceState(outState: Bundle) {
        outState?.run{
            putString("tvExpression",tvExpression.text.toString())
            putString("tvResult",tvResult.text.toString())
        }
        super.onSaveInstanceState(outState)
        Log.i(LOG_TAG, "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i(LOG_TAG, "onRestoreInstanceState")

        tvExpression.text = savedInstanceState?.getString("tvExpression")
        tvResult.text = savedInstanceState?.getString("tvResult")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Number
        tvOne.setOnClickListener{appendOnExpresstion("1",true)}
        tvTwo.setOnClickListener{appendOnExpresstion("2",true)}
        tvThree.setOnClickListener{appendOnExpresstion("3",true)}
        tvFour.setOnClickListener{appendOnExpresstion("4",true)}
        tvFive.setOnClickListener{appendOnExpresstion("5",true)}
        tvSix.setOnClickListener{appendOnExpresstion("6",true)}
        tvSeven.setOnClickListener{appendOnExpresstion("7",true)}
        tvEight.setOnClickListener{appendOnExpresstion("8",true)}
        tvNine.setOnClickListener{appendOnExpresstion("9",true)}
        tvZero.setOnClickListener{appendOnExpresstion("0",true)}
        tvDot.setOnClickListener{appendOnExpresstion(".",true)}

        //operators
        tvPlus.setOnClickListener{appendOnExpresstion("+",false)}
        tvMinus.setOnClickListener{appendOnExpresstion("-",false)}
        tvMul.setOnClickListener{appendOnExpresstion("*",false)}
        tvDivide.setOnClickListener{appendOnExpresstion("/",false)}
        tvOpen.setOnClickListener{appendOnExpresstion("(",false)}
        tvClose.setOnClickListener{appendOnExpresstion(")",false)}
        tvClear.setOnClickListener {
            tvExpression.text =" "
            tvResult.text = "" }
        tvBack.setOnClickListener {
            var string = tvExpression.text.toString()
            if (string.isNotEmpty()) {
                tvExpression.text =string.substring(0,string.length-1)
            }
            tvResult.text = ""
        }

        tvEquals.setOnClickListener {
            try{

                val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if(result == longResult.toDouble()){
                    tvResult.text =longResult.toString()
                }else{
                    tvResult.text =result.toString()
                }

            }catch (e:Exception){
                Log.d("Exception"," message : " + e.message )
            }
        }
    }

    fun appendOnExpresstion(string: String, canClear : Boolean){
        if(canClear){
            tvResult.text = ""
            tvExpression.append (string)
        }else{
            tvExpression.append(tvResult.text)
            tvExpression.append(string)
            tvResult.text =""
        }
    }
}