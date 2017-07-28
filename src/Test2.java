
import java.io.File;
//import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Date;

class Test2
{

    public static void main(String[] args)
    {
        System.out.println(new SimpleDateFormat("dd MMM yyy HH:mm:ss a").format(new Date(System.currentTimeMillis())));
        System.out.println(new Date(System.currentTimeMillis()));
    }
}
