package com.xxy.xjsearchbacked.model.dto.search;

import com.xxy.xjsearchbacked.common.PageRequest;
import lombok.Data;

@Data
public class SearchParamsRequest extends PageRequest {

    String category;

    String  searchText;

}
