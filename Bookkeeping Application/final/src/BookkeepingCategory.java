import java.util.*;

public class BookkeepingCategory {
    private ArrayList<String> categoryList;
    
  

    public BookkeepingCategory() {
        categoryList = new ArrayList<String>(5);
    }
    
    public void addCategoryListByIndex(String category){
        categoryList.add(category);
    }

    public String geteGategoryByIndex (int index){
        return categoryList.get(index);
    }
    
    public void removeCategory (String category){
        categoryList.remove(category);
    }

    public ArrayList<String> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ArrayList<String> categoryList) {
        this.categoryList = categoryList;
    }
    
    public void printCategoryList(){
     for (int i = 0 ; i < categoryList.size(); i++ ){
        System.out.print(categoryList.get(i));
     }
    }
  
}
