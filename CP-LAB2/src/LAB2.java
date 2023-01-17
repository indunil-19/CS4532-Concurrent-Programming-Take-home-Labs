import java.util.concurrent.Semaphore;

public class LAB2
{

    public static void main(String[] args)  {
        Resources resources = new Resources();

        Thread busScheduler = new Thread(new BusScheduler(resources));
        Thread riderScheduler = new Thread(new RiderScheduler(resources));

        busScheduler.start();
        riderScheduler.start();

    }




}


