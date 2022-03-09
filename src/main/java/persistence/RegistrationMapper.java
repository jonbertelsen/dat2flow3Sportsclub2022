package persistence;

public class RegistrationMapper
{
    private Database database;

    public RegistrationMapper(Database database)  // Dependency Injection  (SOLID)
    {
        this.database = database;
    }

    public void getRegistrationCount()
    {
        String sql = "";
    }


}
