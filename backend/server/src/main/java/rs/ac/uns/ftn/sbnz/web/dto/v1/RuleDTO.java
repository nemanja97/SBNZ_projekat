package rs.ac.uns.ftn.sbnz.web.dto.v1;

import javax.validation.constraints.NotEmpty;

public class RuleDTO {

    @NotEmpty
    private String path;

    @NotEmpty
    private String content;

    public RuleDTO(@NotEmpty String path, @NotEmpty String content) {
        this.path = path;
        this.content = content;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
