package com.example.homeworkandroid.homework001;

import java.util.Random;

public class Triangle {
    public Double getSideA() {
        return sideA;
    }

    public void setSideA(Double sideA) {
        if(sideA>0.0)
        this.sideA = sideA;
    }

    public Double getSideB() {
        return sideB;
    }

    public void setSideB(Double sideB) {
        if(sideB>0.0)
        this.sideB = sideB;
    }

    public Double getSideC() {
        return sideC;
    }

    public void setSideC(Double sideC) {
        if(sideC>0.0)
        this.sideC = sideC;
    }

    private Double sideA = 0.;
    private Double sideB = 0.;
    private Double sideC = 0.;
    public Double CalculateArea()
    {
        Double Area = -1.;

        if(sideA>0. && sideB>0. && sideC>0.)
        {
            //use formula Heron`s
            Double S = (sideA+sideB+sideC)/2.0;
            Area = Math.sqrt(S*(S-sideA)*(S-sideB)*(S-sideC));
        }
            return Area;
    }

    public Double CalculatePerimeter()
    {
        Double Perimeter = -1.;

        if(sideA>0. && sideB>0. && sideC>0.)
            Perimeter=sideA+sideB+sideC;
        return Perimeter;
    }

    public Triangle(){}

    public Triangle(Double _sideA, Double _sideB , Double _sideC)
    {
        this.setSideA(_sideA);
        this.setSideB(_sideB);
        this.setSideC(_sideC);
    }

    private Double GetDouble(Double rangeMin , Double rangeMax) {
        return rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
    }

    Random rand = new Random();

    public void Generate(Double min , Double max) {
        if (min >= 1. && min < max) {
            this.setSideA(GetDouble(min, max));
            this.setSideB(GetDouble(min, max));
            this.setSideC(GetDouble(min, max));
        }
    }
}
