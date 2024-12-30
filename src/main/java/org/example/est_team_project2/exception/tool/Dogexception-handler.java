// exception/CustomExceptionHandler.java
@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException e) {
        log.error("Invalid argument error: {}", e.getMessage());
        return ResponseEntity
            .badRequest()
            .body(new ErrorResponse("INVALID_ARGUMENT", e.getMessage()));
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralError(Exception e) {
        log.error("Unexpected error occurred", e);
        return ResponseEntity
            .internalServerError()
            .body(new ErrorResponse("INTERNAL_ERROR", "An unexpected error occurred"));
    }
}

// exception/ErrorResponse.java
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String code;
    private String message;
}