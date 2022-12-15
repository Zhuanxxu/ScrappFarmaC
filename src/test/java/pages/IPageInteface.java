package pages;

import java.util.List;

public interface IPageInteface {
    void addItemsToArray(List<List<String>> articulos) throws InterruptedException;
    String getTextFromDescription(int i);
    String getTextFromBestPrice( int i);
    String getTextFromOldPrice( int i);
    String getTextFromImg( int i);
    String getTextFromPromo(int i);
    boolean verifyIfThereIsAnyPromo(int i) throws InterruptedException;
}
