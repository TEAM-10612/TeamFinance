package com.financialboard.exception.user;

public class UnauthenticatedUserException extends RuntimeException{
   public UnauthenticatedUserException(String s){
       super(s);

   }
}
