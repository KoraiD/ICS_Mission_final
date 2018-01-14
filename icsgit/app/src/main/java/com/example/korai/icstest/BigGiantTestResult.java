package com.example.korai.icstest;

public class BigGiantTestResult {
    public double min = 0;
    public double max = 0;
    public double atlag = 0;
    public String color = "red";
    public final double perc = 1.0 / 1440.0;


    public BigGiantTestResult(double bparam1, double a, double b)
    {
        if (test(bparam1, a) == 0)
        {
            min = 0;
        }
        else
        {
            for (int i = 1; i <= 720; i++)
            {
                if (test(bparam1, perc * i) >= a && test(bparam1, perc * (i - 1)) < a)
                {
                    min = i;
                }
            }
        }
        if (test(bparam1, 720) == 0)
        {
            max = 0;
        }
        else
        {
            for (int i = 1; i <= 720; i++)
            {
                if (test(bparam1, perc * i) >= b && test(bparam1, perc * (i - 1)) < b)
                {
                    max = i;
                }
            }
        }

        atlag = (min + max) / 2;

        if (atlag < 15)
        {
            color = "green";
        }
        else if (atlag < 40)
        {
            color = "yellow";
        }
        else
        {
            color = "red";
        }

    }

    private double test(double param1, double param2)
    {
        return 1 - Math.pow(Math.exp(-param1), param2);
    }
}

