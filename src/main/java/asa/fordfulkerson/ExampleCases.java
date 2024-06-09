package asa.fordfulkerson;

public class ExampleCases {
    public static final int[][] MID = {
            { 0, 10, 5, 0 },
            { 0, 0, 5, 10 },
            { 0, 0, 0, 10 },
            { 0, 0, 0, 0 },
    };

    public static final int[][] EXTREME = {
            { 0, 16, 13, 0, 0, 0 },
            { 0, 0, 10, 12, 0, 0 },
            { 0, 4, 0, 0, 14, 0 },
            { 0, 0, 9, 0, 0, 20 },
            { 0, 0, 0, 7, 0, 4 },
            { 0, 0, 0, 0, 0, 0 },
    };

    public static final int[][] OUT_OF_SCOPE = {
            { 0, 10, 10, 0 },
            { 0, 0, 0, 0 },
            { 0, 0, 0, 0 },
            { 0, 0, 0, 0 },
    };
}
