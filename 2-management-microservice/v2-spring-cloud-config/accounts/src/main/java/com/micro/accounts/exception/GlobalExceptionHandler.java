package com.micro.accounts.exception;

import com.micro.accounts.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * Handles {@link MethodArgumentNotValidException} by returning an error response with a 400 status code.
     * <p>
     * This method is called when a {@link MethodArgumentNotValidException} is thrown by Spring.
     * It takes the exception and the current web request as parameters.
     * <p>
     * This method returns a ResponseEntity containing an error response with a 400 status code.
     * The body of the response is a Map containing the validation errors.
     * The keys of the map are the field names and the values are the validation messages.
     * <p>
     * For example, if the request contains a field "name" with an invalid value,
     * the error response will contain the following map:
     * <pre>
     * {
     *     "name": "name must be between 3 and 50 characters"
     * }
     * </pre>
     * <p>
     * The error response will have a 400 status code.
     *
     * @param ex the exception that was thrown
     * @param headers the current http headers
     * @param status the http status code of the response
     * @param request the current web request
     * @return a ResponseEntity containing an error response with a 400 status code
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

/**
 * Handles general exceptions by returning an error response with a 500 status code.
 * <p>
 * This method is a global exception handler that is triggered for any unhandled exceptions.
 * It returns a 500 Internal Server Error response with an error response body.
 *
 * @param exception the exception that was thrown
 * @param webRequest the current web request
 * @return a 500 Internal Server Error response with an error response body
 */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception,
                                                                  WebRequest webRequest) {
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles {@link ResourceNotFoundException} by returning an error response with a 404 status code.
     *
     * @param exception the exception that was thrown
     * @param webRequest the current web request
     * @return a ResponseEntity containing an ErrorResponseDto with details about the error
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                            WebRequest webRequest) {
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle {@link CustomerAlreadyExistsException} by returning an error response.
     * <p>
     * This method is a global exception handler and is triggered whenever a {@link CustomerAlreadyExistsException} is thrown.
     * It returns a 400 Bad Request response with an error response body.
     *
     * @param exception the exception to handle
     * @param webRequest the web request
     * @return a 400 Bad Request response with an error response body
     */
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException exception,
                                                                                 WebRequest webRequest) {
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }
}
