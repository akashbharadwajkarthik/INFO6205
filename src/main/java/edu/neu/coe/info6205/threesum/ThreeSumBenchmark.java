package edu.neu.coe.info6205.threesum;

import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Stopwatch;
import edu.neu.coe.info6205.util.TimeLogger;
import edu.neu.coe.info6205.util.Utilities;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class ThreeSumBenchmark {
    public ThreeSumBenchmark(int runs, int n, int m) {
        this.runs = runs;
        this.supplier = new Source(n, m).intsSupplier(10);
        this.n = n;
    }

    public void runBenchmarks() {
        System.out.println("ThreeSumBenchmark: N=" + n);
        benchmarkThreeSum("ThreeSumQuadratic", (xs) -> new ThreeSumQuadratic(xs).getTriples(), n, timeLoggersQuadratic);
        benchmarkThreeSum("ThreeSumQuadrithmic", (xs) -> new ThreeSumQuadrithmic(xs).getTriples(), n, timeLoggersQuadrithmic);
        benchmarkThreeSum("ThreeSumCubic", (xs) -> new ThreeSumCubic(xs).getTriples(), n, timeLoggersCubic);
    }

    public static void main(String[] args) {
        new ThreeSumBenchmark(1000, 250, 250).runBenchmarks();
        new ThreeSumBenchmark(500, 500, 500).runBenchmarks();
        new ThreeSumBenchmark(200, 1000, 1000).runBenchmarks();
        new ThreeSumBenchmark(100, 2000, 2000).runBenchmarks();
        new ThreeSumBenchmark(10, 4000, 4000).runBenchmarks();
        new ThreeSumBenchmark(100, 8000, 8000).runBenchmarks();
        new ThreeSumBenchmark(100, 16000, 16000).runBenchmarks();
    }

    private void benchmarkThreeSum(final String description, final Consumer<int[]> function, int n, final TimeLogger[] timeLoggers) {
        if (description.equals("ThreeSumCubic") && n > 4000) return;
        // FIXME

        double averageTime = 0; // initialize average time variable to record final average time for 3-sum operation
        Stopwatch stopwatch = new Stopwatch(); // instantiate stopwatch object to utilize lap() method to calculate time taken by operation

        for(int i = 1; i <= runs; i++){ // average over 'runs' number of trials
            stopwatch.lap(); // set "start" time private variable inside stopwatch before 3-sum operation
            function.accept(supplier.get()); // perform 3-sum
            averageTime = averageTime + (stopwatch.lap() - averageTime)/i; // recursive average time sum to avoid potential overflows
        }

        timeLoggers[0].log(averageTime,n); // log average raw time over 'runs' number of trials in mSec
        timeLoggers[1].log(averageTime,n); // log average normailize time over 'runs' number of trials in nSec
        System.out.println(); // Leave a new line for readability
        // END 
    }

    private final static TimeLogger[] timeLoggersCubic = {
            new TimeLogger("Raw time per run (n^3) (mSec): ", (time, n) -> time),
            new TimeLogger("Normalized time per run (n^3) (nSec): ", (time, n) -> time / n / n / n * 1e6)
    };
    private final static TimeLogger[] timeLoggersQuadrithmic = {
            new TimeLogger("Raw time per run (n^2 log n) (mSec): ", (time, n) -> time),
            new TimeLogger("Normalized time per run (n^2 log n) (nSec): ", (time, n) -> time / n / n / Utilities.lg(n) * 1e6)
    };
    private final static TimeLogger[] timeLoggersQuadratic = {
            new TimeLogger("Raw time per run (n^2) (mSec): ", (time, n) -> time),
            new TimeLogger("Normalized time per run (n^2) (nSec): ", (time, n) -> time / n / n * 1e6)
    };

    private final int runs;
    private final Supplier<int[]> supplier;
    private final int n;
}
