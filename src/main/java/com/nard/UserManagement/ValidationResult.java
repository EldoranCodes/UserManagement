package com.nard.UserManagement;

import java.util.HashMap;
import java.util.Map;

public class ValidationResult {

  private Boolean valid = true;
  private Map<String, String> errorMap = new HashMap<>();

  void addError(String field, String errorMessage) {
    valid = false;
    errorMap.put(field, errorMessage);
  }

  public boolean isValid() {
    return valid;
  }

  public Map<String, String> getErrors() {
    return errorMap;
  }

  // Optional: convenient string version for logs or simple API messages
  public String toErrorMessage() {
    StringBuilder sb = new StringBuilder();
    errorMap.forEach((field, msg) -> sb.append(field).append(": ").append(msg).append("; "));
    return sb.toString();
  }
}
