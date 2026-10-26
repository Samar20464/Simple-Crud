package Simple.Cruid.Simple.Oauth2.Crud.dtos.response;

public class ErrorResponse extends  AppResponse{

    public ErrorResponse(String errorMessage){
        super(false);
        addFullMessage(errorMessage);
    }

}
