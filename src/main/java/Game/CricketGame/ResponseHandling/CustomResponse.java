package Game.CricketGame.ResponseHandling;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CustomResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private HttpStatus status;

    public CustomResponse(boolean success, String message, T data, HttpStatus status) {
        this.status = status;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> CustomResponse<T> success(String message, T data, HttpStatus status) {
        return new CustomResponse<>(true, message, data, status);
    }

    public static <T> CustomResponse<T> failure(String message, T data, HttpStatus status) {
        return new CustomResponse<>(false, message, null, status);
    }
}
