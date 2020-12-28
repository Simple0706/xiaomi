package com.xiaomi.pojo.vo;

public class Cart {
	@Override
	public String toString() {
		return "Cart [preId=" + preId + ", uid=" + uid + ", goodId=" + goodId + ", goodNum=" + goodNum + ", status="
				+ status + ", price=" + price + "]";
	}

	private Integer preId;

	private Integer uid;

	private Integer goodId;

	private Integer goodNum;

	private Integer status;

	private Float price;

	public Integer getPreId() {
		return preId;
	}

	public void setPreId(Integer preId) {
		this.preId = preId;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public Integer getGoodNum() {
		return goodNum;
	}

	public void setGoodNum(Integer goodNum) {
		this.goodNum = goodNum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
}