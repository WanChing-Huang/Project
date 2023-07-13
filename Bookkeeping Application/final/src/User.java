import java.io.IOException;

public class User {
    private String password;
    private BookkeepingMonthlyReport bookkeepingMonthlyReport;
    
    
    public User(String password, BookkeepingMonthlyReport bookkeepingMonthlyReport) {
        this.password = password;
        this.bookkeepingMonthlyReport = bookkeepingMonthlyReport;
    }




    public void setBookkeepingRecord(BookkeepingItem bItem) throws IOException{
       
       bookkeepingMonthlyReport.addBookkeepingRecord(bItem);
    }




    public void releaseBookkeepingReport() throws IOException{
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
