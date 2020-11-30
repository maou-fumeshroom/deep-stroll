package ap.deepstroll.global;

import java.util.Date;

public class VerificationCode {

	public String code;
	private long genTime;
	private final long expireTime = 5 * 60;
	
	public VerificationCode(String code){
		this.code = code;
		this.genTime = (new Date().getTime()) / 1000;
	}
    
    public Boolean isExpire(){
		return ((new Date().getTime()) / 1000) - this.genTime < this.expireTime;
	}

	public String getCode(){
		return this.code;
	}
	
}
