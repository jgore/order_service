package pl.goreit.order_service;

public enum CategoryName {

    MOTORYZACJA("Motoryzacja"),
    ELEKTRONIKA("Elektronika"),
    USLUGI("USLUGI"),
    IT("IT"),
    DZIECKO("DZIECKO");

    private String value;

    CategoryName(String value) {
        this.value = value;
    }
}
