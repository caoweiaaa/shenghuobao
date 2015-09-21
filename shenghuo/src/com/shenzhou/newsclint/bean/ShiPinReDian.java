package com.shenzhou.newsclint.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class ShiPinReDian implements Serializable{
	private ArrayList<ShiPin>V9LG4B3A0=new ArrayList<ShiPinReDian.ShiPin>();
	
    public ArrayList<ShiPin> getV9LG4B3A0() {
		return V9LG4B3A0;
	}

	public void setV9LG4B3A0(ArrayList<ShiPin> v9lg4b3a0) {
		V9LG4B3A0 = v9lg4b3a0;
	}

	public class ShiPin implements Serializable{
    	private String cover;
    	private String length;
    	private String m3u8Hd_url;
    	private String m3u8_url;
    	private String mp4Hd_url;
    	private String mp4_url;
    	private String playersize;
    	private String ptime;
    	private String replyCount;
    	private String title;
    	private String vid;
		public String getCover() {
			return cover;
		}
		public void setCover(String cover) {
			this.cover = cover;
		}
		public String getLength() {
			return length;
		}
		public void setLength(String length) {
			this.length = length;
		}
		public String getM3u8Hd_url() {
			return m3u8Hd_url;
		}
		public void setM3u8Hd_url(String m3u8Hd_url) {
			this.m3u8Hd_url = m3u8Hd_url;
		}
		public String getM3u8_url() {
			return m3u8_url;
		}
		public void setM3u8_url(String m3u8_url) {
			this.m3u8_url = m3u8_url;
		}
		public String getMp4Hd_url() {
			return mp4Hd_url;
		}
		public void setMp4Hd_url(String mp4Hd_url) {
			this.mp4Hd_url = mp4Hd_url;
		}
		public String getMp4_url() {
			return mp4_url;
		}
		public void setMp4_url(String mp4_url) {
			this.mp4_url = mp4_url;
		}
		public String getPlayersize() {
			return playersize;
		}
		public void setPlayersize(String playersize) {
			this.playersize = playersize;
		}
		public String getPtime() {
			return ptime;
		}
		public void setPtime(String ptime) {
			this.ptime = ptime;
		}
		public String getReplyCount() {
			return replyCount;
		}
		public void setReplyCount(String replyCount) {
			this.replyCount = replyCount;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getVid() {
			return vid;
		}
		public void setVid(String vid) {
			this.vid = vid;
		}
    	
    }
}
