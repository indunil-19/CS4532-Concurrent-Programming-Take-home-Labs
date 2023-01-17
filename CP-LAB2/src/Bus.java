public class Bus implements Runnable
{
    private Resources resources;
    public int loadedRiders=0;

    public Bus(Resources resources) {
        this.resources = resources;
    }
    @Override
    public void run()
    {
        try
        {
            resources.mutex.acquire(); // avoid late arrivals
            System.out.println("Bus arrived .....Waiting count :"+ resources.waitingRiders);
            if (resources.waitingRiders > 0) {
                resources.bus=this; // add new arrived bus to resources
                resources.busWait.release();    //Signal riders that bus arrived
                resources.boarded.acquire();    //Wait till 50 or less are get on the bus
            }
            System.out.println("Bus departed..... loaded : " + loadedRiders + " riders left: " + resources.waitingRiders + " at bus stop.");
            resources.mutex.release(); // rider release mutex


        }
        catch( InterruptedException e )
        {
            e.printStackTrace();
        }

    }
}
