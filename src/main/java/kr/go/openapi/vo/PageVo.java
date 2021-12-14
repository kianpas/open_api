package kr.go.openapi.vo;

import java.util.List;

import com.google.gson.JsonArray;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageVo {

	private int page;
	private int perPage;
	private int totalCount;
	private int currentCount;
	private int matchCount;
	private JsonArray data;
	
	
}
