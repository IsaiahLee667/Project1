package exceptions;

public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound(int id){
        super("The resource with id " + id+ "was not found");
    }

    public ResourceNotFound(String status){
        super("The resource with status " + status + " was not found");
    }
}

