import java.io.*;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFSimpleShape;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.nio.DataSource;
import org.apache.poi.ss.formula.functions.DateFunc;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Chart;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Shape;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.ClientAnchor.AnchorType;
import org.apache.poi.ss.usermodel.charts.AxisCrosses;
import org.apache.poi.ss.usermodel.charts.AxisPosition;
import org.apache.poi.ss.usermodel.charts.ChartAxis;
import org.apache.poi.ss.usermodel.charts.ChartDataFactory;
import org.apache.poi.ss.usermodel.charts.ChartDataSource;
import org.apache.poi.ss.usermodel.charts.ChartLegend;
import org.apache.poi.ss.usermodel.charts.DataSources;
import org.apache.poi.ss.usermodel.charts.LegendPosition;
import org.apache.poi.ss.usermodel.charts.LineChartData;
import org.apache.poi.ss.usermodel.charts.ValueAxis;
import org.apache.poi.util.IOUtils;
import java.awt.Desktop;

import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.text.html.HTMLDocument.Iterator;

public class BookkeepingMonthlyReport {
    private ArrayList<BookkeepingItem> bookkeepingItemList;
    private BookkeepingCategory bookkeepingCategory;
   
    private Workbook bookkeepingReport;
    private String overBudgetWaring;
    private double budget;                           
    private ArrayList<String> month;

    public BookkeepingMonthlyReport() {
        bookkeepingItemList = new ArrayList<BookkeepingItem>();
        bookkeepingReport = new HSSFWorkbook();
        bookkeepingCategory = new BookkeepingCategory();
        budget = 0;
        String[] values = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December" };
        month = new ArrayList<String>();
        for (String element : values) {
            month.add(element);
        }
    }

    // create category list before run this function
    public void createExcel() throws IOException {
        CreationHelper createHelper = bookkeepingReport.getCreationHelper();
        CellStyle cellStyle = bookkeepingReport.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.LAVENDER.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THICK);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        // create monthly sheet
        for (String monthString : month) {
            Sheet sheet = bookkeepingReport.createSheet(monthString);
            Row row = sheet.createRow(0);


            Cell cell = row.createCell(0);
            cell.setCellValue("CateGory");
            cell.setCellStyle(cellStyle);

            cell = row.createCell(1);
            cell.setCellValue("Date");
            cell.setCellStyle(cellStyle);

            cell = row.createCell(2);
            cell.setCellValue("Item Name");
            cell.setCellStyle(cellStyle);

            cell = row.createCell(3);
            cell.setCellValue("Expense");
            cell.setCellStyle(cellStyle);

            cell = row.createCell(4);
            cell.setCellValue("Note");
            cell.setCellStyle(cellStyle);

            cell = row.createCell(5);
            cell.setCellValue("Receipt Picture");
            cell.setCellStyle(cellStyle);

            for (int i = 0; i < 5; i++) {
                sheet.autoSizeColumn(i);
            }

        }

        // create category sheet
        Sheet categorySheet = bookkeepingReport.createSheet("Category");
        Row categoryRow = categorySheet.createRow(0);
        Cell cell = categoryRow.createCell(0);
        cell.setCellValue("Category");
        cell.setCellStyle(cellStyle);
        ArrayList<String> a = bookkeepingCategory.getCategoryList();
        for (int i = 0; i < a.size(); i++) {
            Cell cell1 = categoryRow.createCell(i + 1);
            cell1.setCellValue(a.get(i));
            categorySheet.autoSizeColumn(i);
        }

        try (OutputStream fileOut = new FileOutputStream("test.xls");) {

            System.out.println("Excel File has been created successfully.");
            bookkeepingReport.write(fileOut);
        } catch (FileNotFoundException e) {
            System.out.println("file error occurred.");
            e.printStackTrace();
        }
    }

    public void addBookkeepingRecord(BookkeepingItem bookkeepingItem) throws IOException {

        // add to array
        bookkeepingItemList.add(bookkeepingItem);
        try {

            //cerat input stream
            FileInputStream input = new FileInputStream(new File("test.xls"));
            HSSFWorkbook report = new HSSFWorkbook(input);
            Calendar date = Calendar.getInstance();
            int currentMonth = date.get(Calendar.MONTH);
            // System.out.println(date.get(Calendar.MONTH));
            

            //get sheet base on current month
            Sheet sheet = report.getSheet(this.month.get(currentMonth));
            int columnCount = 0;
            int rowCount = sheet.getLastRowNum();
            System.out.println(rowCount + 1);

            CellStyle cellStyle = report.createCellStyle();
            CreationHelper createHelper = report.getCreationHelper();

            Row row = sheet.createRow(rowCount + 1);
            
            //add category form category list
            Cell cell = row.createCell(columnCount);
            cell.setCellValue(bookkeepingItem.getCategory());
            
            //get current date
            cell = row.createCell(columnCount + 1);
            cell.setCellValue(bookkeepingItem.getDate());
            cellStyle.setDataFormat(
            createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
            cell.setCellStyle(cellStyle);
           
            cell = row.createCell(columnCount + 2);
            cell.setCellValue(bookkeepingItem.getItemName());

            cell = row.createCell(columnCount + 3);
            cell.setCellValue(bookkeepingItem.getExpense());

            cell = row.createCell(columnCount + 4);
            cell.setCellValue(bookkeepingItem.getNote());
             
            //add picture to bookkeeping report
            InputStream is = new FileInputStream(bookkeepingItem.getPicturePath());
            byte[] bytes = IOUtils.toByteArray(is);
            int pictureIdx = report.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
            CreationHelper helper = report.getCreationHelper();
            Drawing drawing = sheet.createDrawingPatriarch();
            ClientAnchor anchor = helper.createClientAnchor();
            anchor.setCol1(columnCount + 5);
            anchor.setCol2(columnCount + 6);
            anchor.setRow1(rowCount + 1);
            anchor.setRow2(rowCount + 2);
            drawing.createPicture(anchor, pictureIdx);
            is.close();
            
           
            //setup total sum table

            CellStyle cellStyleSum = report.createCellStyle();
            cellStyleSum.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
    
            cellStyleSum.setBorderBottom(BorderStyle.THICK);
            cellStyleSum.setBottomBorderColor(IndexedColors.DARK_RED.getIndex());

            Row sumTagRow = sheet.getRow(0);
            Cell sumTagCell = sumTagRow.createCell(columnCount + 7);
            sumTagCell.setCellValue("Total");
            sumTagCell.setCellStyle(cellStyleSum);

            Row sumRow = sheet.getRow(1);
            Cell sumCell = sumRow.createCell(columnCount + 7);
            sumCell.setCellFormula("SUM(D:D)");

            // create total by Category table
            ArrayList<String> a = bookkeepingCategory.getCategoryList();
            Cell totalCell = sumTagRow.createCell(columnCount + 8);
            totalCell.setCellValue("Total by Category");
            totalCell.setCellStyle(cellStyleSum);
            for (int i = 0; i < a.size(); i++) {
                Cell cell1 = sumTagRow.createCell(i + 9);
                cell1.setCellValue(a.get(i));
                cell1.setCellStyle(cellStyleSum);
                sheet.autoSizeColumn(i);

                Cell sumCell1 = sumRow.createCell(i + 9);

                StringBuilder stringBuilder = new StringBuilder("SUMIF(A2:A19,\"\",D2:D19)");

                int index = stringBuilder.indexOf("\"");

                stringBuilder.insert(index + 1, a.get(i));
                String formula = stringBuilder.toString();
                sumCell1.setCellFormula(formula);
                sumCell1.setCellStyle(cellStyle);

            }

            for (int i = 0; i < 30; i++) {
                sheet.autoSizeColumn(i);
            }

            input.close();
            FileOutputStream outputStream = new FileOutputStream("test.xls");
            report.write(outputStream);
            report.close();
            outputStream.close();

            System.out.println("Update item successful");

        } catch (FileNotFoundException e) {
            System.out.println("file error occurred.");
            e.printStackTrace();
        }

    }


    public Boolean OverBudgetWaring() throws FileNotFoundException, IOException {

        //create input steam and get current month sheet
        FileInputStream input = new FileInputStream(new File("test.xls"));
        HSSFWorkbook wb = new HSSFWorkbook(input);
        Calendar date = Calendar.getInstance();
        int currentMonth = date.get(Calendar.MONTH);

        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
       

        Sheet bdSheet = wb.getSheet(this.month.get(currentMonth));
        Row bdRow = bdSheet.getRow(1);
        
        if (bdRow==null){
            input.close();
            return false;
        }
        //look through table to check if it is out of project
        Cell cell = bdRow.getCell(7);
        
        double sum = evaluator.evaluate(cell).getNumberValue();
        if (sum > budget) {
            
            return true;
        }
        input.close();
        return false;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void setOverBudgetWaring(String overBudgetWaring) {
        this.overBudgetWaring = overBudgetWaring;
    }

    public String warnOfOverBudget() {
        return overBudgetWaring;
    }

    public void addCategory(String category) throws IOException {
        bookkeepingCategory.addCategoryListByIndex(category); 
    }

    public ArrayList<BookkeepingItem> getBookkeepingItemList() {
        return bookkeepingItemList;
    }

    public BookkeepingCategory getBookkeepingCategory() {
        return bookkeepingCategory;
    }

    public void openExcel() throws IOException {
        try {
            File file = new File("test.xls");
            if (!Desktop.isDesktopSupported()) {
                System.out.println("not supported");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if (file.exists())
                desktop.open(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
