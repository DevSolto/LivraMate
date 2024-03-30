import java.util.Date;

public class Book {
    private String title;
    private String author;
    private int publicationYear;
    private int copies;

    public Book(String title, String author, int publicationYear, int copies){
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.copies = copies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    @Override
    public String toString() {
        return "title= " + title + "\n" +
                "author= " + author + "\n" +
                "publicationYear= " + publicationYear + "\n" +
                "copies= " + copies + "\n" + "\n";
    }

    public boolean borrowBook(){
        if(copies>0){
            copies --;
            return true;
        }
        return false;
    }
    public void returnBook(){
        copies++;
    }
}
