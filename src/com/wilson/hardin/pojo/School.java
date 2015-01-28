package com.wilson.hardin.pojo;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
public class School {
	public int gsId;
	public String name;
	public String type;
	public String gradeRange;
	public String city;
	public String state;
	public String address;
	public String phone;
	public String website;
	public String ncesId;
	public String overviewLink;
	public String ratingsLink;
	public String reviewsLink;
	public String schoolStatsLink;
	public int enrollment;
	public int parentRating;
	public int gsRating;
	
	@XmlElement(name="lat")
	public double latitude;
	@XmlElement(name="lon")
	public double longitude;
}
