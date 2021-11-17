package com.financialboard.exception;

public class UnauthenticatedUserException extends RuntimeException{
   public UnauthenticatedUserException(String s){
       super(s);
   }
}
