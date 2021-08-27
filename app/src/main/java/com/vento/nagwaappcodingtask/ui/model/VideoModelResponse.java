package com.vento.nagwaappcodingtask.ui.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class VideoModelResponse{

	@SerializedName("VideoModelResponse")
	private List<VideoModelResponseItem> videoModelResponse;

	public void setVideoModelResponse(List<VideoModelResponseItem> videoModelResponse){
		this.videoModelResponse = videoModelResponse;
	}

	public List<VideoModelResponseItem> getVideoModelResponse(){
		return videoModelResponse;
	}

	@Override
 	public String toString(){
		return 
			"VideoModelResponse{" + 
			"videoModelResponse = '" + videoModelResponse + '\'' + 
			"}";
		}
}