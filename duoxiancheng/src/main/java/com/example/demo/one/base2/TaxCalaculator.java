package com.example.demo.one.base2;

/**
 * Created by King on 2017/9/5.
 */
public class TaxCalaculator {
    private final double salary;
    private final double bonus;
    private final CalculatorStrategy calculatorStrategy;
    public TaxCalaculator(double salary, double bonus,CalculatorStrategy calculatorStrategy) {
        this.salary = salary;
        this.bonus = bonus;
        this.calculatorStrategy = calculatorStrategy;
    }

    //执行实现接口得方法
    protected double calcTax(){
        return calculatorStrategy.calculate(salary,bonus);
    }

    public double calculate(){
        System.out.println("ssssssss");
        return this.calcTax();
    }



}
