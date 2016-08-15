package work.bigapp.esau.big.Utility;

public final class InputValidator
{
    //region Singleton Implementation

    private static InputValidator _Instance;
    private InputValidator()
    {

    }
    public static synchronized InputValidator GetInstance()
    {
        if (_Instance == null)
        {
            _Instance = new InputValidator();
        }

        return _Instance;
    }

    //endregion Singleton Implementation\

    private final String AlphaRegex = "[a-zA-Z]+";
    private final String NumericRegex = "\\d+";
    private final String AlphaNumericRegex = "^[a-zA-Z0-9]*$";


    public boolean IsAlpha(String Value){

        return Value.matches(AlphaRegex);
    }

    public boolean IsNumeric(String Value)
    {
        return Value.matches(NumericRegex);
    }

    public boolean IsAlphaNumeric(String Value){

        return Value.matches(AlphaNumericRegex);
    }

    public boolean IsNullOrEmpty(String Value){

        return (Value == null || Value.isEmpty());
    }
}
