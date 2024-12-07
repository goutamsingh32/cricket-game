package Game.CricketGame.exceptionHanding;

public class BadRequestException  extends  RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
