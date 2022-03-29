package org.tila.tilaexception;

public class ScannerException extends RuntimeException{
    public ScannerException(){
        super("Exception while scanning");
    }
    public ScannerException(String msg){
        super(msg);
    }
}
