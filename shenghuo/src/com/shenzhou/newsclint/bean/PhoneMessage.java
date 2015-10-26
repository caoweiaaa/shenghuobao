package com.shenzhou.newsclint.bean;
/**
 * 
phone({
    "meta": {
        "result": "0",
        "result_info": "",
        "jump_url": ""
    },
    "data": {
        "operator": "\u8054\u901a",
        "area": "\u5317\u4eac",
        "area_operator": "\u5317\u4eac\u8054\u901a",
        "support_price": {
            "1000": "1013",
            "2000": "1986",
            "3000": "2970",
            "5000": "4950",
            "10000": "9900",
            "30000": "29699"
        }
    }
})
 * @author zuoyou
 *
 */
public class PhoneMessage {
	private Meta meta;
	private Data data;
    public class Meta{
    	private String result;
    	private String result_info;
    	private String jump_url;
		public String getResult() {
			return result;
		}
		public void setResult(String result) {
			this.result = result;
		}
		public String getResult_info() {
			return result_info;
		}
		public void setResult_info(String result_info) {
			this.result_info = result_info;
		}
		public String getJump_url() {
			return jump_url;
		}
		public void setJump_url(String jump_url) {
			this.jump_url = jump_url;
		}
    	
    }
    public class Data{
    	private String operator;
    	private String area;
    	private String area_operator;
		public String getOperator() {
			return operator;
		}
		public void setOperator(String operator) {
			this.operator = operator;
		}
		public String getArea() {
			return area;
		}
		public void setArea(String area) {
			this.area = area;
		}
		public String getArea_operator() {
			return area_operator;
		}
		public void setArea_operator(String area_operator) {
			this.area_operator = area_operator;
		}
    	
    }
	public Meta getMeta() {
		return meta;
	}
	public void setMeta(Meta meta) {
		this.meta = meta;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
    
}
