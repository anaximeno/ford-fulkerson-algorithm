package asa.fordfulkerson.examples;

public class Example {
    public final int[][] matrix;
    public final int source;
    public final int sink;

    public Example(int[][] matrix, int source, int sink) {
        this.matrix = matrix;
        this.source = source;
        this.sink = sink;
    }

    public static Example fromType(ExampleType type) {
        switch (type) {
            case OUT_OF_SCOPE:
                return new Example(ExampleSetup.OUT_OF_SCOPE_MATRIX, ExampleSetup.OUT_OF_SCOPE_SOURCE,
                        ExampleSetup.OUT_OF_SCOPE_SINK);
            case EXTREME:
                return new Example(ExampleSetup.EXTREME_MATRIX, ExampleSetup.EXTREME_SOURCE, ExampleSetup.EXTREME_SINK);
            case MID:
            default:
                return new Example(ExampleSetup.MID_MATRIX, ExampleSetup.MID_SOURCE, ExampleSetup.MID_SINK);

        }
    }
}
