package com.nard.UserManagement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

  private boolean success;
  private String message;
  private Object data;

  // public ApiResponse(boolean success, String message, Object data) {
  // this.success = success;
  // this.message = message;
  // this.data = data;
  // }

}
