package com.example.demo.one.base2;

/***************************************
 * @author:Alex Wang
 * @Date:2017/2/15 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class TaxCalculatorMain {

    public static void main(String[] args) {

 //     TaxCalaculator calculator = new TaxCalaculator(10000d, 2000d,new SimpleCalculatorStrategy());

        TaxCalaculator calculator = new TaxCalaculator(10000d, 2000d,(double s,double b)->s * 0.2 + b * 0.15);

        System.out.println(calculator.calculate());
    }
}
