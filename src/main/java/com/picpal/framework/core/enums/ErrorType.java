package com.picpal.framework.core.enums;

public enum ErrorType implements IntentStateWithDescription {

    // System
    SYS0001("System error."),
    SYS0002("Param is not acceptable."),
    SYS0003("You need use 'bearer' token."),
    SYS0004("Signature error."),
    SYS0005("Format error."),
    SYS0006("Invalid client."),
    SYS0007("Invalid token."),
    SYS0008("Request too frequently."),

    // Commons
    SYS0100("%s error."),
    SYS0110("%s create failed."),
    SYS0111("%s already existing, %s taken."),
    SYS0120("%s find error."),
    SYS0121("%s find error, no %s exists."),
    SYS0122("Cannot find any %s by %s param."),
    SYS0130("%s update failed."),
    SYS0131("%s's %s update failed."),
    SYS0140("%s delete failed."),

    // File Processing
    FILE0001("File not found."),
    FILE0002("File upload failed."),
    FILE0003("File format not supported."),
    FILE0004("File read/write error."),

    // Database
    DB0001("Database connection error."),
    DB0002("Database timeout."),
    DB0003("Data integrity violation."),
    DB0004("Transaction failed."),

    // Network
    NET0001("Network error."),
    NET0002("Service unavailable."),
    NET0003("Gateway timeout."),

    // Authentication and Authorization
    AUTH0001("Authentication failed."),
    AUTH0002("Authorization failed."),
    AUTH0003("Access denied."),

    // Server Load
    SRV0001("Server overload."),
    SRV0002("Service temporarily unavailable."),

    // Unknown error.
    UNKNOWN("unknown error.");

    /**
     * Description
     */
    private final String description;

    /**
     * =
     * Constructor
     *
     * @param description description
     */
    ErrorType(String description) {
        this.description = description;
    }

    @Override
    public String description() {
        return this.description;
    }

    public static ErrorType parse(String name) {
        ErrorType[] errorTypes = ErrorType.values();
        for (ErrorType errorType : errorTypes) {
            if (errorType.name().equals(name)) {
                return errorType;
            }
        }
        return UNKNOWN;
    }
}