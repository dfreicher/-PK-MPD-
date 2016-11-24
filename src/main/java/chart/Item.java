package chart;

public class Item {
    
    private int start;
    private int end;
    private String title;
    
    public Item(String title, int start, int end) {
        this.title = title;
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public String getTitle() {
        return title;
    }

}
