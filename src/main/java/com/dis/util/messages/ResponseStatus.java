package com.dis.util.messages;

public enum ResponseStatus {
   OK ("OK"),
   KO("KO");

   String status;

   ResponseStatus(String status) {
      this.status = status;
   }

   public String getStatus() {
      return status;
   }
}
