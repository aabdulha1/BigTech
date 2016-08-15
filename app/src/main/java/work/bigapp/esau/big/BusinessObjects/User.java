package work.bigapp.esau.big.BusinessObjects;

import android.media.Image;
import java.util.ArrayList;

public final class User implements IDataObject
{
    //region Singleton Implementation

    private static volatile User _Instance;
    private User()
    {

    }
    public static synchronized User GetInstance()
    {
        if (_Instance == null)
        {
            _Instance = new User();
        }

        return _Instance;
    }

    //endregion Singleton Implementation

    //region Members

    // private Object Key

    public String UserName;
    public String Password;

    public Boolean NewUser = true;

    public String FirstName;
    public String LastName;
    public String CompanyName;
    public String ZipCode;
    public String Phone;
    public String Email;
    public ArrayList<String> List_WorkSpecialties = new ArrayList<String>();
    public Image ProfilePhoto;
    public String LicenseState;
    public String LicenseNumber;
    public Boolean HaveInsurance;
    public ArrayList<String> List_Website = new ArrayList<String>();

    //endregion Members

    //region Methods

    public static synchronized void Clear()
    {
        _Instance = new User();
    }

    //#endregion Methods

    //region IDataObject Implementation

    @Override
    public void Create()
    {

    }

    @Override
    public void Read()
    {

    }

    @Override
    public void Update()
    {

    }

    @Override
    public void Delete()
    {

    }

    //endregion IDataObject Implementation
}
