package rs.ac.uns.ftn.sbnz.web.dto.v1;


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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
