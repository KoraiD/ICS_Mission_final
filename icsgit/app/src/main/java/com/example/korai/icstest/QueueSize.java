package com.example.korai.icstest;

/**
 * Created by korai on 2018. 01. 13..
 */

public class QueueSize {
    public int rowSize = 0;

    public QueueSize(double timeValue, double[] a)
    {
        double searchTimeTransformed = timeTransform(timeValue);
        double rowSizeDouble = 0.0;

        for (int i = 0; i <= 14; i++)
        {
            rowSizeDouble = rowSizeDouble + a[i]* Math.pow( searchTimeTransformed,i);
        }
        rowSize = (int)Math.round(rowSizeDouble);
    }

    private int calculateHour(double inputNumber)
    {
        if (inputNumber * 24 < 9)
        {
            return 1;
        }
        else if (inputNumber * 24 >= 21)
        {
            return 14;
        }

        for (int i = 2; i <= 13; i++)
        {
            if (inputNumber * 24 >= (i + 7) && inputNumber * 24 < (i + 8))
            {
                return i;
            }
        }
        return 1;
    }

    public double timeTransform(double timeValue)
    {
        return calculateHour(timeValue) + (timeValue * 24 - Math.round(timeValue * 24));
    }
}
