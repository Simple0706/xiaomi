package com.alipay.config;

public class AlipayConfig {
	// 商户appid
	public static String APPID = "2021000116693204";
	// 私钥 pkcs8格式的
	public static String RSA_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC9HyLHNmmKJw8v0bSVka2e4VpErM0Jw/IZyQ97TKgx7+cJjUJ1lV8TN4ORAxHC7dqJD5D/DMDTbFx8/VDFT1+Ya/JA1azK3Wsks4l5Mqvlg+KKApPhrGY7I5e0kGVGZQldJoySq4f8R4EcZZZDSzF1HRCLX3NWsCph4VJakFmQzf39LyGrIXQEq9N6erUQ8c1A+rCBFRSF9qqfN7R6AaN6dAeXOZu34L0CXnrNoyu8vKJTzrzItprTef5CQGc6VIMRvxP73sA54zDzhcoOBLHyZpTP9ChhJl6F+nt/AMmwhZrWkvAcQXYAs3RZ8OwLlzhGNPZEXThnWOcxExEnm/FfAgMBAAECggEAKYu2eybj5nqQJoQd9KGND5MX3nBLlrKZ87oskXyUkmvOSFXk3KnUugb++xM+iqDv2h42TyFBFvD+HGMVpFK8gGJuVHncPLCJggWC1MIh6wie8oyAahoSVzmJcRwrc1xswy7dwLa3597ZaSCT8vKIyd55rPRqBQgsN1ZlXgnJ8zo8HyJxvpmPrSc4Vh1sGw3EBvx1zMo+BdU25f85zWpz0pn5678U7ahYIkgeoBNiJDviSwD3vWaQLefpjQiIjM0lcAuXQPVZpjQxW1AZcfqqEEczx1gwL3RoEgrrBXae/4ekey+BNkma/Xh3r4zB+Z1vW1Z5lG8jHD/ElynchA56+QKBgQD2ahB48YJX+Agg559k9/DcSJZRXjDzB8oOxzDG/BQTOFg4bDWFkED2vQ8kWmFeIQWQS6h8XmtHNhub+WltK0cLupASYMhJSZ4r/aTNbPlwbZd86cj1OduhHKSVcZH4c2QzyFA5j8KuwvY2SPMN7lfaFvyMqP+2P/KSwrlQ6IXovQKBgQDEeoSVrioxAEPvgT1SC5ArcLFjFE8/84KRuPFcwcudSoXZRdkh3BDVNr4L/uUWLmlx5NAfZ/67QTAXMb13wAl/JKp5vtp1hiJ+3H8fMePVaIypJXQhShj6W8Ata6nO3wT80HzsLnPEQKWybZb5gkZNnbw6Zg+496QNXBS6nHTqSwKBgQCfG9tpDKCaRriQ7p54ZrRquJ//CC/ZLUVmB25SfgBEbNRiElT42i1oZKk7UvSd//qEsqWnMJxajfCD5ScS66fjReKzSzToRZXRV+UrE9t4ofr4GEi/N02+mS0Ypg2X/IJOAWDz2gTeLVsnsDDo5OZrFv6ZoKIEvwPVidB5ErssjQKBgQC2mE4jdOrNAzwfl3JPj2N6m46fT76e5kWP+9qDo68BDML7u0ghiY0zOV7Bcy+fv55LNKSeq1021UxGIfm9EoFA4eh0oIQR+IiScYTKjuj36ePfRml4jo5MDXy6OXZkRSszcObH8BuyZYoYGnTWiNWhSFgnxbYS5kJWEB9Llh+ppQKBgDd500o0c5ddExYUDU5yGNRuezsfl/J7o1xzMxd9t1WGlBQBQ+OVbhJ9xXOlIZgHY/q2Kwul7SyUGySbEWiKVXPEVc1VOA1XaBkFO/mDI+HabGzj8gaVRP5LH2+kZneJFRfoFs2Rfi9lD/Glqg0sEuC6Na1npUDM1dJe5N5ocMVs";
	// 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/test1/notify_url.jsp";
	// 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
	public static String return_url = "http://localhost:8080/test1/return_url.jsp";
	// 请求网关地址
	public static String URL = "https://openapi.alipaydev.com/gateway.do";
	// 编码
	public static String CHARSET = "UTF-8";
	// 返回格式
	public static String FORMAT = "json";
	// 支付宝公钥
	public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvR8ixzZpiicPL9G0lZGtnuFaRKzNCcPyGckPe0yoMe/nCY1CdZVfEzeDkQMRwu3aiQ+Q/wzA02xcfP1QxU9fmGvyQNWsyt1rJLOJeTKr5YPiigKT4axmOyOXtJBlRmUJXSaMkquH/EeBHGWWQ0sxdR0Qi19zVrAqYeFSWpBZkM39/S8hqyF0BKvTenq1EPHNQPqwgRUUhfaqnze0egGjenQHlzmbt+C9Al56zaMrvLyiU868yLaa03n+QkBnOlSDEb8T+97AOeMw84XKDgSx8maUz/QoYSZehfp7fwDJsIWa1pLwHEF2ALN0WfDsC5c4RjT2RF04Z1jnMRMRJ5vxXwIDAQAB";
	// 日志记录目录
	public static String log_path = "D:/log";
	// RSA2
	public static String SIGNTYPE = "RSA2";
}
