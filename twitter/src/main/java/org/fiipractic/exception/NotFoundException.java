package org.fiipractic.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String entityType, Long id) {
        super("Entity " + entityType + " not found for id: " + id);
    }
}
