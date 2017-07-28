
import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.ScriptStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class GenerateBillSheet
{

    ArrayList<BillSheet> alBillSheet;

    public GenerateBillSheet(ArrayList<BillSheet> alBillSheet)
    {
        this.alBillSheet = alBillSheet;
        generateBill();
    }

    void generateBill()
    {
        try
        {

            File f = new File(System.getProperty("user.home") + File.separator + "demo.xls");

            WritableWorkbook workbook = Workbook.createWorkbook(f);
            WritableSheet sheet = workbook.createSheet("Sheet 1", 0);

            WritableFont fontShopName = new WritableFont(WritableFont.createFont("Tahoma"));
            fontShopName.setBoldStyle(WritableFont.BOLD);
            fontShopName.setPointSize(18);
            fontShopName.setScriptStyle(ScriptStyle.NORMAL_SCRIPT);

            String shopName = "jShopZone Bill Sheet";

            SheetSettings ss = new SheetSettings(sheet);
            ss.setBottomMargin(0.25);
            ss.setTopMargin(0.25);
            ss.setLeftMargin(0.25);
            ss.setRightMargin(0.25);
            sheet.mergeCells(0, 0, 4, 0);
            sheet.setColumnView(0, 10);
            sheet.setColumnView(1, 15);
            sheet.setColumnView(2, 15);
            sheet.setColumnView(3, 13);
            sheet.setColumnView(4, 10);

            sheet.setRowView(0, 700);
            sheet.setRowView(1, 400);
            
            for (int i = 2; i < 50; i++)
            {
                sheet.setRowView(i, 350);
            }

            WritableCellFormat cfShopName = new WritableCellFormat();
            cfShopName.setFont(fontShopName);
            cfShopName.setAlignment(Alignment.CENTRE);
            cfShopName.setVerticalAlignment(VerticalAlignment.CENTRE);
            Label label = new Label(0, 0, shopName, cfShopName);
            sheet.addCell(label);

            WritableFont fontHeading = new WritableFont(WritableFont.createFont("Tahoma"));
            fontHeading.setBoldStyle(WritableFont.NO_BOLD);
            fontHeading.setPointSize(11);
            fontHeading.setScriptStyle(ScriptStyle.NORMAL_SCRIPT);

            WritableCellFormat cfHeading = new WritableCellFormat();
            cfHeading.setFont(fontHeading);
            cfHeading.setAlignment(Alignment.LEFT);
            cfHeading.setBackground(Colour.GREY_25_PERCENT);
            cfHeading.setVerticalAlignment(VerticalAlignment.CENTRE);

            sheet.addCell(new Label(0, 1, "Bill ID", cfHeading));
            sheet.addCell(new Label(1, 1, "Total Items", cfHeading));
            sheet.addCell(new Label(2, 1, "Grand Total", cfHeading));
            sheet.addCell(new Label(3, 1, "Date", cfHeading));
            sheet.addCell(new Label(4, 1, "Time", cfHeading));

            WritableFont fontData = new WritableFont(WritableFont.createFont("Tahoma"));
            fontData.setBoldStyle(WritableFont.NO_BOLD);
            fontData.setPointSize(10);
            fontData.setScriptStyle(ScriptStyle.NORMAL_SCRIPT);
            
            WritableCellFormat cfData = new WritableCellFormat();
            cfData.setFont(fontData);
            cfData.setAlignment(Alignment.RIGHT);
            cfData.setVerticalAlignment(VerticalAlignment.CENTRE);

            for (int i = 0; i < alBillSheet.size(); i++)
            {
                BillSheet billSheet = alBillSheet.get(i);
                sheet.addCell(new Label(0, i + 2, billSheet.billId + "", cfData));
                sheet.addCell(new Label(1, i + 2, billSheet.totalItems + "", cfData));
                sheet.addCell(new Label(2, i + 2, billSheet.grandTotal + "", cfData));
                sheet.addCell(new Label(3, i + 2, new SimpleDateFormat("dd MMM, yyyy").format(billSheet.date), cfData));
                sheet.addCell(new Label(4, i + 2, billSheet.time, cfData));
            }
                
            workbook.write();
            workbook.close();
            Desktop.getDesktop().open(f);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}