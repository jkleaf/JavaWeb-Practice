package bean;

public class TreeImage {

	private String id;
	
	private String name;
	
	private Double longitude;
	
	private Double latitude;
	
	private String u_account;
	
	private String record_date;

	public TreeImage(String id, String name,Double longitude, Double latitude, String u_account, String record_date) {
		this.id = id;
		this.name=name;
		this.longitude = longitude;
		this.latitude = latitude;
		this.u_account = u_account;
		this.record_date = record_date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getU_account() {
		return u_account;
	}

	public void setU_account(String u_account) {
		this.u_account = u_account;
	}

	public String getRecord_date() {
		return record_date;
	}

	public void setRecord_date(String record_date) {
		this.record_date = record_date;
	}

}
