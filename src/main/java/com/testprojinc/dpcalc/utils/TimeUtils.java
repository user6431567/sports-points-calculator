package com.testprojinc.dpcalc.utils;

import java.util.Objects;

public class TimeUtils {

    /**
     * Converts time string to seconds with milliseconds.
     *
     * @param time time in format "ss.SSS" or "mm:ss.SSS"
     * @return seconds.milliseconds
     */
    public static Double timeStringToDouble(String time) {
        Objects.requireNonNull(time, "Time string not present.");

        if (time.contains(":")) {
            final String[] split = time.split(":");
            if (split.length != 2) {
                throw new IllegalArgumentException("Unable to parse time: " + time);
            }
            return Double.parseDouble(split[0]) * 60 + Double.parseDouble(split[1]);
        } else {
            return Double.parseDouble(time);
        }
    }

}
