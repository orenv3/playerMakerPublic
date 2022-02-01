package com.playermaker.errorsHandler;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	public ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		pageNotFoundLogger.warn(ex.getMessage());

		Set<HttpMethod> supportedMethods = ex.getSupportedHttpMethods();
		if (!CollectionUtils.isEmpty(supportedMethods)) {
			headers.setAllow(supportedMethods);
		}
		ErrorOutput errOutput = new ErrorOutput();
		errOutput.setStatus(status.getReasonPhrase());
		errOutput.setDescription(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errOutput);
//		return handleExceptionInternal(ex, null, headers, status, request);
	}

	/**
	 * Customize the response for HttpMediaTypeNotSupportedException.
	 * <p>This method sets the "Accept" header and delegates to
	 * {@link #handleExceptionInternal}.
	 * @param ex the exception
	 * @param headers the headers to be written to the response
	 * @param status the selected response status
	 * @param request the current request
	 * @return a {@code ResponseEntity} instance
	 */
	public ResponseEntity<Object> handleHttpMediaTypeNotSupported(
			HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<MediaType> mediaTypes = ex.getSupportedMediaTypes();
		if (!CollectionUtils.isEmpty(mediaTypes)) {
			headers.setAccept(mediaTypes);
			if (request instanceof ServletWebRequest) {
				ServletWebRequest servletWebRequest = (ServletWebRequest) request;
				if (HttpMethod.PATCH.equals(servletWebRequest.getHttpMethod())) {
					headers.setAcceptPatch(mediaTypes);
				}
			}
		}
		ErrorOutput errOutput = new ErrorOutput();
		errOutput.setStatus(status.getReasonPhrase());
		errOutput.setDescription(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errOutput);
//		return handleExceptionInternal(ex, null, headers, status, request);
	}

	/**
	 * Customize the response for HttpMediaTypeNotAcceptableException.
	 * <p>This method delegates to {@link #handleExceptionInternal}.
	 * @param ex the exception
	 * @param headers the headers to be written to the response
	 * @param status the selected response status
	 * @param request the current request
	 * @return a {@code ResponseEntity} instance
	 */
	public ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
			HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ErrorOutput errOutput = new ErrorOutput();
		errOutput.setStatus(status.getReasonPhrase());
		errOutput.setDescription(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errOutput);
//		return handleExceptionInternal(ex, null, headers, status, request);
	}

	/**
	 * Customize the response for MissingPathVariableException.
	 * <p>This method delegates to {@link #handleExceptionInternal}.
	 * @param ex the exception
	 * @param headers the headers to be written to the response
	 * @param status the selected response status
	 * @param request the current request
	 * @return a {@code ResponseEntity} instance
	 * @since 4.2
	 */
	public ResponseEntity<Object> handleMissingPathVariable(
			MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ErrorOutput errOutput = new ErrorOutput();
		errOutput.setStatus(status.getReasonPhrase());
		errOutput.setDescription(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errOutput);
//		return handleExceptionInternal(ex, null, headers, status, request);
	}

	/**
	 * Customize the response for MissingServletRequestParameterException.
	 * <p>This method delegates to {@link #handleExceptionInternal}.
	 * @param ex the exception
	 * @param headers the headers to be written to the response
	 * @param status the selected response status
	 * @param request the current request
	 * @return a {@code ResponseEntity} instance
	 */
	public ResponseEntity<Object> handleMissingServletRequestParameter(
			MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ErrorOutput errOutput = new ErrorOutput();
		errOutput.setStatus(status.getReasonPhrase());
		errOutput.setDescription(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errOutput);
//		return handleExceptionInternal(ex, null, headers, status, request);
	}

	/**
	 * Customize the response for ServletRequestBindingException.
	 * <p>This method delegates to {@link #handleExceptionInternal}.
	 * @param ex the exception
	 * @param headers the headers to be written to the response
	 * @param status the selected response status
	 * @param request the current request
	 * @return a {@code ResponseEntity} instance
	 */
	public ResponseEntity<Object> handleServletRequestBindingException(
			ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorOutput errOutput = new ErrorOutput();
		errOutput.setStatus(status.getReasonPhrase());
		errOutput.setDescription(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errOutput);
		
//		return handleExceptionInternal(ex, null, headers, status, request);
	}

	/**
	 * Customize the response for ConversionNotSupportedException.
	 * <p>This method delegates to {@link #handleExceptionInternal}.
	 * @param ex the exception
	 * @param headers the headers to be written to the response
	 * @param status the selected response status
	 * @param request the current request
	 * @return a {@code ResponseEntity} instance
	 */
	public ResponseEntity<Object> handleConversionNotSupported(
			ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorOutput errOutput = new ErrorOutput();
		errOutput.setStatus(status.getReasonPhrase());
		errOutput.setDescription(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errOutput);
		
//		return handleExceptionInternal(ex, null, headers, status, request);
	}

	/**
	 * Customize the response for TypeMismatchException.
	 * <p>This method delegates to {@link #handleExceptionInternal}.
	 * @param ex the exception
	 * @param headers the headers to be written to the response
	 * @param status the selected response status
	 * @param request the current request
	 * @return a {@code ResponseEntity} instance
	 */
	public ResponseEntity<Object> handleTypeMismatch(
			TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorOutput errOutput = new ErrorOutput();
		errOutput.setStatus(status.getReasonPhrase());
		errOutput.setDescription(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errOutput);
//		return handleExceptionInternal(ex, null, headers, status, request);
	}

	/**
	 * Customize the response for HttpMessageNotReadableException.
	 * <p>This method delegates to {@link #handleExceptionInternal}.
	 * @param ex the exception
	 * @param headers the headers to be written to the response
	 * @param status the selected response status
	 * @param request the current request
	 * @return a {@code ResponseEntity} instance
	 */
	public ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		handleExceptionInternal(ex, null, headers, status, request);
		ErrorOutput errOutput = new ErrorOutput();
		errOutput.setStatus(status.getReasonPhrase());
		errOutput.setDescription(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errOutput);
	}

	/**
	 * Customize the response for HttpMessageNotWritableException.
	 * <p>This method delegates to {@link #handleExceptionInternal}.
	 * @param ex the exception
	 * @param headers the headers to be written to the response
	 * @param status the selected response status
	 * @param request the current request
	 * @return a {@code ResponseEntity} instance
	 */
	public ResponseEntity<Object> handleHttpMessageNotWritable(
			HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorOutput errOutput = new ErrorOutput();
		errOutput.setStatus(status.getReasonPhrase());
		errOutput.setDescription(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errOutput);
		
//		return handleExceptionInternal(ex, null, headers, status, request);
	}

	/**
	 * Customize the response for MethodArgumentNotValidException.
	 * <p>This method delegates to {@link #handleExceptionInternal}.
	 * @param ex the exception
	 * @param headers the headers to be written to the response
	 * @param status the selected response status
	 * @param request the current request
	 * @return a {@code ResponseEntity} instance
	 */
	public ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

//		Throwable rootCause = ex.getBindingResult().gete;
//		String f=rootCause.getMessage();
//		if(rootCause.getMessage().toString()!=null)
//			errOutput.setDescription(ex.getCause().getMessage());
		ErrorOutput errOutput = new ErrorOutput();
		errOutput.setStatus(status.getReasonPhrase());
		errOutput.setDescription(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errOutput);
//		return handleExceptionInternal(ex, null, headers, status, request);
	}

	/**
	 * Customize the response for MissingServletRequestPartException.
	 * <p>This method delegates to {@link #handleExceptionInternal}.
	 * @param ex the exception
	 * @param headers the headers to be written to the response
	 * @param status the selected response status
	 * @param request the current request
	 * @return a {@code ResponseEntity} instance
	 */
	public ResponseEntity<Object> handleMissingServletRequestPart(
			MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorOutput errOutput = new ErrorOutput();
		errOutput.setStatus(status.getReasonPhrase());
		errOutput.setDescription(ex.getMessage());
		errOutput.setStatus(status.getReasonPhrase());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errOutput);
		
//		return handleExceptionInternal(ex, null, headers, status, request);
	}

	/**
	 * Customize the response for BindException.
	 * <p>This method delegates to {@link #handleExceptionInternal}.
	 * @param ex the exception
	 * @param headers the headers to be written to the response
	 * @param status the selected response status
	 * @param request the current request
	 * @return a {@code ResponseEntity} instance
	 */
	public ResponseEntity<Object> handleBindException(
			BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorOutput errOutput = new ErrorOutput();
		errOutput.setStatus(status.getReasonPhrase());
		errOutput.setDescription(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errOutput);
		
//		return handleExceptionInternal(ex, null, headers, status, request);
	}

	/**
	 * Customize the response for NoHandlerFoundException.
	 * <p>This method delegates to {@link #handleExceptionInternal}.
	 * @param ex the exception
	 * @param headers the headers to be written to the response
	 * @param status the selected response status
	 * @param request the current request
	 * @return a {@code ResponseEntity} instance
	 * @since 4.0
	 */
	public ResponseEntity<Object> handleNoHandlerFoundException(
			NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorOutput errOutput = new ErrorOutput();
		errOutput.setStatus(status.getReasonPhrase());
		errOutput.setDescription(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errOutput);
		
//		return handleExceptionInternal(ex, null, headers, status, request);
	}

	/**
	 * Customize the response for AsyncRequestTimeoutException.
	 * <p>This method delegates to {@link #handleExceptionInternal}.
	 * @param ex the exception
	 * @param headers the headers to be written to the response
	 * @param status the selected response status
	 * @param webRequest the current request
	 * @return a {@code ResponseEntity} instance
	 * @since 4.2.8
	 */
	@Nullable
	public ResponseEntity<Object> handleAsyncRequestTimeoutException(
			AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {

		if (webRequest instanceof ServletWebRequest) {
			ServletWebRequest servletWebRequest = (ServletWebRequest) webRequest;
			HttpServletResponse response = servletWebRequest.getResponse();
			if (response != null && response.isCommitted()) {
				if (logger.isWarnEnabled()) {
					logger.warn("Async request timed out");
				}
				return null;
			}
		}

		ErrorOutput errOutput = new ErrorOutput();
		errOutput.setStatus(status.getReasonPhrase());
		errOutput.setDescription(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errOutput);
		
//		return handleExceptionInternal(ex, null, headers, status, webRequest);
	}

	/**
	 * A single place to customize the response body of all exception types.
	 * <p>The default implementation sets the {@link WebUtils#ERROR_EXCEPTION_ATTRIBUTE}
	 * request attribute and creates a {@link ResponseEntity} from the given
	 * body, headers, and status.
	 * @param ex the exception
	 * @param body the body for the response
	 * @param headers the headers for the response
	 * @param status the response status
	 * @param request the current request
	 */
	public ResponseEntity<Object> handleExceptionInternal(
			Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		}
		ErrorOutput errOutput = new ErrorOutput();
		errOutput.setStatus(status.getReasonPhrase());
		errOutput.setDescription(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errOutput);
		
//		return new ResponseEntity<>(body, headers, status);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
		ErrorOutput errOutput = new ErrorOutput();
		errOutput.setStatus("500 Internal Server Error");
		errOutput.setDescription(ex.getMessage());
	    return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
