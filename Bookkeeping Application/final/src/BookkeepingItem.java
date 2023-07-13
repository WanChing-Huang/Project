import java.util.Calendar;

public class BookkeepingItem {

    private BookkeepingCategory bookkeepingCategory;
    private Calendar date;
    private String itemName;
    private double expense;
    private String note;
    private String picturePath;
    private String category;
 

    //make sure is bookkepping list or one of it
    public BookkeepingItem(BookkeepingCategory bookkeepingCategory,int index, String itemName, double expense,
            String note, String picturePath) {
        category = bookkeepingCategory.geteGategoryByIndex(index);
        this.date = Calendar.getInstance();
        this.itemName = itemName;
        this.expense = expense;
        this.note = note;
        this.picturePath = picturePath;
    }

    public String getCategory() {
        return category;
    }


    public Calendar getDate() {
        return date;
    }

   
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }


    
}
