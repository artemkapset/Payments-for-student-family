package edu.kapset.studentorder.exception;

// исключение для сетевых проблем: например, нет соединения с сервисом ГРН
public class TransportException extends Exception {
    public TransportException() {
    }

    public TransportException(String message) {
        super(message);
    }

    public TransportException(String message, Throwable cause) {
        super(message, cause);
    }
}
