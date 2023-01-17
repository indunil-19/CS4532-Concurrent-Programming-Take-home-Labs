import java.util.Random;

public class BusScheduler implements Runnable
{
    private Resources resources;
    private double mean_bus=1200000;

    private double wait_time_bus;
    public static double rand_bus;

    public BusScheduler(Resources resources) {
        this.resources = resources;
        rand_bus = new Random().nextDouble();
        double lambda = 1 / mean_bus;
        wait_time_bus = Math.round(-Math.log(1 - rand_bus) / lambda); // time to arrive next bus
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                Thread.sleep( ( long ) wait_time_bus );
            }
            catch( InterruptedException e )
            {
                e.printStackTrace();
            }
            new Thread( new Bus( resources ) ).start();
        }

    }
}
