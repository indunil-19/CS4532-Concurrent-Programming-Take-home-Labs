public class Rider implements Runnable
{
    private Resources resources;

    public Rider(Resources resources) {
        this.resources = resources;
    }
    @Override
    public void run()
    {
        try
        {
            resources.mutex.acquire(); // avoid new rider
                resources.waitingRiders += 1; // increment waiting rider count at bus stop
                System.out.println("Rider is waiting at bus stop.");
            resources.mutex.release(); // release rider mutex
            resources.busWait.acquire(); // wait for get on bus
            System.out.println("Rider got on the bus.");
            resources.bus.loadedRiders+=1; // loaded a rider to bus
            if(resources.bus.loadedRiders==50 || resources.bus.loadedRiders==resources.waitingRiders ){
                resources.waitingRiders =Math.max(resources.waitingRiders - 50, 0); // set remaining riders at bus stop before departure
                resources.boarded.release(); // signal to depart bus
            }else {
                resources.busWait.release(); // signal rider to get on
            }
        }
        catch( InterruptedException e )
        {
            e.printStackTrace();
        }

    }
}
