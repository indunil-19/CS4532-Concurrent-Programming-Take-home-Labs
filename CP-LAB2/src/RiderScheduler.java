import java.util.Random;

public class RiderScheduler  implements Runnable
{

    private Resources resources;
    private double mean_rider=30000;

    private double wait_time_rider;
    public static double rand_rider;

    public RiderScheduler(Resources resources) {
        this.resources = resources;
        rand_rider = new Random().nextDouble();
        double lambda = 1 / mean_rider;
        wait_time_rider = Math.round(-Math.log(1 - rand_rider) / lambda);
    }
    @Override
    public void run()
    {
        while (true)
        {
            new Thread( new Rider( resources ) ).start();
            try
            {
                Thread.sleep( ( long ) wait_time_rider );
            }
            catch( InterruptedException e )
            {
                e.printStackTrace();
            }
        }

    }
}
