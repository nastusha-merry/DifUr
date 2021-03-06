package ru.bmstu.nastasia.difur.examples;

import org.mariuszgromada.math.mxparser.Function;

import java.util.ArrayList;

public class EquationSystem {
    public String[] func_raws;
    public double[] inits;
    public double x1;
    public double x2;

    public final static EquationSystem TEST1 = test1();

    public final static EquationSystem TEST2 = test2();

    public static EquationSystem test1() {
        EquationSystem eq = new EquationSystem();
        eq.func_raws = new String[] {
                "y2",
                "6*y1-y2"
        };
        eq.inits = new double[] { 3., 1.};
        eq.x1 = 0.;
        eq.x2 = 1.;
        return eq;
    }

    public static EquationSystem test2() {
        EquationSystem eq = new EquationSystem();
        eq.func_raws = new String[] {
                "y2",
                "cos(3*x)-4*y1"
        };
        eq.inits = new double[] { 0.8, 2};
        eq.x1 = 0.;
        eq.x2 = 1.;
        return eq;
    }


    public double[] getInits() {
        return inits;
    }

    public String[] getFuncRaws() {
        return func_raws;
    }

}
