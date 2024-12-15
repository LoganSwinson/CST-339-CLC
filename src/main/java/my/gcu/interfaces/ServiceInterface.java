package my.gcu.interfaces;

/**
 * Interface for service classes with initialization and destruction methods.
 */
public interface ServiceInterface
{
    /**
     * Initializes the service. Called to set up any necessary resources or configurations.
     */
    public void init();

    /**
     * Destroys the service. Called to clean up resources or perform any necessary shutdown tasks.
     */
    public void destroy();
}
