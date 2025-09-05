package sv.edu.udb.controller.responce;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import java.time.LocalDate;

@Getter
@Setter
@Builder(toBuilder = true)
@FieldNameConstants
public class PostResponce {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    private String title;
    private LocalDate postDate;
}
