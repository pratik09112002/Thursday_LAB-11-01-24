package Thursday_LAB_11_01_24;

public class Producer implements Runnable
{
	private final ShirtFactory shirtFactory;

    public Producer(ShirtFactory shirtFactory) 
    {
        this.shirtFactory = shirtFactory;
    }

    @Override
    public void run() 
    {
        shirtFactory.produceShirts();
    }

}
