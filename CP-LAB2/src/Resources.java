import java.util.concurrent.Semaphore;

public class Resources
{
    public int waitingRiders;
    public Semaphore busWait;
    public Semaphore boarded;
    public Semaphore mutex;
    public Bus bus;

    public Resources()
    {
        this.waitingRiders = 0; // waiting riders count at bus stop
        this.busWait = new Semaphore( 0 );        //Signals when the bus has arrived
        this.boarded = new Semaphore( 0 );        //Signal when bus boarded
        this.mutex = new Semaphore( 1 );          //avoid new riders when bus is at stop
    }

}
