package phat.jwtytb.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseObject {
    private String status,
            message;
    private Object data;
}
