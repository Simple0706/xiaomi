package com.xiaomi.pojo.vo;

public class CartGood {
	
		

		private Integer preId;

		private Integer uid;

		private Integer goodId;

		private Integer goodNum;

		private Integer status;

		private Float price;
		
		private Good g;
		
		public CartGood() {
			super();
		}
		public CartGood(Cart cart,Good good){
			this.preId=cart.getPreId();
			this.uid=cart.getUid();
			this.goodId=cart.getGoodId();
			this.goodNum=cart.getGoodNum();
			this.status=cart.getStatus();
			this.price=cart.getPrice();
			this.g= good;
		}
		public CartGood(Integer preId, Integer uid, Integer goodId, Integer goodNum, Integer status, Float price,
				Good g) {
			super();
			this.preId = preId;
			this.uid = uid;
			this.goodId = goodId;
			this.goodNum = goodNum;
			this.status = status;
			this.price = price;
			this.g = g;
		}

		@Override
		public String toString() {
			return "CartGood [preId=" + preId + ", uid=" + uid + ", goodId=" + goodId + ", goodNum=" + goodNum
					+ ", status=" + status + ", price=" + price + ", g=" + g + "]";
		}

		public Good getG() {
			return g;
		}

		public void setG(Good g) {
			this.g = g;
		}

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
