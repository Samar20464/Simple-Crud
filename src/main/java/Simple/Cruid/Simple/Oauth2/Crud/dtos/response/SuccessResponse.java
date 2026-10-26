package Simple.Cruid.Simple.Oauth2.Crud.dtos.response;

public class SuccessResponse extends AppResponse{
    public SuccessResponse(String message){
        super(true);
        addFullMessage(message);
    }
    public SuccessResponse(){
        this(null);
    }
}
