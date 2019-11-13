package com.project.hubert.testersmatcher.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity hasn't been found")
public class NotFoundException extends RuntimeException {
}
