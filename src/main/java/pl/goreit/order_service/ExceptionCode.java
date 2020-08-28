package pl.goreit.order_service;

public enum ExceptionCode {

    GOREIT_01("GoreIT.01", "Product does not exist"),
    GOREIT_02("GoreIT.02", "Category does not exist"),
    GOREIT_03("GoreIT.03", "Comment can be added only to available produdts"),
    GOREIT_04("GoreIT.04", "Order not found {} "),
    GOREIT_05("GoreIT.05", "Some Import already in progress"),
    GOREIT_06("GoreIT.06", "Order must contains orderlines");


    private final String message;
    private String code;

    private ExceptionCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
