package com.sms.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelationPojo {

    private Long relationId;

    private Long screenId;

    private Long authoritiesId;

    private Long roleId;
}
