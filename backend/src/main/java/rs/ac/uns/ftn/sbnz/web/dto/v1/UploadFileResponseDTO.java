package rs.ac.uns.ftn.sbnz.web.dto.v1;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadFileResponseDTO {

    private String fileName;
    private String fileType;
    private long size;
    private long id;

    public UploadFileResponseDTO(String fileName, String fileType, long size, long id) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.size = size;
        this.id = id;
    }

}
