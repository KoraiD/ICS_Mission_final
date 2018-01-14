package com.example.korai.icstest;

/**
 * Created by korai on 17/11/25.
 */

public enum TimeBoundary {
    CLOSED(-1, -1) {
        @Override
        int getPicture() {
            return R.drawable.t_icon_x;
        }
    },
    LOW(0,10) {
        @Override
        int getPicture() {
            return R.drawable.t_icon_g;
        }
    },
    MEDIUM(10,25) {
        @Override
        int getPicture() {
            return R.drawable.t_icon_y;
        }
    },
    HIGH(25, Integer.MAX_VALUE) {
        @Override
        int getPicture() {
            return R.drawable.t_icon_r;
        }
    };

    int from;
    int to;


    TimeBoundary(int from, int to){
        this.from = from;
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    abstract int getPicture();
}
