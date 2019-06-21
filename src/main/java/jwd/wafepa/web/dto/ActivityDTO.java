package jwd.wafepa.web.dto;


//ovo je napravljeno kako ostali ne bi videli komentare admina vec ovj entitet se salje kroz rest
public class ActivityDTO {
	private Long id;
	private String name;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
