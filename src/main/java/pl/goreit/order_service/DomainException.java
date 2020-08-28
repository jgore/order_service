package pl.goreit.order_service;

public class DomainException extends Exception {

    public DomainException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
    }
}
