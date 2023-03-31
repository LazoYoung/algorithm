package common;

public class Result<T> {
    private final long elapsedTime;
    private final T output;

    public Result(long elapsedTime, T output) {
        this.elapsedTime = elapsedTime;
        this.output = output;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public T getOutput() {
        return output;
    }
}
