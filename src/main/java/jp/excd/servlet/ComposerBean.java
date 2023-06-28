package jp.excd.servlet;

public class ComposerBean {
	
	private String nickname;
	private String joined_date;
	private String gender;
	private String birthday;
	private String listener_count;
	private String language_type;
	private String composer_id;
	
	/**
	 * @return nickname
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @param nickname セットする nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	/**
	 * @return joined_date
	 */
	public String getJoined_date() {
		return joined_date;
	}
	/**
	 * @joined_date セットする joined_date
	 */
	public void setJoined_date(String joined_date) {
	
		this.joined_date = joined_date;
	}
	
	/**
	 * @return gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender セットする gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/**
	 * @return  birthday
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * @param  birthday セットする birthday
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	/**
	 * @return  listener_count
	 */
	public String getListener_count() {
		return listener_count;
	}
	/**
	 * @param  listener_count セットする listener_count
	 */
	public void setListener_count(String listener_count) {
		this.listener_count = listener_count;
	}
	
	/**
	 * @return  language_type
	 */
	public String getLanguage_type() {
		return language_type;
	}
	/**
	 * @param  language_type セットする language_type
	 */
	public void setLanguage_type(String language_type) {
		this.language_type = language_type;
	}
	
	/**
	 * @return  listener_count
	 */
	public String getComposer_id() {
		return composer_id;
	}
	/**
	 * @param  composer_id セットする composer_id
	 */
	public void setComposer_id(String composer_id) {
		this.composer_id = composer_id;
	}
}
