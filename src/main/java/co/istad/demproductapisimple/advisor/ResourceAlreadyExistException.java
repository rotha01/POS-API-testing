package co.istad.demproductapisimple.advisor;

//exception class use when resource already exist
public class ResourceAlreadyExistException extends  RuntimeException{
    public ResourceAlreadyExistException(String message){
        super(message);
    }
}
