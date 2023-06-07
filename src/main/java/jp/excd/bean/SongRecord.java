package jp.excd.bean;

public class SongRecord {

	private String title;
	private long rating_total;
	private double rating_average;
	private long total_listen_count;
	private double release_datetime;
	private String image_file_name;
	private String song_id;
	private int image_file_height;
	private int image_file_width;

	/**
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title セットする title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return rating_total
	 */
	public long getRating_total() {
		return rating_total;
	}
	/**
	 * @param rating_total セットする rating_total
	 */
	public void setRating_total(long rating_total) {
		this.rating_total = rating_total;
	}
	/**
	 * @return rating_average
	 */
	public double getRating_average() {
		return rating_average;
	}
	/**
	 * @param rating_average セットする rating_average
	 */
	public void setRating_average(double rating_average) {
		this.rating_average = rating_average;
	}
	/**
	 * @return total_listen_count
	 */
	public long getTotal_listen_count() {
		return total_listen_count;
	}
	/**
	 * @param total_listen_count セットする total_listen_count
	 */
	public void setTotal_listen_count(long total_listen_count) {
		this.total_listen_count = total_listen_count;
	}
	/**
	 * @return release_datetime
	 */
	public double getRelease_datetime() {
		return release_datetime;
	}
	/**
	 * @param release_datetime セットする release_datetime
	 */
	public void setRelease_datetime(double release_datetime) {
		this.release_datetime = release_datetime;
	}
	/**
	 * @return image_file_name
	 */
	public String getImage_file_name() {
		return image_file_name;
	}
	/**
	 * @param image_file_name セットする image_file_name
	 */
	public void setImage_file_name(String image_file_name) {
		this.image_file_name = image_file_name;
	}
	/**
	 * @return song_id
	 */
	public String getSong_id() {
		return song_id;
	}
	/**
	 * @param song_id セットする song_id
	 */
	public void setSong_id(String song_id) {
		this.song_id = song_id;
	}
	/**
	 * @return image_file_height
	 */
	public int getImage_file_height() {
		return image_file_height;
	}
	/**
	 * @param image_file_height セットする image_file_height
	 */
	public void setImage_file_height(int image_file_height) {
		this.image_file_height = image_file_height;
	}
	/**
	 * @return image_file_width
	 */
	public int getImage_file_width() {
		return image_file_width;
	}
	/**
	 * @param image_file_width セットする image_file_width
	 */
	public void setImage_file_width(int image_file_width) {
		this.image_file_width = image_file_width;
	}

}

