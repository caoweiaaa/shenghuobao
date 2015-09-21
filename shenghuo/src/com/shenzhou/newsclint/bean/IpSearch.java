package com.shenzhou.newsclint.bean;
/**
 * {
    "ret": 1,
    "start": "106.2.160.0",
    "end": "106.2.171.255",
    "country": "中国",
    "province": "北京",
    "city": "北京",
    "district": "",
    "isp": "联通",
    "type": "企业",
    "desc": "北京优位风尚信息公司联通节点联通"
}
 * @author zuoyou
 *
 */
public class IpSearch {
     private int ret;
     private String start;
     private String end;
     private String country;
     private String province;
     private String city;
     private String district;
     private String isp;
     private String type;
     private String desc;
	public int getRet() {
		return ret;
	}
	public void setRet(int ret) {
		this.ret = ret;
	}
     
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getIsp() {
		return isp;
	}
	public void setIsp(String isp) {
		this.isp = isp;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
     
}
