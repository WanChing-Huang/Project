import java.io.IOException;

public class Admin{
    private String password;
    private BookkeepingMonthlyReport bookkeepingMonthlyReport;
    
    

    public Admin(String password, BookkeepingMonthlyReport bookkeepingMonthlyReport) {
        this.password = password;
        this.bookkeepingMonthlyReport = bookkeepingMonthlyReport;
    }

    public void setBudget(double budget){
      bookkeepingMonthlyReport.setBudget(budget);
    }

    public void setNoticeOfOverBudget(String warning){
        bookkeepingMonthlyReport.setOverBudgetWaring(warning);

    }

    public void createExcel() throws IOException{
        bookkeepingMonthlyReport.createExcel();
    }

    public void setCategory(String category) throws IOException{
      bookkeepingMonthlyReport.addCategory(category);
    }

    public void releaseReport() throws IOException{
        bookkeepingMonthlyReport.openExcel();
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public BookkeepingMonthlyReport getBookkeepingMonthlyReport() {
        return bookkeepingMonthlyReport;
    }
    public void setBookkeepingMonthlyReport(BookkeepingMonthlyReport bookkeepingMonthlyReport) {
        this.bookkeepingMonthlyReport = bookkeepingMonthlyReport;
    }








}