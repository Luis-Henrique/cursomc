package br.com.luish.cursomc.resources.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer time;
	private String msg;
	private Long timeStamp;
	
	public StandardError(Integer time, String msg, Long timeStamp) {
		super();
		this.time = time;
		this.msg = msg;
		this.timeStamp = timeStamp;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
}
