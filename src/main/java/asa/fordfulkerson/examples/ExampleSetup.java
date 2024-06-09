package asa.fordfulkerson.examples;

public class ExampleSetup {
    public static final int MID_SOURCE = 0;
    public static final int MID_SINK = 3;
    public static final int[][] MID_MATRIX = {
            { 0, 10, 5, 0 },
            { 0, 0, 5, 10 },
            { 0, 0, 0, 10 },
            { 0, 0, 0, 0 },
    };

    public static final int EXTREME_SOURCE = 0;
    public static final int EXTREME_SINK = 5;
    public static final int[][] EXTREME_MATRIX = {
            { 0, 16, 13, 0, 0, 0 },
            { 0, 0, 10, 12, 0, 0 },
            { 0, 4, 0, 0, 14, 0 },
            { 0, 0, 9, 0, 0, 20 },
            { 0, 0, 0, 7, 0, 4 },
            { 0, 0, 0, 0, 0, 0 },
    };

    public static final int OUT_OF_SCOPE_SOURCE = 0;
    public static final int OUT_OF_SCOPE_SINK = 3;
    public static final int[][] OUT_OF_SCOPE_MATRIX = {
            { 0, 10, 10, 0 },
            { 0, 0, 0, 0 },
            { 0, 0, 0, 0 },
            { 0, 0, 0, 0 },
    };;
}
