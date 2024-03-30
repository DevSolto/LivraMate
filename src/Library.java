import java.util.ArrayList;

public class Library {
    private final ArrayList<Book> books = new ArrayList<Book>();

    public boolean addBook(Book bookToBeAdded){
        for(Book book: books){
            if (book.getTitle().equals(bookToBeAdded.getTitle())){
                return false;
            }
        }
        books.add(bookToBeAdded);
        return true;
    }
    public boolean removeBook(String title){
        int index = 0;
        for (Book book: books){
            if(book.getTitle().equals(title)){
                books.remove(index);
                return true;
            }
            index ++;
        }
        return false;
    }

    public ArrayList<Book> searchByTitle(String title){
        ArrayList<Book> bookList = new ArrayList<Book>();
        for(Book book: books){
            if(book.getTitle().contains(title)) {
                bookList.add(book);
            }
        }
        return bookList;
    }
    public ArrayList<Book> searchByAuthor(String author){
        ArrayList<Book> bookList = new ArrayList<Book>();
        for(Book book: books){
            if(book.getAuthor().contains(author)) {
                bookList.add(book);
            }
        }
        return bookList;
    }
    public boolean borrowBook(String title){
        for(Book book: books){
            if(book.getTitle().equals(title)){
                return book.borrowBook();
            }
        }
        return false;
    }

    public boolean returnBook(String title){
        for(Book book: books){
            if(book.getTitle().equals(title)){
                book.returnBook();
                return true;
            }
        }
        return false;
    }

    public ArrayList<Book> listAllBooks(){
        return books;
    }

    public ArrayList<Book> listAvailableBooks(){
        ArrayList<Book> availableBooks = new ArrayList<>();
        for (Book book: books){
            if (book.getCopies()>0) availableBooks.add(book);
        }
        return availableBooks;
    }



}
