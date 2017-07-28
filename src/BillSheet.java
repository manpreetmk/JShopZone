
import java.sql.Date;

class BillSheet
{

    int billId, totalItems;
    double grandTotal;
    Date date;
    String time;

    public BillSheet(int billId, int totalItems, double grandTotal, Date date, String time)
    {
        this.billId = billId;
        this.totalItems = totalItems;
        this.grandTotal = grandTotal;
        this.date = date;
        this.time = time;
    }

}
