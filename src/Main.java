import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.io.*;

public class Main {

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_RED_BACKGROUND
            = "\u001B[41m";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int option = 0;
        Library library = new Library();
        System.out.println("Bem-vindo ao Sistema de Gerenciamento de Biblioteca!\n");

        while (option<7){
            System.out.print(
                    "Por favor, escolha uma das seguintes opções digitando o número correspondente e pressionando Enter:\n" +
                    "\n" +
                    "1. Adicionar um novo livro ao catálogo\n" +
                    "2. Buscar um livro por título\n" +
                    "3. Buscar um livro por autor\n" +
                    "4. Emprestar um livro\n" +
                    "5. Devolver um livro\n" +
                    "6. Listar todos os livros disponíveis\n" +
                    "7. Sair\n" +
                    "\n" +
                    "Digite sua escolha aqui: "
            );
            option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1:
                    System.out.println(
                            "Digite as informações do livro separados por virgula. \n" +
                                    "Título,autor,ano de publicação, copias disponivies"
                    );
                    String bookInfo = input.nextLine();
                    String[] separateBookInfo = bookInfo.split(";");
                    if (separateBookInfo.length != 4) {
                        System.out.println(
                                ANSI_RED_BACKGROUND
                                        + "\nErro na quantidade de informações passadas!\n"
                                        + ANSI_RESET);
                        continue;
                    } else {
                        if (separateBookInfo[2].matches("[0-9]*") && separateBookInfo[3].matches("[0-9]*")) {
                            String title = separateBookInfo[0];
                            String autor = separateBookInfo[1];
                            int publicationYear = Integer.parseInt(separateBookInfo[2]);
                            int copies = Integer.parseInt(separateBookInfo[3]);
                            Book bookAdded = new Book(title, autor, publicationYear, copies);
                            if (library.addBook(bookAdded)) {
                                System.out.println("\nLivro " + bookAdded.getTitle() + " Adicionado com sucesso!\n");
                            } else {
                                System.out.println(
                                        "\n" + ANSI_RED_BACKGROUND
                                                + "Livro " + bookAdded.getTitle() + " já exite!\n"
                                                + ANSI_RESET);
                            }
                        } else {
                            System.out.println(
                                    ANSI_RED_BACKGROUND
                                            + "Erro nas informações numericas!"
                                            + ANSI_RESET
                            );
                            continue;
                        }
                    }
                    continue;

                case 2:
                    System.out.print("Digite o titulo do livro que você quer buscar:");
                    String title = input.next();
                    ArrayList<Book> listBooksByTitle = library.searchByTitle(title);
                    if (listBooksByTitle.isEmpty()) {
                        System.out.println("Nunhum livro encontrado!");
                    } else {
                        for (Book book : listBooksByTitle) {
                            System.out.println(book.toString());
                        }
                    }
                    continue;
                case 3:
                    System.out.print("Digite o nome de um autor que vou procurar todos os livros dele:");
                    String autor = input.next();
                    ArrayList<Book> listBooksByAuthor = library.searchByAuthor(autor);
                    if(listBooksByAuthor.isEmpty()){
                        System.out.println(
                                ANSI_RED_BACKGROUND
                                        + "Nenhum livro encontrado"
                                        + ANSI_RESET
                        );
                    } else {
                        for (Book book : listBooksByAuthor) {
                            System.out.println(book.toString());
                        }
                    }
                    continue;
                case 4:
                    System.out.print("Digite o titulo do livro que vai ser alugado:");
                    String titleBook = input.next();
                    ArrayList<Book> listBooks = library.searchByTitle(titleBook);
                    if (listBooks.isEmpty()){
                        System.out.println(
                                ANSI_RED_BACKGROUND
                                        + "Nenhum livro encontrado"
                                        + ANSI_RESET
                        );
                    }else if(listBooks.size() == 1){
                        Book book = listBooks.getFirst();
                        if (book.getCopies()==0){
                            System.out.print("Livro encontrado mas não temos nenhuma copia disponivel");
                        }else {
                            System.out.print("Livro encontrado!\n" + listBooks.get(0).toString() +
                                    "\nDeseja alugar esse?"
                            );
                            String isBorrow = input.next();
                            if (isBorrow.equals("sim")){
                                book.borrowBook();
                                System.out.println("Livro alugado com sucesso");
                            }
                        }

                    }else {
                        System.out.println("Encontramos varios livros com esse nome:");
                        int cont = 1;
                        for (Book book: listBooks){
                            System.out.println(cont + "\n" +
                                    book.toString() + "\n");
                            cont++;

                        }
                        System.out.print("Ecolha um desses para alugar digitando a numeração dele. \n" +
                                "Se não for nenhum desses digite 0");
                        int chosenBookNumber = input.nextInt();
                        if(chosenBookNumber>0){
                            Book chosenBook = listBooks.get(chosenBookNumber-1);
                            if (chosenBook.getCopies()==0){
                                System.out.print("Livro encontrado mas não temos nenhuma copia disponivel");
                            }else {
                                System.out.print("Livro encontrado!\n" + chosenBook.toString() +
                                        "\nDeseja alugar esse?"
                                );
                                String isBorrow = input.next();
                                if (isBorrow.equals("sim")){
                                    chosenBook.borrowBook();
                                    System.out.println("Livro alugado com sucesso");
                                }
                            }
                        }
                    }
                    continue;
                case 5:
                    System.out.println("Digite o nome completo do livro para que possa ser devolvido:");
                    String returnedBook = input.nextLine();
                    if (library.returnBook(returnedBook)){
                        System.out.println("Livro devolvido com sucesso!");
                    }else {
                        System.out.println(
                                ANSI_RED_BACKGROUND
                                        + "Livro não encontrado"
                                        + ANSI_RESET
                        );
                    }
                    continue;
                case 6:
                    System.out.println("Os livros disponiveis são:");
                    for (Book book: library.listAvailableBooks()){
                        System.out.println(book.toString() + "\n");
                    }
            }
        }
    }
}